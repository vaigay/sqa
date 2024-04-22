package com.vaigay.WebSpringBoot.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.junit.jupiter.api.Test;



public class ReportControllerTestSelenium {
	
	WebDriver driver;
	

	@BeforeEach
	public void setup() {
		System.setProperty("webdriver.chrome.driver","./chromedriver_mac/chromedriver");
		driver = new ChromeDriver();
		System.out.println("Bat Dau Test ...");
	}
	
	
	
	@Test// xem trang bao cao
	public void getReportPage() throws InterruptedException {
		driver.get("http://localhost:8080/report");


		List<WebElement> course = driver.findElements(By.xpath("//*[@id=\"reportType\"]/tbody/tr/td[2]/select/option"));
		assertEquals(course.size(), 4);
		List<WebElement> major = driver.findElements(By.xpath("//*[@id=\"reportType\"]/tbody/tr/td[4]/select/option"));
		assertEquals(major.size(), 6);
		
		WebElement linkHome = driver.findElement(By.xpath("/html/body/div[1]/div[1]/a"));
		assertEquals(linkHome.getAttribute("href"),"http://localhost:8080/" ); // kiem tra duong dan ve trang chu
		
		WebElement title = driver.findElement(By.xpath("/html/body/div[1]/h1"));
		assertEquals(title.getText(), "Báo cáo"); // kiem tra tieu de
		
		WebElement tableTitle = driver.findElement(By.xpath("/html/body/div[1]/div[2]/h1"));
		assertEquals(tableTitle.getText(), "Danh sách sinh viên");// kiem tra tieu de cua bang du lieu

	}

	
	@Test// xem bao cao tong
	public void getTotalReport() throws InterruptedException {
		driver.get("http://localhost:8080/report");
		
		List<WebElement> courses = driver.findElements(By.xpath("//*[@id=\"reportType\"]/tbody/tr/td[2]/select/option"));
		assertEquals(courses.size(), 4);
		List<WebElement> majors = driver.findElements(By.xpath("//*[@id=\"reportType\"]/tbody/tr/td[4]/select/option"));
		assertEquals(majors.size(), 6);
		
		Select course = new Select( driver.findElement(By.xpath("//*[@id=\"reportType\"]/tbody/tr/td[2]/select")));
		course.selectByIndex(0);
		Select major = new Select( driver.findElement(By.xpath("//*[@id=\"reportType\"]/tbody/tr/td[4]/select")));
		major.selectByIndex(0);
		driver.findElement(By.xpath("//*[@id=\"reportType\"]/tbody/tr/td[5]/input")).click();
		Thread.sleep(500);
		
		assertEquals(driver.getCurrentUrl(),"http://localhost:8080/reportBy?CourseId=0&MajorId=0"); // xem dia chi trang web sau khi click nut xuat
		
		List<WebElement> reportMessage = driver.findElements(By.xpath("/html/body/div[1]/div[2]/ul"));// So luong report
		assertTrue(reportMessage.size() > 0);
		
		assertEquals(reportMessage.get(0).getText(),"Báo cáo tổng" );
		
		List<WebElement> data = driver.findElements(By.xpath("//*[@id=\"data\"]/tbody/tr"));// So luong user
		assertTrue(data.size() > 0);
		
		List<WebElement> attribute = driver.findElements(By.xpath("//*[@id=\"data\"]/tbody/tr[1]/td"));// So luong thuoc tinh
		assertTrue(attribute.size() == 7);
		
	}
	
	@Test// xem bao cao cua 1 khoa co sinh vien cua tat ca cac nganh
	public void getReportWithOneCourseHasStudent() throws InterruptedException {
		driver.get("http://localhost:8080/report");
		
		List<WebElement> courses = driver.findElements(By.xpath("//*[@id=\"reportType\"]/tbody/tr/td[2]/select/option"));
		assertEquals(courses.size(), 4);
		List<WebElement> majors = driver.findElements(By.xpath("//*[@id=\"reportType\"]/tbody/tr/td[4]/select/option"));
		assertEquals(majors.size(), 6);
		
		Select course = new Select( driver.findElement(By.xpath("//*[@id=\"reportType\"]/tbody/tr/td[2]/select")));
		course.selectByIndex(1);
		Select major = new Select( driver.findElement(By.xpath("//*[@id=\"reportType\"]/tbody/tr/td[4]/select")));
		major.selectByIndex(0);
		driver.findElement(By.xpath("//*[@id=\"reportType\"]/tbody/tr/td[5]/input")).click();
		Thread.sleep(500);
		
		assertEquals(driver.getCurrentUrl(),"http://localhost:8080/reportBy?CourseId=1&MajorId=0"); // xem dia chi trang web sau khi click nut xuat
		
		List<WebElement> reportMessage = driver.findElements(By.xpath("/html/body/div[1]/div[2]/ul"));// So luong report
		assertTrue(reportMessage.size() > 1);
		
		assertTrue(reportMessage.get(0).getText().contains("Báo cáo số sinh viên của khoá "));
		
		List<WebElement> data = driver.findElements(By.xpath("//*[@id=\"data\"]/tbody/tr"));// So luong user
		assertTrue(data.size() > 0);
		
		List<WebElement> attribute = driver.findElements(By.xpath("//*[@id=\"data\"]/tbody/tr[1]/td"));// So luong thuoc tinh
		assertTrue(attribute.size() == 7);
		
	}
	
