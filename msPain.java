import ecs100.*; // import ecs100 library
import java.awt.Color; // import colours
import javax.swing.JColorChooser; // import colour chooser
/**
 *  Creates a clone of MS Paint
 *
 * @author Creed
 * @version 1.0
 * 18.3.24
 */
public class msPain
{
    // instance variables
    private Color currentColour = Color.black; // current colour start at black
    private double startX, startY; // fields to remember pressed position
    private String shape;  // what shape is drawn when clicked
    String action; // what action is currently being done
    String text; // what text the user wants to enter
    private double width; // size of the shape
    Boolean isWriting; // whether the user is writing or not
    /**
     * Constructor for objects of class msPain
     */
    public msPain()
    {
        // initialise instance variables
        shape = "circle";
        isWriting = false;
        width = 50.0;
        UI.initialise(); // bring up GUI interface
        UI.setMouseListener(this:: doMouse);
        UI.addButton("Choose colour", this::doChooseColour);
        UI.addButton("Choose shape", this::setShape);
        UI.addButton("Clear", UI::clearGraphics);
        UI.addButton("Quit", UI::quit); // creates buttons
        UI.addTextField("text", this::writeText); // adds a text field
        UI.addSlider("Line width", 10.0, 100.0, 10.0, this::setSize);
        UI.addSlider("Shape size", 10.0, 100.0, 50.0, this::shapeWidth);
        UI.addSlider("Text size", 10.0, 100.0, 10.0, this::textSize); // creates sliders
    }

    /**
     * Set the width of a line
     */
    public void setSize(double sliderPosition){
        UI.setLineWidth(sliderPosition);
        isWriting = false;
    }
    
    /**\
     * Set the size of a shape
     */
    public void shapeWidth(double sliderPosition){
        width = sliderPosition;
    }
    
    /**
     * set text size
     */
    public void textSize(double sliderPosition){
        UI.setFontSize(sliderPosition);
    }
    
    /**
     * Allows the user to place text on the screen
     */
    public void writeText(String text){
        this.text = text;
        isWriting = true;
    }
    
    /**
     * Creates a colour pane for the user to select a colour
     */
    public void doChooseColour(){
        this.currentColour = JColorChooser.showDialog(null, "Choose colour", this.currentColour);
        UI.setColor(this.currentColour);
        isWriting = false;
    }
    
    /**
     * Call back method for mouse
     * Draws a line
     */
    public void doMouse(String action, double x, double y)
    {
        // checks if the mouse is being pressed or released
        if (action.equals("pressed")){
            // store pressed position
            this.startX = x;
            this.startY = y;
        }
        else if (action.equals("released") && this.startX != x && this.startY != y){
            // draw line when mouse is released
            UI.drawLine(this.startX, this.startY, x, y);
        } 
        else if(action.equals("clicked")){
            if (isWriting == true){
                UI.drawString(text, x ,y); // checks if the user is writing or not
        }   
            else if  (isWriting == false){  // draws an oval when mouse is clicked
                if (shape == "circle"){
                    // draws an oval when mouse is clicked
                    UI.fillOval(x - width/2, y - width/2, width, width);
                }
                else if (shape == "square") {
                    // draws a square when mouse is clicked
                    UI.fillRect(x -width/2, y -width/2, width, width);
            }
        }
    }
        
}
    
    /**
     * Changes from circle to square and vice versa
     */
    public void setShape(){ 
    if (shape == "circle"){
     shape = "square"; // changes from circle to square
        }
    
    else if (shape == "square"){
        shape = "circle"; // changes from square to circle
    }
    isWriting = false;
}
}
