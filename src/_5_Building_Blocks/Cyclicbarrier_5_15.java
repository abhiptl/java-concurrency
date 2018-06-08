package _5_Building_Blocks;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Cyclicbarrier_5_15 {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new BarrierCompleteAction());
        Worker[] workers = new Worker[5];
        workers[0] = new Worker(cyclicBarrier, 3);
        workers[1] = new Worker(cyclicBarrier, 4);
        workers[2] = new Worker(cyclicBarrier, 1);
        workers[3] = new Worker(cyclicBarrier, 5);
        workers[4] = new Worker(cyclicBarrier, 2);

        new Thread(workers[0]).start();
        new Thread(workers[1]).start();
        new Thread(workers[2]).start();
        new Thread(workers[3]).start();
        new Thread(workers[4]).start();
    }
}

class Worker implements Runnable {
    private CyclicBarrier cyclicBarrier;
    private long workEstimationInSeconds;
    public Worker(CyclicBarrier cyclicBarrier, long workEstimationInSeconds) {
        this.cyclicBarrier = cyclicBarrier;
        this.workEstimationInSeconds = workEstimationInSeconds;
    }

    @Override
    public void run() {
        System.out.println("Worked started : "+ Thread.currentThread().getName());
        try {
            Thread.sleep(workEstimationInSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Worked Completed : "+ Thread.currentThread().getName());
        try {
            cyclicBarrier.await();
        } catch (InterruptedException i) {
            i.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

    }
}

class BarrierCompleteAction implements Runnable {
    @Override
    public void run() {
        System.out.println("All thread reached to barrier : "+ Thread.currentThread().getName());
    }
}
