package com.pyro.skd;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
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
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panel3 = new JPanel();
    private BufferedImage bgImage1 = readImage("/home/shobhit/Pictures/test.jpg");
    private BufferedImage bgImage2 = readImage("/home/shobhit/Pictures/test_blur.jpg");
    
	public imageLayers() {
		frame.setPreferredSize(new Dimension(1024, 680));	//set default size for frame
        frame.setLayout(new BorderLayout());	//set border layout
        frame.add(lpane, BorderLayout.CENTER);	//add the settings to frmae
         
        //panelBlue.setBackground(Color.BLUE);	//set color to pane 1
        panel1.setBounds(0, 0, 1024, 680);	//set size of pane 1
        JLabel picLabel1 = new JLabel(new ImageIcon(bgImage1));
        panel1.add(picLabel1);
        panel1.setOpaque(true);	//fill all pixels
        
        //panelGreen.setBackground(Color.GREEN);	
        panel2.setBounds(0, 0, 1024, 680);
        JLabel picLabel2 = new JLabel(new ImageIcon(bgImage2));
        panel2.add(picLabel2);
        panel2.setOpaque(true);
        
        //transparent panel
        panel3.setBackground(Color.WHITE);
        panel3.setBounds(0, 0, 1024, 680);
        panel3.setOpaque(false);	//false adds transparency
        
        lpane.setBounds(0, 0, 1024, 680);	//set the size for layered pane
        lpane.add(panel1, new Integer(0), 0);	//add pane 1 to layered pane; bottom
        lpane.add(panel2, new Integer(1), 0);	//add pane 2 to layered pane; top
        lpane.add(panel3, new Integer(2), 0);	//add pane 3 to layered pane; top most
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
