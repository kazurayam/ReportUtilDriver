import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.context.TestSuiteContext

class TL_listBunchDir {
	
	@AfterTestSuite
	def sampleAfterTestSuite(TestSuiteContext testSuiteContext) {
		Path projectDir = Paths.get(RunConfiguration.getProjectDir())
		Path bunchDir = Paths.get(RunConfiguration.getReportFolder())
		Files.list(bunchDir).sorted().each ({ Path p ->
			println projectDir.relativize(p).toString()	
		})
	}
}