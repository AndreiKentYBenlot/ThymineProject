package PD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import java.util.Random;

public class StatesideRiver implements KeyListener {
    JFrame frame;
    ImageIcon sand;
    ImageIcon water;
    ImageIcon midplank;
    ImageIcon leftplank;
    ImageIcon rightplank;
    ImageIcon topplank;
    ImageIcon bottomplank;
    ImageIcon topleft;
    ImageIcon topright;
    ImageIcon bottomleft;
    ImageIcon bottomright;
    ImageIcon plant1;
    ImageIcon plant2;
    ImageIcon shrub;
    ImageIcon trunk;
    ImageIcon branch;
    ImageIcon transition;
    ImageIcon grass;
    
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
    int secretNumber = new Random().nextInt(10) + 1;
    int attempts = 0;
    int area;
    boolean hasWon = false;
    boolean hasAnswered = false;
    boolean answered = false;
    
    public StatesideRiver() {
        frame = new JFrame();
        sand = new ImageIcon("PD Assets/sand.png");
        water = new ImageIcon("PD Assets/water.png");
        midplank = new ImageIcon("PD Assets/midplank.png");
        leftplank = new ImageIcon("PD Assets/leftplank.png");
        rightplank = new ImageIcon("PD Assets/rightplank.png");
        topplank =  new ImageIcon("PD Assets/topplank.png");
        bottomplank = new ImageIcon("PD Assets/bottomplank.png");
        topleft = new ImageIcon("PD Assets/topleft.png");
        topright = new ImageIcon("PD Assets/topright.png");
        bottomleft = new ImageIcon("PD Assets/bottomleft.png");
        bottomright = new ImageIcon("PD Assets/bottomright.png");
        plant1 =  new ImageIcon("PD Assets/plant1.png");
        plant2 =  new ImageIcon("PD Assets/plant2.png");
        shrub = new ImageIcon("PD Assets/shrub.png");
        trunk = new ImageIcon("PD Assets/trunk.png");
        branch = new ImageIcon("PD Assets/branch.png");
        transition = new ImageIcon("PD Assets/transition.png");
        grass = new ImageIcon("PD Assets/grass.png");
        
        frontS=new ImageIcon("PD Assets/frontstand.png");
        frontW=new ImageIcon("PD Assets/frontwalk.png");
        leftS=new ImageIcon("PD Assets/leftstand.png");
        leftW=new ImageIcon("PD Assets/leftwalk.png");
        rightS=new ImageIcon("PD Assets/rightstand.png");
        rightW=new ImageIcon("PD Assets/rightwalk.png");
        backS=new ImageIcon("PD Assets/backstand.png");
        backW=new ImageIcon("PD Assets/backwalk.png");

        sand = new ImageIcon(sand.getImage().getScaledInstance((frameWidth/15),(frameHeight/9), Image.SCALE_DEFAULT));
        water = new ImageIcon(water.getImage().getScaledInstance((frameWidth/15),(frameHeight/9), Image.SCALE_DEFAULT));
        midplank = new ImageIcon(midplank.getImage().getScaledInstance((frameWidth/15),(frameHeight/9), Image.SCALE_DEFAULT));
        leftplank = new ImageIcon(leftplank.getImage().getScaledInstance((frameWidth/15),(frameHeight/9), Image.SCALE_DEFAULT));
        rightplank = new ImageIcon(rightplank.getImage().getScaledInstance((frameWidth/15),(frameHeight/9), Image.SCALE_DEFAULT));
        topplank = new ImageIcon(topplank.getImage().getScaledInstance((frameWidth/15),(frameHeight/9), Image.SCALE_DEFAULT));
        bottomplank = new ImageIcon(bottomplank.getImage().getScaledInstance((frameWidth/15),(frameHeight/9), Image.SCALE_DEFAULT));
        topleft = new ImageIcon(topleft.getImage().getScaledInstance((frameWidth/15),(frameHeight/9), Image.SCALE_DEFAULT));
        topright = new ImageIcon(topright.getImage().getScaledInstance((frameWidth/15),(frameHeight/9), Image.SCALE_DEFAULT));
        bottomleft = new ImageIcon(bottomleft.getImage().getScaledInstance((frameWidth/15),(frameHeight/9), Image.SCALE_DEFAULT));
        bottomright = new ImageIcon(bottomright.getImage().getScaledInstance((frameWidth/15),(frameHeight/9), Image.SCALE_DEFAULT));
        plant1 = new ImageIcon(plant1.getImage().getScaledInstance((frameWidth/15),(frameHeight/9), Image.SCALE_DEFAULT));
        plant2 = new ImageIcon(plant2.getImage().getScaledInstance((frameWidth/15),(frameHeight/9), Image.SCALE_DEFAULT));
        shrub = new ImageIcon(shrub.getImage().getScaledInstance((frameWidth/15),(frameHeight/9), Image.SCALE_DEFAULT));
        trunk = new ImageIcon(trunk.getImage().getScaledInstance((frameWidth/15),(frameHeight/9), Image.SCALE_DEFAULT));
        branch = new ImageIcon(branch.getImage().getScaledInstance((frameWidth/15),(frameHeight/9), Image.SCALE_DEFAULT));
        transition = new ImageIcon(transition.getImage().getScaledInstance((frameWidth/15),(frameHeight/9), Image.SCALE_DEFAULT));
        grass = new ImageIcon(grass.getImage().getScaledInstance((frameWidth/15),(frameHeight/9), Image.SCALE_DEFAULT));
        
        frontS=new ImageIcon(frontS.getImage().getScaledInstance((frameWidth/15), (frameHeight/9), Image.SCALE_DEFAULT));
        frontW=new ImageIcon(frontW.getImage().getScaledInstance((frameWidth/15), (frameHeight/9), Image.SCALE_DEFAULT));
        leftS=new ImageIcon(leftS.getImage().getScaledInstance((frameWidth/15), (frameHeight/9), Image.SCALE_DEFAULT));
        leftW=new ImageIcon(leftW.getImage().getScaledInstance((frameWidth/15), (frameHeight/9), Image.SCALE_DEFAULT));
        rightS=new ImageIcon(rightS.getImage().getScaledInstance((frameWidth/15), (frameHeight/9), Image.SCALE_DEFAULT));
        rightW=new ImageIcon(rightW.getImage().getScaledInstance((frameWidth/15), (frameHeight/9), Image.SCALE_DEFAULT));
        backS=new ImageIcon(backS.getImage().getScaledInstance((frameWidth/15), (frameHeight/9), Image.SCALE_DEFAULT));
        backW=new ImageIcon(backW.getImage().getScaledInstance((frameWidth/15), (frameHeight/9), Image.SCALE_DEFAULT));
        
        frame.setLayout(new GridLayout(9, 15));
        character=new JLabel[135];
        characterPlace=new int[]{
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            1,1,1,1,1,1,1,0,0,1,0,1,0,0,0,
            1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,
            0,0,0,0,0,0,0,1,1,1,0,1,1,1,1,
            0,0,0,2,0,0,0,0,0,1,0,1,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
        };

        tiles = new JLabel[135];
        layout = new int[] {
            15,13,17,17,15,13,17,17,15,13,17,15,17,17,13,
            14,17,17,13,14,17,17,13,14,15,17,14,13,15,17,
            16,16,16,16,16,16,16,16,16,14,13,17,17,14,17,
            0,0,0,0,0,0,0,0,16,16,16,16,16,16,16,
            1,1,1,1,1,1,1,0,0,7,5,8,0,0,0,
            1,1,1,1,1,1,1,1,1,3,2,4,1,1,1,
            0,0,0,0,12,0,0,1,1,3,2,4,1,1,1,
            0,11,0,0,0,0,0,0,0,9,6,10,0,0,0,
            0,0,0,0,0,11,0,0,0,0,0,12,0,11,0,
        };

        for (int i = 0; i < 135; i = i + 1) {
            if (layout[i] == 0) { tiles[i] = new JLabel(sand); }
            else if (layout[i] == 1) { tiles[i] = new JLabel(water); }
            else if (layout[i] == 2) { tiles[i] = new JLabel(midplank); }
            else if (layout[i] == 3) { tiles[i] = new JLabel(leftplank); }
            else if (layout[i] == 4) { tiles[i] = new JLabel(rightplank); }
            else if (layout[i] == 5) { tiles[i] = new JLabel(topplank); }
            else if (layout[i] == 6) { tiles[i] = new JLabel(bottomplank); }
            else if (layout[i] == 7) { tiles[i] = new JLabel(topleft); }
            else if (layout[i] == 8) { tiles[i] = new JLabel(topright); }
            else if (layout[i] == 9) { tiles[i] = new JLabel(bottomleft); }
            else if (layout[i] == 10) { tiles[i] = new JLabel(bottomright); }
            else if (layout[i] == 11) { tiles[i] = new JLabel(plant1); }
            else if (layout[i] == 12) { tiles[i] = new JLabel(plant2); }
            else if (layout[i] == 13) { tiles[i] = new JLabel(shrub); }
            else if (layout[i] == 14) { tiles[i] = new JLabel(trunk); }
            else if (layout[i] == 15) { tiles[i] = new JLabel(branch); }
            else if (layout[i] == 16) { tiles[i] = new JLabel(transition); }
            else { tiles[i] = new JLabel(grass); }

            if (characterPlace[i] == 2) {
                character[i] = new JLabel(frontS);
                characterPosition = i;
            } else {
                character[i] = new JLabel();
            }

            tiles[i].setLayout(new BorderLayout());
            tiles[i].add(character[i]);
            frame.add(tiles[i]);
        }
        
        frame.addKeyListener(this);
        setFrame();
    }

