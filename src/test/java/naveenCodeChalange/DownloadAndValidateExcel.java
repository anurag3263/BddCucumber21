package naveenCodeChalange;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.*;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.apache.poi.ss.usermodel.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DownloadAndValidateExcel {
	static WebDriver driver;

	public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
		// Set download directory
        String downloadDir = System.getProperty("user.home") + "/Downloads";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadDir);
        prefs.put("download.prompt_for_download", false);
        options.setExperimentalOption("prefs", prefs);
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://fragilestatesindex.org/excel/");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		System.out.println("Title Of page : " + driver.getTitle());

		// Click the download link (update selector if needed)
		driver.findElement(By.xpath("//*[@id='rt-mainbody']//a//b[text()='2023']/../..")).click();

		// Wait for the file to appear (simple polling)
		String fileName = "FSI-2023-DOWNLOAD.xlsx"; // Update if file name changes
		File file = new File(downloadDir + "/" + fileName);
		int waitSeconds = 30;
		while (waitSeconds-- > 0 && !file.exists()) {
			Thread.sleep(1000);
		}

		if (file.exists()) {
			System.out.println("File downloaded successfully: " + file.getAbsolutePath());
			// Validate content using Apache POI
			try (FileInputStream fis = new FileInputStream(file)) {
				Workbook workbook = WorkbookFactory.create(fis);
				Sheet sheet = workbook.getSheetAt(0);
				Row row = sheet.getRow(0);
				Cell cell = row.getCell(0);
				System.out.println("First cell value: " + cell.getStringCellValue());
				// Add more validation as needed
				workbook.close();
			}
		} else {
			System.out.println("File was not downloaded!");
		}

		driver.quit();
	}

}
