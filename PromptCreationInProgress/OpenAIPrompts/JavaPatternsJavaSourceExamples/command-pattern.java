import java.util.ArrayList;
import java.util.List;

/**
 * Command Pattern implementation for a simple task management system
 */
public interface Command {
    void execute();
    void undo();
}

public class Task {
    private String name;
    private boolean completed;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    public void complete() {
        completed = true;
        System.out.println("Task '" + name + "' completed");
    }

    public void reopen() {
        completed = false;
        System.out.println("Task '" + name + "' reopened");
    }
}

public class CompleteTaskCommand implements Command {
    private Task task;

    public CompleteTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute() {
        task.complete();
    }

    @Override
    public void undo() {
        task.reopen();
    }
}

public class TaskManager {
    private List<Command> commandHistory = new ArrayList<>();

    public void executeCommand(Command command) {
        command.execute();
        commandHistory.add(command);
    }

    public void undoLastCommand() {
        if (!commandHistory.isEmpty()) {
            Command lastCommand = commandHistory.remove(commandHistory.size() - 1);
            lastCommand.undo();
        }
    }
}

// Usage
public class CommandDemo {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Task task1 = new Task("Implement feature X");
        Task task2 = new Task("Write documentation");

        taskManager.executeCommand(new CompleteTaskCommand(task1));
        taskManager.executeCommand(new CompleteTaskCommand(task2));
        taskManager.undoLastCommand();
    }
}
