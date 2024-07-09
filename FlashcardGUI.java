import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.Paths;

public class FlashcardGUI extends JFrame{
    //Declaration of Some of the GUI Components
    private final JMenuItem saveasFile;
    private final JMenuItem closeFile;

    private final JPanel out_panel;
    private final JPanel p1;
    private final GridBagConstraints gbc_op;

    private final JLabel fcText;

    private final JLabel lblFileName;
    private final JLabel lblItems;
    private final JButton btnInsert;
    private final JButton btnDelete;
    private final JButton btnUpdate;
    private final JButton btnQuiz;
    private final JButton btnState;

    private final JTextField workHourField;
    private final JTextField workMinuteField;
    private final JTextField workSecondField;
    private final JTextField breakHourField;
    private final JTextField breakMinuteField;
    private final JTextField breakSecondField;
    private final JButton btnStart;


    //Method that disables some GUI components when there is no file opened
    private void FlashcardClose(){
        //Panel 1
        out_panel.remove(p1);
        out_panel.revalidate();
        out_panel.repaint();

        //Menu
        saveasFile.setEnabled(false);
        closeFile.setEnabled(false);

        //Panel 2
        btnInsert.setEnabled(false);
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
        btnQuiz.setEnabled(false);
        workHourField.setEnabled(false);
        workMinuteField.setEnabled(false);
        workSecondField.setEnabled(false);
        breakHourField.setEnabled(false);
        breakMinuteField.setEnabled(false);
        breakSecondField.setEnabled(false);
        btnStart.setEnabled(false);
        btnState.setEnabled(false);
    }

    //Method the enables some GUI components when a file is opened
    private void FlashcardOpen(){
        //Panel 1
        gbc_op.fill = GridBagConstraints.BOTH;
        gbc_op.gridx = 0;
        gbc_op.weighty = 1.0;
        out_panel.add(p1, gbc_op);
        out_panel.revalidate();
        out_panel.repaint();

        //Menu
        saveasFile.setEnabled(true);
        closeFile.setEnabled(true);

        //Panel 2
        btnInsert.setEnabled(true);
        btnUpdate.setEnabled(true);
        btnDelete.setEnabled(true);
        btnQuiz.setEnabled(true);
        workHourField.setEnabled(true);
        workMinuteField.setEnabled(true);
        workSecondField.setEnabled(true);
        breakHourField.setEnabled(true);
        breakMinuteField.setEnabled(true);
        breakSecondField.setEnabled(true);
        btnStart.setEnabled(true);
        btnState.setEnabled(true);
    }


    //Method that enables some GUI components when a file is not empty
    private void FlashcardNotEmpty(){
        btnUpdate.setEnabled(true);
        btnDelete.setEnabled(true);
        btnQuiz.setEnabled(true);
        btnState.setEnabled(true);
    }

    //Method that disables some GUI components when a file is empty
    private void FlashcardEmpty(){
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
        btnQuiz.setEnabled(false);
        btnState.setText(" \u200E ");
        btnState.setEnabled(false);
        fcText.setText("");
        lblItems.setText("");
    }

