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


import com.kms.katalon.core.configuration.RunConfiguration

@RunWith(JUnit4.class)
public class ReportUtilDriverTest {

	static Path projectDir
	static Path fixtureDir
	static Path classOutputDir

	@BeforeClass
	static void beforeClass() {
		this.projectDir = Paths.get(RunConfiguration.getProjectDir())
		this.fixtureDir = projectDir.resolve("Include/fixtures")
		this.classOutputDir = this.projectDir.resolve("build/tmp/testOutput/ReportUtilDriverTest/")
		if (Files.exists(classOutputDir)) {
			FileUtils.deleteDirectory(classOutputDir.toFile())
		}
	}

	@Before
	void setup() {
	}

	@Test
	void test_findAncestorReportsDir() {
		Path inputReportsDir = this.fixtureDir.resolve("Reports")
		Path bunchDir = inputReportsDir.resolve(FixtureConstants.RELATIVE_PATH)
		Path actual = ReportUtilDriver.findAncestorReportsDir(bunchDir)
		assert actual == inputReportsDir
	}

	@Test
	void test_resolveOutputReportDir() {
		Path inputReportsDir = this.fixtureDir.resolve("Reports")
		Path inputBunchDir = inputReportsDir.resolve(FixtureConstants.RELATIVE_PATH)
		Path targetDir = this.classOutputDir.resolve("test_resolveOutputReportDir")
		Files.createDirectories(targetDir)
		Path outputReportDir = ReportUtilDriver.resolveOutputReportDir(inputReportsDir, inputBunchDir, targetDir)
		assert outputReportDir == targetDir.resolve(FixtureConstants.RELATIVE_PATH)
	}

	@Test
	void test_generateABunch() {
		Path inputReportsDir = this.fixtureDir.resolve("Reports")
		Path bunchDir = inputReportsDir.resolve(FixtureConstants.RELATIVE_PATH)
		Path targetDir = this.classOutputDir.resolve("test_generateABunch")
		Files.createDirectories(targetDir)
		ReportUtilDriver.generateABunch(bunchDir, targetDir)
		//
		Path html = targetDir.resolve(FixtureConstants.RELATIVE_PATH).resolve(FixtureConstants.HTML_FILENAME)
		assert Files.exists(html)
		//
		Path xml = targetDir.resolve(FixtureConstants.RELATIVE_PATH).resolve(FixtureConstants.XML_FILENAME)
		assert Files.exists(xml)
		//
	}

	@Test
	void test_generateBunches() {
		Path inputReportsDir = this.fixtureDir.resolve("Reports")
		Path targetDir = this.classOutputDir.resolve("test_generateBunches")
		Files.createDirectories(targetDir)
		ReportUtilDriver.generateBunches(inputReportsDir, targetDir)
		//
		Path html = targetDir.resolve(FixtureConstants.RELATIVE_PATH).resolve(FixtureConstants.HTML_FILENAME)
		assert Files.exists(html)
		//
		Path xml = targetDir.resolve(FixtureConstants.RELATIVE_PATH).resolve(FixtureConstants.XML_FILENAME)
		assert Files.exists(xml)
		//
	}
}
