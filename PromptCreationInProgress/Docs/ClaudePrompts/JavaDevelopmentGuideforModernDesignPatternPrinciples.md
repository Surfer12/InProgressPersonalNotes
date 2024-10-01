# Java Development Guide: Modern Design Pattern Principles

## Objective

Develop a Java framework that demonstrates the implementation of both traditional and modern design patterns, integrating advanced Java features to build scalable, maintainable, and modern applications suitable for cloud-native environments.

## Guidelines

### 1. Select and Implement 10 Design Patterns

#### Creational Patterns

**Singleton Pattern**

```java
/**
 * Thread-safe Singleton implementation using enum
 */
public enum DatabaseConnection {
    INSTANCE;

    private Connection connection;

    DatabaseConnection() {
        // Simulate database connection
        connection = new Connection();
    }

    public Connection getConnection() {
        return connection;
    }

    public void executeQuery(String query) {
        System.out.println("Executing query: " + query);
    }

    // Simulated Connection class
    private static class Connection {
        // Connection details
    }
}

// Usage
public class SingletonDemo {
    public static void main(String[] args) {
        DatabaseConnection.INSTANCE.executeQuery("SELECT * FROM users");
    }
}
```

**Explanation:**
- Ensures a class has only one instance and provides a global point of access.
- Useful in microservices for managing shared resources like connection pools or configuration settings.

**Factory Method Pattern**

```java
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
```

**Explanation:**
- Defines an interface for creating an object but lets subclasses decide which class to instantiate.
- Useful in microservices for creating different service implementations based on configuration or runtime conditions.

#### Behavioral Patterns

**Observer Pattern**

```java
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
```

**Explanation:**
- Defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.
- Useful in microservices for event-driven communication between services.

#### Structural Patterns

**Adapter Pattern**

```java
public interface ModernPaymentGateway {
    void processPayment(double amount);
}

public class LegacyPaymentSystem {
    public void oldProcessPayment(String amount) {
        System.out.println("Legacy system processing payment of " + amount);
    }
}

public class PaymentSystemAdapter implements ModernPaymentGateway {
    private LegacyPaymentSystem legacySystem;

    public PaymentSystemAdapter(LegacyPaymentSystem legacySystem) {
        this.legacySystem = legacySystem;
    }

    @Override
    public void processPayment(double amount) {
        String legacyAmount = String.format("%.2f", amount);
        legacySystem.oldProcessPayment(legacyAmount);
    }
}

// Usage
public class AdapterDemo {
    public static void main(String[] args) {
        LegacyPaymentSystem legacySystem = new LegacyPaymentSystem();
        ModernPaymentGateway modernGateway = new PaymentSystemAdapter(legacySystem);

        modernGateway.processPayment(100.00);
    }
}
```

**Explanation:**
- Allows incompatible interfaces to work together.
- Useful in microservices for integrating services with different interfaces or protocols.

**Decorator Pattern**

```java
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
```

**Explanation:**
- Allows behavior to be added to an individual object, either statically or dynamically, without affecting the behavior of other objects from the same class.
- Useful in microservices for adding cross-cutting concerns like logging, monitoring, or security to service calls.

#### Modern Patterns

**Dependency Injection (DI) Pattern**

```java
public interface MessageService {
    void sendMessage(String message, String receiver);
}

public class EmailService implements MessageService {
    @Override
    public void sendMessage(String message, String receiver) {
        System.out.println("Sending email to " + receiver + ": " + message);
    }
}

public class SMSService implements MessageService {
    @Override
    public void sendMessage(String message, String receiver) {
        System.out.println("Sending SMS to " + receiver + ": " + message);
    }
}

public class NotificationService {
    private MessageService messageService;

    // Constructor injection
    public NotificationService(MessageService messageService) {
        this.messageService = messageService;
    }

    public void sendNotification(String message, String receiver) {
        messageService.sendMessage(message, receiver);
    }
}

// Simple DI container
public class DIContainer {
    public static NotificationService getNotificationService() {
        // Here we're deciding which implementation to inject
        return new NotificationService(new EmailService());
    }
}

// Usage
public class DependencyInjectionDemo {
    public static void main(String[] args) {
        NotificationService notificationService = DIContainer.getNotificationService();
        notificationService.sendNotification("Hello, DI!", "user@example.com");
    }
}
```