	@Test// xem bao cao cua 1 khoa khong co sinh vien cua tat cac nganh
	public void getReportWithOneCourseHasNoStudent() throws InterruptedException {
		driver.get("http://localhost:8080/report");
		
		List<WebElement> courses = driver.findElements(By.xpath("//*[@id=\"reportType\"]/tbody/tr/td[2]/select/option"));
		assertEquals(courses.size(), 4);
		List<WebElement> majors = driver.findElements(By.xpath("//*[@id=\"reportType\"]/tbody/tr/td[4]/select/option"));
		assertEquals(majors.size(), 6);
		
		Select course = new Select( driver.findElement(By.xpath("//*[@id=\"reportType\"]/tbody/tr/td[2]/select")));
		course.selectByIndex(3);
		Select major = new Select( driver.findElement(By.xpath("//*[@id=\"reportType\"]/tbody/tr/td[4]/select")));
		major.selectByIndex(0);
		driver.findElement(By.xpath("//*[@id=\"reportType\"]/tbody/tr/td[5]/input")).click();
		Thread.sleep(500);
		
		assertEquals(driver.getCurrentUrl(),"http://localhost:8080/reportBy?CourseId=3&MajorId=0"); // xem dia chi trang web sau khi click nut xuat
		
		List<WebElement> reportMessage = driver.findElements(By.xpath("/html/body/div[1]/div[2]/ul"));// So luong report
		assertTrue(reportMessage.size() == 1);
		
		assertTrue(reportMessage.get(0).getText().contains("Báo cáo số sinh viên của khoá "));
		
		List<WebElement> data = driver.findElements(By.xpath("//*[@id=\"data\"]/tbody/tr"));// So luong user
		assertTrue(data.size() == 0);
		
		List<WebElement> attribute = driver.findElements(By.xpath("//*[@id=\"data\"]/tbody/tr[1]/td"));// So luong thuoc tinh
		assertTrue(attribute.size() == 0);
		
	}
	
	@Test// xem bao cao cua 1 nganh co sinh vien cua tat ca cac khoa
	public void getReportWithOneMajorHasStudent() throws InterruptedException {
		driver.get("http://localhost:8080/report");
		
		List<WebElement> courses = driver.findElements(By.xpath("//*[@id=\"reportType\"]/tbody/tr/td[2]/select/option"));
		assertEquals(courses.size(), 4);
		List<WebElement> majors = driver.findElements(By.xpath("//*[@id=\"reportType\"]/tbody/tr/td[4]/select/option"));
		assertEquals(majors.size(), 6);
		
		Select course = new Select( driver.findElement(By.xpath("//*[@id=\"reportType\"]/tbody/tr/td[2]/select")));
		course.selectByIndex(0);
		Select major = new Select( driver.findElement(By.xpath("//*[@id=\"reportType\"]/tbody/tr/td[4]/select")));
		major.selectByIndex(1);
		driver.findElement(By.xpath("//*[@id=\"reportType\"]/tbody/tr/td[5]/input")).click();
		Thread.sleep(500);
		
		assertEquals(driver.getCurrentUrl(),"http://localhost:8080/reportBy?CourseId=0&MajorId=1"); // xem dia chi trang web sau khi click nut xuat
		
		List<WebElement> reportMessage = driver.findElements(By.xpath("/html/body/div[1]/div[2]/ul"));// So luong report
		assertTrue(reportMessage.size() > 1 );
		
		assertTrue(reportMessage.get(0).getText().contains("Báo cáo số sinh viên của ngành "));
		
		List<WebElement> data = driver.findElements(By.xpath("//*[@id=\"data\"]/tbody/tr"));// So luong user
		assertTrue(data.size() > 0);
		
		List<WebElement> attribute = driver.findElements(By.xpath("//*[@id=\"data\"]/tbody/tr[1]/td"));// So luong thuoc tinh
		assertTrue(attribute.size() == 7);
		
	}
	
