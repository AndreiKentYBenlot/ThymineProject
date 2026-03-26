package CS4Q3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Q3PD6 implements KeyListener {

    JFrame frame;
    ImageIcon bone, boneinv, tile, tendril, eye; 
    ImageIcon q1Tile, q2Tile, q3Tile, q4Tile, finishTile;
    ImageIcon frontS;
    ImageIcon frontW;
    ImageIcon leftS;
    ImageIcon leftW;
    ImageIcon rightS;
    ImageIcon rightW;
    ImageIcon backS;
    ImageIcon backW; 
    int finishLocation; 
    int q1Location; 
    int q2Location; 
    int q3Location; 
    int q4Location;

    JLabel tiles[];
    inheritance2 player;
    int characterMode;
    JLabel character[]; 
    int characterPlace[];
    int mapLayout[]; 
    int direction;

    int mapWidth = 9;
    int mapHeight = 9;
    int frameWidth = 600;
    int frameHeight = 600;


    public Q3PD6() {
        frame = new JFrame();
        player = new inheritance2(-1);
        characterMode=0; 
        direction=-1;

        bone = new ImageIcon("Q3PD4ASSETS/bone tile 100x100.jpg");
        tile = new ImageIcon("Q3PD4ASSETS/flesh tile 100x100.jpg");
        boneinv = new ImageIcon("Q3PD4ASSETS/mirrored bone tile 100x100.jpg");
        tendril = new ImageIcon("Q3PD4ASSETS/tendril tile 100x100.jpg");
        eye = new ImageIcon("Q3PD4ASSETS/eye tile 100x100.jpg"); 
        
        q1Tile = new ImageIcon("Q3PD4ASSETS/questionTile.png"); 
        q2Tile = new ImageIcon("Q3PD4ASSETS/questionTile.png"); 
        q3Tile = new ImageIcon("Q3PD4ASSETS/questionTile.png"); 
        q4Tile = new ImageIcon("Q3PD4ASSETS/questionTile.png");
        finishTile = new ImageIcon("Q3PD4ASSETS/flesh tile 100x100.jpg"); 
        
        frontS=new ImageIcon("Q3PD4ASSETS/frontstand.png");
        frontW=new ImageIcon("Q3PD4ASSETS/frontwalk.png");
        leftS=new ImageIcon("Q3PD4ASSETS/leftstand.png");
        leftW=new ImageIcon("Q3PD4ASSETS/leftwalk.png");
        rightS=new ImageIcon("Q3PD4ASSETS/rightstand.png");
        rightW=new ImageIcon("Q3PD4ASSETS/rightwalk.png");
        backS=new ImageIcon("Q3PD4ASSETS/backstand.png");
        backW=new ImageIcon("Q3PD4ASSETS/backwalk.png");

        bone = scale(bone);
        tile = scale(tile);
        boneinv = scale(boneinv);
        tendril = scale(tendril);
        eye = scale(eye); 
        
        q1Tile = scale(q1Tile); 
        q2Tile = scale(q2Tile); 
        q3Tile = scale(q3Tile); 
        q4Tile = scale(q4Tile);
        finishTile = scale(finishTile);
        
        frontS = scale(frontS); 
        frontW = scale(frontW); 
        leftS = scale(leftS); 
        leftW = scale(leftW); 
        rightS = scale(rightS); 
        rightW = scale(rightW); 
        backS = scale(backS); 
        backW = scale(backW); 
        
        
        
        character=new JLabel[mapWidth*mapHeight]; 
        characterPlace = new int[]{
            0,0,0,0,0,0,0,0,0,
            1,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0
        };
        
        for(int i=0;i<character.length;i++){
            if(characterPlace[i]==1){
                character[i]=new JLabel(rightS);
                player.setPosition(i);
            }
            else character[i]=new JLabel();
        }

        
        tiles = new JLabel[mapWidth * mapHeight];
        mapLayout = new int[]{
            1,1,4,4,1,5,2,1,2,
            2,2,7,2,2,1,3,3,4,
            4,4,4,3,2,1,2,4,2,
            1,3,5,4,8,1,5,3,4,
            5,3,1,4,2,5,1,1,5,
            5,5,3,2,2,9,2,1,3,
            4,3,4,3,2,5,2,2,5,
            4,3,5,4,5,1,4,10,1,
            5,4,2,4,2,1,3,6,5
        };

        for (int i = 0; i < tiles.length; i++) {
            if (mapLayout[i] == 1) tiles[i] = new JLabel(bone);
            else if (mapLayout[i] == 2) tiles[i] = new JLabel(tile);
            else if (mapLayout[i] == 3) tiles[i] = new JLabel(tendril);
            else if (mapLayout[i] == 4) tiles[i] = new JLabel(boneinv);
            else if (mapLayout[i] == 5) tiles[i] = new JLabel(eye); 
            else if (mapLayout[i] == 6) { 
                tiles[i] = new JLabel(finishTile); 
                finishLocation = i;
            } 
            else if (mapLayout[i] == 7) { 
                tiles[i] = new JLabel(q1Tile); 
                q1Location = i;
            } 
            else if (mapLayout[i] == 8) { 
                tiles[i] = new JLabel(q2Tile); 
                q2Location = i;
            }  
            else if (mapLayout[i] == 9) { 
                tiles[i] = new JLabel(q3Tile); 
                q3Location = i;
            } 
            else if (mapLayout[i] == 10) { 
                tiles[i] = new JLabel(q4Tile); 
                q4Location = i;
            } 
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

         int x=0, y=0, w=1, h=1;
        for(int i=0;i<character.length;i++){
            frame.add(character[i], new Rectangle(x,y,w,h));
            x++;
            if(x%mapWidth==0){
                x=0;
                y++;
            }
        }

        x=0; y=0; w=1; h=1;
        for (JLabel tile1 : tiles) {
            frame.add(tile1, new Rectangle(x,y,w,h));
            x++;
            if(x%mapWidth==0){
                x=0;
                y++;
            }
        }

        frame.setSize(frameWidth, frameHeight);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addKeyListener(this); 
        
        JOptionPane.showMessageDialog(
                    frame,
                    "Cody: Whe-Where am I? What is this place? I gotta find a way out of here...",
                    "Dialogue",
                    JOptionPane.INFORMATION_MESSAGE
            );
    }
    
    public boolean isWall(int index){
        if(index < 0 || index >= mapLayout.length){
            return true;
        }
        if(mapLayout[index]==1 || mapLayout[index]==3 || mapLayout[index]==4 || mapLayout[index]==5){
            return true;
        } 
        
        return false;
    } 
    
    @Override
    public void keyTyped(KeyEvent e){
        
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            direction=3;
            if (isWall(player.getPosition() + 1)) {
                if(characterMode == 0) {
                    character[player.getPosition()].setIcon(rightW);
                    characterMode = 1;
                }
                else{
                    character[player.getPosition()].setIcon(rightS);
                    characterMode = 0;
                }

            }
            else{
                character[player.getPosition()].setIcon(null);
                if(characterMode == 0) {
                    character[player.getPosition() + 1].setIcon(rightW);
                    characterMode = 1;
                }
                else{
                    character[player.getPosition() + 1].setIcon(rightS);
                    characterMode = 0;
                }
                characterPlace[player.getPosition()]=0;
                player.setPosition(player.getPosition() + 1);
                characterPlace[player.getPosition()]=1;
            }

        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            direction=2;
            if (isWall(player.getPosition() - 1)) {
                if(characterMode == 0) {
                    character[player.getPosition()].setIcon(leftW);
                    characterMode = 1;
                }
                else{
                    character[player.getPosition()].setIcon(leftS);
                    characterMode = 0;
                }

            }
            else{
                character[player.getPosition()].setIcon(null);
                if(characterMode == 0) {
                    character[player.getPosition() - 1].setIcon(leftW);
                    characterMode = 1;
                }
                else{
                    character[player.getPosition() - 1].setIcon(leftS);
                    characterMode = 0;
                }
                characterPlace[player.getPosition()]=0;
                player.setPosition(player.getPosition() - 1);
                characterPlace[player.getPosition()]=1;
            }
        } 
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            direction=1;
            if (isWall(player.getPosition() + mapWidth)) {
                if(characterMode == 0) {
                    character[player.getPosition()].setIcon(backW);
                    characterMode = 1;
                }
                else{
                    character[player.getPosition()].setIcon(backS);
                    characterMode = 0;
                }

            }
            else{
                character[player.getPosition()].setIcon(null);
                if(characterMode == 0) {
                    character[player.getPosition() + mapWidth].setIcon(backW);
                    characterMode = 1;
                }
                else{
                    character[player.getPosition() + mapWidth].setIcon(backS);
                    characterMode = 0;
                }
                characterPlace[player.getPosition()]=0;
                player.setPosition(player.getPosition() + mapWidth);
                characterPlace[player.getPosition()]=1;
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            direction=0;
            if (isWall(player.getPosition() - mapWidth)) {
                if(characterMode == 0) {
                    character[player.getPosition()].setIcon(frontW);
                    characterMode = 1;
                }
                else{
                    character[player.getPosition()].setIcon(frontS);
                    characterMode = 0;
                }

            }
            else{
                character[player.getPosition()].setIcon(null);
                if(characterMode == 0) {
                    character[player.getPosition() - mapWidth].setIcon(frontW);
                    characterMode = 1;
                }
                else{
                    character[player.getPosition() - mapWidth].setIcon(frontS);
                    characterMode = 0;
                }
                characterPlace[player.getPosition()]=0;
                player.setPosition(player.getPosition() - mapWidth);
                characterPlace[player.getPosition()]=1;
            }
        }
        
        
        if(player.getPosition()==q1Location) { 
            int answer1; 
            do { 
                String[] options1 = {"False", "True"};

                answer1 = JOptionPane.showOptionDialog(
                frame,
                "In Griffith’s experiment, the R strain of Streptococcus pneumoniae was virulent and caused sickness in mice.",
                "Question 1",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options1,
                options1[0]
                );
            }while(answer1 == -1); 
                if(answer1 == 0) { 
                    character[player.getPosition()].setIcon(null);
                if(characterMode==0){   
                    character[player.getPosition()+1].setIcon(rightW);
                    characterMode=1;
                }
                else{
                    character[player.getPosition()+1].setIcon(rightS);
                    characterMode=0;
                }
                player.setPosition(player.getPosition() + 1);
                JOptionPane.showMessageDialog(
                    frame,
                    "Cody: That was not so bad.",
                    "Dialogue",
                    JOptionPane.INFORMATION_MESSAGE
                );
                } 
                else { 
                    character[player.getPosition()].setIcon(null);
                if(characterMode==0){   
                    character[player.getPosition()-1].setIcon(rightW);
                    characterMode=1;
                }
                else{
                    character[player.getPosition()-1].setIcon(rightS);
                    characterMode=0;
                }
                player.setPosition(player.getPosition() - 1);
                    
                
                
                }
        } 
        
        if(player.getPosition()==q2Location) { 
            JOptionPane.showMessageDialog(
                    frame,
                    "Cody: Another one?",
                    "Dialogue",
                    JOptionPane.INFORMATION_MESSAGE
                );
            int answer2; 
            do { 
                String[] options2 = {"False", "True"};

                answer2 = JOptionPane.showOptionDialog(
                frame,
                "In the Hershey and Chase experiment, radioactive sulfur (³⁵S) was used to label DNA.",
                "Question 2",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options2,
                options2[0]
                );
            }while(answer2 == -1); 
                if(answer2 == 0) { 
                    character[player.getPosition()].setIcon(null);
                if(characterMode==0){   
                    character[player.getPosition()+mapWidth].setIcon(backW);
                    characterMode=1;
                }
                else{
                    character[player.getPosition()+mapWidth].setIcon(backS);
                    characterMode=0;
                }
                player.setPosition(player.getPosition() + mapWidth);
                JOptionPane.showMessageDialog(
                    frame,
                    "Cody: Okay. It's getting a bit difficult",
                    "Dialogue",
                    JOptionPane.INFORMATION_MESSAGE
                );
                } 
                else { 
                    character[player.getPosition()].setIcon(null);
                if(characterMode==0){   
                    character[player.getPosition() - mapWidth].setIcon(backW);
                    characterMode=1;
                }
                else{
                    character[player.getPosition() - mapWidth].setIcon(backS);
                    characterMode=0;
                }
                player.setPosition(player.getPosition() - mapWidth);
                
                
                }
        }
        
        if(player.getPosition()==q3Location) { 
            JOptionPane.showMessageDialog(
                    frame,
                    "Cody: How many questions are there....?",
                    "Dialogue",
                    JOptionPane.INFORMATION_MESSAGE
            );
            int answer3; 
            do { 
                String[] options3 = {"False", "True"};

                answer3 = JOptionPane.showOptionDialog(
                frame,
                "According to Watson and Crick’s model, adenine pairs with thymine through two hydrogen bonds.",
                "Question 3",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options3,
                options3[0]
                );
            }while(answer3 == -1); 
                if(answer3 == 1) { 
                    character[player.getPosition()].setIcon(null);
                if(characterMode==0){   
                    character[player.getPosition()+1].setIcon(rightW);
                    characterMode=1;
                }
                else{
                    character[player.getPosition()+1].setIcon(rightS);
                    characterMode=0;
                }
                player.setPosition(player.getPosition() + 1);
                JOptionPane.showMessageDialog(
                    frame,
                    "Cody: I am almost there...",
                    "Dialogue",
                    JOptionPane.INFORMATION_MESSAGE
                );
                } 
                else { 
                    character[player.getPosition()].setIcon(null);
                if(characterMode==0){   
                    character[player.getPosition()-1].setIcon(rightW);
                    characterMode=1;
                }
                else{
                    character[player.getPosition()-1].setIcon(rightS);
                    characterMode=0;
                }
                player.setPosition(player.getPosition() - 1);
                    
                
                
                }
        } 
        if(player.getPosition()==q4Location) { 
            JOptionPane.showMessageDialog(
                    frame,
                    "Cody: Please be the last one",
                    "Dialogue",
                    JOptionPane.INFORMATION_MESSAGE
                );
            int answer4; 
            do { 
                String[] options4 = {"False", "True"};

                answer4 = JOptionPane.showOptionDialog(
                frame,
                "The sugar-phosphate backbone forms the outer portion of the DNA double helix.",
                "Question 4",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options4,
                options4[0]
                );
            }while(answer4 == -1); 
                if(answer4 == 1) { 
                    character[player.getPosition()].setIcon(null);
                if(characterMode==0){   
                    character[player.getPosition()+mapWidth].setIcon(backW);
                    characterMode=1;
                }
                else{
                    character[player.getPosition()+mapWidth].setIcon(backS);
                    characterMode=0;
                }
                player.setPosition(player.getPosition() + mapWidth); 
                JOptionPane.showMessageDialog(
                    frame,
                    "Cody: Yes! I am finally free!",
                    "Congratulations!",
                    JOptionPane.INFORMATION_MESSAGE
                ); 
                System.exit(0);
                
                } 
                else { 
                    character[player.getPosition()].setIcon(null);
                if(characterMode==0){   
                    character[player.getPosition() - mapWidth].setIcon(backW);
                    characterMode=1;
                }
                else{
                    character[player.getPosition() - mapWidth].setIcon(backS);
                    characterMode=0;
                }
                player.setPosition(player.getPosition() - mapWidth);
                
                
                }
        }
        
        
    }
    
    @Override
    public void keyReleased(KeyEvent e){
        
    }

    
    
    public static void main(String[] args) {
        new Q3PD6().setFrame();
    }
}
