import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class TaskManager {
    private PriorityQueue<Task> taskQueue;
    private HashMap<String, Task> taskMap;

    public TaskManager() {
       
        taskQueue = new PriorityQueue<>(Comparator.comparingInt(Task::getPriority).reversed());
        taskMap = new HashMap<>();
    }

    public void addTask(String id, String description, int priority) {
        if (id == null || description == null) {
            throw new IllegalArgumentException("ID and description cannot be null");
        }
        
        if (taskMap.containsKey(id)) {
            throw new IllegalArgumentException("Task with ID " + id + " already exists");
        }

        Task task = new Task(id, description, priority);
        taskQueue.offer(task);
        taskMap.put(id, task);
    }

    public Task removeTask(String id) {
        if (id == null || !taskMap.containsKey(id)) {
            throw new IllegalArgumentException("Task with ID " + id + " not found");
        }

        Task task = taskMap.remove(id);
        taskQueue.remove(task);
        return task;
    }

    public Task getHighestPriorityTask() {
        if (taskQueue.isEmpty()) {
            return null;
        }
        return taskQueue.peek();
    }

    public int getTaskCount() {
        return taskQueue.size();
    }

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        
        System.out.println("Adding tasks...");
        try {
            manager.addTask("T1", "Urgent bug fix", 3);
            manager.addTask("T2", "Documentation update", 1);
            manager.addTask("T3", "Security patch", 5);
            manager.addTask("T4", "Feature implementation", 2);
            System.out.println("Successfully added 4 tasks.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding task: " + e.getMessage());
        }

        
        System.out.println("\nTotal tasks: " + manager.getTaskCount());

        
        System.out.println("\nHighest priority task:");
        Task highestPriority = manager.getHighestPriorityTask();
        System.out.println(highestPriority);

        
        System.out.println("\nRemoving task T2...");
        try {
            Task removedTask = manager.removeTask("T2");
            System.out.println("Successfully removed: " + removedTask);
        } catch (IllegalArgumentException e) {
            System.out.println("Error removing task: " + e.getMessage());
        }

       
        System.out.println("\nUpdated task count: " + manager.getTaskCount());

        
        System.out.println("\nTrying to add task with duplicate ID (T1)...");
        try {
            manager.addTask("T1", "Another task", 4);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        
        System.out.println("\nTrying to remove non-existent task...");
        try {
            manager.removeTask("T99");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