**Explanation:**
- A technique whereby one object supplies the dependencies of another object.
- Useful in microservices for managing dependencies between different components and services.

**Repository Pattern**

```java
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repository Pattern implementation for managing user data
 */
public class User {
    private int id;
    private String name;
    private String email;

    // Constructor, getters, and setters omitted for brevity
}

public interface UserRepository {
    User save(User user);
    Optional<User> findById(int id);
    List<User> findAll();
    void delete(int id);
}

public class InMemoryUserRepository implements UserRepository {
    private List<User> users = new ArrayList<>();
    private int nextId = 1;

    @Override
    public User save(User user) {
        if (user.getId() == 0) {
            user.setId(nextId++);
            users.add(user);
        } else {
            users.removeIf(u -> u.getId() == user.getId());
            users.add(user);
        }
        return user;
    }

    @Override
    public Optional<User> findById(int id) {
        return users.stream().filter(u -> u.getId() == id).findFirst();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users);
    }

    @Override
    public void delete(int id) {
        users.removeIf(u -> u.getId() == id);
    }
}

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String name, String email) {
        User user = new User(0, name, email);
        return userRepository.save(user);
    }

    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    // Other service methods omitted for brevity
}

// Usage
public class RepositoryPatternDemo {
    public static void main(String[] args) {
        UserRepository userRepository = new InMemoryUserRepository();
        UserService userService = new UserService(userRepository);

        User newUser = userService.createUser("John Doe", "john@example.com");
        System.out.println("Created user: " + newUser.getName());

        Optional<User> retrievedUser = userService.getUserById(newUser.getId());
        retrievedUser.ifPresent(user -> System.out.println("Retrieved user: " + user.getName()));
    }
}
```

**Explanation:**
- Mediates between the domain and data mapping layers, acting like an in-memory collection of domain objects.
- Useful in microservices for providing a clean abstraction for data access.

**Circuit Breaker Pattern**

```java
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
```

**Explanation:**
- Prevents an application from repeatedly trying to execute an operation that's likely to fail, allowing it to continue without waiting for the fault to be fixed or wasting CPU cycles.
- Useful in microservices for preventing cascading failures when one service is down.

**Strategy Pattern**

```java
import java.util.List;

/**
 * Strategy Pattern implementation for sorting algorithms
 */
public interface SortStrategy {
    <T extends Comparable<T>> void sort(List<T> list);
}

public class BubbleSortStrategy implements SortStrategy {
    @Override
    public <T extends Comparable<T>> void sort(List<T> list) {
        // Bubble sort implementation
        System.out.println("Sorting using Bubble Sort");
        // ... (implementation details omitted for brevity)
    }
}

public class QuickSortStrategy implements SortStrategy {
    @Override
    public <T extends Comparable<T>> void sort(List<T> list) {
        // Quick sort implementation
        System.out.println("Sorting using Quick Sort");
        // ... (implementation details omitted for brevity)
    }
}

public class Sorter {
    private SortStrategy strategy;

    public void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public <T extends Comparable<T>> void sort(List<T> list) {
        if (strategy == null) {
            throw new IllegalStateException("Sort strategy not set");
        }
        strategy.sort(list);
    }
}

// Usage
public class StrategyPatternDemo {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(5, 2, 8, 1, 9);
        Sorter sorter = new Sorter();

        sorter.setStrategy(new BubbleSortStrategy());
        sorter.sort(numbers);

        sorter.setStrategy(new QuickSortStrategy());
        sorter.sort(numbers);
    }
}
```

**Explanation:**
- Defines a family of algorithms, encapsulates each one, and makes them interchangeable. It lets the algorithm vary independently from clients that use it.
- Useful in microservices for implementing different business rules or processing algorithms that can be easily swapped or updated.

### 2. Integration with Modern Java Frameworks

#### Spring Framework

- **Dependency Injection (DI):** Demonstrate how Spring facilitates DI to promote loose coupling. Provide examples of constructor and setter injection in real-world scenarios.
- **Factory Patterns:** Explain how Spring's `BeanFactory` and `ApplicationContext` use Factory Method and Abstract Factory patterns to manage bean creation and lifecycle.
- **Aspect-Oriented Programming (AOP):** Illustrate how patterns like Proxy and Decorator are utilized in Spring AOP to implement cross-cutting concerns such as logging, security, and transaction management.

