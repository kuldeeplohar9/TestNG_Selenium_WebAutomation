package com.TestNg_Selenuim;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNg_Selenuim {

	public static WebDriver driver;
	public static Select Select;

	@BeforeTest
	public void start() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.mycarhelpline.com/");
	}

	@Test(priority = 1, enabled = true)
	public void Landing_Page() {
		String expectedURL = "https://www.mycarhelpline.com/";
		String actualURL = driver.getCurrentUrl();
		Assert.assertEquals(actualURL, expectedURL, "correct URL");
	}

	@Test(priority = 2, enabled = true, dependsOnMethods = "Landing_Page")
	public void Calculator_Click() {
		driver.findElement(By.xpath("//a[text()='Calculator']")).click();
	}

	@Test(priority = 3, enabled = true, dependsOnMethods = { "Landing_Page", "Calculator_Click" })
	public void Home_page() {
		driver.findElement(By.partialLinkText("Home")).click();
	}

	@Test(priority = 4, enabled = true, dependsOnMethods = { "Landing_Page", "Calculator_Click" })
	public void New_Cars() throws InterruptedException {
		Select = new Select(driver.findElement(By.xpath("//div[@class='newcar-right']//select[@id='brand']\n" + "")));
		Select.selectByVisibleText("Honda");
		Thread.sleep(2000);
		Select = new Select(driver.findElement(By.xpath("//div[@class='newcar-right']//select[@id='model']")));
		Select.selectByVisibleText("Amaze");

		Thread.sleep(2000);

//		Alert alert = driver.switchTo().alert();
//		System.out.println("alert value=" + alert.getText());
//		// alert.accept();
//		alert.dismiss();

		driver.findElement(By.xpath("//div[@class='newcar-right']//input[@class='red-btn']")).click();
	}

	@AfterTest
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
