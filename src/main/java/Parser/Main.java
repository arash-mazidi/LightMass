package Parser;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import com.opencsv.exceptions.CsvException;

public class Main {

	public static void main(String[] args) throws IOException, CsvException {

		long startTime = System.currentTimeMillis();
		
		RemoveRef rm = new RemoveRef();
		rm.convert();

		System.out.println("wait ...");
		int second = 0;
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		Parser2 objparser = new Parser2();
		objparser.parserfunction();

		FindReadOnlyFields.mainfunction();
		FindLineNumber.findlinenumbers();
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				OpenAPIViewer gui = new OpenAPIViewer();
				gui.displayOpenAPIFileContent();
			}
		});
			
		long EndTime = System.currentTimeMillis();
		long processTime = EndTime - startTime;
		double time = processTime / 1000.0;
		System.out.println("\nNumber of Mass Assignment Candidates: "+FindReadOnlyFields.numberofMasscandidate);
		System.out.println("\n"+time);
		System.out.println("\nDone!");
	}

}