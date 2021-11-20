package com.kazurayam.ks.reporting

import com.kms.katalon.core.logging.model.TestSuiteLogRecord
import com.kms.katalon.core.reporting.ReportUtil
import java.nio.file.Files

public class ReportUtilWrapper {

	static TestSuiteLogRecord generate(String bunchDir) {
		return ReportUtil.generate(bunchDir)
	}

	static void writeLogRecordToFiles(TestSuiteLogRecord suiteLogEntry, File bunchDir) {
		Files.createDirectories(bunchDir.toPath())
		ReportUtil.writeLogRecordToFiles(suiteLogEntry, bunchDir)
	}
}
