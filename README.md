Executable is in Main.java

                            _________ __            .___             __      _________.__               
                           /   _____//  |_ __ __  __| _/____   _____/  |_   /   _____/|__| _____        
                           \_____  \\   __\  |  \/ __ |/ __ \ /    \   __\  \_____  \ |  |/     \       
                           /        \|  | |  |  / /_/ \  ___/|   |  \  |    /        \|  |  Y Y  \      
                          /_______  /|__| |____/\____ |\___  >___|  /__|   /_______  /|__|__|_|  /      
                                  \/                 \/    \/     \/               \/          \/         

         _________     _____ __________________      ________    _____      _____  ___________
\_   ___ \   /  _  \\______   \______ \    /  _____/   /  _  \    /     \ \_   _____/
/    \  \/  /  /_\  \|       _/|    |  \  /   \  ___  /  /_\  \  /  \ /  \ |    __)_ 
\     \____/    |    \    |   \|    `   \ \    \_\  \/    |    \/    Y    \|        \
 \______  /\____|__  /____|_  /_______  /  \______  /\____|__  /\____|__  /_______  /
        \/         \/       \/        \/          \/         \/         \/        \/ 





         


        Sustainable Development Goal Number 4: Quality Education
        This program was made to simulate a student's life in the form of an entertaining card game!
        You play as an avatar defined in Avatar.java
        Actions are in the form of a card defined in Actions.java
        Subjects/Enemies are in the form of books, 1 book per day for 4 days
        turn number is represented by time, that's 24 hours! but only 7 to 15 are used as per game rules

        OOP Concepts:
        Abstraction: Use of abstract classes and methods such as the abstract class "Actions" and abstract method "getMessage"
        Polymorphism and Inheritance: use of the super method for classes that extend the class "Actions" to do actions different from other inheriting classes
        Encapsulation: Use of setters and getters for values in the classes "Avatar" and "Subjects"

        DATABASE:
        With the use of a database, the class actions which defines the values for each playable card in the game is fed values taken from the database
        to create object instances, These values are defined in the 'Cards' table
        Another table is the Decks Table which contains the saved decks made by the user through the application, it can accomodate 10 decks at most.
        The 'DeckName' column will be displayed in a menu allowing the user to select one to edit existing values and use the contents in the game..
        If less than 10 rows are present in the database, the application will display a default string indicating that the user can select it and input values to add to the table.
        
        Gameplay:
        The gameplay aspect is the main part of the application.
        Once a deck is selected, the game can be loaded in where the game screen is displayed
        In the game shows your character, enemies, turn number and day, playable cards, and the action history
        Day: days represent rounds, the game has 4 rounds in total
        Turns: the game display the numbers 1-24 to imitate the hours in a day, only 7-15 are usually accessible and once turn 15 is passed, the next day will start and the turn will           restart to 7
        Avatar: Your character, has the fields AP(attack), MP(Health), FP(Energy). AP is used for damage calculation at the end of a round
        Enemies: The enemies have items as their hp set randomly from 10-15, at the end of a day, if they are still above 0 items, the character will take 3 damage.
        Cards: Performs different actions, they have different colors signify their purpose, Red(AP), Blue(FP, Green(MP). Some cards will advance the turnnumber. energy required to             play are also shown in the card display.
        You Win: Beat all enemies before the end of day 4.
        You lose: MP reaches 0 or Enemies are left after day 4.
        
