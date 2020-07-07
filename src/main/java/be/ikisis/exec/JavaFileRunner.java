package be.ikisis.exec;

import lombok.val;

/**
 *
 *
 * @author sangwookpark
 * @history
 *          Jul 7, 2020 initial creation
 */
public class JavaFileRunner {

	public static void main(String[] args) {
//		String filename = "/Users/sangwookpark/dev/eclipses/BXI/Eclipse.app/workspace/PLayg/src/test/p1/p2/PPP.java"; // args[0];
		String filename =
		        "/Users/sangwookpark/dev/gits/java-cmd-runner/src/main/java/be/ikisis/exec/TEST.java"; // args[0];

		val unit = JavaFileUnit.of(filename);

		unit.run();
	}

}
