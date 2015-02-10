package com.pyro.skd;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class imageLayers {

    static JFrame frame;
    static JButton funcButton;
    static JLabel[] imageLabels;
    //static JPanel panel;
    static JLayeredPane lpane;
    static final String imagePathTrue = "/home/shobhit/Pictures/test.jpg";
    static final String imagePathBlur = "/home/shobhit/Pictures/test_blur.jpg";
    final static int rows = 4; //You should decide the values for rows and cols variables
    final static int cols = 4;
    final static int chunks = rows * cols;
    final static int SPACING = 0;//spacing between split images
    static int chunkWidth;
    static int chunkHeight;
    static int z = 0;
    //static int y = 30;
    
    public void createUI() {
        frame = new JFrame("Test");
        frame.setPreferredSize(new Dimension(1024, 680));
        //frame.getContentPane().setLayout(new GridLayout(rows, cols, SPACING, SPACING));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lpane = new JLayeredPane();
        lpane.setPreferredSize(new Dimension(640, 425));
        /*panel = new JPanel();
        panel.setPreferredSize(new Dimension(1024, 680));
        panel.setBackground(Color.red);
        panel.setVisible(false);
        lpane.add(panel, new Integer(Integer.MIN_VALUE), 0);*/
        
        initComponents(imagePathTrue, lpane);
        z++;
        initComponents(imagePathBlur, lpane);
        frame.add(lpane);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    private void initComponents(String imagePath, JLayeredPane lpane) {
    	//y = 10;
    	BufferedImage[] imgs = getImages(imagePath);
    	imageLabels = new JLabel[imgs.length];
    	//create JLabels with split images and add to frame contentPane
        for (int i = 0; i < imgs.length; i++) {
        	imageLabels[i] = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().createImage(imgs[i].getSource())));
        }
        //System.out.println(imgs.length);
        int j = 0;
        for (int i = 0; i < imgs.length; i++)
        {
        	//System.out.println("i:"+i);
        	imageLabels[i].setBounds((i%rows)*chunkWidth, (j++/cols)*chunkHeight, chunkWidth, chunkHeight);
        	imageLabels[i].setBackground(Color.blue);
        	imageLabels[i].setOpaque(true);//no change
        	
        	//labels[i].set
        	lpane.add(imageLabels[i], new Integer(z), 0);
        }
        //System.out.println(z);
    }
    
    private BufferedImage[] getImages(String imagePath) {
        File file = new File(imagePath);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(imageLayers.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedImage image = null;
        try {
            image = ImageIO.read(fis); //reading the image file
        } catch (IOException ex) {
            Logger.getLogger(imageLayers.class.getName()).log(Level.SEVERE, null, ex);
        }
        chunkWidth = image.getWidth() / cols; // determines the chunk width and height
        chunkHeight = image.getHeight() / rows;
        //System.out.println("chunkWidth: " +chunkWidth);
    	//System.out.println("chunkHeight: " +chunkHeight);
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