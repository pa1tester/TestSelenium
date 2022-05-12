package com.shoppingcart;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class AddToCart {
	
	 private WebDriver driver;
	 private Actions actions;
	 
	 @Before
	 public void setUp() {
	    driver  = new ChromeDriver();
	    actions = new Actions (driver);
	 }
	  
	 @After
	 public void tearDown() {
	  //  driver.quit();
	 }
	 
	  @Test
	  public void addToCartHighestCostDress() {
		driver.get("http://automationpractice.com/index.php");
		driver.manage().window().maximize();
		
		//gets menu items
		List<WebElement> elements = driver.findElements( By.xpath("//a[@class='sf-with-ul']"));
		
		try {
			   if(elements != null ) {
				
				for(int i=0;i<elements.size();i++)	{
					System.out.print(elements.get(i).getAttribute("title"));
				}
				
				//click on dresses
				elements.get(3).click();
				
				List<WebElement> productElements  = driver.findElements( By.xpath("//div[@class='product-container']")); 
				List<WebElement> productPrices = driver.findElements(By.xpath("//span[@class='price product-price']"));
				List<WebElement> addToCart = driver.findElements(By.xpath("//a[@class='button ajax_add_to_cart_button btn btn-default']"));
				
				
				double highestPrice = 0.0;
				int highestPriceIndex = -1;
				
				for(int i=0;i<productPrices.size();i++)	{
					String productPrice = productPrices.get(i).getAttribute("innerText").trim().substring(1);
					double price = Double.parseDouble(productPrice);
				
					if(price > highestPrice)	{
						highestPrice = price;
						highestPriceIndex = i/2;
					}
			   }
				
				actions.moveToElement(productElements.get(highestPriceIndex)).perform();
				
				addToCart.get(highestPriceIndex).click();
				}
			   
			   } catch(Exception e)	{
				   System.out.print(e.toString());
			   }
	  }

	
	


}

