/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Q4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

class PD8 implements KeyListener {

    JFrame frame;

    ImageIcon sand, water, pearl, jelly1, jelly2, portal2, sand2;

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

    int size = 450;
    int tileW = size / mapWidth;
    int tileH = size / mapHeight;

    Player player;

    int pearlsCollected = 0;
    int totalPearls = 0;

    int characterMode = 0;

    Map<Integer, Question> pearlQuestions = new HashMap<>();

    public PD8() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}

        frame = new JFrame("Jellyfish Depths");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        sand   = scale(new ImageIcon("Images/sand.png"));
        water  = scale(new ImageIcon("Images/water.png"));
        pearl  = scale(new ImageIcon("Images/pearl2.png"));
        jelly1 = scale(new ImageIcon("Images/jelly1.png"));
        jelly2 = scale(new ImageIcon("Images/jelly2.png"));
        portal2 = scale(new ImageIcon("Images/portal2.png"));
        sand2 = scale(new ImageIcon("Images/sand2.png"));

        frontS = scale(new ImageIcon("Images/frontS.png"));
        frontW = scale(new ImageIcon("Images/frontW.png"));
        backS  = scale(new ImageIcon("Images/backS.png"));
        backW  = scale(new ImageIcon("Images/backW.png"));
        leftS  = scale(new ImageIcon("Images/leftS.png"));
        leftW  = scale(new ImageIcon("Images/leftW.png"));
        rightS = scale(new ImageIcon("Images/rightS.png"));
        rightW = scale(new ImageIcon("Images/rightW.png"));

        // 0 drip sand, 1 water, 2 jelly1, 3 jelly2, 4 pearl, 5 portal, 6 sand
        mapLayout = new int[]{
            6,0,0,0,0,0,0,0,6,
            6,1,1,2,1,3,1,4,6,
            6,1,0,0,0,0,1,1,6,
            6,1,4,1,2,1,4,1,6,
            6,0,0,1,0,1,0,0,6,
            6,1,1,1,4,1,1,1,6,
            6,1,0,0,0,0,0,1,6,
            6,1,1,1,1,1,5,1,6,
            6,6,6,6,6,6,6,6,6
        };

        walkable = new int[mapLayout.length];

        for (int i = 0; i < mapLayout.length; i++) {
            if (mapLayout[i] == 1 || mapLayout[i] == 4 || mapLayout[i] == 5) {
                walkable[i] = 1;
            } else {
                walkable[i] = 0;
            }

            if (mapLayout[i] == 4) totalPearls++;
        }

        // QUESTIONS
        Question[] qList = new Question[3];

        qList[0] = new Question(
            "What gas do humans need?",
            "Oxygen",
            new String[]{"Carbon dioxide", "Nitrogen", "Hydrogen"}
        );

        qList[1] = new Question(
            "What causes tides?",
            "Moon",
            new String[]{"Wind", "Rain", "Earth only"}
        );

        qList[2] = new Question(
            "What happens to pressure underwater?",
            "It increases",
            new String[]{"It decreases", "No change", "It disappears"}
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
                case 0 -> tiles[i] = new JLabel(sand);
                case 1 -> tiles[i] = new JLabel(water);
                case 2 -> tiles[i] = new JLabel(jelly1);
                case 3 -> tiles[i] = new JLabel(jelly2);
                case 4 -> tiles[i] = new JLabel(pearl);
                case 5 -> tiles[i] = new JLabel(portal2);
                case 6 -> tiles[i] = new JLabel(sand2);
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

        // JELLYFISH CHECK (instant fail)
        if (mapLayout[nextPos] == 2 || mapLayout[nextPos] == 3) {
            JOptionPane.showMessageDialog(frame,
                    "You got electrocuted by a jellyfish!\nRestarting...");
            frame.dispose();
            new PD8();
            return;
        }

        if (walkable[nextPos] == 0) return;

        character[player.getPosition()].setIcon(null);

        player.move(nextPos);
        player.draw(character, characterMode == 0 ? walk : stand);
        characterMode = 1 - characterMode;

        // PEARL LOGIC
        if (mapLayout[player.getPosition()] == 4) {

            Question q = pearlQuestions.get(player.getPosition());

            boolean correct = q.ask(frame);

            if (!correct) {
                JOptionPane.showMessageDialog(frame,
                        "Wrong! Restarting...");
                frame.dispose();
                new PD8();
                return;
            }

            pearlsCollected++;
            player.move(player.getPosition(), 10);

            mapLayout[player.getPosition()] = 1;
            tiles[player.getPosition()].setIcon(water);

            JOptionPane.showMessageDialog(frame,
                    "Correct! Pearl collected.");
        }

        // WIN CONDITION
        if (mapLayout[player.getPosition()] == 5) {
            if (pearlsCollected == totalPearls) {

                    int choice = JOptionPane.showConfirmDialog(
                            frame,
                            "You escaped the Jellyfish Zone!\nFinal Score: " + player.getScore() +
                            "\n\nContinue to next level?",
                            "Continue?",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (choice == JOptionPane.YES_OPTION) {
                        frame.dispose();

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
        new PD8();
    }
}