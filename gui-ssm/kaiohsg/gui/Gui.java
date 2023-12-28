package kaiohsg.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import tools4free.ssm.SsdSlowMark;

public class Gui extends JFrame implements ActionListener{
    JSpinner fileCountSpinner;
    public int fileCount = 1000;

    JSpinner fileSizeSpinner;
    public int fileSize = 1024;

    JSpinner blockSizeSpinner;
    public int blockSize = 8192;

    JTextField dumpTextField;
    public String dumpFolder = "dump";

    JTextField resultTextField;
    public String resultFolder = "Results";

    JSpinner imageWidthSpinner;
    public int imageWidth = 800;

    JSpinner imageHeightSpinner;
    public int imageHeight = 600;

    JSpinner imagePaddingSpinner;
    public int imagePadding = 60;

    JRadioButton wButton;
    JRadioButton rButton;
    JRadioButton rwButton;
    public String testType;
    
    JButton starButton;
    JButton creditsButton;

    public boolean startSsm;

    public Gui() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setTitle("GUI SSD Slow Mark");
        setSize(650, 380);

        JLabel label;

        // File panel
        JPanel filePanel = new JPanel();
        filePanel.setLocation(0,0);
        filePanel.setSize(634,60);
        filePanel.setBorder(new TitledBorder("Files"));

        SpinnerModel fileCountModel = new SpinnerNumberModel(fileCount, 1, 1000, 1);
        SpinnerModel fileSizetModel = new SpinnerNumberModel(fileSize, 1, 32768, 1);
        SpinnerModel blockModel = new SpinnerNumberModel(blockSize, 4, 1048576, 1);

        fileCountSpinner = new JSpinner(fileCountModel);
        fileSizeSpinner = new JSpinner(fileSizetModel);
        blockSizeSpinner = new JSpinner(blockModel);

        label = new JLabel("File count:");
        filePanel.add(label);
        filePanel.add(fileCountSpinner);

        label = new JLabel("File size (MB):");
        filePanel.add(label);
        filePanel.add(fileSizeSpinner);

        label = new JLabel("Block size (KB):");
        filePanel.add(label);
        filePanel.add(blockSizeSpinner);

        add(filePanel);

        // Folder panel
        JPanel folderPanel = new JPanel();
        folderPanel.setLocation(0,60);
        folderPanel.setSize(634,120);
        folderPanel.setBorder(new TitledBorder("Folders"));
        folderPanel.setLayout(new GridLayout(0,1));

        dumpTextField = new JTextField(dumpFolder);
        resultTextField = new JTextField(resultFolder);

        label = new JLabel("Dump folder:");
        folderPanel.add(label);
        folderPanel.add(dumpTextField);
        
        label = new JLabel("Results folder:");
        folderPanel.add(label);
        folderPanel.add(resultTextField);

        add(folderPanel);

        // Image panel
        JPanel imagePanel = new JPanel();
        imagePanel.setLocation(0,180);
        imagePanel.setSize(634,60);
        imagePanel.setBorder(new TitledBorder("Images"));

        SpinnerModel imageWidthModel = new SpinnerNumberModel(imageWidth, 1, 9999, 1);
        SpinnerModel imageHeightModel = new SpinnerNumberModel(imageHeight, 1, 9999, 1);
        SpinnerModel imagePaddingModel = new SpinnerNumberModel(imagePadding, 1, 9999, 1);

        imageWidthSpinner = new JSpinner(imageWidthModel);
        imageHeightSpinner = new JSpinner(imageHeightModel);
        imagePaddingSpinner = new JSpinner(imagePaddingModel);

        label = new JLabel("Image width (px):");
        imagePanel.add(label);
        imagePanel.add(imageWidthSpinner);

        label = new JLabel("Image height (px):");
        imagePanel.add(label);
        imagePanel.add(imageHeightSpinner);

        label = new JLabel("Image padding (px):");
        imagePanel.add(label);
        imagePanel.add(imagePaddingSpinner);

        add(imagePanel);

        // Test type panel
        JPanel testTypePanel = new JPanel();
        testTypePanel.setLocation(0,240);
        testTypePanel.setSize(634,60);
        testTypePanel.setBorder(new TitledBorder("Test type"));

        rButton = new JRadioButton("Read");
        wButton = new JRadioButton("Write");
        rwButton = new JRadioButton("Write and read", true);

        ButtonGroup testTypeGroup = new ButtonGroup();
        testTypeGroup.add(wButton);
        testTypeGroup.add(rButton);
        testTypeGroup.add(rwButton);

        testTypePanel.add(wButton);
        testTypePanel.add(rButton);
        testTypePanel.add(rwButton);

        add(testTypePanel);

        // Start Panel
        JPanel startPanel = new JPanel();
        startPanel.setLocation(0,300);
        startPanel.setSize(634,60);

        starButton = new JButton("Start Test");
        starButton.addActionListener(this);

        creditsButton = new JButton("Credits");
        creditsButton.addActionListener(this);

        startPanel.add(starButton);
        startPanel.add(creditsButton);

        add(startPanel);

        // JFrame
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Convert
        Object o;
        Number n;

        o = fileCountSpinner.getValue();
        n = (Number) o;
        fileCount = n.intValue();
        o = fileSizeSpinner.getValue();
        n = (Number) o;
        fileSize = n.intValue();
        o = blockSizeSpinner.getValue();
        n = (Number) o;
        blockSize = n.intValue();
        o = imageWidthSpinner.getValue();
        n = (Number) o;
        imageWidth = n.intValue();
        o = imageHeightSpinner.getValue();
        n = (Number) o;
        imageHeight = n.intValue();
        o = imagePaddingSpinner.getValue();
        n = (Number) o;
        imagePadding = n.intValue();

        dumpFolder = dumpTextField.getText();
        resultFolder = resultTextField.getText();

        if (rButton.isSelected())
            testType = "r";
        if (wButton.isSelected())
            testType = "w";
        if (rwButton.isSelected())
            testType = "rw";

        if(e.getSource() == starButton) {
            // Start SSM
            /*
            System.out.println("\n" + "'" + fileCount + "', '" + fileSize + "', '" + blockSize + "'");
            System.out.println("'" + dumpFolder + "', '" + resultFolder + "'");
            System.out.println("'" + imageWidth + "', '" + imageHeight + "', '" + imagePadding + "'");
            System.out.println("'" + testType + "'");
            */

            dispose();
            startSsm = true;
        }
        if(e.getSource() == creditsButton) {
            // Credits
            JDialog creditsDialog = new JDialog();
            creditsDialog.setResizable(false);
            creditsDialog.setTitle("Credits");
            creditsDialog.setSize(200, 100);
            creditsDialog.setLayout(new GridLayout(0,1));
            creditsDialog.setAlwaysOnTop(true);

            JLabel label;

            label = new JLabel("GUI SSD Slow Mark - " + SsdSlowMark.gssmVersion, SwingConstants.CENTER);
            creditsDialog.add(label);
            label = new JLabel();
            creditsDialog.add(label);
            label = new JLabel("SSM: tools4free", SwingConstants.CENTER);
            creditsDialog.add(label);
            label = new JLabel("GUI: Kaio HSG", SwingConstants.CENTER);
            creditsDialog.add(label);

            creditsDialog.setLocationRelativeTo(null);
            creditsDialog.setVisible(true);
        }
    }

}
