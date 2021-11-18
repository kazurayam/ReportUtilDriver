package com.kazurayam.ks.report

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import com.kms.katalon.core.configuration.RunConfiguration

@RunWith(JUnit4.class)
public class ReportWriterDriverTest {

	private Path projectDir
	private Path fixtureDir
	
	private static String RELATIVE_PATH = "20211117_210214/main/TS1/20211117_210214"
	private static String HTML_FILENAME = "20211117_210214.html"
	private static String XML_FILENAME  = "JUnit_Report.xml"
	
	@Before
	void setup() {
		this.projectDir = Paths.get(RunConfiguration.getProjectDir())
		this.fixtureDir = projectDir.resolve("Include/testFixture")
	}

	@Test
	void test_resolveOutputReportDir() {
		String relativePath = "20211117_210214/main/TS1/20211117_210214"
		Path inputReportsDir = this.fixtureDir.resolve("Reports")
		Path inputReportDir = inputReportsDir.resolve(relativePath)
		Path targetDir = this.projectDir.resolve("build/tmp/testOutput/ReportWriterDriverTest/test_resolveOutputReportDir")
		Files.createDirectories(targetDir)
		Path outputReportDir = ReportWriterDriver.resolveOutputReportDir(inputReportsDir, inputReportDir, targetDir)
		assert outputReportDir == targetDir.resolve(relativePath)
	}

	@Test
	void test_generateABunch() {
		Path inputReportsDir = this.fixtureDir.resolve("Reports")
		Path execution0log = inputReportsDir.resolve(RELATIVE_PATH).resolve("execution0.log")
		Path targetDir = this.projectDir.resolve("build/tmp/testOutput/ReportWriterDriverTest/test_generateABunch")
		Files.createDirectories(targetDir)
		ReportWriterDriver.generateABunch(execution0log, targetDir)
		//
		Path html = targetDir.resolve(RELATIVE_PATH).resolve(HTML_FILENAME)
		assert Files.exists(html)
		//
		Path xml = targetDir.resolve(RELATIVE_PATH).resolve(XML_FILENAME)
		assert Files.exists(xml)
		//
	}
	
	@Ignore
	@Test
	void test_generateBunches() {
		Path inputReportsDir = this.fixtureDir.resolve("Reports")
		Path targetDir = this.projectDir.resolve("build/tmp/testOutput/ReportWriterDriverTest/test_generateBunches")
		Files.createDirectories(targetDir)
		ReportWriterDriver.generateBunches(inputReportsDir, targetDir)
		//
		Path html = targetDir.resolve(RELATIVE_PATH).resolve(HTML_FILENAME)
		assert Files.exists(html)
		//
		Path xml = targetDir.resolve(RELATIVE_PATH).resolve(XML_FILENAME)
		assert Files.exists(xml)
		//
	}
}
