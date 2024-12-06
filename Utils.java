
import java.io.IOException;

public class Utils {
    public void clearConsole() {
        try {
            // For Windows systems
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // For Unix-based systems
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            // Fallback: Print new lines
            for (int i = 0; i < 100; i++) {
                System.out.println();
            }
        }
    }

    public void sleep(int seconds) {
        try {
            for (int i = 0; i < seconds; i++) {
                System.out.print(". ");
                Thread.sleep(1000);
        } // Convert seconds to milliseconds
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted: " + e.getMessage());
            Thread.currentThread().interrupt(); // Preserve the interrupt status
        }
    }
}
