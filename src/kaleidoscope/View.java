package kaleidoscope;

import java.awt.Color;
import java.awt.Graphics;
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
    Model model;

    /**
     * Constructor.
     * @param model The Model whose working is to be displayed.
     */
    View(Model model) {
        this.model = model;
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
        g.setColor(Color.red);
       // g.fillOval(model.getX(), model.getY(),
         //      model.BALL_SIZE, model.BALL_SIZE);
        
        List<List<Integer>> list = model.buildBalls(model.getX(), model.getY(), getWidth(), getHeight());
        for (int i = 0; i < list.size(); i++) {
    		g.fillOval(list.get(i).get(0), list.get(i).get(1),
                   model.BALL_SIZE, model.BALL_SIZE);           
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
}