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

public class imageLayers {

<<<<<<< HEAD
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
                new imageLayers().createAndShowUI();
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
=======
	private JFrame frame = new JFrame();
    private JLayeredPane lpane = new JLayeredPane();
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panel3 = new JPanel();
    private JLabel picLabel1;
    private JLabel picLabel2;
    private JLabel[] labels;
    
    private final static int rows = 4;
    private final static int cols = 4;
    private final int SPACING = 0;
    private final int chunks = rows * cols;
    
    private static int imgWidth;
	private static int imgHeight;
	
    private static BufferedImage img = null;
    private BufferedImage bgImage1 = readImage("C:/Users/happy_skd/Pictures/test.jpg");//("/home/shobhit/Pictures/test.jpg");
    //private BufferedImage bgArrayImage1[] = getImages(bgImage1);
    //img = null;
    private BufferedImage bgImage2 = readImage("C:/Users/happy_skd/Pictures/test_blur.jpg");//("/home/shobhit/Pictures/test_blur.jpg");
    //private BufferedImage bgArrayImage2[] = getImages(bgImage2);
    
	private static int z = 0;
    
	public imageLayers() {
		frame.setPreferredSize(new Dimension(imgWidth, imgHeight));	//set default size for frame
        frame.setLayout(new BorderLayout());	//set border layout
        frame.add(lpane, BorderLayout.CENTER);	//add the settings to frmae
         
        //panelBlue.setBackground(Color.BLUE);	//set color to pane 1
        //panel1.setBounds(0, 0, 1024, 680);	//set size of pane 1
        picLabel1 = new JLabel(new ImageIcon(bgImage1));
        picLabel1.setBounds(0, 0, imgWidth, imgHeight);
        //panel1.add(picLabel1);
        //panel1.setOpaque(true);	//fill all pixels
        
        //panel2.setForeground(bgImage2);	
        //panel2.setBounds(0, 0, 1024, 680);
        picLabel2 = new JLabel(new ImageIcon(bgImage2));
        picLabel2.setBounds(0, 0, imgWidth, imgHeight);
        //panel2.add(picLabel2);
        //panel2.setOpaque(true);
        
        //transparent panel
        panel3.setBackground(Color.red);
        panel3.setBounds(0, 0, imgWidth, imgHeight);
        panel3.setOpaque(false);	//false adds transparency
        
        lpane.setBounds(0, 0, imgWidth, imgHeight);	//set the size for layered pane
        panel1.setLayout(new GridLayout(rows, cols, SPACING, SPACING));
        panel2.setLayout(new GridLayout(rows, cols, SPACING, SPACING));
        panel3.setLayout(new GridLayout(rows, cols, SPACING, SPACING));
        
        //lpane.add(picLabel1, new Integer(0), 0);	//add pane 1 to layered pane; bottom
        //lpane.add(picLabel2, new Integer(1), 0);	//add pane 2 to layered pane; top
        getImages(bgImage1);
        getImages(bgImage2);
        //createImageGrid(bgArrayImage2);
        lpane.add(panel3, new Integer(Integer.MAX_VALUE), 0);	//add pane 3 to layered pane; top most
        frame.pack();	//size the window to fit the size of its layout and sub components
        
        frame.setVisible(true);	//show the window
	}

	private static BufferedImage readImage(String fileLocation) {
        try 
        {
            img = ImageIO.read(new File(fileLocation));
            imgWidth = img.getWidth();
            imgHeight = img.getHeight();
        } 
        catch (IOException e) 
>>>>>>> 5d5d8fb57f06897921d491e099cf38eda28a064c
        {
        	labels[i].setBounds((i%rows)*chunkWidth, (j++/cols)*chunkHeight, chunkWidth, chunkHeight);
        	lpane.add(labels[i], new Integer(z), 0);
        	//System.out.println(i+". "+z);
        }
        //System.out.println(lpane.getComponent(0));
    }
<<<<<<< HEAD
=======
	
	 private BufferedImage[] getImages(BufferedImage bImage) 
	 {
	        int count = 0;
	        /*
	        System.out.println("Width: " +imgWidth);
	        System.out.println("Height: " +imgHeight);
	        */
	    	int chunkWidth = imgWidth/cols;
	    	int chunkHeight = imgWidth/rows;
	    	/*
	    	System.out.println("chunkWidth: " +chunkWidth);
	    	System.out.println("chunkHeight: " +chunkHeight);
	    	*/
	        BufferedImage imgs[] = new BufferedImage[chunks]; //Image array to hold image chunks
	        labels = new JLabel[imgs.length];
	        for (int x = 0; x < rows; x++) {
	            for (int y = 0; y < cols; y++) {
	                //Initialize the image array with image chunks
	                imgs[count] = new BufferedImage(chunkWidth, chunkHeight, img.getType());

	                // draws the image chunk
	                Graphics2D gr = imgs[count].createGraphics();
	                 
	                gr.drawImage(img, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x, 
	                		(chunkWidth * y) + chunkWidth, (chunkHeight * x) + chunkHeight, null);
	                gr.dispose();
	                
	                labels[count] = new JLabel(new ImageIcon(imgs[count]));
	                labels[count].setBounds(chunkWidth * y, chunkHeight * x, 
	                		chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight);
		 			lpane.add(labels[count], new Integer(z), 0);
		 			count++;
	            }
	        }
	        z++;
	        return imgs;
	    }
	 
	 	@SuppressWarnings("unused")
		/*private void createImageGrid(BufferedImage[] bArrayImage)
	 	{
	 		labels = new JLabel[bArrayImage.length];
	 		for(int j = 0; j < bArrayImage.length; j++)
	 		{
	 			//int i = 0;
	 			labels[j] = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().createImage(bArrayImage[0].getSource())));
	 			labels[j].setBounds(chunkWidth * (j%4), chunkHeight * x, 
                		chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight);
	 			lpane.add(labels[0], new Integer(z), 0);
	 		}
	 		z++;
	 	}*/

		private void show()
		{
			//lpane.setLayer(panel3.getComponent(5), Integer.MIN_VALUE, 0);
		}
		
		public static void main(String args[])
		{
			new imageLayers().show();
		}
		
>>>>>>> 5d5d8fb57f06897921d491e099cf38eda28a064c

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
