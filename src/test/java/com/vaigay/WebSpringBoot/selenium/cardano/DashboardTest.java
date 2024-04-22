package com.vaigay.WebSpringBoot.selenium.cardano;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DashboardTest {

    WebDriver driver;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver","./chromedriver_mac/chromedriver");
        driver = new ChromeDriver();
        System.out.println("Bat Dau Test ...");
    }

    @Test
    public void testGetTitle(){
        driver.get("https://beta.explorer.cardano.org/en/");

        WebElement element =  driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[3]/main/div/div[2]/div/div/div[1]/div[1]/h3"));

        System.out.println(element.getText());
        assertEquals("Transactions in the last 24 hours 2",element.getText());

        driver.close();
    }
}
