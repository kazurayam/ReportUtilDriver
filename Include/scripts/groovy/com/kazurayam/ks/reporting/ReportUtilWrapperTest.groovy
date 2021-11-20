package com.kazurayam.ks.reporting

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import org.junit.Before
import org.junit.BeforeClass
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.apache.commons.io.FileUtils

import com.kms.katalon.core.logging.model.TestSuiteLogRecord

import com.kms.katalon.core.configuration.RunConfiguration

@RunWith(JUnit4.class)
public class ReportUtilWrapperTest {

	static Path projectDir
	static Path fixtureDir
	static Path classOutputDir

	@BeforeClass
	static void beforeClass() {
		this.projectDir = Paths.get(RunConfiguration.getProjectDir())
		this.fixtureDir = projectDir.resolve("Include/fixtures")
		this.classOutputDir = this.projectDir.resolve("build/tmp/testOutput/ReportUtilWrapperTest/")
		if (Files.exists(classOutputDir)) {
			FileUtils.deleteDirectory(classOutputDir.toFile())
		}
		Files.createDirectories(classOutputDir)
	}

	@Test
	void test_generate() {
		Path inputReportsDir = this.fixtureDir.resolve("Reports")
		Path bunchDir = inputReportsDir.resolve(FixtureConstants.RELATIVE_PATH)
		TestSuiteLogRecord suiteLogEntry = ReportUtilWrapper.generate(bunchDir.toString())
		assert suiteLogEntry != null
	}

	@Test
	void test_writeLogRecordToFiles() {
		Path inputReportsDir = this.fixtureDir.resolve("Reports")
		Path inputBunchDir = inputReportsDir.resolve(FixtureConstants.RELATIVE_PATH)
		Path bunchDir = inputReportsDir.resolve(FixtureConstants.RELATIVE_PATH)
		TestSuiteLogRecord suiteLogEntry = ReportUtilWrapper.generate(bunchDir.toString())
		Path targetDir = this.classOutputDir.resolve("test_writeLogRecordToFiles")
		Path outputBunchDir = ReportUtilDriver.resolveOutputReportDir(inputReportsDir, inputBunchDir, targetDir)
		ReportUtilWrapper.writeLogRecordToFiles(suiteLogEntry, outputBunchDir.toFile())
		//
		Path html = targetDir.resolve(FixtureConstants.RELATIVE_PATH).resolve(FixtureConstants.HTML_FILENAME)
		assert Files.exists(html)
	}

	@Ignore
	@Test
	void test_isReportUtilClassFound() {
		assert ReportUtilWrapper.isReportUtilClassFound()
	}
	
	@Test
	void test_isBasicReportWriterUtilClassFound() {
		assert ReportUtilWrapper.isBasicReportWriterUtilClassFound()
	}
}
