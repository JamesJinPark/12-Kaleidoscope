package kaleidoscope;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This is the Square class for a bouncing shape. It is an Observable,
 * which means that it can notifyObservers that something in the
 * Square has changed, and they should take appropriate actions.
 * 
 * @author David Matuszek
 * @author James Park
 * @author <Your name goes here>
 */
public class Square extends Observable {
    public final int SHAPE_SIZE = 25;
    private int xPosition = 0;
    private int yPosition = 0;
    private int xLimit, yLimit;
    private int xDelta = 10;
    private int yDelta = 10;
    private Timer timer;

    
    public void changeDelta(){
    	this.xDelta = xDelta - 1;
    	this.yDelta = yDelta - 1;
    }
    
    /**
     * Sets the "walls" that the shape should bounce off from.
     * 
     * @param xLimit The position (in pixels) of the wall on the right.
     * @param yLimit The position (in pixels) of the floor.
     */
    public void setLimits(int xLimit, int yLimit) {
        this.xLimit = xLimit - SHAPE_SIZE;
        this.yLimit = yLimit - SHAPE_SIZE;
        xPosition = Math.min(xPosition, xLimit);
        yPosition = Math.min(yPosition, yLimit);
    }

    /**
     * @return The shapes X position.
     */
    public int getX() {
        return xPosition;
    }

    /**
     * @return The shapes Y position.
     */
    public int getY() {
        return yPosition;
    }
    
    /**
     * Tells the shape to start moving. This is done by starting a Timer
     * that periodically executes a TimerTask. The TimerTask then tells
     * the shape to make one "step."
     */
    public void start() {
        timer = new Timer(true);
        timer.schedule(new Strobe(), 0, 40); // 25 times a second        
    }
    
    /**
     * Tells the shape to stop where it is.
     */
    public void pause() {
        timer.cancel();
    }
    
    /**
     * Tells the shape to advance one step in the direction that it is moving.
     * If it hits a wall, its direction of movement changes.
     */
    public void makeOneStep() {
        // Do the work
        xPosition -= xDelta;
        if (xPosition < 0 || xPosition >= xLimit) {
            xDelta = -xDelta;
            xPosition -= xDelta;
        }
        yPosition -= yDelta;
        if (yPosition < 0 || yPosition >= yLimit) {
            yDelta = -yDelta;
            yPosition -= yDelta;
        }
        // Notify observers
        setChanged();
        notifyObservers();
    }
    
    /**
     * Tells the RandomShape to advance one "step."
     */
    private class Strobe extends TimerTask {
        @Override
        public void run() {
            makeOneStep();
        }
    }
    
    public List<List<Integer>> buildShapes(int x, int y, int width, int height) {
    	List<List<Integer>> list = new ArrayList<List<Integer>>();
    	list.add(shape1(x,y));
    	list.add(shape2(x,y, width, height));
    	list.add(shape3(x,y, width, height));
    	list.add(shape4(x,y, width, height));
    	list.add(shape5(x,y, width, height));
    	list.add(shape6(x,y, width, height));
    	list.add(shape7(x,y, width, height));
    	list.add(shape8(x,y, width, height));
    	return list; 
    }
    
    public ArrayList<Integer> shape1(int x, int y) {
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	list.add(x);
    	list.add(y);
    	return list;	
    }
    
    public ArrayList<Integer> shape2(int x, int y, int width, int height) {
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	x = x - width/2;
    	y = -y + height/2;
    	
    	x = -x;
    	y = -y;
    	
    	x = x + width/2;
    	y = -y + height/2;

    	list.add(x);
    	list.add(y);
    	return list;	
    }

    public ArrayList<Integer> shape3(int x, int y, int width, int height) {
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	x = x - width/2;
    	y = -y + height/2;
    	
    	x = -x;
    	y = y;
    	
    	x = x + width/2;
    	y = -y + height/2;

    	list.add(x);
    	list.add(y);
    	return list;	
    }

    public ArrayList<Integer> shape4(int x, int y, int width, int height) {
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	x = x - width/2;
    	y = -y + height/2;
    	
    	x = x;
    	y = -y;
    	
    	x = x + width/2;
    	y = -y + height/2;

    	list.add(x);
    	list.add(y);
    	return list;	
    }    
    
    public ArrayList<Integer> shape5(int x, int y, int width, int height) {
    	int i = x;
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	x = x - width/2;
    	y = -y + height/2;
    	i = x;
    	x = y;
    	y = i;
    	
    	x = x + width/2;
    	y = -y + height/2;

    	list.add(x);
    	list.add(y);
    	return list;	
    }
    
    public ArrayList<Integer> shape6(int x, int y, int width, int height) {
    	int i = x;
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	x = x - width/2;
    	y = -y + height/2;
    	i = x;
    	x = y;
    	y = -i;
    	
    	x = x + width/2;
    	y = -y + height/2;

    	list.add(x);
    	list.add(y);
    	return list;	
    }
    
    public ArrayList<Integer> shape7(int x, int y, int width, int height) {
    	int i = x;
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	x = x - width/2;
    	y = -y + height/2;
    	i = x;
    	x = -y;
    	y = i;
    	
    	x = x + width/2;
    	y = -y + height/2;

    	list.add(x);
    	list.add(y);
    	return list;	
    }
    
    public ArrayList<Integer> shape8(int x, int y, int width, int height) {
    	int i = x;
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	x = x - width/2;
    	y = -y + height/2;
    	i = x;
    	x = -y;
    	y = -i;
    	
    	x = x + width/2;
    	y = -y + height/2;

    	list.add(x);
    	list.add(y);
    	return list;	
    }

}