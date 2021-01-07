package twiliopack;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonRegistration
{
	public static void main(String[] args) throws Exception
	{
		ChromeOptions co=new ChromeOptions();
		co.addArguments("--disable-notifications");
		//Open browser
		WebDriverManager.chromedriver().setup();
		RemoteWebDriver driver=new ChromeDriver(co);
		//Maximize
		driver.manage().window().maximize();
		//Launch site
		driver.get("https://www.amazon.in/");
		//Create wait object
		WebDriverWait wait=new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Account & Lists']")));
		WebElement e=driver.findElement(By.xpath("//*[text()='Account & Lists']"));
		Actions a=new Actions(driver);
		a.moveToElement(e).build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[text()='New customer? '])[1]/a"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("customerName"))).sendKeys("Rohan");
		WebElement e1=driver.findElement(By.name("countryCode"));
		Select s=new Select(e1);
		s.selectByValue("US");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email"))).sendKeys("2408007216");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password"))).sendKeys("Priyanka@123");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("continue"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("code")));
		//Connect to twilio cloud for SMS Service
		String asid="AC51615bfa2dbe3698bafdd5300e307c05";
		String auth="cc8e966a8fd2a9d35ee88031e952ddad";
		Twilio.init(asid,auth);
		ResourceSet<Message> messages=Message.reader().read();
		Message m=messages.iterator().next();
		String body=m.getBody();
		String[] wimb=body.split(" ");
		String otp=wimb[0];
		/*Pattern p=Pattern.compile("[0-9]{6}");
		Matcher mat=p.matcher(body);
		if(mat.find())
		{
			String otp=mat.group();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("code"))).sendKeys(otp);
		}*/
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("code"))).sendKeys(otp);
	}
}
