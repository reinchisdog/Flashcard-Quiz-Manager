import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Question {
    private String question;
    private String answer;

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}

class Questions {
    private ArrayList<Question> questions = new ArrayList<>();

    public Questions(String currPathFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(currPathFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String question = line.trim();
                String correctAnswer = reader.readLine().trim();
                questions.add(new Question(question, correctAnswer));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }
}

public class QuizGUI {
    private ArrayList<Question> questions;
    private int currentQuestionIndex;
    private JDialog quizDialog;
    private JTextArea questionTextArea;
    private JTextField answerField;
    private JButton nextButton;

    public void run(String filePath) {
        currentQuestionIndex = 0;

        quizDialog = new JDialog((Frame) null, "Quiz Application");
        questions = new Questions(filePath).getQuestions();
        quizDialog.setSize(400, 200);
        quizDialog.setResizable(false);
        quizDialog.setLayout(new GridBagLayout());
        quizDialog.getContentPane().setBackground(new Color(229, 232, 234));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Panel for the question label (now using JTextArea)
        JPanel questionPanel = new JPanel(new BorderLayout());
        questionPanel.setBackground(new Color(229, 232, 234));
        questionTextArea = new JTextArea("Question");
        questionTextArea.setLineWrap(true);
        questionTextArea.setWrapStyleWord(true);
        questionTextArea.setEditable(false);
        questionTextArea.setBorder(BorderFactory.createEmptyBorder());
        questionTextArea.setBackground(null);
        questionTextArea.setFont(new Font("Arial", Font.BOLD, 15));
        questionPanel.add(questionTextArea, BorderLayout.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        quizDialog.add(questionPanel, gbc);

        // Panel for the answer field
        JPanel answerPanel = new JPanel(new BorderLayout());
        answerPanel.setBackground(new Color(229, 232, 234));
        answerField = new JTextField();
        answerField.setFont(new Font("Helvetica", Font.PLAIN, 14));
        answerField.setPreferredSize(new Dimension(200, 30));
        answerPanel.add(answerField, BorderLayout.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.weighty = 0;
        quizDialog.add(answerPanel, gbc);

        // Panel for the next button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(229, 232, 234));
        nextButton = new JButton("NEXT");
        nextButton.setPreferredSize(new Dimension(100, 30));
        nextButton.setBackground(new Color(27, 29, 31));
        nextButton.setForeground(Color.WHITE);
        nextButton.addActionListener(_ -> {
            checkAnswer();
            currentQuestionIndex++;
            if (currentQuestionIndex < questions.size()) {
                loadQuestion(currentQuestionIndex);
            } else {
                JOptionPane.showMessageDialog(null, "Quiz finished!");
                quizDialog.dispose();
            }
        });
        buttonPanel.add(nextButton);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.weighty = 0;
        quizDialog.add(buttonPanel, gbc);

        loadQuestion(currentQuestionIndex);

        quizDialog.setLocationRelativeTo(null);
        quizDialog.setModal(true);
        quizDialog.setVisible(true);
    }

    private void loadQuestion(int questionIndex) {
        Question question = questions.get(questionIndex);
        questionTextArea.setText(question.getQuestion());
        answerField.setText("");
    }

    private void checkAnswer() {
        Question question = questions.get(currentQuestionIndex);
        String correctAnswer = question.getAnswer();
        String userAnswer = answerField.getText().trim();

        if (userAnswer.equalsIgnoreCase(correctAnswer)) {
            JOptionPane.showMessageDialog(quizDialog, "Correct!");
        } else {
            JOptionPane.showMessageDialog(quizDialog, "Incorrect. The correct answer is: " + correctAnswer);
        }
    }
}
