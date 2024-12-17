package JAVA;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {

    // ANSI escape codes for styling
    private static final String RESET = "\033[0m";
    private static final String HIGHLIGHT = "\033[7m"; // Inverted colors
    private static final String CLEAR_SCREEN = "\033[2J\033[H";
    int selectedcard = 0;
    int selectedcardHand = 0;
    public ArrayList<Integer> deck = new ArrayList<>();

    private static final String[] MENU_ITEMS = {
        "New Game    ",
        "Load Game   ",
        "Game History",
        "Exit        "
    };

    private static final String[] MENU_ITEMS_CARD_SELECT = {
        " << ",
        " + ",
        " - ",
        " >> ",
        " Main Menu ",
        " Start Game "
    };

    private static final String[] MENU_ITEMS_PLAY = {
        " << ",
        " PLAY CARD ",
        " END TURN ",
        " >> ",
        " EXIT GAME "
    };

    private static final String[] MENU_ITEMS_CARD_SELECT_DESC = {
        " Show Previous Card  ",
        " Add Card            ",
        " Remove from Deck    ",
        " Show Next Card      ",
        " Goto Main Menu      ",
        " Start your game     ",};

    public void show() throws IOException {
        enableRawMode(); // Enable raw mode for input
        int selectedIndex = 0;

        int r = 25;
        GameScreen.printAt(r++,20,"        _________ __            .___             __      _________.__           ");    
        GameScreen.printAt(r++,20,"       /   _____//  |_ __ __  __| _/____   _____/  |_   /   _____/|__| _____        ");
        GameScreen.printAt(r++,20,"       \\_____  \\\\   __\\  |  \\/ __ |/ __ \\ /    \\   __\\  \\_____  \\ |  |/     \\       ");
        GameScreen.printAt(r++,20,"       /        \\|  | |  |  / /_/ \\  ___/|   |  \\  |    /        \\|  |  Y Y  \\      ");
        GameScreen.printAt(r++,20,"      /_______  /|__| |____/\\____ |\\___  >___|  /__|   /_______  /|__|__|_|  /      ");
        GameScreen.printAt(r++,20,"             \\/                 \\/    \\/     \\/               \\/          \\/       ");
        GameScreen.printAt(r++,20,"_________     _____ __________________      ________    _____      _____  ___________");
        GameScreen.printAt(r++,20,"\\_   ___ \\   /  _  \\\\______   \\______ \\    /  _____/   /  _  \\    /     \\ \\_   _____/");
        GameScreen.printAt(r++,20,"/    \\  \\/  /  /_\\  \\|       _/|    |  \\  /   \\  ___  /  /_\\  \\  /  \\ /  \\ |    __)_ ");
        GameScreen.printAt(r++,20,"\\     \\____/    |    \\    |   \\|    `   \\ \\    \\_\\  \\/    |    \\/    Y    \\|        \\");
        GameScreen.printAt(r++,20,"\\______  /\\____|__  /____|_  /_______  /  \\______  /\\____|__  /\\____|__  /_______  /");
        GameScreen.printAt(r++,20,"       \\/         \\/       \\/        \\/          \\/         \\/         \\/        \\/ ");

        // GameScreen.printAt(r++,20,"_________     _____ __________________      ________    _____      _____  ___________");
        // GameScreen.printAt(r++,20,"\\_   ___ \\   /  _  \\\\______   \\______ \\    /  _____/   /  _  \\    /     \\ \\_   _____/");
        // GameScreen.printAt(r++,20,"/    \\  \\/  /  /_\\  \\|       _/|    |  \\  /   \\  ___  /  /_\\  \\  /  \\ /  \\ |    __)_ ");
        // GameScreen.printAt(r++,20,"\\     \\____/    |    \\    |   \\|    `   \\ \\    \\_\\  \\/    |    \\/    Y    \\|        \\");
        // GameScreen.printAt(r++,20," \\______  /\\____|__  /____|_  /_______  /  \\______  /\\____|__  /\\____|__  /_______  /");
        // GameScreen.printAt(r++,20,"       \\/         \\/       \\/        \\/          \\/         \\/         \\/        \\/ ");
        // GameScreen.printAt(r++,20,"  _____                _________ __            .___             __                   ");
        // GameScreen.printAt(r++,20,"_/ ____\\___________   /   _____//  |_ __ __  __| _/____   _____/  |_                 ");
        // GameScreen.printAt(r++,20,"\\   __\\/  _ \\_  __ \\  \\_____  \\\\   __\\  |  \\/ __ |/ __ \\ /    \\   __\\                ");
        // GameScreen.printAt(r++,20,"|  | (  <_> )  | \\/  /        \\|  | |  |  / /_/ \\  ___/|   |  \\  |                  ");
        // GameScreen.printAt(r++,20,"|__|  \\____/|__|    /_______  /|__| |____/\\____ |\\___  >___|  /__|                  ");
        // GameScreen.printAt(r++,20,"                            \\/                 \\/    \\/     \\/                      ");

        try {
            while (true) {
                // GameScreen.clearScreen();
                renderMenu(selectedIndex);

                // Capture raw input
                String input = readRawInput();

                // Handle Ctrl + X for termination
                if (input.equals("\u0018")) { // ASCII for Ctrl + X
                    GameScreen.printAt(0, 0, "Exiting with Ctrl + X...", 30);
                    break;
                }

                // Handle input cases
                switch (input) {
                    case "\033[A": // Arrow Up
                        selectedIndex = (selectedIndex - 1 + MENU_ITEMS.length) % MENU_ITEMS.length;
                        break;
                    case "\033[B": // Arrow Down
                        selectedIndex = (selectedIndex + 1) % MENU_ITEMS.length;
                        break;
                    case "\n": // Enter key (Unix-style)
                    case "\r": // Enter key (Windows-style)
                        handleSelection(selectedIndex); // Perform action for selected item
                        if (selectedIndex == 3) {
                            return; // Exit menu loop
                        }
                        break;
                    default:
                        GameScreen.printAt(0, 0, "Invalid input: " + input, 15);
                }
            }
        } finally {
            disableRawMode(); // Ensure raw mode is disabled on exit
        }
    }

    private static final int MENU_WIDTH = 20;

    private void renderMenu(int selectedIndex) {
        int row = 10;
        int col = 10;
        GameScreen.drawBox(row, col, MENU_WIDTH + 11, MENU_ITEMS.length + 2, "MAIN MENU");
        col += 2;
        row += 1;
        for (int i = 0; i < MENU_ITEMS.length; i++) {
            if (i == selectedIndex) {
                GameScreen.printAt(i + row, col, HIGHLIGHT + ToolKit.padRight(MENU_ITEMS[i], MENU_WIDTH) + RESET);
            } else {
                GameScreen.printAt(i + row, col, ToolKit.padRight(MENU_ITEMS[i], MENU_WIDTH));
            }
        }
    }

    private void renderMenuAddCard(int selectedIndex) {
        // GameScreen.printAt(0, 0, "start renderMenuAddCard", 30);
        int iPrev = selectedcard - 1;
        int iNext = selectedcard + 1;
        int card;
        if (selectedcard == 0) {
            iPrev = Main.cards.size() - 1;
        }
        if (selectedcard == Main.cards.size() - 1) {
            iNext = 0;
        }
        Main.cards.get(iPrev).drawCard(3, 20, 12);
        Main.cards.get(selectedcard).drawCard(1, 57, 14, 2);
        Main.cards.get(iNext).drawCard(3, 94, 12);

        GameScreen.printAt(8, 46, "<-");
        GameScreen.printAt(8, 86, "->");

        GameScreen.printAt(1, 1, String.valueOf(selectedcard));
        int nextmargin = 3;
        int downmargin = 23;
        for (int i = 0; i < Main.avatar.drawpile.size(); i++) {
            Main.cards.get(Main.avatar.drawpile.get(i)).drawCard(downmargin, nextmargin, 12);
            nextmargin += 27;
            if (i == 4) {
                nextmargin = 3;
                downmargin += 15;
            }
        }
        for (int i = Main.avatar.drawpile.size(); i < 10; i++) {
            for (int l = 0; l < 12; l++) {
                GameScreen.printAt(downmargin + l, nextmargin, " ".repeat(20));
            }
            nextmargin += 27;
            if (i == 4) {
                nextmargin = 3;
                downmargin += 15;
            }
        }

        int l = 15;
        int c = 58;
        // GameScreen.printAt(l+3, 1, "_".repeat(ScreenMaxWidth-2));

        for (int i = 0; i < MENU_ITEMS_CARD_SELECT.length - 2; i++) {
            if (i == selectedIndex) {
                GameScreen.printAt(l, c, HIGHLIGHT + MENU_ITEMS_CARD_SELECT[i] + RESET, 30);
            } else {
                GameScreen.printAt(l, c, MENU_ITEMS_CARD_SELECT[i], 30);
            }
            c = c + MENU_ITEMS_CARD_SELECT[i].length() + 1;
        }

        c = 55;
        GameScreen.printAt(l + 2, c, MENU_ITEMS_CARD_SELECT_DESC[selectedIndex], 30, ConsoleColors.WHITE, ConsoleColors.BLACK_BACKGROUND);
        l++;
        for (int i = MENU_ITEMS_CARD_SELECT.length - 2; i < MENU_ITEMS_CARD_SELECT.length; i++) {
            if (i == selectedIndex) {
                GameScreen.printAt(l, c, HIGHLIGHT + MENU_ITEMS_CARD_SELECT[i] + RESET, 30);
            } else {
                GameScreen.printAt(l, c, MENU_ITEMS_CARD_SELECT[i], 30);
            }
            c = c + MENU_ITEMS_CARD_SELECT[i].length() + 1;
        }

        GameScreen.drawLine(l + 4);
    }

    private void handleSelection(int selectedIndex) throws IOException {
        GameScreen.printAt(1, 20, "\nYou selected: " + MENU_ITEMS[selectedIndex], 30);

        switch (selectedIndex) {
            case 0: // "New Game ":
                GameScreen.printAt(0, 0, "Starting a new game...", 30);
                newgameMenu();
                break;
            case 1: // "Load Game ":
                // GameScreen.printAt(0, 0, "Loading a game...", 20);
                break;
            case 2: // "Game History":
                // GameScreen.printAt(0, 0, "Displaying game history...", 30);
                break;
            case 3: // "Exit ":
                GameScreen.printAt(0, 0, "Exiting...", 30);
                return;
        }
    }

    private void handleSelectionAddCard(int selectedIndex) throws IOException {
        int maxqty = Main.cards.get(selectedcard).Number;
        int cardqty = Main.cards.get(selectedcard).qty;

        switch (selectedIndex) {
            case 0: // "Previous":
                GameScreen.printAt(70, 0, "ljkasj dlfkjsdlakfj lskdj fsd");
                // newgameMenu();
                selectedcard--;
                if (selectedcard < 0) {
                    selectedcard = Main.cards.size() - 1;
                }
                // newgameMenu();
                break;
            case 1: // "Add Card":
                // GameScreen.printAt(0, 0, MENU_ITEMS_CARD_SELECT[selectedIndex], 20);
                if (Main.avatar.add2DrawPile(Main.cards.get(selectedcard))) {
                    Main.cards.get(selectedcard).addQty(1);
                    //GameScreen.printAt(18, 90, "Max quantity of copies has been added!");
                } else {
                    GameScreen.MessageBoxOK("Max quantity of copies has been added! press (enter) to confirm");
                }
                break;
            case 2: // "Remove card":
                // GameScreen.printAt(0, 0, MENU_ITEMS_CARD_SELECT[selectedIndex], 30);
                if (Main.avatar.removeFromPile(Main.cards.get(selectedcard))) {
                    Main.cards.get(selectedcard).addQty(-1);
                    //GameScreen.printAt(18, 90, "Max quantity of copies has been added!");
                } else {
                    GameScreen.printAt(18, 90, "No selected copies to remove! press (enter) to confirm");
                }
                break;
            case 3: // "Next Card":
                // GameScreen.printAt(0, 0,MENU_ITEMS_CARD_SELECT[selectedIndex], 30);
                selectedcard++;
                if (selectedcard >= Main.cards.size()) {
                    selectedcard = 0;
                }
                break;
            case 5: // Start Game
                Main.avatar.DrawFirst5();
                play();
                break;
            // case 5: // "Main Menu":
            // // GameScreen.printAt(40, 0, MENU_ITEMS_CARD_SELECT[selectedIndex], 30);
            // GameScreen.clearScreen();
            // show();
            // break;
            // if(Main.avatar.drawpile.size() < 8){}
            // Display Selected Avatar
            }
        for (int x = 0; x < Main.avatar.drawpile.size(); x++) {
            GameScreen.printAt(x, 140, String.valueOf(Main.avatar.drawpile.get(x)));

        }
        GameScreen.printAt(1, 0, "\nYou selected: " + MENU_ITEMS_CARD_SELECT[selectedIndex] + "->" + String.valueOf(selectedcard), 30);

    }

    private String readRawInput() throws IOException {
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

    private void enableRawMode() throws IOException {
        try {
            Runtime.getRuntime().exec(new String[]{"sh", "-c", "stty raw < /dev/tty"}).waitFor();
        } catch (InterruptedException e) {
            throw new IOException("Failed to enable raw mode", e);
        }
    }

    private void disableRawMode() throws IOException {
        try {
            Runtime.getRuntime().exec(new String[]{"sh", "-c", "stty sane < /dev/tty"}).waitFor();
        } catch (InterruptedException e) {
            throw new IOException("Failed to disable raw mode", e);
        }
    }

    public void newgameMenu() throws IOException {
        GameScreen.clearScreen();
        // GameScreen.printAt(3, 0, "newgameMenu", 30);
        int selectedIndex = 0;

        while (true) {
            renderMenuAddCard(selectedIndex);

            // Capture raw input
            String input = readRawInput();

            // Handle Ctrl + X for termination
            if (input.equals("\u0018")) { // ASCII for Ctrl + X
                GameScreen.clearScreen();
                return;
            }

            // Handle input cases
            switch (input) {
                case "\033[A": // Arrow Up
                    selectedIndex = (selectedIndex - 1 + MENU_ITEMS_CARD_SELECT.length) % MENU_ITEMS_CARD_SELECT.length;
                    break;
                case "\033[B": // Arrow Down
                    selectedIndex = (selectedIndex + 1) % MENU_ITEMS_CARD_SELECT.length;
                    break;
                case "\033[C": // Arrow Right
                    selectedIndex = (selectedIndex + 1) % MENU_ITEMS_CARD_SELECT.length;
                    break;
                case "\033[D": // Arrow Left
                    selectedIndex = (selectedIndex - 1 + MENU_ITEMS_CARD_SELECT.length) % MENU_ITEMS_CARD_SELECT.length;
                    break;
                case "\n": // Enter key (Unix-style)
                case "\r": // Enter key (Windows-style)
                    handleSelectionAddCard(selectedIndex); // Perform action for selected item
                    if (selectedIndex == 4) {
                        GameScreen.clearScreen();
                        return; // Exit menu loop
                    }
                    break;
                default:
                    GameScreen.printAt(0, 0, "Invalid input: " + input, 15);
            }
        }
    }

    public void play() throws IOException {
        GameScreen.clearScreen();
        // GameScreen.printAt(3, 0, "newgameMenu", 30);
        int selectedIndex = 0;

        while (true) {
            renderPlay(selectedIndex);

            // Capture raw input
            String input = readRawInput();

            // Handle Ctrl + X for termination
            if (input.equals("\u0018")) { // ASCII for Ctrl + X
                GameScreen.clearScreen();
                return;
            }

            // Handle input cases
            switch (input) {
                case "\033[A": // Arrow Up
                    selectedIndex = (selectedIndex - 1 + MENU_ITEMS_PLAY.length) % MENU_ITEMS_PLAY.length;
                    break;
                case "\033[B": // Arrow Down
                    selectedIndex = (selectedIndex + 1) % MENU_ITEMS_PLAY.length;
                    break;
                case "\033[C": // Arrow Right
                    selectedIndex = (selectedIndex + 1) % MENU_ITEMS_PLAY.length;
                    break;
                case "\033[D": // Arrow Left
                    selectedIndex = (selectedIndex - 1 + MENU_ITEMS_PLAY.length) % MENU_ITEMS_PLAY.length;
                    break;
                case "\n": // Enter key (Unix-style)
                case "\r": // Enter key (Windows-style)
                    handleSelectionPlay(selectedIndex); // Perform action for selected item
                    if (selectedIndex == 4) {
                        GameScreen.clearScreen();
                        return; // Exit menu loop
                    }
                    break;
                default:
                    GameScreen.printAt(0, 0, "Invalid input: " + input, 15);
            }
        }
    }

    private void renderPlay(int selectedIndex) throws IOException {
        GameScreen.clearScreen();
        GameScreen.drawLine(26);
        // GameScreen.printAt(l+3, 1, "_".repeat(ScreenMaxWidth-2));

        int colStartTime = Main.ScreenMaxWidth - 27 * 3;

        if (Main.currentTime > Main.avatar.getMaxTurns()) {
            //end of day
            for (Subject subject : Main.subjects.subList(0, Main.currentDay)) {
                if (subject.getItems() > 0) {
                    Main.avatar.useAP(subject);
                    if (subject.getItems() > 0) {
                        subject.induceAnxiety();
                        if(Main.avatar.getMP() < 0){
                        String error;
                            error  = "You're too stressed to act, YOU LOST";
                            GameScreen.MessageBoxOK(error);
                        }
                    }
                } 
            }
            Main.avatar.academicpoints = 0;
        
            Main.currentTime = Main.starttime;
            Main.currentDay++;
            Main.avatar.refreshstats();
            if(Main.currentDay > 4){
                for(int i = 0; i >= 4; i++){
                    if (Main.subjects.get(i).items > 0) {
                        String error;
                        error  = "Your project is past due, YOU LOST";
                        GameScreen.MessageBoxOK(error);
                    }
                }
                GameScreen.MessageBoxOK("You submitted all projects in time, You Won!");
            }
            
            for (Subject subject : Main.subjects.subList(0, Main.currentDay)) {
                //subject at the start of day
                if (subject.getItems() > 0) {
                    subject.performAction(Main.avatar);
                } 
            }

            // for (int i = 0; i <= Main.currentDay; i++) {
            //     if (Main.subjects.get(i).items > 0) {
            //         if (Main.avatar.useAP(Main.subjects.get(i))) {
            //             i = Main.currentDay;
            //         }
            //     }
            // }
            //Main.avatar.refreshstats();
        }
        GameScreen.drawDay(1, colStartTime + Main.currentTime * 3 - 3, Main.currentDay);

        for (int i = 1; i <= 24; i++) {
            GameScreen.printAt(2, colStartTime + i * 3 + 3, String.valueOf((i)));
        }
// Avatar
        GameScreen.drawAvatar(4, 8);
        GameScreen.printAt(5, 35, "Academic");
        GameScreen.printAt(6, 35, " Points");
        GameScreen.printAt(7, 37, String.valueOf(Main.avatar.academicpoints));
        GameScreen.progressBar(15, 17, "MP", Main.avatar.mentalprowess, 10);
        GameScreen.progressBar(18, 17, "FP", Main.avatar.focuspoints, 10);

// Books
        int j = 1;
        for (Subject subject : Main.subjects.subList(0, Main.currentDay)) {
            if (subject.getItems() <= 0) {
            } else {
                GameScreen.drawBook(12, colStartTime + 18 * j - 18, subject.subjectnum, subject);
                j++;
            }
        }

        int nextmargin = 3;
        int downmargin = 27;

        // Clear CARD Text
        for (int i = 0; i < 25; i++) {
            GameScreen.printAt(downmargin + i, nextmargin, " ".repeat(120));
        }

        for (int i = 0; i < Main.avatar.hand.size(); i++) {
            if (selectedcardHand != i) {
                Main.cards.get(Main.avatar.hand.get(i)).drawCard(downmargin + 3, nextmargin, 12);
                nextmargin += 5;
            } else {
                nextmargin += 15;
            }
            downmargin += 1;
        }
        if (!Main.avatar.hand.isEmpty()) {
            Main.cards.get(Main.avatar.hand.get(selectedcardHand)).drawCard(27, 3 + selectedcardHand * 5, 14);
        }

        GameScreen.drawLine(Main.ScreenMaxHeight-3);

// Menu
        int l = Main.ScreenMaxHeight - 2;
        int c = 58;

        for (int i = 0; i < MENU_ITEMS_PLAY.length; i++) {
            if (i == selectedIndex) {
                GameScreen.printAt(l, c, HIGHLIGHT + MENU_ITEMS_PLAY[i] + RESET, 30);
            } else {
                GameScreen.printAt(l, c, MENU_ITEMS_PLAY[i], 30);
            }
            c = c + MENU_ITEMS_PLAY[i].length() + 1;
        }

// History
        GameScreen.drawBox(28, Main.ScreenMaxWidth-65, 64, 20, "Action History");
        l = 30;
        for (int i = Main.actionhistory.size()-1; i >= 0; i--) {
            GameScreen.printAt(l++, Main.ScreenMaxWidth-63, Main.actionhistory.get(i));
            if (i < Main.actionhistory.size()-15) {
                break;
            }
            
        }

    }

    private void handleSelectionPlay(int selectedIndex) throws IOException {
        int maxqty = Main.cards.get(selectedcardHand).Number;
        int cardqty = Main.cards.get(selectedcardHand).qty;
        String error = "";
        switch (selectedIndex) {
            case 0: // "Previous":
                // newgameMenu();
                selectedcardHand--;
                if (selectedcardHand < 0) {
                    selectedcardHand = Main.avatar.hand.size() - 1;
                }
                // newgameMenu();
                break;
            case 1: // "Play Card:
                // GameScreen.printAt(0, 0, MENU_ITEMS_CARD_SELECT[selectedIndex], 20);
                error = Main.avatar.DropCard(selectedcardHand);
                if (!"".equals(error)) {
                    GameScreen.MessageBoxOK(error);
                }
                selectedcardHand = 0;

                break;
            case 2: // "End Turn":
                Main.currentTime += 1;
                Main.avatar.addFP(1);

                error = Main.avatar.GetCard();
                if (!"".equals(error)) {
                    GameScreen.MessageBoxOK(error);
                }
                break;
            case 3: // "Next Card":
                // GameScreen.printAt(0, 0,MENU_ITEMS_CARD_SELECT[selectedIndex], 30);
                selectedcardHand++;
                if (selectedcardHand >= Main.avatar.hand.size()) {
                    selectedcardHand = 0;
                }
                break;
            case 5: // Start Game
                play();
                break;
            // case 5: // "Main Menu":
            // // GameScreen.printAt(40, 0, MENU_ITEMS_CARD_SELECT[selectedIndex], 30);
            // GameScreen.clearScreen();
            // show();
            // break;
            // if(Main.avatar.drawpile.size() < 8){}
            // Display Selected Avatar
            }
        for (int x = 0; x < 20; x++) {
            GameScreen.printAt(x, 140, "   ");
            GameScreen.printAt(x, 150, "   ");
        }
        for (int x = 0; x < Main.avatar.ofhand.size(); x++) {
            GameScreen.printAt(x, 140, String.valueOf(Main.avatar.ofhand.get(x)));
        }
        for (int x = 0; x < Main.avatar.hand.size(); x++) {
            GameScreen.printAt(x, 150, String.valueOf(Main.avatar.hand.get(x)));
        }
        GameScreen.printAt(1, 0, "\nYou selected: " + MENU_ITEMS_CARD_SELECT[selectedIndex] + "->" + String.valueOf(selectedcard), 30);

    }

    private static void newdeckMenu() {
        Scanner input = new Scanner(System.in);
        System.out.print("   |Input Deck Name: ");
        String deckname = input.nextLine();
        CardList.allCards();
        formatMenu(deckname);
        addCards(deckname);
    }

    private static void addCards(String deckname) {
        ArrayList<Integer> deck = new ArrayList<>();
        System.out.print(
                """
                        -----------Add Cards-----------
                        --------(0)Remove Cards--------
                        ---------(-1)Save Deck---------
                        ----------(-2)Cancel-----------
                        """);
        while (true) {
            System.out.print("   |Input here: ");
            Scanner input = new Scanner(System.in);
            if (input.hasNextInt()) {
                int choice = input.nextInt();
                if (choice > 0 && choice < 10) {
                    deck.add(choice);
                } else {
                    switch (choice) {
                        case 0 -> {
                            input.close();
                            removeCards(deckname, deck);
                        }
                        case -1 -> {
                            input.close();
                            Decks.addDeck(deckname, deck);
                        }
                        case -2 -> {
                            input.close();
                            newdeckMenu();
                        }
                        default -> {
                            System.out.println(ConsoleColors.RED + "Invalid input! Please choose from the given options"
                                    + ConsoleColors.RESET);
                        }
                    }
                }
            } else {
                System.out.println("Error, Input must be an integer!");
            }
        }
    }

    private static void removeCards(String deckname, ArrayList<Integer> deck) {
        formatMenu("Remove Cards");
        System.out.print("""
                ---------Remove Cards---------
                ---------(0)Add Cards---------
                --------(-1)Save Deck---------
                ---------(-2)Cancel-----------
                   """);
        while (true) {
            System.out.print("   |Input here: ");
            Scanner input = new Scanner(System.in);
            if (input.hasNextInt()) {
                int choice = input.nextInt();
                if (choice > 0 && choice < 10) {
                    deck.remove(Integer.valueOf(choice));
                } else {
                    switch (choice) {
                        case 0 ->
                            addCards(deckname);
                        case -1 -> {
                            input.close();
                            Decks.addDeck(deckname, deck);
                        }
                        case -2 ->
                            addCards(deckname);
                        default -> {
                            System.out.println(ConsoleColors.RED + "Invalid input! Please choose from the given options"
                                    + ConsoleColors.RESET);
                        }
                    }
                }
            } else {
                System.out.println("Error, Input must be an integer!");
            }
        }
    }

    private static void formatMenu(String menutitle) {
        int i = (int) Math.ceil((30 - menutitle.length()) / 2.0);
        for (int j = 0; j <= 2; j++) {
            while (i > 0) {
                i--;
                System.out.print("-");
            }
            i = (30 - menutitle.length()) / 2;
        }
        System.out.println(" ");
    }
}
// ----------Main Menu----------
// --------(1)New Game----------
// -------(2)Load Game----------
// ------(3)Game History--------
// Input Here:
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//
// ________________________________________________________________________________________________
// |Enter (x) to exit
// |
// |
// |
// |
// |
// |Academic Points:
// |MP 10|----------|
// |FP 10|----------| Progress 10|----------| Progress 10|----------|
// | ________ ___________
// | | | | | |
// | |______| | | /| |
// | ___||___ | | | |
// | ||| ||| | | --- |
// | |||__||| |_|_________|
// | |||| |___________|
// | ||||
// |_______________________________________________________________________________________________
// | Actions |
// |you dealt damage |
// |subject 1 used an ability |
// |you took damage |
// |you played a card |
// |___________________________________________|
// | --------------–
// | |Card title |
// | | |
// | |-------------- |
// | |This card acts |
// | |by using this |
// | |focus and stu- |
// | |ff |
// | | |
// | |n fp/ n mp |
// | ---------------
// | (1)
// |Input Here:
// |_______________________________________________________________________________________________
