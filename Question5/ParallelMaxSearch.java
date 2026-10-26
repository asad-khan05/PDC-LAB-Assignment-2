public class ParallelMaxSearch extends SensorSearchEngine {
    public ParallelMaxSearch(double[] sensorData, int startIdx, int endIdx) {
        super(sensorData, startIdx, endIdx);
        this.result = Double.MIN_VALUE;
    }

    @Override
    public void processGrid() {
        for (int i = startIdx; i < endIdx; i++) {
            if (sensorData[i] > result) {
                result = sensorData[i];
            }
        }
    }
}
