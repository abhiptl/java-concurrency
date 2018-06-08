package _7_Cancellation_Shutdown;

public class JVMShutdown_7_26 {
    public static void main(String[] args) {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                SlowWorkerThread w = new SlowWorkerThread();
                w.run();
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                SlowWorkerThread w = new SlowWorkerThread();
                w.run();
            }
        };

        Thread t3 = new Thread(){
            @Override
            public void run() {
                //System.exit(0);// JVM Orderly Shutdown
                Runtime.getRuntime().halt(-1);// JVM abruptly Shutdown
            }
        };

        t1.start();
        t2.start();
        t3.start();

        Runtime.getRuntime().addShutdownHook(new JVMShutDownHook());
        System.out.println("Main done");
    }
}

class SlowWorkerThread implements Runnable {
    @Override
    public void run() {
        System.out.println("I am Worker Thread and I'm running");
        try {
            for(int i = 1;i <= 100; i++) {
                System.out.println("I am doing task..");
                Thread.sleep(1000);
            }


        } catch (InterruptedException e) {
            System.out.println("Someone interrupted me.");
            e.printStackTrace();
        }
    }
}

class JVMShutDownHook extends Thread {
    @Override
    public void run() {
        System.out.println("I will run once when JVM shutdown occurs");
    }
}