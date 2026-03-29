import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Q4P7 extends JPanel implements KeyListener
{
    private JFrame frame;
    private ImageIcon boxLocation,door,H3OWall,hydrogenBox,LewisWall,oxygenBox,wall,floor,playerImg;
    
    private int frameWidth=800,frameHeight=800;
    private int mapWidth=7,mapHeight=6;
    
    private JLabel tiles[];
    private int mapLayout[],baseMap[],startMapLayout[];
    private int playerIndex;

    private final int WALL=0,L_WALL=1,DOOR=2,H3O_WALL=3,FLOOR=4;
    private final int O_BOX=5,H_BOX=6,H_TARGET=7,PLAYER=8;
    private final int O_TARGET=9,LOCKED_O=10,LOCKED_H=11;

  
    public Q4P7()
    {
        frame=new JFrame("H3O Lewis Puzzle");

        boxLocation=new ImageIcon("Images/boxLocation.png");
        door=new ImageIcon("Images/door.png");
        floor=new ImageIcon("Images/floor.png");
        H3OWall=new ImageIcon("Images/H3OWall.png");
        hydrogenBox=new ImageIcon("Images/hydrogenBox.png");
        LewisWall=new ImageIcon("Images/LewisWall.png");
        oxygenBox=new ImageIcon("Images/oxygenBox.png");
        wall=new ImageIcon("Images/wall.png");
        playerImg=new ImageIcon("Images/PD4CodyPlaceholder.png");

        boxLocation=new ImageIcon(boxLocation.getImage().getScaledInstance(frameWidth/mapWidth,frameHeight/mapHeight,Image.SCALE_DEFAULT));
        door=new ImageIcon(door.getImage().getScaledInstance(frameWidth/mapWidth,frameHeight/mapHeight,Image.SCALE_DEFAULT)); 
        floor=new ImageIcon(floor.getImage().getScaledInstance(frameWidth/mapWidth,frameHeight/mapHeight,Image.SCALE_DEFAULT));
        H3OWall=new ImageIcon(H3OWall.getImage().getScaledInstance(frameWidth/mapWidth,frameHeight/mapHeight,Image.SCALE_DEFAULT)); 
        hydrogenBox=new ImageIcon(hydrogenBox.getImage().getScaledInstance(frameWidth/mapWidth,frameHeight/mapHeight,Image.SCALE_DEFAULT)); 
        LewisWall=new ImageIcon(LewisWall.getImage().getScaledInstance(frameWidth/mapWidth,frameHeight/mapHeight,Image.SCALE_DEFAULT)); 
        oxygenBox=new ImageIcon(oxygenBox.getImage().getScaledInstance(frameWidth/mapWidth,frameHeight/mapHeight,Image.SCALE_DEFAULT)); 
        wall=new ImageIcon(wall.getImage().getScaledInstance(frameWidth/mapWidth,frameHeight/mapHeight,Image.SCALE_DEFAULT));
        playerImg=new ImageIcon(playerImg.getImage().getScaledInstance(frameWidth/mapWidth,frameHeight/mapHeight,Image.SCALE_DEFAULT));

        tiles=new JLabel[42];

        startMapLayout=new int[]
        {
            0,1,0,2,0,3,0,
            4,4,4,4,4,4,4,
            4,7,4,9,4,7,4, 
            4,4,5,4,4,4,4, 
            4,6,4,7,6,6,4, 
            4,4,4,8,4,4,4  
        };

        resetGame();
    }

  
    public void resetGame()
    {
        mapLayout=startMapLayout.clone();
        baseMap=new int[42];

        for(int i=0;i<mapLayout.length;i++)
        {
            if(mapLayout[i]==H_TARGET||mapLayout[i]==O_TARGET)
                baseMap[i]=mapLayout[i];
            else
                baseMap[i]=FLOOR;

            if(mapLayout[i]==PLAYER)
            {
                playerIndex=i;
                mapLayout[i]=FLOOR;
            }

            if(tiles[i]==null)
                tiles[i]=new JLabel();
        }

        renderMap();
    }

  
    public void renderMap()
    {
        for(int i=0;i<mapLayout.length;i++)
        {
            if(i==playerIndex) tiles[i].setIcon(playerImg);
            else if(mapLayout[i]==WALL) tiles[i].setIcon(wall);
            else if(mapLayout[i]==L_WALL) tiles[i].setIcon(LewisWall);
            else if(mapLayout[i]==DOOR) tiles[i].setIcon(door);
            else if(mapLayout[i]==H3O_WALL) tiles[i].setIcon(H3OWall);
            else if(mapLayout[i]==FLOOR) tiles[i].setIcon(floor);
            else if(mapLayout[i]==O_BOX||mapLayout[i]==LOCKED_O) tiles[i].setIcon(oxygenBox);
            else if(mapLayout[i]==H_BOX||mapLayout[i]==LOCKED_H) tiles[i].setIcon(hydrogenBox);
            else if(mapLayout[i]==H_TARGET||mapLayout[i]==O_TARGET) tiles[i].setIcon(boxLocation);
        }
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        try
        {
            //“R” reset game
            if(e.getKeyCode()==KeyEvent.VK_R)
            {
                resetGame();
                return;
            }

            int dir=0;

            //arrow keys movement
            if(e.getKeyCode()==KeyEvent.VK_UP) dir=-mapWidth;
            else if(e.getKeyCode()==KeyEvent.VK_DOWN) dir=mapWidth;
            else if(e.getKeyCode()==KeyEvent.VK_LEFT) dir=-1;
            else if(e.getKeyCode()==KeyEvent.VK_RIGHT) dir=1;

            //if key isn't arrow or “R”, throws an exception 
            else throw new Exception("Invalid key");

            //moves player
            playerIndex+=dir;
            renderMap();

        }

          
        catch(Exception ex)
        {
            //catches invalid input and shows a message
            JOptionPane.showMessageDialog(frame,
                "Invalid input. Please use arrow keys or press 'R' to reset.");
        }
    }

  
    public void setFrame()
    {
        frame.setLayout(new GridLayout(mapHeight,mapWidth));

        for(JLabel tile:tiles) frame.add(tile);

        frame.setSize(frameWidth,frameHeight);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(this);
    }

  
    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
}

