/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Q2_fornix;

/**
 *
 * @author ANDREI KENT
 */

//groupmates: Deocampo and Naquila
import Q2_map.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FornixMap4 extends GameMap implements KeyListener{
    
    JFrame frame;
    ImageIcon wall;
    ImageIcon tile;
    ImageIcon tree;
    ImageIcon sign;
    ImageIcon frontS;
    ImageIcon frontW;
    ImageIcon leftS;
    ImageIcon leftW;
    ImageIcon rightS;
    ImageIcon rightW;
    ImageIcon backS;
    ImageIcon backW;
    ImageIcon bookSlf;
    ImageIcon openedTree;
    
    JLabel tiles[];
    JLabel character[];
    
    private int correctlyAnsweredCount = 0; // ENCAPSULATION
    boolean[] scenarioSolved = new boolean[3];
    int signsReadCount = 0;
    boolean[] signsVisited = new boolean[100];
    private boolean puzzleSolved = false;
    
    public boolean isPuzzleSolved() {
    return puzzleSolved;
    }
    
    public void setPuzzleSolved(boolean solved) {
    this.puzzleSolved = solved;
    }   
    
    int characterMode=0;
    int characterPosition=-1;
    int characterPlace[];
    int mapLayout[];
    int mapWidth=10; 
    int mapHeight=10;
    int frameWidth=500; 
    int frameHeight=500;    

    //game objective: the player navigates through an underground library. the player would also solve puzzles from the wisdom tree to enter the next level

    public FornixMap4(){
        mapName = "The Underground Library";
        frame=new JFrame();
        characterPosition=-1;
        wall=new ImageIcon("Images/fornix_map2/caveWall.png");
        tile=new ImageIcon("Images/fornix_map2/whole1.png");
        tree=new ImageIcon("Images/fornix_map2/tree.jpg");
        sign=new ImageIcon("Images/fornix_map2/sign.png");
        bookSlf= new ImageIcon("Images/fornix_map2/bookshelf.jpg");
        openedTree= new ImageIcon("Images/fornix_map2/openedTree.png");
        
        //sprites
        frontS=new ImageIcon("Images/fornix/sprites/frontStand.png");
        frontW=new ImageIcon("Images/fornix/sprites/frontWalk.png");
        leftS=new ImageIcon("Images/fornix/sprites/leftStand.png");
        leftW=new ImageIcon("Images/fornix/sprites/leftWalk.png");
        rightS=new ImageIcon("Images/fornix/sprites/rightStand.png");
        rightW=new ImageIcon("Images/fornix/sprites/rightWalk.png");
        backS=new ImageIcon("Images/fornix/sprites/backStand.png");
        backW=new ImageIcon("Images/fornix/sprites/backWalk.png");
        
        bookSlf = new ImageIcon(bookSlf.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        wall=new ImageIcon(wall.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        tile=new ImageIcon(tile.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        sign=new ImageIcon(sign.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        frontS=new ImageIcon(frontS.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        frontW=new ImageIcon(frontW.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        leftS=new ImageIcon(leftS.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        leftW=new ImageIcon(leftW.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        rightS=new ImageIcon(rightS.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        rightW=new ImageIcon(rightW.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        backS=new ImageIcon(backS.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        backW=new ImageIcon(backW.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        
        tree = new ImageIcon(tree.getImage().getScaledInstance(
            (frameWidth / mapWidth) * 4,  
            (frameHeight / mapHeight) * 4, 
            Image.SCALE_DEFAULT
        ));
        
        openedTree = new ImageIcon(openedTree.getImage().getScaledInstance(
            (frameWidth / mapWidth) * 4, 
            (frameHeight / mapHeight) * 4, 
            Image.SCALE_DEFAULT
        ));
        
        character=new JLabel[mapWidth*mapHeight];
        characterPlace=new int[]{
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,1,0,0,0,0,0
        };
        for(int i=0;i<character.length;i++){
            if(characterPlace[i]==1){
                character[i]=new JLabel(frontS);
                characterPosition=i;
            }
            else character[i]=new JLabel();
        }
                
        tiles=new JLabel[mapWidth*mapHeight];
        mapLayout = new int[]{
            0,5,5,5,5,5,5,5,5,0,
            5,1,1,1,1,1,1,1,1,5,
            5,1,2,1,1,1,1,2,1,5, 
            5,1,1,3,4,4,4,1,1,5, 
            5,1,1,4,4,4,4,1,1,5, 
            5,1,1,4,4,4,4,1,1,5, 
            5,1,1,4,4,4,4,1,1,5,
            5,1,2,1,1,1,1,2,1,5, 
            0,1,1,1,1,1,1,1,1,0,
            0,0,0,1,1,1,1,0,0,0 
        };
        
        for(int i=0;i<tiles.length;i++){
            if(mapLayout[i]==0) tiles[i]=new JLabel(wall);
            else if(mapLayout[i]==1) tiles[i]=new JLabel(tile);
            else if(mapLayout[i]==2) tiles[i]=new JLabel(sign);
            else if(mapLayout[i]==3) tiles[i]=new JLabel(tree);
            else if(mapLayout[i]==5) tiles[i]=new JLabel(bookSlf);
        }

    }
    
    public void setFrame(){
        frame.setLayout(new GraphPaperLayout(new Dimension(mapWidth,mapHeight)));
        
        //sprites
        int x=0, y=0, w=1, h=1;
        for(int i=0;i<character.length;i++){
            frame.add(character[i], new Rectangle(x,y,w,h));
            x++;
            if(x%mapWidth==0){
                x=0;
                y++;
            }
        }
        
        //map layout
        x = 0; y = 0; 
        for(int i = 0; i < tiles.length; i++) {
            // 3 is the Big Tree (Top-Left corner)
            if(mapLayout[i] == 3) {
                frame.add(tiles[i], new Rectangle(x, y, 4, 4)); // Spans 4 wide, 4 high
            } 
            // 4 is a "Ghost" tile (the spaces the big tree is covering)
            else if(mapLayout[i] == 4) {
            } 
            // 0, 1, and 2 are normal 1x1 tiles
            else {
                frame.add(tiles[i], new Rectangle(x, y, 1, 1)); 
            }

            x++;
            if(x % mapWidth == 0) {
                x = 0;
                y++;
            }
        }
        
        frame.setSize(frameWidth,frameHeight);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        
        frame.addKeyListener(this);
        
        displayWelcomeMessage();
    }
    //game objective implementation
    
    
    
     @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
       
    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
        // Check all 4 directions around the player for a Sign (2) or Tree (3)
        int[] neighbors = {characterPosition + 1, characterPosition - 1, 
                          characterPosition + mapWidth, characterPosition - mapWidth};

        for (int next : neighbors) {
            if (next < 0 || next >= mapLayout.length) continue;

            // IF PLAYER IS NEXT TO A SIGN (2)
            if (mapLayout[next] == 2) {
                if (!signsVisited[next]) {
                    signsReadCount++;
                    signsVisited[next] = true;
                }
                showSignDialogue(next);
                return; // Exit after talking
            }

            // IF PLAYER IS NEXT TO THE TREE (3 or 4)
            if (mapLayout[next] == 3 || mapLayout[next] == 4) {
                if (signsReadCount < 4) {
                    JOptionPane.showMessageDialog(frame, "The Wisdom Tree whispers: 'You have not yet studied the four pillars of the Beast.'");
                } else {
                    startMachiavelliQuiz();
                }
                return;
            }
        }
    }
    // --- RIGHT MOVEMENT ---
    if(e.getKeyCode() == KeyEvent.VK_RIGHT){
        if(characterPosition % mapWidth == mapWidth - 1) return;
        int next = characterPosition + 1;

        // Added 5 to the blocked list
        if(mapLayout[next] == 0 || mapLayout[next] == 2 || mapLayout[next] == 3 || mapLayout[next] == 4 || mapLayout[next] == 5){
            toggleSprite(rightW, rightS);
        } else {
            character[characterPosition].setIcon(null);
            character[next].setIcon(characterMode == 0 ? rightW : rightS);
            toggleMode();
            characterPosition = next;
        }
    }

    // --- LEFT MOVEMENT ---
    else if(e.getKeyCode() == KeyEvent.VK_LEFT){
        if(characterPosition % mapWidth == 0) return;
        int next = characterPosition - 1;

        if(mapLayout[next] == 0 || mapLayout[next] == 2 || mapLayout[next] == 3 || mapLayout[next] == 4 || mapLayout[next] == 5){
            toggleSprite(leftW, leftS);
        } else {
            character[characterPosition].setIcon(null);
            character[next].setIcon(characterMode == 0 ? leftW : leftS);
            toggleMode();
            characterPosition = next;
        }
    }

    // --- DOWN MOVEMENT ---
    else if(e.getKeyCode() == KeyEvent.VK_DOWN){
        int next = characterPosition + mapWidth;
        if(next >= mapLayout.length) return;

        if(mapLayout[next] == 0 || mapLayout[next] == 2 || mapLayout[next] == 3 || mapLayout[next] == 4 || mapLayout[next] == 5){
            toggleSprite(frontW, frontS);
        } else {
            character[characterPosition].setIcon(null);
            character[next].setIcon(characterMode == 0 ? frontW : frontS);
            toggleMode();
            characterPosition = next;
        }
        
        // UPDATED Victory Check for the area in front of the tree
        if (puzzleSolved && (next >= 73 && next <= 76)) {
            JOptionPane.showMessageDialog(frame, "You descend into the secret passage beneath the Tree...");
            frame.dispose();
            // new NextMap().setFrame();
        }
    }

    // --- UP MOVEMENT ---
    else if(e.getKeyCode() == KeyEvent.VK_UP){
        int next = characterPosition - mapWidth;
        if(next < 0) return;

        if(mapLayout[next] == 0 || mapLayout[next] == 2 || mapLayout[next] == 3 || mapLayout[next] == 4 || mapLayout[next] == 5){
            toggleSprite(backW, backS);
        } else {
            character[characterPosition].setIcon(null);
            character[next].setIcon(characterMode == 0 ? backW : backS);
            toggleMode();
            characterPosition = next;
        }
    }

}
    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    private void toggleMode() {
    characterMode = (characterMode == 0) ? 1 : 0;
}
    
    private void toggleSprite(ImageIcon walk, ImageIcon stand) {
    character[characterPosition].setIcon(characterMode == 0 ? walk : stand);
    toggleMode();
}
    private void showSignDialogue(int index) {
    String text = "";
    if (index == 72){
        text = "To the traveler who enters: Look upon the Lion. "
                + "He is the master of the open field and the terror of the wolf. "
                + "His strength is unmatched, and his roar keeps the boldest enemies at "
                + "bay. But look down at his feet—he does not see the wire. He does not "
                + "smell the oil of the trap. Strength is a shield, but it is also a "
                + "blindfold.";
    } 
    else if (index == 77) {
        text = "To the seeker of secrets: Observe the Fox. He moves "
                + "through the tall grass like a shadow. He knows the scent of the hunter "
                + "and the click of the iron snare. No trap can hold him, for he sees the "
                + "world as a series of hidden threads. But do not let him meet the wolf in "
                + "the clearing, for his wit cannot sharpen his teeth. Cunning is a key, but "
                + "it is not a sword.";
    } else if (index == 22) {
        text = "The Fox is clever, but he is small. When the wolf comes "
                + "not with a trap, but with hunger and teeth, the Fox’s riddles will not save "
                + "him. Cunning cannot stop a landslide; wit cannot blunt a sword. To rely "
                + "only on the mind is to invite the predator to your door. Strength must be "
                + "the Fox’s guardian.";
    } else if (index == 27) {
        text = "The Lion is mighty, but he is loud. He marches with "
                + "thunder, unaware that the ground beneath him is hollow. A single thread of "
                + "silk, tied by a hidden hand, can bring the king to his knees. To rely only "
                + "on the sword is to walk blindly into the pit. Vision must be the Lion’s guide.";
    }
    showSignDialogue(text, "Ancient Tablet"); // Calls Overload 2
}
    
private void showSignDialogue(String message, String title) {
    String formattedText = "<html><div style='width: 300px; text-align: justify;'>" + message + "</div></html>";
    JOptionPane.showMessageDialog(frame, formattedText, title, JOptionPane.PLAIN_MESSAGE);
}

private void startMachiavelliQuiz() {
    // If the puzzle is already solved, don't restart the quiz
    if (correctlyAnsweredCount >= 3) {
        JOptionPane.showMessageDialog(frame, "The Tree is silent, its wisdom shared.");
        return;
    }

    // Pick a scenario that hasn't been solved yet
    int scenario;
    do {
        scenario = (int)(Math.random() * 3);
    } while (scenarioSolved[scenario]); 

    String question = "";
    String correctAnswer = "";

    if (scenario == 0) {
        question = "<b>Scenario A: The Silent Poison</b><br><br>"
                + "\"The Duke of Romagna enters a city where the nobles smile and offer wine, yet beneath the floorboards, they whisper of daggers. They have laid a legal web to strip the Duke of his title the moment he signs their 'Treaty of Friendship.' If the Duke enters with his army and burns the city, the people will hate him. If he signs blindly, he loses his head.\"<br><br>"
                + "<b>Q: To survive this night, which aspect must the Duke prioritize?</b>";
        correctAnswer = "Fox";
    } else if (scenario == 1) {
        question = "<b>Scenario B: The Border Wolves</b><br><br>"
                + "\"A neighboring kingdom sees your famine and interprets your kindness as weakness. They march their 'wolves' toward your gate, demanding your grain and your crown. They do not hide their intent; they bring fire and steel. Your spies are useless here, for the enemy no longer speaks in riddles—they speak in blood.\"<br><br>"
                + "<b>Q: To protect the citizens, which aspect must the Prince now embody?</b>";
        correctAnswer = "Lion";
    } else {
        question = "<b>Scenario C: The False Peace</b><br><br>"
                + "\"The war is over, but the peace is fragile. The Prince has made a promise to the rebels to spare their lives, but he knows that if they live, they will rise again in a year. To keep his word is to lose his throne; to kill them openly is to break the law and look like a tyrant. He invites them to a feast, praises their courage, and ensures they never leave the hall, making it look like an accident of the kitchen fires.\"<br><br>"
                + "<b>Q: In this grim resolution, what was the nature of the Prince's actions?</b>";
        correctAnswer = "Both";
    }

    String formattedQuestion = "<html><div style='width: 350px; text-align: justify;'>" + question + "</div></html>";
    String ans = JOptionPane.showInputDialog(frame, formattedQuestion + "\n(Type: Lion, Fox, or Both)");

    if (ans != null && ans.equalsIgnoreCase(correctAnswer)) {
        scenarioSolved[scenario] = true;
        correctlyAnsweredCount++;
        
        if (correctlyAnsweredCount < 3) {
            JOptionPane.showMessageDialog(frame, "Correct! You have uncovered " + correctlyAnsweredCount + " of the 3 truths.");
        } else {
            // ALL 3 ANSWERED CORRECTLY
            puzzleSolved = true; 
            JOptionPane.showMessageDialog(frame, "The Wisdom Tree glows! The roots shift to reveal a hidden passage.");
            
            // 1. SWAP THE PICTURE TO THE OPENED VERSION
            tiles[33].setIcon(openedTree); 

            // 2. OPEN THE EXIT DIRECTLY IN FRONT OF THE TREE
            int[] exitTiles = {73, 74, 75, 76}; 
            for(int i : exitTiles) {
                mapLayout[i] = 1; // Change logic to 'floor'
                tiles[i].setIcon(tile); // Change visual to floor tile
            }
        }
    } else if (ans != null) {
        JOptionPane.showMessageDialog(frame, "The tree groans in disappointment. 'Your answer lacks the balance of the beast.'");
    }
}


    @Override // OVERRIDING the parent method
    public void displayWelcomeMessage() {
        JOptionPane.showMessageDialog(frame, "Welcome to the next level: " + mapName);
    }
}

class GameMap {
    protected String mapName = "Generic Map";

    // This method will be OVERRIDDEN by FornixMap2
    public void displayWelcomeMessage() {
        System.out.println("Entering " + mapName);
    }
}