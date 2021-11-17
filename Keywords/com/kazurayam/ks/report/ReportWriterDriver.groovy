package com.kazurayam.ks.report

import internal.GlobalVariable
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

public class ReportWriterDriver {

	Path projectDir
	
	ReportWriterDriver(Path projectDir) {
		this.projectDir = projectDir
	}
	
	Path getProjectDir() {
		return this.projectDir
	}
}
