package be.ikisis.exec;

import java.io.InputStream;
import java.util.Arrays;

import lombok.SneakyThrows;

/**
 *
 *
 * @author sangwookpark
 * @history
 *          Jul 7, 2020 initial creation
 */
public class ProcessCaller {

	@SneakyThrows
	public static int exec(String cmd) {

		System.out.println("command : " + cmd);

		Process process = null;

		Arrays.stream(cmd.split("[ ]")).forEach(s -> {
			System.out.println(s);
		});

		process = Runtime.getRuntime().exec(cmd.split("[ ]"));

		process.waitFor();

		int er = -1;

		try (InputStream eis = process.getErrorStream()) {
			while ((er = eis.read()) != -1) {
				System.out.write(er);
			}
		}

		try (InputStream is = process.getInputStream()) {
			while ((er = is.read()) != -1) {
				System.out.write(er);
			}
		}

		return process.exitValue();

	}

}
