package _3_Sharing_Objects;

public class ThreadLocal_3_10 {
    public static void main(String[] args) {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                System.out.println("Thread-1 getValue :"+Context.getValue());
                Context.setValue(200);
                System.out.println("Thread-1 getValue :"+Context.getValue());
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                System.out.println("Thread-2 getValue :"+Context.getValue());
                Context.setValue(300);
                System.out.println("Thread-2 getValue :"+Context.getValue());
            }
        };

        t1.start();
        t2.start();


    }
}


class Context {
    public static ThreadLocal<Integer> value = new ThreadLocal() {
        @Override
        protected Object initialValue() {
            return 100;
        }
    };


    public static Integer getValue() {
        System.out.println("Getting Value :"+ Thread.currentThread().getName());
        return value.get();
    }

    public static void setValue(Integer val) {
        System.out.println("Setting Value :"+ Thread.currentThread().getName());
        value.set(val);
    }

}

