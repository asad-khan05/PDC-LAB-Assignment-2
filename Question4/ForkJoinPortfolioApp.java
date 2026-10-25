import java.util.concurrent.ForkJoinPool;
import java.util.Random;

public class ForkJoinPortfolioApp {
    public static void main(String[] args) {
        int size = 10000;
        double[] assets = new double[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            assets[i] = rand.nextDouble() * 2000;
        }

        ForkJoinPool pool = new ForkJoinPool();
        long startTime = System.nanoTime();

        PortfolioThresholdTask stressTest = new PortfolioThresholdTask(assets, 0, size, 1500.0);
        pool.invoke(stressTest);

        ParallelRiskCalculator riskCalc = new ParallelRiskCalculator(assets, 0, size);
        double totalRisk = pool.invoke(riskCalc);

        long endTime = System.nanoTime();

        System.out.println("Total Risk Metric: " + totalRisk);
        System.out.println("Total Processing Time (ns): " + (endTime - startTime));
    }
}