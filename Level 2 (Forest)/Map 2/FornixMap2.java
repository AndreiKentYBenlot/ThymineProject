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
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FornixMap2 implements KeyListener{
    JFrame frame;
    ImageIcon grass;
    ImageIcon pathway;
    ImageIcon cave;
    ImageIcon torch;
    ImageIcon tree;
    ImageIcon sign;
    JLabel tiles [];
    JLabel character[];
    ImageIcon frontS;
    ImageIcon frontW;
    ImageIcon leftS;
    ImageIcon leftW;
    ImageIcon rightS;
    ImageIcon rightW;
    ImageIcon backS;
    ImageIcon backW;

    
    int characterPosition=-1;
    int characterMode=0;
    int characterPlace[];
    int mapLayout[];
    int mapWidth=10; //insert map width here
    int mapHeight=20;//insert map height here
    int frameWidth=500; //changes frame width
    int frameHeight=800;//changes frame height
    
    StringBuilder collectedLetters = new StringBuilder();
    
    class Question {
    char rewardLetter;
    String text;
    String[] choices;     // null if short answer
    int correctIndex;     // used for MC
    String correctAnswer; // used for short answer
    QuestionType type;

    // MULTIPLE CHOICE
    Question(String text, String[] choices, int correctIndex, char rewardLetter){
        this.text = text;
        this.choices = choices;
        this.correctIndex = correctIndex;
        this.rewardLetter = rewardLetter;
        this.type = QuestionType.MULTIPLE_CHOICE;
    }

    // SHORT ANSWER
    Question(String text, String correctAnswer, char rewardLetter){
        this.text = text;
        this.correctAnswer = correctAnswer;
        this.rewardLetter = rewardLetter;
        this.type = QuestionType.SHORT_ANSWER;
    }
}

    
    enum QuestionType {
    MULTIPLE_CHOICE,
    SHORT_ANSWER
    }

    
    java.util.HashMap<Integer, Question> signQuestions = new java.util.HashMap<>();

    private void checkForCave(){

    int[] directions = {
        characterPosition + 1,
        characterPosition - 1,
        characterPosition + mapWidth,
        characterPosition - mapWidth
    };

    for(int next : directions){
        if(next >= 0 && next < mapLayout.length){
            if(mapLayout[next] == 3){
                openCave();
                break;
            }
        }
    }
}
    
    private void openCave(){

    if(collectedLetters.length() < 4){
        JOptionPane.showMessageDialog(
            frame,
            "The cave is sealed.\nYou need all 4 letters."
        );
        return;
    }

    String input = JOptionPane.showInputDialog(
        frame,
        "The cave requires a password.\nEnter password:"
    );

    if(input == null) return;

    if(input.equalsIgnoreCase("KODI")){
        JOptionPane.showMessageDialog(
            frame,
            "The cave opens... ✨\nLevel complete!"
        );
        
        // 1. Close the current Level 1 window
        frame.dispose(); 

        // 2. Launch Level 2
        FornixMap4 level2 = new FornixMap4();
        level2.setFrame();
        
    } else {
        JOptionPane.showMessageDialog(frame, "Wrong password ❌");
    }
}

    private void loadNextLevel(){
        collectedLetters.setLength(0);

        JOptionPane.showMessageDialog(
            frame,
            "Welcome to Level 2!"
        );

        // Later:
        // - change mapLayout
        // - reset characterPosition
        // - redraw map
    }

    
    public FornixMap2 (){
        frame=new JFrame();
        grass=new ImageIcon("Images/fornix/grass.jpg");
        pathway=new ImageIcon("Images/fornix/pathway.jpg");
        cave=new ImageIcon("Images/fornix/cave.jpg");
        torch=new ImageIcon("Images/fornix/torch.jpg");
        tree=new ImageIcon("Images/fornix/tree.jpg");
        sign=new ImageIcon("Images/fornix/sign.jpg");
        
        
        //sprites
        frontS=new ImageIcon("Images/fornix/sprites/frontStand.png");
        frontW=new ImageIcon("Images/fornix/sprites/frontWalk.png");
        leftS=new ImageIcon("Images/fornix/sprites/leftStand.png");
        leftW=new ImageIcon("Images/fornix/sprites/leftWalk.png");
        rightS=new ImageIcon("Images/fornix/sprites/rightStand.png");
        rightW=new ImageIcon("Images/fornix/sprites/rightWalk.png");
        backS=new ImageIcon("Images/fornix/sprites/backStand.png");
        backW=new ImageIcon("Images/fornix/sprites/backWalk.png");
        
        grass=new ImageIcon(grass.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        pathway=new ImageIcon(pathway.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        cave=new ImageIcon(cave.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        torch=new ImageIcon(torch.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        tree=new ImageIcon(tree.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        sign=new ImageIcon(sign.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        frontS=new ImageIcon(frontS.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        frontW=new ImageIcon(frontW.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        leftS=new ImageIcon(leftS.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        leftW=new ImageIcon(leftW.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        rightS=new ImageIcon(rightS.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        rightW=new ImageIcon(rightW.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        backS=new ImageIcon(backS.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        backW=new ImageIcon(backW.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        
        
        character=new JLabel[mapWidth*mapHeight];
        characterPlace=new int[]{
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,1,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0
        };
        for(int i=0;i<character.length;i++){
            if(characterPlace[i]==1){
                character[i]=new JLabel(frontS);
                characterPosition=i;
            }
            else character[i]=new JLabel();
        }
        
                
        tiles=new JLabel[mapWidth*mapHeight];
        mapLayout=new int[]{
            0,0,0,0,0,1,1,1,1,0,
            0,0,0,1,1,1,0,0,1,4,
            5,2,2,2,0,0,0,0,0,0,
            0,0,0,2,0,2,2,2,0,0,
            0,0,0,2,0,2,0,2,0,0,
            0,0,0,2,0,2,0,2,0,0,
            0,2,2,2,0,2,0,2,0,0,
            0,2,0,0,0,2,0,2,0,0,
            4,2,2,2,2,2,0,2,0,0,
            0,0,0,0,0,0,0,2,0,0,
            0,3,1,1,0,0,0,2,0,0,
            0,0,1,1,0,0,0,2,0,0,
            0,0,0,1,0,0,4,2,0,0,
            0,2,2,2,2,2,2,2,2,0,
            0,2,0,0,0,0,0,0,2,0,
            0,2,0,0,0,0,0,0,2,0,
            0,2,2,2,2,0,0,0,2,0,
            0,0,1,1,2,2,2,2,2,0,
            4,1,1,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0
        };
        
        for(int i=0;i<tiles.length;i++){
            if(mapLayout[i]==0) tiles[i]=new JLabel(tree);
            else if(mapLayout[i]==1) tiles[i]=new JLabel(grass);
            else if(mapLayout[i]==2) tiles[i]=new JLabel(pathway);
            else if(mapLayout[i]==3) tiles[i]=new JLabel(cave);
            else if(mapLayout[i]==4) tiles[i]=new JLabel(sign);
            else if(mapLayout[i]==5) tiles[i]=new JLabel(torch);
        }
    
    signQuestions.put(19, new Question(
        "Which literary period focuses on emotion, imagination, and nature?",
        new String[]{"Victorian","Romantic","Modernist","Renaissance"},
        1,
        'O'
    ));

    signQuestions.put(80, new Question(
        "Who wrote Romeo and Juliet?",
        new String[]{"Geoffrey Chaucer","John Milton","William Shakespeare","Charles Dickens"},
        2,
        'K'
    ));

    signQuestions.put(126, new Question(
        "What term refers to the main character in a literary work?",
        "protagonist",
        'D'
    ));

    signQuestions.put(180, new Question(
        "What literary device gives human qualities to nonhuman objects?",
        "personification",
        'I'
    ));



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
        x=0; y=0; w=1; h=1;
        for(int i=0;i<tiles.length;i++){
            frame.add(tiles[i], new Rectangle(x,y,w,h));
            x++;
            if(x%mapWidth==0){
                x=0;
                y++;
            }
            if(mapLayout[i] == 4){
    System.out.println("SIGN AT INDEX: " + i);
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
    int next = -1;

    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
        next = characterPosition + 1;
        if(next >= mapLayout.length || characterPosition % mapWidth == mapWidth - 1) next = -1;
    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
        next = characterPosition - 1;
        if(next < 0 || characterPosition % mapWidth == 0) next = -1;
    } else if (e.getKeyCode() == KeyEvent.VK_UP) {
        next = characterPosition - mapWidth;
        if(next < 0) next = -1;
    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
        next = characterPosition + mapWidth;
        if(next >= mapLayout.length) next = -1;
    }

    //  Exception handling for the Sign Interaction
    if (next != -1) {
        try {
            checkObstacle(next);
            
            // If no exception was thrown, move the character normally
            character[characterPosition].setIcon(null);
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) character[next].setIcon(characterMode == 0 ? rightW : rightS);
            else if (e.getKeyCode() == KeyEvent.VK_LEFT) character[next].setIcon(characterMode == 0 ? leftW : leftS);
            else if (e.getKeyCode() == KeyEvent.VK_UP) character[next].setIcon(characterMode == 0 ? backW : backS);
            else if (e.getKeyCode() == KeyEvent.VK_DOWN) character[next].setIcon(characterMode == 0 ? frontW : frontS);
            
            characterPosition = next;
            toggleMode();

        } catch (IllegalAccessException ex) {
            if (ex.getMessage().equals("SIGN_HIT")) {
                JOptionPane.showMessageDialog(frame, "Press SPACEBAR to read the sign!");
            }
        } catch (RuntimeException ex) {
            System.out.println("Path blocked.");
        }
    }

    // 3. Spacebar interaction
    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
        checkForSign();
        checkForCave();
    }
}

    private void checkObstacle(int nextIndex) throws IllegalAccessException {
    int tile = mapLayout[nextIndex];
    
    // If the player hits a sign (ID 4), throw the exception
    if (tile == 4) {
        throw new IllegalAccessException("SIGN_HIT");
    }
    
    // If the player hits a tree (0) or cave (3), we don't need an exception, 
    // we just use this to block the move in the 'if' statement below.
    if (tile == 0 || tile == 3 || tile == 5) {
        throw new RuntimeException("BLOCKED"); 
    }
}

    private void checkForSign() {
        int[] directions = {
            characterPosition + 1,
            characterPosition - 1,
            characterPosition + mapWidth,
            characterPosition - mapWidth
        };

        for (int next : directions) {
            if (next >= 0 && next < mapLayout.length) {
                if (mapLayout[next] == 4 && signQuestions.containsKey(next)) {
                    showQuestion(signQuestions.get(next));
                    break;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    private void toggleMode() {
        characterMode = (characterMode == 0) ? 1 : 0;
    }

    private void toggleSprite(ImageIcon walk, ImageIcon stand) {
        character[characterPosition].setIcon(characterMode == 0 ? walk : stand);
        toggleMode();
    }

    private void rewardLetter(char letter) {
        if (collectedLetters.indexOf(String.valueOf(letter)) == -1) {
            collectedLetters.append(letter);
            JOptionPane.showMessageDialog(frame, "Correct! ✅\nYou obtained letter: " + letter + "\nCollected letters: " + collectedLetters);
        }
    }

    private void showQuestion(Question q) {
        if (q.type == QuestionType.MULTIPLE_CHOICE) {
            int answer = JOptionPane.showOptionDialog(frame, q.text, "Sign Question", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, q.choices, q.choices[0]);
            if (answer == q.correctIndex) rewardLetter(q.rewardLetter);
            else JOptionPane.showMessageDialog(frame, "Wrong ❌");
        } else {
            String input = JOptionPane.showInputDialog(frame, q.text, "Type your answer");
            if (input == null) return;
            if (input.trim().equalsIgnoreCase(q.correctAnswer)) rewardLetter(q.rewardLetter);
            else JOptionPane.showMessageDialog(frame, "Wrong ❌");
        }
    }
}
