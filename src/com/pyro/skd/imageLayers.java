package com.pyro.skd;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class splitImage1 {

    private JFrame frame;
    private JLabel[] labels;
    private JLayeredPane lpane;
    private static final String imagePathTrue = "/home/shobhit/Pictures/test.jpg";
    private static final String imagePathBlur = "/home/shobhit/Pictures/test_blur.jpg";
    private final int rows = 4; //You should decide the values for rows and cols variables
    private final int cols = 4;
    private final int chunks = rows * cols;
    private final int SPACING = 0;//spacing between split images
    private int chunkWidth;
    private int chunkHeight;
    private int z = 0;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new splitImage1().createAndShowUI();
            }
        });
    }

    private void createAndShowUI() {
        frame = new JFrame("Test");
        frame.getContentPane().setLayout(new GridLayout(rows, cols, SPACING, SPACING));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lpane = new JLayeredPane();
        lpane.setPreferredSize(new Dimension(1024, 680));
        initComponents(imagePathTrue, lpane);
        initComponents(imagePathBlur, lpane);
        frame.add(lpane);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    private void initComponents(String imagePath, JLayeredPane lpane) {
    	BufferedImage[] imgs = getImages(imagePath);
    	labels = new JLabel[imgs.length];
    	//create JLabels with split images and add to frame contentPane
        for (int i = 0; i < imgs.length; i++) {
            labels[i] = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().createImage(imgs[i].getSource())));
        }
        System.out.println(imgs.length);
        int j = 0;
        for (int i = 0; i < imgs.length; i++) //int i = imgs.length - 1; i >= 0; i--
        {
        	labels[i].setBounds((i%rows)*chunkWidth, (j++/cols)*chunkHeight, chunkWidth, chunkHeight);
        	lpane.add(labels[i], new Integer(z), 0);
        	//System.out.println(i+". "+z);
        }
        //System.out.println(lpane.getComponent(0));
    }

    private BufferedImage[] getImages(String imagePath) {
        File file = new File(imagePath);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(splitImage1.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedImage image = null;
        try {
            image = ImageIO.read(fis); //reading the image file
        } catch (IOException ex) {
            Logger.getLogger(splitImage1.class.getName()).log(Level.SEVERE, null, ex);
        }
        chunkWidth = image.getWidth() / cols; // determines the chunk width and height
        chunkHeight = image.getHeight() / rows;
        System.out.println("chunkWidth: " +chunkWidth);
    	System.out.println("chunkHeight: " +chunkHeight);
        int count = 0;
        BufferedImage imgs[] = new BufferedImage[chunks]; //Image array to hold image chunks
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                //Initialize the image array with image chunks
                imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());
                // draws the image chunk
                Graphics2D gr = imgs[count++].createGraphics();
                gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x, 
                		chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);
                gr.dispose();
                
            }
        }
        return imgs;
    }
}