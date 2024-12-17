package JAVA;
import java.util.ArrayList;

public class StageMenu{

    private static void turnTimer(int turnnum){
        switch(turnnum){
            case 1: System.out.print(centerString(96 , ConsoleColors.WHITE_BOLD + "\t\t    12:00am-2:59am" + ConsoleColors.RESET + "\n\t9:00pm-11:59pm\t\t3:00am-5:59am\n6:00pm-8:59pm\t\t\t\t6:00am-8:59pm\n\t3:00pm-5:59pm\t\t9:00pm-11:59pm\n\t\t    12:00pm-2:59pm\n"));
            break;
            case 2: System.out.print(centerString(96, "\t\t    12:00am-2:59am\n\t9:00pm-11:59pm\t\t" + ConsoleColors.WHITE_BOLD + "3:00am-5:59am" + ConsoleColors.RESET + "\n6:00pm-8:59pm\t\t\t\t6:00am-8:59am\n\t3:00pm-5:59pm\t\t9:00am-11:59am\n\t\t    12:00pm-2:59pm\n"));
            break;
            case 3: System.out.print(centerString(96 , "\t\t    12:00am-2:59am\n\t9:00pm-11:59pm\t\t3:00am-5:59am\n6:00pm-8:59pm\t\t\t\t" + ConsoleColors.WHITE_BOLD + "6:00am-8:59am" + ConsoleColors.RESET + "\n\t3:00pm-5:59pm\t\t9:00am-11:59am\n\t\t    12:00pm-2:59pm\n"));
            case 4: System.out.print(centerString(96 , "\t\t    12:00am-2:59am\n\t9:00pm-11:59pm\t\t3:00am-5:59am\n6:00pm-8:59pm\t\t\t\t6:00am-8:59pm\n\t3:00pm-5:59pm\t\t" + ConsoleColors.WHITE_BOLD + "9:00am-11:59am" + ConsoleColors.RESET + "\n\t\t    12:00pm-2:59pm\n"));
            case 5: System.out.print(centerString(96 , "\t\t    12:00am-2:59am\n\t9:00pm-11:59pm\t\t3:00am-5:59am\n6:00pm-8:59pm\t\t\t\t6:00am-8:59am\n\t3:00pm-5:59pm\t\t9:00am-11:59am\n\t\t    " + ConsoleColors.WHITE_BOLD + "12:00pm-2:59pm\n" + ConsoleColors.RESET));
            case 6: System.out.print(centerString(96 , "\t\t    12:00am-2:59am\n\t9:00pm-11:59pm\t\t3:00am-5:59am\n6:00pm-8:59pm\t\t\t\t6:00am-8:59am\n\t" + ConsoleColors.WHITE_BOLD + "3:00pm-5:59pm" + ConsoleColors.RESET + "\t\t9:00am-11:59am\n\t\t    12:00pm-2:59pm\n"));
            case 7: System.out.print(centerString(96 , "\t\t    12:00am-2:59am\n\t9:00pm-11:59pm\t\t3:00am-5:59am\n" + ConsoleColors.WHITE_BOLD + "6:00pm-8:59pm" + ConsoleColors.RESET + "\t\t\t\t6:00am-8:59am\n\t3:00pm-5:59pm\t\t9:00am-11:59am\n\t\t    12:00pm-2:59pm\n"));
            case 8: System.out.print(centerString(96 , "\t\t    12:00am-2:59am\n\t" + ConsoleColors.WHITE_BOLD + "9:00pm-11:59pm" + ConsoleColors.RESET + "\t\t3:00am-5:59am\n6:00pm-8:59pm\t\t\t\t6:00am-8:59am\n\t3:00pm-5:59pm\t\t9:00am-11:59am\n\t\t    12:00pm-2:59pm\n"));
        }
    }

    public static String centerString(int totalWidth, String text) {
    int padding = (totalWidth - text.length()) / 2;
    String centeredText = String.format("%" + padding + "s%s%" + padding + "s", "", text, "");
    return centeredText;
    }

    public static void startGame(ArrayList<Integer> deck){
       // Avatar avatar = new Avatar(deck);
       // stageScreen(1, 0, 0, avatar);
    }

    public static void stageScreen(int turnnum, int progress1, int progress2, Avatar avatar){
        ArrayList<String> actionhistory = new ArrayList<>();
        int mp = avatar.getMP();
        int fp = avatar.getFP();
        int ap = avatar.getAP();
        System.out.print("+".repeat(96) + "\n\n" + "_".repeat(96));
        System.out.print("Enter (0) to exit");
        turnTimer(turnnum);
        System.out.println(String.format("Academic Points: " + avatar));
        System.out.print(String.format("%-16s", "MP " + mp + "|" + "-".repeat(mp)) + "|" + " ".repeat(28));      
        System.out.print(String.format("%-16s", "FP " + fp + "|" + "-".repeat(fp)) + "|" + " ".repeat(28)
                        + "Progress " + progress1 + String.format("%-11s", "|" + "-".repeat(progress1)) + "|"
                        + " Progress " + progress2 + String.format("%-11s", "|" + "-".repeat(progress2)) + "|");
        System.out.print("       ________                                         ___________             ___________ \n" +
                         "       |      |                                        ║║║         |           | |   ___   |\n" +
                         "       |______|                                        ║║║   /|    |           | |      |  |\n" +
                         "       ___||___                                        ║║║    |    |           | |     /   |\n" +
                         "       |||  |||                                        ║║║   ---   |           | |   /___  |\n" +
                         "       |||__|||                                        ║║╚═════════|           |_|_________|\n" +
                         "         ||||                                          ╚╩══════════|           |___________|\n" +
                         "         ||||                                                            ");
        System.out.print("_".repeat(96));
        System.out.print("|" +"");
        avatar.displayHand();
    }

    private static void startOfTurn(Subject subject, Avatar avatar, ArrayList<String> actionhistory){
        actionhistory.add("");
    }

    private static void endOfTurn(Subject subject, Avatar avatar, ArrayList<String> actionhistory){
        int ap = avatar.getAP();
        subject.addItems(ap);
    }

    public static void drawpileScreen(){
        
    }

    public static void inputScreen(){

    }
}