package JAVA;

import java.io.IOException;
import java.util.ArrayList;

public class GameScreen {
    private static final String ESC = "\033[";

    public static void clearScreen() {
        System.out.print(ESC + "2J");
        System.out.print(ESC + "H");
        drawBox(0, 0, Main.ScreenMaxWidth, Main.ScreenMaxHeight, "CARD GAME BY JOHN KLAINE M. MERCADO");
    }

    public static void moveCursor(int row, int col) {
        // System.out.println("DEBUG: Moving cursor to row " + row + ", column " + col);
        System.out.print(String.format("\033[%d;%dH", row + 1, col + 1));
    }


    public static void printAt(int row, int col, String text, int length, String textColor, String backgroundColor) {
        moveCursor(row, col); // Move to the desired position
    
        // Clear the specified length with the background color
        System.out.print(backgroundColor + " ".repeat(length) + ConsoleColors.RESET);
    
        moveCursor(row, col); // Move back to the starting position
        // Print the text with the specified foreground and background colors
        System.out.print(textColor + backgroundColor + text + ConsoleColors.RESET);
    
        System.out.flush(); // Ensure the output is displayed immediately
    }


    public static void printAt(int row, int col, String text, int length) {
        printAt(row, col, text, text.length(), ConsoleColors.RESET, ConsoleColors.RESET); 
        
        moveCursor(row, col); // Move to the starting position

        // Clear the specified length by overwriting with spaces
        System.out.print(" ".repeat(length));

        moveCursor(row, col); // Move back to the starting position
        System.out.print(text); // Print the new text
        System.out.flush(); // Ensure the output is displayed immediately
    }

    public static void printAt(int row, int col, String text) {
        printAt(row, col, text, text.length()); // Call the original method with the text length
    }

    public static void drawLine(int startRow, int startCol, int len, String textColor, String backColor) {
        printAt(startRow, startCol, "├" + "─".repeat(len - 2) + "┤", len, textColor, backColor);
    }

    public static void drawLine(int startRow, int startCol, int len) {
        drawLine(startRow, startCol, len, ConsoleColors.RESET, ConsoleColors.RESET);
    }

    public static void drawDoubleLine(int startRow, int startCol, int len, String textColor, String backColor) {
        printAt(startRow, startCol, "╠" + "═".repeat(len - 2) + "╣", len, textColor, backColor);
    }

    public static void drawDoubleLine(int startRow, int startCol, int len) {
        drawDoubleLine(startRow, startCol, len, ConsoleColors.RESET, ConsoleColors.RESET);
    }

    public static void drawLine(int startRow) {
        drawLine(startRow, 0, Main.ScreenMaxWidth);
    }

    public static void drawBox(int startRow, int startCol, int width, int height, String title, String textColor, String backColor) {
        // Truncate the title if it exceeds the box width
        String displayTitle = title.length() > width - 4 ? title.substring(0, width - 4) : title;
        displayTitle = " " + displayTitle + " ";
        // Draw the top edge with the title
        String topEdge;
        if (title.length() > 0) {
            topEdge = "┌" + "─".repeat((width - 2 - displayTitle.length()) / 2) + displayTitle +
                    "─".repeat((width - 2 - displayTitle.length() + 1) / 2) + "┐";
        } else {
            topEdge = "┌" + "─".repeat(width - 2) + "┐";
        }
        ;

        printAt(startRow, startCol, topEdge,topEdge.length(),textColor, backColor);

        // Draw the line below the title
        // printAt(startRow + 1, startCol, "├" + "─".repeat(width - 2) + "┤");

        // Draw the sides
        for (int i = 1; i < height - 1; i++) {
            printAt(startRow + i, startCol, "│" + " ".repeat(width - 2) + "│",width,textColor, backColor);
        }

        // Draw the bottom edge
        printAt(startRow + height - 1, startCol, "└" + "─".repeat(width - 2) + "┘",width,textColor, backColor);
    }

    public static void drawBox(int startRow, int startCol, int width, int height, String title) {  
        drawBox(startRow, startCol, width, height, title, ConsoleColors.RESET, ConsoleColors.RESET);
    }

    public static void drawDoubleLineBox(int startRow, int startCol, int width, int height, String title, String textColor, String backColor) {
        // Truncate the title if it exceeds the box width
        String displayTitle = title.length() > width - 4 ? title.substring(0, width - 4) : title;
        displayTitle = " " + displayTitle + " ";
        // Draw the top edge with the title
        String topEdge;
        if (title.length() > 0) {
            topEdge = "╔" + "═".repeat((width - 2 - displayTitle.length()) / 2) + displayTitle +
                    "═".repeat((width - 2 - displayTitle.length() + 1) / 2) + "╗";
        } else {
            topEdge = "╔" + "═".repeat(width - 2) + "╗";
        }
        ;

        printAt(startRow, startCol, topEdge,topEdge.length(),textColor, backColor);

        // Draw the line below the title
        // printAt(startRow + 1, startCol, "├" + "═".repeat(width - 2) + "┤");

        // Draw the sides
        for (int i = 1; i < height - 1; i++) {
            printAt(startRow + i, startCol, "║" + " ".repeat(width - 2) + "║",width,textColor, backColor);
        }

        // Draw the bottom edge
        printAt(startRow + height - 1, startCol, "╚" + "═".repeat(width - 2) + "╝",width,textColor, backColor);
    }

