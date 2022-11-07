package StepDefination;


import static org.testng.Assert.assertEquals;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GreenKartStepDefination {
public WebDriver driver;	
String landingPageProductName;
String offerPageProductName;
	@Given("User is on GreenCart Landing Page")
	public void user_is_on_green_cart_landing_page() throws InterruptedException 
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Sarika\\Desktop\\Wedding\\Automation\\chromedriver.exe");
		driver=new ChromeDriver();
	    driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
	    driver.manage().window().maximize();
	    Thread.sleep(1000);
	    
	}
	@When("User searched with shortname {string} and extracted actual name of product")
	public void user_searched_with_shortname_and_extracted_actual_name_of_product(String shortname) throws InterruptedException 
	{
	   driver.findElement(By.xpath("//input[@type='search']")).sendKeys(shortname);
	   Thread.sleep(2000);
	   landingPageProductName=driver.findElement(By.cssSelector("h4.product-name")).getText().split("-")[0].trim();
	   System.out.println(landingPageProductName  + " is extracted from home page");
	}
	@Then("User searched for {string} shortname in offers page")
	public void user_searched_for_same_shortname_in_offers_page(String shortname) throws InterruptedException 
	{
	    driver.findElement(By.linkText("Top Deals")).click();
	    Thread.sleep(2000);
	    Set<String> s1= driver.getWindowHandles();
	    Iterator<String> i1=s1.iterator();
	    String parentWindow=i1.next();
	    String childWindow=i1.next();
	    driver.switchTo().window(childWindow);
	   
	    
	    driver.findElement(By.xpath("//input[@type='search']")).sendKeys(shortname);
	    Thread.sleep(2000);
	    offerPageProductName=driver.findElement(By.cssSelector("tr td:nth-child(1)")).getText();

   }
	@Then("validate product name in offers page matches with product name in Landing Page")
	public void validate_product_name_in_offers_page()
	
	{
		Assert.assertEquals(offerPageProductName, landingPageProductName);
	}
}