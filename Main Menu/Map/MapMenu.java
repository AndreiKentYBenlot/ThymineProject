package PD;
import Quarter2.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MapMenu implements ActionListener {
    JFrame frame;
    JLabel display;
    JLabel button1;
    ImageIcon background;
    ImageIcon back;
    int frameWidth = 960;
    int frameHeight = 600;

    public MapMenu() {
        frame = new JFrame();
        back = new ImageIcon("PD Assets/back.png");
        background = new ImageIcon("PD Assets/backgroundMap.gif");
        button1 = new JLabel(back);
        display = new JLabel(background);
        
        background = new ImageIcon(background.getImage().getScaledInstance((frameWidth),(frameHeight), Image.SCALE_DEFAULT));
        back = new ImageIcon(back.getImage().getScaledInstance((frameWidth/5),(frameHeight/9), Image.SCALE_DEFAULT));
        button1.setBackground(Color.black);
    }
    
    public void setFrame() {
        frame.setLayout(new GraphPaperLayout(new Dimension(15,9)));
        frame.add(button1, new Rectangle(11,7,3,1));
        frame.add(display, new Rectangle(0,0,15,9));
        frame.setSize(960,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        MapMenu go = new MapMenu();
        go.setFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
