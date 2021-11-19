package com.kazurayam.ks.report

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


public class ReportWriterDriver {

	static void generateABunch(Path execution0log, Path targetDir) {
		Objects.requireNonNull(execution0log)
		assert Files.exists(execution0log) : "${execution0log} is not presennt"
		Objects.requireNonNull(targetDir)
		Files.createDirectories(targetDir)
		//
		Path inputBunchDir = execution0log.getParent()
		Path inputReportsDir = findAncestorReportsDir(execution0log)
		TestSuiteLogRecord suiteLogEntry = ReportUtil.generate(inputBunchDir.toString())
		Path outputBunchDir = resolveOutputReportDir(inputReportsDir, inputBunchDir, targetDir)
		ReportUtil.writeLogRecordToFiles(suiteLogEntry, outputBunchDir.toFile())
	}

	static void generateBunches(Path reportsDir, Path targetDir) {
		Objects.requireNonNull(reportsDir)
		assert Files.exists(reportsDir) : "${reportsDir.toString()} is not present"
		Objects.requireNonNull(targetDir)
		//
		List<Path> execution0logFiles = new ArrayList<Path>()
		Files.walkFileTree(reportsDir, new SimpleFileVisitor<Path>() {
					@Override
					FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
						if (file.getFileName().toString() == 'execution0.log') {
							execution0logFiles.add(file)
						}
					}
				})
		//
		execution0logFiles.each({ Path execution0log ->
			generateABunch(execution0log, targetDir)
		})
	}

	static Path resolveOutputReportDir(Path inputReportsDir, Path inputReportDir, Path targetDir) {
		Path relativePath = inputReportsDir.relativize(inputReportDir)
		return targetDir.resolve(relativePath)
	}

	static Path findAncestorReportsDir(Path execution0log) {
		return execution0log.getParent().getParent().getParent().getParent().getParent()
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
