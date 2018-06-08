package _4_Composing_Objects;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DelegatingVehicleTracker_4_07 {
    public static void main(String[] args) {
        Point p12 = new Point(12,12);
        Point p11 = new Point(11,11);

        Map<String, Point> points = new HashMap<>();
        points.put("12", p12);
        points.put("11", p11);

        DelegatingVehicleTracker delegatingVehicleTracker = new DelegatingVehicleTracker(points);
/*
        Thread t1 = new Thread() {
            @Override
            public void run() {
                System.out.println("Getting Locations");
                Point location = delegatingVehicleTracker.getLocation("11");
                System.out.println("Point :"+ location);
            }
        };*/



        Thread t2 = new Thread() {
            @Override
            public void run() {
                System.out.println("Updating Location");
                delegatingVehicleTracker.setLocation("11", 111,111);
            }
        };

        Thread t3 = new Thread() {
            @Override
            public void run() {
                System.out.println("Before getting Locations");
                Map<String, Point> locations = delegatingVehicleTracker.getLocations();
                System.out.println("After getting Locations");
            }
        };




        t2.start();
        //t1.start();
        t3.start();
    }
}

class DelegatingVehicleTracker {
    private final ConcurrentHashMap<String, Point> locations;
    private final Map<String, Point> unmodifiableMap;

    public DelegatingVehicleTracker(Map<String, Point> points) {
        locations = new ConcurrentHashMap<>(points);
        unmodifiableMap = Collections.unmodifiableMap(locations);
    }

/*
    public Map<String, Point> getLocations() {
        return unmodifiableMap;
    }*/


    public Map<String, Point> getLocations() {
        return Collections.unmodifiableMap(new HashMap<>(locations));
    }

    public Point getLocation(String id) {
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y) {
        if(locations.replace(id, new Point(x,y)) == null) {
            throw new IllegalArgumentException("Invalid vehicle name:"+id);
        }
    }
}

class Point {
    public final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }


}
