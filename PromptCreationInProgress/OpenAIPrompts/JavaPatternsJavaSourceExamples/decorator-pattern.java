/**
 * Decorator Pattern implementation for a configurable logging system
 */
public interface Logger {
    void log(String message);
}

public class BasicLogger implements Logger {
    @Override
    public void log(String message) {
        System.out.println("Log: " + message);
    }
}

public abstract class LoggerDecorator implements Logger {
    protected Logger wrappedLogger;

    public LoggerDecorator(Logger logger) {
        this.wrappedLogger = logger;
    }

    @Override
    public void log(String message) {
        wrappedLogger.log(message);
    }
}

public class TimestampDecorator extends LoggerDecorator {
    public TimestampDecorator(Logger logger) {
        super(logger);
    }

    @Override
    public void log(String message) {
        String timestampedMessage = String.format("[%tF %<tT] %s", System.currentTimeMillis(), message);
        super.log(timestampedMessage);
    }
}

public class EncryptionDecorator extends LoggerDecorator {
    public EncryptionDecorator(Logger logger) {
        super(logger);
    }

    @Override
    public void log(String message) {
        String encryptedMessage = encrypt(message);
        super.log(encryptedMessage);
    }

    private String encrypt(String message) {
        // Simple encryption for demonstration
        return "ENCRYPTED[" + message + "]";
    }
}

// Usage
public class DecoratorDemo {
    public static void main(String[] args) {
        Logger logger = new TimestampDecorator(new EncryptionDecorator(new BasicLogger()));
        logger.log("This is a test message");
    }
}
