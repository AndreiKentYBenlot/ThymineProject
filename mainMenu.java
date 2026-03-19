package PD;
import Quarter2.*;
import PD.DesertCave;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class mainMenu implements ActionListener {
    JFrame frame;
    JLabel display;
    JLabel button1;
    JLabel button2;
    JLabel button3;
    ImageIcon background;
    ImageIcon newGame;
    ImageIcon settings;
    ImageIcon credits;
    int frameWidth = 960;
    int frameHeight = 600;

    public mainMenu() {
        frame = new JFrame();
        newGame = new ImageIcon("PD Assets/newGame.png");
        settings = new ImageIcon("PD Assets/settings.png");
        credits = new ImageIcon("PD Assets/credits.png");
        background = new ImageIcon("PD Assets/background.gif");
        button1 = new JLabel(newGame);
        button2 = new JLabel(settings);
        button3 = new JLabel(credits);
        display = new JLabel(background);
        
        background = new ImageIcon(background.getImage().getScaledInstance((frameWidth),(frameHeight), Image.SCALE_DEFAULT));
        newGame = new ImageIcon(newGame.getImage().getScaledInstance((frameWidth/5),(frameHeight/9), Image.SCALE_DEFAULT));
        settings = new ImageIcon(settings.getImage().getScaledInstance((frameWidth/5),(frameHeight/9), Image.SCALE_DEFAULT));
        credits = new ImageIcon(credits.getImage().getScaledInstance((frameWidth/5),(frameHeight/9), Image.SCALE_DEFAULT));
        button1.setBackground(Color.black);
        button2.setBackground(Color.black);
        button3.setBackground(Color.black);
    }
    
    public void actionPerformed (ActionEvent e) {
        if (e.getSource() == button1) {
            DesertCave go = new DesertCave();
            go.setFrame();
        }
    }
    
    public void setFrame() {
        frame.setLayout(new GraphPaperLayout(new Dimension(15,9)));
        frame.add(button1, new Rectangle(6,4,3,1));
        frame.add(button2, new Rectangle(6,5,3,1));
        frame.add(button3, new Rectangle(6,6,3,1));
        frame.add(display, new Rectangle(0,0,15,9));
        frame.setSize(960,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}

