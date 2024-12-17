package JAVA;

import java.util.ArrayList;
import java.util.Random;

public class ToolKit {
        // Helper method to pad a string with spaces to the specified length
        static String padRight(String text, int length) {
            if (text.length() < length) {
                return text + " ".repeat(length - text.length());
            }
            return text;
        }

        public static String centerString(int totalWidth, String text) {
            int padding = (totalWidth - text.length()) / 2;
            String centeredText = String.format("%" + padding + "s%s%" + padding + "s", "", text, "");
            return centeredText;
        }
    
        public static ArrayList<Integer> generateRandomNumbers(int size, int min, int max) {
        ArrayList<Integer> numbers = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            int randomNumber = random.nextInt(max - min + 1) + min;
            numbers.add(randomNumber);
        }

        return numbers;
    }
}
