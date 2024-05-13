import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Canvas extends JPanel{
    private final static int STROKE_SIZE = 8;

    // holds all the paths created in the canvas
    private List<List<ColorPoint>> allPaths;

    // used to draw a line between points
    private List<ColorPoint> currentPath;

    // color of the dots
    private Color color;

    // canvas width and height
    private int canvasWidth, canvasHeight;

    // location of the dots
    private int x,y; 

    public Canvas(int targetWidth, int targetHeight){
        super();
        setPreferredSize(new Dimension(targetWidth, targetHeight)); // set the size of the canvas
        setOpaque(true); // make the canvas opaque
        setBackground(Color.WHITE); // set the background color of the canvas
        setBorder(BorderFactory.createLineBorder(Color.BLACK)); // set the border of the canvas

        // init vars
        allPaths = new ArrayList<>(25); // create a new list of paths
        canvasWidth = targetWidth; // set the canvas width
        canvasHeight = targetHeight; // set the canvas height


        MouseAdapter ma = new MouseAdapter() {
            @Override   
            public void mousePressed(MouseEvent e){
                // get current mouse location

                x = e.getX(); // get the x coordinate of the mouse
                y = e.getY(); // get the y coordinate of the mouse

                // draw in current mouse location
                Graphics g = getGraphics(); // get the graphics object of the canvas
                g.setColor(color); // set the color of the graphics object
                g.fillRect(x, y, STROKE_SIZE, STROKE_SIZE); // draw a rectangle at the mouse location
                g.dispose(); // dispose of the graphics object

                // start current path
                currentPath = new ArrayList<>(25); // create a new list of color points
                currentPath.add(new ColorPoint(x, y, color)); // add the current point to the list
            }

            @Override
            public void mouseReleased(MouseEvent e){
                // add current path to all paths
                allPaths.add(currentPath); // add the current path to the list of paths

                // reset the current path
                currentPath = null;
        }

            @Override
            public void mouseDragged(MouseEvent e){
                // get current location
                x = e.getX(); // get the x coordinate of the mouse
                y = e.getY(); // get the y coordinate of the mouse

                // used to be able to draw a line
                Graphics2D g2d = (Graphics2D) getGraphics(); // get the graphics object of the canvas
                g2d.setColor(color); // set the color of the graphics object
                if(!currentPath.isEmpty()){ // if the current path is not empty
                    ColorPoint prevPoint = currentPath.get(currentPath.size() - 1); // get the first point in the list
                    g2d.setStroke(new BasicStroke(STROKE_SIZE)); // set the stroke size of the graphics object

                    // connect the current point to the previous point to draw a line
                    g2d.drawLine(prevPoint.getX(), prevPoint.getY(), x, y); // draw a line between the two points

            }
            
            g2d.dispose(); // dispose of the graphics object

            // add the new point to the path
            ColorPoint nextPoint = new ColorPoint(e.getX(), e.getY(), color);
            currentPath.add(nextPoint); // add the new point to the list
            
        }
    };

    addMouseListener(ma);
    addMouseMotionListener(ma); // add the mouse listener to the canvas
}

    public void setColor(Color color){
        this.color = color; // set the color of the canvas
}

    public void resetCanvas(){
        // clear all rectangles
        Graphics g = getGraphics(); // get the graphics object of the canvas
        g.clearRect(0, 0, canvasWidth, canvasHeight);
        g.dispose(); // dispose of the graphics object

        // reset the path
        allPaths = new ArrayList<>(25); // create a new list of paths
        currentPath = null; // set the current path to null

        repaint(); // repaint the canvas
        revalidate(); // revalidate the canvas
}

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g); // call the super method
        Graphics2D g2d = (Graphics2D) g; // get the graphics object of the canvas

        // redraws all of the paths created so far
        for(List<ColorPoint> path : allPaths){ // for each path in the list of paths}
            ColorPoint from = null; // create a new color point
            for(ColorPoint point : path){ // for each point in the path}
                g2d.setColor(point.getColor()); // set the color of the graphics object

                // edge case when a single dot is created
                if(path.size() == 1){
                    g2d.fillRect(point.getX(), point.getY(), STROKE_SIZE, STROKE_SIZE);
                }
                if (from != null){ // if the point is not null
                g2d.setStroke(new BasicStroke(STROKE_SIZE)); // set the stroke size of the graphics object
                g2d.drawLine(from.getX(), from.getY(), point.getX(), point.getY()); // draw a line between the two points
        }
        from = point; // set the current point to the previous point
    }
    
}
    }





}
