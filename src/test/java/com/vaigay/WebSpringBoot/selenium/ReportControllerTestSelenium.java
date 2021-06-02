package com.vaigay.WebSpringBoot.selenium;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.test.context.SpringBootTest;




public class ReportControllerTestSelenium {
	
	WebDriver driver;
	
	@Before
	public void setup() {
		System.setProperty("webdriver.edge.driver","msedgedriver.exe");
		driver = new EdgeDriver();
		
	}
	
	
	
	@Test
	public void getReportPage() throws InterruptedException {
		driver.get("http://localhost:8080/reportBy?CourseId=0&MajorId=0");
	
		
		List<WebElement> table = driver.findElements(By.xpath("//*[@id=\"data\"]/tbody/tr[1]/td"));
		System.out.println(table.size());
		for(WebElement e : table) {
			System.out.println(e.getText());
		}
		
	}
	
	
}
