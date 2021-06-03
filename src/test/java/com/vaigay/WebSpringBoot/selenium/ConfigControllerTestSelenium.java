package com.vaigay.WebSpringBoot.selenium;



import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ConfigControllerTestSelenium {
	private WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		// Khởi tạo trình duyệt
		System.setProperty("webdriver.edge.driver","msedgedriver.exe");
		driver = new EdgeDriver();
	}

//Test GUI
	// test case1: Truy cap vao chuc nang config
	@Test(enabled = true)
	public void TestCase1_OpenConfig() {
		// Mở localhost
		driver.get("http://localhost:8080/config");
		String currentUrl = driver.getCurrentUrl();
		assertFalse(!currentUrl.equals("http://localhost:8080/config"));
	}

	// test case2: Test href trở về trang chủ ở config

	@Test(enabled = true)
	public void TestCase2_ReturnHomePage() {
		// Mở localhost
		driver.get("http://localhost:8080/config");
		// Click Trang chủ
		WebElement homepage = driver.findElement(By.id("hompage"));
		homepage.click();
		// Lấy url hiện thời
		String currentUrl = driver.getCurrentUrl();

		assertFalse(!currentUrl.equals("http://localhost:8080/"));
	}
	//test case3:  Test href trở về trang chủ ở configdetail
	@Test(enabled = true)
	public void TestCase3_ReturnHomePage() {
		// Mở localhost
		driver.get("http://localhost:8080/subjectConfig/4");
		// Click Trang chủ
		WebElement homepage = driver.findElement(By.id("hompage"));
		homepage.click();
		// Lấy url hiện thời
		String currentUrl = driver.getCurrentUrl();

		assertFalse(!currentUrl.equals("http://localhost:8080/"));
	}
	//test case4:  test href môn học khác ở configdetail
	@Test(enabled = true)
	public void TestCase4_RedirectOtherSubjects() {
		// Mở localhost
		driver.get("http://localhost:8080/subjectConfig/4");
		// Click Trang chủ
		WebElement homepage = driver.findElement(By.id("othersubject"));
		homepage.click();
		// Lấy url hiện thời
		String currentUrl = driver.getCurrentUrl();

		assertFalse(!currentUrl.equals("http://localhost:8080/config"));
	}
	// test case3: select 1 môn học cần config
	@Test(enabled = true)
	public void TestCase5_SelectSubject() {
		// Mở localhost
		driver.get("http://localhost:8080/subjectConfig/4");
		String title = driver.getTitle();

		// kiem tra title

		if (title.equals("Cấu hình chi tiết")) {
			// In ra thông báo theo mong muốn
			System.out.println("Selected Success!");
		}

	}

	// test case4: test click button cau hinh
	@Test(enabled = true)
	public void TestCase6_ClickButtonConfig() {
		// Mở localhost
		driver.get("http://localhost:8080/subjectConfig/4");

		// Click button cấu hình
		WebElement btn = driver.findElement(By.id("View"));
		btn.click();
		// lấy Input điểm cc
		WebElement chuyencan = driver.findElement(By.name("attendance"));

		// kiểm tra Attribute readonly of input cc
		assertFalse(chuyencan.getAttribute("readonly") != null);
	}

	// test case5: test button hủy cấu hình
	@Test(enabled = true)
	public void TestCase7_ClickButtonCancelConfig() {
		// Mở localhost
		driver.get("http://localhost:8080/subjectConfig/4");

		// Click button cấu hình
		WebElement btn = driver.findElement(By.id("View"));
		btn.click();

		// Click button hủy
		WebElement btnCancel = driver.findElement(By.id("Cancel"));
		btnCancel.click();

		// lấy Input điểm cc
		WebElement chuyencan = driver.findElement(By.name("attendance"));

		// kiểm tra Attribute readonly of input cc
		assertFalse(chuyencan.getAttribute("readonly") == null);

	}

	// test case5: test button lưu
	@Test(enabled = true)
	public void TestCase8_ClickButtonCancelConfig() {
		// Mở localhost
		driver.get("http://localhost:8080/subjectConfig/4");

		// Click button cấu hình
		WebElement btn = driver.findElement(By.id("View"));
		btn.click();

		// Click button hủy
		WebElement btnSave = driver.findElement(By.id("Save"));
		btnSave.click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		// lấy Input điểm cc
		WebElement chuyencan = driver.findElement(By.name("attendance"));

		// kiểm tra Attribute readonly of input cc

		// kiểm tra Attribute readonly of input cc
		assertFalse(chuyencan.getAttribute("readonly") == null);

	}
	
	