### 3. Reactive Programming and Patterns

#### Project Reactor and RxJava

- **Observer Pattern:** Showcase how reactive libraries employ the Observer pattern for asynchronous and non-blocking event handling.
- **Functional Interfaces and Lambdas:** Emphasize the use of functional programming concepts to implement patterns like Strategy and Command in a reactive context.
- **Backpressure Management:** Discuss how reactive streams handle backpressure to prevent resource exhaustion in systems with variable data flow rates.

### 4. Cloud-Native Development Considerations

- **Stateless Design:** Highlight the importance of designing stateless services in microservices architecture for scalability and resilience. Show how patterns like Strategy and Command support stateless operations.
- **Distributed Systems Management:**
  - **Event Sourcing and CQRS:** Explain how these patterns help maintain data consistency and scalability across distributed systems by separating read and write operations and capturing all changes as events.
  - **Circuit Breaker Pattern:** Introduce the Circuit Breaker pattern to manage fault tolerance, enabling services to fail gracefully in distributed architectures.

### 5. Advanced Use of Generics and Functional Interfaces

- Implement patterns using generics to ensure type safety and reusability.
- Leverage functional interfaces and lambda expressions to simplify implementations, particularly in patterns like Strategy, Command, and Observer.

### 6. Optimized Use of Java Collections and Stream API

- Utilize the Java Collections Framework effectively, demonstrating advanced techniques like custom comparators or thread-safe collections.
- Modernize patterns like Iterator using the Stream API for internal iteration, parallel processing, and improved performance.

### 7. Interface and Abstract Class Driven Development

- Use interfaces with default and static methods to provide flexible and extensible designs.
- Employ abstract classes where shared functionality or common state is required, as seen in patterns like Template Method or Bridge.

### 8. Modular, Readable, and SOLID Code Structure

- Structure the codebase using Java modules (Java Platform Module System) for better encapsulation and maintainability.
- Ensure adherence to SOLID principles:
  - **Single Responsibility Principle:** Each class should have one reason to change.
  - **Open/Closed Principle:** Classes should be open for extension but closed for modification.
  - **Liskov Substitution Principle:** Subtypes must be substitutable for their base types.
  - **Interface Segregation Principle:** Prefer specific interfaces over general-purpose ones.
  - **Dependency Inversion Principle:** Depend upon abstractions, not concretions.

### 9. Enhanced Java Documentation with Modern Insights

- Write comprehensive JavaDoc comments that explain not just the "how," but also the "why" behind design decisions.
- Include insights on modern practices, such as the benefits of immutability, the use of streams over traditional loops, and the application of functional programming concepts.

### 10. Error Handling, Concurrency, and Robustness

- **Advanced Error Handling:**
  - Implement robust error handling using custom exceptions and exception hierarchies.
  - Use try-with-resources for effective management of resources like I/O streams.
- **Concurrency Utilities:**
  - Address concurrency concerns using Java's concurrency utilities such as `CompletableFuture`, `ExecutorService`, and atomic variables.
  - Utilize concurrent collections like `ConcurrentHashMap` toutilize thread-safe data structures without the need for explicit synchronization.
  - Leverage the `java.util.concurrent` package to simplify the development of concurrent applications.
  - Apply best practices for avoiding common concurrency pitfalls such as deadlocks, race conditions, and thread starvation.

- **Robustness:**
  - Ensure robustness by writing defensive code that anticipates and handles potential failures gracefully.
  - Use assertions to catch programming errors during development.
  - Implement thorough logging to aid in diagnosing issues in production environments.
  - Regularly perform code reviews and static analysis to identify and address potential issues early in the development cycle.
  - Write unit tests and integration tests to validate the correctness and performance of your code under various conditions.

  ### Analysis and Improvements

#### Singleton Pattern

