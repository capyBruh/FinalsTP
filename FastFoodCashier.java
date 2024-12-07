import java.util.InputMismatchException;
import java.util.Scanner;

public class FastFoodCashier {
static boolean ordering = true;
    static String name;

        static AdminMenu adminMenu = new AdminMenu(MenuManager.menu, MenuManager.meals, MenuManager.drinks, MenuManager.combos); //uses the txt menu 
        static Scanner scan = new Scanner(System.in);
        static Order order = new Order();  
        static Utils utils = new Utils();
    public static void main(String[] args) {
        
        initializeMenu(); //initializes menu from the menu.txt file

        while (true) { 
            ordering = true;
            utils.clearConsole();
            System.out.println("Welcome to CRAZY RAPID FOODS!!!");
            System.out.print("Please enter your name: ");
            
            name = scan.next().trim();
            order.setName(name); // setting the name of the customer

            checkAdminLogin(); //checks if the input given is "admin" as name  

            while (ordering) {
                displayMenu();
                order.displayOrderSummary();
                addItems(); 

                System.out.print("\nAdd another item? (yes/no): "); //asks for another order
                scan.nextLine();
                String response = scan.nextLine().trim();
                if (!response.equalsIgnoreCase("yes")) { // if not yes then goes to order confirmation
                    order.displayOrderSummary();
                    orderConfirmation(); // gives the user choices to do before confirming
                }
                utils.clearConsole();
            }
        }
    }

    public static void checkAdminLogin(){
        if(name.equalsIgnoreCase("admin")){ //checks if the name is admin
            utils.clearConsole();
            System.out.println("-------ADMIN LOG IN-------");
            adminMenu.adminMenuInterface(scan); //jumps to admin interface and checks if the password is right
            order.clearOrder();
            ordering = false;
        }
    }

    public static void addItems() {  // Displays order first, then asks which to order // Read input as a string
        System.out.print("\nEnter the number of the item you'd like to order: ");
        try {
            
            int itemChoice = scan.nextInt();
            if (itemChoice < 1 || itemChoice > MenuManager.menu.size()) { //checks the if the item choice is less or more than the menu
                System.out.println("Invalid choice! Please select a valid menu item.");
                return;
            }
            order.addItem(MenuManager.menu.get(itemChoice - 1)); // Adds the item to the order list
            utils.clearConsole();
            System.out.println(MenuManager.menu.get(itemChoice - 1).getName() + " has been added to your order!"); //prints the item that is added
    
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a valid number."); // catch invalid number input
        }
    }

    public static void orderConfirmation(){
        boolean confirmation = true;
        while (confirmation) { 
            System.out.println("\nConfirm order? "); //order confirmation
            System.out.println("1. yes");
            System.out.println("2. add more");
            System.out.println("3. cancel order");
            String confirm = scan.next().trim().toLowerCase();
            switch (confirm) {
                case "1" -> {
                    utils.clearConsole(); //confirmation of order that prints receipt
                    order.printReceipt(); //also checks if the total order is zero 
                    System.out.print("\nPress Enter to continue: ");//clears everything after getting the receipt
                    scan.nextLine();
                    scan.nextLine();
                    utils.clearConsole(); 
                    order.clearOrder(); // clears order after 
                    confirmation = false;
                    ordering = false;
                }
                case "2" -> 
                    confirmation = false; //adds new order by breaking the confirmation loop and going to ordering loop
                case "3" -> {
                    //clears everything without receipt
                    System.out.println("Order cancelled.");
                    utils.sleep(3);
                    utils.clearConsole(); 
                    order.clearOrder(); // clears the order and not printing the receipt
                    confirmation = false;
                    ordering = false;
                }
                default -> System.out.println("Invalid input, try again.");
            }
        }
    }

    

    public static void initializeMenu() {
        utils.clearConsole();
        MenuManager.loadMenu(); // loads the menu from the text file
        System.out.println("Menu file found."); 
        utils.sleep(3);
    }
    

    public static void displayMenu() {
        System.out.println("\n----------Menu----------:");
        System.out.println("Meals: ");
        for (int i = 0; i < MenuManager.meals.size(); i++) {
            System.out.println((i + 1) + ". " + MenuManager.meals.get(i)); //displays the meals in menu
        }
        System.out.println("Drinks: ");
        for (int i = 0; i < MenuManager.drinks.size(); i++) {
            System.out.println((i + 1 + MenuManager.meals.size()) + ". " + MenuManager.drinks.get(i)); //displays the drinks in menu
        }
        System.out.println("Combos: ");
        for (int i = 0; i < MenuManager.combos.size(); i++) {
            System.out.println((i + 1 + MenuManager.meals.size() + MenuManager.drinks.size()) + ". " + MenuManager.combos.get(i)); //displays combos in menu
        }

        System.out.println("----------------------------");
    }    

    public static void displayMeals() {
        System.out.println("Meals: ");
        for (int i = 0; i < MenuManager.meals.size(); i++) {
            System.out.println((i + 1) + ". " + MenuManager.meals.get(i));
        }
    }
    
    public static void displayDrinks() {
        System.out.println("Drinks: ");
        for (int i = 0; i < MenuManager.drinks.size(); i++) {
            System.out.println((i + 1 + MenuManager.meals.size()) + ". " + MenuManager.drinks.get(i));
        }
    }
    
    public static void displayCombos() {
        System.out.println("Combos: ");
        for (int i = 0; i < MenuManager.combos.size(); i++) {
            System.out.println((i + 1 + MenuManager.meals.size() + MenuManager.drinks.size()) + ". " + MenuManager.combos.get(i));
        }
    }
}
