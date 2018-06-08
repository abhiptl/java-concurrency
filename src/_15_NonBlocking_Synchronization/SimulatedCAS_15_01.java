package _15_NonBlocking_Synchronization;

public class SimulatedCAS_15_01 {
    public static void main(String[] args) {

        CASCounter casCounter = new CASCounter();

        Thread t1 = new Thread() {
            @Override
            public void run() {
                casCounter.increment();
            }
        };


        Thread t2 = new Thread() {
            @Override
            public void run() {
                int v = casCounter.get();
                int vIncremented = casCounter.increment();
            }
        };

        t1.start();
        t2.start();

    }
}

class SimulatedCAS {
    private int value;

    public synchronized int get() {
        return value;
    }

    public synchronized int compareAndSwap(int existingValue, int newValue) {
        int oldValue = value;

        if( oldValue == existingValue) {
            value = newValue;
        }

        return oldValue;
    }

    public synchronized boolean compareAndSet(int existingValue, int newValue) {
        return (existingValue == compareAndSwap(existingValue, newValue));
    }
}

class CASCounter {
    private SimulatedCAS simulatedCAS;

    public CASCounter() {
        simulatedCAS = new SimulatedCAS();
    }

    public int get() {
        int j =  simulatedCAS.get();
        return j;
    }

    public int increment() {
        int v;
        do {
            v = simulatedCAS.get();
        } while(v != simulatedCAS.compareAndSwap(v, v+1));
        return v+1;
    }
}
