package Quarter_3;

import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;
import javax.swing.border.LineBorder;
import Quarter_2.GraphPaperLayout;

public class PD6 implements KeyListener { 
    JFrame frame; 
    ImageIcon wall,water,gates,c1,c2,c3,c4;
    ImageIcon frontS;
    ImageIcon frontW;
    ImageIcon leftS;
    ImageIcon leftW;
    ImageIcon rightS;
    ImageIcon rightW;
    ImageIcon backS;
    ImageIcon backW;
    int Exit;
    
    JLabel kraken,chatSpace,textSpace,waters[],character[];
    int mapLayout[],characterPlace[];
    int mapWidth = 15,mapHeight = 10,frameWidth = 800, frameHeight = 500; 
    int characterPosition,startPosition,finishLocation,characterMode;
    boolean[] questionTiles;
    boolean[] corals;
    
    String[] questions = {
        "Is water wet",
        "What species is Dory",
        "In what ocean did the titanic sink",
        "Ano ang nasa gitna ng dagat",        
        "What is the largest ocean",
        
        "How many hearts does an octupus have",
        "Fish can fly?",
        "What color is the ocean",
        "Which is faster: sailfish or swordfish",
        "What body part do fish use to breathe",
        
        "What do you call a baby fish",
        "What kills more",
        "What is the deepest trench",
        "Are whale sharks whales",
        "What is the Great Barrier Reef made up of",
        
        "Which marine mammal is known as the 'Unicorn of the Sea'",
        "What species of fish was Nemo from the Disney film 'Finding Nemo'",
        "Are corals animals",
        "How many brains does an octopus have",
        "Is kelp algae"        
    };

    String[] questionType = {
        "TF", "ID", "ID", "MC", "ID",   
        "MC", "TF", "ID", "ID", "ID",   
        "MC", "MC", "ID", "TF", "ID",   
        "ID", "ID", "TF", "MC", "TF"
    };
    
    boolean[] correctAnswers = {
        false, true, true, true, true,
        true, false, false, false, false,
        false, false, false, false, false,
        false, false, true, true, true
    };        
    
    String[] identificationAnswers = {
    "",        "BLUE TANG", "NORTH ATLANTIC OCEAN", "",         "PACIFIC OCEAN",
    "",        "",          "BLUE",                 "SAILFISH", "GILLS",
    "",        "",          "MARIANA TRENCH",       "",         "CORALS",
    "NARWHAL", "CLOWNFISH", "",                     "",         ""
    };
    
    String[] mcData = {
    "",           "",             "", "g|G|0",  "", 
    "3|8|0",      "",             "", "",       "",
    "calf|fry|1", "orca|shark|0", "", "",       "",
    "",           "",             "", "10|9|1", ""    
    };
    
    JLabel darkness[];
    int lightRadius = 2;
    
    int answeredTotal = 0,answeredCorrect = 0;      

