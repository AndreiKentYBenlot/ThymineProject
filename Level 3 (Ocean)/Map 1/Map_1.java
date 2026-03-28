package Map_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PD4 implements KeyListener {

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

        // ✅ IMAGE LOADING WITH EXCEPTION HANDLING
        try {
            wall   = new ImageIcon("Yay/base.png");
            path   = new ImageIcon("Yay/path.png");
            bush   = new ImageIcon("Yay/bush.png");
            portal = new ImageIcon("Yay/end.png");
            pearl  = new ImageIcon("Yay/pearl.png");

            wall   = scale(wall);
            path   = scale(path);
            bush   = scale(bush);
            portal = scale(portal);
            pearl  = scale(pearl);

            frontS = scale(new ImageIcon("Yay/frontS.png"));
            frontW = scale(new ImageIcon("Yay/frontW.png"));
            backS  = scale(new ImageIcon("Yay/backS.png"));
            backW  = scale(new ImageIcon("Yay/backW.png"));
            leftS  = scale(new ImageIcon("Yay/leftS.png"));
            leftW  = scale(new ImageIcon("Yay/leftW.png"));
            rightS = scale(new ImageIcon("Yay/rightS.png"));
            rightW = scale(new ImageIcon("Yay/rightW.png"));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame,
                    "Error loading images.\nCheck file paths.");
        }

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

        frame.setLayout(new GraphPaperLayout(new Dimension(mapWidth, mapHeight)));

        int x = 0, y = 0;

        for (int i = 0; i < tiles.length; i++) {

            frame.add(character[i], new Rectangle(x, y, 1, 1));
            frame.add(tiles[i], new Rectangle(x, y, 1, 1));

            x++;

            if (x == mapWidth) {
                x = 0;
                y++;
            }
        }

        frame.setSize(frameWidth, frameHeight);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.addKeyListener(this);
    }

    private ImageIcon scale(ImageIcon icon) {
        return new ImageIcon(
            icon.getImage().getScaledInstance(tileW, tileH, Image.SCALE_DEFAULT)
        );
    }

    // ✅ INPUT HANDLING WITH EXCEPTION HANDLING
    @Override
    public void keyPressed(KeyEvent e) {

        try {

            int nextPos = characterPosition;
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_UP) {
                move(nextPos - mapWidth, backS, backW);
            }
            else if (key == KeyEvent.VK_DOWN) {
                move(nextPos + mapWidth, frontS, frontW);
            }
            else if (key == KeyEvent.VK_LEFT) {
                move(nextPos - 1, leftS, leftW);
            }
            else if (key == KeyEvent.VK_RIGHT) {
                move(nextPos + 1, rightS, rightW);
            }
            else {
                throw new IllegalArgumentException();
            }

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(frame,
                    "Invalid input!\nUse arrow keys only.");

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(frame,
                    "Unexpected error occurred.");
        }
    }

    // ✅ SAFE MOVEMENT WITH EXCEPTION HANDLING
    private void move(int nextPos, ImageIcon stand, ImageIcon walk) {

        try {

            if (nextPos < 0 || nextPos >= walkable.length) {
                throw new IndexOutOfBoundsException();
            }

            if (walkable[nextPos] == 0) {
                throw new Exception();
            }

            character[characterPosition].setIcon(null);
            characterPosition = nextPos;

            character[characterPosition].setIcon(
                    characterMode == 0 ? walk : stand
            );

            characterMode = 1 - characterMode;

            // Pearl collection
            if (mapLayout[characterPosition] == 4) {

                pearlsCollected++;

                mapLayout[characterPosition] = 1;
                tiles[characterPosition].setIcon(path);

                JOptionPane.showMessageDialog(frame,
                        "Glowing pearl collected!\n" +
                        pearlsCollected + " / " + totalPearls);
            }

            // Portal check
            if (mapLayout[characterPosition] == 2) {

                if (pearlsCollected == totalPearls) {

                    JOptionPane.showMessageDialog(frame,
                            "Cody reaches the light.\nThe chasm yields its hope.");

                } else {

                    JOptionPane.showMessageDialog(frame,
                            "The portal is sealed.\nCollect all glowing pearls first.");
                }
            }

        } catch (IndexOutOfBoundsException e) {

            JOptionPane.showMessageDialog(frame,
                    "You can't go outside the map!");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(frame,
                    "You can't move there!");
        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        new PD4();
    }
}