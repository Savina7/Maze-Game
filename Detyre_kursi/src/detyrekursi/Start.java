package detyrekursi;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.BevelBorder;
import java.io.File;
import java.io.IOException;

public class Start extends JFrame implements ActionListener {

    JButton button;
    JButton gameName;
    JLabel label;
    ImageIcon icon;
    JLayeredPane pane;

    public Start() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1500, 1300);
        this.setLayout(null);

        try {
            Font pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/Press_Start_2P.ttf/PressStart2P-Regular.ttf"));
            pixelFont = pixelFont.deriveFont(30f);

            
            button = new JButton("Start");
            button.setBounds(610, 570, 200, 50);
            button.setFont(pixelFont);
            button.setForeground(Color.black);
            button.setBackground(new Color(255, 0, 0));
            button.setBorder(BorderFactory.createEtchedBorder());
            button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.BLACK, 3),
                    BorderFactory.createEmptyBorder(10, 20, 10, 20)
            ));
            button.addActionListener(this);

            
            Font largerFont = pixelFont.deriveFont(65f);
            gameName = new JButton("MAZE GAME ");
            gameName.setBounds(370, 250, 800, 80);
            gameName.setFont(largerFont);
            
         
            gameName.setForeground(new Color(255, 0, 0));
            
           

            
            gameName.setOpaque(false);
            gameName.setContentAreaFilled(false);
            gameName.setBorderPainted(false);

            
            icon = new ImageIcon(getClass().getResource("/ass.jpg"));
            label = new JLabel();
            label.setBounds(30, 0, 1500, 750);
            label.setIcon(icon);

           
            pane = new JLayeredPane();
            pane.add(button, Integer.valueOf(1));
            pane.add(label, Integer.valueOf(0));
            pane.add(gameName, Integer.valueOf(1));

            
            this.add(button);
            this.add(gameName);
            this.add(label);
            this.add(pane);
            this.setVisible(true);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            this.dispose();
            Login log = new Login();
        }
    }
}
