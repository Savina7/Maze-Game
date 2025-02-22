package detyrekursi;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.BevelBorder;
import java.io.File;
import java.io.IOException;
import java.awt.FontFormatException;

public class Loose extends JFrame implements ActionListener {

    JButton button;
    JButton gameName;
    JLabel label;
    ImageIcon icon;
    JLayeredPane pane;

    public Loose() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1500, 1300);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.black);

        try {
            Font pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/Press_Start_2P.ttf/PressStart2P-Regular.ttf"));
            pixelFont = pixelFont.deriveFont(15f);

            
            button = new JButton("CLose page");
            button.setBounds(1050, 650, 250, 50);
            button.setFont(pixelFont);
            button.setForeground(Color.white);
            button.setBackground(new Color(0, 0, 80));
            button.setBorder(BorderFactory.createEtchedBorder());
            button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.BLACK, 3),
                    BorderFactory.createEmptyBorder(10, 20, 10, 20)
            ));
            button.addActionListener(this);

            
            Font largerFont = pixelFont.deriveFont(65f);
            gameName = new JButton("TRY AGAIN ");
            gameName.setBounds(350, 200, 800, 80);
            gameName.setFont(largerFont);
            gameName.setForeground(new Color(25, 40, 70));
            gameName.setOpaque(false);
            gameName.setContentAreaFilled(false);
            gameName.setBorderPainted(false);

            
            icon = new ImageIcon(getClass().getResource("/robot-idle.gif"));
            label = new JLabel();
            label.setBounds(530, 0, 1792, 1024);
            label.setIcon(icon);

           
            pane = new JLayeredPane();
            pane.add(button, Integer.valueOf(1));
            pane.add(label, Integer.valueOf(0));
            pane.add(gameName, Integer.valueOf(1));

            this.add(gameName);
            this.add(button);
           
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
            
        }
    }
}
