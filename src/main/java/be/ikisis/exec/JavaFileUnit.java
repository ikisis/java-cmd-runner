package be.ikisis.exec;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import be.ikisis.exec.Java.Pojo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.ToString;

/**
 *
 *
 * @author sangwookpark
 * @history
 *          Jul 7, 2020 initial creation
 */

@Getter
@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class JavaFileUnit {

	@RequiredArgsConstructor
	static enum PrjType {
		POJO(new Pojo()),
		MAVEN(new Maven());

		final Java java;

	}

	private final File javaFile;

	private String className;

	private String packagePath = "";

	private String sourceRoot;

	private File projectHome;

	private PrjType projectType = PrjType.POJO;

	public void run() {
		this.getProjectType().java.run(this);
	}

	@SneakyThrows
	public static JavaFileUnit of(String filename) {

		return new JavaFileUnit(new File(filename)).make();
	}

	private JavaFileUnit make() throws FileNotFoundException, IOException {

		try (BufferedReader br = new BufferedReader(new FileReader(javaFile))) {
			String firstLine = br.readLine();

			if (firstLine.contains("package ")) {
				packagePath = firstLine
				        .split("[ ]")[1]
				                .replace(".", System.getProperty("file.separator"))
				                .replace(";", "");
			}
		}

		className = packagePath.replace(System.getProperty("file.separator"), ".") + "."
		        + javaFile.getName().replace(".java", "");

		sourceRoot = javaFile.getParent().replace(packagePath, "");

		projectHome = new File(sourceRoot);

		while (projectHome.getName().equals("src") ||
		        projectHome.getName().equals("main") ||
		        projectHome.getName().equals("java")) {
			projectHome = projectHome.getParentFile();
		}

		if (new File(projectHome.getAbsolutePath() + "/pom.xml").exists()) {
			projectType = PrjType.MAVEN;
		}

		return this;
	}

}
