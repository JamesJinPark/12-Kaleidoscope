package kaleidoscope;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The Controller sets up the GUI and handles all the controls (buttons,
 * menu items, etc.)
 * 
 * @author David Matuszek
 * @author James Park
 * @author <Your name goes here>
 */
public class Controller extends JFrame {
    JPanel buttonPanel = new JPanel();
    JButton runButton = new JButton("Run");
    JButton stopButton = new JButton("Stop");
    JButton colorButton = new JButton("Change Color");
    JButton reflectButton = new JButton("Change Reflection");
    Timer timer;

    /** The Model is the object that does all the computations. It is
     * completely independent of the Controller and View objects. */
    Circle circle;
    /** The View object displays what is happening in the Model. */
    View view;
    Square square;
    Triangle triangle;
    
    /**
     * Runs the kaleidoscope program
     * @param args Ignored.
     */
    public static void main(String[] args) {
        Controller c = new Controller();
        c.init();
        c.display();
    }

    /**
     * Sets up communication between the components.
     */
    private void init() {
        circle = new Circle();     // The model is independent from the other classes
        square = new Square();
        triangle = new Triangle();
        view = new View(circle, square, triangle);  // The view needs to know what model to look at
        circle.addObserver(view); // The model needs to give permission to be observed
        square.addObserver(view);
        triangle.addObserver(view);        
    }

    /**
     * Displays the GUI.
     */
    private void display() {
        layOutComponents();
        attachListenersToComponents();
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * Arranges the various components in the GUI.
     */
    private void layOutComponents() {
        setLayout(new BorderLayout());
        this.add(BorderLayout.SOUTH, buttonPanel);
        buttonPanel.add(runButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(colorButton);
        buttonPanel.add(reflectButton);
        stopButton.setEnabled(false);
        this.add(BorderLayout.CENTER, view);
    }
    
    /**
     * Attaches listeners to the components, and schedules a Timer.
     */
    private void attachListenersToComponents() {
        // The Run button tells the Model to start
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                runButton.setEnabled(false);
                stopButton.setEnabled(true);
                circle.start();
                square.start();
                triangle.start();
            }
        });
        // The Stop button tells the Model to pause
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                runButton.setEnabled(true);
                stopButton.setEnabled(false);
                circle.pause();
                square.pause();
                triangle.pause();
            }
        });
        
        // The Color button tells the Model to change color
        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	colorButton.setEnabled(true); 
            	if (view.getCircleColor() == Color.red) {
            		view.setCircleColor(Color.blue);
            	}
            	else {
            		view.setCircleColor(Color.red);
            	}
            	if (view.getSquareColor() == Color.green){
            		view.setSquareColor(Color.cyan);
            	}
            	else{
            		view.setSquareColor(Color.green);
            	}
            	if (view.getTriangleColor() == Color.yellow){
            		view.setTriangleColor(Color.magenta);
            	}
            	else{
            		view.setTriangleColor(Color.yellow);
            	}
            }
        });
        
        //The fast button and slow button controls speed
        reflectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	reflectButton.setEnabled(true); 
            	circle.changeDelta();
            	square.changeDelta();
            	triangle.changeDelta();
            }
        });
            
        // When the window is resized, the Model is given the new limits
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent arg0) {
                circle.setLimits(view.getWidth(), view.getHeight());
                square.setLimits(view.getWidth(), view.getHeight());
                triangle.setLimits(view.getWidth(), view.getHeight());
            }
        });
    }
}