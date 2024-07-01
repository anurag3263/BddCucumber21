package streamsApiFunction;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JavaStreams2 {
	static WebDriver driver;

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.iplt20.com/");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		System.out.println("Title Of page : " + driver.getTitle());
		driver.findElement(By.xpath("//*[@id='header-main']/descendant::nav/descendant::a[contains(.,'POINTS')]"))
				.click();
		List<WebElement> teamsName = driver.findElements(By.xpath("//*[@id='pointtable']/descendant::h2"));
		List<WebElement> points = driver
				.findElements(By.xpath("//*[@id='pointtable']/descendant::td[@class='bt ng-binding']"));

		// If you want to sort based on key

//		Map<String, String> map = new HashMap<>();
//		for (int i = 0; i < points.size(); i++) {
//			map.put(teamsName.get(i).getText(), points.get(i).getText());
//		}
//
//		map.entrySet().stream().sorted(Map.Entry.comparingByKey())
//				.forEach(e -> System.out.println(e.getKey() + " --> " + e.getValue()));
//		driver.quit();

		// If you want to sort based on value

//		Map<String, Double> map1 = new HashMap<>();
//		for (int i = 0; i < points.size(); i++) {
//			map1.put(teamsName.get(i).getText(), Double.parseDouble(points.get(i).getText()));
//		}
//
//		map1.entrySet().stream().sorted(Map.Entry.<String, Double>comparingByValue().reversed())
//				.forEach(e -> System.out.print(e.getKey() + " --> " + e.getValue() + "\n"));
		Map<Double, List<String>> pointsToTeamsMap = new TreeMap<>(Collections.reverseOrder());

		// Populate the map with points as keys and list of teams as values
		for (int i = 0; i < points.size(); i++) {
			String teamName = teamsName.get(i).getText();
			String pointsValue = points.get(i).getText();
			Double pointsDouble = Double.parseDouble(pointsValue);

			if (!pointsToTeamsMap.containsKey(pointsDouble)) {
				pointsToTeamsMap.put(pointsDouble, new ArrayList<>());
			}
			pointsToTeamsMap.get(pointsDouble).add(teamName);
		}

		// Iterate through the map and print the formatted output
//		KKR --> 20.0
//		SRH, RR --> 17.0
//		RCB, CSK, DC, LSG --> 14.0
//		GT --> 12.0
//		PBKS --> 10.0
//		MI --> 8.0
		pointsToTeamsMap.forEach((pointsDouble, teams) -> {
			String teamsList = String.join(", ", teams);
			System.out.println(teamsList + " --> " + pointsDouble);
		});
		driver.quit();

	}

}
