package PD;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DesertCave implements KeyListener{
    JFrame frame;
    ImageIcon sand;
    ImageIcon wall;
    ImageIcon topWall;
    ImageIcon box;
    ImageIcon campfire;
    ImageIcon lava;
    ImageIcon four;
    ImageIcon twenty;
    ImageIcon thirtyEight;
    ImageIcon bucket;
    
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
    int n = 1;
    
    public DesertCave() {
        frame = new JFrame();
        sand = new ImageIcon("PD Assets/sand.png");
        wall = new ImageIcon("PD Assets/wall.png");
        topWall = new ImageIcon("PD Assets/topWall.png");
        box = new ImageIcon("PD Assets/box.png");
        campfire = new ImageIcon("PD Assets/campfire.png");
        lava = new ImageIcon("PD Assets/lava.png");
        four = new ImageIcon("PD Assets/4.png");
        twenty = new ImageIcon("PD Assets/20.png");
        thirtyEight = new ImageIcon("PD Assets/38.png");
        bucket = new ImageIcon("PD Assets/bucket.png");
        
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
        box = new ImageIcon(box.getImage().getScaledInstance((frameWidth/15),(frameHeight/9), Image.SCALE_DEFAULT));
        campfire = new ImageIcon(campfire.getImage().getScaledInstance((frameWidth/15),(frameHeight/9), Image.SCALE_DEFAULT));
        lava = new ImageIcon(lava.getImage().getScaledInstance((frameWidth/15),(frameHeight/9), Image.SCALE_DEFAULT));
        four = new ImageIcon(four.getImage().getScaledInstance((frameWidth/15),(frameHeight/9), Image.SCALE_DEFAULT));
        twenty = new ImageIcon(twenty.getImage().getScaledInstance((frameWidth/15),(frameHeight/9), Image.SCALE_DEFAULT));
        thirtyEight = new ImageIcon(thirtyEight.getImage().getScaledInstance((frameWidth/15),(frameHeight/9), Image.SCALE_DEFAULT));
        bucket = new ImageIcon(bucket.getImage().getScaledInstance((frameWidth/15),(frameHeight/9), Image.SCALE_DEFAULT));
        
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
            1,1,0,2,0,1,1,1,1,1,1,1,1,1,1,
            1,1,0,0,0,1,1,1,1,1,1,1,1,1,1,
            1,0,0,0,0,0,1,1,1,0,0,0,0,0,1,
            1,0,0,0,0,0,1,1,1,0,0,0,0,0,1,
            1,0,0,1,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,1,1,1,0,0,0,0,0,1,
            1,0,0,0,0,0,1,1,1,0,0,0,0,0,1,
            1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,
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
            0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,
            0,3,2,2,2,3,0,0,0,3,3,6,3,3,0,
            0,2,2,2,2,2,0,0,0,2,2,2,2,2,0,
            0,2,2,5,2,2,3,7,3,2,2,2,2,2,0,
            0,2,5,4,5,2,2,2,2,2,2,8,2,2,0,
            0,2,2,5,2,2,0,0,0,2,2,2,2,2,0,
            0,2,2,2,2,2,0,0,0,2,2,2,2,2,0,
            0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,
            3,3,3,3,3,3,3,3,3,3,1,1,1,3,3
        };
        for (int c = 0; c < tiles.length; c = c + 1) {
            switch (layout[c]) {
                case 0:
                    tiles[c] = new JLabel(topWall);
                    break;
                case 1:
                    tiles[c] = new JLabel(lava);
                    break;
                case 2:
                    tiles[c] = new JLabel(sand);
                    break;
                case 3:
                    tiles[c] = new JLabel(wall);
                    break;
                case 4:
                    tiles[c] = new JLabel(campfire);
                    break;
                case 5:
                    tiles[c] = new JLabel(box);
                    break;
                case 6:
                    tiles[c] = new JLabel(thirtyEight);
                    break;
                case 7:
                    tiles[c] = new JLabel(twenty);
                    break;
                case 8:
                    tiles[c] = new JLabel(four);
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
        if (characterPosition == 18 || characterPosition == 19 || characterPosition == 20) {
            if (n == 1) { 
                JOptionPane.showMessageDialog(frame,  
                    "Some objects may carry out questions that will work if the answer is correct. Make sure to record all the clues in the area \nas well as their location before opening these objects since such questions may cover the clues as you open them.\nFor instance, in this cave, opening one of the crates will provide an item in order to remove the lava.", 
                    "Tutorial",                                         
                    JOptionPane.INFORMATION_MESSAGE);
                n = 0;
            }
        }
        int r = (int)(Math.random() * 5 + 1);
        if (characterPosition == 48 || characterPosition == 62 || characterPosition == 64 || characterPosition == 78) {
            switch (r) {
                case 1:
                    Object[] options1 = {"True", "False"};  
                    int choice1 = JOptionPane.showOptionDialog(frame,  
                        "A teardrop shows a triangular form where the base's measurement is written in the floor while the height's measurement is inscribed in the walls of the passageway. \nThe area of such entity is 30 squared units", 
                        "A question To Open This Crate",   
                        JOptionPane.YES_NO_OPTION,                           
                        JOptionPane.QUESTION_MESSAGE,
                        null,             
                        options1, 
                        options1[0]); 
                    if(choice1==JOptionPane.YES_OPTION){ 
                        JOptionPane.showMessageDialog(frame,  
                            "This option is wrong! The chosen crate cannot be opened.", 
                            "Answer",                                         
                            JOptionPane.INFORMATION_MESSAGE);  
                    }
                    else if(choice1==JOptionPane.NO_OPTION){ 
                        JOptionPane.showMessageDialog(frame,  
                            "Correct! The answer is 40 squared units using (base * height) / 2.", 
                            "Answer",                                         
                            JOptionPane.INFORMATION_MESSAGE);
                        n = n + 1;
                    }
                    break;
                case 2:
                    Object[] options2 = {"True", "False"};  
                    int choice2 = JOptionPane.showOptionDialog(frame,  
                        "The cornea of the eyes resemble a rhombus whose diagonals correspond to both numbers in the walls. \nIts area in millimeters is 380 squared mm.", 
                        "A question To Open This Crate",   
                        JOptionPane.YES_NO_OPTION,                           
                        JOptionPane.QUESTION_MESSAGE,
                        null,             
                        options2, 
                        options2[0]); 
                    if(choice2==JOptionPane.YES_OPTION){ 
                        JOptionPane.showMessageDialog(frame,  
                            "Correct! The answer is 380 squared mm using (first diagonal * second diagonal) / 2.",
                            "Answer",                                         
                            JOptionPane.INFORMATION_MESSAGE);  
                        n = n + 1;
                    }
                    else if(choice2==JOptionPane.NO_OPTION){ 
                        JOptionPane.showMessageDialog(frame,  
                            "This option is wrong! The chosen crate cannot be opened.", 
                            "Answer",                                         
                            JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
                case 3:
                    Object[] options3 = {"True", "False"};  
                    int choice3 = JOptionPane.showOptionDialog(frame,  
                        "The eyes can see the horizon whose distance is equal to the area of a square whose side measurement is the largest number found somewhere in this cave. \nThe distance is greater than 1,500 km.", 
                        "A question To Open This Crate",   
                        JOptionPane.YES_NO_OPTION,                           
                        JOptionPane.QUESTION_MESSAGE,
                        null,             
                        options3, 
                        options3[0]); 
                    if(choice3==JOptionPane.YES_OPTION){ 
                        JOptionPane.showMessageDialog(frame,  
                            "This option is wrong! The chosen crate cannot be opened.",
                            "Answer",                                         
                            JOptionPane.INFORMATION_MESSAGE);  
                    }
                    else if(choice3==JOptionPane.NO_OPTION){ 
                        JOptionPane.showMessageDialog(frame,  
                            "Correct! The answer is 1,444 km for the distance (1,444 squared m for the area) using side ^ 2.",
                            "Answer",                                         
                            JOptionPane.INFORMATION_MESSAGE);
                        n = n + 1;
                    }
                    break;
                case 4:
                    Object[] options4 = {"True", "False"};  
                    int choice4 = JOptionPane.showOptionDialog(frame,  
                        "The area of a rectangular monocle is 80 squared units if its base and height \nare recorded as the two smaller numbers found in this cave.", 
                        "A question To Open This Crate",   
                        JOptionPane.YES_NO_OPTION,                           
                        JOptionPane.QUESTION_MESSAGE,
                        null,             
                        options4, 
                        options4[0]); 
                    if(choice4==JOptionPane.YES_OPTION){ 
                        JOptionPane.showMessageDialog(frame,  
                            "Correct! The answer is 80 squared units using base * height.",
                            "Answer",                                         
                            JOptionPane.INFORMATION_MESSAGE);  
                        n = n + 1;
                    }
                    else if(choice4==JOptionPane.NO_OPTION){ 
                        JOptionPane.showMessageDialog(frame,  
                            "This option is wrong! The chosen crate cannot be opened.",
                            "Answer",                                         
                            JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
                case 5:
                    Object[] options5 = {"True", "False"};  
                    int choice5 = JOptionPane.showOptionDialog(frame,  
                        "The area of a trapezoid-shaped eyewear is 115 squared units if the measurements of the bases are similar to the ones written in the walls \nand the height's measurement is identical to the sign on the floor.", 
                        "A question To Open This Crate",   
                        JOptionPane.YES_NO_OPTION,                           
                        JOptionPane.QUESTION_MESSAGE,
                        null,             
                        options5, 
                        options5[0]); 
                    if(choice5==JOptionPane.YES_OPTION){ 
                        JOptionPane.showMessageDialog(frame,  
                            "This option is wrong! The chosen crate cannot be opened.",
                            "Answer",                                         
                            JOptionPane.INFORMATION_MESSAGE);  
                    }
                    else if(choice5==JOptionPane.NO_OPTION){ 
                        JOptionPane.showMessageDialog(frame,  
                            "Correct! The answer is 116 squared units using ((first base + second base) * height) / 2.",
                            "Answer",                                         
                            JOptionPane.INFORMATION_MESSAGE);
                        n = n + 1;
                    }
                    break;
            }
        }
        if(characterPosition == 48 || characterPosition == 64 || characterPosition == 78){
            if (n == 1) {
                JOptionPane.showMessageDialog(frame,  
                "This crate does not have anything.", 
                "Cody Opens The Crate...",                                         
                JOptionPane.INFORMATION_MESSAGE);
            n = 0;
            }
        }
        else if (characterPosition == 62 && n == 1) {
            JOptionPane.showMessageDialog(frame,  
                "Inside, there is a button. When pressed, something... fell off from the celing!", 
                "Cody Opens The Crate...",                                         
                JOptionPane.INFORMATION_MESSAGE);
            character[67].setIcon(bucket);
            n = n + 1;
        }
        if (n == 2) {
            if (characterPosition == 100 || characterPosition == 101 || characterPosition == 102 || characterPosition == 115 || characterPosition == 116 || characterPosition == 117 || characterPosition == 130 || characterPosition == 131 || characterPosition == 132) {
            characterPlace[115] = 0;
            characterPlace[116] = 0;
            characterPlace[117] = 0;
            tiles[130].setIcon(sand);
            tiles[131].setIcon(sand);
            tiles[132].setIcon(sand);
            tiles[115].setIcon(sand);
            tiles[116].setIcon(sand);
            tiles[117].setIcon(sand);
        }
        if(characterPosition == 130 || characterPosition == 131 || characterPosition == 132){
            JOptionPane.showMessageDialog(frame,  
                "Finally escaped the cave! Congratulations!", 
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
    }
    
    @Override
    public void keyReleased(KeyEvent e) {  
    }
}
