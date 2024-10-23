import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private int sharedData = 0;

    // Method to read data
    public int readData() {
        lock.readLock().lock(); // Acquire the read lock
        try {
            System.out.println(Thread.currentThread().getName() + " is reading data: " + sharedData);
            return sharedData;
        } finally {
            lock.readLock().unlock(); // Always release the read lock
        }
    }

    // Method to write data
    public void writeData(int newData) {
        lock.writeLock().lock(); // Acquire the write lock
        try {
            System.out.println(Thread.currentThread().getName() + " is writing data: " + newData);
            sharedData = newData;
        } finally {
            lock.writeLock().unlock(); // Always release the write lock
        }
    }
}
