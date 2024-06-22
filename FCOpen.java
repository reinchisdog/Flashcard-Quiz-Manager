import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class FCOpen extends JFrame{
    public FCOpen(){
//Creates the Menu Bar of the Application
        JMenuBar menuBar = new JMenuBar();

        //Creates the File Menu for the Menu Bar
        JMenu fileMenu = new JMenu("  File...  ");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(fileMenu);

        //Creates Menu Items for File Menu
        JMenuItem newFile, saveFile, saveasFile, openFile, closeFile;

        newFile = new JMenuItem("New File...", KeyEvent.VK_N);
        newFile.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));

        saveFile = new JMenuItem("Save File...", KeyEvent.VK_S);
        saveFile.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

        saveasFile = new JMenuItem("Save as New File...", KeyEvent.VK_V);
        saveasFile.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));

        openFile = new JMenuItem("Open File...", KeyEvent.VK_O);
        openFile.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));

        closeFile = new JMenuItem("Close File...", KeyEvent.VK_C);
        closeFile.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));

        fileMenu.add(newFile);
        fileMenu.add(saveFile);
        fileMenu.add(saveasFile);
        fileMenu.add(openFile);
        fileMenu.addSeparator();
        fileMenu.add(closeFile);




        JPanel out_panel, p1, p2;
        GridBagLayout op_layout, p1_layout, p2_layout;

        //Outer Panel Configuration
        out_panel = new JPanel();
        op_layout = new GridBagLayout();
        op_layout.columnWidths = new int[]{854, 426};
        out_panel.setLayout(op_layout);

        GridBagConstraints gbc_op = new GridBagConstraints();


        //Panel 1 (Left Section of the System)
        p1 = new JPanel();
        p1.setBackground(new Color(232, 232, 232));

        gbc_op.fill = GridBagConstraints.BOTH;
        out_panel.add(p1, gbc_op);

            //Panel 1 Layout
        p1_layout = new GridBagLayout();
        p1_layout.columnWidths = new int[]{100, 654, 100};
        p1_layout.rowHeights = new int[]{150, 420, 150};
        p1.setLayout(p1_layout);

        GridBagConstraints gbc_p1 = new GridBagConstraints();


            //Panel 1 Items
        JLabel labelQA, labelItems, fcText;
        JButton btnArrows;

                //P1 - Label for Question or Answer Flashcard
        labelQA = new JLabel("[Question/Answers Tab]");                         //Set to change for later
        labelQA.setHorizontalAlignment(SwingConstants.CENTER);
        labelQA.setVerticalAlignment(SwingConstants.CENTER);
        gbc_p1.fill = GridBagConstraints.HORIZONTAL;
        gbc_p1.gridx = 1;
        gbc_p1.gridy = 0;
        p1.add(labelQA, gbc_p1);

                //P1 - Label for the Page Navigation of Flashcard
        labelItems = new JLabel("[Current Item] / [No. of Items]");             //Set to change for later
        labelItems.setHorizontalAlignment(SwingConstants.CENTER);
        labelItems.setVerticalAlignment(SwingConstants.CENTER);
        gbc_p1.fill = GridBagConstraints.HORIZONTAL;
        gbc_p1.anchor = GridBagConstraints.NORTH;
        gbc_p1.insets = new Insets(24, 0, 0, 0);
        gbc_p1.gridx = 1;
        gbc_p1.gridy = 2;
        p1.add(labelItems, gbc_p1);

                //P1 - Button for Traversing to Left
        btnArrows = new JButton("<");
        btnArrows.setMargin(new Insets(0, 5, 0, 5));
        gbc_p1.fill = GridBagConstraints.VERTICAL;
        gbc_p1.anchor = GridBagConstraints.EAST;
        gbc_p1.gridx = 0;
        gbc_p1.gridy = 1;
        gbc_p1.ipady = 50;
        p1.add(btnArrows, gbc_p1);

                //P1 - Button for Traversing to Right
        btnArrows = new JButton(">");
        btnArrows.setMargin(new Insets(0, 5, 0, 5));
        gbc_p1.fill = GridBagConstraints.VERTICAL;
        gbc_p1.anchor = GridBagConstraints.WEST;
        gbc_p1.gridx = 2;
        gbc_p1.gridy = 1;
        p1.add(btnArrows, gbc_p1);

                //P1 - Panel for Showing the Content of the Flashcard
        JPanel flashCard = new JPanel(new BorderLayout());                                            //Set to change for later
        flashCard.setBackground(Color.white);

        fcText = new JLabel();
        fcText.setFont(new Font("Arial", Font.BOLD, 22));
        fcText.setBorder(new EmptyBorder(32, 32, 32, 32));

        String fcString = "[Content goes Here...]";
        fcText.setText("<html><div style='text-align: center;'>"+ fcString +"</div></html>");
        fcText.setHorizontalAlignment(SwingConstants.CENTER);

        gbc_p1.fill = GridBagConstraints.BOTH;
        gbc_p1.gridx = 1;
        gbc_p1.gridy = 1;
        flashCard.add(fcText, BorderLayout.CENTER);
        p1.add(flashCard, gbc_p1);





        //Panel 2 (Right Section of the System)
        p2 = new JPanel();
        p2.setBackground(new Color(191, 191, 191));

        gbc_op.fill = GridBagConstraints.BOTH;
        out_panel.add(p2, gbc_op);

            //Panel 2 Layout
        p2_layout = new GridBagLayout();
        p2_layout.columnWidths = new int[]{27, 124, 124, 124, 27};
        p2_layout.rowHeights = new int[]{20, 70, 175, 95, 225, 135};
        p2.setLayout(p2_layout);

        GridBagConstraints gbc_p2 = new GridBagConstraints();

            //Panel 2 Items
        JLabel lblFileName;
        JPanel pnlFcOptions, pnlQzOptions, pnlTmOptions;
        JButton btnInsert, btnDelete, btnUpdate, btnQuiz, btnStart, btnPause, btnStop;
        TitledBorder tbFcOptions, tbQzOptions, tbTmOptions;
        Font panelTitleFont = new Font("Arial", Font.BOLD, 12);

                //P2 - Label for File Name
        lblFileName = new JLabel();
        lblFileName.setText("[File Name]");
        lblFileName.setHorizontalAlignment(SwingConstants.CENTER);
        lblFileName.setVerticalAlignment(SwingConstants.CENTER);
        gbc_p2.fill = GridBagConstraints.HORIZONTAL;
        gbc_p2.gridx = 1;
        gbc_p2.gridy = 1;
        gbc_p2.gridwidth = 3;
        p2.add(lblFileName, gbc_p2);

                //P2 - Panel for Flash Card Options
        pnlFcOptions = new JPanel(new GridLayout(3, 0, 12, 12));
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
        pnlQzOptions = new JPanel(new GridLayout(1, 0));
        pnlQzOptions.setBackground(null);
        gbc_p2.fill = GridBagConstraints.BOTH;
        gbc_p2.gridx = 1;
        gbc_p2.gridy = 3;
        gbc_p2.gridwidth = 3;
        gbc_p2.insets = new Insets(16, 0, 16, 0);

        btnQuiz = new JButton("G E N E R A T E   Q U I Z");
        btnQuiz.setBackground(new Color(73, 73, 73));
        btnQuiz.setForeground(new Color(255, 255, 255));
        pnlQzOptions.add(btnQuiz);

        tbFcOptions = BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Quiz Generator Options",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                panelTitleFont,
                Color.BLACK
                );
        pnlQzOptions.setBorder(tbFcOptions);
        p2.add(pnlQzOptions, gbc_p2);

                //P2 - Panel for Timer Options
        pnlTmOptions = new JPanel(new GridLayout(4, 0));
        pnlTmOptions.setBackground(null);
        gbc_p2.fill = GridBagConstraints.BOTH;
        gbc_p2.gridx = 1;
        gbc_p2.gridy = 4;
        gbc_p2.gridwidth = 3;

        tbFcOptions = BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Timer Options",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                panelTitleFont,
                Color.BLACK
                );
        pnlTmOptions.setBorder(tbFcOptions);
        p2.add(pnlTmOptions, gbc_p2);



        add(out_panel);
        setJMenuBar(menuBar);
        setVisible(true);


    }
}
