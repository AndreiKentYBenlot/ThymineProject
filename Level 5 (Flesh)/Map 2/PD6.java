package com.mycompany.pdcs4;

//Members: Badilles, Salvo, Sia

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PD6 implements KeyListener,ActionListener{
    
    TendrilNPC tendrilNPC;
    
    JFrame frame;
    ImageIcon wall;
    ImageIcon ground;
    ImageIcon tendril;
    ImageIcon button;
    ImageIcon frontS;
    ImageIcon frontW;
    ImageIcon leftS;
    ImageIcon leftW;
    ImageIcon rightS;
    ImageIcon rightW;
    ImageIcon backS;
    ImageIcon backW;
    ImageIcon blackTile;
    JLabel chatSpace;
    JLabel textSpace;
    JLabel tiles[];
    JLabel character[];
    String NPCScript[];
    JButton choices[];
    JButton nextB;
    JButton exitB;
    int characterPlace[];
    int mapLayout[];
    private int characterPosition;
    int leverLocation;
    int tendLocation;
    int characterMode;
    int direction;
    String Questions[];
    String Answers[];
    int scriptIndex;
    int questionsIndex;
    int answerKey;
    int quizMode;
    int correctCount;
    int mapWidth=9;
    int mapHeight=9;
    int frameWidth=450;
    int frameHeight=450;
    
    public PD6(){
        characterPosition=-1;
        characterMode=0;
        leverLocation=-1;
        tendLocation=-1;
        direction=-1;
        scriptIndex=0;
        questionsIndex=0;
        answerKey=-1;
        quizMode=0;
        correctCount=0;
        
        NPCScript=new String[]{
            "A tendril blocks the path.",
            "You must answer its question to pass.",
        };
        
        Questions=new String[]{
            "What is the powerhouse of the cell?",
            "What is the interaction between a virus and its host?"
        };

        Answers=new String[]{
            "Mitochondria_Ribosome_Golgi Apparatus_Cellulose_0",
            "Mutualism_Commensalism_Amensalism_Parasitism_3"
        };
        
        blackTile = new ImageIcon("Images/black.png");
        blackTile = new ImageIcon(blackTile.getImage().getScaledInstance(frameWidth/mapWidth, frameHeight/mapHeight, Image.SCALE_DEFAULT));
    
        frame=new JFrame();
        chatSpace=new JLabel();
        textSpace=new JLabel(NPCScript[scriptIndex]);
        scriptIndex++;
        choices=new JButton[4];
        for(int i=0;i<4;i++) choices[i]=new JButton((i+1)+"");
        nextB=new JButton("Next");
        exitB=new JButton("Cancel");
        
        frame=new JFrame();
        wall=new ImageIcon("Images/wall.png");
        ground=new ImageIcon("Images/MeatTile1.png");
        tendril=new ImageIcon("Images/tendril.png");
        button=new ImageIcon("Images/lever.png");
        frontS=new ImageIcon("Images/frontstand.png");
        frontW=new ImageIcon("Images/frontwalk.png");
        leftS=new ImageIcon("Images/leftstand.png");
        leftW=new ImageIcon("Images/leftwalk.png");
        rightS=new ImageIcon("Images/rightstand.png");
        rightW=new ImageIcon("Images/rightwalk.png");
        backS=new ImageIcon("Images/backstand.png");
        backW=new ImageIcon("Images/backwalk.png");
        
        wall=new ImageIcon(wall.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        ground=new ImageIcon(ground.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        tendril=new ImageIcon(tendril.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        button=new ImageIcon(button.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        frontS=new ImageIcon(frontS.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        frontW=new ImageIcon(frontW.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        leftS=new ImageIcon(leftS.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        leftW=new ImageIcon(leftW.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        rightS=new ImageIcon(rightS.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        rightW=new ImageIcon(rightW.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        backS=new ImageIcon(backS.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        backW=new ImageIcon(backW.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        
        tiles=new JLabel[mapWidth*mapHeight];
        mapLayout=new int[]{
            0,0,0,0,0,0,0,0,0,
            0,1,1,1,0,1,1,1,0,
            0,1,0,1,1,1,0,1,0,
            0,1,0,1,0,0,0,0,0,
            0,0,0,1,1,2,1,1,0,
            0,1,1,1,0,1,0,1,0,
            0,1,0,1,0,1,0,1,0,
            0,3,0,1,0,1,1,1,0,
            0,0,0,0,0,0,0,0,0
        };
        for(int i=0;i<tiles.length;i++){
            if(mapLayout[i]==0) tiles[i]=new JLabel(wall);
            else if(mapLayout[i]==1) tiles[i]=new JLabel(ground);
            else if(mapLayout[i]==2) tiles[i]=new JLabel(ground);
            else if(mapLayout[i]==3) {
                tiles[i]=new JLabel(button);
                leverLocation = i;
            }
        }
        
        character=new JLabel[mapWidth*mapHeight];
        characterPlace=new int[]{
            0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,2,1,0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0
        };
        for(int i = 0; i < character.length; i++){
            if(characterPlace[i] == 1){
                character[i] = new JLabel(frontS);
                characterPosition = i;
            }
            else if(characterPlace[i] == 2){
                tendrilNPC = new TendrilNPC("Tendril");
                character[i] = new JLabel(tendril);
                tendLocation = i;
            }
            else character[i]=new JLabel();
        }
    }
    
     public void updateVisibility(){
        int playerX = characterPosition % mapWidth;
        int playerY = characterPosition / mapWidth;

        for(int y = 0; y < mapHeight; y++){
           for(int x = 0; x < mapWidth; x++){
               int index = y * mapWidth + x;
               
               if(Math.abs(x - playerX) <= 1 && Math.abs(y - playerY) <= 1){
                   if(mapLayout[index] == 0) tiles[index].setIcon(wall);
                   else if(mapLayout[index] == 1) tiles[index].setIcon(ground);
                   else if(mapLayout[index] == 2) tiles[index].setIcon(ground);
                  else if(mapLayout[index] == 3) tiles[index].setIcon(button);
              } else {
                   tiles[index].setIcon(blackTile);
                }
            }
        }
        for(int i = 0; i < character.length; i++){
            if(Math.abs((i % mapWidth) - playerX) <= 1 && Math.abs((i / mapWidth) - playerY) <= 1){
            character[i].setVisible(true);
            } else {
               character[i].setVisible(false);
            }
        }
    }
    
    public void setAllChatComponentsVisible(boolean b){
        textSpace.setVisible(b);
        choices[0].setVisible(b);
        choices[1].setVisible(b);
        choices[2].setVisible(b);
        choices[3].setVisible(b);
        exitB.setVisible(b);
        nextB.setVisible(b);
        chatSpace.setVisible(b);
    }
    
    public void setConvoComponentsVisible(boolean b){
        textSpace.setVisible(b);
        exitB.setVisible(b);
        nextB.setVisible(b);
        chatSpace.setVisible(b);
    }
    
    public void setChoicesVisible(boolean b){
        choices[0].setVisible(b);
        choices[1].setVisible(b);
        choices[2].setVisible(b);
        choices[3].setVisible(b);
    }
    
    boolean collision(int index) {
        if (index < 0 || index >= mapLayout.length)
            return true;
            
        return mapLayout[index] == 0 || mapLayout[index] == 2 || mapLayout[index] == 3;
    }
    
    public void setFrame(){
        frame.setLayout(new GraphPaperLayout(new Dimension(mapWidth,mapHeight)));
        
        chatSpace.setOpaque(true);
        chatSpace.setBackground(new Color(243,246,232));
        
        frame.add(textSpace, new Rectangle(1,7,7,1));
        frame.add(choices[0], new Rectangle(1,8,2,1));
        frame.add(choices[1], new Rectangle(3,8,2,1));
        frame.add(choices[2], new Rectangle(5,8,2,1));
        frame.add(choices[3], new Rectangle(7,8,2,1));
        frame.add(exitB, new Rectangle(1,5,2,1));
        frame.add(nextB, new Rectangle(6,5,2,1));
        frame.add(chatSpace, new Rectangle(0,6,9,4));
        
        this.setAllChatComponentsVisible(false);
        
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
        choices[0].addActionListener(this);
        choices[1].addActionListener(this);
        choices[2].addActionListener(this);
        choices[3].addActionListener(this);
        
        updateVisibility();
    }
    
    @Override
    public void keyTyped(KeyEvent e){
        
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            direction = 3;
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
             updateVisibility();
            }
        }
        else if(e.getKeyCode()==KeyEvent.VK_LEFT){
            direction = 2;
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
                updateVisibility();
            }
        }
        else if(e.getKeyCode()==KeyEvent.VK_DOWN){
            direction = 1;
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
                updateVisibility();
            }
        }
        else if(e.getKeyCode()==KeyEvent.VK_UP){
            direction = 0;
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
                updateVisibility();
            }
        }
        else if(e.getKeyCode()==KeyEvent.VK_E){
            int movement=0;
            if(direction==0) movement=-1*mapWidth;
            else if(direction==1) movement=mapWidth;
            else if(direction==2) movement=-1;
            else if(direction==3) movement=1;
            if(characterPosition + movement == tendLocation){
                setConvoComponentsVisible(true);
                tendrilNPC.interact(quizMode == 1);
            }
            else if(characterPosition+movement== leverLocation && tendLocation == -1){
                winLevel();
            }
        }
    }
    
    public void winLevel(){
        JOptionPane.showMessageDialog(frame,  
                "You finished the level!", 
                "Information dialog",                                         
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    @Override
    public void keyReleased(KeyEvent e){
        
    }
    
    public void loadNextQuestion(){
        textSpace.setText(Questions[questionsIndex]);
        String answersArray[]=Answers[questionsIndex].split("_");
        choices[0].setText(answersArray[0]);
        choices[1].setText(answersArray[1]);
        choices[2].setText(answersArray[2]);
        choices[3].setText(answersArray[3]);
        answerKey=Integer.parseInt(answersArray[4]);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==exitB){
            this.setAllChatComponentsVisible(false);
            frame.setFocusable(true);
            quizMode=0;
        }
        else if(e.getSource()==nextB){
            if(quizMode==0){
                correctCount=0;
                this.setChoicesVisible(true);
                questionsIndex=0;
                loadNextQuestion();
                quizMode=1;
            }
            else{
                questionsIndex++;
                if(questionsIndex<Questions.length){
                    loadNextQuestion();
                    this.setChoicesVisible(true);
                }
                else{
                    if(correctCount == Questions.length){
                        textSpace.setText("The tendril disappears.");
                        character[tendLocation].setIcon(null);
                        mapLayout[tendLocation] = 1;
                        tendrilNPC.setBlocking(false);
                        tendLocation = -1;
                    }
                    else{
                        textSpace.setText("The tendril stays.");
                    }
                }
            }
        }
        else if(e.getSource()==choices[0]){
            this.setChoicesVisible(false);
            if(answerKey==0){
                textSpace.setText("You're correct!");
                correctCount++;
            }
            else{
                textSpace.setText("You're wrong!");
            }
        }
        else if(e.getSource()==choices[1]){
            this.setChoicesVisible(false);
            if(answerKey==1){
                textSpace.setText("You're correct!");
                correctCount++;
            }
            else{
                textSpace.setText("You're wrong!");
            }
        }
        else if(e.getSource()==choices[2]){
            this.setChoicesVisible(false);
            if(answerKey==2){
                textSpace.setText("You're correct!");
                correctCount++;
            }
            else{
                textSpace.setText("You're wrong!");
            }
        }
        else if(e.getSource()==choices[3]){
            this.setChoicesVisible(false);
            if(answerKey==3){
                textSpace.setText("You're correct!");
                correctCount++;
            }
            else{
                textSpace.setText("You're wrong!");
            }
        }
    }
}

class CharacterEntity {
    private String name;
   
    public CharacterEntity(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void interact() {
        System.out.println(name + " is an NPC");
    }
}

class TendrilNPC extends CharacterEntity {
    private boolean isBlocking;
    
    public TendrilNPC(String name) {
        super(name);
        this.isBlocking = true;
    }
    
    public boolean isBlocking() {
        return isBlocking;
    }
    
    public void setBlocking(boolean blocking) {
        this.isBlocking = blocking;
    }
    
    @Override
    public void interact() {
        if(isBlocking)
            System.out.println(getName() + " blocks your path.");
        else
            System.out.println(getName() + " is gone.");
    }
    
    public void interact(boolean quizMode) {
        if(quizMode)
            System.out.println(getName() + " is ready.");
        else
            interact();
    }
}
