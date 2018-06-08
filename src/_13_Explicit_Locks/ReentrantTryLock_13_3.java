package _13_Explicit_Locks;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantTryLock_13_3 {
    public static void main(String[] args) {
            ReentrantLock lock = new ReentrantLock();
            Thread t1 = new TryLockWorker(lock);
            Thread t2 = new TryLockWorker(lock);
            t1.start();
            t2.start();
    }
}

class TryLockWorker extends Thread {
    private Lock lock;

    public TryLockWorker(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run()  {
        System.out.println("TryLockWorker started at "+new Date());
        try {
            while (true) {
                if(lock.tryLock()) {
                    System.out.println("Accessing shared data started");
                    Thread.sleep(5000);
                    System.out.println("Accessing shared data completed");
                    return;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        System.out.println("TryLockWorker completed at "+new Date());
    }
}
