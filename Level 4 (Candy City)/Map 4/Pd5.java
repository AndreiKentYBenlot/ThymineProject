package pd5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pd5 implements KeyListener {

    JFrame frame; 
    ImageIcon mapFloor; 
    
    ImageIcon door; 
    ImageIcon wall1; 
    ImageIcon wall2; 
    ImageIcon wall3; 
    ImageIcon wall4; 
    ImageIcon corner1; 
    ImageIcon corner2; 
    ImageIcon corner3; 
    ImageIcon corner4; 
    ImageIcon box; 
    ImageIcon questionsTile; 
    ImageIcon objectiveNote; 
   
    
    ImageIcon frontS;
    ImageIcon frontW;
    ImageIcon leftS;
    ImageIcon leftW;
    ImageIcon rightS;
    ImageIcon rightW;
    ImageIcon backS;
    ImageIcon backW;
    
    JLabel tiles[];
    int mapLayout[]; 
    JLabel character[];
    pd5child player;
    int characterMode;  
    int characterPlace[]; 
    int mapWidth=12;
    int mapHeight=12;
    int frameWidth=750;
    int frameHeight=750; 
    int direction; 
    int objectives; 
    int questionLocation; 
    int correctIndex; 
    int wrongIndex; 
    int totalIndex;
    int boxLocation; 
    int moveIndex; 
    
    

    public Pd5() { 
        frame=new JFrame();
        player = new pd5child(-1);
        characterMode=0; 
        direction=-1; 
        objectives=0; 
        moveIndex = 0;

        mapFloor = new ImageIcon("PD Assets/floor.png"); 
        door = new ImageIcon("PD Assets/mapDoor.png"); 
        wall1 = new ImageIcon("PD Assets/wall1.png"); 
        wall2 = new ImageIcon("PD Assets/wall2.png");  
        wall3 = new ImageIcon("PD Assets/wall3.png");  
        wall4 = new ImageIcon("PD Assets/wall4.png"); 
        corner1 = new ImageIcon("PD Assets/cornerBottomLeft.jpg"); 
        corner2 = new ImageIcon("PD Assets/cornerBottomRight.jpg"); 
        corner3 = new ImageIcon("PD Assets/cornerTopLeft.jpg"); 
        corner4 = new ImageIcon("PD Assets/cornerTopRight.jpg"); 
        box = new ImageIcon("PD Assets/box.png"); 
        objectiveNote = new ImageIcon("PD Assets/message.png"); 
        questionsTile = new ImageIcon("PD Assets/question.png");
        
        
        frontS=new ImageIcon("PD Assets/Cody's Character/frontstand.png");
        frontW=new ImageIcon("PD Assets/Cody's Character/frontwalk.png");
        leftS=new ImageIcon("PD Assets/Cody's Character/leftstand.png");
        leftW=new ImageIcon("PD Assets/Cody's Character/leftwalk.png");
        rightS=new ImageIcon("PD Assets/Cody's Character/rightstand.png");
        rightW=new ImageIcon("PD Assets/Cody's Character/rightwalk.png");
        backS=new ImageIcon("PD Assets/Cody's Character/backstand.png");
        backW=new ImageIcon("PD Assets/Cody's Character/backwalk.png");
        
        mapFloor=new ImageIcon(mapFloor.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        wall1=new ImageIcon(wall1.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT)); 
        door=new ImageIcon(door.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT)); 
        wall2=new ImageIcon(wall2.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT)); 
        wall3=new ImageIcon(wall3.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT)); 
        wall4=new ImageIcon(wall4.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT)); 
        corner1=new ImageIcon(corner1.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT)); 
        corner2=new ImageIcon(corner2.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT)); 
        corner3=new ImageIcon(corner3.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT)); 
        corner4=new ImageIcon(corner4.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT)); 
        box=new ImageIcon(box.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT)); 
        questionsTile=new ImageIcon(questionsTile.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));  
        objectiveNote=new ImageIcon(objectiveNote.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT)); 
        
        
        
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
            0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,
            0,2,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0, 
            0,0,0,0,0,0,0,0,0,0,0,0, 
            0,0,0,0,0,0,1,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0 
        }; 
        
        for(int i=0;i<character.length;i++){
            if(characterPlace[i]==1){
                character[i]=new JLabel(frontS);
                player.setPosition(i);
            } 
            
            else character[i]=new JLabel();
        }
        
        tiles = new JLabel[mapWidth*mapHeight];
        mapLayout=new int[]{
            4,6,6,6,6,6,6,6,6,6,6,3,
            9,0,0,0,0,0,0,0,0,0,0,7,
            6,0,0,0,0,0,0,0,0,0,0,7,
            5,10,11,0,0,0,0,0,0,0,0,7,
            8,0,0,0,0,0,0,0,0,0,0,7,
            9,0,0,0,0,0,0,0,0,0,0,7,
            9,0,0,0,0,0,0,0,0,0,0,7,
            9,0,0,0,0,0,0,0,0,0,0,7,
            9,0,0,0,0,0,0,0,0,12,0,7, 
            9,0,0,0,0,0,0,0,0,0,0,7, 
            9,0,0,0,0,0,0,0,0,0,0,7,
            2,8,8,8,9,0,0,7,8,8,8,1 
        };
        
        for(int i=0;i<tiles.length;i++){
            if(mapLayout[i]==0) tiles[i]=new JLabel(mapFloor);
            else if(mapLayout[i]==1) tiles[i]=new JLabel(corner3); 
            else if(mapLayout[i]==2) tiles[i]= new JLabel(corner4);
            else if(mapLayout[i]==3) tiles[i]= new JLabel(corner1);
            else if(mapLayout[i]==4) tiles[i]= new JLabel(corner2);
            else if(mapLayout[i]==5) tiles[i]= new JLabel(door); 
            else if(mapLayout[i]==6) tiles[i]= new JLabel(wall1); 
            else if(mapLayout[i]==7) tiles[i]= new JLabel(wall2); 
            else if(mapLayout[i]==8) tiles[i]= new JLabel(wall3); 
            else if(mapLayout[i]==9) tiles[i]= new JLabel(wall4); 
            else if(mapLayout[i]==10) { 
                tiles[i] = new JLabel(box); 
                boxLocation = i; 
                
            }
            else if(mapLayout[i]==11) { 
                tiles[i] = new JLabel(questionsTile); 
                questionLocation = i;
            } 
            else if(mapLayout[i]==12) { 
                tiles[i] = new JLabel(objectiveNote); 
                objectives = i;
            }
        }
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

    x=0; 
    y=0;

    for(int i=0;i<tiles.length;i++){
        frame.add(tiles[i], new Rectangle(x,y,w,h));
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
    } 
    
    public boolean isWall(int index){
        if(index < 0 || index >= mapLayout.length){
            return true;
        }
        if(mapLayout[index]==5 || mapLayout[index]==6 || mapLayout[index]==7 || mapLayout[index]==8 || mapLayout[index]==9 || mapLayout[index]==10 || mapLayout[index]==1 || mapLayout[index]==2 || mapLayout[index]==3 || mapLayout[index]==4){
            return true;
        } 
        
        return false;
    } 
    
    public void moveBox() { 
        int newLocation = boxLocation + mapWidth;

    
    if(mapLayout[newLocation] == 0) {

        
        mapLayout[boxLocation] = 0;
        tiles[boxLocation].setIcon(mapFloor);
    
       
        mapLayout[newLocation] = 10;
        tiles[newLocation].setIcon(box);

        
        boxLocation = newLocation; 
        moveIndex = 1;
    } 
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
        
        
        if (player.getPosition() == objectives) {
            JOptionPane.showMessageDialog(
                    frame,
                    "To escape the level, you must move the box by answering all the questions CORRECTLY",
                    "OBJECTIVE DIALOG",
                    JOptionPane.INFORMATION_MESSAGE
            ); 
            
            JOptionPane.showMessageDialog(
                    frame,
                    "1 wrong answer and it will be an automatic restart. Good Luck!",
                    "OBJECTIVE DIALOG",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } 
        if(moveIndex==0) {
        if(player.getPosition() == questionLocation) { 
            while(correctIndex != 4) { 
            wrongIndex = 0; 
            correctIndex = 0;
            int answer1; 
            totalIndex = 1;
            do {  
                String[] options1 = {"False", "True"};

                    answer1 = JOptionPane.showOptionDialog(
                    frame,
                    "A pH value of 7 is neutral.",
                    "Question 1",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options1,
                    options1[0]
                    );
            } while (answer1 == -1);    
                    if (answer1 == 1) {
                    correctIndex++;
                    }           
                    else {
                    wrongIndex++;
                    } 
            totalIndex++; 
            
            int answer2; 
                do { 
                    String[] options2 = {
                    "Weak acid",
                    "Strong acid",
                    "Weak base",
                    "Neutral"
                    };

                answer2 = JOptionPane.showOptionDialog(
                frame,
                "A substance with pH = 2 is:",
                "Question 2",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options2,
                options2[0]
                );
                } while(answer2 == -1); 
                if(answer2 == 1) { 
                    correctIndex++;
                } 
                else { 
                    wrongIndex++;
                } 
                totalIndex++;
             
            
            String input1;
            double answer3 = 0;

            while (true) {
                input1 = JOptionPane.showInputDialog(
                frame,
                "A block has mass = 12 g and volume = 3 cm³.\n" +
                "What is its density?\n" +
                "Use d = g/mL as a guide."
                );

                if (input1 == null) continue; 

                try {
                    answer3 = Double.parseDouble(input1);
                    break;
                } 
                catch(NumberFormatException a) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid number!");
                }
            }

            if (answer3 == 4) {
            correctIndex++;
            } else {
            wrongIndex++;
            }

            totalIndex++;
             
             
                double answer4 = 0;  
                String input2;
                double correct = 20 * 0.4 * 9.8 * 5.0;
                while(true) { 
                    input2 = JOptionPane.showInputDialog(
                    frame,
                    "A 20.0 kg table is pushed across a classroom floor for 5.0 m at constant velocity.\n" +
                    "The coefficient of kinetic friction between the table and the floor is 0.40.\n" +
                    "Take g = 9.8 m/s^2\n" +
                    "\n" +
                    "Assuming all the work done against friction is converted into heat,\n" +
                    "\n" +
                    "how much work (in Joules) is done in moving the table? \n\nUse the following formulas in the order: \n1. N = mg <-- (Normal Force) \n2. Kf = (co-e of friction)(Normal Force) <-- (Kinetic Friction Force) \n3. W = Fd <-- (Work)"
                    ); 
                      
                    if (input2 == null) continue; 

                    try {
                        answer4 = Double.parseDouble(input2);
                        break;
                    } 
                    catch(NumberFormatException a) {
                        JOptionPane.showMessageDialog(frame, "Please enter a valid number!");
                    }
                } 
                
                if(answer4 == correct) { 
                    correctIndex++;
                } 
                else { 
                    wrongIndex++;
                } 
            
            JOptionPane.showMessageDialog(
                    frame,
                    "You scored " + correctIndex + " out of " + totalIndex + ".",
                    "Result",
                    JOptionPane.INFORMATION_MESSAGE
            ); 
                
            if(correctIndex != 4) { 
                
            
            JOptionPane.showMessageDialog(
                    frame,
                    "Unfortunately, you have to answer every question correctly. \nSorry! I guess you will have to try again :/",
                    "Result",
                    JOptionPane.ERROR_MESSAGE
            ); 
            
            } 
            } 
            
            JOptionPane.showMessageDialog(
                    frame,
                    "You have successfully answered all questions correctly and passed this level! \nThe box is now moved! :)",
                    "Result",
                    JOptionPane.INFORMATION_MESSAGE
            );  
            
            JOptionPane.showMessageDialog(
                    frame,
                    "Congratulations! You have passed this level!",
                    "Result",
                    JOptionPane.INFORMATION_MESSAGE 
                    
            ); 
            
            
            moveBox(); 
            mapLayout[questionLocation] = 0;
            tiles[questionLocation].setIcon(mapFloor); 
            
            
        }
        } 
        else { 
            if(player.getPosition() == questionLocation) { 
                int x = 0;
            }
        }
    }

    
    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
    
    public static void main(String[] args) {
        new Pd5().setFrame();
    }
    
}
 

