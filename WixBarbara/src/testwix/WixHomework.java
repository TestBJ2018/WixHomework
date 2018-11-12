package testwix;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import java.util.concurrent.TimeUnit;

public class WixHomework {
	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "C:/Users/janushke/Desktop/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		// Set default timeout for page load
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		

	    // Navigate to homepage
		driver.get("http://www.wix.com/");
		
		String expectedTitle = "Free Website Builder | Create a Free Website | Wix.com";
		String actualTitle = driver.getTitle();
		
		
		// Assume that homepage loaded properly if the expected title is present
        if (actualTitle.contentEquals(expectedTitle)){
            System.out.println("Homepage loaded properly");
        } else {
            System.out.println("Homepage failed to load");
        }
        

        // Navigate to templates page
        driver.findElement(By.partialLinkText("Templates")).click();

        // Submit search criteria
		WebElement element = driver.findElement(By.cssSelector("#search-criteria"));
		element.sendKeys("Country Singer");
		element.sendKeys(Keys.RETURN);
		

	    // Wait for ajax call to finish
		try	{
		    Thread.sleep(1000);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
        
		// Fail the test if search returns no templates
		try {
			WebElement templateElement = driver.findElement(By.cssSelector("[data-hook='templates'] li:first-child"));
			String expectedTemplateTitle = "Country Singer";
			// Fail the test if template doesn't contain expected title
	        if (templateElement.getText().contentEquals(expectedTemplateTitle)){
	            System.out.println("Searching for template succeeded");
	        } else {
	            System.out.println("Searching for template failed");
	        }
		} catch (org.openqa.selenium.NoSuchElementException ex) {
			System.out.print("Searching for template failed");
		}
		
		driver.close();	
		System.exit(0);
	}
}
	