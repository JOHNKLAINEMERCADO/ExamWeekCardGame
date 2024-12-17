package JAVA;
import java.util.ArrayList;

public class CardList {
    public static Actions findCard(int code){
        switch(code){
            case 1: return gamingsession;
        }
        return gamingsession;
    }

    public static String centerString(int totalWidth, String text) {
        int padding = (totalWidth - text.length()) / 2;
        String centeredText = String.format("%" + padding + "s%s%" + padding + "s", "", text, "");
        return centeredText;
    }

    public static void displayTitle(ArrayList<Integer> pile){
        for(int cardnum = 0; cardnum <= pile.size(); cardnum++){
                Actions card = CardList.findCard(pile.get(cardnum));
                card.printTitle();
                if(cardnum - 1 == pile.size()){
                    continue;
                }
                System.out.print(" - ");
            }
    }

    public static void displayCards(ArrayList<Integer> pile){
        for(int line = 0; line >= 10; line++){
            System.out.print(" ");
            for(int cardnum = 0; cardnum <= pile.size(); cardnum++){
                Actions card = CardList.findCard(pile.get(cardnum));
                card.displayLine(line);
                System.out.print("\t");
                if(line == 9){
                    System.out.println(centerString(17, "(" + cardnum+1 + ")"));
                }
            }
            System.out.print("\n");
        }
    }

    public static void allCards(){
        System.out.println("Card List");
        for(int line = 0; line >= 10; line++){
            System.out.print(" ");
            for(int i = 1; i <= 10; i++){
                if(line == 0){
                    System.out.println(centerString(17, "(" + i+1 + ")"));
                }
                Actions card = CardList.findCard(i);
                card.displayLine(line);
                System.out.print("\t");
                }
            System.out.print("\n");
        }
    }

    static GamingSession gamingsession = new GamingSession();
    
}
