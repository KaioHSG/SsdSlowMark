package kaiohsg.gui;

import java.awt.GridLayout;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

public class ConsoleLog extends JFrame {
    public ConsoleLog() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setTitle("GUI SSD Slow Mark");
        setSize(650, 380);

        // Log Panel

        JPanel logPanel = new JPanel();
        logPanel.setLocation(10,10);
        logPanel.setSize(614,321);
        logPanel.setBorder(new TitledBorder("Log"));
        logPanel.setLayout(new GridLayout(0,1));

        JTextArea logTextArea = new JTextArea();
        logTextArea.setEditable(false);
        PrintStream printStream = new PrintStream(new CustomOutputStream(logTextArea));
        System.setOut(printStream);
        System.setErr(printStream);
        logPanel.add(logTextArea);

        JScrollPane scroller = new JScrollPane(logTextArea);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        logPanel.add(scroller);
        
        add(logPanel);

        // JFrame
        setLocationRelativeTo(null);
        setVisible(true);
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
}