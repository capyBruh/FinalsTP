import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminMenu {
    Utils utils = new Utils();
    Order order = new Order();
    private final ArrayList<OrderItem> menu, meals, drinks, combos;
    boolean ordering = true;

    public AdminMenu(ArrayList<OrderItem> menu, ArrayList<OrderItem> meals, ArrayList<OrderItem> drinks, ArrayList<OrderItem> combos) {
        // Initialize lists if they are null
        this.menu = menu;
        this.meals = meals;
        this.drinks = drinks;
        this.combos = combos;
    }


    public void adminMenuInterface(Scanner scan){
        utils.clearConsole();
        String currentPassword = PasswordManager.loadAdminPassword();
        System.out.println("--------ADMIN LOG IN-------");
        System.out.println("Please enter the admin PASSWORD: ");
        
        
        if(scan.next().equalsIgnoreCase(currentPassword)){
            adminMenuChoices(scan);
        } else {
            System.out.println("Incorrect admin PASSWORD");
            System.out.println("Returning to Main Menu");
            utils.sleep(3);
        }
        
    }

    private void adminMenuChoices(Scanner scan){
        while (true) {
            try {
                utils.clearConsole();
                System.out.println("-------ADMIN MENU--------");
                System.out.println("1. Add items to menu");
                System.out.println("2. Remove items from menu");
                System.out.println("3. Display Current Menu");
                System.out.println("4. Change admin password");
                System.out.println("5. Return to Main Menu");

            
                String choice = scan.next();
                switch (choice) {
                    case "1" -> {
                        utils.clearConsole();
                        System.out.println("-------Adding New Item to the Menu-------");
                        System.out.println();
                        addItemToMenu(scan);
                    }
                    case "2" -> {
                        utils.clearConsole();
                        System.out.println("--------Removing An Item to the Menu-------");
                        deleteItemFromMenu(scan);
                    }
                    case "3" -> {
                        utils.clearConsole();
                        System.out.println("Current Menu:");
                        FastFoodCashier.displayMenu();
                        System.out.print("\n Press Enter to return to the Admin Menu...");
                        scan.nextLine();
                        scan.nextLine(); // Wait for user input before continuing
                    }
                    case "4" -> {
                        utils.clearConsole();
                        changeAdminPassword(scan);
                    }
                    case "5" -> {
                        ordering = false; // Exit the admin menu loop
                        System.out.println("Returning to Main Menu...");
                        utils.sleep(2);
                        order.clearOrder();
                        return;
                    }
                    default -> {
                        System.out.println("Invalid choice, try again.");
                        utils.sleep(2);
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid choice, try again.");
                utils.sleep(3);
            }
        }
    }



    public void addItemToMenu(Scanner scan) {
        while (true) {
            System.out.println("Select the category to add an item:");
            System.out.println("1. Meals");
            System.out.println("2. Drinks");
            System.out.println("3. Combo Meals");
            System.out.println("4. Display Menu");
            System.out.println("5. Back to Admin Menu");
    
            try {
                String category = scan.next().trim(); // Consume newline
                // Display the corresponding category before proceeding
                if(category.equals("5")){
                    System.out.println("Returning to Admin Menu...");
                    utils.sleep(3);
                    return;
                }
                addToMenuChoices(scan, category);

                // Proceed to get the item details if a valid category is chosen
            } catch (InputMismatchException e) {
                scan.nextLine(); // Clear invalid input
                System.out.println("Invalid input, item not added.");
            }
        }
    }

    private void addToMenuChoices(Scanner scan, String category){
        switch (category) {
            case "1" -> {
                System.out.println("\nCurrent Meals:");
                FastFoodCashier.displayMeals(); // Show existing meals
                addingItem(scan, category);
            }
            case "2" -> {
                System.out.println("\nCurrent Drinks:");
                FastFoodCashier.displayDrinks(); // Show existing drinks
                addingItem(scan, category);
            }
            case "3" -> {
                System.out.println("\nCurrent Combo Meals:");
                FastFoodCashier.displayCombos(); // Show existing combos
                addingItem(scan, category);
            }
            case "4" -> {
                utils.clearConsole();
                System.out.println("Current Menu:");
                FastFoodCashier.displayMenu();
                System.out.print("\n Press Enter to return to the Admin Menu...");
                scan.nextLine();
                scan.nextLine(); // Wait for user input
                utils.clearConsole();
                return;
            }
            default -> {
                System.out.println("Invalid category.");
                return; // Skip to the next loop iteration
            }
        }
    }

    private void addingItem(Scanner scan, String category){
        System.out.print("\nEnter the name of the new item: ");
        scan.nextLine();
        String name = scan.nextLine();
        System.out.print("Enter the price of the new item: ");
        double price = scan.nextDouble();
        scan.nextLine(); // Consume newline

        OrderItem newItem = new OrderItem(name, price);
        switch (category) {
            case "1" -> {
                MenuManager.meals.add(newItem);
                utils.clearConsole();
                System.out.println(name + " has been added to the menu.");
                System.out.println("\nUpdated Meals:");
                FastFoodCashier.displayMeals();
                System.out.println();
            }
            case "2" -> {
                MenuManager.drinks.add(newItem);
                utils.clearConsole();
                System.out.println(name + " has been added to the menu.");
                System.out.println("\nUpdated Drinks:");
                FastFoodCashier.displayDrinks();
                System.out.println();
            }
            case "3" -> {
                MenuManager.combos.add(newItem);
                utils.clearConsole();
                System.out.println(name + " has been added to the menu.");
                System.out.println("\nUpdated Combo Meals:");
                FastFoodCashier.displayCombos();
                System.out.println();
            }
        }
        MenuManager.saveMenu(); // Save the updated menu
    }



    public void deleteItemFromMenu(Scanner scan) {
        while (true) {
            utils.clearConsole();
            FastFoodCashier.displayMenu();
    
            System.out.print("\nEnter the number of the item you'd like to delete: ");
            int itemChoice;
    
            try {
                // Validate input is a valid integer
                itemChoice = scan.nextInt();
    
                // Check if the item choice is within a valid range
                if (itemChoice < 1 || itemChoice > MenuManager.menu.size()) {
                    System.out.println("Invalid choice! No item deleted.\n");
                    System.out.print("Returning to Admin Menu...");
                    utils.sleep(3);
                    return;
                }
    
                // Ask for confirmation
                System.out.println("Confirm deleting the item? (yes/no)");
                String confirmation = scan.next().trim();  // Read confirmation input
    
                if (confirmation.equalsIgnoreCase("yes")) {
                    OrderItem removedItem;
    
                    // Remove the item from the appropriate category
                    if (itemChoice <= MenuManager.meals.size()) {
                        removedItem = MenuManager.meals.remove(itemChoice - 1);
                    } else if (itemChoice <= MenuManager.meals.size() + MenuManager.drinks.size()) {
                        removedItem = MenuManager.drinks.remove(itemChoice - MenuManager.meals.size() - 1);
                    } else {
                        removedItem = MenuManager.combos.remove(itemChoice - MenuManager.meals.size() - MenuManager.drinks.size() - 1);
                    }
    
                    MenuManager.saveMenu(); // Save the updated menu
                    utils.clearConsole();
                    System.out.println(removedItem.getName() + " has been removed from the menu.");
    
                    // Ask if the user wants to delete another item
                    System.out.print("Do you want to delete another item? (yes/no): ");
                    String deleteMore = scan.next().trim();
                    if (!deleteMore.equalsIgnoreCase("yes")) {
                        System.out.println("Returning to Admin Menu...");
                        utils.sleep(3);
                        return;
                    }
                } else if (confirmation.equalsIgnoreCase("no")) {
                    System.out.println("Item deletion cancelled.");
                    System.out.println("Returning to Admin Menu...");
                    utils.sleep(3);
                    return;
                } else {
                    System.out.println("Invalid input. Returning to Admin Menu...");
                    utils.sleep(3);
                    return;
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid number.\n");
                System.out.print("Returning to Admin Menu...");
                utils.sleep(3);
                return;
            }
        }
    }



    private void changeAdminPassword(Scanner scan) {

        System.out.print("Enter the current admin PASSWORD: ");
        String enteredPassword = scan.next();
        String currentPassword = PasswordManager.loadAdminPassword();
        if (!enteredPassword.equals(currentPassword)) {
            System.out.println("Incorrect current PASSWORD. Returning to Admin Menu...");
            utils.sleep(2);
            return;
        }

        System.out.print("Enter the new admin PASSWORD: ");
        String newPassword = scan.next();

        System.out.print("Confirm the new admin PASSWORD: ");
        String confirmPassword = scan.next();

        if (newPassword.equals(confirmPassword)) {
            PasswordManager.saveAdminPassword(newPassword);
            System.out.println("Admin PASSWORD successfully changed!");
        } else {
            System.out.println("PASSWORDs do not match. Returning to Admin Menu...");
        }
        utils.sleep(2);
    }
}

