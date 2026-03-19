package PD;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CaveEntrance implements KeyListener {
    JFrame frame;
    ImageIcon sand;
    ImageIcon wall;
    ImageIcon topWall;
    ImageIcon chair;
    ImageIcon topChair;
    ImageIcon plant1;
    ImageIcon plant2;
    ImageIcon torch;
    
    ImageIcon frontS;
    ImageIcon frontW;
    ImageIcon leftS;
    ImageIcon leftW;
    ImageIcon rightS;
    ImageIcon rightW;
    ImageIcon backS;
    ImageIcon backW;

    JLabel tiles[];
    JLabel character[];
    int layout[];
    int characterPlace[];
    int characterPosition;
    int characterMode;
    int frameWidth = 960;
    int frameHeight = 600;
    int n;
    
    public CaveEntrance() {
        frame = new JFrame();
        sand = new ImageIcon("PD Assets/sand.png");
        wall = new ImageIcon("PD Assets/wall.png");
        topWall = new ImageIcon("PD Assets/topWall.png");
        chair = new ImageIcon("PD Assets/chair.png");
        topChair = new ImageIcon("PD Assets/topChair.png");
        plant1 = new ImageIcon("PD Assets/plant1.png");
        plant2 = new ImageIcon("PD Assets/plant2.png");
        torch = new ImageIcon("PD Assets/torch.png");
        
        frontS=new ImageIcon("PD Assets/frontstand.png");
        frontW=new ImageIcon("PD Assets/frontwalk.png");
        leftS=new ImageIcon("PD Assets/leftstand.png");
        leftW=new ImageIcon("PD Assets/leftwalk.png");
        rightS=new ImageIcon("PD Assets/rightstand.png");
        rightW=new ImageIcon("PD Assets/rightwalk.png");
        backS=new ImageIcon("PD Assets/backstand.png");
        backW=new ImageIcon("PD Assets/backwalk.png");

        sand = new ImageIcon(sand.getImage().getScaledInstance((frameWidth/15),(frameHeight/9), Image.SCALE_DEFAULT));
        wall = new ImageIcon(wall.getImage().getScaledInstance((frameWidth/15),(frameHeight/9), Image.SCALE_DEFAULT));
        topWall = new ImageIcon(topWall.getImage().getScaledInstance((frameWidth/15),(frameHeight/9), Image.SCALE_DEFAULT));
        chair = new ImageIcon(chair.getImage().getScaledInstance((frameWidth/15),(frameHeight/9), Image.SCALE_DEFAULT));
        topChair = new ImageIcon(topChair.getImage().getScaledInstance((frameWidth/15),(frameHeight/9), Image.SCALE_DEFAULT));
        plant1 = new ImageIcon(plant1.getImage().getScaledInstance((frameWidth/15),(frameHeight/9), Image.SCALE_DEFAULT));
        plant2 = new ImageIcon(plant2.getImage().getScaledInstance((frameWidth/15),(frameHeight/9), Image.SCALE_DEFAULT));
        torch = new ImageIcon(torch.getImage().getScaledInstance((frameWidth/15),(frameHeight/9), Image.SCALE_DEFAULT));
        
        frontS=new ImageIcon(frontS.getImage().getScaledInstance((frameWidth/15), (frameHeight/9), Image.SCALE_DEFAULT));
        frontW=new ImageIcon(frontW.getImage().getScaledInstance((frameWidth/15), (frameHeight/9), Image.SCALE_DEFAULT));
        leftS=new ImageIcon(leftS.getImage().getScaledInstance((frameWidth/15), (frameHeight/9), Image.SCALE_DEFAULT));
        leftW=new ImageIcon(leftW.getImage().getScaledInstance((frameWidth/15), (frameHeight/9), Image.SCALE_DEFAULT));
        rightS=new ImageIcon(rightW.getImage().getScaledInstance((frameWidth/15), (frameHeight/9), Image.SCALE_DEFAULT));
        rightW=new ImageIcon(rightW.getImage().getScaledInstance((frameWidth/15), (frameHeight/9), Image.SCALE_DEFAULT));
        backS=new ImageIcon(backS.getImage().getScaledInstance((frameWidth/15), (frameHeight/9), Image.SCALE_DEFAULT));
        backW=new ImageIcon(backW.getImage().getScaledInstance((frameWidth/15), (frameHeight/9), Image.SCALE_DEFAULT));
        
        character=new JLabel[135];
        characterPlace=new int[]{
            1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
            1,1,1,0,0,1,0,1,0,0,0,0,1,1,1,
            1,1,1,0,0,0,0,2,0,0,1,0,1,1,1,
            1,1,1,0,1,0,0,0,0,0,0,0,1,1,1,
            1,1,1,0,0,0,0,0,0,1,0,0,1,1,1,
            1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,
            1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,
        };
        for(int i=0;i<character.length;i++){
            if(characterPlace[i]==2){
                character[i]=new JLabel(frontS);
                characterPosition=i;
            }
            else character[i]=new JLabel();
        }

        tiles = new JLabel[135];
        layout = new int[] {
            1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
            1,1,1,2,7,2,2,3,2,2,7,2,1,1,1,
            1,1,1,0,0,6,0,4,0,0,0,0,1,1,1,
            1,1,1,0,0,0,0,0,0,0,5,0,1,1,1,
            1,1,1,0,5,0,0,0,0,0,0,0,1,1,1,
            1,1,1,0,0,0,0,0,0,6,0,0,1,1,1,
            1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,
            2,2,2,2,2,2,0,0,0,2,2,2,2,2,2,
        };
        for (int c = 0; c < tiles.length; c = c + 1) {
            switch (layout[c]) {
                case 0:
                    tiles[c] = new JLabel(sand);
                    break;
                case 1:
                    tiles[c] = new JLabel(topWall);
                    break;
                case 2:
                    tiles[c] = new JLabel(wall);
                    break;
                case 3:
                    tiles[c] = new JLabel(topChair);
                    break;
                case 4:
                    tiles[c] = new JLabel(chair);
                    break;
                case 5:
                    tiles[c] = new JLabel(plant1);
                    break;
                case 6:
                    tiles[c] = new JLabel(plant2);
                    break;
                case 7:
                    tiles[c] = new JLabel(torch);
                    break;
            }
        }
    }
    
    public void setFrame() {
        frame.setLayout(new GraphPaperLayout(new Dimension(15,9)));
        int x = 0;
        int y = 0;
        int width = 1;
        int height = 1;
        for (int c = 0; c < character.length; c = c + 1) {
            frame.add(character[c], new Rectangle(x,y,width,height));
            x = x + 1;
            if (x % 15 == 0) {
                x = 0;
                y = y + 1;
            }
        }
        x = 0;
        y = 0;
        width = 1;
        height = 1;
        for (int c = 0; c < tiles.length; c = c + 1) {
            frame.add(tiles[c], new Rectangle(x,y,width,height));
            x = x + 1;
            if (x % 15 == 0) {
                x = 0;
                y = y + 1;
            }
        }
        frame.setSize(960,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {  
    }

    @Override
    public void keyPressed(KeyEvent e) {
          if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            if(characterPlace[characterPosition+1]==1){
                if(characterMode==0){
                    character[characterPosition].setIcon(rightW);
                    characterMode=1;
                }
                else{
                    character[characterPosition].setIcon(rightS);
                    characterMode=0;
                }
            }
            else{
                character[characterPosition].setIcon(null);
                if(characterMode==0){
                    character[characterPosition+1].setIcon(rightW);
                    characterMode=1;
                }
                else{
                    character[characterPosition+1].setIcon(rightS);
                    characterMode=0;
             }
             characterPosition++;
            }
        }
        else if(e.getKeyCode()==KeyEvent.VK_LEFT){
            if(characterPlace[characterPosition-1]==1){
                if(characterMode==0){
                    character[characterPosition].setIcon(leftW);
                    characterMode=1;
                }
                else{
                    character[characterPosition].setIcon(leftS);
                    characterMode=0;
                }
            }
            else{
                character[characterPosition].setIcon(null);
                if(characterMode==0){
                    character[characterPosition-1].setIcon(leftW);
                    characterMode=1;
                }
                else{
                    character[characterPosition-1].setIcon(leftS);
                    characterMode=0;
                }
                characterPosition--;
            }
        }
        else if(e.getKeyCode()==KeyEvent.VK_DOWN){
            if(characterPlace[characterPosition+15]==1){
                if(characterMode==0){
                    character[characterPosition].setIcon(frontW);
                    characterMode=1;
                }
                else{
                    character[characterPosition].setIcon(frontS);
                    characterMode=0;
                }
            }
            else{
                character[characterPosition].setIcon(null);
                if(characterMode==0){
                    character[characterPosition+15].setIcon(frontW);
                    characterMode=1;
                }
                else{
                    character[characterPosition+15].setIcon(frontS);
                    characterMode=0;
                }
                characterPosition+=15;
            }
        }
        else if(e.getKeyCode()==KeyEvent.VK_UP){
            if(characterPlace[characterPosition-15]==1){
                if(characterMode==0){
                    character[characterPosition].setIcon(backW);
                    characterMode=1;
                }
                else{
                    character[characterPosition].setIcon(backS);
                    characterMode=0;
                }
            }
            else{
                character[characterPosition].setIcon(null);
                if(characterMode==0){
                    character[characterPosition-15].setIcon(backW);
                    characterMode=1;
                }
                else{
                    character[characterPosition-15].setIcon(backS);
                    characterMode=0;
                }
                characterPosition-=15;
            }
        }
        if (characterPosition == 66 || characterPosition == 68 || characterPosition == 82) {
            n = 1;
            if (n == 1) {
                JOptionPane.showMessageDialog(frame,  
                    "Where am I? What is this place?\nWhy am I here? Last time I was on my bedroom but here I am...", 
                    "Cody:",                                         
                    JOptionPane.INFORMATION_MESSAGE);
                n = n + 1;
            }
        }
        else if (characterPosition == 48 || characterPosition == 63 || characterPosition == 78) {
            if (n == 2) { 
                JOptionPane.showMessageDialog(frame,  
                    "I remember crying about last night because I could not focus\non my studies in the upcoming test, and I just slept while crying.", 
                    "Cody:",                                         
                    JOptionPane.INFORMATION_MESSAGE);
                n = n + 1;
            }
        }
        else if (characterPosition == 56 || characterPosition == 71 || characterPosition == 86) {
            if (n == 3) { 
                JOptionPane.showMessageDialog(frame,  
                    "Or maybe I didn't, that I was just diagnozed to have\nschizophrenia. Yesterday was crazy as nuts.", 
                    "Cody:",                                         
                    JOptionPane.INFORMATION_MESSAGE);
                n = n + 1;
            }
        }
        else if (characterPosition == 127 || characterPosition == 128 || characterPosition == 129) {
            JOptionPane.showMessageDialog(frame,  
                "Is this a fever dream? It's like I am on a video game.\nAm I ... hallucinating?", 
                "Cody:",                                         
                JOptionPane.INFORMATION_MESSAGE);
            Object[] options4 = {"True", "False"};  
                    int choice4 = JOptionPane.showOptionDialog(frame,  
                        "Would you like to Proceed?", 
                        "",   
                        JOptionPane.YES_NO_OPTION,                           
                        JOptionPane.QUESTION_MESSAGE,
                        null,             
                        options4, 
                        options4[0]);
        }
    }
        
    @Override
    public void keyReleased(KeyEvent e) {  
    }
}
  