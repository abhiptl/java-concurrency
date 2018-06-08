package _6_Task_Execution;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Future_6_7 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Future<?>> futures = new ArrayList<>();
        for(int i = 1; i <= 10; i++) {
            WorkerThread workerThread = new WorkerThread();
            Future<?> future = executorService.submit(workerThread);

            futures.add(future);
        }


        for(Future<?> f : futures) {
            System.out.println("Thread Cancelled :"+f.isCancelled()+" , Thread Done :"+f.isDone());
        }

    }
}

class WorkerThread implements Runnable {

    @Override
    public void run() {
        System.out.println("Starting worker thread :"+Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Ending worker thread :"+Thread.currentThread().getName());
    }
}