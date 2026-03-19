package PD;
import Quarter2.*;
import java.awt.*;
import java.util.Hashtable;
public class GraphPaperLayout implements LayoutManager2 {
    int hgap;   
    int vgap;  
    Dimension gridSize;
    Hashtable<Component, Rectangle> compTable;

    public GraphPaperLayout() {
        this(new Dimension(1,1));
    }
    
    public GraphPaperLayout(Dimension gridSize) {
        this(gridSize, 0, 0);
    }

    public GraphPaperLayout(Dimension gridSize, int hgap, int vgap) {
        if ((gridSize.width <= 0) || (gridSize.height <= 0)) {
            throw new IllegalArgumentException(
                "dimensions must be greater than zero");
        }
        this.gridSize = new Dimension(gridSize);
        this.hgap = hgap;
        this.vgap = vgap;
        compTable = new Hashtable<Component, Rectangle>();
    }

    public Dimension getGridSize() {
        return new Dimension( gridSize );
    }

    public void setGridSize( Dimension d ) {
        setGridSize( d.width, d.height );
    }

    public void setGridSize( int width, int height ) {
        gridSize = new Dimension( width, height );
    }

    public void setConstraints(Component comp, Rectangle constraints) {
        compTable.put(comp, new Rectangle(constraints));
    }


    public void addLayoutComponent(String name, Component comp) {
    }

    public void removeLayoutComponent(Component comp) {
        compTable.remove(comp);
    }

    public Dimension preferredLayoutSize(Container parent) {
        return getLayoutSize(parent, true);
    }

    public Dimension minimumLayoutSize(Container parent) {
        return getLayoutSize(parent, false);
    }

    protected Dimension getLayoutSize(Container parent, boolean isPreferred) {
        Dimension largestSize = getLargestCellSize(parent, isPreferred);
        Insets insets = parent.getInsets();
        largestSize.width = ( largestSize.width * gridSize.width ) +
            ( hgap * ( gridSize.width + 1 ) ) + insets.left + insets.right;
        largestSize.height = ( largestSize.height * gridSize.height ) +
            ( vgap * ( gridSize.height + 1 ) ) + insets.top + insets.bottom;
        return largestSize;
    }

    protected Dimension getLargestCellSize(Container parent,
                                           boolean isPreferred) {
        int ncomponents = parent.getComponentCount();
        Dimension maxCellSize = new Dimension(0,0);
        for ( int i = 0; i < ncomponents; i++ ) {
            Component c = parent.getComponent(i);
            Rectangle rect = compTable.get(c);
            if ( c != null && rect != null ) {
                Dimension componentSize;
                if ( isPreferred ) {
                    componentSize = c.getPreferredSize();
                } else {
                    componentSize = c.getMinimumSize();
                }
                maxCellSize.width = Math.max(maxCellSize.width,
                    componentSize.width / rect.width);
                maxCellSize.height = Math.max(maxCellSize.height,
                    componentSize.height / rect.height);
            }
        }
        return maxCellSize;
    }

    public void layoutContainer(Container parent) {
        synchronized (parent.getTreeLock()) {
            Insets insets = parent.getInsets();
            int ncomponents = parent.getComponentCount();

            if (ncomponents == 0) {
                return;
            }

            // Total parent dimensions
            Dimension size = parent.getSize();
            int totalW = size.width - (insets.left + insets.right);
            int totalH = size.height - (insets.top + insets.bottom);

            // Cell dimensions, including padding
            int totalCellW = totalW / gridSize.width;
            int totalCellH = totalH / gridSize.height;

            // Cell dimensions, without padding
            int cellW = (totalW - ( (gridSize.width + 1) * hgap) )
                    / gridSize.width;
            int cellH = (totalH - ( (gridSize.height + 1) * vgap) )
                    / gridSize.height;

            for ( int i = 0; i < ncomponents; i++ ) {
                Component c = parent.getComponent(i);
                Rectangle rect = compTable.get(c);
                if ( rect != null ) {
                    int x = insets.left + ( totalCellW * rect.x ) + hgap;
                    int y = insets.top + ( totalCellH * rect.y ) + vgap;
                    int w = ( cellW * rect.width ) - hgap;
                    int h = ( cellH * rect.height ) - vgap;
                    c.setBounds(x, y, w, h);
                }
            }
        }
    }

    public void addLayoutComponent(Component comp, Object constraints) {
        if (constraints instanceof Rectangle) {
            Rectangle rect = (Rectangle)constraints;
            if ( rect.width <= 0 || rect.height <= 0 ) {
                throw new IllegalArgumentException(
                    "cannot add to layout: rectangle must have positive width and height");
            }
            if ( rect.x < 0 || rect.y < 0 ) {
                throw new IllegalArgumentException(
                    "cannot add to layout: rectangle x and y must be >= 0");
            }
            setConstraints(comp, rect);
        } else if (constraints != null) {
            throw new IllegalArgumentException(
                "cannot add to layout: constraint must be a Rectangle");
        }
    }

    public Dimension maximumLayoutSize(Container target) {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    public float getLayoutAlignmentX(Container target) {
        return 0.5f;
    }

    public float getLayoutAlignmentY(Container target) {
        return 0.5f;
    }

    public void invalidateLayout(Container target) {
    }
}