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
