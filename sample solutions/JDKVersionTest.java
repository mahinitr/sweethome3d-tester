/*
 * JDKVersionTest.java 1 5. 2015
 */
package test.cyclomatic08;

import test.MockJunit;
import mock.OperatingSystemVersion;


/**
 * Tests for comparing different version of JDK environment.
 * It is to test compareVersions(version1, version2) in the OperatingSystem util class.
 * It is usually used by initiation of GUI elements, where need to assign a specific attributes for those elements based on different JDK version
 * The comparison of different format of JDK version should be correct as expected.
 * @author Hui Chen
 * @since 4/6/2015
 */
public class JDKVersionTest {

  private OperatingSystemVersion os;

  public JDKVersionTest(OperatingSystemVersion os) {
    super();
    this.os=os;
  }

  /*
   * Tests compareVersions(String, String) versions comparator
   */
  private Boolean assertJDKVersionIsSmallerOrEqual(String version1, String version2) {
    return MockJunit.assertTrue(version1 + " should be smaller than " + version2, this.os.compareVersions(version1, version2) <= 0);
  }


  public static void main(String args[]){
    JDKVersionTest test=new JDKVersionTest(new OperatingSystemVersion());
    System.out.println(test.assertJDKVersionIsSmallerOrEqual("1.7.0_71-alhpa14","1.7.0_71-alhpa15"));
    System.out.println(test.assertJDKVersionIsSmallerOrEqual("1.7.0_71-beta14","1.7.0_71-beta15"));
    System.out.println(test.assertJDKVersionIsSmallerOrEqual("1.7.0_71-rc14","1.7.0_71-rc15"));
    System.out.println(test.assertJDKVersionIsSmallerOrEqual("1.7.0_71-alpha14","1.7.0_71-14"));
    System.out.println(test.assertJDKVersionIsSmallerOrEqual("1.7.0_71-beta14","1.7.0_71-14"));
    System.out.println(test.assertJDKVersionIsSmallerOrEqual("1.7.0_71-rc14","1.7.0_71-14"));
    System.out.println(test.assertJDKVersionIsSmallerOrEqual("1.7.0_71-14","1.7.0_71-15"));
    System.out.println(test.assertJDKVersionIsSmallerOrEqual("1.7","1.7.0_71-15"));
    System.out.println(test.assertJDKVersionIsSmallerOrEqual("1.7alpha","1.7"));
    System.out.println(test.assertJDKVersionIsSmallerOrEqual("1.7beta","1.7"));
    System.out.println(test.assertJDKVersionIsSmallerOrEqual("1.7rc","1.7"));
    System.out.println(test.assertJDKVersionIsSmallerOrEqual("1.7alpha","1.7beta"));
    System.out.println(test.assertJDKVersionIsSmallerOrEqual("1.7beta","1.7rc"));
    System.out.println(test.assertJDKVersionIsSmallerOrEqual("1.7","1.7"));
    System.out.println(test.assertJDKVersionIsSmallerOrEqual("1.7","1.8"));
    System.out.println(test.assertJDKVersionIsSmallerOrEqual("1.7","2"));
  }


}
