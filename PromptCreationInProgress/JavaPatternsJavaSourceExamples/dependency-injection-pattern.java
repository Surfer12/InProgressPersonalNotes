/**
 * Dependency Injection Pattern implementation
 */
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
