package twiliopack;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ZomatoResgistration 
{
	public static void main(String[] args) throws Exception
	{
		//Open browser
		WebDriverManager.chromedriver().setup();
		RemoteWebDriver driver=new ChromeDriver();
		//Maximize
		driver.manage().window().maximize();
		//Launch site
		driver.get("https://www.zomato.com/hyderabad");
		//Create wait object
		WebDriverWait wait=new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Signup']"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[text()='Login'])[2]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='sc-1rq4z74-7 caphYZ']"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='sc-1rq4z74-5 fmgAfn']/descendant::p[text()='United States']"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@placeholder='Phone']"))).sendKeys("2408007216");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Send One Time Password']"))).click();
		//Connect to twilio cloud for SMS Service
		String asid="AC51615bfa2dbe3698bafdd5300e307c05";
		String auth="cc8e966a8fd2a9d35ee88031e952ddad";
		Twilio.init(asid,auth);
		ResourceSet<Message> messages=Message.reader().read();
		Message m=messages.iterator().next();
		String body=m.getBody();
		String[] wimb=body.split(" ");
		String otp=wimb[0];
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='hp56s6-1 hnCSAc']")));
		List<WebElement> l=driver.findElements(By.xpath("//*[@class='hp56s6-1 hnCSAc']"));
		for(int i=0;i<l.size();i++)
		{
			Thread.sleep(2000);
			Character c=otp.charAt(i);
			l.get(i).sendKeys(c.toString());
		}	
	}
}
