import java.io.*;

public class PasswordManager {
    private static final String PASSWORD_FILE = "admin_password.txt";
    static Utils utils = new Utils();
    
    public static String loadAdminPassword() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PASSWORD_FILE))) {
            return reader.readLine();
        } catch (FileNotFoundException e) {
            utils.clearConsole();
        System.out.println("Password file not found. Setting default password.");
        saveAdminPassword("pass123"); // Default password
        utils.sleep(3);
        return "pass123";
        } catch (IOException e) {
            utils.clearConsole();
            System.out.println("Error reading password file. Setting default password.");
            utils.sleep(3);
            return "pass123";
        }
    }

    public static void saveAdminPassword(String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PASSWORD_FILE))) {
            writer.write(password);
        } catch (IOException e) {
            System.out.println("Error saving admin password: " + e.getMessage());
        }
    }
}
