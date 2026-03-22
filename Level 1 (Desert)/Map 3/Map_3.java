/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Map_3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Mark Angelo
 */
public class Map_3 implements KeyListener, ActionListener {

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

    ImageIcon frontS;
    ImageIcon frontW;
    ImageIcon leftS;
    ImageIcon leftW;
    ImageIcon rightS;
    ImageIcon rightW;
    ImageIcon backS;
    ImageIcon backW;
    ImageIcon npc1;
    ImageIcon npc2;

    JLabel chatSpace;
    JLabel textSpace;
    JButton nextB;
    JButton exitB;

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

    int npc1Location;
    int npc2Location;
    String npc1Script1[];
    String npc1Script2[];
    String npc1Script3[];
    String npc2Script1[];
    String npc2Script2[];
    String npc2Script3[];
    String Questions[];
    String Answers[];
    String Choices[];
    int scriptIndex;
    int questionsIndex;

    int chat;
    int quiz1Mode;
    int quiz2Mode;
    int randomizedNumbers[];
    boolean firstTry;

    int doorA1;
    int doorB1;
    int doorC1;
    int doorD1;
    int doorE1;
    int doorF1;
    int doorA2;
    int doorB2;
    int doorC2;
    int doorD2;
    int doorE2;
    int doorF2;

    int direction;
    int tileW = frameWidth / mapWidth;
    int tileH = frameHeight / mapHeight; //hi sir testing out the rendering things cgpt gave me

