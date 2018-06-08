package _8_Applying_Thread_Pool;

public class GetRunTimeData {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("Available Processors :"+ runtime.availableProcessors());
        System.out.println("Max Memory:"+ runtime.maxMemory());
        System.out.println("Free Memory:"+ runtime.freeMemory());

    }
}
