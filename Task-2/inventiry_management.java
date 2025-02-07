import java.io.*;
import java.util.*;
import java.util.concurrent.*;

// Product Class
class Product {
    private final String productID;
    private final String name;
    private int quantity;
    private final Location location;

    public Product(String productID, String name, int quantity, Location location) {
        this.productID = productID;
        this.name = name;
        this.quantity = quantity;
        this.location = location;
    }

    public synchronized void updateQuantity(int delta) throws OutOfStockException {
        if (this.quantity + delta < 0) {
            throw new OutOfStockException("Product " + name + " is out of stock.");
        }
        this.quantity += delta;
    }

    public String getProductID() { return productID; }
    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public Location getLocation() { return location; }
}

// Location Class
class Location {
    private final int aisle;
    private final int shelf;
    private final int bin;

    public Location(int aisle, int shelf, int bin) {
        this.aisle = aisle;
        this.shelf = shelf;
        this.bin = bin;
    }

    @Override
    public String toString() {
        return "Aisle " + aisle + ", Shelf " + shelf + ", Bin " + bin;
    }
}

// Order Class
class Order implements Comparable<Order> {
    private final String orderID;
    private final List<String> productIDs;
    private final Priority priority;

    public enum Priority { STANDARD, EXPEDITED }

    public Order(String orderID, List<String> productIDs, Priority priority) {
        this.orderID = orderID;
        this.productIDs = productIDs;
        this.priority = priority;
    }

    public String getOrderID() { return orderID; }
    public List<String> getProductIDs() { return productIDs; }
    public Priority getPriority() { return priority; }

    @Override
    public int compareTo(Order other) {
        return this.priority.compareTo(other.priority);
    }
}

// Exception Handling
class OutOfStockException extends Exception {
    public OutOfStockException(String message) { super(message); }
}

class InventoryManager {
    private final Map<String, Product> products = new ConcurrentHashMap<>();
    private final PriorityQueue<Order> orderQueue = new PriorityQueue<>();

    public synchronized void addProduct(Product product) {
        products.put(product.getProductID(), product);
    }

    public void placeOrder(Order order) {
        orderQueue.add(order);
        new Thread(() -> processOrder(order)).start();
    }

    private void processOrder(Order order) {
        for (String productID : order.getProductIDs()) {
            Product product = products.get(productID);
            if (product != null) {
                try {
                  
                    product.updateQuantity(-1);
                    System.out.println("Order " + order.getOrderID() + " processed for product " + product.getName());
                } catch (OutOfStockException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }
}

// Main Class
public class inventiry_management {
    public static void main(String[] args) {
        InventoryManager inventoryManager = new InventoryManager();
        Location loc1 = new Location(1, 2, 3);
        Product p1 = new Product("P001", "Laptop", 10, loc1);
        inventoryManager.addProduct(p1);

        List<String> products = new ArrayList<>();
        products.add("P001");
        Order order1 = new Order("O1001", products, Order.Priority.EXPEDITED); 
        inventoryManager.placeOrder(order1);
    }
}