**Improvement:**
- Ensure the [`Connection`](command:_github.copilot.openSymbolFromReferences?%5B%22%22%2C%5B%7B%22uri%22%3A%7B%22scheme%22%3A%22file%22%2C%22authority%22%3A%22%22%2C%22path%22%3A%22%2Fe%3A%2FCis232Decoupled%2FTenFirstDesignPatterns%2Fsrc%2FDocs%2FDesignPatternCommentsOnRelativity%22%2C%22query%22%3A%22%22%2C%22fragment%22%3A%22%22%7D%2C%22pos%22%3A%7B%22line%22%3A29%2C%22character%22%3A25%7D%7D%2C%7B%22uri%22%3A%7B%22scheme%22%3A%22file%22%2C%22authority%22%3A%22%22%2C%22path%22%3A%22%2FE%3A%2FCis232Decoupled%2FTenFirstDesignPatterns%2Fsrc%2FDocs%2FJavaGHDevGuideDesignPatterns.md%22%2C%22query%22%3A%22%22%2C%22fragment%22%3A%22%22%7D%2C%22pos%22%3A%7B%22line%22%3A21%2C%22character%22%3A12%7D%7D%5D%2C%2293eed0f0-ebd7-4e0f-861d-df6aa4ac9761%22%5D "Go to definition") class is more realistic and includes proper resource management.
- Add error handling for database connection failures.

**Alteration:**
- Ensure the Singleton pattern adheres to the Single Responsibility Principle by separating connection management from query execution.

**Addition:**
- Introduce a method to close the connection.
- Use Java Streams for query results.

**Relativity:**
- Simulate a real-world database connection scenario.

**Contextual Relevance:**
- Add comments to guide a junior developer.

```java
/**
 * Thread-safe Singleton implementation using enum
 */
public enum DatabaseConnection {
    INSTANCE;

    private Connection connection;

    DatabaseConnection() {
        try {
            // Simulate database connection
            connection = new Connection();
        } catch (Exception e) {
            System.err.println("Failed to establish database connection: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void executeQuery(String query) {
        if (connection != null)

 {


            System.out.println("Executing query: " + query);
            // Simulate query execution and result processing
            connection.execute(query).forEach(System.out::println);
        } else {
            System.err.println("No database connection available.");
        }
    }

    public void closeConnection() {
        if (connection != null) {
            connection.close();
            connection = null;
        }
    }

    // Simulated Connection class
    private static class Connection {
        public void close() {
            System.out.println("Closing database connection.");
        }

        public List<String> execute(String query) {
            // Simulate query execution
            return List.of("Result 1", "Result 2", "Result 3");
        }
    }
}

// Usage
public class SingletonDemo {
    public static void main(String[] args) {
        DatabaseConnection.INSTANCE.executeQuery("SELECT * FROM users");
        DatabaseConnection.INSTANCE.closeConnection();
    }
}
```

#### Factory Method Pattern

**Improvement:**
- Improve readability by using meaningful method names and comments.

**Alteration:**
- Ensure adherence to the Open/Closed Principle by making it easy to add new payment processors without modifying existing code.

**Addition:**
- Add error handling for unsupported payment methods.
- Use Java Streams for processing payments.

**Relativity:**
- Simulate a real-world payment processing scenario.

**Contextual Relevance:**
- Add comments to guide a junior developer.

```java
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
        List<PaymentProcessorFactory> factories = List.of(
            new CreditCardProcessorFactory(),
            new PayPalProcessorFactory()
        );

        factories.forEach(factory -> factory.processPayment(100.00));
    }
}
```

#### Observer Pattern

**Improvement:**
- Improve readability by using meaningful method names and comments.

**Alteration:**
- Ensure adherence to the Single Responsibility Principle by separating stock market management from observer notification.

**Addition:**
- Add error handling for observer management.
- Use Java Streams for notifying observers.

**Relativity:**
- Simulate a real-world stock market monitoring scenario.

**Contextual Relevance:**
- Add comments to guide a junior developer.

```java
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
        if (observer != null) {
            observers.add(observer);
        } else {
            System.err.println("Observer cannot be null.");
        }
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
        observers.stream().forEach(observer -> observer.update(stockSymbol, price));
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
```

### Summary

The improvements, alterations, additions, and contextual relevance adjustments ensure that the code is more efficient, readable, and aligned with OOP best practices. The examples are now more relatable to real-world scenarios that a university student might encounter during internships, with added comments to guide junior developers.