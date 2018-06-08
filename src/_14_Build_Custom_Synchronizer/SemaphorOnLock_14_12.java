package _14_Build_Custom_Synchronizer;

import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SemaphorOnLock_14_12 {
    public static void main(String[] args) {
        SemaphorOnLock semaphorOnLock = new SemaphorOnLock(2);


        Thread t1 = new SemaphorWorker(semaphorOnLock);
        Thread t2 = new SemaphorWorker(semaphorOnLock);

        Thread t3 = new SemaphorWorker(semaphorOnLock);

        t1.start();
        t2.start();
        t3.start();



    }
}

class SemaphorOnLock {
    private ReentrantLock lock = new ReentrantLock();
    private int permits;
    private Condition permitsAvailable = lock.newCondition();

    public SemaphorOnLock(int initPermits) {
        lock.lock();
        try{
            this.permits = initPermits;
        } finally {
            lock.unlock();
        }
    }

    public void acquire() throws InterruptedException{
        lock.lock();
        try{
            while (permits <= 0) {
                permitsAvailable.await();
            }
            --permits;
        } finally {
            lock.unlock();
        }
    }

    public void release() throws InterruptedException {
        lock.lock();
        try{
            ++permits;
            permitsAvailable.signal();
        } finally {
            lock.unlock();
        }
    }
}

class SemaphorWorker extends Thread {
    private SemaphorOnLock lock;

    public SemaphorWorker(SemaphorOnLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            lock.acquire();
            System.out.println("SemaphorWorker is started at "+new Date());
            System.out.println("SemaphorWorker is completed at "+new Date());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            try {
                lock.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }
}