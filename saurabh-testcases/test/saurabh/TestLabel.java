package test.saurabh;

import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import com.eteks.sweethome3d.model.Label;

/**
 * test some properties and functionalities of Label class
 * @author Saurabh
 *
 */
public class TestLabel {
    
    private Label label;
	
	/**
	 * construct a tester for Label
	 * @param text
     * @param x
     * @param y
	 */
	public TestLabel(String text, float x, float y) {
		label = new Label(text, x, y);
	}
    
    public void testSetText(String text){
        this.label.setText(text);
    }
    
    public void testGetText() {
        System.out.println("Text - " + this.label.getText());
    }
    
    public void testSetX(float X){
        this.label.setX(X);
    }
    
    public void testGetX() {
        System.out.println("X - " + String.valueOf(this.label.getX()));
    }
    
    public void testSetY(float Y){
        this.label.setY(Y);
    }
    
    public void testGetY() {
        System.out.println("Y - " + String.valueOf(this.label.getY()));
    }
    
    public void testSetStyle(String text){}
    
    public void testGetStyle() {}
        
    public void testSetAngle(String text){}
    
    public void testGeAngle() {}
    
    public void testSetLevel(String text){}
    
    public void testGetLevel() {}
        
    public void testIsAtLevel() {}
       
    public void testGetPoints() {}
    
    public void testContainsPoint(String text){}
    
    public void testMove(String text){}
    
    public void testClone(String text){}
   
    
    /**
     *  test readObject method of Label
     * @param in
     */
    public void testAddPropertyChangeListener(PropertyChangeListener listener){
        this.label.addPropertyChangeListener(listener);
    }
    

	/**
	 * run the test cases
	 * @param args
	 */
	public static void main(String args[]){
		System.out.println("******Running Label test case******");
        System.out.println("\nTesting the label constructor .....");
        TestLabel tester = new TestLabel("first-text", 10, 20);
        tester.testGetText();
        tester.testGetX();
        tester.testGetY();
        System.out.println("\nTesting Setters.....");
        tester.testSetText("second-text");
        tester.testSetX(40);
        tester.testSetY(70);
        System.out.println("\nTesting Getters.....");
        tester.testGetText();
        tester.testGetX();
        tester.testGetY();
        System.out.println("\nTesting othe methods.....");
	}

}
