package test.saurabh;

import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;

import com.eteks.sweethome3d.model.Label;
import com.eteks.sweethome3d.model.TextStyle;
import com.eteks.sweethome3d.model.Level;

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
    /**
     *  test settext method of Label
     * @param
     */
    public void testSetText(String text){
        this.label.setText(text);
    }
    
    /**
     *  test gettext  method of Label
     * @param
     */
    public void testGetText() {
        System.out.println("Text - " + this.label.getText());
    }
    
    /**
     *  test setx method of Label
     * @param
     */
    public void testSetX(float X){
        this.label.setX(X);
    }
    
    /**
     *  test getx method of Label
     * @param
     */
    public void testGetX() {
        System.out.println("X - " + String.valueOf(this.label.getX()));
    }
    
    /**
     *  test sety method of Label
     * @param
     */
    public void testSetY(float Y){
        this.label.setY(Y);
    }
    
    /**
     *  test gety method of Label
     * @param
     */
    public void testGetY() {
        System.out.println("Y - " + String.valueOf(this.label.getY()));
    }
    
    /**
     *  test setStyle method of Label
     * @param
     */
    public void testSetStyle(TextStyle style){
        this.label.setStyle(style);
    }
    
    /**
     *  test getstyle method of Label
     * @param
     */    
    public void testGetStyle() {
        TextStyle style = this.label.getStyle();
        System.out.println("Fontsize - " + String.valueOf(style.getFontSize()));
        System.out.println("Is font Bold - " + String.valueOf(style.isBold()));
        System.out.println("Is font italic - " + String.valueOf(style.isItalic()));
    }
        
     /**
     *  test setangle  method of Label
     * @param
     */
    public void testSetAngle(float angle){
        this.label.setAngle(angle);
    }
    
    /**
     *  test getanlge method of Label
     * @param
     */
    public void testGeAngle() {
        System.out.println("Angle - " + String.valueOf(this.label.getAngle()));
    }
    
    /**
     *  test setlevel method of Label
     * @param
     */
    public void testSetLevel(Level level){
        this.label.setLevel(level);
    }
    
    /**
     *  test getlevel method of Label
     * @param
     */
    public void testGetLevel() {
        Level level = this.label.getLevel();
        System.out.println("Level name - " + level.getName());
        System.out.println("Elevation - " + String.valueOf(level.getElevation()));
        System.out.println("Floorthickness - " + String.valueOf(level.getFloorThickness()));
        System.out.println("Height - " + String.valueOf(level.getHeight()));
    }
      
    /**
     *  test isatlevel method of Label
     * @param
     */      
    public void testIsAtLevel(Level l) {
        System.out.println("Is at Level - " + String.valueOf(this.label.isAtLevel(l)));
    }
     
    /**
     *  test getpoint method of Label
     * @param
     */
    public void testGetPoints() {
        float [][] points = this.label.getPoints();
        System.out.println("Points - " + Arrays.deepToString(points));
    }
    
    /**
     *  test addPropertyChangeListener method of Label
     * @param
     */
    public void testAddPropertyChangeListener(PropertyChangeListener l) {
        this.label.addPropertyChangeListener(l);
    }
    
    /**
     *  test removePropertyChangeListener method of Label
     * @param
     */
    public void testRemovePropertyChangeListener(PropertyChangeListener l) {
        this.label.removePropertyChangeListener(l);
    }
    
    
    /**
     *  test intersectsRectangle method of Label
     * @param
     */
    public void testIntersectsRectangle(float a, float b, float c, float d){
        System.out.println("Intersects Rectanle - " + String.valueOf(this.label.intersectsRectangle(a,b,c,d)));
    }
    
    /**
     *  test containsPoint method of Label
     * @param
     */
    public void testContainsPoint(float x, float y, float m){        
        System.out.println("Contains point - " + String.valueOf(this.label.containsPoint(x,y,m)));
    }
    
    /**
     *  test move method of Label
     * @param
     */
    public void testMove(float a, float b){
        this.label.move(a,b);
    }
     /**
     *  test clone method of Label
     * @param
     */
    public void testClone(){
        Label clone = this.label.clone();
        System.out.println("Cloned label - " + clone.getText());
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
        tester.testGetPoints();
        TextStyle style = new TextStyle(13.0f, true, true);
        tester.testSetStyle(style);
        tester.testSetAngle(12);
        Level level = new Level("level-1", 25, 10, 100);
        tester.testSetLevel(level);
        
        PropertyChangeListener activeWindowListener = new PropertyChangeListener() {
          public void propertyChange(PropertyChangeEvent ev) {
            System.out.println("No window should be displayed for short task");
          }
        };
        tester.testAddPropertyChangeListener(activeWindowListener);
        tester.testRemovePropertyChangeListener(activeWindowListener);
        
        System.out.println("\nTesting Getters.....");
        tester.testGetText();
        tester.testGetX();
        tester.testGetY();
        tester.testGetStyle();
        tester.testGeAngle();
        tester.testGetLevel();
        tester.testIsAtLevel(level);
        tester.testGetPoints();        
        
        System.out.println("\nTesting othe methods.....");
        tester.testIntersectsRectangle(10,10,10,10);
        tester.testContainsPoint(10,10,10);
        tester.testMove(20,30);
        tester.testClone();
	}

}
