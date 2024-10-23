import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    // Create an instance of ReentrantLock
    private final ReentrantLock lock = new ReentrantLock();

    public void performTask() {
        lock.lock(); // Acquire the lock
        try {
            // Critical section (code that needs to be thread-safe)
            System.out.println(Thread.currentThread().getName() + " is performing the task.");
            // Simulate some work
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); // Always release the lock in the finally block to avoid deadlock
        }
    }
}