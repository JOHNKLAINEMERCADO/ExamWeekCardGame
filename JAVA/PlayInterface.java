package JAVA;
import java.util.Random;

public class PlayInterface{
    public int deckcards;
    public int handcards;

    public PlayInterface(){
        deckcards = 0;
        handcards = 0;
    }

    public void updateDeckCards(int qty){
        deckcards = qty;
    }
    public int[] drawhand(){
        Random cardnum = new Random();
        int card;
        int decksize = 10;
        int handsize = 5;
        int[] hand = new int[handsize];
        for(int index = 0; index <= handsize; index++){
            card = cardnum.nextInt(decksize);
            hand[index] = card;
        }
        return hand;
    }
    
    public int[] drawcards(int drawqty){
        return new int[0];
    }

    //add method for drawing in or between stages
    //add method for displaying cards and choosing card to play

}