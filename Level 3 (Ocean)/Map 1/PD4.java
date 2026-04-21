package Q4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PD4 implements KeyListener {

    JFrame frame;

    ImageIcon wall;
    ImageIcon path;
    ImageIcon bush;
    ImageIcon portal;
    ImageIcon pearl;

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

    int frameWidth = 450;
    int frameHeight = 450;

    int tileW = frameWidth / mapWidth;
    int tileH = frameHeight / mapHeight;

    int characterPosition;
    int characterMode = 0;

    int pearlsCollected = 0;
    int totalPearls = 0;

    public PD4() {

        frame = new JFrame("Underwater Chasm");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        wall   = new ImageIcon("Yay/base.png");
        path   = new ImageIcon("Yay/path.png");
        bush   = new ImageIcon("Yay/bush.png");
        portal = new ImageIcon("Yay/end.png");
        pearl  = new ImageIcon("Yay/pearl.png");

        wall   = new ImageIcon(wall.getImage().getScaledInstance(tileW, tileH, Image.SCALE_DEFAULT));
        path   = new ImageIcon(path.getImage().getScaledInstance(tileW, tileH, Image.SCALE_DEFAULT));
        bush   = new ImageIcon(bush.getImage().getScaledInstance(tileW, tileH, Image.SCALE_DEFAULT));
        portal = new ImageIcon(portal.getImage().getScaledInstance(tileW, tileH, Image.SCALE_DEFAULT));
        pearl  = new ImageIcon(pearl.getImage().getScaledInstance(tileW, tileH, Image.SCALE_DEFAULT));

        frontS = new ImageIcon("Yay/frontS.png");
        frontW = new ImageIcon("Yay/frontW.png");
        backS  = new ImageIcon("Yay/backS.png");
        backW  = new ImageIcon("Yay/backW.png");
        leftS  = new ImageIcon("Yay/leftS.png");
        leftW  = new ImageIcon("Yay/leftW.png");
        rightS = new ImageIcon("Yay/rightS.png");
        rightW = new ImageIcon("Yay/rightW.png");

        frontS = new ImageIcon(frontS.getImage().getScaledInstance(tileW, tileH, Image.SCALE_DEFAULT));
        frontW = new ImageIcon(frontW.getImage().getScaledInstance(tileW, tileH, Image.SCALE_DEFAULT));
        backS  = new ImageIcon(backS.getImage().getScaledInstance(tileW, tileH, Image.SCALE_DEFAULT));
        backW  = new ImageIcon(backW.getImage().getScaledInstance(tileW, tileH, Image.SCALE_DEFAULT));
        leftS  = new ImageIcon(leftS.getImage().getScaledInstance(tileW, tileH, Image.SCALE_DEFAULT));
        leftW  = new ImageIcon(leftW.getImage().getScaledInstance(tileW, tileH, Image.SCALE_DEFAULT));
        rightS = new ImageIcon(rightS.getImage().getScaledInstance(tileW, tileH, Image.SCALE_DEFAULT));
        rightW = new ImageIcon(rightW.getImage().getScaledInstance(tileW, tileH, Image.SCALE_DEFAULT));

        // 0 = wall, 1 = path, 2 = portal, 3 = bush, 4 = pearl
        mapLayout = new int[]{
            0,0,0,0,0,0,0,0,0,
            0,1,1,3,1,1,2,1,0,
            0,1,4,0,0,0,4,1,0,
            0,1,1,1,0,1,1,1,0,
            0,0,0,1,0,3,0,0,0,
            0,1,0,1,1,1,4,1,0,
            0,1,0,0,0,0,0,1,0,
            0,1,1,1,3,3,1,1,0,
            0,0,0,0,0,0,0,0,0
        };

        walkable = new int[mapLayout.length];
        for (int i = 0; i < mapLayout.length; i++) {
            if (mapLayout[i] == 1 || mapLayout[i] == 2 || mapLayout[i] == 3 || mapLayout[i] == 4) {
                walkable[i] = 1;
            } else {
                walkable[i] = 0;
            }

            if (mapLayout[i] == 4) totalPearls++;
        }

        character = new JLabel[mapLayout.length];
        for (int i = 0; i < character.length; i++) {
            character[i] = new JLabel();
        }

        characterPosition = (7 * mapWidth) + 1;
        character[characterPosition].setIcon(frontS);

        tiles = new JLabel[mapLayout.length];
        for (int i = 0; i < tiles.length; i++) {
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
            tiles[i].setLayout(new BorderLayout());
            tiles[i].add(character[i]);
            frame.add(tiles[i]);
        }

        frame.setSize(frameWidth, frameHeight);
        frame.setResizable(false);

        // ===== TUTORIAL POPUP =====
        JButton continueBtn = new JButton("Continue");

        JPanel panel = new JPanel(new BorderLayout());
        JTextArea text = new JTextArea(
                "Welcome to Underwater Chasm!\n\n" +
                "Tutorial:\n" +
                "- Use arrow keys to move your character.\n" +
                "- Collect all glowing pearls in the map.\n" +
                "- Only after collecting all pearls can you enter the portal.\n\n" +
                "Good luck!"
        );
        text.setEditable(false);
        text.setOpaque(false);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);

        panel.add(text, BorderLayout.CENTER);
        panel.add(continueBtn, BorderLayout.SOUTH);

        JDialog dialog = new JDialog(frame, "Tutorial", true);
        dialog.add(panel);
        dialog.setSize(320, 220);
        dialog.setLocationRelativeTo(frame);

        continueBtn.addActionListener(e -> dialog.dispose());

        frame.setVisible(true);
        dialog.setVisible(true);
        // ==========================

        frame.addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int nextPos = characterPosition;

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            move(nextPos - mapWidth, backS, backW);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            move(nextPos + mapWidth, frontS, frontW);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            move(nextPos - 1, leftS, leftW);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            move(nextPos + 1, rightS, rightW);
        }
    }

    private void move(int nextPos, ImageIcon stand, ImageIcon walk) {

        if (nextPos < 0 || nextPos >= walkable.length) return;

        if (walkable[nextPos] == 0) {
            character[characterPosition].setIcon(stand);
            return;
        }

        character[characterPosition].setIcon(null);
        characterPosition = nextPos;

        character[characterPosition].setIcon(
                characterMode == 0 ? walk : stand
        );
        characterMode = 1 - characterMode;

        if (mapLayout[characterPosition] == 4) {
            pearlsCollected++;
            mapLayout[characterPosition] = 1;
            tiles[characterPosition].setIcon(path);

            JOptionPane.showMessageDialog(frame,
                    "Glowing pearl collected!\n" +
                    pearlsCollected + " / " + totalPearls);
        }

        if (mapLayout[characterPosition] == 2) {
            if (pearlsCollected == totalPearls) {

                int choice = JOptionPane.showConfirmDialog(
                        frame,
                        "Level Complete!\nDo you want to continue to the next level?",
                        "Continue?",
                        JOptionPane.YES_NO_OPTION
                );

                if (choice == JOptionPane.YES_OPTION) {
                    frame.dispose(); 
                    PD6 move = new PD6(); 
                    
                } else {
                    JOptionPane.showMessageDialog(frame,
                            "Explore the map!");
                }

            } else {
                JOptionPane.showMessageDialog(frame,
                        "The portal is sealed.\nCollect all glowing pearls first.");
            }
        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        new PD4();
    }
}