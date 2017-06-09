package test.saurabh;

import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
    /*
    * 
    */
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
    
    public void testSetStyle(TextStyle style){
        this.label.setStyle(style);
    }
    
    public void testGetStyle() {
        TextStyle style = this.label.getStyle();
        System.out.println("Fontsize - " + String.valueOf(style.getFontSize()));
        System.out.println("Is font Bold - " + String.valueOf(style.isBold()));
        System.out.println("Is font italic - " + String.valueOf(style.isItalic()));
    }
        
    public void testSetAngle(float angle){
        this.label.setAngle(angle);
    }
    
    public void testGeAngle() {
        System.out.println("Angle - " + String.valueOf(this.label.getAngle()));
    }
    
    public void testSetLevel(Level level){
        this.label.setLevel(level);
    }
    
    public void testGetLevel() {
        Level level = this.label.getLevel();
        System.out.println("Level name - " + level.getName());
        System.out.println("Elevation - " + String.valueOf(level.getElevation()));
        System.out.println("Floorthickness - " + String.valueOf(level.getFloorThickness()));
        System.out.println("Height - " + String.valueOf(level.getHeight()));
    }
        
    public void testIsAtLevel(Level l) {
        System.out.println("Is at Level - " + String.valueOf(this.label.isAtLevel(l)));
    }
       
    public void testGetPoints() {
        float [][] points = this.label.getPoints();
        System.out.println("Points - " + Arrays.deepToString(points));
    }
    
    public void testAddPropertyChangeListener() {}
    
    public void testRemovePropertyChangeListener() {}
    
    
    
    public void testIntersectsRectangle(float a, float b, float c, float d){
        System.out.println("Intersects Rectanle - " + String.valueOf(this.label.intersectsRectangle(a,b,c,d)));
    }
    
    public void testContainsPoint(float x, float y, float m){        
        System.out.println("Contains point - " + String.valueOf(this.label.containsPoint(x,y,m)));
    }
    
    public void testMove(float a, float b){
        this.label.move(a,b);
    }
    
    public void testClone(){
        Label clone = this.label.clone();
        System.out.println("Cloned label - " + clone.getText());
    }
   
    public void testReadObject(){}
    
    
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
        tester.testGetPoints();
        TextStyle style = new TextStyle(13.0f, true, true);
        tester.testSetStyle(style);
        tester.testSetAngle(12);
        Level level = new Level("level-1", 25, 10, 100);
        tester.testSetLevel(level);
        
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
        tester.testIntersectsRectangle
	}

}
