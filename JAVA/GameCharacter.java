package JAVA;
public class GameCharacter {
    private int x, y;  // Character's position
    private char symbol; // Symbol representing the character

    public GameCharacter(int x, int y, char symbol) {
        this.x = x;
        this.y = y;
        this.symbol = symbol;
    }

    // Move the character
    public void move(int dx, int dy) {
        clear(); // Clear the previous position
        x += dx;
        y += dy;
        draw(); // Draw at the new position
    }

    // Clear the character from its current position
    public void clear() {
        GameScreen.printAt(y, x, " ",1);
    }

    // Draw the character at its current position
    public void draw() {
        GameScreen.printAt(y, x, String.valueOf(symbol),1);
    }
}
