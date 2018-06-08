package _9_Avoiding_Liveness_Hazards;

public class LeftRightDeadLock_10_1 {
    public static void main(String[] args) {
        LeftRightDeadLock leftRightDeadLock = new LeftRightDeadLock();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Starting leftRight method");
                leftRightDeadLock.leftRight();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Starting rightLeft method");
                leftRightDeadLock.rightLeft();
            }
        });


        t1.start();
        t2.start();
    }
}

class LeftRightDeadLock {
    private final Object left = new Object();
    private final Object right = new Object();

    public void leftRight() {
        synchronized (left) {
            System.out.println("Acquired left lock by "+Thread.currentThread().getName());
            synchronized (right) {
                System.out.println("Acquired right lock by "+Thread.currentThread().getName());
            }
        }
    }


    public void rightLeft() {
        synchronized (right) {
            System.out.println("Acquired right lock by "+Thread.currentThread().getName());
            synchronized (left) {
                System.out.println("Acquired left lock by "+Thread.currentThread().getName());
            }
        }
    }
}