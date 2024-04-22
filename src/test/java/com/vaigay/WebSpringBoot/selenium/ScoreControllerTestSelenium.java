package com.vaigay.WebSpringBoot.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.test.context.event.annotation.AfterTestClass;


import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ScoreControllerTestSelenium {

    WebDriver driver;

//    @Before
    public void setup() {

    	System.setProperty("webdriver.chrome.driver","./chromedriver_mac/chromedriver");
		driver = new ChromeDriver();
        System.out.println("Bat Dau Test ...");
    }

    @Test // xem trang Theo dõi điểm
    public void getViewScorePage() throws InterruptedException {
        setup();
        driver.get("http://localhost:8080/viewScore");

        WebElement linkHome = driver.findElement(By.xpath("/html/body/div[1]/div[1]/a"));
        assertEquals(linkHome.getAttribute("href"),"http://localhost:8080/" ); // check href to Trang Chu

        WebElement title = driver.findElement(By.xpath("/html/body/div/div/h1"));
        assertEquals(title.getText(), "Theo dõi điểm"); // check title

        List<WebElement> semester = driver.findElements(By.xpath("/html/body/div/div/form/select/option"));
        assertEquals(semester.size(),2);

        driver.close();
    }

    @Test // xem danh sách môn học theo kỳ học đã chọn
    public void getViewSubjectBySemester() throws InterruptedException {
        setup();
        driver.get("http://localhost:8080/viewScore");

        Select selectSemester = new Select(driver.findElement(By.name("semester")));
        selectSemester.selectByIndex(0);

        WebElement btn = driver.findElement(By.xpath("/html/body/div/div/form/input"));
        btn.click();
        Thread.sleep(500);
        assertEquals(driver.getCurrentUrl(),"http://localhost:8080/ViewBySemester?semester=1");

        WebElement linkHome = driver.findElement(By.xpath("/html/body/div[1]/div[1]/a"));
        assertEquals(linkHome.getAttribute("href"),"http://localhost:8080/" ); // check href to Trang Chu

        WebElement title = driver.findElement(By.xpath("/html/body/div/div/h1"));
        assertEquals(title.getText(), "Danh sách Môn học"); // check title

        List<WebElement> data = driver.findElements(By.xpath("/html/body/div/div[2]/table/tbody/tr"));// So luong mon hoc
        assertTrue(data.size() > 0);
        driver.close();
    }

    @Test // chọn 1 kỳ học KHÔNG có môn học nào
    public void getViewSubjectBySemester2() throws InterruptedException {
        setup();
        driver.get("http://localhost:8080/viewScore");

        Select selectSemester = new Select(driver.findElement(By.name("semester")));
        selectSemester.selectByIndex(1);

        WebElement btn = driver.findElement(By.xpath("/html/body/div/div/form/input"));
        btn.click();
        Thread.sleep(500);
        assertEquals(driver.getCurrentUrl(),"http://localhost:8080/ViewBySemester?semester=2");

        WebElement linkHome = driver.findElement(By.xpath("/html/body/div[1]/div[1]/a"));
        assertEquals(linkHome.getAttribute("href"),"http://localhost:8080/" ); // check href to Trang Chu

        WebElement title = driver.findElement(By.xpath("/html/body/div/div/h1"));
        assertEquals(title.getText(), "Danh sách Môn học"); // check title

        List<WebElement> data = driver.findElements(By.xpath("/html/body/div/div[2]/table/tbody/tr"));// So luong mon hoc
        assertTrue(data.size() == 0);
        driver.close();
    }

    @Test // Test Xem danh sách lớp của một môn học đã có lớp được tổ chức
    public void getClassBySubject() throws InterruptedException {
        setup();
        driver.get("http://localhost:8080/viewScore");

        Select selectSemester = new Select(driver.findElement(By.name("semester")));
        selectSemester.selectByIndex(0);

        WebElement btn = driver.findElement(By.xpath("/html/body/div/div/form/input"));
        btn.click();

        Thread.sleep(500);
        assertEquals(driver.getCurrentUrl(),"http://localhost:8080/ViewBySemester?semester=1");


        driver.findElement(By.xpath("/html/body/div/div[2]/table/tbody/tr[1]/td[1]/a")).click();
        Thread.sleep(500);

        List<WebElement> data = driver.findElements(By.xpath("/html/body/div[2]/table/tbody/tr"));// So luong lớp
        assertTrue(data.size() > 0);

        WebElement linkHome = driver.findElement(By.xpath("/html/body/div[1]/a"));
        assertEquals(linkHome.getAttribute("href"),"http://localhost:8080/" ); // check href to Trang Chu

        WebElement title = driver.findElement(By.xpath("/html/body/div[2]/h1"));
        assertEquals(title.getText(), "Danh sách Lớp học"); // check title

        WebElement classTitle = driver.findElement(By.xpath("/html/body/div[2]/p"));
        assertEquals(classTitle.getText(),"Môn học: Lập trình mạng");

        driver.close();
    }

    @Test // Test Xem điểm của lớp có sinh viên
    public void getScoreDetail() throws InterruptedException {

        setup();
        driver.get("http://localhost:8080/viewScore");

        Select selectSemester = new Select(driver.findElement(By.name("semester")));
        selectSemester.selectByIndex(0);

        WebElement btn = driver.findElement(By.xpath("/html/body/div/div/form/input"));
        btn.click();
        Thread.sleep(500);
        assertEquals(driver.getCurrentUrl(),"http://localhost:8080/ViewBySemester?semester=1");
        driver.findElement(By.xpath("/html/body/div/div[2]/table/tbody/tr[1]/td[1]/a")).click();
        Thread.sleep(500);
        assertEquals(driver.getCurrentUrl(),"http://localhost:8080/subjectClass/1/semester/1");
        driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[1]/td[1]/a")).click();
        Thread.sleep(500);
        assertEquals(driver.getCurrentUrl(),"http://localhost:8080/ScoreDetail/1");
        List<WebElement> data = driver.findElements(By.xpath("/html/body/div/div[2]/table/tbody/tr"));// So luong mon hoc
        assertTrue(data.size() > 0);
        WebElement linkHome = driver.findElement(By.xpath("/html/body/div/div[1]/a"));
        assertEquals(linkHome.getAttribute("href"),"http://localhost:8080/" ); // check href to Trang Chu

        WebElement title = driver.findElement(By.xpath("/html/body/div/div[2]/h1"));
        assertEquals(title.getText(), "Điểm của lớp"); // check title
        driver.close();
    }

    @Test // Test Xem điểm của lớp KHÔNG có sinh viên
    public void getScoreNoDetail() throws InterruptedException {

        setup();
        driver.get("http://localhost:8080/viewScore");

        Select selectSemester = new Select(driver.findElement(By.name("semester")));
        selectSemester.selectByIndex(0);

        WebElement btn = driver.findElement(By.xpath("/html/body/div/div/form/input"));
        btn.click();
        Thread.sleep(500);
        assertEquals(driver.getCurrentUrl(),"http://localhost:8080/ViewBySemester?semester=1");
        driver.findElement(By.xpath("/html/body/div/div[2]/table/tbody/tr[1]/td[1]/a")).click();
        Thread.sleep(500);
        assertEquals(driver.getCurrentUrl(),"http://localhost:8080/subjectClass/1/semester/1");
        driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[2]/td[1]/a")).click();
        Thread.sleep(500);
        assertEquals(driver.getCurrentUrl(),"http://localhost:8080/ScoreDetail/2");
        List<WebElement> data = driver.findElements(By.xpath("/html/body/div/div[2]/table/tbody/tr"));// So luong mon hoc
        assertTrue(data.size() == 0);
        WebElement linkHome = driver.findElement(By.xpath("/html/body/div/div[1]/a"));
        assertEquals(linkHome.getAttribute("href"),"http://localhost:8080/" ); // check href to Trang Chu

        WebElement title = driver.findElement(By.xpath("/html/body/div/div[2]/h1"));
        assertEquals(title.getText(), "Điểm của lớp"); // check title
        driver.close();
    }
}
