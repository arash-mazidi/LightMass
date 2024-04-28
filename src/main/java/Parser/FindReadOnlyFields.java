package Parser;

import java.io.IOException;
import java.util.*;

public class FindReadOnlyFields {

	public static List<String> candidatepath = new ArrayList<String>();
	public static List<String> candidateoperation = new ArrayList<String>();
	public static List<List<String>> candidateattributes = new ArrayList<List<String>>();
	public static String outputMessage = "";
	public static int numberofMasscandidate = 0;

	public static void mainfunction() {
		int threshold = Integer.parseInt(RemoveRef.threshold);
		candidatepath.clear();
		candidateoperation.clear();
		candidateattributes.clear();
		numberofMasscandidate = 0;
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		List<Double> similaritylist = new ArrayList<Double>();

		for (int i = 0; i < Parser2.listOfreqBody.size(); i++) {

			list1.clear();

			for (int j = 0; j < Parser2.listOfreqBody.get(i).size(); j++)
				if (Parser2.listOfreqBody.get(i).get(j)[0] == null
						|| (!(Parser2.listOfreqBody.get(i).get(j)[0].equals("id"))
								&& !(Parser2.listOfreqBody.get(i).get(j)[0].endsWith("_id"))
								&& !(Parser2.listOfreqBody.get(i).get(j)[0].contains("token"))
								&& !(Parser2.listOfreqBody.get(i).get(j)[0].contains("Token"))))
					list1.add(Parser2.listOfreqBody.get(i).get(j)[0]);

			List<Integer> statuscodelist = new ArrayList<Integer>();

			for (int k = 0; k < Parser2.listOfresBody.size(); k++) {

				statuscodelist.clear();

				// Count the number of attributes with one response status code
				int count = 1;
				for (int h = 1; h < Parser2.listOfresBody.get(k).size(); h++) {
					if (Parser2.listOfresBody.get(k).get(h)[0] == Parser2.listOfresBody.get(k).get(h - 1)[0]) {
						count++;
					} else {
						statuscodelist.add(count);
						count = 1;
					}
				}
				statuscodelist.add(count);

				int loop = statuscodelist.size();
				int counter = 0;
				int jj = 0;
				int current = 0;
				while (statuscodelist.size() > 0 && loop > 0) {
					list2.clear();

					for (jj = current; jj < (current + statuscodelist.get(counter)); jj++) {
						if (Parser2.listOfresBody.get(k).get(jj)[1] == null
								|| (!(Parser2.listOfresBody.get(k).get(jj)[1].equals("id"))
										&& !(Parser2.listOfresBody.get(k).get(jj)[1].endsWith("_id"))
										&& !(Parser2.listOfresBody.get(k).get(jj)[1].contains("token"))
										&& !(Parser2.listOfresBody.get(k).get(jj)[1].contains("Token"))))
							list2.add(Parser2.listOfresBody.get(k).get(jj)[1]);
					}

					double similarity = calculateJaccardSimilarity(list1, list2);
					similaritylist.add(similarity);
					if (similarity > (threshold / 100.0) && similarity < 1 && list1.size() <= list2.size()) {
						List<String> ReadOnlyAttributes = new ArrayList<String>(list2);
						ReadOnlyAttributes.removeAll(list1);

						for (int ii = ReadOnlyAttributes.size() - 1; ii >= 0; ii--) {
							if (ReadOnlyAttributes.get(ii).equals("id")) {
								ReadOnlyAttributes.remove(ii);
							}
						}

						// If Path is not in the candidate path
						if (!(candidatepath.contains(Parser2.path.get(i)))) {
							if (ReadOnlyAttributes.size() > 0
									&& ReadOnlyAttributes.size() <= (int) Math.ceil(0.4 * list2.size())
									&& Parser2.operation.get(k).equals("get")) {
								System.out.println("Endpoint:" + Parser2.path.get(i) + " Operation:"
										+ Parser2.operation.get(i) + " ** Endpoint:" + Parser2.path.get(k) + " Operation:"
										+ Parser2.operation.get(k) + " ** Candidate Attributes for Mass Assignment: "
										+ ReadOnlyAttributes);

								outputMessage = outputMessage + "Endpoint: " + Parser2.path.get(i) + "\n   Operation: "
										+ Parser2.operation.get(i) + "\nEndpoint: " + Parser2.path.get(k)
										+ "\n   Operation: " + Parser2.operation.get(k)
										+ "\nCandidate Attributes for Mass Assignment: " + ReadOnlyAttributes
										+ "\n\n\n";

								candidatepath.add(Parser2.path.get(i));
								candidateoperation.add(Parser2.operation.get(i));
								candidateattributes.add(ReadOnlyAttributes);
								numberofMasscandidate += ReadOnlyAttributes.size();
							}
							// Path is in the candidate path list
						} else {
							boolean exist = false;
							for (int m = 0; m < candidatepath.size(); m++) {
								if (candidatepath.get(m).equals(Parser2.path.get(i))
										&& candidateoperation.get(m).equals(Parser2.operation.get(i))
										&& candidateattributes.get(m).equals(ReadOnlyAttributes)) {
									exist = true;
								}
							}
							if (exist == false) {
								if (ReadOnlyAttributes.size() > 0
										&& ReadOnlyAttributes.size() <= (int) Math.ceil(0.4 * list2.size())
										&& Parser2.operation.get(k).equals("get")) {
									System.out.println("Endpoint:" + Parser2.path.get(i) + " Operation:"
											+ Parser2.operation.get(i) + " ** Endpoint:" + Parser2.path.get(k)
											+ " Operation:" + Parser2.operation.get(k)
											+ " ** Candidate Attributes for Mass Assignment: " + ReadOnlyAttributes);

									outputMessage = outputMessage + "Endpoint: " + Parser2.path.get(i) + "\n   Operation: "
											+ Parser2.operation.get(i) + "\nEndpoint: " + Parser2.path.get(k)
											+ "\n   Operation: " + Parser2.operation.get(k)
											+ "\nCandidate Attributes for Mass Assignment: " + ReadOnlyAttributes
											+ "\n\n\n";

									candidatepath.add(Parser2.path.get(i));
									candidateoperation.add(Parser2.operation.get(i));
									candidateattributes.add(ReadOnlyAttributes);
									numberofMasscandidate += ReadOnlyAttributes.size();

								}
							}

						}

					}
					loop--;
					current = current + statuscodelist.get(counter);
					counter++;
				}

			}
		}

		// Copmute number of attributes
		int counter2 = 0;
		for (int n = 0; n < candidatepath.size(); n++) {
			for (int m = n + 1; m < candidatepath.size(); m++) {
				if (candidatepath.get(n).equals(candidatepath.get(m))
						&& candidateoperation.get(n).equals(candidateoperation.get(m))) {
					int size1 = candidateattributes.get(n).size();
					int size2 = candidateattributes.get(m).size();
					if (size1 > size2) {
						for (int p = 0; p < size2; p++) {
							if (candidateattributes.get(n).contains(candidateattributes.get(m).get(p)))
								counter2++;
						}
					} else {
						for (int p = 0; p < size1; p++) {
							if (candidateattributes.get(m).contains(candidateattributes.get(n).get(p)))
								counter2++;
						}
					}

				}
			}
		}
		numberofMasscandidate = numberofMasscandidate - counter2;

	}

	public static double calculateJaccardSimilarity(List<String> list1, List<String> list2) {
		Set<String> set1 = new HashSet<String>(list1);
		Set<String> set2 = new HashSet<String>(list2);

		int intersectionSize = 0;
		for (String element : set1) {
			if (set2.contains(element)) {
				intersectionSize++;
			}
		}

		int unionSize;
		if (set1.size() > set2.size())
			unionSize = set1.size();
		else
			unionSize = set2.size();

		return (double) intersectionSize / unionSize;
	}
}
