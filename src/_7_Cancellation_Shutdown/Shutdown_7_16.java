package _7_Cancellation_Shutdown;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

public class Shutdown_7_16 {
    public static void main(String[] args) {
        LogService logService = new LogService();
        logService.start();

        Thread t1 = new Thread() {
            @Override
            public void run() {
                logService.log("Message1");
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                logService.log("Message2");
            }
        };

        Thread t3 = new Thread() {
            @Override
            public void run() {
                logService.log("Message3");
            }
        };

        Thread t4 = new Thread() {
            @Override
            public void run() {
                logService.log("Message4");
            }
        };

        Thread t5 = new Thread() {
            @Override
            public void run() {
                logService.stop();
            }
        };

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        System.out.println("Main thread is done");


    }
}

class LogService {
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public void start() {}

    public void stop() {

        /*executorService.shutdown();
        try {
            executorService.awaitTermination(10000, TimeUnit.MILLISECONDS);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    public void log(String msg) {
        try {
            executorService.execute(new LoggerThread(msg));
        } catch (RejectedExecutionException r) {
            r.printStackTrace();
        }

    }
}

class LoggerThread implements Runnable {
    private String msg;

    LoggerThread(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public void run() {
        //System.out.println("Logging message :"+ msg);
        throw new RuntimeException("Exception");
    }
}