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
