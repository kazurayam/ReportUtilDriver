import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.context.TestSuiteContext
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import com.kms.katalon.core.configuration.RunConfiguration
import com.kazurayam.ks.reporting.ReportUtilDriver

class TL_generateReports {
	
	@AfterTestSuite
	def sampleAfterTestSuite(TestSuiteContext testSuiteContext) {
		//println testSuiteContext.getTestSuiteId()
		//println testSuiteContext.getStatus()
		if (testSuiteContext.getTestSuiteId().endsWith("TS1") &&
			testSuiteContext.getStatus() == "COMPLETE") {
			// identify the Reports dir as input
			Path reportsDir = Paths.get(RunConfiguration.getProjectDir()).resolve("Reports")
			// locate the output directory
			Path userHome = Paths.get(System.getProperty("user.home"))
			Path targetDir = userHome.resolve("tmp/katalon-reports")
			Files.createDirectories(targetDir)
			// now we generate the reports into the target directory
			ReportUtilDriver.generateBunches(reportsDir, targetDir)
		}
	}
}