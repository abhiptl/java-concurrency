package _7_Cancellation_Shutdown;

public class AbnormalThreadTermination_7_23 {
    public static void main(String[] args) {
        /*Runnable runnableThread =  new WorkerThreadRunnable();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                runnableThread.run();
            }
        };*/
        //Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler());
        Thread t2 = new WorkerThread();
        //t2.setUncaughtExceptionHandler(new UncaughtExceptionHandler());

        //t1.start();
        t2.start();

    }
}

/*class WorkerThreadRunnable implements Runnable {
    @Override
    public void run() {

        System.out.println("I am  WorkerThreadRunnable and staring my work");
        int i = 1/0;
        System.out.println("I am  WorkerThreadRunnable and completed my work");
    }
}*/

class WorkerThread extends Thread {
    @Override
    public void run() {
        System.out.println("I am WorkerThread and staring my work");
        Integer i = Integer.valueOf("abc");
        System.out.println("I am WorkerThread and completed my work");
    }
}


class UncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("Thread named :"+t.getName() +" has thrown uncaught exception...");
    }
}