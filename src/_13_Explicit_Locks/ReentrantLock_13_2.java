package _13_Explicit_Locks;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock_13_2 {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Thread t1 = new LockWorker(lock);
        Thread t2 = new LockWorker(lock);
        t1.start();
        t2.start();

    }
}

class LockWorker extends Thread {
    private Lock lock;

    public LockWorker(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run()  {
        System.out.println("LockWorker started at "+new Date());
        try {
            lock.lock();
            System.out.println("Accessing shared data started");
            Thread.sleep(5000);
            System.out.println("Accessing shared data completed");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        System.out.println("LockWorker completed at "+new Date());
    }
}
