public class CacheLocalityBenchmark {
    private static final int N = 2000;
    private double[][] matrix = new double[N][N];

    public CacheLocalityBenchmark() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Math.random();
            }
        }
    }

    public long traverseRowMajor() {
        long startTime = System.nanoTime();
        double sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += matrix[i][j];
            }
        }
        return System.nanoTime() - startTime;
    }

    public long traverseColumnMajor() {
        long startTime = System.nanoTime();
        double sum = 0;
        for (int j = 0; j < N; j++) {
            for (int i = 0; i < N; i++) {
                sum += matrix[i][j];
            }
        }
        return System.nanoTime() - startTime;
    }

    public static void main(String[] args) {
        CacheLocalityBenchmark benchmark = new CacheLocalityBenchmark();
        long rowTotalTime = 0, colTotalTime = 0;

        for (int i = 0; i < 10; i++) {
            rowTotalTime += benchmark.traverseRowMajor();
            colTotalTime += benchmark.traverseColumnMajor();
        }

        double rowAvgMs = (rowTotalTime / 10.0) / 1000000.0;
        double colAvgMs = (colTotalTime / 10.0) / 1000000.0;
        double speedup = colAvgMs / rowAvgMs;

        System.out.println("Row-Major Avg Time: " + rowAvgMs + " ms");
        System.out.println("Column-Major Avg Time: " + colAvgMs + " ms");
        System.out.println("Speedup Ratio (Row/Col): " + speedup);
        System.out.println("\n--- Execution Report ---");
        System.out.println("Modern CPU architecture loads memory into L1/L2 caches in contiguous chunks (cache lines).");
        System.out.println("Row-Major traversal accesses elements sequentially in memory, leveraging spatial locality.");
        System.out.println("This results in high cache hit rates and faster execution. Column-Major traversal jumps");
        System.out.println("across memory rows, causing frequent cache misses, invalidations, and memory bus stalls.");
    }
}
