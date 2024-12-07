import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminMenu {
    Utils utils = new Utils();
    Order order = new Order();
    private final ArrayList<OrderItem> menu, meals, drinks, combos;
    boolean ordering = true;

    public AdminMenu(ArrayList<OrderItem> menu, ArrayList<OrderItem> meals, ArrayList<OrderItem> drinks, ArrayList<OrderItem> combos) {
        this.menu = menu; //initializes all the menu and categories from FastFoodCashier class
        this.meals = meals;
        this.drinks = drinks;
        this.combos = combos;
    }

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
    /* admin menu interface methods */

    public void adminMenuInterface(Scanner scan){
        utils.clearConsole();
        String currentPassword = PasswordManager.loadAdminPassword(); //loads the password file
        System.out.println("--------ADMIN LOG IN-------");
        System.out.println("Please enter the admin PASSWORD: ");
        
        if(scan.next().equalsIgnoreCase(currentPassword)){  //checks if the password is correct from the file
            adminMenuChoices(scan); //jumps to admin menu choices
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
                        System.out.println("-------Adding New Item to the Menu-------"); //adding interface
                        System.out.println();
                        addItemToMenu(scan); //jumps to the given method
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

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
    /* adding items in menu method interfaces */

    public void addItemToMenu(Scanner scan) {
        while (true) {
            System.out.println("Select the category to add an item:");
            System.out.println("1. Meals");
            System.out.println("2. Drinks");
            System.out.println("3. Combo Meals");
            System.out.println("4. Display Menu");
            System.out.println("5. Back to Admin Menu");
    
            try {
                String category = scan.next().trim();
                if(category.equals("5")){
                    System.out.println("Returning to Admin Menu..."); //breaks the add to menu interface loop if "5"
                    utils.sleep(3);
                    return;
                }
                addToMenuChoices(scan, category); //a switch method output of the selection
            } catch (InputMismatchException e) {
                scan.nextLine(); // clears invalid input
                System.out.println("Invalid input, item not added.");
            }
        }
    }

    private void addToMenuChoices(Scanner scan, String category){
        switch (category) {
            case "1" -> {
                System.out.println("\nCurrent Meals:");
                FastFoodCashier.displayMeals(); // displays existing meals
                addingItem(scan, category); //method to add the item in the menu file
            }
            case "2" -> {
                System.out.println("\nCurrent Drinks:");
                FastFoodCashier.displayDrinks(); // displays  existing drinks
                addingItem(scan, category); //method to add the item in the menu file
            }
            case "3" -> {
                System.out.println("\nCurrent Combo Meals:");
                FastFoodCashier.displayCombos(); // displays existing combos
                addingItem(scan, category); //method to add the item in the menu file
            }
            case "4" -> {
                utils.clearConsole();
                System.out.println("Current Menu:"); //prints all the menu
                FastFoodCashier.displayMenu();
                System.out.print("\n Press Enter to return to the Admin Menu...");
                scan.nextLine();
                scan.nextLine(); // wait for user input
                utils.clearConsole();
                return;
            }
            default -> {
                System.out.println("Invalid category.");
                return; // skip to the next loop iteration
            }
        }
    }

    private void addingItem(Scanner scan, String category){
        System.out.print("\nEnter the name of the new item: "); //asks for the item name
        scan.nextLine();
        String name = scan.nextLine();
        System.out.print("Enter the price of the new item: "); //asks for item price
        double price = scan.nextDouble();
        scan.nextLine(); 

        OrderItem newItem = new OrderItem(name, price);
        switch (category) {
            case "1" -> {
                MenuManager.meals.add(newItem); //adds the inputted item to the meals category in the file
                utils.clearConsole();
                System.out.println(name + " has been added to the menu.");
                System.out.println("\nUpdated Meals:");
                FastFoodCashier.displayMeals(); //displays the meals
                System.out.println();
            }
            case "2" -> {
                MenuManager.drinks.add(newItem); //adds the inputted item to the meals category in the file
                utils.clearConsole();
                System.out.println(name + " has been added to the menu.");
                System.out.println("\nUpdated Drinks:");
                FastFoodCashier.displayDrinks(); //displays the drinks
                System.out.println();
            }
            case "3" -> {
                MenuManager.combos.add(newItem); //adds the inputted item to the meals category in the file
                utils.clearConsole();
                System.out.println(name + " has been added to the menu.");
                System.out.println("\nUpdated Combo Meals:");
                FastFoodCashier.displayCombos(); //displays the combos
                System.out.println();
            }
        }
        MenuManager.saveMenu(); // Save the updated menu
    }

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
    /* removing items in menu method interfaces */

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

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
    /* changing the password method interfaces */

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

