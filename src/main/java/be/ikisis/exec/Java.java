package be.ikisis.exec;

import java.io.File;

/**
 *
 *
 * @author sangwookpark
 * @history
 *          Jul 7, 2020 initial creation
 */

public interface Java {

	void run(JavaFileUnit unit);

	public static class Pojo implements Java {

		private boolean compile(JavaFileUnit unit) {

			File target = new File(unit.getProjectHome().getAbsolutePath() + "/target");

			if (!target.exists()) {
				target.mkdir();
			}

			int exitValue = ProcessCaller.exec(
			        "javac -sourcepath " + unit.getSourceRoot() + " -d " + target.getAbsolutePath() + " "
			                + unit.getJavaFile().getAbsolutePath());

			return exitValue == 0 ? true : false;

		}

		@Override
		public void run(JavaFileUnit unit) {
			if (compile(unit)) {

				ProcessCaller.exec(
				        "java -classpath " +
				                new File(unit.getProjectHome().getAbsolutePath() + "/target").getAbsolutePath()
				                + " " +
				                unit.getClassName());

			}
		}
	}

}
