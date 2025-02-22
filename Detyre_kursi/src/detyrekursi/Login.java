package detyrekursi;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;


public class Login implements ActionListener {
JFrame frame=new JFrame();
JButton login;
JButton create;
JButton mypoints;
JLabel back;
ImageIcon icon;
JLayeredPane pane;
public Login() {
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(1500,1300);
	frame.setLayout(null);
	frame.getContentPane().setBackground(Color.black);
	 try {
         Font pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/Press_Start_2P.ttf/PressStart2P-Regular.ttf"));
         pixelFont = pixelFont.deriveFont(25f);
	login=new JButton("Login");
	login.setBounds(610,550,200,50);
	login.setFont(pixelFont);
	login.setForeground(Color.black);
	login.setBackground(new Color(255, 0, 0));
	login.addActionListener(this);
	frame.setVisible(true);
	
	Font largerFont = pixelFont.deriveFont(25f);
	create=new JButton("Create");
	create.setBounds(610, 430, 200, 50);
	create.setFont(largerFont);
	create.setForeground(Color.black);
	create.setBackground(new Color(255, 0, 0));
	create.addActionListener(this);
	
	mypoints=new JButton("My Points");
	mypoints.setBounds(580, 310, 270, 50);
	mypoints.setFont(largerFont);
	mypoints.setForeground(Color.black);
	mypoints.setBackground(new Color(255, 0, 0));
	mypoints.addActionListener(this);
	
	icon = new ImageIcon(getClass().getResource("/a.jpg"));
    back=new JLabel();
    back.setBounds(30, 0, 1500, 750);
    back.setIcon(icon);
	
    pane=new JLayeredPane();
    pane.add(back,Integer.valueOf(0));
    pane.add(create,Integer.valueOf(1));
    pane.add(login,Integer.valueOf(1));
    pane.add(mypoints,Integer.valueOf(1));

	
    frame.add(mypoints);
	frame.add(create);
	frame.add(login);
	frame.add(back);
	frame.add(pane);
	frame.setVisible(true);
	 } catch (FontFormatException | IOException e) {
         e.printStackTrace();
     }
}
@Override
public void actionPerformed(ActionEvent e){
    if(e.getSource()==login) {
    	frame.dispose();
    	Loging log=new Loging();
    }
    else if(e.getSource()==create) {
    	frame.dispose();
    	Create create=new Create();
    }
    else if(e.getSource()==mypoints) {
    	frame.dispose();
    	MyPoints create=new MyPoints();
    }
    }
}

