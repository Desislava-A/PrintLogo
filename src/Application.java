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
            boolean changed = false;
            try {
                System.out.print("\nPlease enter your input: ");
                selection = Integer.parseInt(scanner.nextLine());
                changed = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter 1 or 0.");
            }
            if (changed && (selection < 0 || selection > 1)) {
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

    private void showPrintLogoDialogue() {
        System.out.println("\nPlease enter ODD number for desired thickness of the logo.");
        System.out.println("Thickness should be ODD number from 3 up to 9999 inclusively");
        int currentThickess = getThickNess();
        printLogo(currentThickess);
    }

    private int getThickNess() {
        Scanner scanner = new Scanner(System.in);
        int n = 0;


        while (n < 3 || n > 9999 || n % 2 == 0) {
            boolean changed = false;

            try {
                System.out.print("\nPlease enter desired thickness: ");
                n = Integer.parseInt(scanner.nextLine().trim());
                changed = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter ODD number from 3 up to 9999.");
            }

            if (changed && (n < 3 || n > 9999 || n % 2 == 0)) {
                System.out.println("Invalid input. Please enter ODD number from 3 up to 9999.");
            }

        }

        return n;
    }


    private void printLogo(int n) {
        //will store rows of logo as String elements in String array
        String[] rowsToPrint = new String[n + 1];
        int rowNumber = 0;

        //Creating strings for the rows in the upper part of first M letter from the logo
        for (rowNumber = 0; 2 * rowNumber <= n - 1; rowNumber++) {
            int starsCount = n + 2 * rowNumber;
            int dashesStart = n - rowNumber;
            int dashesMiddle = n - 2 * rowNumber;
            StringBuilder builder = new StringBuilder();

            for (int star = 1; star <= starsCount; star++) {
                builder.append("*");
            }

            String starsBlock = builder.toString();
            builder.setLength(0);

            for (int dash = 1; dash <= dashesStart; dash++) {
                builder.append("-");
            }

            String dashesAtStart = builder.toString();
            builder.setLength(dashesMiddle);

            String middleDashes = builder.toString();
            builder.setLength(0);
            builder.append(dashesAtStart);
            builder.append(starsBlock);
            builder.append(middleDashes);
            builder.append(starsBlock);
            builder.append(dashesAtStart);

            String halfRow = builder.toString();
            builder.append(halfRow);
            rowsToPrint[rowNumber] = builder.toString();
            builder.setLength(0);

        }

        /* Creating strings for the rows from down half part of the logo;
         * String nStars is used to store sequence of N star symbols.
         * Within the loop for each row are calculated, sequences of free area symbols and
         * symbols for the biggest sequence of stars in the center of the M letter.
         **/

        StringBuilder builder = new StringBuilder();
        for (int star = 1; star <= n; star++) {
            builder.append("*");
        }
        String nStars = builder.toString();
        builder.setLength(0);


        int middleDashes = 1;

        for (int row = rowNumber; row <= n; row++) {


            for (int star = 1; star <= 2 * n - middleDashes; star++) {
                builder.append("*");
            }

            String middleStars = builder.toString();

            builder.setLength(0);

            for (int dash = 1; dash <= n - row; dash++) {
                builder.append("-");
            }

            String dashesAtStartAndEnd = builder.toString();
            builder.setLength(0);

            for (int dash = 1; dash <= middleDashes; dash++) {
                builder.append("-");
            }

            String interimDashes = builder.toString();
            builder.setLength(0);

            builder.append(dashesAtStartAndEnd);
            builder.append(nStars);
            builder.append(interimDashes);
            builder.append(middleStars);
            builder.append(interimDashes);
            builder.append(nStars);
            builder.append(dashesAtStartAndEnd);

            String rowHalf = builder.toString();
            builder.append(rowHalf);


            rowsToPrint[row] = builder.toString();
            builder.setLength(0);

            middleDashes += 2;


        }

        //printing the logo row by row
        System.out.println();
        for (String rowStr : rowsToPrint
                ) {
            System.out.println(rowStr);
        }
    }
}