    public static void drawDoubleLineBox(int startRow, int startCol, int width, int height, String title) {
        drawDoubleLineBox(startRow, startCol, width, height, title, ConsoleColors.RESET, ConsoleColors.RESET);
    }

    private static void renderMenu(ArrayList<String> options, int selectedIndex, String message, int screenWidth, int screenHeight) {
        GameScreen.clearScreen();
    
        // Calculate the center position for the message
        int messageRow = screenHeight / 2 - options.size() / 2 - 2; // Place the message above the menu
        int messageCol = (screenWidth - message.length()) / 2;
    
        // Display the message
        GameScreen.printAt(messageRow, messageCol, message, message.length(), ConsoleColors.CYAN, ConsoleColors.RESET);
    
        // Display the menu options
        for (int i = 0; i < options.size(); i++) {
            String optionText = (i == selectedIndex ? "> " + options.get(i) : "  " + options.get(i));
            int optionCol = (screenWidth - optionText.length()) / 2; // Center the option
            GameScreen.printAt(messageRow + i + 2, optionCol, optionText, optionText.length(),
                    i == selectedIndex ? ConsoleColors.WHITE : ConsoleColors.RESET, 
                    i == selectedIndex ? ConsoleColors.BLUE_BACKGROUND : ConsoleColors.RESET);
        }
    }    

    private static String readRawInput() throws IOException {
        StringBuilder input = new StringBuilder();

        // Read the first character
        char firstChar = (char) System.in.read();
        input.append(firstChar);

        // If it's an ESC sequence, read the next two characters
        if (firstChar == '\033') { // ESC
            input.append((char) System.in.read());
            input.append((char) System.in.read());
        }

        // Debug log to see raw input
        // System.out.println("Raw Input Captured: " + input + " (ASCII: " + (int)
        // firstChar + ")");

        return input.toString();
    }

    public static int dynamicMenu(ArrayList<String> options, String message) throws IOException {
        GameScreen.clearScreen();
        int selectedIndex = 0;
    
        while (true) {
            renderMenu(options, selectedIndex, message, Main.ScreenMaxWidth, Main.ScreenMaxHeight); // Pass the message to renderMenu
    
            // Capture raw input
            String input = readRawInput();
    
            // Handle Ctrl + X for termination
            if (input.equals("\u0018")) { // ASCII for Ctrl + X
                GameScreen.clearScreen();
                return -1; // Return -1 to indicate the user exited
            }
    
            // Handle input cases
            switch (input) {
                case "\033[A": // Arrow Up
                    selectedIndex = (selectedIndex - 1 + options.size()) % options.size();
                    break;
                case "\033[B": // Arrow Down
                    selectedIndex = (selectedIndex + 1) % options.size();
                    break;
                case "\033[C": // Arrow Right
                    selectedIndex = (selectedIndex + 1) % options.size();
                    break;
                case "\033[D": // Arrow Left
                    selectedIndex = (selectedIndex - 1 + options.size()) % options.size();
                    break;
                case "\n": // Enter key (Unix-style)
                case "\r": // Enter key (Windows-style)
                    GameScreen.clearScreen();
                    return selectedIndex; // Return the index of the selected option
                default:
                    GameScreen.printAt(0, 0, "Invalid input: " + input, 15);
            }
        }
    }

    public static void MessageBoxOK(String message) throws IOException {
        ArrayList<String> options = new ArrayList<>();
        options.add("OK");
    
        int selectedIndex = GameScreen.dynamicMenu(options, message); // Pass the message to dynamicMenu
        // System.out.println("Selected Index: " + selectedIndex);
    }
    
    public static void PrintMultiple(int r, int c, String message, int width) {
        String[] justifiedLines = TextUtils.justifyText(message, width);
        for (String line : justifiedLines) {
            GameScreen.printAt(++r, c, line); 
        }
    }

    public static void drawBook(int r, int c, int num, Subject subject) { 
        GameScreen.printAt(r++, c+1, "Items: " + subject.getItems() + "/" + subject.getMaxItems());       
        GameScreen.printAt(r++, c, "┌╦┬──────────┐");
        GameScreen.printAt(r++, c, "║║│          │");
        GameScreen.printAt(r++, c, "║║│          │");
        GameScreen.printAt(r++, c, "║║│          │");
        GameScreen.printAt(r++, c, "║║│          │");
        GameScreen.printAt(r++, c, "║║│          │");
        GameScreen.printAt(r++, c, "║║│          │");
        GameScreen.printAt(r++, c, "║║│          │");
        GameScreen.printAt(r++, c, "║║└──────────┤ ");
        GameScreen.printAt(r++, c, "╚╩═══════════╝");
        drawNumber(r-8,c+5,num);
        //progressBar(r+1,c-2,"Items", subject.getItems(), subject.getMaxItems());
    }

