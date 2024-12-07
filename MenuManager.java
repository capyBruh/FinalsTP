import java.io.*;
import java.util.ArrayList;
public class MenuManager {
    private static final String MENU_FILE = "menu.txt";
    public static ArrayList<OrderItem> meals = new ArrayList<>();
    public static ArrayList<OrderItem> drinks = new ArrayList<>();
    public static ArrayList<OrderItem> combos = new ArrayList<>();
    public static ArrayList<OrderItem> menu = new ArrayList<>();
    static Utils utils = new Utils();
    
    public static void loadMenu() {
        try (BufferedReader reader = new BufferedReader(new FileReader(MENU_FILE))) { 
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":"); //split the category from name by colon ":"
                if (parts.length == 2) {
                    String category = parts[0].trim(); // Extract the category
                    String[] nameAndPrice = parts[1].split("P", 2); // Split the name and price using the peso sign
                    if (nameAndPrice.length == 2) {
                        String name = nameAndPrice[0].trim(); // Extract the name
                        double price = Double.parseDouble(nameAndPrice[1].trim()); // Parse the price

                        OrderItem item = new OrderItem(name, price); // Load the menu from the text file
                        switch (category.toLowerCase()) { //checks the name of the category in the txt file
                            case "meals" -> meals.add(item);
                            case "drinks" -> drinks.add(item);
                            case "combos" -> combos.add(item);
                            default -> System.out.println("Unknown category: " + category);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Menu file not found."); //if the file is not found then it just initializes the default password
            System.out.println("Initializing default menu.");
            System.out.println("Please customize your own menu.");
            initializeDefaultMenu();
            utils.sleep(5);
        } catch (IOException e) {
            System.out.println("Error reading menu file: " + e.getMessage());
            utils.sleep(5);
        }
        menu.addAll(meals);
        menu.addAll(drinks);
        menu.addAll(combos);
    }

    // Save the current menu to the text file
    public static void saveMenu() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MENU_FILE))) {
            for (OrderItem item : meals) {
                writer.write("meals: " + item.getName() + " P" + item.getPrice() + "\n"); //saves all the added meals in the menu file
            }
            for (OrderItem item : drinks) {
                writer.write("drinks: " + item.getName() + " P" + item.getPrice() + "\n"); //saves all the added drinks in the menu file
            }
            for (OrderItem item : combos) {
                writer.write("combos: " + item.getName() + " P" + item.getPrice() + "\n"); //saves all the added combos in the menu file
            }
        } catch (IOException e) {
            System.out.println("Error saving menu: " + e.getMessage());
            utils.sleep(5);
        }
    }

    private static void initializeDefaultMenu() {
        meals.add(new OrderItem("Burger", 50)); // Add default meals
        drinks.add(new OrderItem("Soda", 20)); // Add default drinks
        combos.add(new OrderItem("Burger and Soda", 60)); // Add default combos
        saveMenu(); // Save the default menu to the file
    }
}
