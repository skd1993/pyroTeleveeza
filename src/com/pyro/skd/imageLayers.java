package com.pyro.skd;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class imageLayers {

	private JFrame frame = new JFrame();
    private JLayeredPane lpane = new JLayeredPane();
    private JPanel panelBlue = new JPanel();
    private JPanel panelGreen = new JPanel();
    private BufferedImage bgImage1 = readImage("/home/shobhit/Pictures/test.jpg");
    private BufferedImage bgImage2 = readImage("/home/shobhit/Pictures/test_blur.jpg");
    
	public imageLayers() {
		frame.setPreferredSize(new Dimension(1024, 680));	//set default size for frame
        frame.setLayout(new BorderLayout());	//set border layout
        frame.add(lpane, BorderLayout.CENTER);	//add the settings to frmae
         
        //panelBlue.setBackground(Color.BLUE);	//set color to pane 1
        panelBlue.setBounds(0, 0, 1024, 680);	//set size of pane 1
        JLabel picLabel1 = new JLabel(new ImageIcon(bgImage1));
        panelBlue.add(picLabel1);
        panelBlue.setOpaque(true);	//fill all pixels
        
        //panelGreen.setBackground(Color.GREEN);	
        panelGreen.setBounds(0, 0, 1024, 680);
        JLabel picLabel2 = new JLabel(new ImageIcon(bgImage2));
        panelGreen.add(picLabel2);
        panelGreen.setOpaque(true);
        
        lpane.setBounds(0, 0, 1024, 680);	//set the size for layered pane
        lpane.add(panelBlue, new Integer(0), 0);	//add pane 1 to layered pane; bottom
        lpane.add(panelGreen, new Integer(1), 0);	//add pane 2 to layered pane; top
        frame.pack();	//size the window to fit the size of its layout and sub components
        
        frame.setVisible(true);	//show the window
	}

	public static BufferedImage readImage(String fileLocation) {
        BufferedImage img = null;
        try 
        {
            img = ImageIO.read(new File(fileLocation));
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return img;
    }
	
	public static void main(String args[])
	{
		new imageLayers();
	}

}
