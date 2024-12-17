package JAVA;

enum CardType{
    BLUE,
    GREEN,
    RED,
    PURPLE
}

enum statType{
    BLUE,
    GREEN,
    RED,
    PURPLE,
    PINK
}
abstract class Actions {
    protected String title;
    protected String description;
    protected CardType cardtype;
    protected int size;
    protected int Blueenergy;
    protected int Blue;
    protected int Red;
    protected int Green;
    protected int Greenenergy;
    protected int code;
    protected int Number;
    protected int qty;

    // String color = cardtype.name();
    protected String color;

    Actions(int Blueenergy, int Blue, int Green, int Greenenergy, int Red, int size, CardType cardtype, String title, String description, int Number, int code){
        this.code = code;
        this.Blue = Blue;
        this.Green = Green;
        this.Red = Red;
        this.size = size;
        this.cardtype = cardtype;
        this.title = title;
        this.description = description;
        this.Greenenergy = Greenenergy;
        this.Blueenergy = Blueenergy;
        this.Number = Number;
        this.qty = 0;
    }

    public void addQty(int amount){
        this.qty += amount;
    }
    
    public void printTitle(){
        System.out.print(title);
    }

    public String getTitle(){
        return title;
    }

    public int getBlue(){
        return Blue;
    }

    public int getRed(){
        return Red;
    }

    public int getGreen(){
        return Green;
    }

    public int getSize(){
        return size;
    }

    public int getGreenEnergy(){
        return Greenenergy;
    }

    public int getBlueEnergy(){
        return Blueenergy;
    }

    public void cardTitle(int section){
        int startindex = (section*15);
        int endindex = (section*15) + 16;
        for(int i = 0; i <= 2; i++){
            ConsoleColors.setColor(color);
            System.out.print("|");
            if(title.length() > 15){
                System.out.print(String.format(ConsoleColors.RESET + ToolKit.centerString(15, title.substring(startindex, endindex))));
                startindex += 16;
                endindex += 16 - title.length();
            }
            else{
                if(i == 0){
                    System.out.print(String.format(ConsoleColors.RESET + ToolKit.centerString(15, title)));
                }
                else{
                    System.out.print(String.format("%-15s= %s" , " "));
                }
            }
            ConsoleColors.setColor(color);
            System.out.print("|");
        }
    }

    public void drawCard(int row, int c,  int heigth, int typ) {
        int r = row;
        String textColor;
        String backColor;
        textColor = ConsoleColors.WHITE;
        backColor =  ConsoleColors.BLACK_BACKGROUND;

        if (null!=cardtype) switch (cardtype) {
            case BLUE -> textColor = ConsoleColors.BLUE;
            case GREEN -> textColor = ConsoleColors.GREEN;
            case RED -> textColor = ConsoleColors.RED;
            case PURPLE -> textColor = ConsoleColors.PURPLE;
            default -> { textColor = ConsoleColors.RESET;
            }
        }

        if (typ == 1) {
            GameScreen.drawBox(r, c, 20, heigth, "",textColor, backColor);
            GameScreen.drawLine(r+2,c,20,textColor, backColor);
            GameScreen.drawLine(heigth-3+row,c,20,textColor, backColor);
        } else {
            GameScreen.drawDoubleLineBox(r, c, 20, heigth, "", textColor, backColor);
            GameScreen.drawDoubleLine(r+2,c,20,textColor, backColor);
            GameScreen.drawDoubleLine(heigth-4+row,c,20,textColor, backColor);
        }

        GameScreen.printAt(++r, c+1, title);
        r++;
        
        GameScreen.PrintMultiple(r, c+2, description, 16);
        // String[] justifiedLines = TextUtils.justifyText(description, 16);
        // for (String line : justifiedLines) {
        //     GameScreen.printAt(++r, c+2, line); 
        // }

        if (typ == 2) {
            GameScreen.printAt(heigth-5+row, c+2, "Time: " + String.valueOf(size*3) + " Hours" );
            GameScreen.printAt(heigth-3+row, c+2, "MP: " + String.valueOf(Greenenergy));
            GameScreen.printAt(heigth-3+row, c+11, "FP: " + String.valueOf(Blueenergy));
            GameScreen.printAt(heigth-2+row, c+5, "Count: " + String.valueOf(qty)+ "/" + String.valueOf(Number));
        } else {
            GameScreen.printAt(heigth-4+row, c+2, "Time: " + String.valueOf(size*3) + " Hours" );
            GameScreen.printAt(heigth-2+row, c+2, "MP: " + String.valueOf(Greenenergy));
            GameScreen.printAt(heigth-2+row, c+11, "FP: " + String.valueOf(Blueenergy));
        }
        r++;
    }

    public void drawCard(int r, int c,  int heigth) {
        drawCard(r,c, heigth, 1);
    }

    public void drawCard(int r, int c) {
        drawCard(r,c, 14, 1);
    }

