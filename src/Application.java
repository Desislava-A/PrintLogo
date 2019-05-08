import java.util.Scanner;

public class Application {

    private boolean exit = false;

    public void runMenu() {

        printWelcomeNote();
        while (!exit) {
            showMainMenu();
            int mainInput = getMainInput();
            doAction(mainInput);
        }
    }

    private void printWelcomeNote() {
        System.out.println("+-----------------------------------------------+");
        System.out.println("+          Welcome to my application            +");
        System.out.println("+     that prints MENTORMATE logo on screen     +");
        System.out.println("+-----------------------------------------------+");
    }

    private void showMainMenu() {
        System.out.println("\n   ++   To PRINT the logo, please enter:        1");
        System.out.println("   ++   To EXIT the application, please enter:  0");
    }

    private int getMainInput() {
        Scanner scanner = new Scanner(System.in);
        int selection = -1;

        while (selection < 0 || selection > 1) {
            try {
                System.out.print("\nPlease enter your input: ");
                selection = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter 1 or 0.");
            }
        }

        return selection;
    }

    private void doAction(int selected) {
        switch (selected) {
            case 0:
                exit = true;
                System.out.println("You successfully exited the application.");
                break;
            case 1:
                showPrintLogoDialogue();
                break;
            default:
                System.out.println("Unknown error");
                // default case never gonna be reached due to validation in getMainInput method.
        }

    }

}
