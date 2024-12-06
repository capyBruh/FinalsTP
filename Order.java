import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    private final String orderId;
    private final List<OrderItem> items;
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
        System.out.println("\n------Order Summary------");
        for (OrderItem item : items) {
            System.out.println("- " + item);
        }
        System.out.println("Total: P" + String.format("%.2f", total));
        System.out.println("----------------------------");
    }

    public void printReceipt() {
        if (total == 0) {
            System.out.println("Order total is 0. The order has been canceled.");
            clearOrder();
            return;
        }
    
        System.out.println("\n-------- RECEIPT --------");
        System.out.println("Customer Name: " + name);
        System.out.println("Order ID: " + orderId);
        for (OrderItem item : items) {
            System.out.println("- " + item);
        }
        System.out.println("Total: P" + String.format("%.2f", total));
        System.out.println("----------------------------");
        System.out.println("Thank you for your order!");
        System.out.println("----------------------------");
    
        saveReceiptToFile();
    }

    private void saveReceiptToFile() {
        try {
            // Create the orders folder if it doesn't exist
            File folder = new File("orders");
            if (!folder.exists()) {
                folder.mkdir();
            }

            // Create a text file named after the order ID
            File receiptFile = new File(folder, orderId + ".txt");
            // Write the receipt content to the file
            try (FileWriter writer = new FileWriter(receiptFile)) {
                // Write the receipt content to the file
                writer.write("--------- RECEIPT ---------\n");
                writer.write("Customer Name: " + name + "\n");
                writer.write("Order ID: " + orderId + "\n");
                for (OrderItem item : items) {
                    writer.write("- " + item + "\n");
                }
                writer.write("Total: P" + String.format("%.2f", total) + "\n");
                writer.write("----------------------------");
                writer.write("\nThank you for your order!\n");
                writer.write("----------------------------");
            }

            System.out.println("Receipt saved to file: " + receiptFile.getAbsolutePath());

        } catch (IOException e) {
            System.out.println("An error occurred while saving the receipt: " + e.getMessage());
        }
    }
}
