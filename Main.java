import javax.swing.*;
import java.awt.*;

public class Main {
    static void Program_Run(){
        ImageIcon icon = new ImageIcon("icons/logo.png");
        Image image = icon.getImage();

        FlashcardGUI progStart = new FlashcardGUI();
        progStart.setIconImage(image);
        progStart.setTitle("Flash Focus: Flashcard-Quiz Manager");
        progStart.setSize(1280, 720);
        progStart.setResizable(false);
        progStart.setLocationRelativeTo(null);
        progStart.setDefaultCloseOperation(progStart.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Program_Run();
    }
}
