package test.bottomup09;

import java.util.Locale;

import mock.DefaultUserPreferences;
import mock.UserPreferences;

/**
 * test some attributes of default user preference with English local
 * @author Barry
 *
 */
public class TestDefaultUserPreference {

	private UserPreferences preferences;
	
	/**
	 * construct a tester with a local language
	 * @param language
	 */
	public TestDefaultUserPreference(Locale language) {
		Locale.setDefault(language);
		preferences=new DefaultUserPreferences();
	}

	/**
	 * test a basic function of get a string
	 * Returns the string matching in current language in the context of class, which is first parameter of getLocalizedString
	 * @param key property key defined in the property file
	 * @param expected
	 */
	public void testGetLocalizedString(String key,String expected){
		System.out.println(expected.equals(this.preferences.getLocalizedString(DefaultUserPreferences.class, key)));
	}

	/**
	 *  test newWallThickness property defined in the DefaultUserPreferences.properties
	 * @param expected
	 */
	public void testGetNewWallThickness(float expected){
		System.out.println(expected==this.preferences.getNewWallThickness());
	}
	/**
	 * test newHomeWallHeight property defined in the DefaultUserPreferences.propertiess
	 * @param expected
	 */
	public void testGetNewHomeWallHeight(float expected){
		System.out.println(expected==this.preferences.getNewWallHeight());
	}
	/**
	 * test newFloorThickness property defined in the DefaultUserPreferences.propertiess
	 * @param expected
	 */
	public void testGetNewFloorThickness(float expected){
		System.out.println(expected==this.preferences.getNewFloorThickness());
	}
	/**
	 * test checkUpdatesEnabled property defined in the DefaultUserPreferences.propertiess
	 * @param expected
	 */
	public void testCheckUpdatesEnabled(boolean expected){
		System.out.println(expected==this.preferences.isCheckUpdatesEnabled());
	}
	
	/**
	 *  test currency label defined in the DefaultUserPreferences.propertiess
	 * @param expected
	 */
	public void testGetCurrency(String expected){
		System.out.println(expected.equals(this.preferences.getCurrency()));
	}

	/**
	 * run the test cases
	 * @param args
	 */
	public static void main(String args[]){
		TestDefaultUserPreference tester=new TestDefaultUserPreference(Locale.ENGLISH);
		tester.testGetLocalizedString("unit", "centimeter");
		tester.testGetNewWallThickness(7.5f);
		tester.testGetNewHomeWallHeight(250f);
		tester.testGetNewFloorThickness(12f);
		tester.testCheckUpdatesEnabled(true);
		tester.testGetCurrency("EUR");
	}

}
