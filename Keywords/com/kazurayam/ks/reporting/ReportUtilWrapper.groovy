package com.kazurayam.ks.reporting

import com.kms.katalon.core.logging.model.TestSuiteLogRecord
import com.kms.katalon.core.reporting.ReportUtil
import java.nio.file.Files
import java.lang.reflect.Method

public class ReportUtilWrapper {

	// since Katalon Studio 8.2.0
	static final String FQCN_ReportUtil = 'com.kms.katalon.core.reporting.ReportUtil'
	
	static Class engine = null
	
	static {
		if (isReportUtilClassFound()) {
			engine = getClassOfReportUtil()
		} else {
			throw new IllegalStateException("Unable to load Class " + FQCN_ReportUtil + " or its equivalents.")
		}
	}
	
	static TestSuiteLogRecord generate(String bunchDir) {
		Method m = engine.getDeclaredMethod("generate", String.class)
		TestSuiteLogRecord result = m.invoke(null, bunchDir)
		return result
	}

	static void writeLogRecordToFiles(TestSuiteLogRecord suiteLogEntry, File bunchDir) {
		Files.createDirectories(bunchDir.toPath())
		Method m = engine.getDeclaredMethod("writeLogRecordToFiles", TestSuiteLogRecord.class, File.class)
		m.invoke(null, suiteLogEntry, bunchDir)
	}
	
	static boolean isReportUtilClassFound() {
		try {
			Class clazz = Class.forName(FQCN_ReportUtil)
			return true
		} catch (ClassNotFoundException e) {
			return false
		}
	}
	
	static Class getClassOfReportUtil() {
		return Class.forName(FQCN_ReportUtil)
	}

}