    public void cardDescription(int section){
        int i = (section*15);
        int j = (section*15) + 16;
        int len = description.length();
        ConsoleColors.setColor(color);
        if(j > len){
            if((j - len) > 0){
                j -= (j-len);
                System.out.print(String.format("%-15s" ,ConsoleColors.RESET + description.substring(i, j)));
            }
            if(len < 15){
                System.out.print(String.format("%-15s" ,ConsoleColors.RESET + description));
            }
            else{
                System.out.print("|");
                System.out.print(String.format("%-15s" , " "));
                System.out.print("|");

            }
        }
        else{
            ConsoleColors.setColor(color);
            System.out.print("|");
            System.out.print(String.format("%-15s" ,ConsoleColors.RESET + description.substring(i, j)));
            ConsoleColors.setColor(color);
            System.out.print("|");
            i += 15;
            j += 15;
        }
    }

    public void energyCost(){
        System.out.print("|");
        System.out.print(String.format("%-15s" ,ConsoleColors.BLUE + Blue + "/" + ConsoleColors.GREEN + Greenenergy));
        ConsoleColors.setColor(color);
        System.out.print("|");
    }

    public abstract String getMessage();

    // public abstract String applyCard(Avatar avatar);
    
    // protected abstract void uniqueEffect();

    public String applyCard(Avatar avatar){
        if  (-Blueenergy > avatar.focuspoints) {
            return "Insuficient Focus Points";
        }
        if  (-Greenenergy >= avatar.mentalprowess) {
            return "Insuficient Mental Prowess";
        }
        avatar.addMP(Greenenergy);
        avatar.addFP(Blueenergy);
        avatar.addFP(Blue);
        avatar.addMP(Green);
        avatar.addAP(Red);
        Main.currentTime += size;
        Main.actionhistory.add(this.getMessage());
        return "";
    }

    public void displayLine(int line){
        switch(line){
            case 0: System.out.print(" --------------- ");
            break;
            case 1: cardTitle(0);
            break;
            case 2: cardTitle(1);
            break;
            case 3: System.out.print("|---------------|");
            break;
            case 4: cardDescription(0);
            break;
            case 5: cardDescription(1);
            break;
            case 6: cardDescription(2);
            break;
            case 7: cardDescription(3);
            break;
            case 8: System.out.print("|               |");
            break;
            case 9: energyCost();
            break;
            case 10: System.out.print(" --------------- ");
            break;
        }
        System.out.print(ConsoleColors.RESET);
    }
    
    public void displayCard(CardType cardtype){
        String color = cardtype.name();
        ConsoleColors.setColor(color);
        for(int line = 0; line <= 10; line++){
            ConsoleColors.setColor(color);
            displayLine(line);
        }
        System.out.print(ConsoleColors.RESET);
    }
    
}

class GamingSession extends Actions{

    GamingSession(){
        super(-3, 0, 4, 0, 0, 2, CardType.GREEN, "Gaming Session", "+2 laze Stacks, Restore 4 Mental Prowess", 2,1);
    }
    
    @Override
    public String getMessage(){
        return("You Played Gaming Session, restored 2 MP");
    }

    public String applyCard(Avatar avatar){
        super.applyCard(avatar);
        avatar.addlaze(2);
        return "";
    }
}

class JustOneGame extends Actions{

    JustOneGame(){
        super(-1, 0, 1, 0, 0, 1, CardType.GREEN, "Just One Game", "+1 laze Stack, Restore 1 Mental Prowess", 3, 2);
        
    }

    @Override
    public String getMessage(){
        return("You Played Just One Game, restored 1 MP");
    }

    public String applyCard(Avatar avatar){
        super.applyCard(avatar);        
        avatar.addlaze(1);
        return "";
    }
}

class NaturalGamer extends Actions{

    NaturalGamer(){
        super(-3, 0, 0, 0, 0, 0, CardType.PURPLE, "Natural Gamer", "add a \"Just One Game\" to hand every round", 1, 3);
    }

    @Override
    public String getMessage(){
        return("You have acquired the trait \"Natural Gamer\" get copies of \"game\" cards");
    }

    @Override
    public String applyCard(Avatar avatar){
        super.applyCard(avatar);        
        avatar.ofhand.add(3);
        return "";
    }
}

class OnTheSide extends Actions{
    
    OnTheSide(){
        super(-1, 0, 1, 0, 0, 0, CardType.GREEN, "On The Side", "Restore 1 Mental Prowess, draw one card", 3, 4);
    }

    @Override
    public String getMessage(){
        return("You relaxed for a short while on the side, restored 1 MP");
    }

    @Override
    public String applyCard(Avatar avatar){
        super.applyCard(avatar);        
        return "";
    }
}

class Procrastinate extends Actions{

    Procrastinate(){
        super(-2, 0, 0, -2, 3, 2, CardType.RED , "Procrastinate", "Use two slots, Gain 3 Academic Points per laze stack", 2, 5);
    }

    @Override
    public String getMessage(){
        return("You chose to Procrastinate, Gained 3*laze AP");
    }

