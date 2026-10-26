import java.util.Random;

public class SensorGridMonitorApp {
    public static void main(String[] args) throws InterruptedException {
        int size = 50000;
        double[] sensorData = new double[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            sensorData[i] = rand.nextDouble() * 100.0;
        }

        int mid = size / 2;
        
        long startTime = System.currentTimeMillis();

        ParallelMinSearch minTask1 = new ParallelMinSearch(sensorData, 0, mid);
        ParallelMinSearch minTask2 = new ParallelMinSearch(sensorData, mid, size);
        ParallelMaxSearch maxTask1 = new ParallelMaxSearch(sensorData, 0, mid);
        ParallelMaxSearch maxTask2 = new ParallelMaxSearch(sensorData, mid, size);

        Thread t1 = new Thread(minTask1);
        Thread t2 = new Thread(minTask2);
        Thread t3 = new Thread(maxTask1);
        Thread t4 = new Thread(maxTask2);

        t1.start(); t2.start(); t3.start(); t4.start();
        t1.join(); t2.join(); t3.join(); t4.join();

        double globalMin = Math.min(minTask1.getResult(), minTask2.getResult());
        double globalMax = Math.max(maxTask1.getResult(), maxTask2.getResult());

        long endTime = System.currentTimeMillis();

        System.out.println("Global Minimum Temp: " + globalMin);
        System.out.println("Global Maximum Vibration: " + globalMax);
        System.out.println("Execution Time (ms): " + (endTime - startTime));
    }
}
