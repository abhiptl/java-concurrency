package _7_Cancellation_Shutdown;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Interruption_7_5 {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        PrimeProducer primeProducer = new PrimeProducer(queue);
        primeProducer.start();

        //primeProducer.interrupt();
        primeProducer.cancel();
    }
}

class PrimeProducer extends Thread {
    private BlockingQueue<Integer> blockingQueue;

    PrimeProducer(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            Integer prime = 1;
            while(!Thread.currentThread().isInterrupted()) {
                prime = getNextPrimeNumber();
                System.out.println("Prime Generated :"+prime);
                blockingQueue.put(prime);
            }
        } catch (InterruptedException e) {
            //System.out.println("Interrupt Status :"+isInterrupted());
            Thread.currentThread().interrupt();

        }
    }

    public void cancel() {
        interrupt();
    }
    private Integer getNextPrimeNumber() {
        return new Integer(11);
    }
}