    @Override
    public String applyCard(Avatar avatar){
        super.applyCard(avatar);        
        avatar.addAP(Red * avatar.getLaze());
        return "";
    }
}

class AdvancedStudy extends Actions{
    
    AdvancedStudy(){
        super(-2, 0, 0, 0, 5, 2, CardType.RED, "Advanced Study", "Use two slots, Gain 5 Academic Points", 2, 6);
    }
    
    @Override
    public String getMessage(){
        return("You Played AdvancedStudy, Gained 5 AP");
    }

    @Override
    public String applyCard(Avatar avatar){
        super.applyCard(avatar);        
        return "";
    }
}

class LastMinuteReview extends Actions{

    LastMinuteReview(){
        super(0, 0, 0, 0, 1, 0, CardType.RED, "Last Minute Review", "Gain 1 Academic Point", 3, 7);
    }

    @Override
    public String getMessage(){
        return("You Played Last Minute Review, Gained 1 AP");
    }

    public String applyCard(Avatar avatar){
        super.applyCard(avatar);        
        return "";
    }
}

class JustAPeek extends Actions{

    JustAPeek(){
        super(-1, 0, 0, -2, 2, 0, CardType.RED, "Just A Peek", "Gain 2 Academic Points", 2, 8);
    }

    @Override
    public String getMessage(){
        return("You Played Just a Peek, Gained 2 AP");
    }

    @Override
    public String applyCard(Avatar avatar){
        super.applyCard(avatar);        
        return "";
    }
}

class BookReading extends Actions{
    
    BookReading(){
        super(-2, 0, 0, 0, 3, 1, CardType.RED, "Book Reading", "Gain 3 Academic Points", 4, 9);
    }

    @Override
    public String getMessage(){
        return("You Played Book Reading, Gained 3 AP");
    }

    @Override
    public String applyCard(Avatar avatar){
        super.applyCard(avatar);        
        return "";
    }
}

class ShortNap extends Actions{

    ShortNap(){
        super(0, 3, 0, 0, 0, 1, CardType.BLUE, "Short Nap", "Restore 2 Focus Points", 2, 10);
    }

    @Override
    public String getMessage(){
        return("You took a Short Nap, Restored 2 FP");
    }

    public String applyCard(Avatar avatar){
        super.applyCard(avatar);        
        return "";
    }
}

class HotShower extends Actions{

    HotShower(){
        super(0, 2, 0, 0, 0, 1, CardType.BLUE, "Hot Shower", "Restore 2 Focus Points", 2, 11);
    }

    @Override
    public String getMessage(){
        return("You took a Hot Shower, Restored 2 FP");
    }

    @Override
    public String applyCard(Avatar avatar){
        super.applyCard(avatar);        
        return "";
    }
}

class AllNighter extends Actions{
    
    AllNighter(){
        super(0, 2, 0, -3, 0, 2, CardType.BLUE, "All Nighter", "Gain two extra slots now, lose 3 MP", 1, 11);
    }

    @Override
    public String getMessage(){
        return("You had an All Nighter");
    }

    @Override
    public String applyCard(Avatar avatar){
        super.applyCard(avatar);        
        avatar.addmaxTurns(2);
        return "";
    }
}

class MusicAndChill extends Actions{

    MusicAndChill(){
        super(-1, 0, 0, 0, 0, 1, CardType.BLUE, "Music And Chill", "Draw 2 cards", 2, 13);
    }

    @Override
    public String getMessage(){
        return("You Played Music and Chill");
    }

    @Override
    public String applyCard(Avatar avatar){
        super.applyCard(avatar);        
        avatar.GetCard();
        avatar.GetCard();
        return "";
    }
}

class HappyPills extends Actions{

    HappyPills(){
        super(0, 0, 0, -5, 0, 2, CardType.RED, "Lucid Pills", "Academic Points this turn is doubled", 2, 14);
    }

    @Override
    public String getMessage(){
        return("You Played Lucid Pills");
    }

    @Override
    public String applyCard(Avatar avatar){
        super.applyCard(avatar);        
        avatar.addEfficieny(1);
        return "";
    }
}

class MasterPlanner extends Actions{
    
    MasterPlanner(){
        super(-3, 0, 0, 0, 0, 0, CardType.PURPLE, "Master Planner", "Gain 2 more energy per turn", 1, 15);
    }

    @Override
    public String getMessage(){
        return("You have earned the Master Planner trait");
    }

    @Override
    public String applyCard(Avatar avatar){
        super.applyCard(avatar);        
        avatar.addFocusRegen(2);
        return "";
    }
}
// 1 --------------–    
// 2 |Card title     | title 15 widcth 2 heigth (30characters max)
// 3 |               |
// 4 |-------------- |
// 5 |This card acts | description 15 width 4 heigth (60characters max)
// 6 |by using this  |
// 7 |focus and stu- |
// 8 |ff             |
// 9 |               |
//10 |n fp/ n mp     |energycost
//11 ----------------
//   12345678901234567