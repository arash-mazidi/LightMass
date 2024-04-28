package Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FindLineNumber {

	public static List<String> linelist = new ArrayList<String>();
	public static List<Integer> number = new ArrayList<Integer>();

	public static void findlinenumbers() {
		String jsoncontent = Parser2.JsonString;
		String[] lines = jsoncontent.split("\n");

		for (int lineNumber = 0; lineNumber < lines.length; lineNumber++) {
			String line = lines[lineNumber];

			linelist.add(line.trim());
			number.add((lineNumber + 1));

		}
		int xx=0;
	}
}
