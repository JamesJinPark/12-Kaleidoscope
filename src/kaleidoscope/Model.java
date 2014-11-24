package kaleidoscope;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This is the Model class for a bouncing ball. It is an Observable,
 * which means that it can notifyObservers that something in the
 * model has changed, and they should take appropriate actions.
 * 
 * @author David Matuszek
 * @author James Park
 * @author <Your name goes here>
 */
public class Model extends Observable {
    public final int BALL_SIZE = 20;
    private int xPosition = 0;
    private int yPosition = 0;
    private int xLimit, yLimit;
    private int xDelta = 8;
    private int yDelta = 8;
    private Timer timer;

    /**
     * Sets the "walls" that the ball should bounce off from.
     * 
     * @param xLimit The position (in pixels) of the wall on the right.
     * @param yLimit The position (in pixels) of the floor.
     */
    public void setLimits(int xLimit, int yLimit) {
        this.xLimit = xLimit - BALL_SIZE;
        this.yLimit = yLimit - BALL_SIZE;
        xPosition = Math.min(xPosition, xLimit);
        yPosition = Math.min(yPosition, yLimit);
    }

    /**
     * @return The balls X position.
     */
    public int getX() {
        return xPosition;
    }

    /**
     * @return The balls Y position.
     */
    public int getY() {
        return yPosition;
    }
    
    /**
     * Tells the ball to start moving. This is done by starting a Timer
     * that periodically executes a TimerTask. The TimerTask then tells
     * the ball to make one "step."
     */
    public void start() {
        timer = new Timer(true);
        timer.schedule(new Strobe(), 0, 40); // 25 times a second        
    }
    
    /**
     * Tells the ball to stop where it is.
     */
    public void pause() {
        timer.cancel();
    }
    
    /**
     * Tells the ball to advance one step in the direction that it is moving.
     * If it hits a wall, its direction of movement changes.
     */
    public void makeOneStep() {
        // Do the work
        xPosition += xDelta;
        if (xPosition < 0 || xPosition >= xLimit) {
            xDelta = -xDelta;
            xPosition += xDelta;
        }
        yPosition += yDelta;
        if (yPosition < 0 || yPosition >= yLimit) {
            yDelta = -yDelta;
            yPosition += yDelta;
        }
        // Notify observers
        setChanged();
        notifyObservers();
    }
    
    /**
     * Tells the model to advance one "step."
     */
    private class Strobe extends TimerTask {
        @Override
        public void run() {
            makeOneStep();
        }
    }
    
    public List<List<Integer>> buildBalls(int x, int y, int width, int height) {
    	List<List<Integer>> list = new ArrayList<List<Integer>>();
    	list.add(ball1(x,y));
    	list.add(ball2(x,y, width, height));
    	list.add(ball3(x,y, width, height));
    	list.add(ball4(x,y, width, height));
    	list.add(ball5(x,y, width, height));
    	list.add(ball6(x,y, width, height));
    	list.add(ball7(x,y, width, height));
    	list.add(ball8(x,y, width, height));
     	
    	return list; 
    }
    
    public ArrayList<Integer> ball1(int x, int y) {
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	list.add(x);
    	list.add(y);
    	return list;	
    }
    
    public ArrayList<Integer> ball2(int x, int y, int width, int height) {
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

    public ArrayList<Integer> ball3(int x, int y, int width, int height) {
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

    public ArrayList<Integer> ball4(int x, int y, int width, int height) {
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
    
    public ArrayList<Integer> ball5(int x, int y, int width, int height) {
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
    
    public ArrayList<Integer> ball6(int x, int y, int width, int height) {
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
    
    public ArrayList<Integer> ball7(int x, int y, int width, int height) {
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
    
    public ArrayList<Integer> ball8(int x, int y, int width, int height) {
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