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

		val unit = JavaFileUnit.of(args[0]);

		unit.run();
	}

}