// Test module
	// Test case: Cấu hình với cái trường Invalid

	// tc9: Test Cấu hình chi tiết mà nhập vào các trường 1 số thỏa mãn > 0 và <100
	// nhưng tổng các trường > 100

	@Test(enabled = true)
	public void TestCase9_SetConfigWithInvalid() {

		// Mở localhost
		driver.get("http://localhost:8080/subjectConfig/4");
		// Click button cấu hình
		WebElement btn = driver.findElement(By.id("View"));
		btn.click();

		// Lay data trc khi test để rollback
		Map<String, String> map1 = new HashMap<String, String>();
		map1 = getDataConfig();

		// set value cc=20, ktra=20, baitap=20, thuchanh= 20, diemthi=21 ---"biên"
		inputConfig("20", "20", "20", "20", "21");
		// Click Button Lưu
		WebElement btnSave = driver.findElement(By.id("Save"));
		btnSave.click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		// Ktra
		driver.navigate().refresh();
		WebElement btn2 = driver.findElement(By.id("View"));
		btn2.click();
		assertFalse(checkDataInsert("20", "20", "20", "20", "21"));

		// rollback
		inputConfig(map1.get("chuyencan"), map1.get("kiemtra"), map1.get("baitap"), map1.get("thuchanh"),
				map1.get("diemthi"));
		WebElement btnSave2 = driver.findElement(By.id("Save"));
		btnSave2.click();
		Alert alert2 = driver.switchTo().alert();
		alert2.accept();
	}
	// tc10: Test Cấu hình chi tiết mà nhập vào các trường 1 số thỏa mãn > 0 và <100
		// nhưng tổng các trường < 100

		@Test(enabled = true)
		public void TestCase10_SetConfigWithInvalid() {

			// Mở localhost
			driver.get("http://localhost:8080/subjectConfig/4");
			// Click button cấu hình
			WebElement btn = driver.findElement(By.id("View"));
			btn.click();

			// Lay data trc khi test để rollback
			Map<String, String> map1 = new HashMap<String, String>();
			map1 = getDataConfig();

			// set value cc=20, ktra=20, baitap=20, thuchanh= 20, diemthi=21 ---"biên"
			inputConfig("20", "20", "20", "20", "19");
			// Click Button Lưu
			WebElement btnSave = driver.findElement(By.id("Save"));
			btnSave.click();
			Alert alert = driver.switchTo().alert();
			alert.accept();
			// Ktra
			driver.navigate().refresh();
			WebElement btn2 = driver.findElement(By.id("View"));
			btn2.click();
			assertFalse(checkDataInsert("20", "20", "20", "20", "21"));

			// rollback
			inputConfig(map1.get("chuyencan"), map1.get("kiemtra"), map1.get("baitap"), map1.get("thuchanh"),
					map1.get("diemthi"));
			WebElement btnSave2 = driver.findElement(By.id("Save"));
			btnSave2.click();
			Alert alert2 = driver.switchTo().alert();
			alert2.accept();
		}

	// test với một trường là số âm
	@Test(enabled = true)
	public void TestCase11_SetConfigWithInvalid() {
		// Mở localhost
		driver.get("http://localhost:8080/subjectConfig/4");
		// Click button cấu hình
		WebElement btn = driver.findElement(By.id("View"));
		btn.click();

		// Lay data trc khi test để rollback
		Map<String, String> map1 = new HashMap<String, String>();
		map1 = getDataConfig();

		// set value cc=40, ktra=20, baitap=20, thuchanh= 21, diemthi=số âm ---"biên"
		inputConfig("40", "20", "20", "21", "-1");
		// Click Button Lưu
		WebElement btnSave=driver.findElement(By.id("Save"));
		btnSave.click();
		
		// Ktra

		Alert alert = driver.switchTo().alert();
		alert.accept();
		// Ktra
		driver.navigate().refresh();
		WebElement btn2 = driver.findElement(By.id("View"));
		btn2.click();
		assertFalse(checkDataInsert("40", "20", "20", "21", "-1"));

		// rollback
		inputConfig(map1.get("chuyencan"), map1.get("kiemtra"), map1.get("baitap"), map1.get("thuchanh"),
				map1.get("diemthi"));
		WebElement btnSave2 = driver.findElement(By.id("Save"));
		btnSave2.click();
		Alert alert2 = driver.switchTo().alert();
		alert2.accept();
	}
	
	@Test(enabled = true)
	public void TestCase12_SetConfigWithInvalid() {
		// Mở localhost
		driver.get("http://localhost:8080/subjectConfig/4");
		// Click button cấu hình
		WebElement btn = driver.findElement(By.id("View"));
		btn.click();

		// Lay data trc khi test để rollback
		Map<String, String> map1 = new HashMap<String, String>();
		map1 = getDataConfig();

		// set value cc=40, ktra=20, baitap=20, thuchanh= số âm, diemthi=21 ---"biên"
		inputConfig("40", "20", "20", "-1", "21");
		// Click Button Lưu
		WebElement btnSave=driver.findElement(By.id("Save"));
		btnSave.click();
		
		// Ktra

		Alert alert = driver.switchTo().alert();
		alert.accept();
		// Ktra
		driver.navigate().refresh();
		WebElement btn2 = driver.findElement(By.id("View"));
		btn2.click();
		assertFalse(checkDataInsert("40", "20", "20", "-1", "21"));

		// rollback
		inputConfig(map1.get("chuyencan"), map1.get("kiemtra"), map1.get("baitap"), map1.get("thuchanh"),
				map1.get("diemthi"));
		WebElement btnSave2 = driver.findElement(By.id("Save"));
		btnSave2.click();
		Alert alert2 = driver.switchTo().alert();
		alert2.accept();
	}
	
	@Test(enabled = true)
	public void TestCase13_SetConfigWithInvalid() {
		// Mở localhost
		driver.get("http://localhost:8080/subjectConfig/4");
		// Click button cấu hình
		WebElement btn = driver.findElement(By.id("View"));
		btn.click();

		// Lay data trc khi test để rollback
		Map<String, String> map1 = new HashMap<String, String>();
		map1 = getDataConfig();

		// set value cc=40, ktra=20, baitap=-1, thuchanh= 21, diemthi=20 ---"biên"
		inputConfig("40", "20", "-1", "21", "20");
		// Click Button Lưu
		WebElement btnSave=driver.findElement(By.id("Save"));
		btnSave.click();
		
		// Ktra

		Alert alert = driver.switchTo().alert();
		alert.accept();
		// Ktra
		driver.navigate().refresh();
		WebElement btn2 = driver.findElement(By.id("View"));
		btn2.click();
		assertFalse(checkDataInsert("40", "20", "-1", "21", "20"));

		// rollback
		inputConfig(map1.get("chuyencan"), map1.get("kiemtra"), map1.get("baitap"), map1.get("thuchanh"),
				map1.get("diemthi"));
		WebElement btnSave2 = driver.findElement(By.id("Save"));
		btnSave2.click();
		Alert alert2 = driver.switchTo().alert();
		alert2.accept();
	}
	
	@Test(enabled = true)
	public void TestCase14_SetConfigWithInvalid() {
		// Mở localhost
		driver.get("http://localhost:8080/subjectConfig/4");
		// Click button cấu hình
		WebElement btn = driver.findElement(By.id("View"));
		btn.click();

		// Lay data trc khi test để rollback
		Map<String, String> map1 = new HashMap<String, String>();
		map1 = getDataConfig();

		// set value cc=40, ktra=-1, baitap=20, thuchanh= 21, diemthi=20 ---"biên"
		inputConfig("40", "-1", "20", "21", "20");
		// Click Button Lưu
		WebElement btnSave=driver.findElement(By.id("Save"));
		btnSave.click();
		
		// Ktra

		Alert alert = driver.switchTo().alert();
		alert.accept();
		// Ktra
		driver.navigate().refresh();
		WebElement btn2 = driver.findElement(By.id("View"));
		btn2.click();
		assertFalse(checkDataInsert("40", "-1", "20", "21", "20"));

		// rollback
		inputConfig(map1.get("chuyencan"), map1.get("kiemtra"), map1.get("baitap"), map1.get("thuchanh"),
				map1.get("diemthi"));
		WebElement btnSave2 = driver.findElement(By.id("Save"));
		btnSave2.click();
		Alert alert2 = driver.switchTo().alert();
		alert2.accept();
	}
	@Test(enabled = true)
	public void TestCase15_SetConfigWithInvalid() {
		// Mở localhost
		driver.get("http://localhost:8080/subjectConfig/4");
		// Click button cấu hình
		WebElement btn = driver.findElement(By.id("View"));
		btn.click();

		// Lay data trc khi test để rollback
		Map<String, String> map1 = new HashMap<String, String>();
		map1 = getDataConfig();

		// set value cc=-1, ktra=20, baitap=20, thuchanh= 21, diemthi=40 ---"biên"
		inputConfig("-1", "20", "20", "21", "40");
		// Click Button Lưu
		WebElement btnSave=driver.findElement(By.id("Save"));
		btnSave.click();
		
		// Ktra

		Alert alert = driver.switchTo().alert();
		alert.accept();
		// Ktra
		driver.navigate().refresh();
		WebElement btn2 = driver.findElement(By.id("View"));
		btn2.click();
		assertFalse(checkDataInsert("-1", "20", "20", "21", "40"));

		// rollback
		inputConfig(map1.get("chuyencan"), map1.get("kiemtra"), map1.get("baitap"), map1.get("thuchanh"),
				map1.get("diemthi"));
		WebElement btnSave2 = driver.findElement(By.id("Save"));
		btnSave2.click();
		Alert alert2 = driver.switchTo().alert();
		alert2.accept();
	}
	// test với 1 trường là chữ cái
	@Test(enabled = true)
	public void TestCase16_SetConfigWithInvalid() {
		// Mở localhost
		driver.get("http://localhost:8080/subjectConfig/4");
		// Click button cấu hình
		WebElement btn = driver.findElement(By.id("View"));
		btn.click();

		// Lay data trc khi test để rollback
		Map<String, String> map1 = new HashMap<String, String>();
		map1 = getDataConfig();

		// set value cc=40, ktra=20, baitap=20, thuchanh= 20, diemthi=chữ cái ---"biên"
		inputConfig("40", "20", "20", "20", "c");
		// Click Button Lưu
		WebElement btnSave=driver.findElement(By.id("Save"));
		btnSave.click();
		

		Alert alert = driver.switchTo().alert();
		alert.accept();
		// Ktra
		driver.navigate().refresh();
		WebElement btn2 = driver.findElement(By.id("View"));
		btn2.click();
		assertFalse(checkDataInsert("40", "20", "20", "20", "c"));

		// rollback
		inputConfig(map1.get("chuyencan"), map1.get("kiemtra"), map1.get("baitap"), map1.get("thuchanh"),
				map1.get("diemthi"));
		WebElement btnSave2 = driver.findElement(By.id("Save"));
		btnSave2.click();
		Alert alert2 = driver.switchTo().alert();
		alert2.accept();
	}
	
	@Test(enabled = true)
	public void TestCase17_SetConfigWithInvalid() {
		// Mở localhost
		driver.get("http://localhost:8080/subjectConfig/4");
		// Click button cấu hình
		WebElement btn = driver.findElement(By.id("View"));
		btn.click();

		// Lay data trc khi test để rollback
		Map<String, String> map1 = new HashMap<String, String>();
		map1 = getDataConfig();

		
		inputConfig("40", "20", "20", "c", "20");
		// Click Button Lưu
		WebElement btnSave=driver.findElement(By.id("Save"));
		btnSave.click();
		

		Alert alert = driver.switchTo().alert();
		alert.accept();
		// Ktra
		driver.navigate().refresh();
		WebElement btn2 = driver.findElement(By.id("View"));
		btn2.click();
		assertFalse(checkDataInsert("40", "20", "20", "c", "20"));

		// rollback
		inputConfig(map1.get("chuyencan"), map1.get("kiemtra"), map1.get("baitap"), map1.get("thuchanh"),
				map1.get("diemthi"));
		WebElement btnSave2 = driver.findElement(By.id("Save"));
		btnSave2.click();
		Alert alert2 = driver.switchTo().alert();
		alert2.accept();
	}
	
	@Test(enabled = true)
	public void TestCase18_SetConfigWithInvalid() {
		// Mở localhost
		driver.get("http://localhost:8080/subjectConfig/4");
		// Click button cấu hình
		WebElement btn = driver.findElement(By.id("View"));
		btn.click();

		// Lay data trc khi test để rollback
		Map<String, String> map1 = new HashMap<String, String>();
		map1 = getDataConfig();

		
		inputConfig("40", "20", "c", "20", "20");
		// Click Button Lưu
		WebElement btnSave=driver.findElement(By.id("Save"));
		btnSave.click();
		

		Alert alert = driver.switchTo().alert();
		alert.accept();
		// Ktra
		driver.navigate().refresh();
		WebElement btn2 = driver.findElement(By.id("View"));
		btn2.click();
		assertFalse(checkDataInsert("40", "20", "c", "20", "20"));

		// rollback
		inputConfig(map1.get("chuyencan"), map1.get("kiemtra"), map1.get("baitap"), map1.get("thuchanh"),
				map1.get("diemthi"));
		WebElement btnSave2 = driver.findElement(By.id("Save"));
		btnSave2.click();
		Alert alert2 = driver.switchTo().alert();
		alert2.accept();
	}
	@Test(enabled = true)
	public void TestCase19_SetConfigWithInvalid() {
		// Mở localhost
		driver.get("http://localhost:8080/subjectConfig/4");
		// Click button cấu hình
		WebElement btn = driver.findElement(By.id("View"));
		btn.click();

		// Lay data trc khi test để rollback
		Map<String, String> map1 = new HashMap<String, String>();
		map1 = getDataConfig();

		
		inputConfig("40", "c", "20", "20", "20");
		// Click Button Lưu
		WebElement btnSave=driver.findElement(By.id("Save"));
		btnSave.click();
		

		Alert alert = driver.switchTo().alert();
		alert.accept();
		// Ktra
		driver.navigate().refresh();
		WebElement btn2 = driver.findElement(By.id("View"));
		btn2.click();
		assertFalse(checkDataInsert("40", "c", "20", "20", "20"));

		// rollback
		inputConfig(map1.get("chuyencan"), map1.get("kiemtra"), map1.get("baitap"), map1.get("thuchanh"),
				map1.get("diemthi"));
		WebElement btnSave2 = driver.findElement(By.id("Save"));
		btnSave2.click();
		Alert alert2 = driver.switchTo().alert();
		alert2.accept();
	}
	@Test(enabled = true)
	public void TestCase20_SetConfigWithInvalid() {
		// Mở localhost
		driver.get("http://localhost:8080/subjectConfig/4");
		// Click button cấu hình
		WebElement btn = driver.findElement(By.id("View"));
		btn.click();

		// Lay data trc khi test để rollback
		Map<String, String> map1 = new HashMap<String, String>();
		map1 = getDataConfig();

		
		inputConfig("c", "40", "20", "20", "20");
		// Click Button Lưu
		WebElement btnSave=driver.findElement(By.id("Save"));
		btnSave.click();
		

		Alert alert = driver.switchTo().alert();
		alert.accept();
		// Ktra
		driver.navigate().refresh();
		WebElement btn2 = driver.findElement(By.id("View"));
		btn2.click();
		assertFalse(checkDataInsert("c", "40", "20", "20", "20"));

		// rollback
		inputConfig(map1.get("chuyencan"), map1.get("kiemtra"), map1.get("baitap"), map1.get("thuchanh"),
				map1.get("diemthi"));
		WebElement btnSave2 = driver.findElement(By.id("Save"));
		btnSave2.click();
		Alert alert2 = driver.switchTo().alert();
		alert2.accept();
	}
	//test case với  trường dạng float 
	
	//diemthicuoiky= float
	@Test(enabled = true)
	public void TestCase21_SetConfigWithInvalid() {
		// Mở localhost
		driver.get("http://localhost:8080/subjectConfig/4");
		// Click button cấu hình
		WebElement btn = driver.findElement(By.id("View"));
		btn.click();

		// Lay data trc khi test để rollback
		Map<String, String> map1 = new HashMap<String, String>();
		map1 = getDataConfig();

		
		inputConfig("40", "20", "20", "20", "20.0");
		// Click Button Lưu
		WebElement btnSave=driver.findElement(By.id("Save"));
		btnSave.click();
		

		Alert alert = driver.switchTo().alert();
		alert.accept();
		// Ktra
		driver.navigate().refresh();
		WebElement btn2 = driver.findElement(By.id("View"));
		btn2.click();
		assertFalse(checkDataInsert("40", "20", "20", "20", "20.0"));

		// rollback
		inputConfig(map1.get("chuyencan"), map1.get("kiemtra"), map1.get("baitap"), map1.get("thuchanh"),
				map1.get("diemthi"));
		WebElement btnSave2 = driver.findElement(By.id("Save"));
		btnSave2.click();
		Alert alert2 = driver.switchTo().alert();
		alert2.accept();
	}
	//Diem bai tap = float
	@Test(enabled = true)
	public void TestCase22_SetConfigWithInvalid() {
		// Mở localhost
		driver.get("http://localhost:8080/subjectConfig/4");
		// Click button cấu hình
		WebElement btn = driver.findElement(By.id("View"));
		btn.click();

		// Lay data trc khi test để rollback
		Map<String, String> map1 = new HashMap<String, String>();
		map1 = getDataConfig();

		
		inputConfig("40", "20", "20", "20.0", "20");
		// Click Button Lưu
		WebElement btnSave=driver.findElement(By.id("Save"));
		btnSave.click();
		

		Alert alert = driver.switchTo().alert();
		alert.accept();
		// Ktra
		driver.navigate().refresh();
		WebElement btn2 = driver.findElement(By.id("View"));
		btn2.click();
		assertFalse(checkDataInsert("40", "20", "20", "20.0", "20"));

		// rollback
		inputConfig(map1.get("chuyencan"), map1.get("kiemtra"), map1.get("baitap"), map1.get("thuchanh"),
				map1.get("diemthi"));
		WebElement btnSave2 = driver.findElement(By.id("Save"));
		btnSave2.click();
		Alert alert2 = driver.switchTo().alert();
		alert2.accept();
	}
	//Diem thuc hanh = float
	@Test(enabled = true)
	public void TestCase23_SetConfigWithInvalid() {
		// Mở localhost
		driver.get("http://localhost:8080/subjectConfig/4");
		// Click button cấu hình
		WebElement btn = driver.findElement(By.id("View"));
		btn.click();

		// Lay data trc khi test để rollback
		Map<String, String> map1 = new HashMap<String, String>();
		map1 = getDataConfig();

		
		inputConfig("40", "20", "20.0", "20", "20");
		// Click Button Lưu
		WebElement btnSave=driver.findElement(By.id("Save"));
		btnSave.click();
		

		Alert alert = driver.switchTo().alert();
		alert.accept();
		// Ktra
		driver.navigate().refresh();
		WebElement btn2 = driver.findElement(By.id("View"));
		btn2.click();
		assertFalse(checkDataInsert("40", "20", "20,0", "20", "20"));

		// rollback
		inputConfig(map1.get("chuyencan"), map1.get("kiemtra"), map1.get("baitap"), map1.get("thuchanh"),
				map1.get("diemthi"));
		WebElement btnSave2 = driver.findElement(By.id("Save"));
		btnSave2.click();
		Alert alert2 = driver.switchTo().alert();
		alert2.accept();
	}
	//diem kiem tra = float
	@Test(enabled = true)
	public void TestCase24_SetConfigWithInvalid() {
		// Mở localhost
		driver.get("http://localhost:8080/subjectConfig/4");
		// Click button cấu hình
		WebElement btn = driver.findElement(By.id("View"));
		btn.click();

		// Lay data trc khi test để rollback
		Map<String, String> map1 = new HashMap<String, String>();
		map1 = getDataConfig();

		
		inputConfig("40", "20.0", "20", "20", "20");
		// Click Button Lưu
		WebElement btnSave=driver.findElement(By.id("Save"));
		btnSave.click();
		

		Alert alert = driver.switchTo().alert();
		alert.accept();
		// Ktra
		driver.navigate().refresh();
		WebElement btn2 = driver.findElement(By.id("View"));
		btn2.click();
		assertFalse(checkDataInsert("40", "20.0", "20", "20", "20"));

		// rollback
		inputConfig(map1.get("chuyencan"), map1.get("kiemtra"), map1.get("baitap"), map1.get("thuchanh"),
				map1.get("diemthi"));
		WebElement btnSave2 = driver.findElement(By.id("Save"));
		btnSave2.click();
		Alert alert2 = driver.switchTo().alert();
		alert2.accept();
	}
	
	//diem chuyen can = float
	@Test(enabled = true)
	public void TestCase25_SetConfigWithInvalid() {
		// Mở localhost
		driver.get("http://localhost:8080/subjectConfig/4");
		// Click button cấu hình
		WebElement btn = driver.findElement(By.id("View"));
		btn.click();

		// Lay data trc khi test để rollback
		Map<String, String> map1 = new HashMap<String, String>();
		map1 = getDataConfig();

		
		inputConfig("40.0", "20", "20", "20", "20");
		// Click Button Lưu
		WebElement btnSave=driver.findElement(By.id("Save"));
		btnSave.click();
		

		Alert alert = driver.switchTo().alert();
		alert.accept();
		// Ktra
		driver.navigate().refresh();
		WebElement btn2 = driver.findElement(By.id("View"));
		btn2.click();
		assertFalse(checkDataInsert("40.0", "20", "20", "20", "20"));

		// rollback
		inputConfig(map1.get("chuyencan"), map1.get("kiemtra"), map1.get("baitap"), map1.get("thuchanh"),
				map1.get("diemthi"));
		WebElement btnSave2 = driver.findElement(By.id("Save"));
		btnSave2.click();
		Alert alert2 = driver.switchTo().alert();
		alert2.accept();
	}
	
	// Test case: Cấu hình với các trường Value
	@Test(enabled = true)
	public void TestCase26_SetConfigWithValid() {
		// Mở localhost
				driver.get("http://localhost:8080/subjectConfig/4");
				// Click button cấu hình
				WebElement btn = driver.findElement(By.id("View"));
				btn.click();

				// Lay data trc khi test để rollback
				Map<String, String> map1 = new HashMap<String, String>();
				map1 = getDataConfig();

				// set value cc=40, ktra=20, baitap=20, thuchanh= 20, diemthi=0 ---"biên"
				inputConfig("40", "20", "20", "20", "0");
				// Click Button Lưu
				WebElement btnSave=driver.findElement(By.id("Save"));
				btnSave.click();
				
				Alert alert = driver.switchTo().alert();
				alert.accept();
				// Ktra
				driver.navigate().refresh();
				WebElement btn2 = driver.findElement(By.id("View"));
				btn2.click();
				assertFalse(!checkDataInsert("40", "20", "20", "20", "0"));

				// rollback
				inputConfig(map1.get("chuyencan"), map1.get("kiemtra"), map1.get("baitap"), map1.get("thuchanh"),
						map1.get("diemthi"));
				WebElement btnSave2 = driver.findElement(By.id("Save"));
				btnSave2.click();
				Alert alert2 = driver.switchTo().alert();
				alert2.accept();
	}

	@Test(enabled = true)
	public void TestCase27_SetConfigWithValid() {
		// Mở localhost
		driver.get("http://localhost:8080/subjectConfig/4");
		// Click button cấu hình
		WebElement btn = driver.findElement(By.id("View"));
		btn.click();

		// Lay data trc khi test để rollback
		Map<String, String> map1 = new HashMap<String, String>();
		map1 = getDataConfig();
		// set value cc=0, ktra=0, baitap=0, thuchanh= 0, diemthi=100 ---"biên"
		inputConfig("0", "0", "0", "0", "100");
		// Click Button Lưu
		WebElement btnSave=driver.findElement(By.id("Save"));
		btnSave.click();
		
		Alert alert = driver.switchTo().alert();
		alert.accept();
		// Ktra
		driver.navigate().refresh();
		WebElement btn2 = driver.findElement(By.id("View"));
		btn2.click();
		assertFalse(!checkDataInsert("0", "0", "0", "0", "100"));

		// rollback
		inputConfig(map1.get("chuyencan"), map1.get("kiemtra"), map1.get("baitap"), map1.get("thuchanh"),
				map1.get("diemthi"));
		WebElement btnSave2 = driver.findElement(By.id("Save"));
		btnSave2.click();
		Alert alert2 = driver.switchTo().alert();
		alert2.accept();

	}

	public void inputConfig(String cc, String kt, String th, String bt, String dt) {
		// lấy Input các thành phần cấu hình
		WebElement chuyencan = driver.findElement(By.name("attendance"));
		WebElement ktra = driver.findElement(By.name("test"));
		WebElement thuchanh = driver.findElement(By.name("practice"));
		WebElement baitap = driver.findElement(By.name("exercise"));
		WebElement diemthi = driver.findElement(By.name("examFinal"));

		// Set data
		chuyencan.clear();
		ktra.clear();
		thuchanh.clear();
		baitap.clear();
		diemthi.clear();

		chuyencan.sendKeys(cc);
		ktra.sendKeys(kt);
		thuchanh.sendKeys(th);
		baitap.sendKeys(bt);
		diemthi.sendKeys(dt);

	}

	public Map<String, String> getDataConfig() {
		Map<String, String> map = new HashMap<String, String>();

		// lấy Input các thành phần cấu hình
		WebElement chuyencan = driver.findElement(By.name("attendance"));
		WebElement ktra = driver.findElement(By.name("test"));
		WebElement thuchanh = driver.findElement(By.name("practice"));
		WebElement baitap = driver.findElement(By.name("exercise"));
		WebElement diemthi = driver.findElement(By.name("examFinal"));

		// lấy data các thành phần cấu hình
		String cc;
		if (chuyencan.getAttribute("value") == null) {
			cc = "0";
		} else {
			cc = chuyencan.getAttribute("value");
		}

		String kt;
		if (ktra.getAttribute("value") == null) {
			kt = "0";
		} else {
			kt = ktra.getAttribute("value");
		}
		String th;
		if (thuchanh.getAttribute("value") == null) {
			th = "0";
		} else {
			th = thuchanh.getAttribute("value");

		}
		String bt;
		if (baitap.getAttribute("value") == null) {
			bt = "0";
		} else {
			bt = baitap.getAttribute("value");
		}
		String dt;
		if (diemthi.getAttribute("value") == null) {
			dt = "0";
		} else {
			dt = diemthi.getAttribute("value");
		}

		map.put("chuyencan", cc);
		map.put("kiemtra", kt);
		map.put("thuchanh", th);
		map.put("baitap", bt);
		map.put("diemthi", dt);
		return map;

	}

	public boolean checkDataInsert(String cc, String kt, String th, String bt, String dt) {
		Map<String, String> map3 = new HashMap<String, String>();
		map3 = getDataConfig();
		if (map3.get("chuyencan").equals(cc)||cc=="0") {
			if (map3.get("kiemtra").equals(kt)||kt=="0") {
				if (map3.get("thuchanh").equals(th)||th=="0") {
					if (map3.get("baitap").equals(bt)||bt=="0") {
						if (map3.get("diemthi").equals(dt)||dt=="0") {
							return true;
						}
					}
				}
			}
		}
		return false;
	}


	@AfterClass
	public void afterClass() {
		// Đóng trình duyệt
		driver.quit();
	}
}
