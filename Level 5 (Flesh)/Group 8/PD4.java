package com.mycompany.pdcs4;

//Members: Badilles, Salvo, Sia

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PD4 implements KeyListener,ActionListener{
    JFrame frame;
    ImageIcon ground;
    ImageIcon CleanFloorTile;
    ImageIcon meatGround;
    ImageIcon Mach1Icon;
    ImageIcon Mach2Icon;
    ImageIcon Mach3Icon;
    ImageIcon frontS;
    ImageIcon frontW;
    ImageIcon leftS;
    ImageIcon leftW;
    ImageIcon rightS;
    ImageIcon rightW;
    ImageIcon backS;
    ImageIcon backW;
    ImageIcon NPCIcon;
    JLabel tiles[];
    JLabel character[];
    JLabel chatSpace;
    JTextArea textSpace;
    JLabel Mach1;
    JLabel Mach2;
    JLabel Mach3;
    JButton nextB;
    JButton exitB;
    String NPCScript[];
    int mapLayout[];
    int characterPlace[];
    int mapWidth=9;
    int mapHeight=9;
    int frameWidth=450;
    int frameHeight=450;
    int characterPosition;
    int characterMode;
    int NPCLocation;
    int direction;
    int scriptIndex;
    int correctCombination[] = {2,3,1};
    int[] playerCombination = new int[3];
    int index = 0;
    int[] Mach1Tiles = {0,1,9,10};
    int[] Mach2Tiles = {2,3,11,12};
    int[] Mach3Tiles = {4,5,13,14};
    
    public PD4(){
        characterPosition=-1;
        characterMode=0;
        NPCLocation=-1;
        direction=-1;
        scriptIndex=0;
        
        NPCScript=new String[]{
            "Hey! Please help me! I lost my arms and I can't operate the machine anymore.",
            "All you gotta do is interact with the machines in the right order.",
            "I remember what the exact order is, but I can't recall what each button is called.",
            "The first one should be the upper limbs of the human body.",
            "Then, the second one should be lower limbs of the human body.",
            "Lastly, the third one should be the upper division of the human body that contains the brain."
        };
        
        chatSpace = new JLabel();
        textSpace = new JTextArea();
        nextB = new JButton("Next");
        exitB = new JButton("Exit");
        frame=new JFrame();
        ground=new ImageIcon("Images/MeatTile1.png");
        CleanFloorTile=new ImageIcon("Images/CleanFloorTile.png");
        meatGround=new ImageIcon("Images/MeatTile2.png");
        NPCIcon=new ImageIcon("Images/Skeleton.png");
        Mach1Icon=new ImageIcon("Images/MeatMachine.png");
        Mach2Icon=new ImageIcon("Images/MeatMachine2.png");
        Mach3Icon=new ImageIcon("Images/MeatMachine3.png");
        frontS=new ImageIcon("Images/frontstand.png");
        frontW=new ImageIcon("Images/frontwalk.png");
        leftS=new ImageIcon("Images/leftstand.png");
        leftW=new ImageIcon("Images/leftwalk.png");
        rightS=new ImageIcon("Images/rightstand.png");
        rightW=new ImageIcon("Images/rightwalk.png");
        backS=new ImageIcon("Images/backstand.png");
        backW=new ImageIcon("Images/backwalk.png");
        
        ground=new ImageIcon(ground.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        CleanFloorTile=new ImageIcon(CleanFloorTile.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        meatGround=new ImageIcon(meatGround.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        NPCIcon=new ImageIcon(NPCIcon.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        frontS=new ImageIcon(frontS.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        frontW=new ImageIcon(frontW.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        leftS=new ImageIcon(leftS.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        leftW=new ImageIcon(leftW.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        rightS=new ImageIcon(rightS.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        rightW=new ImageIcon(rightW.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        backS=new ImageIcon(backS.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        backW=new ImageIcon(backW.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        Mach1Icon=new ImageIcon(Mach1Icon.getImage().getScaledInstance((frameWidth/mapWidth)*2, (frameHeight/mapHeight)*2, Image.SCALE_DEFAULT));
        Mach2Icon=new ImageIcon(Mach2Icon.getImage().getScaledInstance((frameWidth/mapWidth)*2, (frameHeight/mapHeight)*2, Image.SCALE_DEFAULT));
        Mach3Icon=new ImageIcon(Mach3Icon.getImage().getScaledInstance((frameWidth/mapWidth)*2, (frameHeight/mapHeight)*2, Image.SCALE_DEFAULT));
               
        Mach1=new JLabel(Mach1Icon);
        Mach2=new JLabel(Mach2Icon);
        Mach3=new JLabel(Mach3Icon);
        
        tiles=new JLabel[mapWidth*mapHeight];
        mapLayout=new int[]{
            0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,1,0,
            0,0,0,0,1,1,0,0,0,
            0,0,1,0,0,1,0,1,0,
            0,0,0,1,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,1,0,0,0,1,0,0,
            0,1,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0
        };
        for(int i=0;i<tiles.length;i++){
            if(mapLayout[i]==0) tiles[i]=new JLabel(CleanFloorTile);
            else if(mapLayout[i]==1) tiles[i]=new JLabel(CleanFloorTile);
        }
        
        character=new JLabel[mapWidth*mapHeight];
        characterPlace=new int[]{
            3,3,4,4,5,5,0,0,0,
            3,3,4,4,5,5,0,0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,0,0,2,0,0,0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,
            0,1,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0
        };
        for(int i=0;i<character.length;i++){
            if(characterPlace[i]==1){
                character[i]=new JLabel(frontS);
                characterPosition=i;
            }
            else if(characterPlace[i]==2){
                character[i]=new JLabel(NPCIcon);
                NPCLocation=i;
            }
            else if(characterPlace[i]==3){
                character[i]=new JLabel();
            }
            else if(characterPlace[i]==4){
                character[i]=new JLabel();
            }
            else if(characterPlace[i]==5){
                character[i]=new JLabel();
            }
            else character[i]=new JLabel();
        }
    }
    
    boolean collision(int index) {
        if (index < 0 || index >= characterPlace.length)
            return true;
        return characterPlace[index] == 2 || characterPlace[index] == 3 || characterPlace[index] == 4 || characterPlace[index] == 5;
    }
            
    boolean contains(int[] arr, int val){
        for(int i : arr) if(i == val) return true;
        return false;
    }

    
    public void setConvoComponentsVisible(boolean b){
        textSpace.setVisible(b);
        exitB.setVisible(b);
        nextB.setVisible(b);
        chatSpace.setVisible(b);
        
        textSpace.setLineWrap(true);
        textSpace.setWrapStyleWord(true);
        textSpace.setEditable(false);
        textSpace.setOpaque(false);
    }
    
    public void setFrame(){
        frame.setLayout(new GraphPaperLayout(new Dimension(mapWidth,mapHeight)));
        
        frame.add(Mach1, new Rectangle(0,0,2,2));
        frame.add(Mach2, new Rectangle(2,0,2,2));
        frame.add(Mach3, new Rectangle(4,0,2,2));
        
        chatSpace.setOpaque(true);
        chatSpace.setBackground(new Color(243,246,232));
        
        frame.add(textSpace, new Rectangle(1,7,7,1));
        frame.add(exitB, new Rectangle(1,5,2,1));
        frame.add(nextB, new Rectangle(6,5,2,1));
        frame.add(chatSpace, new Rectangle(0,6,9,4));
        
        this.setConvoComponentsVisible(false);
        
        int x=0, y=0, w=1, h=1;
        
        for(int i=0;i<character.length;i++){
            frame.add(character[i], new Rectangle(x,y,w,h));
            x++;
            if(x%mapWidth==0){
                x=0;
                y++;
            }
        }
        
        x=0; y=0; w=1; h=1;
        
        for(int i=0;i<tiles.length;i++){
            frame.add(tiles[i], new Rectangle(x,y,w,h));
            x++;
            if(x%mapWidth==0){
                x=0;
                y++;
            }
        }
        
        frame.setSize(frameWidth,frameHeight);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        
        frame.addKeyListener(this);
        exitB.addActionListener(this);
        nextB.addActionListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e){
        
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            direction=3;
            if (collision(characterPosition + 1)) {
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
            direction=2;
            if (collision(characterPosition - 1)) {
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
            direction=1;
            if (collision(characterPosition + mapWidth)) {
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
                    character[characterPosition+mapWidth].setIcon(frontW);
                    characterMode=1;
                }
                else{
                    character[characterPosition+mapWidth].setIcon(frontS);
                    characterMode=0;
                }
                characterPosition+=mapWidth;
            }
        }
        else if(e.getKeyCode()==KeyEvent.VK_UP){
            direction=0;
            if (collision(characterPosition - mapWidth)) {
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
                    character[characterPosition-mapWidth].setIcon(backW);
                    characterMode=1;
                }
                else{
                    character[characterPosition-mapWidth].setIcon(backS);
                    characterMode=0;
                }
                characterPosition-=mapWidth;
            }
        }
        else if(e.getKeyCode()==KeyEvent.VK_E){
            int movement=0;
            if(direction==0) movement=-1*mapWidth;
            else if(direction==1) movement=mapWidth;
            else if(direction==2) movement=-1;
            else if(direction==3) movement=1;
            int frontTile = characterPosition + movement;
            if (frontTile == NPCLocation) {
                textSpace.setText(NPCScript[scriptIndex]);
                setConvoComponentsVisible(true);
                return;
            }

            if (contains(Mach1Tiles, frontTile)) playerInput(1);
            else if (contains(Mach2Tiles, frontTile)) playerInput(2);
            else if (contains(Mach3Tiles, frontTile)) playerInput(3);
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e){
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==exitB){
            this.setConvoComponentsVisible(false);
            frame.setFocusable(true);
            scriptIndex=0;
            textSpace.setText(NPCScript[scriptIndex]);
        }
        else if(e.getSource()==nextB){
            if (scriptIndex < NPCScript.length - 1) {
                scriptIndex++;
                textSpace.setText(NPCScript[scriptIndex]);
            }
        }
    }
    
    public void playerInput(int code) {

        playerCombination[index] = code;

        if (playerCombination[index] != correctCombination[index]) index = 0;

        index++;
  
        if (index == correctCombination.length) {
            JOptionPane.showMessageDialog(frame,  
                "You finished the level!", 
                "Information dialog",                                         
                JOptionPane.INFORMATION_MESSAGE);
            index = 0;
        }
    }
}