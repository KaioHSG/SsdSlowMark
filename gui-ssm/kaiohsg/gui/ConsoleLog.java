package kaiohsg.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import tools4free.ssm.SsdSlowMark;

public class ConsoleLog extends JFrame implements ActionListener{
    JButton exitButton;
    JButton exitOpenButton;
    public boolean exitOpen = false;

    public ConsoleLog() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setTitle("GUI SSD Slow Mark - Log");
        setSize(650, 380);

        // Log Panel
        JPanel logPanel = new JPanel();
        logPanel.setLocation(0,0);
        logPanel.setSize(634,300);
        logPanel.setBorder(new TitledBorder("Log"));
        logPanel.setLayout(new GridLayout(0,1));

        JTextArea logTextArea = new JTextArea();
        logTextArea.setEditable(false);
        PrintStream printStream = new PrintStream(new CustomOutputStream(logTextArea));
        System.setOut(printStream);
        logPanel.add(logTextArea);

        JScrollPane scroller = new JScrollPane(logTextArea);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        logPanel.add(scroller);
        
        add(logPanel);

        // Start Panel
        JPanel startPanel = new JPanel();
        startPanel.setLocation(0,300);
        startPanel.setSize(634,60);

        exitOpenButton = new JButton("Open results");
        exitOpenButton.addActionListener(this);

        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);

        startPanel.add(exitOpenButton);
        startPanel.add(exitButton);

        add(startPanel);

        // JFrame
        setLocationRelativeTo(null);
        setVisible(true);
    }

    void title() {
        
    }

    public class CustomOutputStream extends OutputStream {
        private JTextArea logTextArea;
        public CustomOutputStream(JTextArea logTextArea) {
            this.logTextArea = logTextArea;
        }

        @Override
        public void write(int b) throws IOException {
            logTextArea.append(String.valueOf((char)b));
            logTextArea.setCaretPosition(logTextArea.getDocument().getLength());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exitButton)
            System.exit(0);
        if (e.getSource() == exitOpenButton) {
            SsdSlowMark.exitOpen = true;
            System.exit(0);
        }
    }
}