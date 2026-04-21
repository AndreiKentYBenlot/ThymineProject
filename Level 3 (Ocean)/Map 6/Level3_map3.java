package Project;

import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;
import javax.swing.border.LineBorder;

public class Level3_map3 implements KeyListener, ActionListener { 
    JFrame frame; 
    ImageIcon wall,floor,gate,blend,doorT,doorB,wind,witch;
    ImageIcon frontS;
    ImageIcon frontW;
    ImageIcon leftS;
    ImageIcon leftW;
    ImageIcon rightS;
    ImageIcon rightW;
    ImageIcon backS;
    ImageIcon backW;
    int Exit;
    boolean convoFinished = false;
    
    JLabel tiles[];
    JLabel character[];
    JLabel chatSpace;
    JLabel textSpace;
    JButton nextB;
    JButton exitB;
    int mapLayout[];
    int characterPlace[];
    int mapWidth;
    int mapHeight;
    int frameWidth;
    int frameHeight;
    int characterPosition;
    int characterMode;
    int NPCLocation;
    int direction;
    String NPCScript[];
    int scriptIndex;
    int puzzleMode;
    int randomizedNumbers[];
    
    
    public Level3_map3(){
        mapWidth=15;
        mapHeight=10;
        frameWidth=800;
        frameHeight=500;
        characterPosition=-1;
        characterMode=0;
        NPCLocation= 7;
        direction=-1;
        scriptIndex=0;
        puzzleMode=0;
    
        NPCScript = new String[]{
            "Hello Cody, you have now accomplished the required objectives from this level .",
            "however your journey does not end here....",
            "Beyond these walls lies a land of sugar and gates.",
            "Not all is as sweet as it may first seem,",
            "Step with both wit and a curious mind…",
            "Goodluck Traveler.....!"
        };
        
        frame=new JFrame();
        chatSpace = new JLabel();
        textSpace=new JLabel();
        nextB= new JButton("Next");
        exitB=new JButton("Cancel");
        
        wall = new ImageIcon("Project (2)/66.png");
        floor = new ImageIcon("Project (2)/68.png");
        blend = new ImageIcon("Project (2)/65.png");
        frontS = new ImageIcon("Project (2)/frontstand.png");
        frontW = new ImageIcon("Project (2)/frontwalk.png");
        leftS = new ImageIcon("Project (2)/leftstand.png");
        leftW = new ImageIcon("Project (2)/leftwalk.png");
        rightS = new ImageIcon("Project (2)/rightstand.png");
        rightW = new ImageIcon("Project (2)/rightwalk.png");
        backS = new ImageIcon("Project (2)/backstand.png");
        backW = new ImageIcon("Project (2)/backwalk.png");
        witch = new ImageIcon("Project (2)/72.png");
        doorT = new ImageIcon("Project (2)/69.png");
        doorB = new ImageIcon("Project (2)/70.png");
        wind = new ImageIcon("Project (2)/67.png");
        
        wall=new ImageIcon(wall.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        floor=new ImageIcon(floor.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        blend=new ImageIcon(blend.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        frontS=new ImageIcon(frontS.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        frontW=new ImageIcon(frontW.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        leftS=new ImageIcon(leftS.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        leftW=new ImageIcon(leftW.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        rightS=new ImageIcon(rightS.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        rightW=new ImageIcon(rightW.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        backS=new ImageIcon(backS.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        backW=new ImageIcon(backW.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        doorT=new ImageIcon(doorT.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        doorB=new ImageIcon(doorB.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        wind=new ImageIcon(wind.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));
        witch=new ImageIcon(witch.getImage().getScaledInstance((frameWidth/mapWidth), (frameHeight/mapHeight), Image.SCALE_DEFAULT));

        character = new JLabel[mapWidth*mapHeight];
        characterPlace = new int[]{
            1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
            9,0,0,0,0,0,0,0,0,0,0,0,0,0,9,
            9,0,0,0,0,0,0,0,0,0,0,0,0,0,9,
            9,0,0,0,0,0,0,0,0,0,3,0,0,0,9,
            9,0,2,0,0,0,0,0,0,0,0,0,0,0,9,
            9,0,0,0,0,0,0,0,0,0,0,0,0,0,9,
            9,0,0,0,0,0,0,0,0,0,0,0,0,0,9,
            9,9,9,9,9,9,9,9,9,9,9,9,9,9,9      
        };
        for(int i=0;i<character.length;i++){
            if(characterPlace[i]==2){
                character[i]=new JLabel(frontS);
                characterPosition=i;
            }
            else if(characterPlace[i]==3){
                character[i]=new JLabel(witch);
                NPCLocation=i;
            }
            else character[i]=new JLabel();
        }
        
        tiles=new JLabel[mapWidth*mapHeight];
        mapLayout=new int[]{
            1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
            1,5,1,5,1,5,1,3,1,5,1,5,1,5,1,
            2,2,2,2,2,2,2,4,2,2,2,2,2,2,2,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
        };
        for(int i=0;i<tiles.length;i++){
            if(mapLayout[i]==1) tiles[i]=new JLabel(wall);
            else if(mapLayout[i]==0) tiles[i]=new JLabel(floor);
            else if(mapLayout[i]==2) tiles[i]=new JLabel(blend);
            else if(mapLayout[i]==3) tiles[i]=new JLabel(wind);
            else if(mapLayout[i]==4) tiles[i]=new JLabel(blend);
            else if(mapLayout[i]==5) tiles[i]=new JLabel(wind);
            else tiles[i]=new JLabel();
            }
        }
    public void setConvoComponentsVisible(boolean b){
        textSpace.setVisible(b);
        exitB.setVisible(b);
        nextB.setVisible(b);
        chatSpace.setVisible(b);
    }
    
    public void setFrame(){
        frame.setLayout(new GraphPaperLayout(new Dimension(mapWidth,mapHeight)));
        
        chatSpace.setOpaque(true);
        chatSpace.setBackground(new Color(243,246,232));
        
        //if nauna sya ng add, nasa top/front sya na layer
        
        frame.add(textSpace, new Rectangle(1,8,9,1));
        frame.add(exitB, new Rectangle(7,9,2,1));
        frame.add(nextB, new Rectangle(9,9,2,1));
        frame.add(chatSpace, new Rectangle(0,7,11,4));
        
        this.setConvoComponentsVisible(false);
        
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
        for(int i=0;i<tiles.length;i++){
            frame.add(tiles[i], new Rectangle(x,y,w,h));
            x++;
            if(x%mapWidth==0){
                x=0;
                y++;
            }
        }
        
        frame.setSize(frameWidth,frameHeight);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        
        frame.addKeyListener(this);
        exitB.addActionListener(this);
        nextB.addActionListener(this);
    }
    

    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (characterPosition % mapWidth != mapWidth - 1) {
                int next = characterPosition + 1;

                if (characterPlace[next] != 1 && characterPlace[next] != 3 && !(mapLayout[next] == 3 || mapLayout[next] == 4)) {
                    character[characterPosition].setIcon(null);
                    character[next].setIcon(characterMode==0 ? rightW : rightS);
                    characterMode = 1 - characterMode;
                    characterPosition = next;
                } else {
                    character[characterPosition].setIcon(characterMode==0 ? rightW : rightS);
                    characterMode = 1 - characterMode;
                }
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (characterPosition % mapWidth != 0) {
                int next = characterPosition - 1;

                if (characterPlace[next] != 1 && characterPlace[next] != 3 && !(mapLayout[next] == 3 || mapLayout[next] == 4)) {
                    character[characterPosition].setIcon(null);
                    character[next].setIcon(characterMode==0 ? leftW : leftS);
                    characterMode = 1 - characterMode;
                    characterPosition = next;
                } else {
                    character[characterPosition].setIcon(characterMode==0 ? leftW : leftS);
                    characterMode = 1 - characterMode;
                }
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (characterPosition + mapWidth < characterPlace.length) {
                int next = characterPosition + mapWidth;

                if (characterPlace[next] != 1 && characterPlace[next] != 3 && !(mapLayout[next] == 3 || mapLayout[next] == 4)) {
                    character[characterPosition].setIcon(null);
                    character[next].setIcon(characterMode==0 ? frontW : frontS);
                    characterMode = 1 - characterMode;
                    characterPosition = next;
                } else {
                    character[characterPosition].setIcon(characterMode==0 ? frontW : frontS);
                    characterMode = 1 - characterMode;
                }
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (characterPosition - mapWidth >= 0) {
                int next = characterPosition - mapWidth;

                if (characterPlace[next] != 1 && characterPlace[next] != 3 && !(mapLayout[next] == 3 || mapLayout[next] == 4)) {
                    character[characterPosition].setIcon(null);
                    character[next].setIcon(characterMode==0 ? backW : backS);
                    characterMode = 1 - characterMode;
                    characterPosition = next;
                } else {
                    character[characterPosition].setIcon(characterMode==0 ? backW : backS);
                    characterMode = 1 - characterMode;
                }
            }
        }
        else if(e.getKeyCode()==KeyEvent.VK_SPACE){
            if(
                characterPosition + 1 == NPCLocation ||
                characterPosition - 1 == NPCLocation ||
                characterPosition + mapWidth == NPCLocation ||
                characterPosition - mapWidth == NPCLocation
            ){
                textSpace.setText(NPCScript[scriptIndex]);
                scriptIndex++;
                setConvoComponentsVisible(true);

                if(scriptIndex >= NPCScript.length){
                    scriptIndex = 0;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Level3_map3 x=new Level3_map3();
        x.setFrame();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==exitB){
            this.setConvoComponentsVisible(false);
            frame.setFocusable(true);
            scriptIndex=0;
            textSpace.setText(NPCScript[scriptIndex]);
            puzzleMode=0;
        }
        else if(e.getSource()==nextB){
            if(puzzleMode==0){
                if(scriptIndex < NPCScript.length){
                    textSpace.setText(NPCScript[scriptIndex]);
                    scriptIndex++;
                }
                else{
                    
                    convoFinished = true;

                   
                    for(int i = 0; i < tiles.length; i++){
                        if(mapLayout[i] == 3){
                            tiles[i].setIcon(doorT);
                        }
                        else if(mapLayout[i] == 4){
                            tiles[i].setIcon(doorB);
                        }
                    }

                    JOptionPane.showMessageDialog(frame, "The door is now open!");
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}

