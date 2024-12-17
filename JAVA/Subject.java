package JAVA;
import java.util.Random;
public class Subject {
    protected int tempshield;
    protected int items;
    protected int maxitems;
    public int subjects;
    public int subjectnum;
    
    Subject(int subjectnum){
        this.subjectnum = subjectnum;
        Random number = new Random();
        this.tempshield = 0;
        this.maxitems = number.nextInt(15-10) + 10;
        this.items = this.maxitems;
    }

    public void performAction(Avatar avatar){
        Random action = new Random();
        Random numofaction = new Random();
        int prevaction = 8;
        for(int i = numofaction.nextInt(1)+1;i > 0; i--){
            int actionnum = action.nextInt(7);
            if(prevaction == actionnum){
                actionnum += 1;
            }
            prevaction = actionnum;
        //String startmessage = ("Subject 1 has Induced Anxiety, your MP was reduced by " + (avatar.getTurnNum()+2));
        //avatar.addMP(avatar.getTurnNum());
        String actionmessage = "action";
        switch(actionnum + 1){
            case 1: {avatar.addEfficieny(-(Main.avatar.getEfficiency()*0.25));
                    actionmessage = "Project " + String.valueOf(this.subjectnum) +  " has caused Mental Block, AP will be 0.25% less today ";}
            break;
            case 2: {actionmessage = "Project " + String.valueOf(this.subjectnum) +  " has induced Decision Paralysis, you have one less turn";
                    avatar.addmaxTurns(-1);}
            break;
            case 3: {actionmessage = "Project " + String.valueOf(this.subjectnum) +  " was off topic, it gained 3 difficulty";
                    this.tempshield += 7;}
            break;
            case 4: {actionmessage = "Project " + String.valueOf(this.subjectnum) +  " seems fun";
                    avatar.addMP(1);}
            break;
            case 5: {actionmessage = "Bonus question1!, project " + String.valueOf(this.subjectnum) +  " loses an item";
                    this.addItems(-1);}
            break;
            case 6: {actionmessage = "Essaying, project " + String.valueOf(this.subjectnum) +  " has gained 2 difficulty";
                    this.tempshield += 5;}
            break;
            case 7: {actionmessage = "You have low ink, you lost 2 focus points";
                    avatar.addFP(-2);}
            break;
        }
        Main.actionhistory.add(actionmessage);
        }
        
    }

    public void induceAnxiety(){
        Main.avatar.addMP(-3);
        Main.actionhistory.add("Project " + this.subjectnum + " has induced anxiety, MP -3.");
    }

    public void addSubjects(int amount){
        subjects += amount;
    }
    
    public void setItems(int amount){
        this.items = amount;
    }

    public int getItems(){
        return this.items;
    }

    public int getMaxItems(){
        return this.maxitems;
    }

    public void addItems(int amount){
        while(-amount > 0){
            if(this.tempshield > 0){
                this.tempshield += amount;
                amount -= this.tempshield;
            }
            else{
                this.items += amount;
                amount -= this.items;
            }
        }
        this.tempshield = 0;
    }
}
