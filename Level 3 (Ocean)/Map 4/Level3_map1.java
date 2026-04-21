package Project;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Level3_map1 implements KeyListener {

    JFrame frame;
    ImageIcon wall, water, gates, c1, c2, c3, c4, c5;
    ImageIcon Tf, Tb, Tl, Tr;
    ImageIcon frontS;
    ImageIcon frontW;
    ImageIcon leftS;
    ImageIcon leftW;
    ImageIcon rightS;
    ImageIcon rightW;
    ImageIcon backS;
    ImageIcon backW;
    int Exit;
    int previousPosition;
    int startPosition;
    int followerStart;
    int retryCount = 0;
    String retryFile = "retries.txt";

    JLabel chatSpace, textSpace, waters[], character[];
    
    int mapLayout[], characterPlace[];
    int mapWidth = 15;
    int mapHeight = 10;
    int frameWidth = 950;
    int frameHeight = mapHeight * (frameWidth / mapWidth);
    int characterPosition, finishLocation, characterMode, followerloc;
    int questionloc1, questionloc2, questionloc3, questionloc4, questionloc5;
    
    
    boolean[] questionAnswered = new boolean[6]; 
    String[] questions = new String[6];          
    boolean[] correctAnswer = new boolean[6];    
    
    boolean allQuestionsAnswered() {
    for (int i = 1; i <= 5; i++) {
        if (!questionAnswered[i]) return false;
    }
    return true;
}


    public Level3_map1() {
        loadRetries();
        frame = new JFrame();
        characterPosition = -1;
        characterMode = 0;
        
        wall = new ImageIcon("Images/wall.png"); 
        water = new ImageIcon("Images/Final_water.png");
        gates = new ImageIcon("Images/Gates.png");        
        c1 = new ImageIcon("Images/Chest_1.png");
        c2 = new ImageIcon("Images/Chest_1.png"); 
        c3 = new ImageIcon("Images/Chest_1.png"); 
        c4 = new ImageIcon("Images/Chest_1.png");
        c5 = new ImageIcon("Images/Chest_1.png"); 
        Tf = new ImageIcon("Images/37.png");
        Tb = new ImageIcon("Images/40.png");
        Tl = new ImageIcon("Images/39.png");
        Tr = new ImageIcon("Images/38.png");
        frontS=new ImageIcon("Images/frontstand.png");
        frontW=new ImageIcon("Images/frontwalk.png");
        leftS=new ImageIcon("Images/leftstand.png");
        leftW=new ImageIcon("Images/leftwalk.png");
        rightS=new ImageIcon("Images/rightstand.png");
        rightW=new ImageIcon("Images/rightwalk.png");
        backS=new ImageIcon("Images/backstand.png");
        backW=new ImageIcon("Images/backwalk.png");

        wall = new ImageIcon(wall.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        water = new ImageIcon(water.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        gates = new ImageIcon(gates.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        c1 = new ImageIcon(c1.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        c2 = new ImageIcon(c2.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        c3 = new ImageIcon(c3.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        c4 = new ImageIcon(c4.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        c5 = new ImageIcon(c5.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        Tf = new ImageIcon(Tf.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        Tb = new ImageIcon(Tb.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        Tl = new ImageIcon(Tl.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        Tr = new ImageIcon(Tr.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        frontS = new ImageIcon(frontS.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        frontW = new ImageIcon(frontW.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        leftS = new ImageIcon(leftS.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        leftW = new ImageIcon(leftW.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        rightS = new ImageIcon(rightS.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        rightW = new ImageIcon(rightW.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        backS = new ImageIcon(backS.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));
        backW = new ImageIcon(backW.getImage().getScaledInstance((frameWidth / mapWidth), (frameHeight / mapHeight), Image.SCALE_DEFAULT));

        questions[1] = "What the great white is the largest shark among its species";
        questions[2] = "Spongebob is a star";
        questions[3] = "Coral reefs are often refered to as the rainforest of the seas ";
        questions[4] = "Is water wet?";
        questions[5] = "Some fish, like the angler fish, have relationship with sea anemones for protection.?";

        correctAnswer[1] = false;   
        correctAnswer[2] = false;  
        correctAnswer[3] = true;  
        correctAnswer[4] = false;   
        correctAnswer[5] = false;  
        
        
        character = new JLabel[mapWidth * mapHeight];
        characterPlace = new int[]{
            1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1,
            0, 1, 2, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 3, 1,
            0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1,
            0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1,
            1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 4,
            1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1,
            0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1,
            1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1,
            1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1,
            1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1,};
        for (int i = 0; i < character.length; i++) {
            switch (characterPlace[i]) {
                case 2:
                    character[i] = new JLabel(frontS);
                    characterPosition = i;
                    startPosition = i; 
                    characterPlace[i] = i;
                    break;
                case 3:
                    character[i]=new JLabel(Tf);
                    followerloc=i;
                    followerStart = i; // ⭐ SAVE START POSITION
                    break;
                case 4:
                    Exit=i;
                    character[i]=new JLabel();
                    characterPlace[i]=0;
                default:
                    character[i] = new JLabel();
                    break;
            }
        }

        waters = new JLabel[mapWidth * mapHeight];
        mapLayout = new int[]{
            1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1,
            2, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1,
            0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 6, 0, 1,
            0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1,
            1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 7,
            1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1,
            3, 0, 1, 0, 0, 0, 1, 1, 1, 5, 1, 0, 1, 0, 1,
            1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1,
            1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1,
            1, 0, 0, 1, 1, 1, 4, 1, 1, 1, 0, 1, 1, 1, 1,};

        for (int i = 0; i < waters.length; i++) {
            switch (mapLayout[i]) {
                case 0:
                    waters[i] = new JLabel(water);
                    break;
                case 1:
                    waters[i] = new JLabel(wall);
                    break;
                case 2:
                    waters[i] = new JLabel(c1);
                    questionloc1=i;           
                    break;
                case 3:
                    waters[i] = new JLabel(c2);
                    questionloc2=i; 
                    break;
                case 4:
                    waters[i] = new JLabel(c3);
                    questionloc3=i; 
                    break;
                case 5:
                    waters[i] = new JLabel(c4);
                    questionloc4=i; 
                    break;
                case 6:
                    waters[i] = new JLabel(c5);
                    questionloc5=i; 
                    break;
                case 7:
                    waters[i] = new JLabel(gates);
                    finishLocation=i;
                    break;

                default:
                    break;
            }
        }
        
        
        setFrame();
        frame.setSize(frameWidth, frameHeight);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        frame.addKeyListener(this);
    };
    
    public void setFrame() {
    frame.setLayout(new GraphPaperLayout(new Dimension(mapWidth, mapHeight)));

    int x = 0, y = 0;

    for (JLabel ch : character) {
        frame.add(ch, new Rectangle(x, y, 1, 1));
        x++;
        if (x % mapWidth == 0) {
            x = 0;
            y++;
        }
    }
    for (JLabel tile : waters) {
        frame.add(tile, new Rectangle(x, y, 1, 1));
        x++;
        if (x % mapWidth == 0) {
            x = 0;
            y++;
        }
    }

    
    x = 0;
    y = 0;
    for (JLabel tile : waters) {
        frame.add(tile, new Rectangle(x, y, 1, 1));
        x++;
        if (x % mapWidth == 0) {
            x = 0;
            y++;
        }
    }
}
    public void checkSpace(){
        if(characterPosition==Exit){
            Level3_map2 m=new Level3_map2();
            m.setFrame();
            frame.dispose();
        }
    }
    
    public void moveFollower() {
        for (int stepCount = 0; stepCount < 1; stepCount++) {

            int nextMove = getNextStepBFS();

            if (nextMove == -1) return;


            character[followerloc].setIcon(null);
            character[nextMove].setIcon(Tf);

            characterPlace[followerloc] = 0;
            characterPlace[nextMove] = 4;

            followerloc = nextMove;


            if (followerloc == characterPosition) {
                JOptionPane.showMessageDialog(frame, "Caught by the turtle!");
                resetPlayer();
                break;
            }
        }
    }
    
    public int getNextStepBFS() {
        int size = mapWidth * mapHeight;

        boolean[] visited = new boolean[size];
        int[] prev = new int[size]; 
        Arrays.fill(prev, -1);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(followerloc);
        visited[followerloc] = true;

        int[] directions = {-mapWidth, mapWidth, -1, 1}; 

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (current == characterPosition) break;

            for (int d : directions) {
                int next = current + d;

                if (next < 0 || next >= size) continue;

                
                if (d == -1 && current % mapWidth == 0) continue;
                if (d == 1 && current % mapWidth == mapWidth - 1) continue;

                if (!visited[next] && mapLayout[next] != 1) { 
                    visited[next] = true;
                    prev[next] = current;
                    queue.add(next);
                }
            }
        }
        int step = characterPosition;
        while (prev[step] != -1 && prev[step] != followerloc) {
            step = prev[step];
        }
        return step;
    }

    public void resetPlayer() {

    retryCount++;      
    saveRetries();     

    JOptionPane.showMessageDialog(frame,
        "You were caught!\nTotal retries: " + retryCount);

    character[characterPosition].setIcon(null);
    characterPosition = startPosition;
    character[characterPosition].setIcon(frontS);

    character[followerloc].setIcon(null);
    followerloc = followerStart;
    character[followerloc].setIcon(Tf);

    characterPlace[followerloc] = 4;
}
    
    public void checkQuestion(int pos) {
    int questionIndex = -1;
    if (pos == questionloc1) questionIndex = 1;
    else if (pos == questionloc2) questionIndex = 2;
    else if (pos == questionloc3) questionIndex = 3;
    else if (pos == questionloc4) questionIndex = 4;
    else if (pos == questionloc5) questionIndex = 5;
    else return;

    
    if (questionAnswered[questionIndex]) return; 

    Object[] options = {"True", "False"};
    int choice = JOptionPane.showOptionDialog(frame,
            questions[questionIndex],
            "Question",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);

    boolean isYes = choice == JOptionPane.YES_OPTION;

    if (isYes == correctAnswer[questionIndex]) {
        
        questionAnswered[questionIndex] = true;
        JOptionPane.showMessageDialog(frame, "Correct!");
        
        if (allQuestionsAnswered()) {
                waters[finishLocation].setIcon(water); 
                mapLayout[finishLocation] = 0;         
                JOptionPane.showMessageDialog(frame, "The gate has opened!");
        } 
    }
        else {
            JOptionPane.showMessageDialog(frame, "Wrong! Try again.");

            character[pos].setIcon(null);
            character[previousPosition].setIcon(frontS);
            characterPosition = previousPosition;
        }
    }

    public void loadRetries() {
    try {
        File file = new File(retryFile);

        if (!file.exists()) {
            file.createNewFile();
            retryCount = 0;
            return;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();

        if (line != null) {
            retryCount = Integer.parseInt(line);
        }

        br.close();

    } catch (Exception e) {
        retryCount = 0;
    }
}
    
    public void saveRetries() {
    try {
        BufferedWriter bw = new BufferedWriter(new FileWriter(retryFile));
        bw.write(String.valueOf(retryCount));
        bw.close();
    } catch (Exception e) {
        System.out.println("Error saving retries");
    }
}
   
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    checkSpace();

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

            previousPosition = characterPosition; 

            if (characterMode == 0) {
                character[characterPosition + 1].setIcon(rightW);
                characterMode = 1;
            } else {
                character[characterPosition + 1].setIcon(rightS);
                characterMode = 0;
            }
            characterPosition++;
        }
        moveFollower();

    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {

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

            previousPosition = characterPosition; 

            if (characterMode == 0) {
                character[characterPosition - 1].setIcon(leftW);
                characterMode = 1;
            } else {
                character[characterPosition - 1].setIcon(leftS);
                characterMode = 0;
            }
            characterPosition--;
        }
        moveFollower();

    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {

        if (characterPlace[characterPosition + mapWidth] == 1) {
            if (characterMode == 0) {
                character[characterPosition].setIcon(frontW);
                characterMode = 1;
            } else {
                character[characterPosition].setIcon(frontS);
                characterMode = 0;
            }
        } else {
            character[characterPosition].setIcon(null);

            previousPosition = characterPosition;

            if (characterMode == 0) {
                character[characterPosition + mapWidth].setIcon(frontW);
                characterMode = 1;
            } else {
                character[characterPosition + mapWidth].setIcon(frontS);
                characterMode = 0;
            }
            characterPosition += mapWidth;
        }
        moveFollower();

    } else if (e.getKeyCode() == KeyEvent.VK_UP) {

        if (characterPlace[characterPosition - mapWidth] == 1) {
            if (characterMode == 0) {
                character[characterPosition].setIcon(backW);
                characterMode = 1;
            } else {
                character[characterPosition].setIcon(backS);
                characterMode = 0;
            }
        } else {
            character[characterPosition].setIcon(null);

            previousPosition = characterPosition; 

            if (characterMode == 0) {
                character[characterPosition - mapWidth].setIcon(backW);
                characterMode = 1;
            } else {
                character[characterPosition - mapWidth].setIcon(backS);
                characterMode = 0;
            }
            characterPosition -= mapWidth;
        }
        moveFollower();
    }

    checkQuestion(characterPosition);
}

    public static void main(String[] args) {
        Level3_map1 x=new Level3_map1();
        x.setFrame();
    }
    
    @Override
    public void keyReleased(KeyEvent e) {

    }

}
