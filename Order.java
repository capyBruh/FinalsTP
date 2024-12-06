import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    private String orderId;
    private List<OrderItem> items;
    private double total;
    private String name;

    public Order() {
        this.orderId = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.items = new ArrayList<>();
        this.total = 0;
        this.name = null;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void addItem(OrderItem item) {
        items.add(item);
        total += item.getPrice();
    }

    public String getOrderId() {
        return orderId;
    }

    public double getTotal() {
        return total;
    }

    public void clearOrder(){
        items.clear();
        total = 0;
        name = null;
    }
    public void displayOrderSummary() {
        System.out.println("\nOrder Summary:");
        for (OrderItem item : items) {
            System.out.println("- " + item);
        }
        System.out.println("Total: ₱" + String.format("%.2f", total));
    }

    public void printReceipt() {
        System.out.println("\n--- RECEIPT ---");
        System.out.println("Customer Name: " + name);
        System.out.println("Order ID: " + orderId);
        for (OrderItem item : items) {
            System.out.println("- " + item);
        }
        System.out.println("Total: $" + String.format("%.2f", total));
        System.out.println("Thank you for your order!");
    }
}
