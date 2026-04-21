package PD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Map_5 implements KeyListener{
    
    JFrame frame;
    ImageIcon prisonWall1;
    ImageIcon prisonWall2;
    ImageIcon prisonWall3;
    ImageIcon prisonWallTop;
    ImageIcon prisonWallTop2;
    ImageIcon prisonFloor;
    ImageIcon prisonDoor;
    ImageIcon prisonDoorOpen;
    ImageIcon menuButton;
    
    ImageIcon mirror;
    ImageIcon mirrorCovered;
    ImageIcon sideDoor;
    ImageIcon laserHor;
    ImageIcon laserVer;
    ImageIcon crate;
    
    ImageIcon frontS;
    ImageIcon frontW;
    ImageIcon leftS;
    ImageIcon leftW;
    ImageIcon rightS;
    ImageIcon rightW;
    ImageIcon backS;
    ImageIcon backW;
    ImageIcon eyeBoss;
    
    JLabel tiles[];
    JLabel character[];
    int mapLayout[];
    int characterPlace[];
    int mapWidth = 17;
    int mapHeight = 11;
    int frameWidth = 850;
    int frameHeight = 550;
    int characterPosition;
    int characterMode;
    
    int mirror1Mode;
    int mirror2Mode;
    int mirror3Mode;
    int mirror4Mode;
    
    int mirror1;
    int mirror2;
    int mirror3;
    int mirror4;
    int crateLoc;
    int door;
    
    boolean key=false;
    
    int direction;
    int tileW = frameWidth / mapWidth;
    int tileH = frameHeight / mapHeight;
    
    public Map_5() {
        frame=new JFrame();
        
        mirror1Mode=0;
        mirror2Mode=0;
        mirror3Mode=0;
        mirror4Mode=0;
        
        mirror1=76;
        mirror2=105;
        mirror3=115;
        mirror4=144;
        crateLoc=110;
        door=42;
        
        prisonWall1 = new ImageIcon("PD Assets/Level 1 (Desert)/prisonWall1.png");
        prisonFloor = new ImageIcon("PD Assets/Level 1 (Desert)/prisonFloor.png");
        prisonWall2 = new ImageIcon("PD Assets/Level 1 (Desert)/prisonWall2.png");
        prisonWall3 = new ImageIcon("PD Assets/Level 1 (Desert)/prisonWall3.png");
        prisonWallTop = new ImageIcon("PD Assets/Level 1 (Desert)/prisonWallTop.png");
        prisonWallTop2 = new ImageIcon("PD Assets/Level 1 (Desert)/prisonWallTop2.png");
        prisonDoor = new ImageIcon("PD Assets/Level 1 (Desert)/prisonDoor.png");
        prisonDoorOpen = new ImageIcon("PD Assets/Level 1 (Desert)/openDoor.png");
        menuButton = new ImageIcon("PD Assets/Level 1 (Desert)/menuButton.png");
        
        mirror=new ImageIcon("PD Assets/Level 1 (Desert)/mirror.png");
        mirrorCovered=new ImageIcon("PD Assets/Level 1 (Desert)/coveredMirror.png");
        sideDoor=new ImageIcon("PD Assets/Level 1 (Desert)/doorSide.png");
        laserHor=new ImageIcon("PD Assets/Level 1 (Desert)/laserHor.png");
        laserVer=new ImageIcon("PD Assets/Level 1 (Desert)/laserVer.png");
        crate=new ImageIcon("PD Assets/Level 1 (Desert)/crate.png");
        
        frontS = new ImageIcon("PD Assets/Cody's Character/frontstand.png");
        frontW = new ImageIcon("PD Assets/Cody's Character/frontwalk.png");
        leftS = new ImageIcon("PD Assets/Cody's Character/leftstand.png");
        leftW = new ImageIcon("PD Assets/Cody's Character/leftwalk.png");
        rightS = new ImageIcon("PD Assets/Cody's Character/rightstand.png");
        rightW = new ImageIcon("PD Assets/Cody's Character/rightwalk.png");
        backS = new ImageIcon("PD Assets/Cody's Character/backstand.png");
        backW = new ImageIcon("PD Assets/Cody's Character/backwalk.png");
        eyeBoss = new ImageIcon("PD Assets/Level 1 (Desert)/eyeBoss.png");

        prisonWall1=new ImageIcon(prisonWall1.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        prisonWall2=new ImageIcon(prisonWall2.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        prisonWall3=new ImageIcon(prisonWall3.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        prisonWallTop=new ImageIcon(prisonWallTop.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        prisonWallTop2=new ImageIcon(prisonWallTop2.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        mirror=new ImageIcon(mirror.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT)); 
        mirrorCovered=new ImageIcon(mirrorCovered.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT)); 
        sideDoor=new ImageIcon(sideDoor.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        laserHor=new ImageIcon(laserHor.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        laserVer=new ImageIcon(laserVer.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        crate=new ImageIcon(crate.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        prisonFloor=new ImageIcon(prisonFloor.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        prisonDoor=new ImageIcon(prisonDoor.getImage().getScaledInstance(tileW, tileH, Image.SCALE_REPLICATE));
        prisonDoorOpen = new ImageIcon(prisonDoorOpen.getImage().getScaledInstance(tileW, tileH, Image.SCALE_REPLICATE));
        menuButton=new ImageIcon(menuButton.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        
        frontS = new ImageIcon(frontS.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        frontW = new ImageIcon(frontW.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        leftS = new ImageIcon(leftS.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        leftW = new ImageIcon(leftW.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        rightS = new ImageIcon(rightS.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        rightW = new ImageIcon(rightW.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        backS = new ImageIcon(backS.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        backW = new ImageIcon(backW.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        eyeBoss = new ImageIcon(eyeBoss.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        
        character = new JLabel[mapWidth * mapHeight];
        characterPlace = new int[]{
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 1,
            1, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 1,
            1, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 1,
            1, 5, 5, 5, 5, 5, 5, 5, 3, 5, 5, 5, 5, 5, 5, 5, 1,
            1, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 1,
            1, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 1,
            1, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 1,
            1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,};
        for (int i = 0; i < character.length; i++) {
            if (characterPlace[i] == 2) {
                character[i] = new JLabel(frontS);
                characterPosition = i;
            } else if (characterPlace[i] == 3) {
                character[i] = new JLabel(eyeBoss);
            } else if (characterPlace[i] == 4) {
                character[i] = new JLabel(laserVer);
            } else if (characterPlace[i] == 5) {
                character[i] = new JLabel(laserHor);
            } else {
                character[i] = new JLabel();
            }
        }
        
        tiles=new JLabel[mapWidth*mapHeight];
        mapLayout=new int[]{
            7, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 6,
            6, 3, 3, 3, 3, 2, 3, 3, 4, 2, 3, 3, 3, 3, 3, 2, 6,
            6, 3, 3, 2, 3, 3, 3, 3, 5, 3, 3, 3, 3, 1, 3, 3, 6, 
            6, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 6,
            6, 4, 4, 4, 4, 4, 4, 4, 8, 4, 4, 4, 4, 4, 4, 4, 6,
            6, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 6,
            6, 4, 4, 8, 4, 4, 4, 4, 4, 4, 4, 4, 4, 8, 4, 4, 6,
            6, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 6,
            6, 4, 4, 4, 4, 4, 4, 4, 8, 4, 4, 4, 4, 4, 4, 4, 6,
            6, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 6,
            6, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6,
        };
        
        for(int i=0;i<tiles.length;i++){
            switch (mapLayout[i]) {
                case 0:
                    tiles[i]=new JLabel(prisonWallTop);
                    break;
                case 1:
                    tiles[i]=new JLabel(prisonWall1);
                    break;
                case 2:
                    tiles[i]=new JLabel(prisonWall2);
                    break;
                case 3:
                    tiles[i]=new JLabel(prisonWall3);
                    break;
                case 4:
                    tiles[i]=new JLabel(prisonFloor);
                    break;
                case 5:
                    tiles[i]=new JLabel(prisonDoor);
                    break;
                case 6:
                    tiles[i]=new JLabel(prisonWallTop2);
                    break;
                case 7:
                    tiles[i]=new JLabel(menuButton);
                    break;
                case 8:
                    tiles[i]=new JLabel(mirrorCovered);
                    break;
                case 9:
                    tiles[i]=new JLabel(sideDoor);
                    break;
                default:
                    break;
            }
            
        }
}
    public void setFrame(){
        frame.setLayout(new GraphPaperLayout(new Dimension(mapWidth,mapHeight)));


        int x = 0, y = 0, w = 1, h = 1;
        for (int i = 0; i < character.length; i++) {
            frame.add(character[i], new Rectangle(x, y, w, h));
            x++;
            if (x % mapWidth == 0) {
                x = 0;
                y++;
            }
        }

        x = 0;
        y = 0;
        w = 1;
        h = 1;
        for (JLabel tile1 : tiles) {
            frame.add(tile1, new Rectangle(x, y, w, h));
            x++;
            if (x % mapWidth == 0) {
                x = 0;
                y++;
            }
        }
        frame.setSize(frameWidth,frameHeight);
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
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            direction = 3;
            if (characterPlace[characterPosition + 1] == 1 || characterPlace[characterPosition + 1] == 3 || characterPlace[characterPosition + 1] == 4 || characterPlace[characterPosition + 1] == 5) {
                if (characterMode == 0) {
                    character[characterPosition].setIcon(rightW);
                    characterMode = 1;
                } 
                else {
                    character[characterPosition].setIcon(rightS);
                    characterMode = 0;
                }
            } 
            else {
                character[characterPosition].setIcon(null);
                if (characterMode == 0) {
                    character[characterPosition + 1].setIcon(rightW);
                    characterMode = 1;
                } 
                else {
                    character[characterPosition + 1].setIcon(rightS);
                    characterMode = 0;
                }
                characterPosition++;
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            direction = 2;
            if (characterPlace[characterPosition - 1] == 1 || characterPlace[characterPosition - 1] == 3 || characterPlace[characterPosition - 1] == 4 || characterPlace[characterPosition - 1] == 5) {
                if (characterMode == 0) {
                    character[characterPosition].setIcon(leftW);
                    characterMode = 1;
                } 
                else {
                    character[characterPosition].setIcon(leftS);
                    characterMode = 0;
                }
            } 
            else {
                character[characterPosition].setIcon(null);
                if (characterMode == 0) {
                    character[characterPosition - 1].setIcon(leftW);
                    characterMode = 1;
                } 
                else {
                    character[characterPosition - 1].setIcon(leftS);
                    characterMode = 0;
                }
                characterPosition--;
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            direction = 1;
            if (characterPlace[characterPosition + mapWidth] == 1 || characterPlace[characterPosition + mapWidth] == 3 || characterPlace[characterPosition + mapWidth] == 4 || characterPlace[characterPosition + mapWidth] == 5) {
                if (characterMode == 0) {
                    character[characterPosition].setIcon(frontW);
                    characterMode = 1;
                } 
                else {
                    character[characterPosition].setIcon(frontS);
                    characterMode = 0;
                }
            } 
            else {
                character[characterPosition].setIcon(null);
                if (characterMode == 0) {
                    character[characterPosition + mapWidth].setIcon(frontW);
                    characterMode = 1;
                } 
                else {
                    character[characterPosition + mapWidth].setIcon(frontS);
                    characterMode = 0;
                }
                characterPosition += mapWidth;
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_UP) {
            direction = 0;
            if (characterPlace[characterPosition - mapWidth] == 1 || characterPlace[characterPosition - mapWidth] == 3 || characterPlace[characterPosition - mapWidth] == 4 || characterPlace[characterPosition - mapWidth] == 5) {
                if (characterMode == 0) {
                    character[characterPosition].setIcon(backW);
                    characterMode = 1;
                } 
                else {
                    character[characterPosition].setIcon(backS);
                    characterMode = 0;
                }
            } 
            else {
                character[characterPosition].setIcon(null);
                if (characterMode == 0) {
                    character[characterPosition - mapWidth].setIcon(backW);
                    characterMode = 1;
                } 
                else {
                    character[characterPosition - mapWidth].setIcon(backS);
                    characterMode = 0;
                }
                characterPosition -= mapWidth;
            }
            if((characterPosition) == 8) {
                Object[] options4 = {"True", "False"};
                        int choice4 = JOptionPane.showOptionDialog(frame,
                                "Would you like to proceed?",
                                "",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options4,
                                options4[0]);
                frame.dispose();
                StatesideRiver move = new StatesideRiver();
                move.setFrame();
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            int movement=0;
            if(direction==0){
                movement=-1*mapWidth;
            }
            else if(direction==1){
                movement=mapWidth;
            }
            else if(direction==2){
                movement=-1;
            }
            else if(direction==3){
                movement=1;
            }
            
            if(characterPosition+movement==mirror4){
                if(mirror4Mode==0){
                    int answer=0;
                    try{
                        JOptionPane.showMessageDialog(frame,
                                "This mirror is covered by something.. it seems I must answer\n"
                                        + "a riddle to uncover it.",
                                "Mirror",
                                JOptionPane.INFORMATION_MESSAGE);
                        String input = JOptionPane.showInputDialog(frame,
                                "A laser is blasted, with a width of 50 centimeters and a length of 12 meters."
                                        + " What is the area covered by the laser?"
                                        + " (Hint: A = lw; 1 cm = 0.01 m)",
                                "Riddle",
                                JOptionPane.INFORMATION_MESSAGE);
                        answer = Integer.parseInt(input);
                    }
                    catch (NumberFormatException E){
                        JOptionPane.showMessageDialog(frame,
                                "I need a number answer. This won't work.",
                                "Mirror",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                    
                    if(answer!=6){
                        JOptionPane.showMessageDialog(frame,
                                "This answer is wrong, I can't uncover the mirror."
                                        + "I should think about this more.",
                                "Mirror",
                                JOptionPane.INFORMATION_MESSAGE);
                        answer=0;
                    }
                    else{
                        JOptionPane.showMessageDialog(frame,
                                "I uncovered the mirror!"
                                        + " (Laser reflected! -1 Eye Health)",
                                "Mirror",
                                JOptionPane.INFORMATION_MESSAGE);
                        characterPlace[mirror4+mapWidth]=0;
                        mirror4Mode=1;
                        
                        tiles[mirror4].setIcon(mirror);
                        character[mirror4+mapWidth].setIcon(null);
                        
                        if(mirror1Mode==1&&mirror2Mode==1&&mirror3Mode==1&&mirror4Mode==1){
                            JOptionPane.showMessageDialog(frame,
                                    "I've reflected all of the Eye's lasers!"
                                            + "(0/4) Eye Health",
                                    "Mirror",
                                    JOptionPane.INFORMATION_MESSAGE);
                            characterPlace[mirror1+mapWidth]=0;
                            characterPlace[mirror4-mapWidth]=0;
                            characterPlace[103]=0;
                            characterPlace[104]=0;
                            characterPlace[106]=0;
                            characterPlace[107]=0;
                            characterPlace[108]=0;
                            characterPlace[109]=0;
                            characterPlace[111]=0;
                            characterPlace[112]=0;
                            characterPlace[113]=0;
                            characterPlace[114]=0;
                            characterPlace[116]=0;
                            characterPlace[117]=0;
                            
                            character[mirror1].setIcon(null);
                            character[mirror4].setIcon(null);
                            character[mirror1+mapWidth].setIcon(null);
                            character[mirror4-mapWidth].setIcon(null);
                            character[crateLoc].setIcon(null);
                            tiles[crateLoc].setIcon(crate);
                            character[103].setIcon(null);
                            character[104].setIcon(null);
                            character[105].setIcon(null);
                            character[106].setIcon(null);
                            character[107].setIcon(null);
                            character[108].setIcon(null);
                            character[109].setIcon(null);
                            character[110].setIcon(null);
                            character[111].setIcon(null);
                            character[112].setIcon(null);
                            character[113].setIcon(null);
                            character[114].setIcon(null);
                            character[115].setIcon(null);
                            character[116].setIcon(null);
                            character[117].setIcon(null);
                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(frame, 
                            "I'm done with this mirror. I should check out the other stuff here.",
                            "Mirror",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else if(characterPosition+movement==mirror3){
                if(mirror3Mode==0){
                    int answer=0;
                    try{
                        JOptionPane.showMessageDialog(frame,
                                "This mirror is covered by something.. it seems I must answer\n"
                                        + "a riddle to uncover it.",
                                "Mirror",
                                JOptionPane.INFORMATION_MESSAGE);
                        String input = JOptionPane.showInputDialog(frame,
                                "The pupils of the eye dilate, from a radius of 5 cm to a radius of 30 cm"
                                        + " What is the difference between the final and initial radii? ___π cm²"
                                        + " (Hint: A = πr²)",
                                "Riddle",
                                JOptionPane.INFORMATION_MESSAGE);
                        answer = Integer.parseInt(input);
                    }
                    catch (NumberFormatException E){
                        JOptionPane.showMessageDialog(frame,
                                "I need a number answer. This won't work.",
                                "Mirror",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                    
                    if(answer!=875){
                        JOptionPane.showMessageDialog(frame,
                                "This answer is wrong, I can't uncover the mirror."
                                        + "I should think about this more.",
                                "Mirror",
                                JOptionPane.INFORMATION_MESSAGE);
                        answer=0;
                    }
                    else{
                        JOptionPane.showMessageDialog(frame,
                                "I uncovered the mirror!",
                                "Mirror",
                                JOptionPane.INFORMATION_MESSAGE);
                        characterPlace[mirror3+1]=0;
                        characterPlace[mirror3+2]=0;
                        mirror3Mode=1;
                        
                        tiles[mirror3].setIcon(mirror);
                        character[mirror3+1].setIcon(null);
                        character[mirror3+2].setIcon(null);
                        
                        if(mirror1Mode==1&&mirror2Mode==1&&mirror3Mode==1&&mirror4Mode==1){
                            JOptionPane.showMessageDialog(frame,
                                    "I've reflected all of the Eye's lasers!"
                                            + "(0/4) Eye Health",
                                    "Mirror",
                                    JOptionPane.INFORMATION_MESSAGE);
                            characterPlace[mirror1+mapWidth]=0;
                            characterPlace[mirror4-mapWidth]=0;
                            characterPlace[103]=0;
                            characterPlace[104]=0;
                            characterPlace[106]=0;
                            characterPlace[107]=0;
                            characterPlace[108]=0;
                            characterPlace[109]=0;
                            characterPlace[111]=0;
                            characterPlace[112]=0;
                            characterPlace[113]=0;
                            characterPlace[114]=0;
                            characterPlace[116]=0;
                            characterPlace[117]=0;
                            
                            character[mirror1].setIcon(null);
                            character[mirror4].setIcon(null);
                            character[mirror1+mapWidth].setIcon(null);
                            character[mirror4-mapWidth].setIcon(null);
                            character[crateLoc].setIcon(null);
                            tiles[crateLoc].setIcon(crate);
                            character[103].setIcon(null);
                            character[104].setIcon(null);
                            character[105].setIcon(null);
                            character[106].setIcon(null);
                            character[107].setIcon(null);
                            character[108].setIcon(null);
                            character[109].setIcon(null);
                            character[110].setIcon(null);
                            character[111].setIcon(null);
                            character[112].setIcon(null);
                            character[113].setIcon(null);
                            character[114].setIcon(null);
                            character[115].setIcon(null);
                            character[116].setIcon(null);
                            character[117].setIcon(null);
                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(frame, 
                            "I'm done with this mirror. I should check out the other stuff here.",
                            "Mirror",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else if(characterPosition+movement==mirror2){
                if(mirror2Mode==0){
                    int answer=0;
                    try{
                        JOptionPane.showMessageDialog(frame,
                                "This mirror is covered by something.. it seems I must answer\n"
                                        + "a riddle to uncover it.",
                                "Mirror",
                                JOptionPane.INFORMATION_MESSAGE);
                        String input = JOptionPane.showInputDialog(frame,
                                "The dark pit is 5 meters wide and 20 meters long."
                                        + " What is the area of the pit?"
                                        + " (Hint: A = lw)",
                                "Riddle",
                                JOptionPane.INFORMATION_MESSAGE);
                        answer = Integer.parseInt(input);
                    }
                    catch (NumberFormatException E){
                        JOptionPane.showMessageDialog(frame,
                                "I need a number answer. This won't work.",
                                "Mirror",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                    
                    if(answer!=100){
                        JOptionPane.showMessageDialog(frame,
                                "This answer is wrong, I can't uncover the mirror."
                                        + "I should think about this more.",
                                "Mirror",
                                JOptionPane.INFORMATION_MESSAGE);
                        answer=0;
                    }
                    else{
                        JOptionPane.showMessageDialog(frame,
                                "I uncovered the mirror!",
                                "Mirror",
                                JOptionPane.INFORMATION_MESSAGE);
                        characterPlace[mirror2-1]=0;
                        characterPlace[mirror2-2]=0;
                        mirror2Mode=1;
                        
                        tiles[mirror2].setIcon(mirror);
                        character[mirror2-1].setIcon(null);
                        character[mirror2-2].setIcon(null);
                        
                        if(mirror1Mode==1&&mirror2Mode==1&&mirror3Mode==1&&mirror4Mode==1){
                            JOptionPane.showMessageDialog(frame,
                                    "I've reflected all of the Eye's lasers!"
                                            + "(0/4) Eye Health",
                                    "Mirror",
                                    JOptionPane.INFORMATION_MESSAGE);
                            characterPlace[mirror1+mapWidth]=0;
                            characterPlace[mirror4-mapWidth]=0;
                            characterPlace[103]=0;
                            characterPlace[104]=0;
                            characterPlace[106]=0;
                            characterPlace[107]=0;
                            characterPlace[108]=0;
                            characterPlace[109]=0;
                            characterPlace[111]=0;
                            characterPlace[112]=0;
                            characterPlace[113]=0;
                            characterPlace[114]=0;
                            characterPlace[116]=0;
                            characterPlace[117]=0;
                            
                            character[mirror1].setIcon(null);
                            character[mirror4].setIcon(null);
                            character[mirror1+mapWidth].setIcon(null);
                            character[mirror4-mapWidth].setIcon(null);
                            character[crateLoc].setIcon(null);
                            tiles[crateLoc].setIcon(crate);
                            character[103].setIcon(null);
                            character[104].setIcon(null);
                            character[105].setIcon(null);
                            character[106].setIcon(null);
                            character[107].setIcon(null);
                            character[108].setIcon(null);
                            character[109].setIcon(null);
                            character[110].setIcon(null);
                            character[111].setIcon(null);
                            character[112].setIcon(null);
                            character[113].setIcon(null);
                            character[114].setIcon(null);
                            character[115].setIcon(null);
                            character[116].setIcon(null);
                            character[117].setIcon(null);
                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(frame, 
                            "I'm done with this mirror. I should check out the other stuff here.",
                            "Mirror",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else if(characterPosition+movement==mirror1){
                if(mirror1Mode==0){
                    int answer=0;
                    try{
                        JOptionPane.showMessageDialog(frame,
                                "This mirror is covered by something.. it seems I must answer\n"
                                        + "a riddle to uncover it.",
                                "Mirror",
                                JOptionPane.INFORMATION_MESSAGE);
                        String input = JOptionPane.showInputDialog(frame,
                                "An eye's gaze is triangular, spreading 5 meters wide and reaching a distance of 10 meters"
                                        + " What is the area of the eye's gaze?"
                                        + " (Hint: A = ½bh)",
                                "Riddle",
                                JOptionPane.INFORMATION_MESSAGE);
                        answer = Integer.parseInt(input);
                    }
                    catch (NumberFormatException E){
                        JOptionPane.showMessageDialog(frame,
                                "I need a number answer. This won't work.",
                                "Mirror",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                    
                    if(answer!=25){
                        JOptionPane.showMessageDialog(frame,
                                "This answer is wrong, I can't uncover the mirror."
                                        + "I should think about this more.",
                                "Mirror",
                                JOptionPane.INFORMATION_MESSAGE);
                        answer=0;
                    }
                    else{
                        JOptionPane.showMessageDialog(frame,
                                "I uncovered the mirror!",
                                "Mirror",
                                JOptionPane.INFORMATION_MESSAGE);
                        characterPlace[mirror1-mapWidth]=0;
                        mirror1Mode=1;
                        
                        tiles[mirror1].setIcon(mirror);
                        character[mirror1-mapWidth].setIcon(null);
                        
                        if(mirror1Mode==1&&mirror2Mode==1&&mirror3Mode==1&&mirror4Mode==1){
                            JOptionPane.showMessageDialog(frame,
                                    "I've reflected all of the Eye's lasers!"
                                            + "(0/4) Eye Health",
                                    "Mirror",
                                    JOptionPane.INFORMATION_MESSAGE);
                            characterPlace[mirror1+mapWidth]=0;
                            characterPlace[mirror4-mapWidth]=0;
                            characterPlace[103]=0;
                            characterPlace[104]=0;
                            characterPlace[106]=0;
                            characterPlace[107]=0;
                            characterPlace[108]=0;
                            characterPlace[109]=0;
                            characterPlace[111]=0;
                            characterPlace[112]=0;
                            characterPlace[113]=0;
                            characterPlace[114]=0;
                            characterPlace[116]=0;
                            characterPlace[117]=0;
                            
                            character[mirror1].setIcon(null);
                            character[mirror4].setIcon(null);
                            character[mirror1+mapWidth].setIcon(null);
                            character[mirror4-mapWidth].setIcon(null);
                            character[crateLoc].setIcon(null);
                            tiles[crateLoc].setIcon(crate);
                            character[103].setIcon(null);
                            character[104].setIcon(null);
                            character[105].setIcon(null);
                            character[106].setIcon(null);
                            character[107].setIcon(null);
                            character[108].setIcon(null);
                            character[109].setIcon(null);
                            character[110].setIcon(null);
                            character[111].setIcon(null);
                            character[112].setIcon(null);
                            character[113].setIcon(null);
                            character[114].setIcon(null);
                            character[115].setIcon(null);
                            character[116].setIcon(null);
                            character[117].setIcon(null);
                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(frame, 
                            "I'm done with this mirror. I should check out the other stuff here.",
                            "Mirror",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else if(characterPosition+movement==crateLoc){
                if(key){
                    JOptionPane.showMessageDialog(frame,
                            "The crate is empty. You already searched through this crate.",
                            "Crate",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(frame,
                            "You opened the crate and found a Door Key!",
                            "Crate",
                            JOptionPane.INFORMATION_MESSAGE);
                    key=true;
                }
            }
            else if(characterPosition+movement==door){
                if(key){
                    characterPlace[door]=0;
                    characterPlace[door-mapWidth]=0;
                    characterPlace[door-(mapWidth*2)]=0;
                    tiles[door].setIcon(prisonDoorOpen);
                    door=0;
                }
                else{
                    JOptionPane.showMessageDialog(frame,
                            "This door is locked. I should look around for a key somewhere.",
                            "Hint",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
    public static void main(String[] args) {
        Map_5 x = new Map_5();
        x.setFrame();
    }
}
//members: Simona Roda, Mark de la Cruz, Safina Ibañez