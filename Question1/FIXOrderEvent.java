public class FIXOrderEvent extends TradeEvent {
    private String clOrdId;

    public FIXOrderEvent(String senderId, long timestamp, double basePrice, String clOrdId) {
        super(senderId, timestamp, basePrice);
        this.clOrdId = clOrdId;
    }

    @Override
    public void dispatch() {
        double totalCost = basePrice + (basePrice * 0.0015);
        System.out.println("Dispatching FIX Order [" + clOrdId + "] from " + senderId + ". Total cost (with 0.15% fee): " + totalCost);
    }
}