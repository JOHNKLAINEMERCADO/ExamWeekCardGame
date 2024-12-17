package JAVA;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Avatar {
    protected double efficiency;
    protected int laze;
    protected int academicpoints;
    protected int mentalprowess;
    protected int focuspoints;
    protected int turnnum;
    protected int maxturns;
    protected int trait;
    protected int focusregen = 5;
    protected int mpregen = 1;
    public ArrayList<Integer> hand = new ArrayList<>();
    public ArrayList<Integer> ofhand = new ArrayList<>();
    public ArrayList<Integer> drawpile = new ArrayList<>();
    protected int MaxMP = 10;
    protected int MaxFP = 10;

    Avatar(){
        this.efficiency = 1;
        this.laze = 0;
        this.academicpoints = 0;
        this.mentalprowess = 10;
        this.focuspoints = 10;
        this.turnnum = 1;
        this.maxturns = 15;
    }

    public boolean add2DrawPile(Actions card) {
        
        if (card.Number <= card.qty) {
            return false;
        }

        if(drawpile.size() == 10){
            return false;
        }
        drawpile.add(card.code-1);
        return true;
    }


    public void DrawFirst5() {
        Collections.shuffle(drawpile);        
        hand = new ArrayList<>();
        ofhand = new ArrayList<>();
        for (int j = 0; j < 5; j++) {
            hand.add(drawpile.get(j));
        }
        // get all other cards ofhand
        for (int j = 5; j < drawpile.size(); j++) {
            ofhand.add(drawpile.get(j));
        }
    }

    // Get cards to put on hand
    public String GetCard() {
        if (hand.size() >= 8) {
            return "More than 8 cards is not allowed";
        }
        if(ofhand.size() > 0) {
            hand.add( ofhand.get(0));
            ofhand.remove(0);
        } else {
            return "no more remaining cards";
        }
        return "";
    }

    // Play card
    public String DropCard(int cardIndex) {
        String error = "";
        if(!hand.isEmpty()) {
            if (Main.cards.get(hand.get(cardIndex)).Blueenergy > focuspoints){
               return "insufficient Focus Point";
            }
            error = Main.cards.get(hand.get(cardIndex)).applyCard(this);
            if (!"".equals(error)) {
                return error;
            }
            if (Main.cards.get(hand.get(cardIndex)).cardtype != CardType.PURPLE){
               ofhand.add(hand.get(cardIndex));
            }
            hand.remove(cardIndex);
        } else {
            return "no more remaining cards";
        }
        return "";
    }

    public boolean removeFromPile(Actions card){
        if(card.qty == 0){
            return false;
        }
        drawpile.remove(Integer.valueOf(card.code-1));
        return true;
    }

    public void useAP(Subject subject){
        subject.items -= academicpoints;
        if(academicpoints > subject.items){
            academicpoints -= subject.items;
        }
        else{
            academicpoints = 0;
        }
    }

    public void refreshstats(){
        this.addFP(focusregen);
        this.addMP(mpregen);
        this.efficiency = 1;
    }

    public int getFocusRegen(){
        return focusregen;
    }

    public int getMPRegen(){
        return mpregen;
    }

    public double getEfficiency(){
        return efficiency;
    }

    public int getLaze(){
        return laze;
    }

    public int getAP(){
        return academicpoints;
    }

    public int getFP(){
        return focuspoints;
    }

    public int getMP(){
        return mentalprowess;
    }

    public int getTurnNum(){
        return turnnum;
    }

    public int getMaxTurns(){
        return this.maxturns;
    }

    public void addFocusRegen(int amount){
        this.focusregen += amount;
    }

    public void addMPRegen(int amount){
        this.mpregen += amount;
    }

    public void addTurnNum(int amount){
        this.turnnum += amount;
    }
    
    public void addmaxTurns(int amount){
        this.maxturns += amount;
    }

    public void addEfficieny(double amount){
        this.efficiency += amount;
    }

    public void addlaze(int amount){
        this.laze += amount;
    }

    public void addAP(int amount){
        this.academicpoints += amount;
        if (amount > this.academicpoints) {
            this.academicpoints = 0;
        }
    }

    public void addMP(int amount){
        this.mentalprowess += amount;
        if (this.mentalprowess > MaxMP) {
            this.mentalprowess = MaxMP;
        }
    }
    
    public void addFP(int amount){
        this.focuspoints += amount;
        if (this.focuspoints > MaxFP) {
            this.focuspoints = MaxFP;
        }
    }

    public void playCard(int cardnum){
        drawpile.add(cardnum);
        hand.remove(Integer.valueOf(cardnum));
    }

    public void drawCard(int cardnum){
        drawpile.remove(Integer.valueOf(cardnum));
        hand.add(cardnum);
    }

    public void drawHand(){
        for(int i = 0; i <= 5; i++){
            int drawsize = drawpile.size();
            Random card = new Random();
            int cardnum = drawpile.get(card.nextInt(drawsize));
            hand.add(cardnum);
            drawpile.remove(Integer.valueOf(cardnum));
        }
    }
    
    public void displayHand(){
        CardList.displayCards(hand);
    }

    public void setTrait(int num){
        this.trait = num;
    }
    
    public void getTrait(){
        switch(trait){
            case 1: hand.add(2);
            break;
            default: ;
        }
    }
}
