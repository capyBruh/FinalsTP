import java.util.ArrayList;
import java.util.Scanner;

public class FastFoodCashier {
    private static ArrayList<OrderItem> meals = new ArrayList<>();
    private static ArrayList<OrderItem> drinks = new ArrayList<>();
    private static ArrayList<OrderItem> combos = new ArrayList<>();
    private static ArrayList<OrderItem> menu = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Order order = new Order();
        AdminMenu adminMenu = new AdminMenu(menu, meals, drinks, combos);
        boolean ordering = true;
        Utils utils = new Utils();

        // Initialize the menu
        initializeMenu();
        

        while (true) { 
            ordering = true;
            utils.clearConsole();
            System.out.println("Welcome to FastFood Cashier System!");
            System.out.print("Please enter your name: ");
            
            String name = scan.next().trim();
            order.setName(name); // setting the name of the customer

            if(order.getName().equalsIgnoreCase("admin")){ //checks if the name is admin
                utils.clearConsole();
                System.out.println("- - ADMIN LOG IN - -");
                System.out.println("Please enter the admin password: "); //if right password it goes to ADMIN interface

                adminMenu.adminMenuInterface(scan);
            }

            while (ordering) {
                displayMenu();
                order.displayOrderSummary();
                System.out.print("\nEnter the number of the item you'd like to order: "); //displays order first then asks which order

                int itemChoice;
                try {
                    itemChoice = scan.nextInt();
                    if (itemChoice < 1 || itemChoice > menu.size()) {
                        System.out.println("Invalid choice! Please select a valid menu item.");  // if the input is higher or lower than the menu count, prompts this
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a number."); //number mismatch catch expression
                    continue;
                }

                order.addItem(menu.get(itemChoice - 1)); //adds the order to the list
                utils.clearConsole();
                System.out.println(menu.get(itemChoice - 1).getName() + " has been added to your order!");  //prints the latest order

                order.displayOrderSummary();
                System.out.print("\nAdd another item? (yes/no): "); //asks for another order
                
                String response = scan.next().trim();
                if (!response.equalsIgnoreCase("yes")) {
                    order.displayOrderSummary();

                    boolean confirmation = true;
                    while (confirmation) { 
                        System.out.print("\nConfirm order? (1yes/2add more/3cancel order): "); //order confirmation
                        String confirm = scan.next().trim().toLowerCase();
                        if (confirm.equals("1")) { //confirmation of order that prints receipt
                            utils.clearConsole();
                            order.printReceipt();
                            System.out.print("Click 1 to continue: "); 

                            if(scan.next().equals("1")){ //clears everything after getting the receipt
                                utils.clearConsole();
                                order.clearOrder();
                                confirmation = false;
                                ordering = false;
                            }

                        } else if(confirm.equals("2")) { //adds new order by breaking the confirmation loop
                            confirmation = false;

                        } else if(confirm.equals("3")) { //clears everything without receipt
                            System.out.println("Order cancelled.");
                            utils.clearConsole();
                            order.clearOrder();
                            confirmation = false;
                            ordering = false;
                        }else {
                            System.out.println("Invalid input, try again.");
                        }
                    }
                }
                utils.clearConsole();
            }
        }
    }
        /* 
        while (true) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Order items");
            System.out.println("2. Add a new item to the menu");
            System.out.println("3. Delete an item from the menu");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    ordering = true;

                    order.displayOrderSummary();

                    System.out.println("1. yes");
                    System.out.println("2. add another");
                    System.out.println("3. cancel order");
                    System.out.print("\nConfirm order? : ");
                    
                    int confirm = scanner.nextInt();

                    if (confirm == 1) {
                        order.printReceipt();
                    } else if (confirm == 2) {
                        System.out.println("Order cancelled.");
                    } else if (confirm == 3) {
                        System.out.println("Order cancelled.");
                    }
                    break;

                case 2:
                    adminMenu.addItemToMenu(scanner);
                    break;

                case 3:
                    adminMenu.deleteItemFromMenu(scanner);
                    break;

                case 4:
                    System.out.println("Thank you for using FastFood Cashier System. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }*/

    public static void initializeMenu() {
        meals.add(new OrderItem("Burger", 50));
        meals.add(new OrderItem("Fries", 35));
        drinks.add(new OrderItem("Soda", 20));
        drinks.add(new OrderItem("Juice", 20));
        combos.add(new OrderItem("Burger and Juice", 60));
        combos.add(new OrderItem("Fries and Soda", 50));
        menu.addAll(meals);
        menu.addAll(drinks);
        menu.addAll(combos);
    }

    public static void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("Meals: ");
        for (int i = 0; i < meals.size(); i++) {
            System.out.println((i + 1) + ". " + meals.get(i));
        }
        System.out.println("Drinks: ");
        for (int i = 0; i < drinks.size(); i++) {
            System.out.println((i + 1 + meals.size()) + ". " + drinks.get(i));
        }
        System.out.println("Combos: ");
        for (int i = 0; i < combos.size(); i++) {
            System.out.println((i + 1 + meals.size() + drinks.size()) + ". " + combos.get(i));
        }
    }

    public static void displayMeals(){
        System.out.println("Meals: ");
        for (int i = 0; i < meals.size(); i++) {
            System.out.println((i + 1) + ". " + meals.get(i));
        }
    }
    public static void displayDrinks(){
        System.out.println("Drinks: ");
        for (int i = 0; i < drinks.size(); i++) {
            System.out.println((i + 1 + meals.size()) + ". " + drinks.get(i));
        }
    }
    public static void displayCombos(){
        System.out.println("Combos: ");
        for (int i = 0; i < combos.size(); i++) {
            System.out.println((i + 1 + meals.size() + drinks.size()) + ". " + combos.get(i));
        }
    }
}
