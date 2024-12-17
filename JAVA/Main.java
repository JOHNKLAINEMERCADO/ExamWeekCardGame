package JAVA;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static ArrayList<Actions> cards = new ArrayList<>();
    public static ArrayList<String> actionhistory = new ArrayList<>();
    public static int ScreenMaxWidth;
    public static int ScreenMaxHeight;
    public static Avatar avatar = new Avatar();
    public static Subject subject1 = new Subject(1);
    public static Subject subject2 = new Subject(2);
    public static Subject subject3 = new Subject(3);
    public static Subject subject4 = new Subject(4);
    public static ArrayList<Subject> subjects= new ArrayList<>();
    public static int currentDay = 1;
    public static int currentTime = 7;
    public static int starttime = 7;
    
    public static void main(String[] args) throws IOException {

        try {
            // Execute the 'stty size' command
            Process process = Runtime.getRuntime().exec(new String[]{"sh", "-c", "stty size < /dev/tty"});
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String size = reader.readLine();
            reader.close();

            if (size != null) {
                String[] dimensions = size.split(" ");
                ScreenMaxHeight = Integer.parseInt(dimensions[0]); // Height
                ScreenMaxWidth = Integer.parseInt(dimensions[1]); // Width
                ScreenMaxWidth=ScreenMaxWidth>134?134:ScreenMaxWidth;

                System.out.println("Terminal Width: " + ScreenMaxWidth);
                System.out.println("Terminal Height: " + ScreenMaxHeight);
            } else {
                System.out.println("Could not determine terminal size.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        
        System.out.println("Screen Width: " + ScreenMaxWidth);
        System.out.println("Screen Height: " + ScreenMaxHeight);

        GameScreen.clearScreen();


        // GameCharacter hero = new GameCharacter(10, 10, '@');
        // hero.draw();

        // GameScreen.printAt(10,10,"String");
        // GameScreen.printAt(0,0,"KLAINE",10);

        cards.add( new GamingSession());
        cards.add( new JustOneGame());
        cards.add( new NaturalGamer());
        cards.add( new OnTheSide());
        cards.add( new Procrastinate());
        cards.add( new AdvancedStudy());
        cards.add( new LastMinuteReview());
        cards.add( new JustAPeek());
        cards.add( new BookReading());
        cards.add( new ShortNap());
        cards.add( new HotShower());
        cards.add( new AllNighter());
        cards.add( new MusicAndChill());
        cards.add( new HappyPills());
        cards.add( new MasterPlanner());

        subjects.add(subject1);
        subjects.add(subject2);
        subjects.add(subject3);
        subjects.add(subject4);

        avatar.add2DrawPile(Main.cards.get(1));
        avatar.add2DrawPile(Main.cards.get(2));
        avatar.add2DrawPile(Main.cards.get(3));
        avatar.add2DrawPile(Main.cards.get(4));
        avatar.add2DrawPile(Main.cards.get(5));
        avatar.add2DrawPile(Main.cards.get(6));
        avatar.add2DrawPile(Main.cards.get(7));
        avatar.add2DrawPile(Main.cards.get(8));
        avatar.add2DrawPile(Main.cards.get(9));
        avatar.add2DrawPile(Main.cards.get(10));

        Main.cards.get(1).addQty(1);
        Main.cards.get(2).addQty(1);
        Main.cards.get(3).addQty(1);
        Main.cards.get(4).addQty(1);
        Main.cards.get(5).addQty(1);
        Main.cards.get(6).addQty(1);
        Main.cards.get(7).addQty(1);
        Main.cards.get(8).addQty(1);
        Main.cards.get(9).addQty(1);
        Main.cards.get(10).addQty(1);


        MainMenu menu = new MainMenu();
        menu.show(); // Display the main menu
    }
}