public class TradingEngineApp {
    public static void main(String[] args) {
        TradeEvent event1 = new FIXOrderEvent("TraderA", System.currentTimeMillis(), 150.0, "ORD1234");
        TradeEvent event2 = new ReutersQuoteEvent("MarketMaker1", System.currentTimeMillis(), 150.0, 149.8, 150.2);
        
        event1.dispatch();
        event2.dispatch();
    }
}