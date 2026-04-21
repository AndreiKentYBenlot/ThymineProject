package Q4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class GameObject {
    protected int position;

    public GameObject(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int p) {
        position = p;
    }

    public void draw(JLabel[] labels, ImageIcon icon) {
        labels[position].setIcon(icon);
    }
}

class Player extends GameObject {

    private int score = 0; 

    public Player(int pos) {
        super(pos);
    }

    public void move(int newPos) {
        position = newPos;
    }

    public void move(int newPos, int points) {
        position = newPos;
        score += points;
    }

    public int getScore() {
        return score;
    }

    @Override
    public void draw(JLabel[] labels, ImageIcon icon) {
        labels[position].setIcon(icon);
    }
}

class Question {
    String question;
    String correct;
    String[] choices;

    public Question(String q, String correct, String[] wrong) {
    this.question = q;
    this.correct = correct;

    choices = new String[4];

    choices[0] = correct;
    choices[1] = wrong[0];
    choices[2] = wrong[1];
    choices[3] = wrong[2];

    // manual shuffle
    for (int i = 0; i < choices.length; i++) {
        int r = (int)(Math.random() * choices.length);

        String temp = choices[i];
        choices[i] = choices[r];
        choices[r] = temp;
    }
    }
    public boolean ask(Component parent) {
        int result = JOptionPane.showOptionDialog(
                parent,
                question,
                "Pearl Challenge",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                choices,
                choices[0]
        );

        return choices[result].equals(correct);
    }
}

public class PD6 implements KeyListener {

    JFrame frame;

    ImageIcon wall, path, bush, portal, pearl;

    ImageIcon frontS, frontW;
    ImageIcon backS, backW;
    ImageIcon leftS, leftW;
    ImageIcon rightS, rightW;

    JLabel[] tiles;
    JLabel[] character;

    int[] mapLayout;
    int[] walkable;

    int mapWidth = 9;
    int mapHeight = 9;

    int frameSize = 450;

    int tileW = frameSize / mapWidth;
    int tileH = frameSize / mapHeight;

    Player player;

    int characterMode = 0;

    int pearlsCollected = 0;
    int totalPearls = 0;

    Map<Integer, Question> pearlQuestions = new HashMap<>();

    public PD6() {

        frame = new JFrame("Dark Cave of the Abyss");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        wall   = scale(new ImageIcon("Images/wall.png"));
        path   = scale(new ImageIcon("Images/path.png"));
        bush   = scale(new ImageIcon("Images/bush.png"));
        portal = scale(new ImageIcon("Images/portal.png"));
        pearl  = scale(new ImageIcon("Images/pearl.png"));

        frontS = scale(new ImageIcon("Images/frontS.png"));
        frontW = scale(new ImageIcon("Images/frontW.png"));
        backS  = scale(new ImageIcon("Images/backS.png"));
        backW  = scale(new ImageIcon("Images/backW.png"));
        leftS  = scale(new ImageIcon("Images/leftS.png"));
        leftW  = scale(new ImageIcon("Images/leftW.png"));
        rightS = scale(new ImageIcon("Images/rightS.png"));
        rightW = scale(new ImageIcon("Images/rightW.png"));

        mapLayout = new int[]{
            0,0,0,0,0,0,0,0,0,
            0,1,1,1,3,3,1,2,0,
            0,1,0,0,0,0,1,1,0,
            0,1,4,1,3,1,4,1,0,
            0,0,0,1,0,1,0,0,0,
            0,1,1,1,4,1,1,1,0,
            0,1,0,0,0,0,0,1,0,
            0,1,1,1,1,1,1,1,0,
            0,0,0,0,0,0,0,0,0
        };

        walkable = new int[mapLayout.length];

        for (int i = 0; i < mapLayout.length; i++) {
            if (mapLayout[i] == 1 || mapLayout[i] == 2 || mapLayout[i] == 4) {
                walkable[i] = 1;
            } else {
                walkable[i] = 0;
            }

            if (mapLayout[i] == 4) totalPearls++;
        }

        // QUESTIONS
        Question[] qList = new Question[3];

        qList[0] = new Question(
            "What is oxygen used for in respiration?",
            "To make ATP (energy)",
            new String[]{"To store glucose", "To carry DNA", "To break proteins"}
        );

        qList[1] = new Question(
            "Lenz’s Law: induced current flows to?",
            "Oppose the change",
            new String[]{"Increase the change", "Stop motion", "Create energy"}
        );

        qList[2] = new Question(
            "What happens to pressure underwater?",
            "It increases",
            new String[]{"It decreases", "It stays the same", "It disappears"}
        );

        int qIndex = 0;
        for (int i = 0; i < mapLayout.length; i++) {
            if (mapLayout[i] == 4) {
                pearlQuestions.put(i, qList[qIndex % qList.length]);
                qIndex++;
            }
        }

        int startPos = (7 * mapWidth) + 1;
        player = new Player(startPos);

        tiles = new JLabel[mapLayout.length];
        character = new JLabel[mapLayout.length];

        for (int i = 0; i < mapLayout.length; i++) {

            character[i] = new JLabel();

            switch (mapLayout[i]) {
                case 0 -> tiles[i] = new JLabel(wall);
                case 1 -> tiles[i] = new JLabel(path);
                case 2 -> tiles[i] = new JLabel(portal);
                case 3 -> tiles[i] = new JLabel(bush);
                case 4 -> tiles[i] = new JLabel(pearl);
            }
        }

        frame.setLayout(new GridLayout(mapHeight, mapWidth));

        for (int i = 0; i < tiles.length; i++) {
            JPanel tilePanel = new JPanel();
            tilePanel.setLayout(new OverlayLayout(tilePanel));

            tilePanel.add(character[i]);
            tilePanel.add(tiles[i]);

            frame.add(tilePanel);
        }

        player.draw(character, frontS);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.addKeyListener(this);
    }

