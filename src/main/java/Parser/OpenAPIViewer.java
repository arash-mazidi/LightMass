package Parser;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.impl.CreatorCandidate;

public class OpenAPIViewer {
	private JTextArea textArea;

	public List<Integer> highlightpath = new ArrayList<Integer>();
	public List<Integer> highlightoperation = new ArrayList<Integer>();
	private Highlighter highlighter;

	public void displayOpenAPIFileContent() {
		// Create the GUI components
		highlightpath.clear();
		JFrame frame = new JFrame("OpenAPI JSON Content");
		textArea = new JTextArea();
		highlighter = textArea.getHighlighter();
		JScrollPane scrollPane = new JScrollPane(textArea);

		// Set the dimensions of the frame
		frame.setSize(1300, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create a button to load the content
		JButton loadButton = new JButton("Load Content");
		loadButton.addActionListener(new LoadButtonListener());

		// Add the button and the scrollable text area to the frame
		frame.add(loadButton, BorderLayout.NORTH);
		frame.add(scrollPane, BorderLayout.CENTER);

		// Create a button to load the content
		JButton MassButton = new JButton("MassA Candidates");
		MassButton.addActionListener(new MassAButtonListener());

		// Add the button and the scrollable text area to the frame
		frame.add(MassButton, BorderLayout.SOUTH);
		frame.add(scrollPane, BorderLayout.CENTER);

		// Make the frame visible
		frame.setVisible(true);
	}

	private class LoadButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// Read and set the content of the JSON file to the text area

			// String content =
			// readFileContent("C:\\Users\\am57\\eclipse-workspace\\OpenAPIParser\\resource\\vampi.json");

			ObjectMapper mapper = new ObjectMapper();
			String jsonString = null;
			try {
				jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(RemoveRef.originAPI);
			} catch (IOException ee) {
				ee.printStackTrace();
			}
			textArea.setText(jsonString);
			// Highlight line 3 (line numbers are 1-based)

			findnumbers();
			for (int l = 0; l < highlightpath.size(); l++) {
				highlightLine(highlightpath.get(l),"yellow");
				highlightLine(highlightoperation.get(l),"red");
			}

		}
	}

	//////////////////////////////////////////////////////////////////
	// Display MassA candidate
	private class MassAButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			ObjectMapper mapper = new ObjectMapper();
			String jsonString = null;
			try {
				jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(RemoveRef.originAPI);
			} catch (IOException ee) {
				ee.printStackTrace();
			}
			textArea.setText(FindReadOnlyFields.outputMessage);
		}
	}

	private JsonNode readFileContent(String filePath) throws IOException {
//        byte[] encodedBytes = Files.readAllBytes(Paths.get(filePath));
		// return new String(encodedBytes);
		ObjectMapper mapper = new ObjectMapper();
		byte[] encodedBytes = Files.readAllBytes(Paths.get(filePath));
		return mapper.readTree(encodedBytes);
	}

	private void highlightLine(int lineNumber, String highlightcolor) {
		try {

			int startOffset = textArea.getLineStartOffset(lineNumber - 1);
			int endOffset = textArea.getLineEndOffset(lineNumber - 1);
			Highlighter.HighlightPainter painter;
			if (highlightcolor.equals("yellow"))
				painter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
			else
				painter = new DefaultHighlighter.DefaultHighlightPainter(Color.RED);
			highlighter.addHighlight(startOffset, endOffset, painter);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	public void findnumbers() {
		
		for (int h = 0; h < FindReadOnlyFields.candidatepath.size(); h++) {
			for (int f = 0; f < FindLineNumber.linelist.size(); f++) {
				String linetext = FindLineNumber.linelist.get(f);
				
				int firstQuotePosition = linetext.indexOf("\"");
				int secondQuotePosition = linetext.indexOf("\"", firstQuotePosition + 1);

				// Extract the text between the double quotation marks
				if (!(firstQuotePosition == -1 || secondQuotePosition == -1)) {
					String extractedText = linetext.substring(firstQuotePosition + 1, secondQuotePosition);

					if (extractedText.equals(FindReadOnlyFields.candidatepath.get(h))) {
						highlightpath.add(f + 1);
						
						for (int ff = f+1; ff < FindLineNumber.linelist.size(); ff++) {
							String linetext2 = FindLineNumber.linelist.get(ff);
							
							int firstQuotePosition2 = linetext2.indexOf("\"");
							int secondQuotePosition2 = linetext2.indexOf("\"", firstQuotePosition2 + 1);

							// Extract the text between the double quotation marks
							if (!(firstQuotePosition2 == -1 || secondQuotePosition2 == -1)) {
								String extractedText2 = linetext2.substring(firstQuotePosition2 + 1, secondQuotePosition2);

								if (extractedText2.equals(FindReadOnlyFields.candidateoperation.get(h))) {
									highlightoperation.add(ff + 1);
									break;
								}
							}
						}
					}
				}
			}
		}
	}

}
