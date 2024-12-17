package JAVA;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
public class Decks {
    public static void displayDecks(){
        ArrayList<Integer> decks = new ArrayList<Integer>();
        String deckname;
        int i = 0;
        try {
            File deckFile = new File("deckfile.txt");
            Scanner deckScanner = new Scanner(deckFile);
            deckScanner.useDelimiter(",");

            while (deckScanner.hasNextLine()) {
                if(i == 0){
                    deckname = deckScanner.nextLine();
                    System.out.println("(" + (i+1) + ") " + deckname);
                }
                else{
                    decks.add(Integer.valueOf(deckScanner.nextLine()));
                }

                i++;

                if(i == 8){
                        CardList.displayCards(decks);
                        decks.clear();
                        System.out.print("\n\n");
                        i = 0;
                    }
            }
            deckScanner.close();
            } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            }
            
    }

    //access file of decks to read values
    public static ArrayList<Integer> selectDeck(int decknum){
        ArrayList<Integer> deck = new ArrayList<Integer>();
        String deckname = "value not found";
        int i = 0;
        int j = 1;
        try {
            File deckFile = new File("deckfile.txt");
            Scanner deckScanner = new Scanner(deckFile);
            deckScanner.useDelimiter(",");

            while (deckScanner.hasNextLine()) {
                if(i == 0){
                    deckname = deckScanner.nextLine();
                }
                else{
                    deck.add(Integer.valueOf(deckScanner.nextLine()));
                }

                i++;

                if(j == decknum){
                    break;
                }
                if(i == 8){
                    j++;
                    deck.clear();
                    i = 0;
                }
            }
            deckScanner.close();
            } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            }
            System.out.println(deckname + "was successfully selected!");
            return deck;
    }

    //access file of decks to add values
    public static void addDeck(String deckname, ArrayList<Integer> deck){
        try {
            FileWriter deckScanner = new FileWriter("filename.txt");
            deckScanner.write(deckname + ",");
            for(int i = 0; i <= 8; i++){
                deckScanner.write(deck.get(i) + ",");
            }deckScanner.write("\n");
            deckScanner.close();
            System.out.println("Deck has been added");
            } catch (IOException e) {
            System.out.println("An error occurred.");
                e.printStackTrace();
            }
    }
}
