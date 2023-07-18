package naveenCodeChalange;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DynamicTableCheckBox {
	
	static WebDriver driver;

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://testuserautomation.github.io/WebTable/");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		System.out.println("Title Of page : " + driver.getTitle());
		
		String name= "Will";
		
		WebElement checkbox= driver.findElement(By.xpath("//table//td[text()='"+name+"']/..//input[@type='checkbox']"));
		
		List<WebElement> rows= driver.findElements(By.xpath("//table//tr"));
		
		List<WebElement> cellValue=driver.findElements(By.xpath("//table//tr/td"));
		
		System.out.println("Number of rows : "+rows.size());
		
		for(WebElement element : cellValue) {
			System.out.println("Text : "+element.getText());
		}
//		checkbox.click();
		
		
		List<WebElement> ele=driver.findElements(By.xpath("//table//tr/td[2]"));
		
		for(int i=0 ; i<ele.size(); i++){
			
			String FirstName=ele.get(i).getText();
			System.out.println("FirstName : "+FirstName);
			
			if(FirstName.contains(name)) {
				int j=i+2;
				WebElement checkBox=driver.findElement(By.xpath("//tbody//tr["+j+"]/td[1]/input"));
				checkBox.click();
				
			}
		}

	}

}
