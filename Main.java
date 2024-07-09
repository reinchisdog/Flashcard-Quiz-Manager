public class Main {
    static void Program_Run(){
        FlashcardGUI progStart = new FlashcardGUI();
        progStart.setTitle("Flashcard-Quiz Manager");
        progStart.setSize(1280, 720);
        progStart.setResizable(false);
        progStart.setLocationRelativeTo(null);
        progStart.setDefaultCloseOperation(progStart.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Program_Run();
    }
}
