package bot;


import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Util {	
	/**
	   * Selects all content on page
	   * @param x x coordinate to click
	   * @param y y coordinate to click
	   * @param bot robot to execute the action
	   */	
	
	public static void fastClick(int x, int y, Robot bot) throws InterruptedException {	
		bot.mouseMove(x, y);
		Thread.sleep(5);	
		bot.mousePress(InputEvent.BUTTON1_MASK);
		bot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	/**
	   * Highlights page and puts onto clipboard
	   * @param bot Name of robot 
	   * @param sleepTime How long between ctrl+a and ctrl+c
	   */		
	public static void fetchContentToClipboard(Robot bot, int sleepTime) throws InterruptedException {
		bot.keyPress(KeyEvent.VK_CONTROL);
		bot.keyPress(KeyEvent.VK_A);
		bot.keyRelease(KeyEvent.VK_CONTROL);
		bot.keyRelease(KeyEvent.VK_A);
		Thread.sleep(sleepTime);
		bot.keyPress(KeyEvent.VK_CONTROL);
		bot.keyPress(KeyEvent.VK_C);
		bot.keyRelease(KeyEvent.VK_CONTROL);
		bot.keyRelease(KeyEvent.VK_C);
	}
	
	/**
	   * Indicates if login failed. If login failed, it was because the remind link exists
	   * @param driv Name of Webdriver to search
	   * @param bot Name of Robot to click with
	   * @return true if login failed, false if login was successful
	   */
	public static boolean loginFailed(WebDriver driv, Robot bot, ArrayList<WebElement> list, WebDriverWait wait) throws InterruptedException {
		//Thread.sleep(350);
		Util.fastClick(660, 647, bot);		
		try {
			wait.until(ExpectedConditions.visibilityOfAllElements(list));
			driv.findElement(By.id("rmndlink"));
	    } catch (Exception e) {
	        return false;
	    }
	    return true;
	}

	
	
	/**
	   * Indicates if the info page has loaded yet
	   * @param driv Name of WebDriver
	   * @param bot Name of robot 
	   * @return Returns true if haven't loaded yet, Returns false if has loaded
	   */


	/**
	   * Loads content from clip board
	   * @return returns clip board content
	   */
	public static String clipboardContent() throws Exception { //This method is the method that gets gets the content from cboard
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		String result = (String) clipboard.getData(DataFlavor.stringFlavor);
		return result;
	}
}