    //Main GUI Components Declaration and Configuration ----------------------------------------------------------------
    public FlashcardGUI(){
        FileHandling fh = new FileHandling();
        QuizGUI qg = new QuizGUI();

        //Menu Panel of the GUI ----------------------------------------------------------------------------------------
        //Creates the Menu Bar of the Application
        JMenuBar menuBar = new JMenuBar();

        //Creates the File Menu for the Menu Bar
        JMenu fileMenu = new JMenu("   File   ");
        fileMenu.setFont(new Font("Helvetica", Font.PLAIN, 12));
        menuBar.add(fileMenu);

        //Creates Menu Items for File Menu
        JMenuItem newFile = new JMenuItem("New File   ", KeyEvent.VK_N);
        newFile.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));

        saveasFile = new JMenuItem("Save as   ", KeyEvent.VK_S);
        saveasFile.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        saveasFile.setEnabled(false);

        JMenuItem openFile = new JMenuItem("Open File   ", KeyEvent.VK_O);
        openFile.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));

        closeFile = new JMenuItem("Close File   ", KeyEvent.VK_C);
        closeFile.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        closeFile.setEnabled(false);

        JMenuItem exitProg = new JMenuItem("Exit Program", KeyEvent.VK_E);
        exitProg.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));

        fileMenu.add(newFile);
        fileMenu.add(saveasFile);
        fileMenu.add(openFile);
        fileMenu.addSeparator();
        fileMenu.add(closeFile);
        fileMenu.add(exitProg);





        //Outer Panel Configuration ------------------------------------------------------------------------------------
        GridBagLayout op_layout, p1_layout, p2_layout;

        out_panel = new JPanel();
        op_layout = new GridBagLayout();
        op_layout.columnWidths = new int[]{854, 426};
        out_panel.setLayout(op_layout);

        gbc_op = new GridBagConstraints();

        //Panel 1 (Left Section of the System) -------------------------------------------------------------------------
        p1 = new JPanel();
        p1.setBackground(new Color(232, 232, 232));

            //Panel 1 Layout
        p1_layout = new GridBagLayout();
        p1_layout.columnWidths = new int[]{100, 654, 100};
        p1_layout.rowHeights = new int[]{30, 100, 420, 75, 75};
        p1.setLayout(p1_layout);

        GridBagConstraints gbc_p1 = new GridBagConstraints();


            //Panel 1 Items
                //P1 - Button for Traversing to Left
        JButton btnArrowLeft = new JButton("<");
        btnArrowLeft.setMargin(new Insets(0, 5, 0, 5));
        gbc_p1.fill = GridBagConstraints.VERTICAL;
        gbc_p1.anchor = GridBagConstraints.EAST;
        gbc_p1.gridx = 0;
        gbc_p1.gridy = 2;
        p1.add(btnArrowLeft, gbc_p1);

                //P1 - Button for Traversing to Right
        JButton btnArrowRight = new JButton(">");
        btnArrowRight.setMargin(new Insets(0, 5, 0, 5));
        gbc_p1.fill = GridBagConstraints.VERTICAL;
        gbc_p1.anchor = GridBagConstraints.WEST;
        gbc_p1.gridx = 2;
        gbc_p1.gridy = 2;
        p1.add(btnArrowRight, gbc_p1);

                //P1 - Panel for Showing the Content of the Flashcard
        JPanel flashCard = new JPanel(new BorderLayout());
        flashCard.setBackground(Color.white);

        fcText = new JLabel();
        fcText.setFont(new Font("Arial", Font.BOLD, 22));
        fcText.setBorder(new EmptyBorder(32, 32, 32, 32));

        fcText.setText("");
        fcText.setHorizontalAlignment(SwingConstants.CENTER);

        gbc_p1.fill = GridBagConstraints.BOTH;
        gbc_p1.gridx = 1;
        gbc_p1.gridy = 2;
        flashCard.add(fcText, BorderLayout.CENTER);
        p1.add(flashCard, gbc_p1);



        //Panel 2 (Right Section of the System) ------------------------------------------------------------------------
        JPanel p2 = new JPanel();
        p2.setBackground(new Color(191, 191, 191));
        gbc_op.fill = GridBagConstraints.BOTH;
        gbc_op.gridx = 1;
        gbc_op.weighty = 1.0;

            //Panel 2 Layout
        p2_layout = new GridBagLayout();
        p2_layout.columnWidths = new int[]{27, 124, 124, 124, 27};
        p2_layout.rowHeights = new int[]{15, 52, 165, 68, 180, 75, 0};
        p2.setLayout(p2_layout);

        GridBagConstraints gbc_p2 = new GridBagConstraints();
        gbc_p2.fill = GridBagConstraints.BOTH;
        gbc_p2.weightx = 1.0;
        gbc_p2.weighty = 1.0;
        gbc_p2.insets = new Insets(10,10,10,10);

            //Panel 2 Items
        TitledBorder tbFcOptions, tbQzOptions, tbTmOptions, tbNvOptions;
        Font panelTitleFont = new Font("Arial", Font.BOLD, 12);

                //P2 - Label for File Name
        lblFileName = new JLabel();
        lblFileName.setText("\u200E");
        lblFileName.setFont(new Font("Helvetica", Font.BOLD, 18));
        lblFileName.setHorizontalAlignment(SwingConstants.CENTER);
        lblFileName.setVerticalAlignment(SwingConstants.CENTER);
        gbc_p2.fill = GridBagConstraints.HORIZONTAL;
        gbc_p2.gridx = 1;
        gbc_p2.gridy = 1;
        gbc_p2.gridwidth = 3;
        p2.add(lblFileName, gbc_p2);

                //P2 - Panel for Flash Card Options
        JPanel pnlFcOptions = new JPanel(new GridLayout(3, 0, 12, 12));
        pnlFcOptions.setBackground(null);
        gbc_p2.fill = GridBagConstraints.BOTH;
        gbc_p2.gridx = 1;
        gbc_p2.gridy = 2;
        gbc_p2.gridwidth = 3;

        btnInsert = new JButton("I N S E R T");
        btnUpdate = new JButton("U P D A T E");
        btnDelete = new JButton("D E L E T E");

        pnlFcOptions.add(btnInsert);
        pnlFcOptions.add(btnUpdate);
        pnlFcOptions.add(btnDelete);

        tbFcOptions = BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Flashcard Options",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                panelTitleFont,
                Color.BLACK
                );
        pnlFcOptions.setBorder(tbFcOptions);
        p2.add(pnlFcOptions, gbc_p2);

                //P2 - Panel for Quiz Generator Options
        JPanel pnlQzOptions = new JPanel(new GridLayout(1, 0));
        pnlQzOptions.setBackground(null);
        gbc_p2.fill = GridBagConstraints.BOTH;
        gbc_p2.gridx = 1;
        gbc_p2.gridy = 3;
        gbc_p2.gridwidth = 3;

        btnQuiz = new JButton("G E N E R A T E   Q U I Z");
        btnQuiz.setBackground(new Color(73, 73, 73));
        btnQuiz.setForeground(new Color(255, 255, 255));

        pnlQzOptions.add(btnQuiz);

        tbQzOptions = BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Quiz Generator Options",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                panelTitleFont,
                Color.BLACK
                );
        pnlQzOptions.setBorder(tbQzOptions);
        p2.add(pnlQzOptions, gbc_p2);

                //P2 - Panel for Timer Options
        JPanel pnlTmOptions = new JPanel();
        pnlTmOptions.setBackground(null);
        gbc_p2.fill = GridBagConstraints.BOTH;
        gbc_p2.gridx = 1;
        gbc_p2.gridy = 4;
        gbc_p2.gridwidth = 3;
        pnlTmOptions.setLayout(new GridBagLayout());

        GridBagConstraints gbc_pt = new GridBagConstraints();

        //Panel 2 Timer
        JLabel lblTimer = new JLabel("00:25:00", SwingConstants.CENTER);
        lblTimer.setFont(new Font("Helvetica", Font.PLAIN, 32));
        lblTimer.setBackground(null);
        lblTimer.setOpaque(true);
        lblTimer.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        workHourField = new JTextField("00", 2);
        workMinuteField = new JTextField("25", 2);
        workSecondField = new JTextField("00", 2);

        breakHourField = new JTextField("00", 2);
        breakMinuteField = new JTextField("05", 2);
        breakSecondField = new JTextField("00", 2);

        btnStart = new JButton("Start");
        btnStart.setEnabled(false);

        JButton btnPausePlay = new JButton("Pause");
        btnPausePlay.setVisible(false);

        JPanel timerPanel = new JPanel();
        timerPanel.add(lblTimer);
        gbc_pt.anchor = GridBagConstraints.NORTH;
        gbc_pt.fill = GridBagConstraints.HORIZONTAL;
        gbc_pt.weightx = 1.0;
        gbc_pt.gridx = 0;
        gbc_pt.gridy = 0;
        gbc_pt.gridwidth = 2;
        pnlTmOptions.add(timerPanel, gbc_pt);

        JPanel workTimePanel = new JPanel();
        workTimePanel.add(new JLabel("Work Time:"));
        workTimePanel.add(workHourField);
        workTimePanel.add(new JLabel(":"));
        workTimePanel.add(workMinuteField);
        workTimePanel.add(new JLabel(":"));
        workTimePanel.add(workSecondField);
        workTimePanel.setBackground(null);
        gbc_pt.anchor = GridBagConstraints.CENTER;
        gbc_pt.fill = GridBagConstraints.HORIZONTAL;
        gbc_pt.weightx = 1.0;
        gbc_pt.gridx = 0;
        gbc_pt.gridy = 1;
        gbc_pt.gridwidth = 2;
        pnlTmOptions.add(workTimePanel, gbc_pt);

        JPanel breakTimePanel = new JPanel();
        breakTimePanel.add(new JLabel("Break Time:"));
        breakTimePanel.add(breakHourField);
        breakTimePanel.add(new JLabel(":"));
        breakTimePanel.add(breakMinuteField);
        breakTimePanel.add(new JLabel(":"));
        breakTimePanel.add(breakSecondField);
        breakTimePanel.setBackground(null);
        gbc_pt.anchor = GridBagConstraints.CENTER;
        gbc_pt.fill = GridBagConstraints.HORIZONTAL;
        gbc_pt.weightx = 1.0;
        gbc_pt.gridx = 0;
        gbc_pt.gridy = 2;
        gbc_pt.gridwidth = 2;
        pnlTmOptions.add(breakTimePanel, gbc_pt);

        gbc_pt.anchor = GridBagConstraints.CENTER;
        gbc_pt.fill = GridBagConstraints.BOTH;
        gbc_pt.weightx = 1.0;
        gbc_pt.gridx = 0;
        gbc_pt.gridy = 3;
        gbc_pt.gridwidth = 1;
        pnlTmOptions.add(btnStart, gbc_pt);

        gbc_pt.anchor = GridBagConstraints.NORTH;
        gbc_pt.fill = GridBagConstraints.BOTH;
        gbc_pt.weightx = 1.0;
        gbc_pt.gridx = 1;
        gbc_pt.gridy = 3;
        gbc_pt.gridwidth = 1;
        pnlTmOptions.add(btnPausePlay, gbc_pt);

        tbTmOptions = BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Timer Options",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                panelTitleFont,
                Color.BLACK);
        pnlTmOptions.setBorder(tbTmOptions);
        p2.add(pnlTmOptions, gbc_p2);

        PomodoroTimer pt = new PomodoroTimer(lblTimer, workHourField, workMinuteField, workSecondField,
                breakHourField, breakMinuteField, breakSecondField, btnStart, btnPausePlay);

                //P2 - Panel for Q/A Label and  Page Navigation Label
        JPanel pnlStateNav = new JPanel(new GridLayout(2, 0, 12, 8));
        pnlStateNav.setBackground(null);
        gbc_p2.fill = GridBagConstraints.BOTH;
        gbc_p2.gridx = 1;
        gbc_p2.gridy = 5;
        gbc_p2.gridwidth = 3;
        p2.add(pnlStateNav, gbc_p2);

                    //P2 - Label for Question or Answer Flashcard
        btnState = new JButton(" \u200E ");
        pnlStateNav.add(btnState);

                    //P2 - Label for the Page Navigation of Flashcard
        lblItems = new JLabel("");
        lblItems.setHorizontalAlignment(SwingConstants.CENTER);
        lblItems.setVerticalAlignment(SwingConstants.CENTER);
        lblItems.setFont(new Font("Arial", Font.BOLD, 14));
        pnlStateNav.add(lblItems);

        tbNvOptions = BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Navigation Options",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                panelTitleFont,
                Color.BLACK
                );
        pnlStateNav.setBorder(tbNvOptions);
        p2.add(pnlStateNav, gbc_p2);

        btnInsert.setEnabled(false);
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
        btnQuiz.setEnabled(false);
        workHourField.setEnabled(false);
        workMinuteField.setEnabled(false);
        workSecondField.setEnabled(false);
        breakHourField.setEnabled(false);
        breakMinuteField.setEnabled(false);
        breakSecondField.setEnabled(false);
        btnState.setEnabled(false);
        out_panel.add(p2, gbc_op);

        add(out_panel);
        setJMenuBar(menuBar);
        setVisible(true);


        //FILE MENU ACTIONS --------------------------------------------------------------------------------------------
        FileDialog fdSave = new FileDialog(this, "Save this File:", FileDialog.SAVE);
        String userDocuments = Paths.get(System.getProperty("user.home"), "Documents").toString();
        fdSave.setDirectory(userDocuments);
        fdSave.setFile("Untitled.txt");
        fdSave.setLocationRelativeTo(null);

        FileDialog fdOpen = new FileDialog(this, "Open a File:");
        fdOpen.setLocationRelativeTo(null);

            //New File
        newFile.addActionListener(_ -> {
            if (fh.isTempFileExist()) {
                int userChoice = fh.savePopup();

                switch (userChoice) {
                    case 1:
                        fdSave.setVisible(true);
                        String directory = fdSave.getDirectory();
                        String filename = fdSave.getFile();

                        if (directory != null && filename != null) {
                            String filePath = Paths.get(directory, filename).toString();
                            fh.saveFile(filePath);
                        }
                        break;

                    case 2:
                        fh.getTempFile().delete();
                        fh.setTempFileExist(false);
                        break;

                    case 0:
                        return;

                    default:
                        // Handle unexpected result
                        break;
                }
            }

            fh.newFile();

            FlashcardOpen();

            lblFileName.setText("");

            FlashcardEmpty();
        });

            //Save File
        saveasFile.addActionListener(_ -> {
            fdSave.setVisible(true);

            String directory = fdSave.getDirectory();
            String filename = fdSave.getFile();

            if (directory == null || filename == null) {
                return;
            }

            String filePath = Paths.get(directory, filename).toString();
            fh.saveFile(filePath);
        });

            //Open File
        openFile.addActionListener(_ -> {
            if (fh.isTempFileExist()) {
                int userChoice = fh.savePopup();

                switch (userChoice) {
                    case 1:
                        fdSave.setVisible(true);
                        String directory = fdSave.getDirectory();
                        String filename = fdSave.getFile();

                        if (directory != null && filename != null) {
                            String filePath = Paths.get(directory, filename).toString();
                            fh.saveFile(filePath);
                        }
                        break;

                    case 2:
                        fh.getTempFile().delete();
                        fh.setTempFileExist(false);
                        break;

                    case 0:
                        return;

                    default:
                        // Handle unexpected result
                        break;
                }
            }

            fdOpen.setVisible(true);

            String directory = fdOpen.getDirectory();
            String filename = fdOpen.getFile();
            if (directory == null || filename == null) {
                return;
            }

            FlashcardOpen();

            fh.setCurrPathFile(directory + filename);
            fh.readFile();

            fh.setCurrent(0);

            lblFileName.setText(filename);

            if (fh.countFile()==0){
                FlashcardEmpty();
                return;
            }

            btnState.setText("S H O W   A N S W E R   S I D E");
            fh.setStateQuestion(false);

            fcText.setText("<html><div style='text-align: center;'>"+ fh.getContent() +"</div></html>");
            lblItems.setText(fh.getCurrent() + 1 + " / " + fh.countFile() / 2);
        });

            //Close File
        closeFile.addActionListener(_ ->{
            if (fh.isTempFileExist()) {
                int userChoice = fh.savePopup();

                switch (userChoice) {
                    case 1:
                        fdSave.setVisible(true);
                        String directory = fdSave.getDirectory();
                        String filename = fdSave.getFile();

                        if (directory != null && filename != null) {
                            String filePath = Paths.get(directory, filename).toString();
                            fh.saveFile(filePath);
                        }
                        break;

                    case 2:
                        fh.getTempFile().delete();
                        fh.setTempFileExist(false);
                        break;

                    case 0:
                        return;

                    default:
                        // Handle unexpected result
                        break;
                }
            }

            fh.setCurrent(0);

            btnState.setText("\u200E");
            fh.setStateQuestion(false);

            fcText.setText("");
            lblItems.setText("");

            lblFileName.setText("\u200E");

            FlashcardClose();
        });

            //Exit Program
        exitProg.addActionListener(_ -> {
            if (fh.isTempFileExist()) {
                int userChoice = fh.savePopup();

                switch (userChoice) {
                    case 1:
                        fdSave.setVisible(true);
                        String directory = fdSave.getDirectory();
                        String filename = fdSave.getFile();

                        if (directory != null && filename != null) {
                            String filePath = Paths.get(directory, filename).toString();
                            fh.saveFile(filePath);
                        }
                        break;

                    case 2:
                        fh.getTempFile().delete();
                        fh.setTempFileExist(false);
                        break;

                    case 0:
                        return;

                    default:
                        // Handle unexpected result
                        break;
                }
            }

            this.dispose();
        });

        //PANEL 1 ACTIONS
            //Left Traversal
        btnArrowLeft.addActionListener(_ -> {
            if(fh.getCurrent() != 0){
                fh.decrementCurrent();
                fcText.setText("<html><div style='text-align: center;'>"+ fh.getContent() +"</div></html>");
                lblItems.setText(fh.getCurrent()+1 + " / " + fh.countFile()/2);
                System.out.println(fh.getCurrent());
            }
        });

            //Right Traversal
        btnArrowRight.addActionListener(_ -> {
            if(fh.getCurrent() != (fh.countFile()/2)-1){
                fh.incrementCurrent();
                fcText.setText("<html><div style='text-align: center;'>"+ fh.getContent() +"</div></html>");
                lblItems.setText(fh.getCurrent()+1 + " / " + fh.countFile()/2);
                System.out.println(fh.getCurrent());
            }
        });

        //PANEL 2 ACTIONS
            //Insert
        btnInsert.addActionListener(_ -> {
            if(!fh.insertBox()){
                return;
            }

            fh.readFile();
            fcText.setText("<html><div style='text-align: center;'>"+ fh.getContent() +"</div></html>");
            lblItems.setText(fh.getCurrent()+1 + " / " + fh.countFile()/2);

            if(fh.isStateQuestion()){
                btnState.setText("S H O W   Q U E S T I O N   S I D E");
                fh.setStateQuestion(true);
            } else{
                btnState.setText("S H O W   A N S W E R   S I D E");
                fh.setStateQuestion(false);
            }

            FlashcardNotEmpty();
        });

            //Update
        btnUpdate.addActionListener(_ -> {
            fh.updateBox();
            fcText.setText("<html><div style='text-align: center;'>"+ fh.getContent() +"</div></html>");
        });

            //Delete
        btnDelete.addActionListener(_ ->{
            fh.deleteBox();

             try {
                if (fh.isFileEmpty()) {
                    fh.setCurrent(0);
                    fcText.setText(" ");
                    lblItems.setText(" ");
                    FlashcardEmpty();
                } else {
                    FlashcardNotEmpty();
                    fcText.setText("<html><div style='text-align: center;'>" + fh.getContent() + "</div></html>");
                    lblItems.setText((fh.getCurrent() + 1) + " / " + (fh.countFile() / 2));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

            //Quiz Generator
        btnQuiz.addActionListener(_ -> {
            this.setVisible(false);
            qg.run(fh.getCurrPathFile());
            this.setVisible(true);
        });

            //Flashcard Navigator
        btnState.addActionListener(_ -> {
            if(!fh.isStateQuestion()){
                btnState.setText("S H O W   Q U E S T I O N   S I D E");
                fh.setStateQuestion(true);
            } else{
                btnState.setText("S H O W   A N S W E R   S I D E");
                fh.setStateQuestion(false);
            }
            fcText.setText("<html><div style='text-align: center;'>"+ fh.getContent() +"</div></html>");
        });

        //Close Operation
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (fh.isTempFileExist()){
                    fh.getTempFile().delete();
                    fh.setTempFileExist(false);
                }
                dispose();
                System.exit(0);
            }
        });
    }
}
