package com.kazurayam.ks.report

import java.nio.file.Path
import com.kms.katalon.core.logging.model.TestSuiteLogRecord
import com.kms.katalon.core.reporting.ReportWriterUtil

public class ReportWriterDriver {

	static void writeReports(Path execution0log, Path targetDir) {
		Objects.requireNonNull(execution0log)
		Objects.requireNonNull(targetDir)
		Path inputReportDir = execution0log.getParent()
		Path inputReportsDir = inputReportDir.getParent().getParent().getParent().getParent()
		TestSuiteLogRecord suiteLogEntry = ReportWriterUtil.generate(inputReportDir.toString())
		Path outputReportDir = resolveOutputReportDir(inputReportsDir, inputReportDir, targetDir)
		ReportWriterUtil.writeLogRecordToFiles(suiteLogEntry, outputReportDir.toFile())
	}

	static Path resolveOutputReportDir(Path inputReportsDir, Path inputReportDir, Path targetDir) {
		Path relativePath = inputReportsDir.relativize(inputReportDir)
		return targetDir.resolve(relativePath)
	}
}
