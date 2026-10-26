import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class InventoryCatalogManager {
    private static final int THREADS = 10;
    private static final int OPERATIONS = 50000;

    public void benchmarkSynchronizedMap() throws InterruptedException {
        Map<String, Integer> syncMap = Collections.synchronizedMap(new HashMap<>());
        runBenchmark("SynchronizedMap", syncMap);
    }

    public void benchmarkConcurrentMap() throws InterruptedException {
        Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        runBenchmark("ConcurrentHashMap", concurrentMap);
    }

    private void runBenchmark(String name, Map<String, Integer> map) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(THREADS);
        long startTime = System.nanoTime();

        for (int i = 0; i < THREADS; i++) {
            executor.execute(() -> {
                for (int j = 0; j < OPERATIONS; j++) {
                    map.put("Product_" + j, j);
                    map.get("Product_" + j);
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
        long endTime = System.nanoTime();

        long durationMs = (endTime - startTime) / 1000000;
        long throughput = ((long) THREADS * OPERATIONS * 1000) / (durationMs > 0 ? durationMs : 1);
        
        System.out.println(name + " Execution Time: " + durationMs + " ms");
        System.out.println(name + " Throughput: " + throughput + " ops/sec");
    }

    public static void main(String[] args) throws InterruptedException {
        InventoryCatalogManager manager = new InventoryCatalogManager();
        manager.benchmarkSynchronizedMap();
        manager.benchmarkConcurrentMap();
    }
}