    public void setFrame() {
        frame.setTitle("Stateside River");
        frame.setSize(frameWidth, frameHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(true);
        frame.setVisible(true);
        frame.requestFocusInWindow();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (characterPosition == 100 && hasWon == false) {
            try {
                String input = JOptionPane.showInputDialog(null, 
                "Hello, fellow traveller, I am the bridge to eternity.\nWithout me, you won't see the sweetest words of Fornix, the forest. Now please say the magic password.");
                if (input != null) {
                    int guess = Integer.parseInt(input); 
                    attempts = attempts + 1;
                    if (guess == secretNumber) {
                        JOptionPane.showMessageDialog(null, "Correct! It took you " + attempts + " tries.");
                        hasWon = true;
                    } else if (guess < secretNumber) {
                        JOptionPane.showMessageDialog(null, "Too low! Try again.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Too high! Try again.");
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Oops! The password is actually a number from 1 - 10", "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                if (hasWon == true) { 
                    area = secretNumber * 6;
                    int guess = anotherQuestion();
                    if (guess == area) {
                    hasAnswered = false;
                    }
                    while (guess != area) {
                        JOptionPane.showMessageDialog(null, "Try again.");
                        guess = anotherQuestion();
                        if (guess == area) {
                        hasAnswered = true;
                    }
                    }
                }
                if (hasAnswered == true) { JOptionPane.showMessageDialog(null, "Now go forth, fellow traveller!"); }
                frame.requestFocusInWindow();
            }
            return;
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (characterPlace[characterPosition + 1] == 1) {
                if (characterMode == 0) {
                    character[characterPosition].setIcon(rightW);
                    characterMode = 1;
                } else {
                    character[characterPosition].setIcon(rightS);
                    characterMode = 0;
                }
            } else {
                character[characterPosition].setIcon(null);
                if (characterMode == 0) {
                    character[characterPosition + 1].setIcon(rightW);
                    characterMode = 1;
                } else {
                    character[characterPosition + 1].setIcon(rightS);
                    characterMode = 0;
                }
                characterPosition = characterPosition + 1;
            }
        } 
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (characterPlace[characterPosition - 1] == 1) {
                if (characterMode == 0) {
                    character[characterPosition].setIcon(leftW);
                    characterMode = 1;
                } else {
                    character[characterPosition].setIcon(leftS);
                    characterMode = 0;
                }
            } else {
                character[characterPosition].setIcon(null);
                if (characterMode == 0) {
                    character[characterPosition - 1].setIcon(leftW);
                    characterMode = 1;
                } else {
                    character[characterPosition - 1].setIcon(leftS);
                    characterMode = 0;
                }
                characterPosition = characterPosition - 1;
            }
        } 
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (characterPlace[characterPosition + 15] == 1) {
                if (characterMode == 0) {
                    character[characterPosition].setIcon(frontW);
                    characterMode = 1;
                } else {
                    character[characterPosition].setIcon(frontS);
                    characterMode = 0;
                }
            } else {
                character[characterPosition].setIcon(null);
                if (characterMode == 0) {
                    character[characterPosition + 15].setIcon(frontW);
                    characterMode = 1;
                } else {
                    character[characterPosition + 15].setIcon(frontS);
                    characterMode = 0;
                }
                characterPosition = characterPosition + 15;
            }
        } 
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (characterPlace[characterPosition - 15] == 1) {
                if (characterMode == 0) {
                    character[characterPosition].setIcon(backW);
                    characterMode = 1;
                } else {
                    character[characterPosition].setIcon(backS);
                    characterMode = 0;
                }
            } else {
                character[characterPosition].setIcon(null);
                if (characterMode == 0) {
                    character[characterPosition - 15].setIcon(backW);
                    characterMode = 1;
                } else {
                    character[characterPosition - 15].setIcon(backS);
                    characterMode = 0;
                }
                characterPosition = characterPosition - 15;
            }
        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
    
    int anotherQuestion () {
    String input = JOptionPane.showInputDialog(null,   
            "Great, you got it! But before you enter... There's one final question:\nWhat is the area of a rectangle whose length is the same number as the password you just gueesed\nand the width is the number of trees you can find in the area?");
    return Integer.parseInt(input);
}
}
