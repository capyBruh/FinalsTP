import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminMenu {
    Utils utils = new Utils();
    Order order = new Order();
    private ArrayList<OrderItem> menu, meals, drinks, combos;
    private boolean ordering = true;
    FastFoodCashier FFC = new FastFoodCashier();

    public AdminMenu(ArrayList<OrderItem> menu, ArrayList<OrderItem> meals, ArrayList<OrderItem> drinks, ArrayList<OrderItem> combos) {
        this.menu = menu;
        this.meals = meals;
        this.drinks = drinks;
        this.combos = combos;
    }

    public void adminMenuInterface(Scanner scan){
        utils.clearConsole();
        System.out.println("- - ADMIN LOG IN - -");
        System.out.println("Please enter the admin password: ");
        if(scan.next().equalsIgnoreCase("pass123")){
            while (ordering) { 
                utils.clearConsole();
                int choice;
                System.out.println("- - ADMIN MENU - -");
                System.out.println("1. Add items to menu");
                System.out.println("2. Remove items from menu");
                System.out.println("3. Display Current Menu");
                System.out.println("4. Return to Main Menu");

                    try {
                            choice = scan.nextInt(); 
                        switch (choice) {
                            case 1 -> {
                                utils.clearConsole();
                                System.out.println("- - Adding New Item to the Menu - -");
                                System.out.println();
                                addItemToMenu(scan);
                            }
                            case 2 -> {
                                utils.clearConsole();
                                System.out.println("- - Removing An Item to the Menu - -");
                                deleteItemFromMenu(scan);
                            }
                            case 3 -> {
                                utils.clearConsole();
                                System.out.println("Current Menu: ");
                                FFC.displayMenu();
            
                                System.out.print("Type and Enter anything to continue: "); 
                                if(scan.next().equals("1")){
                                    utils.clearConsole();
                                    addItemToMenu(scan);
                                } else  {
                                    utils.clearConsole();
                                    addItemToMenu(scan);
                                }
                            }
                            case 4 -> {
                                return;
                            }
                            
                            default -> System.out.println("Invalid choice try again.");
                        }
                    }    catch (Exception e) {
                        scan.nextLine();
                    }
            }
        } else {
            System.out.println("Incorrect admin password");
        }
        
    }

    public void addItemToMenu(Scanner scan) {
        while(true){
            System.out.println("Select the category to add an item:");
            System.out.println("1. Meals");
            System.out.println("2. Drinks");
            System.out.println("3. Combo Meals");
            System.out.println("4. Display Menu");
            System.out.println("5. Back to Admin Menu");

        try {
            int category = scan.nextInt();
            scan.nextLine(); // Consume newline

            // Display the corresponding category before proceeding
            switch (category) {
                case 1 -> {
                    System.out.println("\nCurrent Meals:");
                    FFC.displayMeals(); // Show existing meals
                }
                case 2 -> {
                    System.out.println("\nCurrent Drinks:");
                    FFC.displayDrinks(); // Show existing drinks
                }
                case 3 -> {
                    System.out.println("\nCurrent Combo Meals:");
                    FFC.displayCombos(); // Show existing combos
                }
                case 4 -> {
                    utils.clearConsole();
                    System.out.println("Current Menu: ");
                    FFC.displayMenu();

                    System.out.print("Type and Enter anything to continue: "); 
                    if(scan.next().equals("1")){
                        utils.clearConsole();
                        addItemToMenu(scan);
                    } else  {
                        utils.clearConsole();
                        addItemToMenu(scan);
                    }
                }
                    
                case 5 -> {
                    System.out.println("Returning to Admin Menu");
                    utils.sleep(3);
                    return;
                }
                default -> {
                    System.out.println("Invalid category.");
                    return; // Exit if the category is invalid
                }
            }

            // Proceed to get the item details
            System.out.print("\nEnter the name of the new item: ");
            String name = scan.nextLine();
            System.out.print("Enter the price of the new item: ");
            double price = scan.nextDouble();
            scan.nextLine(); // Consume newline

            OrderItem newItem = new OrderItem(name, price);
                switch (category) {
                    case 1 -> {
                        meals.add(newItem);
                        utils.clearConsole();
                        System.out.println(name + " has been added to the menu! \n");
                        System.out.println("\nUpdated Meals:");
                        FFC.displayMeals();
                        System.out.println();
                    }
                    case 2 -> {
                        drinks.add(newItem);
                        utils.clearConsole();
                        System.out.println(name + " has been added to the menu! \n");
                        System.out.println("\nUpdated Drinks:");
                        FFC.displayDrinks();
                        System.out.println();
                    }
                    case 3 -> {
                        combos.add(newItem);
                        utils.clearConsole();
                        System.out.println(name + " has been added to the menu! \n");
                        System.out.println("\nUpdated Combo Meals:");
                        FFC.displayCombos();
                        System.out.println();
                    }
                }
            } catch (InputMismatchException e) {
                scan.nextLine(); // Clear invalid input
                System.out.println("Invalid input, item not added.");
            }
                

            /* 
            System.out.print("Enter the name of the new item: "); // the new item name
            String name = scanner.next();
            if(scanner.nextLine().equalsIgnoreCase("1")){
                break;
            }
            System.out.print("Enter the price of the new item: "); // the new item price
            double price;
            try {
                price = scanner.nextDouble();
            } catch (InputMismatchException e) {
                utils.clearConsole();
                System.out.println("Invalid price! Item not added! \n");
                System.out.print("Returning to Admin Menu");
                utils.sleep(3);
                return;
            }

            
            System.out.println("Confirm adding new item? yes/no"); // confirmation of new item
            String c = scanner.next();

            if(c.equalsIgnoreCase("yes")){
                menu.add(new OrderItem(name, price));
                utils.clearConsole();
                System.out.println(name + "has been added to the menu! \n");

                System.out.println("Add another item? yes/no"); // asks if the user wants to add another
                String ch = scanner.next();
                if(ch.equalsIgnoreCase("yes")){
                } else if(ch.equalsIgnoreCase("no")){
                    System.out.print("Returning to Admin Menu");
                    utils.sleep(3);
                    break;
                } else {
                    System.out.println("Invalid input, returning to Admin Menu.");
                    utils.sleep(3);
                    break;
                }

            } else if(c.equalsIgnoreCase("no")){
                System.out.print("Returning to Admin Menu");
                utils.sleep(3);
                break;
            } else{
                System.out.println("Invalid input.");
            }*/
        }
    }

    public void deleteItemFromMenu(Scanner scanner) {
        FFC.displayMenu();

        while(true){
            System.out.print("\nEnter the number of the item you'd like to delete: ");
            int itemChoice;
            try {
                itemChoice = scanner.nextInt();
                if (itemChoice < 1 || itemChoice > menu.size()) {
                    utils.clearConsole();
                    System.out.println("Invalid choice! No item deleted. \n");
                    System.out.print("Returning to Admin Menu");
                    utils.sleep(3);
                    return;
                }
            System.out.println("Confirm deleting the item? yes/no");
                String ch = scanner.next();
                if(ch.equalsIgnoreCase("yes")){
                } else if (ch.equalsIgnoreCase("no")){
                    break;
                } else {
                    System.out.print("Invalid input, returning to Admin Menu");
                    utils.sleep(3);
                }

                OrderItem removedItem = menu.remove(itemChoice - 1);
                System.out.println(removedItem.getName() + " has been removed from the menu.");
            } catch (InputMismatchException e) {
                utils.clearConsole();
                System.out.println("Invalid input! Please enter a NUMBER. \n");
                System.out.print("Returning to Admin Menu");
                utils.sleep(3);
                return;
            }


                

        }
    }
}
