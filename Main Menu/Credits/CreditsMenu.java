package PD;
import Quarter2.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreditsMenu implements ActionListener {
    JFrame frame;
    JLabel display;
    JButton button1;
    ImageIcon background;
    ImageIcon back;
    int frameWidth = 960;
    int frameHeight = 600;

    public CreditsMenu() {
        frame = new JFrame();
        back = new ImageIcon("PD Assets/back.png");
        background = new ImageIcon("PD Assets/backgroundCredits.gif");
        button1 = createIconButton(back);
        display = new JLabel(background);
        
        background = new ImageIcon(background.getImage().getScaledInstance((frameWidth),(frameHeight), Image.SCALE_DEFAULT));
        back = new ImageIcon(back.getImage().getScaledInstance((frameWidth/5),(frameHeight/9), Image.SCALE_DEFAULT));
        button1.setBackground(Color.black);
        button1.addActionListener(this);
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
    
    public void setFrame() {
        frame.setLayout(new GraphPaperLayout(new Dimension(15,9)));
        frame.add(button1, new Rectangle(11,7,3,1));
        frame.add(display, new Rectangle(0,0,15,9));
        frame.setSize(960,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            frame.dispose();
            mainMenu move = new mainMenu();
            move.setFrame();
        }
    }
    
    public static void main(String[] args) {
        CreditsMenu go = new CreditsMenu();
        go.setFrame();
    }
}
