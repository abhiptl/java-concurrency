package _5_Building_Blocks;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayList_5_2_3 {
    public static void main(String[] args) {
        List<String> copyOnWriteList = new CopyOnWriteArrayList();
        copyOnWriteList.add("Abhishek");
        copyOnWriteList.add("Chandni");

        Thread t1 = new Thread() {
            @Override
            public void run() {
                copyOnWriteList.add("Not Visible1 - Added By Thread1");
                copyOnWriteList.add("Not Visible2 - Added By Thread1");
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                for(String s : copyOnWriteList) {
                    System.out.println("Elements :"+ s);
                }
            }
        };


        t1.start();
        t2.start();
    }
}
