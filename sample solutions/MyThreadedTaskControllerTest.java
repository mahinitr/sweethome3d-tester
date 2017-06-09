package test.bottomup09;

import java.awt.KeyboardFocusManager;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;

import test.MockJunit;
import test.MyTestUtilities;
import mock.DefaultUserPreferences;
import mock.SwingViewFactory;
import mock.ThreadedTaskController;
import mock.ThreadedTaskPanel;
import mock.UserPreferences;
import mock.ViewFactory;


/**
 * Tests threaded task controller features.
 * In this test, it assigns different durations for each task and test with executeTask method,
 * since ThreadedTaskController is to handle the task of printing home plan, export home plan to various format
 * and new version checking in this system.
 * It is important that executeTask method execute those tasks in different threads and completed the works defined in the task
 * @author Hui chen
 */
public class MyThreadedTaskControllerTest {
  
  private UserPreferences preferences;
  private ViewFactory viewFactory;
  private ThreadedTaskController.ExceptionHandler noExceptionHandler;

  public void setUp(){
    preferences=new DefaultUserPreferences();
    viewFactory=new SwingViewFactory();
    noExceptionHandler=new ThreadedTaskController.ExceptionHandler() {// Create an exception handler that fails test if it was called
      public void handleException(Exception ex) {
        MockJunit.fail("Exception handler shouldn't be called");
      }
    };
  }

  public void testSuit() throws InterruptedException{
    testNormalExecute();
    waitAbort(3000);// wait 3 seconds before run next test
    testCancelTask();
    waitAbort(3000);
    testRunningTask();
    waitAbort(3000);
    testThreadTaskViewLabelText();
    waitAbort(3000);
    testThreadTaskViewCreate();
  }

  /**
   * testing execution of a thread task lasts for 2 seconds
   * @throws InterruptedException
   */
  public void testNormalExecute() throws InterruptedException{
	// 1. Create a longer task 
    final CountDownLatch longTaskLatch = new CountDownLatch(2);
    Callable<Void> longTask = new Callable<Void>() {
        public Void call() throws Exception {
          Thread.sleep(2000);
          longTaskLatch.countDown();
          System.out.println("NormalExecute thread end");
          return null;
        }
      };
    // Add a listener that counts down latch once a waiting dialog is displayed
      PropertyChangeListener activeWindowListener = new PropertyChangeListener() {
          public void propertyChange(PropertyChangeEvent ev) {
        	  System.out.println("NormalExecute button press");
            longTaskLatch.countDown();
          }
        };
    KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("activeWindow", activeWindowListener);
    // Check that a long task creates a visible dialog at screen
    new ThreadedTaskController(longTask, "a long task Message", noExceptionHandler, preferences, viewFactory).executeTask(null);
    longTaskLatch.await(2100, TimeUnit.MILLISECONDS);// wait 2.1 seconds to see if the thread is completed
    try {
      MockJunit.assertEquals("Long task was executed with a waiting dialog", new Long(0), longTaskLatch.getCount());
    } catch (AssertionError ex1) {
      System.out.println(ex1.getMessage());
    }
    KeyboardFocusManager.getCurrentKeyboardFocusManager().removePropertyChangeListener("activeWindow", activeWindowListener);
  }

  /**
   * test canceling a task, it cancels the task after 1 second, the task last 5 seconds
   * @throws InterruptedException
   */
  public void testCancelTask() throws InterruptedException{
    // 2. Create a long task that we will cancel  
    final CountDownLatch cancelledTaskLatch = new CountDownLatch(1);
    Callable<Void> cancelledTask = new Callable<Void>() {
        public Void call() throws Exception {
          try {
            Thread.sleep(5000);
            cancelledTaskLatch.countDown();
          } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
          }
          return null;
        }
      };
    
