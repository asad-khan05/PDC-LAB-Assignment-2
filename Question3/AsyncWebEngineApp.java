public class AsyncWebEngineApp {
    public static void main(String[] args) {
        System.out.println("Default JVM thread name: " + Thread.currentThread().getName());

        TelemetryThread telemetryWorker = new TelemetryThread();
        telemetryWorker.setName("Telemetry-Worker");

        Thread logWorker = new Thread(new LogProcessorTask());
        logWorker.setName("Log-Worker");

        telemetryWorker.start();
        logWorker.start();
    }
}