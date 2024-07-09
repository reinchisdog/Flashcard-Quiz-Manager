import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class PomodoroTimer {
    private Timer timer;
    private boolean isRunning;
    private boolean isPaused;
    private long remainingTime;
    private final JLabel lblTimer;
    private final JTextField workHourField;
    private final JTextField workMinuteField;
    private final JTextField workSecondField;
    private final JTextField breakHourField;
    private final JTextField breakMinuteField;
    private final JTextField breakSecondField;
    private final JButton btnStart;
    private final JButton btnPausePlay;
    private boolean isWorkTime;

    public PomodoroTimer(JLabel lblTimer, JTextField workHourField, JTextField workMinuteField, JTextField workSecondField,
                         JTextField breakHourField, JTextField breakMinuteField, JTextField breakSecondField,
                         JButton btnStart, JButton btnPausePlay) {
        this.lblTimer = lblTimer;
        this.workHourField = workHourField;
        this.workMinuteField = workMinuteField;
        this.workSecondField = workSecondField;
        this.breakHourField = breakHourField;
        this.breakMinuteField = breakMinuteField;
        this.breakSecondField = breakSecondField;
        this.btnStart = btnStart;
        this.btnPausePlay = btnPausePlay;

        initialize();
    }

    private void initialize() {
        btnStart.addActionListener(_ -> {
            if (isRunning || isPaused) {
                restartTimer();
            } else {
                if (validateInput()) {
                    startWorkTimer();
                }
            }
        });

        btnPausePlay.addActionListener(_ -> {
            if (isRunning && !isPaused) {
                pauseTimer();
            } else if (isPaused) {
                resumeTimer();
            }
        });

        InputVerifier verifier = new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                if (input instanceof JTextField textField) {
                    try {
                        int value = Integer.parseInt(textField.getText());
                        return value >= 0 && value < 60;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                }
                return true;
            }
        };

        workHourField.setInputVerifier(verifier);
        workMinuteField.setInputVerifier(verifier);
        workSecondField.setInputVerifier(verifier);
        breakHourField.setInputVerifier(verifier);
        breakMinuteField.setInputVerifier(verifier);
        breakSecondField.setInputVerifier(verifier);
    }

    private boolean validateInput() {
        if (!workHourField.getInputVerifier().verify(workHourField) ||
                !workMinuteField.getInputVerifier().verify(workMinuteField) ||
                !workSecondField.getInputVerifier().verify(workSecondField) ||
                !breakHourField.getInputVerifier().verify(breakHourField) ||
                !breakMinuteField.getInputVerifier().verify(breakMinuteField) ||
                !breakSecondField.getInputVerifier().verify(breakSecondField)) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid numbers between 0 and 59.");
            return false;
        }
        return true;
    }

    private void startWorkTimer() {
        isWorkTime = true;
        isRunning = true;
        isPaused = false;
        btnPausePlay.setText("Pause");
        btnPausePlay.setVisible(true);
        btnStart.setText("Restart");

        int workHours = Integer.parseInt(workHourField.getText());
        int workMinutes = Integer.parseInt(workMinuteField.getText());
        int workSeconds = Integer.parseInt(workSecondField.getText());
        remainingTime = (workHours * 3600L + workMinutes * 60L + workSeconds) * 1000L;

        startTimerTask();
        disableTextFields();
    }

    private void startBreakTimer() {
        isWorkTime = false;
        isRunning = true;
        isPaused = false;
        btnPausePlay.setText("Pause");
        btnPausePlay.setVisible(true);
        btnStart.setText("Restart");

        int breakHours = Integer.parseInt(breakHourField.getText());
        int breakMinutes = Integer.parseInt(breakMinuteField.getText());
        int breakSeconds = Integer.parseInt(breakSecondField.getText());
        remainingTime = (breakHours * 3600L + breakMinutes * 60L + breakSeconds) * 1000L;

        startTimerTask();
    }

    private void startTimerTask() {
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (remainingTime > 0) {
                    remainingTime -= 1000;
                    updateTimerLabel();
                } else {
                    timer.cancel();
                    isRunning = false;
                    notifyUser();
                    if (isWorkTime) {
                        startBreakTimer();
                    } else {
                        startWorkTimer();
                    }
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    private void notifyUser() {
        if (isWorkTime) {
            JOptionPane.showMessageDialog(null, "Work time is over! Time for a break.");
        } else {
            JOptionPane.showMessageDialog(null, "Break time is over! Time to get back to work.");
        }
    }

    private void pauseTimer() {
        if (!isRunning || isPaused) {
            return;
        }
        isPaused = true;
        btnPausePlay.setText("Resume");
        timer.cancel();
    }

    private void resumeTimer() {
        if (!isPaused) {
            return;
        }
        isPaused = false;
        btnPausePlay.setText("Pause");

        startTimerTask();
    }

    private void restartTimer() {
        if (isRunning) {
            timer.cancel();
        }
        isRunning = false;
        isPaused = false;
        isWorkTime = true;
        btnPausePlay.setText("Pause");
        btnPausePlay.setVisible(false);
        btnStart.setText("Start");
        enableTextFields();
        int workHours = Integer.parseInt(workHourField.getText());
        int workMinutes = Integer.parseInt(workMinuteField.getText());
        int workSeconds = Integer.parseInt(workSecondField.getText());
        lblTimer.setText(String.format("%02d:%02d:%02d", workHours, workMinutes, workSeconds));
    }

    private void updateTimerLabel() {
        long hours = remainingTime / 3600000;
        long minutes = (remainingTime % 3600000) / 60000;
        long seconds = (remainingTime % 60000) / 1000;
        lblTimer.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }

    private void disableTextFields() {
        workHourField.setEnabled(false);
        workMinuteField.setEnabled(false);
        workSecondField.setEnabled(false);
        breakHourField.setEnabled(false);
        breakMinuteField.setEnabled(false);
        breakSecondField.setEnabled(false);
    }

    private void enableTextFields() {
        workHourField.setEnabled(true);
        workMinuteField.setEnabled(true);
        workSecondField.setEnabled(true);
        breakHourField.setEnabled(true);
        breakMinuteField.setEnabled(true);
        breakSecondField.setEnabled(true);
    }
}