    public static void drawNumber(int r, int c, int num) {        
        if (num ==0) {
        GameScreen.printAt(r++, c, " 0000");
        GameScreen.printAt(r++, c, "00  00");
        GameScreen.printAt(r++, c, "00  00");
        GameScreen.printAt(r++, c, "00  00");
        GameScreen.printAt(r++, c, " 0000");
    } else if (num==1) {
        GameScreen.printAt(r++, c, "1111");
        GameScreen.printAt(r++, c, "  11");
        GameScreen.printAt(r++, c, "  11");
        GameScreen.printAt(r++, c, "  11");
        GameScreen.printAt(r++, c, "111111");
    } else if (num==2) {
        GameScreen.printAt(r++, c, "222222");
        GameScreen.printAt(r++, c, "     2");
        GameScreen.printAt(r++, c, "222222");
        GameScreen.printAt(r++, c, "2     ");
        GameScreen.printAt(r++, c, "222222");
    } else if (num==3) {
        GameScreen.printAt(r++, c, "333333");
        GameScreen.printAt(r++, c, "    33");
        GameScreen.printAt(r++, c, "333333");
        GameScreen.printAt(r++, c, "    33");
        GameScreen.printAt(r++, c, "333333");
    } else if (num==4) {
        GameScreen.printAt(r++, c, "44  44");
        GameScreen.printAt(r++, c, "44  44");
        GameScreen.printAt(r++, c, "444444");
        GameScreen.printAt(r++, c, "    44");
        GameScreen.printAt(r++, c, "    44");
    } else if (num==5) {
        GameScreen.printAt(r++, c, "555555");
        GameScreen.printAt(r++, c, "55");
        GameScreen.printAt(r++, c, "555555");
        GameScreen.printAt(r++, c, "    55");
        GameScreen.printAt(r++, c, "555555");
    } else if (num==6) {
        GameScreen.printAt(r++, c, "666666");
        GameScreen.printAt(r++, c, "66");
        GameScreen.printAt(r++, c, "666666");
        GameScreen.printAt(r++, c, "66  66");
        GameScreen.printAt(r++, c, "666666");
    } else if (num==7) {
        GameScreen.printAt(r++, c, "777777");
        GameScreen.printAt(r++, c, "   77 ");
        GameScreen.printAt(r++, c, "  77  ");
        GameScreen.printAt(r++, c, " 77   ");
        GameScreen.printAt(r++, c, "77   ");

    } else if (num==8) {
        GameScreen.printAt(r++, c, " 8888");
        GameScreen.printAt(r++, c, "88  88");
        GameScreen.printAt(r++, c, "888888");
        GameScreen.printAt(r++, c, "88  88");
        GameScreen.printAt(r++, c, " 8888");
    } else if (num==9) {
        GameScreen.printAt(r++, c, " 9999 ");
        GameScreen.printAt(r++, c, "99  99");
        GameScreen.printAt(r++, c, "  9999 ");
        GameScreen.printAt(r++, c, "    99");
        GameScreen.printAt(r++, c, " 9999");
    }
    }

    public static void drawDay(int r, int c, int day) {   
        GameScreen.printAt(r++, c, "     ┌──┐");
        GameScreen.printAt(r++, c, "     │  │");
        GameScreen.printAt(r++, c, "     └──┤ ");
        GameScreen.printAt(r++, c, "        │");
        GameScreen.printAt(r++, c, "        │");
        GameScreen.printAt(r++, c, "╔═══════╣");
        GameScreen.printAt(r++, c, "║       ║");
        GameScreen.printAt(r++, c, "╚═══════╝");
        GameScreen.printAt(r-2, c+2, "Day " + String.valueOf(day));
    }
   
    public static void drawAvatar(int r, int c) {   
        GameScreen.drawDoubleLineBox(r++, c, 40, 20, "");
        GameScreen.drawDoubleLine(r+8, c,40);
        c = c + 20-4;
        GameScreen.printAt(r++, c, "________");
        GameScreen.printAt(r++, c, "|      |");
        GameScreen.printAt(r++, c, "|______|");
        GameScreen.printAt(r++, c, "___||___ ");
        GameScreen.printAt(r++, c, "|||  ||| ");
        GameScreen.printAt(r++, c, "|||__|||");
        GameScreen.printAt(r++, c, "  ||||  ");
        GameScreen.printAt(r++, c, "  ||||  ");
    }

    public static void progressBar(int r, int c, String item, int Progress, int Max) {  
        int p = Progress;
        if (p < 0) {
            p = 0;
        }
        if (p > Max) {
            p = Max;
        }
        GameScreen.printAt(r+1, c+40, ">>>>>"+ String.valueOf(p) + "/" + String.valueOf(Max));
        GameScreen.drawBox(r, c, 15+2+item.length()+4,3, ""); 
        GameScreen.printAt(r+1, c+2, item + ": " + "█".repeat(p) + " ".repeat(15-p-4) + String.valueOf(Progress) + "/" + String.valueOf(Max));
    }
}


