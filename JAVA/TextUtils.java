package JAVA;

import java.util.ArrayList;

public class TextUtils {

    /**
     * Justifies the input text into an array of strings, each of a specified width.
     * @param text The input string to justify.
     * @param width The maximum width of each line.
     * @return A String array containing justified lines.
     */
    public static String[] justifyText(String text, int width) {
        // Split the text into words
        String[] words = text.split("\\s+");
        ArrayList<String> lines = new ArrayList<>();

        StringBuilder currentLine = new StringBuilder();
        for (String word : words) {
            // If adding the word exceeds the width, start a new line
            if (currentLine.length() + word.length() + 1 > width) {
                lines.add(justifyLine(currentLine.toString().trim(), width));
                currentLine.setLength(0); // Clear the StringBuilder
            }

            // Add the word to the current line
            if (currentLine.length() > 0) {
                currentLine.append(" ");
            }
            currentLine.append(word);
        }

        // Add the last line (left-aligned, not justified)
        if (currentLine.length() > 0) {
            lines.add(currentLine.toString());
        }

        // Convert ArrayList to String array
        return lines.toArray(new String[0]);
    }

    /**
     * Justifies a single line of text to fit the specified width.
     * @param line The line to justify.
     * @param width The desired width.
     * @return A justified line as a string.
     */
    private static String justifyLine(String line, int width) {
        String[] words = line.split("\\s+");
        if (words.length == 1) {
            // If only one word, return it left-aligned
            return line;
        }

        int totalSpaces = width - line.replace(" ", "").length();
        int spacesBetweenWords = totalSpaces / (words.length - 1);
        int extraSpaces = totalSpaces % (words.length - 1);

        StringBuilder justified = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            justified.append(words[i]);
            if (i < words.length - 1) {
                // Add spaces between words
                justified.append(" ".repeat(spacesBetweenWords));
                if (extraSpaces > 0) {
                    justified.append(" ");
                    extraSpaces--;
                }
            }
        }

        return justified.toString();
    }

    public static void main(String[] args) {
        String text = "This is a test string to demonstrate how text justification works in Java.";
        int width = 20;

        String[] justifiedLines = justifyText(text, width);
        for (String line : justifiedLines) {
            System.out.println("|" + line + "|"); // Print lines with borders for visibility
        }
    }
}