    public Map_3() {
        frame = new JFrame();
        characterPosition = -1;
        characterMode = 0;
        npc1Location = -1;
        npc2Location = -1;
        direction = -1;
        scriptIndex = 0;
        questionsIndex = 0;
        chat = 0;
        quiz1Mode = 0;
        quiz2Mode = 0;
        firstTry = true;

        randomizedNumbers = new int[]{-1, -1};
        Choices = new String[]{" ", " ", " ", " ", " ", " "};

        npc1Script1 = new String[]{
            "HEY!! WHY ARE YOU OUT OF YOUR CELL!!!!",
            "I DON'T LIKE THAT.",
            "YOU DON'T WANNA GO BACK? WELL!...",
            "Guess I can't force you.",
            "BUT I'M NOT LETTING YOU GO ANY FURTHER!!",
            "Unless.. you answer a riddle of mine..",
            "BUT BEWARE, FOR THESE ARE NO ORDINARY RIDDLES!!!",
            "Wait, really? You'll answer them?",
            "WELL, YOU'LL NEVER BE ABLE TO ANSWER THEM ANYWAY!! HAHAHAHA",
            "The riddle is.."
        };

        /*npc1Script2 = new String[]{
            "You got the answer wrong? HAHAHAHA, TOO BAD!!",
            "What? You want to try again?",
            "WHATEVER, IT'S NOT LIKE YOU CAN EVER GET THEM RIGHT!! HAHAHA",
            "The riddle is.."
        };*/
        //for future purposes

        npc1Script3 = new String[]{
            "WHAT?! YOU GOT THE ANSWER RIGHT??",
            "Alright, fine. You can go ahead.",
            "BUT THIS ISN'T OVER!! That other guard will never let you pass, HAHAHAHA"
        };

        npc2Script1 = new String[]{
            "Oh, hey dawg. What're you doing out here?",
            "Oh right, did the guard over there make you answer his \"riddles\"?",
            "Yeah, he just made all of that up.",
            "Anyway, I can't really let you go any further. Sorry, dawg.",
            "Huh? You want to answer riddles from me too?",
            "..Sure, why not? I'll let you pass if you answer correct.",
            "Hm.. what should I ask you?..",
            "Alright, I got it. The riddle is.."
        };
        
        npc2Script3 = new String[]{
            "Oh, you got that riddle right?",
            "Dang, I guess I didn't make it too hard for you.",
            "Alright, you can go on ahead."
        };

        Questions = new String[]{
            "This room is 2 meters wide and 13 meters long. What is the area of the room?",
            "<html>I have a cat at home, and she's getting bigger. I need to buy a circular catbed with a diameter twice her size, 20 centimeters. How much area will my new catbed take up?</html>",
            "If an eye has a radius of 3 inches, what is the eye's area?",
            "<html>A shriek emits a sound wave that travels and forms a triangular shape. It reaches a length of 10 meters, and spreads 25 meters wide. How much area did the sound wave travel over?</html>",
            "What is 9 + 10?"
        };

        Answers = new String[]{
            "26 m²_22 m²_36 m²_26 nm²_25 m²_30 m²",
            "400π cm²_200π cm²_20π cm²_40π cm²_400π m²_100π cm²",
            "9π in²_30π in²_6π in²_90π in²_9π cm²_3π in²",
            "125 m²_250 m²_50 m²_200 m²_100 m²_150 m²",
            "19_91_67_18_1.9_20"
        };

        doorA1 = 105;
        doorB1 = 107;
        doorC1 = 109;
        doorD1 = 111;
        doorE1 = 113;
        doorF1 = 115;
        doorA2 = 37;
        doorB2 = 39;
        doorC2 = 41;
        doorD2 = 43;
        doorE2 = 45;
        doorF2 = 47;

        frame = new JFrame();
        chatSpace = new JLabel();
        textSpace = new JLabel();
        nextB = new JButton("Next");
        exitB = new JButton("Back");

        prisonWall1 = new ImageIcon("Images/prisonWall1.png");
        prisonFloor = new ImageIcon("Images/prisonFloor.png");
        prisonWall2 = new ImageIcon("Images/prisonWall2.png");
        prisonWall3 = new ImageIcon("Images/prisonWall3.png");
        prisonWallTop = new ImageIcon("Images/prisonWallTop.png");
        prisonWallTop2 = new ImageIcon("Images/prisonWallTop2.png");
        prisonDoor = new ImageIcon("Images/prisonDoor.png");
        prisonDoorOpen = new ImageIcon("Images/openDoor.png");
        menuButton = new ImageIcon("Images/menuButton.png");

        frontS = new ImageIcon("Images/frontstand.png");
        frontW = new ImageIcon("Images/frontwalk.png");
        leftS = new ImageIcon("Images/leftstand.png");
        leftW = new ImageIcon("Images/leftwalk.png");
        rightS = new ImageIcon("Images/rightstand.png");
        rightW = new ImageIcon("Images/rightwalk.png");
        backS = new ImageIcon("Images/backstand.png");
        backW = new ImageIcon("Images/backwalk.png");
        npc1 = new ImageIcon("Images/guard1.png");
        npc2 = new ImageIcon("Images/guard2.png");

        prisonWall1 = new ImageIcon(prisonWall1.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        prisonWall2 = new ImageIcon(prisonWall2.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        prisonWall3 = new ImageIcon(prisonWall3.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        prisonWallTop = new ImageIcon(prisonWallTop.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        prisonWallTop2 = new ImageIcon(prisonWallTop2.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        prisonFloor = new ImageIcon(prisonFloor.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        prisonDoor = new ImageIcon(prisonDoor.getImage().getScaledInstance(tileW, tileH, Image.SCALE_REPLICATE));
        prisonDoorOpen = new ImageIcon(prisonDoorOpen.getImage().getScaledInstance(tileW, tileH, Image.SCALE_REPLICATE));
        menuButton = new ImageIcon(menuButton.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));

        frontS = new ImageIcon(frontS.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        frontW = new ImageIcon(frontW.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        leftS = new ImageIcon(leftS.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        leftW = new ImageIcon(leftW.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        rightS = new ImageIcon(rightS.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        rightW = new ImageIcon(rightW.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        backS = new ImageIcon(backS.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        backW = new ImageIcon(backW.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        npc1 = new ImageIcon(npc1.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        npc2 = new ImageIcon(npc2.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));

        character = new JLabel[mapWidth * mapHeight];
        characterPlace = new int[]{
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 1, 1,
            1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 1, 1,
            1, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,};
        for (int i = 0; i < character.length; i++) {
            if (characterPlace[i] == 2) {
                character[i] = new JLabel(frontS);
                characterPosition = i;
            } else if (characterPlace[i] == 3) {
                character[i] = new JLabel(npc1);
                npc1Location = i;
            } else if (characterPlace[i] == 4) {
                character[i] = new JLabel(npc2);
                npc2Location = i;
            } else {
                character[i] = new JLabel();
            }
        }

        tiles = new JLabel[mapWidth * mapHeight];
        mapLayout = new int[]{
            7, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6,
            6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6,
            6, 6, 1, 5, 2, 5, 1, 5, 1, 5, 2, 5, 1, 5, 2, 6, 6,
            6, 6, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 6, 6,
            6, 6, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 6, 6,
            6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6,
            6, 6, 2, 5, 1, 5, 1, 5, 2, 5, 3, 5, 3, 5, 1, 6, 6,
            6, 6, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 6, 6,
            6, 6, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 6, 6,
            6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6,
            6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6,};

        for (int i = 0; i < tiles.length; i++) {
            switch (mapLayout[i]) {
                case 0:
                    tiles[i] = new JLabel(prisonWallTop);
                    break;
                case 1:
                    tiles[i] = new JLabel(prisonWall1);
                    break;
                case 2:
                    tiles[i] = new JLabel(prisonWall2);
                    break;
                case 3:
                    tiles[i] = new JLabel(prisonWall3);
                    break;
                case 4:
                    tiles[i] = new JLabel(prisonFloor);
                    break;
                case 5:
                    tiles[i] = new JLabel(prisonDoor);
                    break;
                case 6:
                    tiles[i] = new JLabel(prisonWallTop2);
                    break;
                case 7:
                    tiles[i] = new JLabel(menuButton);
                    break;
                default:
                    break;
            }

        }
    }

    public void setConvoComponentsVisible(boolean b) {
        textSpace.setVisible(b);
        exitB.setVisible(b);
        nextB.setVisible(b);
        chatSpace.setVisible(b);
    }

    public void setFrame() {
        frame.setLayout(new GraphPaperLayout(new Dimension(mapWidth, mapHeight)));

        chatSpace.setOpaque(true);
        chatSpace.setBackground(new Color(243, 246, 232));

        frame.add(textSpace, new Rectangle(1, 9, 13, 1));
        frame.add(exitB, new Rectangle(13, 10, 2, 1));
        frame.add(nextB, new Rectangle(15, 10, 2, 1));
        frame.add(chatSpace, new Rectangle(0, 9, 17, 2));

        this.setConvoComponentsVisible(false);

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
        exitB.addActionListener(this);
        nextB.addActionListener(this);
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
        else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            int movement = 0;
            if (direction == 0) {
                movement = -1 * mapWidth;
            } 
            else if (direction == 1) {
                movement = mapWidth;
            } 
            else if (direction == 2) {
                movement = -1;
            } 
            else if (direction == 3) {
                movement = 1;
            }
            if (characterPosition + movement == npc1Location) {
                if (quiz1Mode == 0) {
                    textSpace.setText(npc1Script1[scriptIndex]);
                    setConvoComponentsVisible(true);
                    chat = 1;
                } 
                else if (quiz1Mode == 1) {
                    textSpace.setText(Questions[questionsIndex]);
                    setConvoComponentsVisible(true);
                    nextB.setVisible(false);
                    chat = 1;
                }
                else if(quiz1Mode == 2){
                    textSpace.setText(npc1Script3[scriptIndex]);
                    setConvoComponentsVisible(true);
                    chat = 1;
                }
               
            } 
            else if (characterPosition + movement == npc2Location) {
                if (quiz2Mode == 0) {
                    textSpace.setText(npc2Script1[scriptIndex]);
                    setConvoComponentsVisible(true);
                    chat = 2;
                } 
                else if (quiz2Mode == 1) {
                    textSpace.setText(Questions[questionsIndex]);
                    setConvoComponentsVisible(true);
                    nextB.setVisible(false);
                    chat = 2;
                }
                else if(quiz2Mode == 2){
                    textSpace.setText(npc2Script3[scriptIndex]);
                    setConvoComponentsVisible(true);
                    chat = 2;
                }
            } 
            else if (characterPosition + movement == doorA1) {
                if (quiz1Mode == 0) {
                    JOptionPane.showMessageDialog(frame,
                            "This door is locked. Maybe I should talk to the guard over there.",
                            "Hint",
                            JOptionPane.INFORMATION_MESSAGE);
                } 
                else if (quiz1Mode == 1) {
                    textSpace.setText("A. " + Choices[0]);
                    setConvoComponentsVisible(true);
                    chat = 3;
                } 
                else if (quiz1Mode == 2) {
                    JOptionPane.showMessageDialog(frame,
                            "You have already opened a door.",
                            "Hint",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } 
            else if (characterPosition + movement == doorB1) {
                if (quiz1Mode == 0) {
                    JOptionPane.showMessageDialog(frame,
                            "This door is locked. Maybe I should talk to the guard over there.",
                            "Hint",
                            JOptionPane.INFORMATION_MESSAGE);
                } 
                else if (quiz1Mode == 1) {
                    textSpace.setText("B. " + Choices[1]);
                    setConvoComponentsVisible(true);
                    chat = 3;
                } 
                else if (quiz1Mode == 2) {
                    JOptionPane.showMessageDialog(frame,
                            "You have already opened a door.",
                            "Hint",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } 
            else if (characterPosition + movement == doorC1) {
                if (quiz1Mode == 0) {
                    JOptionPane.showMessageDialog(frame,
                            "This door is locked. Maybe I should talk to the guard over there.",
                            "Hint",
                            JOptionPane.INFORMATION_MESSAGE);
                } 
                else if (quiz1Mode == 1) {
                    textSpace.setText("C. " + Choices[2]);
                    setConvoComponentsVisible(true);
                    chat = 3;
                } 
                else if (quiz1Mode == 2) {
                    JOptionPane.showMessageDialog(frame,
                            "You have already opened a door.",
                            "Hint",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } 
            else if (characterPosition + movement == doorD1) {
                if (quiz1Mode == 0) {
                    JOptionPane.showMessageDialog(frame,
                            "This door is locked. Maybe I should talk to the guard over there.",
                            "Hint",
                            JOptionPane.INFORMATION_MESSAGE);
                } 
                else if (quiz1Mode == 1) {
                    textSpace.setText("D. " + Choices[3]);
                    setConvoComponentsVisible(true);
                    chat = 3;
                } 
                else if (quiz1Mode == 2) {
                    JOptionPane.showMessageDialog(frame,
                            "You have already opened a door.",
                            "Hint",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } 
            else if (characterPosition + movement == doorE1) {
                if (quiz1Mode == 0) {
                    JOptionPane.showMessageDialog(frame,
                            "This door is locked. Maybe I should talk to the guard over there.",
                            "Hint",
                            JOptionPane.INFORMATION_MESSAGE);
                } 
                else if (quiz1Mode == 1) {
                    textSpace.setText("E. " + Choices[4]);
                    setConvoComponentsVisible(true);
                    chat = 3;
                } 
                else if (quiz1Mode == 2) {
                    JOptionPane.showMessageDialog(frame,
                            "You have already opened a door.",
                            "Hint",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } 
            else if (characterPosition + movement == doorF1) {
                if (quiz1Mode == 0) {
                    JOptionPane.showMessageDialog(frame,
                            "This door is locked. Maybe I should talk to the guard over there.",
                            "Hint",
                            JOptionPane.INFORMATION_MESSAGE);
                } 
                else if (quiz1Mode == 1) {
                    textSpace.setText("F. " + Choices[5]);
                    setConvoComponentsVisible(true);
                    chat = 3;
                } 
                else if (quiz1Mode == 2) {
                    JOptionPane.showMessageDialog(frame,
                            "You have already opened a door.",
                            "Hint",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } 
            else if (characterPosition + movement == doorA2) {
                if (quiz2Mode == 0) {
                    JOptionPane.showMessageDialog(frame,
                            "This door is locked. Maybe I should talk to the guard over there.",
                            "Hint",
                            JOptionPane.INFORMATION_MESSAGE);
                } 
                else if (quiz2Mode == 1) {
                    textSpace.setText("A. " + Choices[0]);
                    setConvoComponentsVisible(true);
                    chat = 3;
                } 
                else if (quiz2Mode == 2) {
                    JOptionPane.showMessageDialog(frame,
                            "You have already opened a door.",
                            "Hint",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } 
            else if (characterPosition + movement == doorB2) {
                if (quiz2Mode == 0) {
                    JOptionPane.showMessageDialog(frame,
                            "This door is locked. Maybe I should talk to the guard over there.",
                            "Hint",
                            JOptionPane.INFORMATION_MESSAGE);
                } 
                else if (quiz2Mode == 1) {
                    textSpace.setText("B. " + Choices[1]);
                    setConvoComponentsVisible(true);
                    chat = 3;
                } 
                else if (quiz2Mode == 2) {
                    JOptionPane.showMessageDialog(frame,
                            "You have already opened a door.",
                            "Hint",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } 
            else if (characterPosition + movement == doorC2) {
                if (quiz2Mode == 0) {
                    JOptionPane.showMessageDialog(frame,
                            "This door is locked. Maybe I should talk to the guard over there.",
                            "Hint",
                            JOptionPane.INFORMATION_MESSAGE);
                } 
                else if (quiz2Mode == 1) {
                    textSpace.setText("C. " + Choices[2]);
                    setConvoComponentsVisible(true);
                    chat = 3;
                } 
                else if (quiz2Mode == 2) {
                    JOptionPane.showMessageDialog(frame,
                            "You have already opened a door.",
                            "Hint",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } 
            else if (characterPosition + movement == doorD2) {
                if (quiz2Mode == 0) {
                    JOptionPane.showMessageDialog(frame,
                            "This door is locked. Maybe I should talk to the guard over there.",
                            "Hint",
                            JOptionPane.INFORMATION_MESSAGE);
                } 
                else if (quiz2Mode == 1) {
                    textSpace.setText("D. " + Choices[3]);
                    setConvoComponentsVisible(true);
                    chat = 3;
                } 
                else if (quiz2Mode == 2) {
                    JOptionPane.showMessageDialog(frame,
                            "You have already opened a door.",
                            "Hint",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } 
            else if (characterPosition + movement == doorE2) {
                if (quiz2Mode == 0) {
                    JOptionPane.showMessageDialog(frame,
                            "This door is locked. Maybe I should talk to the guard over there.",
                            "Hint",
                            JOptionPane.INFORMATION_MESSAGE);
                } 
                else if (quiz2Mode == 1) {
                    textSpace.setText("E. " + Choices[4]);
                    setConvoComponentsVisible(true);
                    chat = 3;
                } 
                else if (quiz2Mode == 2) {
                    JOptionPane.showMessageDialog(frame,
                            "You have already opened a door.",
                            "Hint",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } 
            else if (characterPosition + movement == doorF2) {
                if (quiz2Mode == 0) {
                    JOptionPane.showMessageDialog(frame,
                            "This door is locked. Maybe I should talk to the guard over there.",
                            "Hint",
                            JOptionPane.INFORMATION_MESSAGE);
                } 
                else if (quiz2Mode == 1) {
                    textSpace.setText("F. " + Choices[5]);
                    setConvoComponentsVisible(true);
                    chat = 3;
                } 
                else if (quiz2Mode == 2) {
                    JOptionPane.showMessageDialog(frame,
                            "You have already opened a door.",
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
        Map_3 x = new Map_3();
        x.setFrame();
    }

    public int randomizeQuestion() {
        int ind;
        do {
            ind = (int) (Math.random() * Questions.length);
        } while (checkIfInArray(ind));
        if (questionsIndex != Questions.length) {
            randomizedNumbers[questionsIndex] = ind;
        }
        return ind;
    }

    public boolean checkIfInArray(int n) {
        for (int i = 0; i < 2; i++) {
            if (randomizedNumbers[i] == n) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfInArray2(String n) {
        for (int i = 0; i < Choices.length; i++) {
            if (Choices[i] == n) {
                return true;
            }
        }
        return false;
    }

    public void randomizeChoices(int n) {
        int ind;
        String getAnswers[] = Answers[n].split("_");
        for (int x = 0; x < Choices.length; x++) {
            do {
                ind = (int) (Math.random() * Choices.length);
            } while (checkIfInArray2(getAnswers[ind]));
            Choices[x] = getAnswers[ind];
        }
    }

    public boolean checkAnswer() {
        String answer = textSpace.getText();
        String getAnswers[] = Answers[questionsIndex].split("_");
        if (answer.contains(getAnswers[0])) {
            return true;
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitB) {
            this.setConvoComponentsVisible(false);
            frame.setFocusable(true);
            scriptIndex = 0;
            textSpace.setText("");
            if (quiz1Mode == 1 && firstTry) {
                JOptionPane.showMessageDialog(frame,
                        "Each door corresponds to a choice for the riddle. Interact with each door and choose the correct answer.",
                        "Hint",
                        JOptionPane.INFORMATION_MESSAGE);
                firstTry = false;
            }
            if (quiz1Mode == 1 && chat == 1 || quiz2Mode == 1 && chat == 2) {
                switch (questionsIndex) {
                    case 0:
                        JOptionPane.showMessageDialog(frame,
                                "A = lw",
                                "Hint",
                                JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 1:
                    case 2:
                        JOptionPane.showMessageDialog(frame,
                                "A = πr²",
                                "Hint",
                                JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 3:
                        JOptionPane.showMessageDialog(frame,
                                "A = ½bh",
                                "Hint",
                                JOptionPane.INFORMATION_MESSAGE);
                        break;
                }
            }
            chat = 0;
        }
        if (e.getSource() == nextB) {
            switch (chat) {
                case 1:
                    if (quiz1Mode == 0) {
                        if (scriptIndex < npc1Script1.length - 1) {
                            scriptIndex++;
                            textSpace.setText(npc1Script1[scriptIndex]);
                        } 
                        else {
                            questionsIndex = randomizeQuestion();
                            textSpace.setText(Questions[questionsIndex]);
                            randomizeChoices(questionsIndex);
                            nextB.setVisible(false);
                            quiz1Mode = 1;
                        }
                    }
                    else if(quiz1Mode == 2 && chat == 1) {
                        if (scriptIndex < npc1Script3.length - 1) {
                            scriptIndex++;
                            textSpace.setText(npc1Script3[scriptIndex]);
                            if (scriptIndex == npc1Script3.length - 1){
                                nextB.setVisible(false);
                            }
                        } 
                        
                    }
                    break;
                case 2:
                    if (quiz2Mode == 0) {
                        if (scriptIndex < npc2Script1.length - 1) {
                            scriptIndex++;
                            textSpace.setText(npc2Script1[scriptIndex]);
                        } 
                        else {
                            questionsIndex = randomizeQuestion();
                            textSpace.setText(Questions[questionsIndex]);
                            randomizeChoices(questionsIndex);
                            nextB.setVisible(false);
                            quiz2Mode = 1;
                        }
                    }
                    else if(quiz2Mode == 2 && chat == 2) {
                        if (scriptIndex < npc2Script3.length - 1) {
                            scriptIndex++;
                            textSpace.setText(npc2Script3[scriptIndex]);
                            if (scriptIndex == npc2Script3.length - 1){
                                nextB.setVisible(false);
                            }
                        } 
                        
                    }
                    break;
                case 3:
                    Object[] options = {"Yes", "No"};
                    int confirm = JOptionPane.showOptionDialog(frame,
                            "Is this your final answer?",
                            "Confirmation",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);
                    if (confirm == JOptionPane.YES_OPTION) {
                        setConvoComponentsVisible(false);
                        if (checkAnswer()) {
                            JOptionPane.showMessageDialog(frame,
                                    "Correct answer!",
                                    " ",
                                    JOptionPane.INFORMATION_MESSAGE);
                            if (quiz1Mode == 1) {
                                quiz1Mode = 2;
                            } else if (quiz2Mode == 1) {
                                quiz2Mode = 2;
                            }

                            characterPlace[characterPosition - mapWidth] = 0;
                            characterPlace[characterPosition - (mapWidth * 2)] = 0;
                            tiles[characterPosition - mapWidth].setIcon(prisonDoorOpen);
                            tiles[characterPosition - (mapWidth * 2)].setIcon(prisonFloor);
                            if (quiz2Mode == 2) {
                                characterPlace[characterPosition - (mapWidth * 3)] = 0;
                                tiles[characterPosition - (mapWidth * 3)].setIcon(prisonFloor);
                            }

                            textSpace.setText("");
                            questionsIndex = 0;
                            for (int i = 0; i < Choices.length; i++) {
                                Choices[i] = " ";
                            }
                        } 
                        else {
                            JOptionPane.showMessageDialog(frame,
                                    "Wrong answer.",
                                    " ",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    chat = 0;
                    break;
            }
        }
    }
}

//members: Simona Roda, Mark De la Cruz, Safina Ibanez
//sir the graphics look cooler sa file viewer than the real output 🙁
//i asked chatgpt to fix but it still looks that way, hlep Sir. Thank you.
//sir i will add limited guesses in the future
