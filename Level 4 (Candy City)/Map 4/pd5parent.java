
package pd5;

import javax.swing.*;

public class pd5parent {
    
    protected int position; 
    
    public pd5parent(int startPosition) {
        this.position = startPosition;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int newPosition) {
        position = newPosition;
    }
    
}
