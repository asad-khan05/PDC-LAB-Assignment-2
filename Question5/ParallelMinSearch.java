public class ParallelMinSearch extends SensorSearchEngine {
    public ParallelMinSearch(double[] sensorData, int startIdx, int endIdx) {
        super(sensorData, startIdx, endIdx);
        this.result = Double.MAX_VALUE;
    }

    @Override
    public void processGrid() {
        for (int i = startIdx; i < endIdx; i++) {
            if (sensorData[i] < result) {
                result = sensorData[i];
            }
        }
    }
}
