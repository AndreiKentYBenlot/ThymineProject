package PD;
import Quarter2.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class mainMenu implements ActionListener {
    JFrame frame;
    JLabel display;
    JButton button1;
    JButton button2;
    JButton button3;
    ImageIcon background;
    ImageIcon newGame;
    ImageIcon map;
    ImageIcon credits;
    int frameWidth = 960;
    int frameHeight = 600;

    public mainMenu() {
        frame = new JFrame();
        newGame = new ImageIcon("PD Assets/newGame.png");
        map = new ImageIcon("PD Assets/map.png");
        credits = new ImageIcon("PD Assets/credits.png");
        background = new ImageIcon("PD Assets/background.gif");
        button1 = createIconButton(newGame);
        button2 = createIconButton(map);
        button3 = createIconButton(credits);
        display = new JLabel(background);
        
        background = new ImageIcon(background.getImage().getScaledInstance((frameWidth),(frameHeight), Image.SCALE_DEFAULT));
        newGame = new ImageIcon(newGame.getImage().getScaledInstance((frameWidth/5),(frameHeight/9), Image.SCALE_DEFAULT));
        map = new ImageIcon(map.getImage().getScaledInstance((frameWidth/5),(frameHeight/9), Image.SCALE_DEFAULT));
        credits = new ImageIcon(credits.getImage().getScaledInstance((frameWidth/5),(frameHeight/9), Image.SCALE_DEFAULT));
        button1.setBackground(Color.black);
        button2.setBackground(Color.black);
        button3.setBackground(Color.black);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
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
    
    
    private JButton createIconButton(ImageIcon icon) {
        JButton btn = new JButton(icon);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        return btn;
    }
  
    private ImageIcon scaleImage(String path, int w, int h) {
        Image img = new ImageIcon(path).getImage();
        return new ImageIcon(img.getScaledInstance(w, h, Image.SCALE_SMOOTH));
    }
    
    public void actionPerformed (ActionEvent e) {
        if (e.getSource() == button1) {
            frame.dispose();
            CaveEntrance move = new CaveEntrance();
            move.setFrame();
        }
        else if (e.getSource() == button2) {
            frame.dispose();
            MapMenu move = new MapMenu();
            move.setFrame();
        }
        else if (e.getSource() == button3) {
            frame.dispose();
            CreditsMenu move = new CreditsMenu();
            move.setFrame();
        }
    }
    
    public static void main(String[] args) {
        mainMenu go = new mainMenu();
        go.setFrame();
    }
}
