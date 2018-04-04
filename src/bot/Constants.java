package bot;

public class Constants {
	// ------------------------------------------------TIMER
	// CONSTANTS-----------------------------------------------------------------------//
	/** How long to wait if login was unsuccessful */
	public static final int msDelayAfterLoginFailure = 5;
	/** How long between ctrl+a and ctrl+c */
	public static final int msDelayBetweenSelectAndCopy = 10;
	/** How long between landing and scanning */
	public static final int msDelayBetweenLandingAndScanning = 100;

	// ------------------------------------------------OTHER
	// CONSTANTS-----------------------------------------------------------------------//
	/** What Selenium expects the site title to be */
	public static final String expectedTitle = "Parent Portal";
	/** What URL to search */
	public static final String appUrl = "https://mistar.oakland.k12.mi.us/novi/parentportal";
	/** Where the Chrome Web Driver is located */
	public static final String webDriverLocation = "chromedriver.exe";
	/** Where the Chrome user data folder is */
	public static final String userDataLocation = "C:/Users/James/AppData/Local/Google/Chrome/User Data";
	/** x coordinate click to do nothing */
	public static final int dummyXCoordinate = 933;
}
