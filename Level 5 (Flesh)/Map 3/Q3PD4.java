package CS4Q3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Q3PD4 implements KeyListener {

    JFrame frame;
    ImageIcon bone, boneinv, tile, tendril, eye;
    ImageIcon down, downw, up, upw;

    JLabel tiles[];
    JLabel character[];

    int mapLayout[];
    int characterPlace[];

    int mapWidth = 9;
    int mapHeight = 9;
    int frameWidth = 600;
    int frameHeight = 600;

    int characterPosition;
    int lastDirection = KeyEvent.VK_DOWN;

    boolean movementLocked = false;

    final int QUIZ_TILE_1 = 22;   // first machine tile
    final int QUIZ_TILE_2 = 49;   // second machine tile
    final int END_TILE    = 76;   // end of hallway tile

    boolean quiz1Done = false;
    boolean quiz2Done = false;
    boolean endTriggered = false;

    public Q3PD4() {
        frame = new JFrame();
        characterPosition = -1;

        bone = new ImageIcon(getClass().getResource("Q3PD4ASSETS/bone tile 100x100.jpg"));
        tile = new ImageIcon(getClass().getResource("Q3PD4ASSETS/flesh tile 100x100.jpg"));
        boneinv = new ImageIcon(getClass().getResource("Q3PD4ASSETS/mirrored bone tile 100x100.jpg"));
        tendril = new ImageIcon(getClass().getResource("Q3PD4ASSETS/tendril tile 100x100.jpg"));
        eye = new ImageIcon(getClass().getResource("Q3PD4ASSETS/eye tile 100x100.jpg"));

        down = new ImageIcon(getClass().getResource("Q3PD4ASSETS/codyfst.png"));
        downw = new ImageIcon(getClass().getResource("Q3PD4ASSETS/codyfw.png"));
        up = new ImageIcon(getClass().getResource("Q3PD4ASSETS/codybst.png"));
        upw = new ImageIcon(getClass().getResource("Q3PD4ASSETS/codybw.png"));

        bone = scale(bone);
        tile = scale(tile);
        boneinv = scale(boneinv);
        tendril = scale(tendril);
        eye = scale(eye);
        down = scale(down);
        downw = scale(downw);
        up = scale(up);
        upw = scale(upw);

        character = new JLabel[mapWidth * mapHeight];
        characterPlace = new int[]{
            0,0,0,0,1,0,0,0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0
        };

        for (int i = 0; i < character.length; i++) {
            if (characterPlace[i] == 1) {
                character[i] = new JLabel(down);
                characterPosition = i;
            } else character[i] = new JLabel();
        }

        tiles = new JLabel[mapWidth * mapHeight];
        mapLayout = new int[]{
            3,1,2,4,2,1,2,1,2,
            2,1,5,4,2,1,3,5,4,
            2,5,3,4,2,1,2,4,2,
            1,3,2,4,2,1,5,3,4,
            2,4,1,4,2,1,1,3,2,
            2,3,5,4,2,1,5,2,1,
            4,3,2,4,2,1,2,3,2,
            2,3,5,4,2,1,3,1,1,
            5,1,2,1,2,1,2,1,2
        };

        for (int i = 0; i < tiles.length; i++) {
            if (mapLayout[i] == 1) tiles[i] = new JLabel(bone);
            else if (mapLayout[i] == 2) tiles[i] = new JLabel(tile);
            else if (mapLayout[i] == 3) tiles[i] = new JLabel(tendril);
            else if (mapLayout[i] == 4) tiles[i] = new JLabel(boneinv);
            else if (mapLayout[i] == 5) tiles[i] = new JLabel(eye);
        }
    }

    private ImageIcon scale(ImageIcon icon) {
        return new ImageIcon(icon.getImage().getScaledInstance(
                frameWidth / mapWidth,
                frameHeight / mapHeight,
                Image.SCALE_DEFAULT));
    }

    public void setFrame() {
        frame.setLayout(new GraphPaperLayout(new Dimension(mapWidth, mapHeight)));

        int x = 0, y = 0;
        for (int i = 0; i < character.length; i++) {
            frame.add(character[i], new Rectangle(x, y, 1, 1));
            x++;
            if (x % mapWidth == 0) { x = 0; y++; }
        }

        x = 0; y = 0;
        for (int i = 0; i < tiles.length; i++) {
            frame.add(tiles[i], new Rectangle(x, y, 1, 1));
            x++;
            if (x % mapWidth == 0) { x = 0; y++; }
        }

        frame.setSize(frameWidth, frameHeight);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addKeyListener(this);

       
        JOptionPane.showMessageDialog(frame,
                "Cody: Finally, I got the door open!\nI thought I couldn't make it out of this one...");
    }

    private void checkTileEvents() {

        if (characterPosition == QUIZ_TILE_1 && !quiz1Done) {
            movementLocked = true;
            quiz1Done = true;
            runQuizPart1();
            movementLocked = false;
        }

        if (characterPosition == QUIZ_TILE_2 && quiz1Done && !quiz2Done) {
            movementLocked = true;
            quiz2Done = true;
            runQuizPart2();
            movementLocked = false;
        }

        if (characterPosition == END_TILE && quiz2Done && !endTriggered) {
            movementLocked = true;
            endTriggered = true;
            runEnding();
        }
    }

    private void runQuizPart1() {
        JOptionPane.showMessageDialog(frame,
                "System: Warning, Unauthorized specimen detected.\nSwitching to verification of authorization.");
        JOptionPane.showMessageDialog(frame,
                "Cody: OH! HIDDEN TRAP");

        askQuestion(
            "Question 1:\nWhich base pairs with Adenine (A)?\nA. Cytosine\nB. Guanine\nC. Thymine\nD. Uracil",
            "C"
        );

        askQuestion(
            "Question 2:\nMain role of DNA?\nA. Producing energy\nB. Storing genetic information\nC. Breaking down food\nD. Transporting oxygen",
            "B"
        );
        JOptionPane.showMessageDialog(frame,
                "Cody: Phew, that was easier than expected");
    }

    private void runQuizPart2() {
        JOptionPane.showMessageDialog(frame,
                "Cody: No way, not again? All right fine.");
        askQuestion(
            "Question 3:\nGenotype Tt phenotype?\nA. Short\nB. Tall\nC. Half & half\nD. None",
            "B"
        );

        askQuestion(
            "Question 4:\nTt x Tt → short offspring?\nA. 0%\nB. 25%\nC. 50%\nD. 75%",
            "B"
        );
        JOptionPane.showMessageDialog(frame,
                "Cody: Piece of cake!");
    }

    private void askQuestion(String question, String correct) {
        String input;
        do {
            input = JOptionPane.showInputDialog(frame, question);
            if (input == null) System.exit(0);
        } while (!input.equalsIgnoreCase(correct));
    }

    private void runEnding() {
        JOptionPane.showMessageDialog(frame,
                "Cody: There you go!\n\nSystem: Machine Assembling permitted.");

        JOptionPane.showMessageDialog(frame,
                "Cody: Alright, looks like I have to assemble this thing.");

        JOptionPane.showMessageDialog(frame,
                "Cody: All done.\nFrom the looks of this, this is my ticket out of here.\nI shouldn't linger for long.");

        JOptionPane.showMessageDialog(frame,
                "ESCAPED!\nLEVEL COMPLETE.");

        System.exit(0);
    }

    public void keyPressed(KeyEvent e) {
        if (movementLocked) return;

        if (e.getKeyCode() == KeyEvent.VK_DOWN && characterPosition + mapWidth < character.length) {
            character[characterPosition].setIcon(null);
            characterPosition += mapWidth;
            character[characterPosition].setIcon(downw);
            lastDirection = KeyEvent.VK_DOWN;
            checkTileEvents();
        }

        if (e.getKeyCode() == KeyEvent.VK_UP && characterPosition - mapWidth >= 0) {
            character[characterPosition].setIcon(null);
            characterPosition -= mapWidth;
            character[characterPosition].setIcon(upw);
            lastDirection = KeyEvent.VK_UP;
            checkTileEvents();
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) character[characterPosition].setIcon(down);
        if (e.getKeyCode() == KeyEvent.VK_UP) character[characterPosition].setIcon(up);
    }

    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        new Q3PD4().setFrame();
    }
}
//other groupmates are Kean L. Layog and Ck Zoe B. Villegas
