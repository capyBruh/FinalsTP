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
        this.orderId = UUID.randomUUID().toString().substring(0, 8).toUpperCase(); //generates random unique ID
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
        total += item.getPrice(); //stores the chosen items to an array list
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
        name = null; //clears the entire ordering
    }
    public void displayOrderSummary() {
        System.out.println("\n------Order Summary------");
        for (OrderItem item : items) {
            System.out.println("- " + item); //displays the arraylist of items
        }
        System.out.println("Total: P" + String.format("%.2f", total));
        System.out.println("----------------------------");
    }

    public void printReceipt() {
        if (total == 0) {
            System.out.println("Order total is 0. The order has been canceled."); //canceling the order if the total is zero 0
            clearOrder();
            return; 
        }
    
        System.out.println("\n-------- RECEIPT --------");
        System.out.println("Customer Name: " + name);
        System.out.println("Order ID: " + orderId);
        for (OrderItem item : items) {
            System.out.println("- " + item); //prints all the chosen items in the terminal
        }
        System.out.println("Total: P" + String.format("%.2f", total));
        System.out.println("----------------------------");
        System.out.println("Thank you for your order!");
        System.out.println("----------------------------");
    
        saveReceiptToFile();
    }

    private void saveReceiptToFile() {
        try {
            
            File folder = new File("orders"); // creates the orders folder if it doesn't exist
            if (!folder.exists()) {
                folder.mkdir();
            }
            File receiptFile = new File(folder, orderId + ".txt"); // in the folder made, create a text file named after the order ID
            try (FileWriter writer = new FileWriter(receiptFile)) { // writes the receipt content to the file
                writer.write("--------- RECEIPT ---------\n");
                writer.write("Customer Name: " + name + "\n"); //order credentials 
                writer.write("Order ID: " + orderId + "\n");
                for (OrderItem item : items) {
                    writer.write("- " + item + "\n"); //prints all the ordered item in different lines
                }
                writer.write("Total: P" + String.format("%.2f", total) + "\n"); 
                writer.write("----------------------------");
                writer.write("\nThank you for your order!\n");
                writer.write("----------------------------");
            }
            System.out.println("Receipt saved to file: " + receiptFile.getAbsolutePath()); //prints the file location
        } catch (IOException e) {
            System.out.println("An error occurred while saving the receipt: " + e.getMessage());
        }
    }
}