    private ImageIcon scale(ImageIcon icon) {
        return new ImageIcon(
            icon.getImage().getScaledInstance(tileW, tileH, Image.SCALE_DEFAULT)
        );
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int next = player.getPosition();

        if (e.getKeyCode() == KeyEvent.VK_UP) move(next - mapWidth, backS, backW);
        if (e.getKeyCode() == KeyEvent.VK_DOWN) move(next + mapWidth, frontS, frontW);
        if (e.getKeyCode() == KeyEvent.VK_LEFT) move(next - 1, leftS, leftW);
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) move(next + 1, rightS, rightW);
    }

    private void move(int nextPos, ImageIcon stand, ImageIcon walk) {

        if (nextPos < 0 || nextPos >= mapLayout.length) return;
        if (walkable[nextPos] == 0) return;

        character[player.getPosition()].setIcon(null);

        player.move(nextPos);
        player.draw(character, characterMode == 0 ? walk : stand);
        characterMode = 1 - characterMode;

        if (mapLayout[player.getPosition()] == 4) {

            Question q = pearlQuestions.get(player.getPosition());

            boolean correct = q.ask(frame);

            if (!correct) {
                JOptionPane.showMessageDialog(frame,
                        "Wrong answer! Restarting level...");
                frame.dispose();
                new PD6();
                return;
            }

            pearlsCollected++;
            player.move(player.getPosition(), 10);

            mapLayout[player.getPosition()] = 1;
            tiles[player.getPosition()].setIcon(path);

            JOptionPane.showMessageDialog(frame,
                    "Correct! Pearl collected.\nScore: " + player.getScore());
        }

        if (mapLayout[player.getPosition()] == 2) {

            if (pearlsCollected == totalPearls) {

                int choice = JOptionPane.showConfirmDialog(
                        frame,
                        "You escaped the cave!\nFinal Score: " + player.getScore() +
                        "\n\nContinue to next level?",
                        "Continue?",
                        JOptionPane.YES_NO_OPTION
                );

                if (choice == JOptionPane.YES_OPTION) {
                    frame.dispose();
                    PD8 move = new PD8(); 
                    
                } else {
                    JOptionPane.showMessageDialog(frame,
                            "Feel free to explore the map!");
                }

            } else {
                JOptionPane.showMessageDialog(frame,
                        "Portal locked! Collect all pearls.");
            }
        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        new PD6();
    }
}