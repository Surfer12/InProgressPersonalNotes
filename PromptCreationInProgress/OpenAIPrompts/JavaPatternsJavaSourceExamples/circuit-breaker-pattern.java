import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/**
 * Circuit Breaker Pattern implementation
 */
public class CircuitBreaker {
    private final int failureThreshold;
    private final long resetTimeout;
    private AtomicInteger failureCount;
    private long lastFailureTime;
    private State state;

    public enum State {
        CLOSED, OPEN, HALF_OPEN
    }

    public CircuitBreaker(int failureThreshold, long resetTimeout) {
        this.failureThreshold = failureThreshold;
        this.resetTimeout = resetTimeout;
        this.failureCount = new AtomicInteger(0);
        this.state = State.CLOSED;
    }

    public <T> T execute(Supplier<T> operation) throws Exception {
        if (state == State.OPEN) {
            if (System.currentTimeMillis() - lastFailureTime >= resetTimeout) {
                state = State.HALF_OPEN;
            } else {
                throw new Exception("Circuit is OPEN");
            }
        }

        try {
            T result = operation.get();
            reset();
            return result;
        } catch (Exception e) {
            recordFailure();
            throw e;
        }
    }

    private void recordFailure() {
        failureCount.incrementAndGet();
        if (failureCount.get() >= failureThreshold) {
            state = State.OPEN;
            lastFailureTime = System.currentTimeMillis();
        }
    }

    private void reset() {
        failureCount.set(0);
        state = State.CLOSED;
    }

    public State getState() {
        return state;
    }
}

// Simulated external service
class ExternalService {
    private static int callCount = 0;

    public static String call() throws Exception {
        callCount++;
        if (callCount % 4 == 0) { // Simulate intermittent failures
            throw new Exception("External service failed");
        }
        return "Success";
    }
}

// Usage
public class CircuitBreakerDemo {
    public static void main(String[] args) {
        CircuitBreaker circuitBreaker = new CircuitBreaker(3, 5000); // 3 failures, 5 second timeout

        for (int i = 0; i < 10; i++) {
            try {
                String result = circuitBreaker.execute(() -> ExternalService.call());
                System.out.println("Call " + i + ": " + result);
            } catch (Exception e) {
                System.out.println("Call " + i + ": " + e.getMessage());
            }
            System.out.println("Circuit state: " + circuitBreaker.getState());
        }
    }
}