    public PD6() { 
        frame = new JFrame(); 
        characterPosition = -1;
        characterMode=0;
        chatSpace=new JLabel();
        textSpace=new JLabel();
        corals = new boolean[mapWidth * mapHeight]; 

        wall = new ImageIcon("PD6_UpdatedImages/wall.png"); 
        water = new ImageIcon("PD6_UpdatedImages/Final_Water.png"); 
        c1 = new ImageIcon("PD6_UpdatedImages/50.png"); 
        c2 = new ImageIcon("PD6_UpdatedImages/51.png"); 
        c3 = new ImageIcon("PD6_UpdatedImages/52.png"); 
        c4 = new ImageIcon("PD6_UpdatedImages/53.png"); 
        gates = new ImageIcon("PD6_UpdatedImages/Gates.png"); 
        kraken = new JLabel(new ImageIcon("PD6_UpdatedImages/kraken.png"));
        frontS=new ImageIcon("PD6_UpdatedImages/frontstand.png");
        frontW=new ImageIcon("PD6_UpdatedImages/frontwalk.png");
        leftS=new ImageIcon("PD6_UpdatedImages/leftstand.png");
        leftW=new ImageIcon("PD6_UpdatedImages/leftwalk.png");
        rightS=new ImageIcon("PD6_UpdatedImages/rightstand.png");
        rightW=new ImageIcon("PD6_UpdatedImages/rightwalk.png");
        backS=new ImageIcon("PD6_UpdatedImages/backstand.png");
        backW=new ImageIcon("PD6_UpdatedImages/backwalk.png");
        
        wall = new ImageIcon(wall.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT)); 
        water = new ImageIcon(water.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT)); 
        c1 = new ImageIcon(c1.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT)); 
        c2 = new ImageIcon(c2.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT)); 
        c3 = new ImageIcon(c3.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT)); 
        c4 = new ImageIcon(c4.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT)); 
        gates = new ImageIcon(gates.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT)); 
        kraken.setIcon(new ImageIcon(((ImageIcon)kraken.getIcon()).getImage().getScaledInstance((frameWidth/mapWidth)*5,(frameHeight/mapHeight)*7, Image.SCALE_DEFAULT)));
        frontS=new ImageIcon(frontS.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        frontW=new ImageIcon(frontW.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        leftS=new ImageIcon(leftS.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        leftW=new ImageIcon(leftW.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        rightS=new ImageIcon(rightS.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        rightW=new ImageIcon(rightW.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        backS=new ImageIcon(backS.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        backW=new ImageIcon(backW.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        
        darkness = new JLabel[mapWidth * mapHeight];
        for (int i = 0; i < darkness.length; i++) {
            darkness[i] = new JLabel();
            darkness[i].setOpaque(true);
            darkness[i].setBackground(new Color(0, 0, 0, 255)); 
            darkness[i].setBorder(new LineBorder(new Color(0,0,0,180),100));
        }
        
        character = new JLabel[mapWidth * mapHeight];
        characterPlace = new int[]{
            1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
            1,0,0,0,0,0,0,0,1,0,0,0,0,0,1,
            1,0,1,1,1,0,0,0,0,0,0,0,0,0,1,
            0,0,1,0,0,0,0,1,0,0,0,0,0,0,1,
            2,0,1,0,1,1,1,1,0,0,0,0,0,0,3,
            0,0,1,0,0,0,0,1,0,0,0,0,0,0,1,
            1,0,1,1,1,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,1,0,0,0,0,0,1,
            1,0,0,0,1,1,1,0,0,0,0,0,0,0,1,
            1,1,1,1,1,1,1,1,1,1,1,1,1,1,1
        };
        for (int i = 0; i < character.length; i++) {
            switch (characterPlace[i]) {
                case 2 -> {
                    character[i] = new JLabel(frontS);
                    characterPosition = i;
                    startPosition = i;
                }
                case 3 -> {
                    Exit=i;
                    character[i]=new JLabel();
                    characterPlace[i]=0;
                }
                default -> character[i] = new JLabel();
            }
        }

        waters = new JLabel[mapWidth * mapHeight]; 
        questionTiles = new boolean[mapWidth * mapHeight];
        mapLayout = new int[]{ 
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, 
            0,2,1,1,1,1,4,1,0,1,8,6,6,6,0, 
            0,1,0,0,0,1,1,1,1,1,8,6,6,6,0, 
            7,1,0,1,1,1,1,0,1,1,8,6,6,6,0, 
            7,1,0,1,0,0,0,0,1,1,8,6,6,6,9, 
            7,1,0,3,1,1,1,0,1,1,8,6,6,6,0, 
            0,1,0,0,0,1,1,1,1,1,8,6,6,6,0, 
            0,1,1,1,1,5,1,1,0,1,8,6,6,6,0,
            0,1,1,1,0,0,0,1,1,1,8,6,6,6,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 
        };         
        for (int i = 0; i < waters.length; i++) { 
            switch (mapLayout[i]) { 
                case 0 -> waters[i] = new JLabel(wall); 
                case 1 -> waters[i] = new JLabel(water); 
                case 2 -> {
                    waters[i] = new JLabel(c1);
                    corals[i] = true;}
                case 3 -> {
                    waters[i] = new JLabel(c2);
                    corals[i] = true;} 
                case 4 -> {
                    waters[i] = new JLabel(c3);
                    corals[i] = true;} 
                case 5 -> {
                    waters[i] = new JLabel(c4);
                    corals[i] = true;}
                case 6 -> waters[i] = new JLabel(water); 
                case 7 -> waters[i] = new JLabel(gates);
                case 8 -> {
                    waters[i] = new JLabel(water);
                    questionTiles[i] = true;
                }
                case 9 -> {
                    waters[i] = new JLabel(gates);
                    finishLocation = i;
                }
            } 
        }         
    } 
    public class Player {
    private int position;
    private int startPosition; //encapsulation
    private int correctAnswers;

    public Player(int startPosition) {
        this.startPosition = startPosition;
        this.position = startPosition;
        this.correctAnswers = 0;
    }

    public int getPosition() {
        return position;
    }

    public void move(int newPosition) {
        position = newPosition;
    }

    public void reset() {
        position = startPosition;
        correctAnswers = 0;
    }

    public void addCorrect() {
        correctAnswers++;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }
}
    public void setConvoComponentsVisible(boolean b){
        textSpace.setVisible(b);
        chatSpace.setVisible(b);
    }
    
    public void setFrame() {
        frame.setLayout(new GraphPaperLayout(new Dimension(mapWidth, mapHeight)));
        chatSpace.setOpaque(true);
        chatSpace.setBackground(new Color(243,246,232, 204));
        
        frame.add(textSpace, new Rectangle(1,7,8,2));
        frame.add(chatSpace, new Rectangle(0,6,7,4));
              
        this.setConvoComponentsVisible(false);
        
        int dx = 0, dy = 0;
        for (int i = 0; i < mapWidth * mapHeight; i++) {
            frame.add(darkness[i], new Rectangle(dx, dy, 1, 1));
            dx++;
            if (dx == mapWidth) {
                dx = 0;
                dy++;
            }
        }
        frame.add(kraken, new Rectangle(10,1,4,8));
        int x = 0, y = 0;
        for (int i = 0; i < mapWidth * mapHeight; i++) {
            frame.add(character[i], new Rectangle(x, y, 1, 1));        
            frame.add(waters[i], new Rectangle(x, y, 1, 1));
            x++;
            if (x == mapWidth) {
                x = 0;
                y++;
            }
        }
        
        updateDarkness();
        frame.setSize(frameWidth, frameHeight);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.addKeyListener(this);
    }

    public void updateDarkness() {
        for (int i = 0; i < darkness.length; i++) {

            int px = characterPosition % mapWidth;
            int py = characterPosition / mapWidth;

            int x = i % mapWidth;
            int y = i / mapWidth;

            int distance = Math.abs(px - x) + Math.abs(py - y);

            darkness[i].setVisible(distance > lightRadius);
        }        
    }
    
    public void checkSpace(){
        if(characterPosition==Exit){
            PD4_ver2 m=new PD4_ver2();
            m.setFrame();
            frame.dispose();
        }
    }

    @Override //override
    public void keyPressed(KeyEvent e) {
        checkSpace();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT -> {
                if(characterPlace[characterPosition+1]!=0){
                    if(characterMode==0){
                        character[characterPosition].setIcon(rightW);
                        characterMode=1;
                    }
                    else{
                        character[characterPosition].setIcon(rightS);
                        characterMode=0;
                    }
                }
                else{
                    character[characterPosition].setIcon(null);
                    if(characterMode==0){
                        character[characterPosition+1].setIcon(rightW);
                        characterMode=1;
                    }
                    else{
                        character[characterPosition+1].setIcon(rightS);
                        characterMode=0;
                    }
                    characterPosition++;
                    updateDarkness();
                }
            }
            case KeyEvent.VK_LEFT -> {
                if(characterPlace[characterPosition-1]==1){
                    if(characterMode==0){
                        character[characterPosition].setIcon(leftW);
                        characterMode=1;
                    }
                    else{
                        character[characterPosition].setIcon(leftS);
                        characterMode=0;
                    }
                }
                else{
                    character[characterPosition].setIcon(null);
                    if(characterMode==0){
                        character[characterPosition-1].setIcon(leftW);
                        characterMode=1;
                    }
                    else{
                        character[characterPosition-1].setIcon(leftS);
                        characterMode=0;
                    }
                    characterPosition--;
                    updateDarkness();
                }
            }
            case KeyEvent.VK_DOWN -> {
                if(characterPlace[characterPosition+mapWidth]==1){
                    if(characterMode==0){
                        character[characterPosition].setIcon(frontW);
                        characterMode=1;
                    }
                    else{
                        character[characterPosition].setIcon(frontS);
                        characterMode=0;
                    }
                }
                else{
                    character[characterPosition].setIcon(null);
                    if(characterMode==0){
                        character[characterPosition+mapWidth].setIcon(frontW);
                        characterMode=1;
                    }
                    else{
                        character[characterPosition+mapWidth].setIcon(frontS);
                        characterMode=0;
                    }
                    characterPosition+=mapWidth;
                    updateDarkness();
                }
            }
            case KeyEvent.VK_UP -> {
                if(characterPlace[characterPosition-mapWidth]==1){
                    if(characterMode==0){
                        character[characterPosition].setIcon(backW);
                        characterMode=1;
                    }
                    else{
                        character[characterPosition].setIcon(backS);
                        characterMode=0;
                    }
                }
                else{
                    character[characterPosition].setIcon(null);
                    if(characterMode==0){
                        character[characterPosition-mapWidth].setIcon(backW);
                        characterMode=1;
                    }
                    else{
                        character[characterPosition-mapWidth].setIcon(backS);
                        characterMode=0;
                    }
                    characterPosition-=mapWidth;
                    updateDarkness();
                }
            }
            default -> {
            }
        }
        
        switch (mapLayout[characterPosition]) {
            case 2 -> { textSpace.setText("<html>You should always complete the word like: <br><br>"
                    + "North Atlantic Ocean and Pacific Ocean <br> or Mariana Trench");
                      setConvoComponentsVisible(true);}            
            case 3 -> { textSpace.setText("<html>In the movie 'Finding Nemo' <br><br>"
                    + "Nemo is a clownfish"
                    + " while Dory is a Blue Tang");
                    setConvoComponentsVisible(true);}
            case 4 -> { textSpace.setText("<html>Always put an 's' if there is more than one!<br><br>"
                    + "Example: Gills and Corals");
                    setConvoComponentsVisible(true);}
            case 5 -> { textSpace.setText("<html>Check the other corals before answering<br>"
                    + "and take note of what it says!!");
                    setConvoComponentsVisible(true);}
            default -> setConvoComponentsVisible(false);
        }
        
            if (questionTiles[characterPosition]) {
            int qIndex = answeredTotal;

            boolean isCorrect = false;

                switch (questionType[qIndex]) {
                    case "TF" ->                     {
                            Object[] options = {"True", "False"};
                            int choice = JOptionPane.showOptionDialog(
                                    frame,
                                    questions[qIndex],
                                    "Question " + (qIndex + 1),
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    options,
                                    options[0]);
                            boolean playerAnswer = (choice == JOptionPane.YES_OPTION);
                            isCorrect = (playerAnswer == correctAnswers[qIndex]);
                        }
                    case "MC" ->                     {
                            String[] data = mcData[qIndex].split("\\|");
                            Object[] options = { data[0], data[1] };
                            int choice = JOptionPane.showOptionDialog(
                                    frame,
                                    questions[qIndex],
                                    "Question " + (qIndex + 1),
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    options,
                                    options[0]);
                            isCorrect = (choice == Integer.parseInt(data[2]));
                        }
                    default -> {
                        String answer = JOptionPane.showInputDialog(
                                frame,
                                questions[qIndex] + "\n(Type in ALL CAPS)");
                        if (answer != null) {
                            isCorrect = answer.equals(identificationAnswers[qIndex]);
                        }
                    }
                }

            if (isCorrect) {
                answeredCorrect++;
                answeredTotal++;

                JOptionPane.showMessageDialog(frame,"Correct!\nProgress: " + answeredCorrect + "/20");

                character[characterPosition].setIcon(null);

                if (answeredCorrect == 20) {
                    characterPosition++;
                    kraken.setVisible(false);
                } else {
                    characterPosition--;
                }

                character[characterPosition].setIcon(rightS);
                updateDarkness();
            } else {
                JOptionPane.showMessageDialog(frame,"Oops! Incorrect.\nRestarting...");

                answeredTotal = 0;
                answeredCorrect = 0;

                character[characterPosition].setIcon(null);
                characterPosition = startPosition;
                character[characterPosition].setIcon(frontS);
                
                updateDarkness();
            }
        }
        if (characterPosition == finishLocation) {
            JOptionPane.showMessageDialog(frame, "🎉 YOU WIN!");
        }
    }
    
    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
    
    public static void main(String[] args) {
        PD6 x=new PD6();
        x.setFrame();
    }
}