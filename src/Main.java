import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // Submit Runnable task
        executorService.submit(() -> {
            System.out.println("Runnable Task Executed");
        });

        // Submit Callable task and get the result
        Future<Integer> future = executorService.submit(() -> {
            System.out.println("Callable Task Executed");
            return 42;  // Returning a result from the Callable
        });

        // Get the result of the Callable
        Integer result = future.get();  // Blocks until the result is available
        System.out.println("Result of Callable: " + result);

        // Shut down the ExecutorService gracefully

        //Running tasks continue to execute and Pending tasks (queued but not started) will execute.
        executorService.shutdown();
        if (executorService.awaitTermination(5, TimeUnit.SECONDS)) {
            System.out.println("All tasks completed.");
        } else {
            System.out.println("Timeout: Forcing shutdown.");

            //Attempts to stop running tasks by interrupting them and Pending tasks are returned and do not execute.
            executorService.shutdownNow();
        }


        //creates a thread pool that can dynamically allocate new threads when needed.
        // It does not impose a fixed limit on the number of threads and
        // will reuse previously created threads when they become available.
        // Create a cached thread pool
        //this can cause unlimited threads
        ExecutorService executor = Executors.newCachedThreadPool();

        // Submit multiple tasks
        for (int i = 0; i < 5; i++) {
            executor.submit(new Task(i));
        }

        // Shutdown the executor after tasks are submitted
        executor.shutdown();


        //even if we submit multiple  tasks , they will get executed one by one
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        // Submit multiple tasks
        for (int i = 0; i < 5; i++) {
            singleThreadExecutor.submit(new Task(i));
        }

        // Shutdown the executor after tasks are submitted
        singleThreadExecutor.shutdown();


        // we also has tryLock() in this do as expected if we cannot create lock
        //ReentrantLock lock = new ReentrantLock(true); // Fair lock we need to pass true in constructor
        //In a fair lock, the longest-waiting thread gets the lock first,
        // but fair locking can lead to reduced throughput as the system must manage the queue of waiting threads.
        ReentrantLockExample example = new ReentrantLockExample();

        // Create and start multiple threads that will use the same lock
        Thread t1 = new Thread(example::performTask, "Thread-1");
        Thread t2 = new Thread(example::performTask, "Thread-2");

        t1.start();
        t2.start();


        //Read and Write Locks:
        //Read Lock: Allows multiple threads to read the shared resource simultaneously, as long as there are no threads writing to it.
        //Write Lock: Allows only one thread to write to the shared resource. During this time, no other thread can read or write.

        ReadWriteLockExample example1 = new ReadWriteLockExample();

        // Create threads for reading and writing
        Thread writerThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                example1.writeData(i);
                try {
                    Thread.sleep(500); // Simulate time taken to write
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Writer-Thread");

        Thread readerThread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                example1.readData();
                try {
                    Thread.sleep(300); // Simulate time taken to read
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Reader-Thread-1");

        Thread readerThread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                example1.readData();
                try {
                    Thread.sleep(400); // Simulate time taken to read
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Reader-Thread-2");

        writerThread.start();
        readerThread1.start();
        readerThread2.start();

        ConditionExample example2 = new ConditionExample();

        Thread waiter = new Thread(example2::awaitThreshold, "Waiter-Thread");
        Thread incrementer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                example2.increment();
                try {
                    Thread.sleep(500); // Simulate time taken to increment
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "Incrementer-Thread");

        waiter.start();
        incrementer.start();


        //atomic integer example
        //private AtomicLong counter = new AtomicLong(0); similar t this
        //AtomicReference<T> is used to hold an object reference atomically.
        // It’s particularly useful when you want to perform atomic operations on non-primitive objects,
        // such as updating a reference to a mutable object or doing compare-and-swap (CAS) on object references.

        AtomicInteger count = new AtomicInteger(0);


        Thread at1 = new Thread(() -> {
            int newValue = count.incrementAndGet(); // Atomically increments by 1
            System.out.println(Thread.currentThread().getName() + " incremented count to: " + newValue);
        }, "Thread-1");
        Thread at2 = new Thread(() -> {
            int newValue = count.incrementAndGet(); // Atomically increments by 1
            System.out.println(Thread.currentThread().getName() + " incremented count to: " + newValue);
        }, "Thread-2");

        at1.start();
        at2.start();


        AtomicReferenceExample example10 = new AtomicReferenceExample();

        // Create new person objects
        Person newPerson1 = new Person("Alice");
        Person newPerson2 = new Person("Bob");
        Person oldPerson = new Person("John");

        // Thread that attempts to update the person reference using compareAndSet
        Thread ar1 = new Thread(() -> example10.compareAndSetPerson(oldPerson, newPerson1), "Thread-1");

        // Another thread that directly updates the person reference
        Thread ar2 = new Thread(() -> example10.updatePerson(newPerson2), "Thread-2");

        // Start both threads
        ar1.start();
        ar2.start();

        // Wait for threads to complete
        ar1.join();
        ar2.join();

        // Display final person reference
        System.out.println("Final person is: " + example10.getPerson());


        //Concurrent HashMap does not support  null keys and null values
        //It provides atomic operations like putIfAbsent, computeIfAbsent, and replace.
        //The code submits five threads, each trying to add 1000 entries to the HashMap.
        // However, due to the lack of synchronization, when two or more threads try to update the same bucket simultaneously,
        // it can lead to unpredictable behavior. instead of 1000 entries we will get 985 something like that
        ConcurrentHashMapExample exampleMap = new ConcurrentHashMapExample();

        // Thread 1 adds employees to the map
        Thread ch1 = new Thread(() -> {
            exampleMap.addEmployee("Alice", 50000);
            exampleMap.addEmployee("Bob", 60000);
        }, "Thread-1");

        // Thread 2 retrieves employee salaries
        Thread ch2 = new Thread(() -> {
            exampleMap.getSalary("Alice");
            exampleMap.getSalary("Bob");
        }, "Thread-2");

        // Thread 3 updates salary of Bob
        Thread ch3 = new Thread(() -> exampleMap.updateSalaryIfPresent("Bob", 70000), "Thread-3");

        // Thread 4 attempts to remove Alice based on salary
        Thread ch4 = new Thread(() -> exampleMap.removeEmployeeIfSalaryMatches("Alice", 50000), "Thread-4");

        // Start the threads
        ch1.start();
        ch2.start();
        ch3.start();
        ch4.start();

        // Join threads to ensure main waits for completion
        ch1.join();
        ch2.join();
        ch3.join();
        ch4.join();

        // Final state of the map
        System.out.println("Final employee map: " + exampleMap.getMap());


        //CopyOnWriteArrayList
        //concurrent modification error wont come
        //Adds new elements ("David" and "Eve") to the list while the reader thread is iterating.
        // Each write creates a new copy of the list, so the reader will not see the new elements during iteration,
        // but subsequent iterations after modifications will reflect the new list.
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        // Add elements to the list
        list.add("Alice");
        list.add("Bob");
        list.add("Charlie");

        // Create a thread that reads from the list
        Thread readerThread = new Thread(() -> {
            for (String name : list) {
                System.out.println(Thread.currentThread().getName() + " reading: " + name);
                try {
                    Thread.sleep(100);  // Simulate some work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Create a thread that modifies the list
        Thread writerThread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " adding: David");
            list.add("David");
            System.out.println(Thread.currentThread().getName() + " adding: Eve");
            list.add("Eve");
        });

        // Start the threads
        readerThread.start();
        writerThread1.start();

        // Wait for the threads to finish
        readerThread.join();
        writerThread1.join();

        // Final state of the list
        System.out.println("Final list: " + list);


        //BlockingQueue
        // Create a BlockingQueue with a capacity of 5
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

        // Producer Thread: Adds elements to the queue
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    System.out.println("Producing: " + i);
                    queue.put(i); // Adds element, waits if the queue is full
                    Thread.sleep(500); // Simulate some delay
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Consumer Thread: Retrieves elements from the queue
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    Integer item = queue.take(); // Retrieves element, waits if the queue is empty
                    System.out.println("Consuming: " + item);
                    Thread.sleep(1000); // Simulate some delay
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Start both producer and consumer threads
        producer.start();
        consumer.start();

        // Wait for both threads to complete
        producer.join();
        consumer.join();


        //Future example
        ExecutorService executorFuture = Executors.newSingleThreadExecutor();

        // Submit a Callable task that returns a result
        Future<Integer> futureResult = executorFuture.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                // Simulate long-running task
                Thread.sleep(2000);
                return 42;
            }
        });

        // Do something else while the task runs
        System.out.println("Task submitted. Doing other work...");

        // Now we can retrieve the result using Future.get() (it will wait if necessary)
        Integer result1 = futureResult.get(); // This will block until the task is done
        System.out.println("Result of the task: " + result1);

        // Shutdown the executor
        executorFuture.shutdown();


        //ScheduledExecutedService allows us to schedule tasks to run after a delay or at fixed intervals.
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Schedule a task to run after a delay of 3 seconds
        scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("Task executed after 3 seconds delay.");
            }
        }, 3, TimeUnit.SECONDS);

        // Schedule a task to run repeatedly with an initial delay of 1 second, then every 2 seconds
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("Task executed at fixed rate of 2 seconds.");
            }
        }, 1, 2, TimeUnit.SECONDS);

        // Optionally, you can shut down the scheduler after some time
        scheduler.schedule(() -> {
            System.out.println("Shutting down scheduler...");
            scheduler.shutdown();
        }, 10, TimeUnit.SECONDS);


        //Semaphore is a synchronization aid that controls access to a shared resource through the use of a counter.
        // It allows a specified number of threads to access the resource concurrently.
        // Create a Semaphore with 2 permits
        //If your thread catches InterruptedException and does not reset the interrupted status,
        // any subsequent checks for whether the thread was interrupted will return false,
        // which could lead to undesired behavior, especially in multi-threaded environments.
        Semaphore semaphore = new Semaphore(2);

        Runnable task = () -> {
            try {
                // Acquire a permit
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + " acquired a permit.");

                // Simulate work
                Thread.sleep(2000);

                System.out.println(Thread.currentThread().getName() + " releasing a permit.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                // Release the permit
                semaphore.release();
            }
        };

        // Start 5 threads
        for (int i = 0; i < 5; i++) {
            new Thread(task, "Thread-" + (i + 1)).start();
        }

        // CountDownLatch is initialized with a count of 3, meaning the main thread will wait for 3 worker threads to finish their tasks.
        //Each worker thread simulates some work and calls countDown() when it finishes.
        //The main thread waits on latch.await(), which blocks until the count reaches zero.

        CountDownLatch latch = new CountDownLatch(3);

        Runnable task1 = () -> {
            try {
                // Simulate work
                Thread.sleep((long) (Math.random() * 1000));
                System.out.println(Thread.currentThread().getName() + " finished work.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                // Decrease the count of the latch
                latch.countDown();
            }
        };

        // Start 3 threads
        for (int i = 0; i < 3; i++) {
            new Thread(task1, "Worker-" + (i + 1)).start();
        }

        // Wait for all threads to finish
        latch.await();
        System.out.println("All workers have finished their tasks.");


        //A CyclicBarrier is set up for 3 threads. Once all 3 threads reach the barrier, they will proceed together.
        //The Runnable task simulates work and then calls barrier.await(), which causes the thread to wait at the barrier until all participating threads have called it.
        //Once all threads arrive, the barrier releases them, and the specified action (printing a message) is executed.
        // Create a CyclicBarrier for 3 threads

        //Main difference of countdown latch is aftee count becoming 0 we cannot make 3 or some count automatically by cyclic barrier does by giving any 3 threads access it
        CyclicBarrier barrier = new CyclicBarrier(3, () -> {
            System.out.println("All parties have arrived at the barrier. Proceeding...");
        });

        Runnable taskCyclic = () -> {
            try {
                // Simulate work
                Thread.sleep((long) (Math.random() * 1000));
                System.out.println(Thread.currentThread().getName() + " reached the barrier.");

                // Wait at the barrier
                barrier.await();
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        };

        // Start 3 threads
        for (int i = 0; i < 9; i++) {
            new Thread(taskCyclic, "Worker-" + (i + 1)).start();
        }

        //out put will be like this
        //Worker-6 reached the barrier.
        //Worker-9 reached the barrier.
        //Worker-5 reached the barrier.
        //All parties have arrived at the barrier. Proceeding...
        //Worker-8 reached the barrier.
        //Worker-2 reached the barrier.
        //Worker-1 reached the barrier.
        //All parties have arrived at the barrier. Proceeding...
        //Worker-7 reached the barrier.
        //Worker-3 reached the barrier.
        //Worker-4 reached the barrier.
        //All parties have arrived at the barrier. Proceeding...


        //The Fork/Join framework is a powerful concurrency framework in Java designed to take advantage of multiple processors for parallel computing.
        // It allows you to split a task into smaller subtasks (forking) and then join the results of those subtasks together once they have been completed.
        // This approach is particularly effective for tasks that can be recursively divided into smaller tasks, making it ideal for divide-and-conquer algorithms.


        ThreadLocal<String> threadLocalValue = ThreadLocal.withInitial(() -> "Default Value");

        // Create two threads that will use the ThreadLocal variable
        Thread threadl1 = new Thread(() -> {
            // Set a value for thread 1
            threadLocalValue.set("Thread 1 Value");
            System.out.println("Thread 1: " + threadLocalValue.get());
        });

        Thread threadl2 = new Thread(() -> {
            // Set a value for thread 2
            threadLocalValue.set("Thread 2 Value");
            System.out.println("Thread 2: " + threadLocalValue.get());
        });

        // Start both threads
        threadl1.start();
        threadl2.start();

        try {
            // Wait for both threads to finish
            threadl1.join();
            threadl2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the default value in the main thread
        System.out.println("Main Thread: " + threadLocalValue.get());
    }
}