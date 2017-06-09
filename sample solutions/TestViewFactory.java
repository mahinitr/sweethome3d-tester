package test.bottomup09;

import java.util.concurrent.Callable;

import javax.swing.JLabel;

import test.MockJunit;
import test.MyTestUtilities;
import mock.DefaultUserPreferences;
import mock.SwingViewFactory;
import mock.ThreadedTaskController;
import mock.ThreadedTaskPanel;
import mock.UserPreferences;
import mock.ViewFactory;

public class TestViewFactory {

	private ViewFactory viewFactory;
	private Callable<Void> longTask;
	private ThreadedTaskController.ExceptionHandler noExceptionHandler;
	private UserPreferences preferences;
	private ThreadedTaskController ttc;
	public TestViewFactory() {
		viewFactory=new SwingViewFactory();
		longTask = new Callable<Void>() {
	        public Void call() throws Exception {
	          Thread.sleep(2000);
	          return null;
	        }
	      };
	      noExceptionHandler=new ThreadedTaskController.ExceptionHandler() {
	          public void handleException(Exception ex) {
	            MockJunit.fail("Exception handler shouldn't be called");
	          }
	        };
        preferences=new DefaultUserPreferences();
        ttc=new ThreadedTaskController(longTask, "a long task Message", noExceptionHandler, preferences, viewFactory);
	}
	/**
	 * 
	 * @param message - the label text of the created panel
	 * @param expected - the expected label text of the created panel
	 */
	public void testCreateThreadedTaskView(String message,String expected){
		ThreadedTaskPanel ttp=(ThreadedTaskPanel) viewFactory.createThreadedTaskView(message, preferences, ttc);
		try {
			JLabel tmp=(JLabel)MyTestUtilities.getField(ttp, "taskLabel");
			System.out.println(expected.equals(tmp.getText()));
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TestViewFactory test=new TestViewFactory();
		test.testCreateThreadedTaskView("a long task Message","a long task Message");
	}

}
