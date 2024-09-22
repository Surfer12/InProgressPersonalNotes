/**
 * Factory Method Pattern implementation for creating payment processors
 */
public interface PaymentProcessor {
    void processPayment(double amount);
}

public class CreditCardProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment of $" + amount);
    }
}

public class PayPalProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing PayPal payment of $" + amount);
    }
}

public abstract class PaymentProcessorFactory {
    public abstract PaymentProcessor createProcessor();

    public void processPayment(double amount) {
        PaymentProcessor processor = createProcessor();
        processor.processPayment(amount);
    }
}

public class CreditCardProcessorFactory extends PaymentProcessorFactory {
    @Override
    public PaymentProcessor createProcessor() {
        return new CreditCardProcessor();
    }
}

public class PayPalProcessorFactory extends PaymentProcessorFactory {
    @Override
    public PaymentProcessor createProcessor() {
        return new PayPalProcessor();
    }
}

// Usage
public class FactoryMethodDemo {
    public static void main(String[] args) {
        PaymentProcessorFactory factory = new CreditCardProcessorFactory();
        factory.processPayment(100.00);

        factory = new PayPalProcessorFactory();
        factory.processPayment(50.00);
    }
}