	@Test// xem bao cao cua 1 nganh khong co sinh vien cua tat ca cac khoa
	public void getReportWithOneMajorHasNoStudent() throws InterruptedException {
		driver.get("http://localhost:8080/report");
		
		List<WebElement> courses = driver.findElements(By.xpath("//*[@id=\"reportType\"]/tbody/tr/td[2]/select/option"));
		assertEquals(courses.size(), 4);
		List<WebElement> majors = driver.findElements(By.xpath("//*[@id=\"reportType\"]/tbody/tr/td[4]/select/option"));
		assertEquals(majors.size(), 6);
		
		Select course = new Select( driver.findElement(By.xpath("//*[@id=\"reportType\"]/tbody/tr/td[2]/select")));
		course.selectByIndex(0);
		Select major = new Select( driver.findElement(By.xpath("//*[@id=\"reportType\"]/tbody/tr/td[4]/select")));
		major.selectByIndex(5);
		driver.findElement(By.xpath("//*[@id=\"reportType\"]/tbody/tr/td[5]/input")).click();
		Thread.sleep(500);
		
		assertEquals(driver.getCurrentUrl(),"http://localhost:8080/reportBy?CourseId=0&MajorId=5"); // xem dia chi trang web sau khi click nut xuat
		
		List<WebElement> reportMessage = driver.findElements(By.xpath("/html/body/div[1]/div[2]/ul"));// So luong report
		assertTrue(reportMessage.size() == 1 );
		
		assertTrue(reportMessage.get(0).getText().contains("Báo cáo số sinh viên của ngành "));
		
		List<WebElement> data = driver.findElements(By.xpath("//*[@id=\"data\"]/tbody/tr"));// So luong user
		assertTrue(data.size() == 0);
		
		List<WebElement> attribute = driver.findElements(By.xpath("//*[@id=\"data\"]/tbody/tr[1]/td"));// So luong thuoc tinh
		assertTrue(attribute.size() == 0);
		
	}
	
	@Test// xem bao cao cua 1 nganh va 1 khoa k co sinh vien cua tat ca cac khoa
	public void getReportWithOneMajorAndCourseHasStudent() throws InterruptedException {
		driver.get("http://localhost:8080/report");
		
		List<WebElement> courses = driver.findElements(By.xpath("//*[@id=\"reportType\"]/tbody/tr/td[2]/select/option"));
		assertEquals(courses.size(), 4);
		List<WebElement> majors = driver.findElements(By.xpath("//*[@id=\"reportType\"]/tbody/tr/td[4]/select/option"));
		assertEquals(majors.size(), 6);
		
		Select course = new Select( driver.findElement(By.xpath("//*[@id=\"reportType\"]/tbody/tr/td[2]/select")));
		course.selectByIndex(1);
		Select major = new Select( driver.findElement(By.xpath("//*[@id=\"reportType\"]/tbody/tr/td[4]/select")));
		major.selectByIndex(1);
		driver.findElement(By.xpath("//*[@id=\"reportType\"]/tbody/tr/td[5]/input")).click();
		Thread.sleep(500);
		
		assertEquals(driver.getCurrentUrl(),"http://localhost:8080/reportBy?CourseId=1&MajorId=1"); // xem dia chi trang web sau khi click nut xuat
		
		List<WebElement> reportMessage = driver.findElements(By.xpath("/html/body/div[1]/div[2]/ul"));// So luong report
		assertTrue(reportMessage.size() == 1 );
		
		assertTrue(reportMessage.get(0).getText().contains("Số sinh viên của khoá"));
		assertTrue(reportMessage.get(0).getText().contains("Chuyên ngành"));
		
		List<WebElement> data = driver.findElements(By.xpath("//*[@id=\"data\"]/tbody/tr"));// So luong user
		assertTrue(data.size() > 0);
		
		List<WebElement> attribute = driver.findElements(By.xpath("//*[@id=\"data\"]/tbody/tr[1]/td"));// So luong thuoc tinh
		assertTrue(attribute.size() == 7);
		
	}
	
	@Test// xem bao cao cua 1 nganh va 1 khoa k co sinh vien cua tat ca cac khoa
	public void getReportWithOneMajorAndCourseHasNoStudent() throws InterruptedException {
		driver.get("http://localhost:8080/report");
		
		List<WebElement> courses = driver.findElements(By.xpath("//*[@id=\"reportType\"]/tbody/tr/td[2]/select/option"));
		assertEquals(courses.size(), 4);
		List<WebElement> majors = driver.findElements(By.xpath("//*[@id=\"reportType\"]/tbody/tr/td[4]/select/option"));
		assertEquals(majors.size(), 6);
		
		Select course = new Select( driver.findElement(By.xpath("//*[@id=\"reportType\"]/tbody/tr/td[2]/select")));
		course.selectByIndex(1);
		Select major = new Select( driver.findElement(By.xpath("//*[@id=\"reportType\"]/tbody/tr/td[4]/select")));
		major.selectByIndex(5);
		driver.findElement(By.xpath("//*[@id=\"reportType\"]/tbody/tr/td[5]/input")).click();
		Thread.sleep(500);
		
		assertEquals(driver.getCurrentUrl(),"http://localhost:8080/reportBy?CourseId=1&MajorId=5"); // xem dia chi trang web sau khi click nut xuat
		
		List<WebElement> reportMessage = driver.findElements(By.xpath("/html/body/div[1]/div[2]/ul"));// So luong report
		assertTrue(reportMessage.size() == 1 );
		
		assertTrue(reportMessage.get(0).getText().contains("Số sinh viên của khoá"));
		assertTrue(reportMessage.get(0).getText().contains("Chuyên ngành"));
		
		List<WebElement> data = driver.findElements(By.xpath("//*[@id=\"data\"]/tbody/tr"));// So luong user
		assertTrue(data.size() == 0);
		
		List<WebElement> attribute = driver.findElements(By.xpath("//*[@id=\"data\"]/tbody/tr[1]/td"));// So luong thuoc tinh
		assertTrue(attribute.size() == 0);
		
	}
}
