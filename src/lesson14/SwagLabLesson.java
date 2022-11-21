package lesson14;

import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout.Group;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SwagLabLesson {
	public WebDriver driver;

	@BeforeTest
	public void login_test() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com");
		driver.manage().window().maximize();
		String username = "standard_user";
		String password = "secret_sauce";
		driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys(username);
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
	}

	@Test(priority = 1)
	public void sorting_low_to_high() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select")).click();

		driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select/option[3]")).click();
		Thread.sleep(2000);
		List<WebElement> myOptionList = driver.findElements(By.className("inventory_item_price"));
		List<Double> neweditedList = new ArrayList<>();
		for (int i = 0; i < myOptionList.size(); i++) {
			String price = myOptionList.get(i).getText();
			String editedPrice = price.replace("$", "");
			double val = Double.parseDouble(editedPrice.trim());
			neweditedList.add(val);
			// System.out.println(neweditedList);

		}

		for (int j = 0; j < neweditedList.size(); j++) {
			Boolean checkProcess = neweditedList.get(0) < neweditedList.get(neweditedList.size() - 1);
			Assert.assertEquals(checkProcess, true);

		}
		System.out.println(neweditedList);
	}

	///////////////////////////////////////////////////
	@Test(priority = 2)
	public void sorting_high_to_low() throws InterruptedException {

		driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select/option[4]")).click();
		Thread.sleep(2000);
		List<WebElement> myOptionList2 = driver.findElements(By.className("inventory_item_price"));
		List<Double> neweditedList2 = new ArrayList<>();
		for (int k = 0; k < myOptionList2.size(); k++) {
			String price2 = myOptionList2.get(k).getText();
			String editedPrice2 = price2.replace("$", "");

			double val2 = Double.parseDouble(editedPrice2.trim());
			neweditedList2.add(val2);
		

		}
		for (int f = 0; f > neweditedList2.size(); f++) {

			Boolean checkprocess2 = neweditedList2.get(neweditedList2.size() - 1) > neweditedList2.get(0);
			Assert.assertEquals(checkprocess2, true);

		}
		System.out.println(neweditedList2);

	}

}
