package kaleidoscope;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
/**
 * The View "observes" and displays what is going on in the Model.
 * In this example, the Model contains only a single bouncing ball.
 * 
 * @author David Matuszek
 * @author James Park
 * @author Your name goes here
 */
public class View extends JPanel implements Observer {
    
    /** This is what we will be observing. */
    Circle circle;
    Square square;
    Triangle triangle;
    private Polygon poly;
    private Color circleColor = Color.red;
    private Color squareColor = Color.green;
    private Color triangleColor = Color.yellow;
    /**
     * Constructor.
     * @param circle The Model whose working is to be displayed.
     */
    View(Circle circle, Square square, Triangle triangle) {
        this.circle = circle;
        this.square = square;
        this.triangle = triangle;
    }

    /**
     * Displays what is going on in the Model. Note: This method should
     * NEVER be called directly; call repaint() instead.
     * 
     * @param g The Graphics on which to paint things.
     * @see javax.swing.JComponent#paint(java.awt.Graphics)
     */
    @Override
    public void paint(Graphics g) {    	
    	g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(this.circleColor);         
        List<List<Integer>> list = circle.buildShapes(circle.getX(), circle.getY(), getWidth(), getHeight());
        for (int i = 0; i < list.size(); i++) {
    		g.fillOval(list.get(i).get(0), list.get(i).get(1),
                   circle.BALL_SIZE, circle.BALL_SIZE);           
    	}        
        g.setColor(this.squareColor);
        list = square.buildShapes(square.getX(), square.getY(), getWidth(), getHeight());
        for (int i = 0; i < list.size(); i++) {
    		g.fillRect(list.get(i).get(0), list.get(i).get(1),
    				square.SHAPE_SIZE, square.SHAPE_SIZE);           
    	}
        
        g.setColor(this.triangleColor);
        list = triangle.buildShapes(triangle.getX(), triangle.getY(), getWidth(), getHeight());
        for (int i = 0; i < list.size(); i++) {
        	int [] Xcoord = {list.get(i).get(0), list.get(i).get(0) + 20, list.get(i).get(0) + 20};
            int [] Ycoord = {list.get(i).get(1), list.get(i).get(1) - 20, list.get(i).get(1) + 20};
        	poly = new Polygon(Xcoord, Ycoord, 3);
        	g.drawPolygon(poly);
        	g.fillPolygon(poly);
    	}
    }
     
    /**
     * When an Observer notifies Observers (and this View is an Observer),
     * this is the method that gets called.
     * 
     * @param obs Holds a reference to the object being observed.
     * @param arg If notifyObservers is given a parameter, it is received here.
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(Observable obs, Object arg) {
        repaint();
    }
    
    public Color getCircleColor(){
    	return circleColor;
    }
    
    public Color getSquareColor(){
    	return squareColor;
    }
    
    public Color getTriangleColor(){
    	return triangleColor;
    }
    
    public void setCircleColor(Color circleColor){
    	this.circleColor = circleColor;
    	repaint();
    }    
    
    public void setSquareColor(Color squareColor){
    	this.squareColor = squareColor;
    	repaint();
    }
    
    public void setTriangleColor(Color triangleColor){
    	this.triangleColor = triangleColor;
    	repaint();
    }
}
