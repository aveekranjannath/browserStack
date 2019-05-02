package browserStack;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BrowserStackTest {
	
	public static String USERNAME="aveekranjannath1";
	public static String ACCESSKEY="6UCDdCc5iqXHBhGQV5hz";
	public static String URL="https://"+USERNAME+":"+ACCESSKEY+"@hub.browserstack.com/wd/hub";
	
	@Test(dataProvider="browserStackData")
	public void openGoogle(Platform platform, String browserName, String browserVersion) throws MalformedURLException{
		
		DesiredCapabilities cap= new DesiredCapabilities();
		cap.setPlatform(platform);
		cap.setBrowserName(browserName);
		cap.setVersion(browserVersion);
		cap.setCapability("browserstack.debug", true);
		
		URL browserStackUrl=new URL(URL);
		
		WebDriver driver=new RemoteWebDriver(browserStackUrl, cap);
		driver.get("https://www.google.com");
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String actualURL=driver.getCurrentUrl();
		String expectedURL="https://www.google.com/";
		Assert.assertEquals(actualURL, expectedURL, "Scenario Failed");
		driver.quit();
		
	}
	
	@DataProvider(name = "browserStackData", parallel=true)
	public Object[][] getData(){
		Object[][] testData=new Object[][]{
			{Platform.MAC, "chrome", "72.0"},
			{Platform.WIN8,"chrome","72.0"},
			{Platform.WINDOWS,"firefox","57"}
			
		};
		return testData;
	}

}
