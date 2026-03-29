package pd4mapobj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class PD4MapObj implements KeyListener {
    JFrame frame; 
    ImageIcon mapFloor; 
    ImageIcon meltedKey; 
    ImageIcon frozenKey; 
    ImageIcon torch; 
    ImageIcon cauldron; 
    ImageIcon note; 
    ImageIcon campfire; 
    ImageIcon door; 
    ImageIcon wall1; 
    ImageIcon wall2; 
    ImageIcon wall3; 
    ImageIcon wall4; 
    ImageIcon corner1; 
    ImageIcon corner2; 
    ImageIcon corner3; 
    ImageIcon corner4; 
    ImageIcon finishTile;
    
    ImageIcon frontS;
    ImageIcon frontW;
    ImageIcon leftS;
    ImageIcon leftW;
    ImageIcon rightS;
    ImageIcon rightW;
    ImageIcon backS;
    ImageIcon backW;

    JLabel tiles[];
    JLabel character[];
    int mapLayout[];
    int characterPlace[]; 
    int charPath[];
    int mapWidth=12;
    int mapHeight=12;
    int frameWidth=750;
    int frameHeight=750; 
    int objectives;
    int characterPosition; 
    int characterMode; 
    int direction; 
    int frozenKeyLocation; 
    int finishLocation;
    
    boolean keyIsMelted = false;
    
    int guessIndex = 0; 
    int melt1, melt2, melt3; 
    
    ImageIcon currentKeyIcon; 
    
    

    public PD4MapObj() { 
        frame=new JFrame();
        characterPosition=-1;
        characterMode=0;
        objectives=0; 
        direction=-1;

        mapFloor = new ImageIcon("PD Assets/floor.png"); 
        meltedKey = new ImageIcon("PD Assets/meltedKey.png"); 
        frozenKey = new ImageIcon("PD Assets/frozenKey.png"); 
        torch = new ImageIcon("PD Assets/torch2.png"); 
        cauldron = new ImageIcon("PD Assets/mysterypot.png"); 
        note = new ImageIcon("PD Assets/message.png"); 
        campfire = new ImageIcon("PD Assets/fire.png"); 
        door = new ImageIcon("PD Assets/mapDoor.png"); 
        wall1 = new ImageIcon("PD Assets/wall1.png"); 
        wall2 = new ImageIcon("PD Assets/wall2.png");  
        wall3 = new ImageIcon("PD Assets/wall3.png");  
        wall4 = new ImageIcon("PD Assets/wall4.png"); 
        corner1 = new ImageIcon("PD Assets/cornerBottomLeft.jpg"); 
        corner2 = new ImageIcon("PD Assets/cornerBottomRight.jpg"); 
        corner3 = new ImageIcon("PD Assets/cornerTopLeft.jpg"); 
        corner4 = new ImageIcon("PD Assets/cornerTopRight.jpg"); 
        finishTile = new ImageIcon("PD Assets/floor.png"); 
        
        frontS=new ImageIcon("PD Assets/Cody's Character/frontstand.png");
        frontW=new ImageIcon("PD Assets/Cody's Character/frontwalk.png");
        leftS=new ImageIcon("PD Assets/Cody's Character/leftstand.png");
        leftW=new ImageIcon("PD Assets/Cody's Character/leftwalk.png");
        rightS=new ImageIcon("PD Assets/Cody's Character/rightstand.png");
        rightW=new ImageIcon("PD Assets/Cody's Character/rightwalk.png");
        backS=new ImageIcon("PD Assets/Cody's Character/backstand.png");
        backW=new ImageIcon("PD Assets/Cody's Character/backwalk.png");
        
        mapFloor=new ImageIcon(mapFloor.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        meltedKey=new ImageIcon(meltedKey.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        frozenKey=new ImageIcon(frozenKey.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        torch=new ImageIcon(torch.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        cauldron=new ImageIcon(cauldron.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        note=new ImageIcon(note.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        campfire=new ImageIcon(campfire.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        wall1=new ImageIcon(wall1.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT)); 
        door=new ImageIcon(door.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT)); 
        wall2=new ImageIcon(wall2.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT)); 
        wall3=new ImageIcon(wall3.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT)); 
        wall4=new ImageIcon(wall4.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT)); 
        corner1=new ImageIcon(corner1.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT)); 
        corner2=new ImageIcon(corner2.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT)); 
        corner3=new ImageIcon(corner3.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT)); 
        corner4=new ImageIcon(corner4.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT)); 
        finishTile=new ImageIcon(finishTile.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        
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
            0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0, 
            0,0,0,0,0,1,0,0,0,2,0,0, 
            0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0 
        }; 
        
        for(int i=0;i<character.length;i++){
            if(characterPlace[i]==1){
                character[i]=new JLabel(frontS);
                characterPosition=i;
            } 
            else if(characterPlace[i]==2){ 
                character[i]=new JLabel(frozenKey); 
                currentKeyIcon = frozenKey;
                frozenKeyLocation=i;
            }
            else character[i]=new JLabel();
        }
       
        tiles = new JLabel[mapWidth*mapHeight];
        mapLayout=new int[]{
            4,6,6,6,6,6,6,6,6,6,6,3,
            9,0,0,0,0,0,0,0,0,0,0,7,
            9,0,0,0,0,0,0,0,12,0,0,6,
            9,0,0,10,0,0,0,0,0,0,14,5,
            9,0,0,0,0,0,0,0,0,0,0,8,
            9,0,0,0,0,0,0,0,0,0,0,7,
            9,0,0,0,0,0,0,0,0,0,0,7,
            9,0,0,0,0,0,0,0,0,0,0,7,
            9,0,11,0,0,0,0,0,0,0,0,7, 
            9,0,0,0,0,0,0,0,13,0,0,7, 
            9,0,0,0,0,0,0,0,0,0,0,7,
            2,8,8,8,8,8,8,8,8,8,8,1 
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
            else if(mapLayout[i]==10){
                tiles[i]= new JLabel(campfire); 
                melt1 = i;
            } 
            else if(mapLayout[i]==11){
                tiles[i]= new JLabel(cauldron); 
                melt3 = i;
            } 
            else if(mapLayout[i]==12){
                tiles[i]= new JLabel(torch); 
                melt2 = i;
            } 
            else if(mapLayout[i]==13) { 
                tiles[i] = new JLabel(note); 
                objectives = i;
            } 
            else if(mapLayout[i]==14) { 
                tiles[i] = new JLabel(finishTile); 
                finishLocation = i;
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
        if(mapLayout[index]==5 || mapLayout[index]==6 || mapLayout[index]==7 || mapLayout[index]==8 || mapLayout[index]==9 || mapLayout[index]==10 || mapLayout[index]==11 || mapLayout[index]==12){
            return true;
        } 
        if(index == finishLocation) return true;
        if(characterPlace[index]!=0) return true;
        return false;
    } 
    
    public void moveFrozenKey(){
        boolean isBoulder=false;
        int movement=-1; 
        if(direction==0){   //up
            if(characterPosition-mapWidth==frozenKeyLocation){
                isBoulder=true;
                movement=mapWidth*-1;
            } 
            
        }
        else if(direction==1){  //down
            if(characterPosition+mapWidth==frozenKeyLocation){
                isBoulder=true;
                movement=mapWidth;
            } 
            
        }
        else if(direction==2){  //left
            if(characterPosition-1==frozenKeyLocation){
                isBoulder=true;
                movement=-1;
            } 
            
        }
        else if(direction==3){  //right
            if(characterPosition+1==frozenKeyLocation){
                isBoulder=true;
                movement=1;
            } 
            
        } 
        
        
        
        if(isBoulder){
            if((mapLayout[frozenKeyLocation+movement]==0||frozenKeyLocation+movement==melt1||frozenKeyLocation+movement==melt2||frozenKeyLocation+movement==melt3 ||frozenKeyLocation+movement==finishLocation)&&characterPlace[frozenKeyLocation+movement]==0){
                character[frozenKeyLocation].setIcon(null);
                if(keyIsMelted) {
                    character[frozenKeyLocation + movement].setIcon(meltedKey);
                } 
                else {
                    character[frozenKeyLocation + movement].setIcon(frozenKey);
                }
                characterPlace[frozenKeyLocation]=0;
                characterPlace[frozenKeyLocation+movement]=3;
                frozenKeyLocation+=movement; 
                
                if(frozenKeyLocation==finishLocation) 
                {   
                
                if(guessIndex==3) 
                { 
                    tiles[finishLocation].setIcon(meltedKey);
                    JOptionPane.showMessageDialog(
                    frame,
                    "Congrats! You have passed this level!",
                    "Information Dialog",
                    JOptionPane.INFORMATION_MESSAGE
                    ); 
                    System.exit(0);
                } 
                else 
                {   
                    tiles[finishLocation].setIcon(frozenKey);
                    JOptionPane.showMessageDialog(
                    frame,
                    "You are not finished yet!",
                    "Error Dialog",
                    JOptionPane.ERROR_MESSAGE
                    );
                }
                }
            }
            
            if (frozenKeyLocation == melt1 && guessIndex == 0) {
                guessIndex++;
            }
            else if (frozenKeyLocation == melt3 && guessIndex == 1) {
                guessIndex++;
            }
            else if (frozenKeyLocation == melt2 && guessIndex == 2) {
                guessIndex++;
                keyIsMelted = true; 
                character[frozenKeyLocation].setIcon(meltedKey);

                JOptionPane.showMessageDialog(
                frame,
                "Congrats! You guessed the correct combination!",
                "Information Dialog",
                JOptionPane.INFORMATION_MESSAGE
                );

                double inputNumber = 0;
                while (inputNumber != 250) {
                String input = JOptionPane.showInputDialog(
                frame,
                "Calculate the total heat used to melt the ice:"
                );

                if (input == null) inputNumber = 0;
                else { 
                    try { 
                        inputNumber = Double.parseDouble(input);
                    } 
                    catch (NumberFormatException a) { 
                        JOptionPane.showMessageDialog(frame, "Please input a valid number!", "Error",JOptionPane.ERROR_MESSAGE);
                    }
                }

                if (inputNumber != 250) {
                    JOptionPane.showMessageDialog(
                    frame,
                    "Incorrect amount of heat. Try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                    );
                } 
                else {
                JOptionPane.showMessageDialog(
                frame,
                "You're right! The total heat is 250 J!",
                "Information Dialog",
                JOptionPane.INFORMATION_MESSAGE
                );
                }
                
            } 
            
            

        }
        else if (frozenKeyLocation == melt1 || frozenKeyLocation == melt2 || frozenKeyLocation == melt3  ) {
            JOptionPane.showMessageDialog(
            frame,
            "Wrong combination, buddy!",
            "Error Dialog",
            JOptionPane.ERROR_MESSAGE
            );
            guessIndex = 0;
        } 
           
                
            
        }
    }

    
    
    
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            direction=3;
            if (isWall(characterPosition + 1)) {
                if(characterMode == 0) {
                    character[characterPosition].setIcon(rightW);
                    characterMode = 1;
                }
                else{
                    character[characterPosition].setIcon(rightS);
                    characterMode = 0;
                }

            }
            else{
                character[characterPosition].setIcon(null);
                if(characterMode == 0) {
                    character[characterPosition + 1].setIcon(rightW);
                    characterMode = 1;
                }
                else{
                    character[characterPosition + 1].setIcon(rightS);
                    characterMode = 0;
                }
                characterPlace[characterPosition]=0;
                characterPosition++;
                characterPlace[characterPosition]=1;
            }

        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            direction=2;
            if (isWall(characterPosition - 1)) {
                if(characterMode == 0) {
                    character[characterPosition].setIcon(leftW);
                    characterMode = 1;
                }
                else{
                    character[characterPosition].setIcon(leftS);
                    characterMode = 0;
                }

            }
            else{
                character[characterPosition].setIcon(null);
                if(characterMode == 0) {
                    character[characterPosition - 1].setIcon(leftW);
                    characterMode = 1;
                }
                else{
                    character[characterPosition - 1].setIcon(leftS);
                    characterMode = 0;
                }
                characterPlace[characterPosition]=0;
                characterPosition--;
                characterPlace[characterPosition]=1;
            }
        } 
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            direction=1;
            if (isWall(characterPosition + mapWidth)) {
                if(characterMode == 0) {
                    character[characterPosition].setIcon(backW);
                    characterMode = 1;
                }
                else{
                    character[characterPosition].setIcon(backS);
                    characterMode = 0;
                }

            }
            else{
                character[characterPosition].setIcon(null);
                if(characterMode == 0) {
                    character[characterPosition + mapWidth].setIcon(backW);
                    characterMode = 1;
                }
                else{
                    character[characterPosition + mapWidth].setIcon(backS);
                    characterMode = 0;
                }
                characterPlace[characterPosition]=0;
                characterPosition+=mapWidth;
                characterPlace[characterPosition]=1;
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            direction=0;
            if (isWall(characterPosition - mapWidth)) {
                if(characterMode == 0) {
                    character[characterPosition].setIcon(frontW);
                    characterMode = 1;
                }
                else{
                    character[characterPosition].setIcon(frontS);
                    characterMode = 0;
                }

            }
            else{
                character[characterPosition].setIcon(null);
                if(characterMode == 0) {
                    character[characterPosition - mapWidth].setIcon(frontW);
                    characterMode = 1;
                }
                else{
                    character[characterPosition - mapWidth].setIcon(frontS);
                    characterMode = 0;
                }
                characterPlace[characterPosition]=0;
                characterPosition-=mapWidth;
                characterPlace[characterPosition]=1;
            }
        }
        else if(e.getKeyCode()==KeyEvent.VK_SPACE){
            this.moveFrozenKey();
        }
        
        if (characterPosition == objectives) {
            JOptionPane.showMessageDialog(
                    frame,
                    "To escape the level, melt the ice to get the key using the three materials in the correct sequence.",
                    "OBJECTIVE DIALOG",
                    JOptionPane.INFORMATION_MESSAGE
            ); 
            
            JOptionPane.showMessageDialog(
                    frame,
                    "Given: The cauldron's heat is 80 J. The torch's heat is 70. Lastly, the campfire's heat is 100 J. Good Luck!",
                    "OBJECTIVE DIALOG",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    
    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
    
}
