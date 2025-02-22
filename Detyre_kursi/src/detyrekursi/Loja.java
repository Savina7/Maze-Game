package detyrekursi;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Scanner;

public class Loja implements KeyListener, ActionListener {
	Scanner scan=new Scanner(System.in);
	int player_id;
    JFrame frame = new JFrame();
    JLabel robot;
    JLabel vullkani;
    JLabel coini;
    ImageIcon icon;
    ImageIcon vullkan;
    ImageIcon coin;
    JPanel matrica;
    JButton finish;
    JButton start;
    
    JLayeredPane pane;
    
    
    static Random random = new Random();
    int m = random.nextInt(7) + 18;
    int n = random.nextInt(7) + 18;
    int[][] maze = matrica(m,n);
    
    int treasuresCollected;
    int points;
    public Loja() {
    	System.out.print("Player_id:");
		player_id=scan.nextInt();
		
		
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 1300);
        frame.setLayout(null);
        frame.setFocusable(true);
        frame.getContentPane().setBackground(Color.white);
        frame.addKeyListener(this);
        

        icon = new ImageIcon(getClass().getResource("/Robotii.gif"));
        vullkan=new ImageIcon(getClass().getResource("/vullkani2.jpg"));
        coin=new ImageIcon(getClass().getResource("/leku3.gif"));
    
        robot = new JLabel();
        robot.setBounds(100, 0, 100, 100);
        robot.setIcon(icon);
        
        
        vullkani=new JLabel();
        vullkani.setBounds(0, 0, 100, 100);
        vullkani.setIcon(vullkan);
        
        coini=new JLabel();
        coini.setBounds(0, 0, 100, 100);
        coini.setIcon(coin);

        matrica = new JPanel();
        matrica.setBounds(100, 0, 1200,700);
        matrica.setLayout(new GridLayout(m, n));
        
        finish = new JButton();
        finish.setBounds(1238, 652, 58, 35);
        finish.setText("Finish");
        finish.setForeground(Color.white);
        finish.setBackground(Color.black);
        finish.setBorder(null);
        finish.setFocusPainted(false);
        finish.setContentAreaFilled(false);
        finish.addActionListener(this);
    
        finish.setFont(new Font("Arial", Font.BOLD, 14));
          
        
        start = new JButton();
        start.setBounds(100, 10, 58, 35);
        start.setText("Start");
        start.setForeground(Color.white);
        start.setBackground(Color.black);
        start.setBorder(null);
        start.setFocusPainted(false);
        start.setContentAreaFilled(false);
        start.addActionListener(this);
        start.setFont(new Font("Arial", Font.BOLD, 15));

        
     
        
        pane = new JLayeredPane();
        pane.add(finish, Integer.valueOf(1));
        pane.add(start, Integer.valueOf(1));
     
        
        design();
        frame.add(finish);
        frame.add(start);
        frame.add(pane);
        frame.add(robot);
        frame.add(matrica);
        
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
    	points = treasuresCollected * 10;
	    if(e.getSource()==finish && treasuresCollected >= 5) {
	    	points= treasuresCollected *10;
	    	System.out.println("Points: " + points);
	    	Databaza insert=new Databaza();
	    	Databaza.databaza(points,"Won",player_id);  
	    	frame.dispose();
	       Win win=new Win();
	  }
	    else if(e.getSource()==finish && treasuresCollected <5) {
	    	Databaza insert=new Databaza();
	    	Databaza.databaza(points,"Lost",player_id);  
	    	frame.dispose();
	    	Loose loose=new Loose();
	    }
    }
    private void design() {
        matrica.removeAll();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                JLabel cell = new JLabel();
                cell.setOpaque(true);
                cell.setHorizontalAlignment(SwingConstants.CENTER);
                cell.setVerticalAlignment(SwingConstants.CENTER);

                if (maze[i][j] == 1) {
                    cell.setIcon(vullkan);
                } else if (maze[i][j] == 2) {
                    cell.setIcon(coin);
                } else {
                    cell.setBackground(Color.black);
                }

                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                matrica.add(cell);
            }
        }

        matrica.revalidate();
        matrica.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
        switch (e.getKeyChar()) {
            case 'a':
                robot.setLocation(robot.getX()-10,robot.getY());
                break;
            case 'w': 
                robot.setLocation(robot.getX(),robot.getY()-10);
                break;
            case 's':
                robot.setLocation(robot.getX(),robot.getY()+10);
                break;
            case 'd':
                robot.setLocation(robot.getX()+10,robot.getY());
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
       
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                moveRobot(-10, 0);
                break;
            case KeyEvent.VK_UP:
                moveRobot(0, -10);
                break;
            case KeyEvent.VK_DOWN:
                moveRobot(0, 10);
                break;
            case KeyEvent.VK_RIGHT:
                moveRobot(10, 0);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void moveRobot(int dx, int dy) {
        int x = robot.getX();
        int y = robot.getY();

       
        int newX = Math.max(0, Math.min(frame.getWidth() - robot.getWidth(), x + dx));
        int newY = Math.max(0, Math.min(frame.getHeight() - robot.getHeight(), y + dy));

        robot.setLocation(newX, newY);

        checkForTreasure();
    }

    private void checkForTreasure() {
        int robotX = robot.getX();
        int robotY = robot.getY();

        
        int cellX = robotX / (matrica.getWidth() / n);
        int cellY = robotY / (matrica.getHeight() / m);

     
        if (maze[cellY][cellX] == 2) {

            System.out.println("You found money!");

            
            maze[cellY][cellX] = 0;
            treasuresCollected++; 
            design();

            if (treasuresCollected >= 5) {
                System.out.print("U have saved enough money!!");
            }
        }
    }
    private static int[][] matrica(int m, int n){
        int[][] maze = new int[m][n];
        int cells = m * n;
        int opencells = (int)(0.7 * cells);
        int treasure = (int)(0.08 * cells);
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                maze[i][j] = 1;
            }
        }
        
        maze[0][0] = 0;
        maze[1][0] = 0;
        maze[m - 1][n - 1] = 0;
        maze[m - 2][n - 1] = 0;
        maze[m - 1][n - 2] = 0;
        

        while(opencells != 0) {
            int m1 = random.nextInt(m);
            int n1 = random.nextInt(n);
            if (maze[m1][n1] == 1) {
                maze[m1][n1] = 0;
                opencells--;
            }
        }

        while(treasure != 0) {
            int m1 = random.nextInt(m);
            int n1 = random.nextInt(n);
            if (maze[m1][n1] == 0 && !(m1 == 0 && n1 == 0)) {
                maze[m1][n1] = 2;
                treasure--;
            }
        }

        return maze;
    }

}

