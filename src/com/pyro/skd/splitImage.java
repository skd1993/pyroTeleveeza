package com.pyro.skd;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class splitImage extends JFrame 
{
		private int imageWidth, imageHeight;
				
		private JLayeredPane lpane;
		private JPanel panelTrue;
		private JPanel panelBlur;
		private JLabel[] labels;
		
		private static String OriginalImagePath = "/home/shobhit/Pictures/test.jpg";
	    private static String BlurImagePath = "/home/shobhit/Pictures/test_blur.jpg";
	    
	    private final int rows = 4; //You should decide the values for rows and cols variables
	    private final int cols = 4;
	    private final int chunks = rows * cols;
	    private final int SPACING = 1;	//spacing between split images
	    
	    private void splitImage()
	    {
	    	Dimension imageSize = new Dimension(imageWidth, imageHeight);
	    	
	    	BufferedImage[] imgs = getImages(OriginalImagePath);
	        BufferedImage[] imgsBlur = getImages(BlurImagePath);
	        
	    	lpane = new JLayeredPane();
	    	getContentPane().add(lpane);
	    	lpane.setPreferredSize(imageSize);
	    	lpane.setLayout(new GridLayout(rows, cols, SPACING, SPACING));
	    	
	    	panelTrue = new JPanel();
	    	panelTrue.setPreferredSize(imageSize);
	    	panelTrue.setLayout(new GridLayout(rows, cols, SPACING, SPACING));
	    	panelTrue.setBounds(0, 0, imageSize.width, imageSize.height);
	    	
	    	panelBlur = new JPanel();
	    	panelBlur.setPreferredSize(imageSize);
	    	panelBlur.setLayout(new GridLayout(rows, cols));
	    	panelBlur.setBounds(0, 0, imageSize.width, imageSize.height);
	    	
	    	lpane.add(panelTrue, new Integer(0), 0);
	    	lpane.add(panelBlur, new Integer(1), 0);
	    	
	    	labels = new JLabel[imgs.length];
	    	//create JLabels with split images and add to frame contentPane
	        for (int i = 0; i < imgs.length; i++) {
	            labels[i] = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().createImage(imgs[i].getSource())));
	            panelTrue.add(labels[i]);
	            //lpane.add(panelTrue, new Integer(0), 0);
	            //frame.getContentPane().add(panelTrue);
	        }
	        
	        labels = null;
	        for (int i = 0; i < imgsBlur.length; i++) {
	            labels[i] = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().createImage(imgsBlur[i].getSource())));
	            panelBlur.add(labels[i]);
	            //lpane.add(panelBlur, new Integer(1), 0);
	            //frame.getContentPane().add(panelBlur);
	        }
	    }
	    
	    /*
	    private void accessImage(String imagePath)
	    {
	    	File file = new File(imagePath);
	    	FileInputStream fis = null;
	    	try{
	    		fis = new FileInputStream(file);
	    	} catch(IOException e){
	    		Logger.getLogger(splitImage.class.getName()).log(Level.SEVERE, null, e);
	    	}
	    	
	        try{
	            image = ImageIO.read(fis); //reading the image file
	        } catch (IOException ex) {
	            Logger.getLogger(splitImage.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    	imageWidth = image.getWidth();
	    	imageHeight = image.getHeight();
	    }*/
	    
	    @SuppressWarnings("unused")
		private BufferedImage[] getImages(String imagePath)
	    {
	    	File file = new File(imagePath);
	    	FileInputStream fis = null;
	    	try{
	    		fis = new FileInputStream(file);
	    	} catch(IOException e){
	    		Logger.getLogger(splitImage.class.getName()).log(Level.SEVERE, null, e);
	    	}
	    	BufferedImage image = null;
	        try{
	            image = ImageIO.read(fis); //reading the image file
	        } catch (IOException ex) {
	            Logger.getLogger(splitImage.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    	imageWidth = image.getWidth();
	    	imageHeight = image.getHeight();
	    	
	    	int chunkWidth = imageWidth/cols; // determines the chunk width and height
	        int chunkHeight = imageHeight/rows;
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
	    
	    public static void main(String args[])
	    {
	    	JFrame frame = new splitImage();
	    	frame.setDefaultCloseOperation( DISPOSE_ON_CLOSE );
			frame.pack();
			frame.setResizable( false );
			frame.setLocationRelativeTo( null );
			frame.setVisible(true);
	    }
}
