package JAVA;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class InteractiveMenu {

    public static void interactiveMenu(ArrayList<String> options) throws IOException {
        int selectedIndex = 0;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            renderMenu(options, selectedIndex);

            // Capture raw input
            String input = readRawInput();

            switch (input) {
                case "\033[A": // Arrow Up
                    selectedIndex = (selectedIndex - 1 + options.size()) % options.size();
                    break;
                case "\033[B": // Arrow Down
                    selectedIndex = (selectedIndex + 1) % options.size();
                    break;
                case "\n": // Enter key
                    System.out.println("You selected: " + options.get(selectedIndex));
                    return;
                case "\u0018": // Ctrl + X to exit
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid input: " + input);
            }
        }
    }

    private static void renderMenu(ArrayList<String> options, int selectedIndex) {
        System.out.print("\033[H\033[2J"); // Clear the screen
        System.out.flush();

        for (int i = 0; i < options.size(); i++) {
            if (i == selectedIndex) {
                System.out.println(ConsoleColors.BLUE_BACKGROUND + ConsoleColors.WHITE + "> " + options.get(i) + " " + ConsoleColors.RESET);
            } else {
                System.out.println("  " + options.get(i));
            }
        }
    }

    private static String readRawInput() throws IOException {
        // Enable raw mode
        String[] cmd = { "/bin/sh", "-c", "stty raw < /dev/tty" };
        Runtime.getRuntime().exec(cmd);

        // Read a single character
        int input = System.in.read();

        // Disable raw mode
        cmd = new String[]{ "/bin/sh", "-c", "stty sane < /dev/tty" };
        Runtime.getRuntime().exec(cmd);

        // Return the input as a string
        return Character.toString((char) input);
    }

    public static void main(String[] args) throws IOException {
        ArrayList<String> options = new ArrayList<>();
        options.add("Option 1");
        options.add("Option 2");
        options.add("Option 3");
        options.add("Exit");

        interactiveMenu(options);
    }
}
