package com.kazurayam.ks.reporting

import java.nio.file.FileVisitResult
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.SimpleFileVisitor
import java.nio.file.attribute.BasicFileAttributes

import com.kms.katalon.core.logging.model.TestSuiteLogRecord

// until v8.1.x
// import com.kms.katalon.core.reporting.ReportWriterUtil

// since v8.2.0
import com.kms.katalon.core.reporting.ReportUtil


public class ReportUtilDriver {

	static void generateABunch(Path bunchDir, Path targetDir) {
		Objects.requireNonNull(bunchDir)
		assert Files.exists(bunchDir) : "${bunchDir} is not presennt"
		Objects.requireNonNull(targetDir)
		Files.createDirectories(targetDir)
		//
		Path inputBunchDir = bunchDir
		Path inputReportsDir = findAncestorReportsDir(inputBunchDir)
		TestSuiteLogRecord suiteLogEntry = ReportUtil.generate(inputBunchDir.toString())
		Path outputBunchDir = resolveOutputReportDir(inputReportsDir, inputBunchDir, targetDir)
		ReportUtil.writeLogRecordToFiles(suiteLogEntry, outputBunchDir.toFile())
	}

	static void generateBunches(Path reportsDir, Path targetDir) {
		Objects.requireNonNull(reportsDir)
		assert Files.exists(reportsDir) : "${reportsDir.toString()} is not present"
		Objects.requireNonNull(targetDir)
		//
		List<Path> bunchDirs = new ArrayList<Path>()
		Files.walkFileTree(reportsDir, new SimpleFileVisitor<Path>() {
					@Override
					FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
						if (file.getFileName().toString() == 'execution0.log') {
							bunchDirs.add(file.getParent())
						}
					}
				})
		//
		bunchDirs.each({ Path bunchDir ->
			generateABunch(bunchDir, targetDir)
		})
	}

	static Path resolveOutputReportDir(Path inputReportsDir, Path inputBunchDir, Path targetDir) {
		Path relativePath = inputReportsDir.relativize(inputBunchDir)
		return targetDir.resolve(relativePath)
	}

	static Path findAncestorReportsDir(Path bunchDir) {
		return bunchDir.getParent().getParent().getParent().getParent()
	}

	/**
	 * 
	 * @author kazurayam
	 */
	class Execution0logFinder extends SimpleFileVisitor<Path> {
		private List<Path> list;
		static String FILENAME = "execution0.log"

		Execution0logFinder() {
			list = new ArrayList<Path>()
		}



	}

}
