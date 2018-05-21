package bot;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebTableAttempt {
	// -------------------------------------------------------------------------------
	public static final int number = 100; // BTW: MOST RECENT WAS: |
	public static final int initialId = 20000067; // 20034130 |
	public static final String fileLocation = "D:/Users/James/Desktop/advait.txt";
	// -------------------------------------------------------------------------------
	// ALWAYS REMEMBER TO CLOSE ALL OTHER WINDOWS AND MINIMIZE CONSOLE
	public static int currentId = initialId;
	public static final String pwd = "wildcats";

	public static void main(String[] args) throws Exception {
		Robot rbt = new Robot();
		ChromeOptions options = new ChromeOptions();
		//options.addArguments("user-data-dir=" + Constants.userDataLocation);
		System.setProperty("webdriver.chrome.driver", Constants.webDriverLocation);
		WebDriver driver = new ChromeDriver(options);
		driver.switchTo().defaultContent();
		driver.get(Constants.appUrl);
		driver.manage().window().maximize();
		String actualTitle = driver.getTitle(); // fetch the title of the web page and save it into a string variable
		if (Constants.expectedTitle.equals(actualTitle)) {
			System.out.println("Verification Successful - The correct title is displayed on the web page.");
		} else {
			System.out.println("Verification Failed - An incorrect title is displayed on the web page.");
		}
		rbt.keyPress(KeyEvent.VK_CONTROL);
		rbt.keyPress(KeyEvent.VK_F);
		rbt.keyRelease(KeyEvent.VK_CONTROL);
		rbt.keyRelease(KeyEvent.VK_F);

		String text = "Magna";
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(new StringSelection(text), new StringSelection(text));

		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);

		Thread.sleep(5000);
		Util.fastClick((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2), rbt);

		Thread.sleep(500);
		Util.fastClick(1340, 507, rbt);
		Thread.sleep(100);
		Util.fastClick(711, 497, rbt);

		text = "Adolf";
		clipboard.setContents(new StringSelection(text), new StringSelection(text));

		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyPress(KeyEvent.VK_TAB);

		text = "Hitler";
		clipboard.setContents(new StringSelection(text), new StringSelection(text));

		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyPress(KeyEvent.VK_TAB);

		text = "ahitler3652@gmail.com";
		clipboard.setContents(new StringSelection(text), new StringSelection(text));

		Util.fastClick(827, 624, rbt);
	}
}
