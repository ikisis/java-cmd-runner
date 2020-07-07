package be.ikisis.exec;

/**
 *
 *
 * @author sangwookpark
 * @history
 *          Jul 7, 2020 initial creation
 */
public class Maven implements Java {

	@Override
	public void run(JavaFileUnit unit) {

		ProcessCaller.exec(
		        "mvn -f " + unit.getProjectHome().getAbsolutePath() + "/pom.xml"
		                + " exec:java -Dexec.mainClass="
		                + unit.getClassName());

	}

}
