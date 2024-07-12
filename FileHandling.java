import java.awt.*;
import java.io.*;
import java.nio.file.*;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class FileHandling {
    //
    private String currPathFile;
    private String[] fcQuestions;
    private String[] fcAnswers;
    private int current;
    private boolean isStateQuestion;
    private File tempFile;
    private boolean isTempFileExist;

     // Constructor
    public FileHandling() {
        this.current = 0;
        this.isStateQuestion = false;
        this.isTempFileExist = false;
    }

    // Getters and Setters
    public String getCurrPathFile() {
        return currPathFile;
    }

    public void setCurrPathFile(String currPathFile) {
        this.currPathFile = currPathFile;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public void incrementCurrent(){
        this.current++;
    }

    public void decrementCurrent(){
        this.current--;
    }

    public boolean isStateQuestion() {
        return isStateQuestion;
    }

    public void setStateQuestion(boolean stateQuestion) {
        isStateQuestion = stateQuestion;
    }

    public File getTempFile() {
        return tempFile;
    }

    public boolean isTempFileExist() {
        return isTempFileExist;
    }

    public void setTempFileExist(boolean tempFileExist) {
        isTempFileExist = tempFileExist;
    }

    // Method that checks if the current selected file is empty --------------------------------------------------------
    boolean isFileEmpty() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(currPathFile));
        if (br.readLine() == null) {
            System.out.println("File is now Empty");
            return true;
        } else {
            return false;
        }
    }

    // Method for creating a new empty file ----------------------------------------------------------------------------
    void newFile() {
        String tempFilePath = "temp.txt";
        tempFile = new File(tempFilePath);
        try {
            if (tempFile.createNewFile()) {
                System.out.println("File created: " + tempFile.getName());
            } else {
                System.out.println("File already exists");
            }
            isTempFileExist = true;
            currPathFile = tempFilePath;
            current = 0;
            isStateQuestion = false;
        } catch (IOException e) {
            System.err.println("An error occurred in creating a file: " + e.getMessage());
        }
    }

    // Method for saving the current file ------------------------------------------------------------------------------
    void saveFile(String savePathFile) {
        File saveFile = new File(savePathFile);
        Path sourcePath = Paths.get(currPathFile);
        Path targetPath = saveFile.toPath();

        try {
            Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File Saved");
        } catch (IOException e) {
            System.err.println("An error occurred in saving the file: " + e.getMessage());
        }
    }

    //Method for a save warning message before performing the next action ---------------------------------------------
    int savePopup() {
        final int[] result = {0};

        JDialog saveDialog = new JDialog((Frame) null);
        saveDialog.setLayout(new BorderLayout());
        saveDialog.getContentPane().setBackground(new Color(163, 201, 218)); // Set background color

        JLabel lblQuestion;
        JPanel panelQA, panelBtn;
        JButton btnSaveAs, btnDontSaveAs, btnSvCancel;

        panelQA = new JPanel(new BorderLayout());
        saveDialog.add(panelQA, BorderLayout.CENTER);

        lblQuestion = new JLabel(
                "<html><div style='text-align: center; width: 300px;'>" +
                        "Would you like to save the current file first before executing your stated action?" +
                        "</div></html>");
        lblQuestion.setBorder(new EmptyBorder(32, 0, 32, 0));
        lblQuestion.setVerticalAlignment(SwingConstants.CENTER);
        lblQuestion.setHorizontalAlignment(SwingConstants.CENTER);
        panelQA.add(lblQuestion, BorderLayout.CENTER);

        panelBtn = new JPanel(new GridBagLayout());
        saveDialog.add(panelBtn, BorderLayout.SOUTH);
        GridBagConstraints gbc = new GridBagConstraints();

        // Cancel Button (leftmost)
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        btnSvCancel = new JButton("Cancel");
        panelBtn.add(btnSvCancel, gbc);

        // Space between buttons
        gbc.insets = new Insets(0, 10, 0, 0); // Space to the left of buttons

        // Save Button (rightmost)
        gbc.gridx = 1;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.EAST;
        btnSaveAs = new JButton("Save");
        panelBtn.add(btnSaveAs, gbc);

        // Don't Save Button (rightmost)
        gbc.gridx = 2;
        gbc.weightx = 0.0;
        btnDontSaveAs = new JButton("Don't Save");
        panelBtn.add(btnDontSaveAs, gbc);

        // Add padding to the button panel
        panelBtn.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add space around the buttons

        btnSaveAs.addActionListener(e -> {
            result[0] = 1;
            saveDialog.dispose();
        });

        btnDontSaveAs.addActionListener(e -> {
            result[0] = 2;
            saveDialog.dispose();
        });

        btnSvCancel.addActionListener(e -> saveDialog.dispose());

        saveDialog.setSize(420, 184);
        saveDialog.setResizable(false);
        saveDialog.setLocationRelativeTo(null);
        saveDialog.setModal(true);
        saveDialog.setVisible(true);

        return result[0];
    }


    // Function for counting the lines in a File
    int countFile() {
        int fcTotal = 0;
        try {
            File file = new File(currPathFile);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                sc.nextLine();
                fcTotal++;
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        }
        return fcTotal;
    }

    // Function for reading a File
    void readFile() {
        try {
            File file = new File(currPathFile);
            Scanner sc = new Scanner(file);

            System.out.println("Reading " + currPathFile);

            int lineCount = countFile();

            fcQuestions = new String[lineCount / 2];
            fcAnswers = new String[lineCount / 2];

            int qIndex = 0, aIndex = 0;
            int i = 0;

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (i % 2 == 0) {
                    fcQuestions[qIndex++] = line;
                } else {
                    fcAnswers[aIndex++] = line;
                }
                i++;
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        }
    }

    // Function to get content based on current state
    String getContent() {
        if (currPathFile.isEmpty() || current < 0) {
            return "";
        } else if (!isStateQuestion) {
            return fcQuestions[current];
        } else {
            return fcAnswers[current];
        }
    }

    // Function to insert a new flashcard
    public boolean insertBox() {
        final boolean[] isInserted = {false};

        JDialog insertDialog = new JDialog((Frame) null, "Flashcard INSERT");
        insertDialog.setLayout(new BorderLayout());
        JLabel lblQuestion, lblAnswer;
        JPanel panelQA, panelBtn;
        JTextField tfQuestion, tfAnswer;
        JButton btnDbInsert, btnDbCancel;

        panelQA = new JPanel(new FlowLayout());
        insertDialog.add(panelQA, BorderLayout.CENTER);

        lblQuestion = new JLabel("Enter a Question: ");
        lblQuestion.setVerticalAlignment(SwingConstants.CENTER);
        panelQA.add(lblQuestion);

        tfQuestion = new JTextField(28);
        panelQA.add(tfQuestion);

        lblAnswer = new JLabel("Enter the Answer: ");
        lblAnswer.setVerticalAlignment(SwingConstants.CENTER);
        panelQA.add(lblAnswer);

        tfAnswer = new JTextField(28);
        panelQA.add(tfAnswer);

        panelBtn = new JPanel(new FlowLayout());
        insertDialog.add(panelBtn, BorderLayout.SOUTH);

        btnDbInsert = new JButton("Insert");
        btnDbInsert.setSize(25, 30);
        panelBtn.add(btnDbInsert);

        btnDbCancel = new JButton("Cancel");
        btnDbCancel.setSize(25, 30);
        panelBtn.add(btnDbCancel);

        // Insert dialog box actions
        btnDbInsert.addActionListener(_ -> {
            try {
                if (!tfQuestion.getText().isEmpty() && !tfAnswer.getText().isEmpty()) {
                    insertContent(tfQuestion.getText(), tfAnswer.getText());
                    isInserted[0] = true;
                }
            } catch (IOException ex) {
                System.err.println("An error occurred in writing to the file: " + ex.getMessage());
            }
            insertDialog.dispose();
        });

        btnDbCancel.addActionListener(_ -> insertDialog.dispose());

        insertDialog.setSize(420, 184);
        insertDialog.setResizable(false);
        insertDialog.setLocationRelativeTo(null);
        insertDialog.setModal(true);
        insertDialog.setVisible(true);

        return isInserted[0];
    }

    // Function to insert content into the file
    void insertContent(String question, String answer) throws IOException {
        try (FileWriter fw = new FileWriter(currPathFile, true)) {
            fw.write(question + "\n");
            fw.write(answer + "\n");
            readFile();
        } catch (IOException e) {
            System.err.println("An error occurred in writing to the file: " + e.getMessage());
        }
    }

    // Function to update a flashcard
    void updateBox() {
        JDialog updateDialog = new JDialog((Frame) null, "Flashcard UPDATE");
        updateDialog.setLayout(new BorderLayout());
        JLabel lblQuestion, lblAnswer;
        JPanel panelQA, panelBtn;
        JTextField tfQuestion, tfAnswer;
        JButton btnDbUpdate, btnDbCancel;

        panelQA = new JPanel(new FlowLayout());
        updateDialog.add(panelQA, BorderLayout.CENTER);

        lblQuestion = new JLabel("Update the Question: ");
        lblQuestion.setVerticalAlignment(SwingConstants.CENTER);
        panelQA.add(lblQuestion);

        tfQuestion = new JTextField(fcQuestions[current], 28);
        panelQA.add(tfQuestion);

        lblAnswer = new JLabel("Update the Answer: ");
        lblAnswer.setVerticalAlignment(SwingConstants.CENTER);
        panelQA.add(lblAnswer);

        tfAnswer = new JTextField(fcAnswers[current], 28);
        panelQA.add(tfAnswer);

        panelBtn = new JPanel(new FlowLayout());
        updateDialog.add(panelBtn, BorderLayout.SOUTH);

        btnDbUpdate = new JButton("Update");
        btnDbUpdate.setSize(25, 30);
        panelBtn.add(btnDbUpdate);

        btnDbCancel = new JButton("Cancel");
        btnDbCancel.setSize(25, 30);
        panelBtn.add(btnDbCancel);

        // Update dialog box actions
        btnDbUpdate.addActionListener(_ -> {
            if (!tfQuestion.getText().isEmpty() && !tfAnswer.getText().isEmpty()) {
                fcQuestions[current] = tfQuestion.getText();
                fcAnswers[current] = tfAnswer.getText();
                updateFile();
                readFile();
            }
            updateDialog.dispose();
        });

        btnDbCancel.addActionListener(_ -> updateDialog.dispose());

        updateDialog.setSize(420, 184);
        updateDialog.setResizable(false);
        updateDialog.setLocationRelativeTo(null);
        updateDialog.setModal(true);
        updateDialog.setVisible(true);
    }

    // Function to update the file with new content
    void updateFile() {
        String tempPathFile = "tempUpdate.txt";
        File oldFile = new File(currPathFile);
        File tempFile = new File(tempPathFile);

        try (FileWriter fw = new FileWriter(tempFile)) {
            for (int i = 0; i < fcQuestions.length; i++) {
                fw.write(fcQuestions[i] + "\n");
                fw.write(fcAnswers[i] + "\n");
            }
        } catch (IOException e) {
            System.err.println("An error occurred in updating the file: " + e.getMessage());
        }

        if (oldFile.delete()) {
            if (!tempFile.renameTo(new File(currPathFile))) {
                System.err.println("Failed to rename the temporary file.");
            }
        } else {
            System.err.println("Failed to delete the old file.");
        }
    }

    // Function to delete a flashcard
    void deleteBox() {
        JDialog deleteDialog = new JDialog((Frame) null, "Flashcard DELETE");
        deleteDialog.setLayout(new BorderLayout());
        JLabel lblQuestion;
        JPanel panelQA, panelBtn;
        JButton btnDbDelete, btnDbCancel;

        panelQA = new JPanel(new BorderLayout());
        deleteDialog.add(panelQA, BorderLayout.CENTER);

        lblQuestion = new JLabel(
                "<html><div style='text-align: center; width: 300px;'>You are about to <b>DELETE</b> the current " +
                "Flashcard (Card " + (current + 1) + ") that is shown on the screen! " +
                "Would you like to continue the deletion?</div></html>");
        lblQuestion.setBorder(new EmptyBorder(32, 0, 32, 0));
        lblQuestion.setVerticalAlignment(SwingConstants.CENTER);
        lblQuestion.setHorizontalAlignment(SwingConstants.CENTER);
        panelQA.add(lblQuestion, BorderLayout.CENTER);

        panelBtn = new JPanel(new FlowLayout());
        deleteDialog.add(panelBtn, BorderLayout.SOUTH);

        btnDbDelete = new JButton("Delete");
        btnDbDelete.setSize(25, 30);
        panelBtn.add(btnDbDelete);

        btnDbCancel = new JButton("Cancel");
        btnDbCancel.setSize(25, 30);
        panelBtn.add(btnDbCancel);

        // Delete dialog box actions
        btnDbDelete.addActionListener(_ -> {
            deleteContent();
            updateFile();
            deleteDialog.dispose();
        });

        btnDbCancel.addActionListener(_ -> deleteDialog.dispose());

        deleteDialog.setSize(420, 184);
        deleteDialog.setResizable(false);
        deleteDialog.setLocationRelativeTo(null);
        deleteDialog.setModal(true);
        deleteDialog.setVisible(true);
    }

    // Function to delete content from the arrays
    void deleteContent() {
        if (fcQuestions.length == 0 || fcAnswers.length == 0) {
            return;
        }

        String[] tempQuestions = new String[fcQuestions.length - 1];
        String[] tempAnswers = new String[fcAnswers.length - 1];

        for (int i = 0, k = 0; i < fcQuestions.length; i++) {
            if (i == current) {
                continue;
            }
            tempQuestions[k] = fcQuestions[i];
            tempAnswers[k] = fcAnswers[i];
            k++;
        }

        fcQuestions = tempQuestions;
        fcAnswers = tempAnswers;

        if (current >= fcQuestions.length) {
            current = fcQuestions.length - 1;
        }
    }
}
