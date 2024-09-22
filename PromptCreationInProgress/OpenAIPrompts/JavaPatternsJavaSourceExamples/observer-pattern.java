import java.util.ArrayList;
import java.util.List;

/**
 * Observer Pattern implementation for a stock price monitoring system
 */
public interface StockObserver {
    void update(String stock, double price);
}

public class StockMarket {
    private List<StockObserver> observers = new ArrayList<>();
    private String stockSymbol;
    private double price;

    public void addObserver(StockObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(StockObserver observer) {
        observers.remove(observer);
    }

    public void setStockPrice(String symbol, double price) {
        this.stockSymbol = symbol;
        this.price = price;
        notifyObservers();
    }

    private void notifyObservers() {
        for (StockObserver observer : observers) {
            observer.update(stockSymbol, price);
        }
    }
}

public class StockTrader implements StockObserver {
    private String name;

    public StockTrader(String name) {
        this.name = name;
    }

    @Override
    public void update(String stock, double price) {
        System.out.println(name + " notified. " + stock + " price: $" + price);
    }
}

// Usage
public class ObserverDemo {
    public static void main(String[] args) {
        StockMarket market = new StockMarket();
        market.addObserver(new StockTrader("Trader 1"));
        market.addObserver(new StockTrader("Trader 2"));

        market.setStockPrice("AAPL", 150.00);
        market.setStockPrice("GOOGL", 2800.00);
    }
}