    // Check that a long task creates a visible dialog at screen
    ThreadedTaskController t1=new ThreadedTaskController(cancelledTask, "a long cancelledTask task Message", noExceptionHandler, preferences, viewFactory);
    t1.executeTask(null);
    cancelledTaskLatch.await(1000, TimeUnit.MILLISECONDS);
    t1.cancelTask();
    try {
      MockJunit.assertEquals("Task was cancelled", new Long(1), cancelledTaskLatch.getCount());
    } catch (AssertionError ex) {
      System.out.println(ex.getMessage());
    }
  }

  /**
   *  test a running state of the task after 2.2 second, the task last 5 seconds
   * @throws InterruptedException
   */
  public void testRunningTask() throws InterruptedException{
    // 3. Create a long task that we will check its running state  
    final CountDownLatch cancelledTaskLatch = new CountDownLatch(1);
    Callable<Void> cancelledTask = new Callable<Void>() {
        public Void call() throws Exception {
          try {
            Thread.sleep(5000);
          } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
          }
          return null;
        }
      };
    
    // Check that a long task creates a visible dialog at screen
    ThreadedTaskController t1=new ThreadedTaskController(cancelledTask, "a long runningtask task Message", noExceptionHandler, preferences, viewFactory);
    t1.executeTask(null);
    cancelledTaskLatch.await(2200, TimeUnit.MILLISECONDS);
    try {
      MockJunit.assertEquals("Task is running", true, t1.isTaskRunning());
    } catch (AssertionError ex) {
      System.out.println(ex.getMessage());
    }
  }

  /**
   * test a message display while create a view in the thread task
   */
  public void testThreadTaskViewLabelText(){
    String message="a taskview task label text";
    final CountDownLatch taskLatch = new CountDownLatch(1);
    Callable<Void> taskview = new Callable<Void>() {
      public Void call() throws Exception {
        try {
          Thread.sleep(1000);
          taskLatch.countDown();
        } catch (InterruptedException ex) {
          System.out.println(ex.getMessage());
        }
        return null;
      }
    };
    ThreadedTaskController t1=new ThreadedTaskController(taskview, message, noExceptionHandler, preferences, viewFactory);
    t1.executeTask(null);
    ThreadedTaskPanel ttv=(ThreadedTaskPanel)t1.getView();
    try {
	  taskLatch.await(2000, TimeUnit.MILLISECONDS);
      JLabel tmp=(JLabel)MyTestUtilities.getField(ttv, "taskLabel");
      try {
        MockJunit.assertEquals("Task's text field ", message, tmp.getText());
      } catch (AssertionError ex) {
        System.out.println(ex.getMessage());
      }
    } catch (NoSuchFieldException ex) {
      ex.printStackTrace();
    } catch (IllegalAccessException ex) {
      ex.printStackTrace();
    } catch (InterruptedException e) {
		e.printStackTrace();
	}
  }

  /**
   * test creating a view in a thread task
   */
  public void testThreadTaskViewCreate(){
    String message="a taskview create task Message";
    final CountDownLatch taskLatch = new CountDownLatch(1);
    Callable<Void> taskview = new Callable<Void>() {
      public Void call() throws Exception {
        try {
          Thread.sleep(5000);
          taskLatch.countDown();
        } catch (InterruptedException ex) {
          System.out.println(ex.getMessage());
        }
        return null;
      }
    };
    ThreadedTaskController t1=new ThreadedTaskController(taskview, message, noExceptionHandler, preferences, viewFactory);
    t1.executeTask(null);
    try {
	  taskLatch.await(2100, TimeUnit.MILLISECONDS);
      MockJunit.assertEquals("Task view created ", true, t1!=null);
      String actual= (String)MyTestUtilities.getField(t1, "taskMessage");
      MockJunit.assertEquals("Task view created with ", message, actual);
    } catch (AssertionError ex) {
      System.out.println(ex.getMessage());
    } catch (NoSuchFieldException ex) {
      ex.printStackTrace();
    } catch (IllegalAccessException ex) {
      ex.printStackTrace();
    } catch (InterruptedException e) {
		e.printStackTrace();
	}
  }

  private void waitAbort(long timeMillseconds){
    try {
      Thread.sleep(timeMillseconds);
    } catch (InterruptedException ex) {
      ex.printStackTrace();
    }
  }


  // Execute the tests
  public static void main(String args[]){
    MyThreadedTaskControllerTest test=new MyThreadedTaskControllerTest();
    test.setUp();
    try {
      test.testSuit();
    } catch (InterruptedException ex) {
      ex.printStackTrace();
    }finally{
      System.exit(0);
    }
  }


}
