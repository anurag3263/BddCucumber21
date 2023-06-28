package plugIn;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;
import net.masterthought.cucumber.json.support.Status;
import net.masterthought.cucumber.presentation.PresentationMode;

public class JvmReportingPlugin {
//	private static final String properties_File_PathName="system.properties";
	public static void generateJVMReport() throws Throwable {
		File reportOutputDirectory = new File("target");
		List<String> jsonFiles = new ArrayList<>();
		jsonFiles.add("target/Results/cucumber.json");
		//FileInputStream fis=new FileInputStream(properties_File_PathName);
//		 Properties prop= loadProperties(properties_File_PathName);
		// prop.load(fis);

		String buildNumber = "1";
		String projectName = "Anurag BDD Test";

		Configuration configuration = new Configuration(reportOutputDirectory, projectName);
		// optional configuration - check javadoc for details
		configuration.addPresentationModes(PresentationMode.RUN_WITH_JENKINS);
		// do not make scenario failed when step has status SKIPPED
		configuration.setNotFailingStatuses(Collections.singleton(Status.SKIPPED));
		configuration.setBuildNumber(buildNumber);
		// addidtional metadata presented on main page
		configuration.addClassifications("Platform", "API");
		//configuration.addClassifications("Execution_Environment", prop.getProperty("env"));
		configuration.addClassifications("Execution_Environment", "Git Action");
		configuration.addClassifications("Branch", "release/1.0");

		// optionally add metadata presented on main page via properties file
		/*
		 * List<String> classificationFiles = new ArrayList<>();
		 * classificationFiles.add("properties-1.properties");
		 * classificationFiles.add("properties-2.properties");
		 * configuration.addClassificationFiles(classificationFiles);
		 */

		// optionally specify qualifiers for each of the report json files
	//	        configuration.addPresentationModes(PresentationMode.PARALLEL_TESTING);
	//	        configuration.setQualifier("cucumber-report-1","First report");
	//	        configuration.setQualifier("cucumber-report-2","Second report");

		        ReportBuilder reportBuilder=new ReportBuilder(jsonFiles,configuration);
		        Reportable result=reportBuilder.generateReports();
		// and here validate 'result' to decide what to do if report has failed
	}

//	private static Properties loadProperties(String propertiesFilename) {
//		Properties prop = new Properties();
//
//		ClassLoader loader = ReportPortalCucumberPlugin.class.getClassLoader();
//
//		try (InputStream stream = loader.getResourceAsStream(propertiesFilename)) {
//			if (stream == null) {
//				throw new FileNotFoundException();
//			}
//			prop.load(stream);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return prop;
//	}
}