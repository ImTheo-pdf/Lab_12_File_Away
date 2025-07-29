import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
public class FileInspector {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("src"));
        int result = chooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            Path filePath = chooser.getSelectedFile().toPath();
            ArrayList<String> lines = new ArrayList<>();
            int lineCount = 0, wordCount = 0, charCount = 0;

            try {
                lines.addAll(Files.readAllLines(filePath));

                for (String line : lines) {
                    System.out.println(line);
                    lineCount++;
                    wordCount += line.trim().isEmpty() ? 0 : line.trim().split("\\s+").length;
                    charCount += line.length();
                }

                System.out.println("\n--- File Summary Report ---");
                System.out.println("File: " + filePath.getFileName());
                System.out.println("Total Lines: " + lineCount);
                System.out.println("Total Words: " + wordCount);
                System.out.println("Total Characters: " + charCount);

            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        } else {
            System.out.println("No file selected.");
        }
    }
}
