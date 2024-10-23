import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExample {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private int count = 0;

    public void increment() {
        lock.lock(); // Acquire the lock
        try {
            count++;
            System.out.println(Thread.currentThread().getName() + " incremented count to: " + count);
            // Signal waiting threads if the count reaches a certain threshold
            if (count >= 5) {
                condition.signalAll(); // Notify waiting threads
            }
        } finally {
            lock.unlock(); // Always release the lock
        }
    }

    public void awaitThreshold() {
        lock.lock(); // Acquire the lock
        try {
            while (count < 5) {
                System.out.println(Thread.currentThread().getName() + " is waiting for count to reach 5");
                condition.await(); // Wait until signaled
            }
            System.out.println(Thread.currentThread().getName() + " detected count is: " + count);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted status
        } finally {
            lock.unlock(); // Always release the lock
        }
    }

}
