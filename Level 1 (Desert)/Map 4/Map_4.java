/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Map_4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Map_4 implements KeyListener{
    JFrame frame;
    ImageIcon prisonWall1;
    ImageIcon prisonWall2;
    ImageIcon prisonWall3;
    ImageIcon prisonWallTop;
    ImageIcon prisonWallTop2;
    ImageIcon prisonFloor;
    ImageIcon menuButton;
    ImageIcon prisonWallCrack1;
    ImageIcon prisonWallCrack2;
    ImageIcon sideDoor;
    ImageIcon sideDoorOpen;
    ImageIcon crate;
    ImageIcon alarm;
    ImageIcon openWall1;
    ImageIcon openWall2;
    ImageIcon openWall3;
    ImageIcon openWall4;
    
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
    int mapLayout[];
    int characterPlace[];
    int mapWidth = 17;
    int mapHeight = 11;
    int frameWidth = 850;
    int frameHeight = 550;
    int characterPosition;
    int characterMode;
    
    int door1;
    int door2;
    int crate1;
    int crate2;
    int wallCrack1;
    int wallCrack2;
    int wallCrack3;
    int wallCrack4;
    
    boolean key=false;
    boolean dynamite=false;
    
    int direction;
    int tileW = frameWidth / mapWidth;
    int tileH = frameHeight / mapHeight; //hi sir testing out the rendering things cgpt gave me
    
    public Map_4(){
        frame=new JFrame();
        
        door1=147;
        door2=141;
        crate1=151;
        crate2=137;
        wallCrack1=25;
        wallCrack2=26;
        wallCrack3=42;
        wallCrack4=43;
        
        prisonWall1=new ImageIcon("Images/prisonWall1.png");
        prisonFloor=new ImageIcon("Images/prisonFloor.png");
        prisonWall2=new ImageIcon("Images/prisonWall2.png");
        prisonWall3=new ImageIcon("Images/prisonWall3.png");
        prisonWallTop=new ImageIcon("Images/prisonWallTop.png");
        prisonWallTop2=new ImageIcon("Images/prisonWallTop2.png");
        menuButton=new ImageIcon("Images/menuButton.png");
        prisonWallCrack1=new ImageIcon("Images/prisonWallCrack1.png");
        prisonWallCrack2=new ImageIcon("Images/prisonWallCrack2.png");
        sideDoor=new ImageIcon("Images/doorSide.png");
        sideDoorOpen=new ImageIcon("Images/doorSideOpen.png");
        crate=new ImageIcon("Images/crate.png");
        alarm=new ImageIcon("Images/alarm.png");
        openWall1=new ImageIcon("Images/openWall1.png");
        openWall2=new ImageIcon("Images/openWall2.png");
        openWall3=new ImageIcon("Images/openWall3.png");
        openWall4=new ImageIcon("Images/openWall4.png");
        
        frontS = new ImageIcon("Images/frontstand.png");
        frontW = new ImageIcon("Images/frontwalk.png");
        leftS = new ImageIcon("Images/leftstand.png");
        leftW = new ImageIcon("Images/leftwalk.png");
        rightS = new ImageIcon("Images/rightstand.png");
        rightW = new ImageIcon("Images/rightwalk.png");
        backS = new ImageIcon("Images/backstand.png");
        backW = new ImageIcon("Images/backwalk.png");

        prisonWall1=new ImageIcon(prisonWall1.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        prisonWall2=new ImageIcon(prisonWall2.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        prisonWall3=new ImageIcon(prisonWall3.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        prisonWallTop=new ImageIcon(prisonWallTop.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        prisonWallTop2=new ImageIcon(prisonWallTop2.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        prisonWallCrack1=new ImageIcon(prisonWallCrack1.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        prisonWallCrack2=new ImageIcon(prisonWallCrack2.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        sideDoor=new ImageIcon(sideDoor.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        prisonFloor=new ImageIcon(prisonFloor.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        menuButton=new ImageIcon(menuButton.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        crate=new ImageIcon(crate.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        alarm=new ImageIcon(alarm.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        openWall1=new ImageIcon(openWall1.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        openWall2=new ImageIcon(openWall2.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        openWall3=new ImageIcon(openWall3.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        openWall4=new ImageIcon(openWall4.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        
        frontS = new ImageIcon(frontS.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        frontW = new ImageIcon(frontW.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        leftS = new ImageIcon(leftS.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        leftW = new ImageIcon(leftW.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        rightS = new ImageIcon(rightS.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        rightW = new ImageIcon(rightW.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        backS = new ImageIcon(backS.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        backW = new ImageIcon(backW.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        
        character = new JLabel[mapWidth * mapHeight];
        characterPlace = new int[]{
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
            1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
            1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1,
            1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1,
            1, 0, 0, 0, 0, 1, 0, 0, 2, 0, 0, 1, 0, 0, 0, 0, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,};
        for (int i = 0; i < character.length; i++) {
            if (characterPlace[i] == 2) {
                character[i] = new JLabel(frontS);
                characterPosition = i;
            } else {
                character[i] = new JLabel();
            }
        }
        
        tiles=new JLabel[mapWidth*mapHeight];
        mapLayout=new int[]{
            6, 0,  0,  0, 0, 0, 0,  0, 0, 0, 0,  0, 0, 0, 0,  0,  5,
            5, 3,  11, 3, 3, 2, 11, 3, 3, 8, 11, 3, 3, 3, 11, 2,  5,
            5, 3,  3,  2, 3, 3, 3,  3, 7, 3, 3,  3, 3, 1, 3,  3,  5, 
            5, 4,  4,  4, 4, 4, 4,  4, 4, 4, 4,  4, 4, 4, 4,  4,  5,
            5, 4,  4,  4, 4, 4, 4,  4, 4, 4, 4,  4, 4, 4, 4,  4,  5,
            5, 0,  0,  0, 0, 0, 4,  4, 4, 4, 4,  0, 0, 0, 0,  0,  5,
            5, 3,  3,  3, 3, 5, 4,  4, 4, 4, 4,  5, 3, 3, 3,  3,  5,
            5, 3,  3,  3, 3, 5, 4,  4, 4, 4, 4,  5, 3, 3, 3,  3,  5,
            5, 10, 4,  4, 4, 9, 4,  4, 4, 4, 4,  9, 4, 4, 4,  10, 5,
            5, 4,  4,  4, 4, 5, 4,  4, 4, 4, 4,  5, 4, 4, 4,  4,  5,
            5, 0,  0,  0, 0, 0, 0,  0, 0, 0, 0,  0, 0, 0, 0,  0,  5,
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
                    tiles[i]=new JLabel(prisonWallTop2);
                    break;
                case 6:
                    tiles[i]=new JLabel(menuButton);
                    break;
                case 7:
                    tiles[i]=new JLabel(prisonWallCrack1);
                    break;
                case 8:
                    tiles[i]=new JLabel(prisonWallCrack2);
                    break;
                case 9:
                    tiles[i]=new JLabel(sideDoor);
                    break;
                case 10:
                    tiles[i]=new JLabel(crate);
                    break;
                case 11:
                    tiles[i]=new JLabel(alarm);
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
        frame.setSize(frameWidth, frameHeight);
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
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            direction = 3;
            if (characterPlace[characterPosition + 1] == 1 || characterPlace[characterPosition + 1] == 3 || characterPlace[characterPosition + 1] == 4) {
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
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            direction = 2;
            if (characterPlace[characterPosition - 1] == 1 || characterPlace[characterPosition - 1] == 3 || characterPlace[characterPosition - 1] == 4) {
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
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            direction = 1;
            if (characterPlace[characterPosition + mapWidth] == 1) {
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
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            direction = 0;
            if (characterPlace[characterPosition - mapWidth] == 1 || characterPlace[characterPosition - mapWidth] == 3 || characterPlace[characterPosition - mapWidth] == 4) {
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
        } 
        else if (e.getKeyCode() == KeyEvent.VK_SPACE){
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
            if(characterPosition+movement==door1){
                characterPlace[door1]=0;
                tiles[door1].setIcon(sideDoorOpen);
                door1=0;
            }
            else if(characterPosition+movement==door2){
                if(key){
                    characterPlace[door2]=0;
                    tiles[door2].setIcon(sideDoorOpen);
                    door2=0;
                }
                else{
                    JOptionPane.showMessageDialog(frame,
                            "This door is locked. I should look around for a key somewhere.",
                            "Hint",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else if(characterPosition+movement==crate1){
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
            else if(characterPosition+movement==crate2){
                if(dynamite){
                    JOptionPane.showMessageDialog(frame,
                            "The crate is empty. You already searched through this crate.",
                            "Crate",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(frame,
                            "You opened the crate and found Dynamite!",
                            "Crate",
                            JOptionPane.INFORMATION_MESSAGE);
                    dynamite=true;
                }
            }
            else if(characterPosition+movement==wallCrack3 || characterPosition+movement==wallCrack4){
                if(dynamite){
                    JOptionPane.showMessageDialog(frame,
                            "I can use the Dynamite to blast through the wall.. but how many should I use?",
                            "Wall",
                            JOptionPane.INFORMATION_MESSAGE);
                    int dynamiteAmt=0;
                    do{
                        try{
                            JOptionPane.showMessageDialog(frame,
                                    "The crack is 80cm in diameter, and each Dynamite has a blast radius of 10 cm. Each Dynamite added will increase the blast radius by 10cm. "
                                            + "There's also an alarm 50cm away from the center of the crack, and I have to be sure not to set it off.",
                                    "Wall",
                                    JOptionPane.INFORMATION_MESSAGE);
                            String input= JOptionPane.showInputDialog(frame,
                                    "How much Dynamite should I use? (Hint: A = πr²)");
                            dynamiteAmt=Integer.parseInt(input);
                        }
                        catch (NumberFormatException E){
                            JOptionPane.showMessageDialog(frame,
                                    "I need a number of dynamite to use. This won't work.",
                                    "Wall",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                        
                        if(dynamiteAmt<4){
                            JOptionPane.showMessageDialog(frame,
                                    "This might not be strong enough to break the wall open.. I should think about this more.",
                                    "Wall",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                        else if(dynamiteAmt>4){
                            JOptionPane.showMessageDialog(frame,
                                    "If I use too much I might set off the alarm.. I should think about this more.",
                                    "Wall",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                        else if(dynamiteAmt==4){
                            JOptionPane.showMessageDialog(frame,
                                    "I successfully blew the wall open!",
                                    "Wall",
                                    JOptionPane.INFORMATION_MESSAGE);
                            characterPlace[wallCrack1]=0;
                            characterPlace[wallCrack2]=0;
                            characterPlace[wallCrack3]=0;
                            characterPlace[wallCrack4]=0;
                            tiles[wallCrack1].setIcon(openWall1);
                            tiles[wallCrack2].setIcon(openWall2);
                            tiles[wallCrack3].setIcon(openWall3);
                            tiles[wallCrack4].setIcon(openWall4);
                            wallCrack1=0;
                            wallCrack2=0;
                            wallCrack3=0;
                            wallCrack4=0;
                        }
                    }while(dynamiteAmt!=4);
                }
                else{
                    JOptionPane.showMessageDialog(frame,
                            "This part of the wall is cracked. Maybe I could break it open with something.",
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
        Map_4 x = new Map_4();
        x.setFrame();
    }
}
//the keyListener counts as override for our oop concept <3
//exception handling: number format exception for answer input

//objective: one of the crates has contraband in it, and the character finds dynamite
// the player must get the correct amount of dynamite to blow up the cracked wall but to also not set off alarms
// each dynamite has a set area for it to explode, the player must calculate for the correct total circular area to blow up to escape the prison
// group members : Simona Roda, Safina Ibanez, Mark De la Cruz

