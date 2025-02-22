package detyrekursi;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.awt.Dimension;
import java.util.*;

import javax.swing.*;
public class MyPoints implements ActionListener {
	
	Scanner scan=new Scanner(System.in);
	int player_id;
	JFrame frame=new JFrame();
	ImageIcon icon;
	JLabel back;
	JButton submit;
	JButton create;
	JTextField username;
	JTextField surname;
	JLayeredPane pane;
	
	public MyPoints() {
		System.out.print("Player_id:");
		player_id=scan.nextInt();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1500,1300);
		frame.setLayout(null);
		try {
            Font pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/Press_Start_2P.ttf/PressStart2P-Regular.ttf"));
            pixelFont = pixelFont.deriveFont(40f);
            
		submit=new JButton("Submit");
		submit.setBounds(720, 360, 80, 30);
		submit.setFont(new Font("Mv Boli", Font.PLAIN, 13));
		submit.setForeground(Color.black);
		submit.setBackground(Color.white);
		submit.addActionListener(this);
		
		create=new JButton("Points Earned");
		create.setBounds(300, 130, 800, 80);
		create.setFont(pixelFont);
		create.setForeground(Color.black);
		create.setBackground(new Color(59, 89, 152));
		create.setOpaque(false);
		create.setContentAreaFilled(false);
		create.setBorderPainted(false);
		
		icon = new ImageIcon(getClass().getResource("/a.jpg"));
	    back=new JLabel();
	    back.setBounds(30, 0, 1500, 750);
	    back.setIcon(icon);
	    
	    username=new JTextField();
	    username.setBounds(600, 250, 200, 40);
	    username.setPreferredSize(new Dimension(50, 30)); 
	    username.setFont(new Font("Mv Boli",Font.PLAIN,30));
	    username.setForeground(Color.black);
	    username.setBackground(Color.white);
	    username.setCaretColor(Color.black);
	    username.setText("Username");
	    username.setBorder(BorderFactory.createLineBorder(new Color(59, 89, 152)));
	    
	    
	    surname=new JTextField();
	    surname.setBounds(600, 300, 200, 40);
	    surname.setPreferredSize(new Dimension(50, 30)); 
	    surname.setFont(new Font("Mv Boli",Font.PLAIN,30));
	    surname.setForeground(Color.black);
	    surname.setBackground(Color.white);
	    surname.setCaretColor(Color.black);
	    surname.setText("Surname");
	    surname.setBorder(BorderFactory.createLineBorder(new Color(59, 89, 152)));
        
	    
	    pane=new JLayeredPane();
	    pane.add(back,Integer.valueOf(0));
	    pane.add(submit,Integer.valueOf(1));
	    pane.add(username,Integer.valueOf(1));
	    pane.add(surname,Integer.valueOf(1));
	    pane.add(create,Integer.valueOf(1));
	   
	    frame.add(create);
	    frame.add(username);
	    frame.add(surname);
	    frame.add(submit);
	    frame.add(back);
	    frame.add(pane);
	    
		frame.setVisible(true);
		 } catch (FontFormatException | IOException e) {
	            e.printStackTrace();
	        }
		
		
	}
	public void actionPerformed(ActionEvent e){
	    if(e.getSource()==submit) {
	    	frame.dispose();
	   Control2 kontroll=new Control2();
	   kontroll.Kontrollo(username.getText(),surname.getText(),player_id);
	    	
	  } 	
	    	
	    }
	    }
