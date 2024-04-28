package Parser;

import org.json.JSONArray;
import org.json.JSONObject;
import org.tartarus.snowball.ext.PorterStemmer;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import org.json.JSONException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystemAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Parser2 {

	public static int pathcount = 0, operationcount = 0;
	public static List<String> path = new ArrayList<String>();
	public static List<String> operation = new ArrayList<String>();

	public static List<String> reqbody = new ArrayList<String>();
	public static List<String> resbody = new ArrayList<String>();
	public static List<List<String[]>> listOfreqBody = new ArrayList<List<String[]>>();
	public static List<List<String[]>> listOfresBody = new ArrayList<List<String[]>>();
	public static String requestfields = "", responsefields = "", datarow = "";
	public static List<String> data = new ArrayList<String>();
	public static String JsonString = "";

	public void parserfunction() throws IOException, CsvException {

		parseOpenAPI();
		readFile("parsing\\" + RemoveRef.openapiname + "_InitialParsing.csv");
		requestbody();
		responsebody();
		savecontent();
		Stemmer();
	}

	public void parseOpenAPI() throws IOException {

		String jsonContent = new String(
				Files.readAllBytes(Paths.get("resource\\" + RemoveRef.openapiname + "New.json")));
		JsonString = jsonContent;
		JSONObject jsonObject = new JSONObject(jsonContent);
		JSONObject paths = jsonObject.getJSONObject("paths");

		// Iterate over each path
		for (String path : paths.keySet()) {
			JSONObject pathObject = paths.optJSONObject(path);
			pathcount++;

			// Display the operations for the path
			JSONArray operations = pathObject.names();

			for (int i = 0; i < operations.length(); i++) {
				requestfields = "";
				String operationName = operations.getString(i);

				if (operationName.equals("get") || operationName.equals("post") || operationName.equals("put")
						|| operationName.equals("delete") || operationName.equals("patch")) {
					JSONObject operation = pathObject.optJSONObject(operationName);

					if (operation == null)
						break;
					operationcount++;

					// Display the input fields
					JSONObject requestBody = operation.optJSONObject("requestBody");

					if (requestBody != null) {
						JSONArray fieldsrequestbody = requestBody.names();
						for (int i2 = 0; i2 < fieldsrequestbody.length(); i2++) {
							JSONObject object1 = requestBody.optJSONObject(fieldsrequestbody.getString(i2));
							if (fieldsrequestbody.get(i2).toString().equals("properties"))
								propertiesContent(object1, "request");

							JSONArray fieldsobject1 = null;
							if (object1 != null)
								fieldsobject1 = object1.names();

							if (fieldsobject1 != null) {
								for (int i3 = 0; i3 < fieldsobject1.length(); i3++) {
									JSONObject object2 = object1.optJSONObject(fieldsobject1.getString(i3));
									if (fieldsobject1.get(i3).toString().equals("properties"))
										propertiesContent(object2, "request");

									JSONArray fieldsobject2 = null;
									if (object2 != null)
										fieldsobject2 = object2.names();

									if (fieldsobject2 != null) {
										for (int i4 = 0; i4 < fieldsobject2.length(); i4++) {
											JSONObject object3 = object2.optJSONObject(fieldsobject2.getString(i4));
											if (fieldsobject2.get(i4).toString().equals("properties"))
												propertiesContent(object3, "request");

											JSONArray fieldsobject3 = null;
											if (object3 != null)
												fieldsobject3 = object3.names();

											if (fieldsobject3 != null) {
												for (int i5 = 0; i5 < fieldsobject3.length(); i5++) {
													JSONObject object4 = object3
															.optJSONObject(fieldsobject3.getString(i5));
													if (fieldsobject3.get(i5).toString().equals("properties"))
														propertiesContent(object4, "request");

													JSONArray fieldsobject4 = null;
													if (object4 != null)
														fieldsobject4 = object4.names();

													if (fieldsobject4 != null) {
														for (int i6 = 0; i6 < fieldsobject4.length(); i6++) {
															JSONObject object5 = object4
																	.optJSONObject(fieldsobject4.getString(i6));
															if (fieldsobject4.get(i6).toString().equals("properties"))
																propertiesContent(object5, "request");

															JSONArray fieldsobject5 = null;
															if (object5 != null)
																fieldsobject5 = object5.names();

															if (fieldsobject5 != null) {
																for (int i7 = 0; i7 < fieldsobject5.length(); i7++) {
																	JSONObject object6 = object5
																			.optJSONObject(fieldsobject5.getString(i7));
																	if (fieldsobject5.get(i7).toString()
																			.equals("properties"))
																		propertiesContent(object6, "request");

																	JSONArray fieldsobject6 = null;
																	if (object6 != null)
																		fieldsobject6 = object6.names();

																	if (fieldsobject6 != null) {
																		for (int i8 = 0; i8 < fieldsobject6
																				.length(); i8++) {
																			JSONObject object7 = object6.optJSONObject(
																					fieldsobject6.getString(i8));
																			if (fieldsobject6.get(i8).toString()
																					.equals("properties"))
																				propertiesContent(object7, "request");

																			JSONArray fieldsobject7 = null;
																			if (object7 != null)
																				fieldsobject7 = object7.names();

																			if (fieldsobject7 != null) {
																				for (int i9 = 0; i9 < fieldsobject7
																						.length(); i9++) {
																					JSONObject object8 = object7
																							.optJSONObject(fieldsobject7
																									.getString(i9));
																					if (fieldsobject7.get(i9).toString()
																							.equals("properties"))
																						propertiesContent(object8,
																								"request");

																					JSONArray fieldsobject8 = null;
																					if (object8 != null)
																						fieldsobject8 = object8.names();

																					if (fieldsobject8 != null) {
																						for (int i10 = 0; i10 < fieldsobject8
																								.length(); i10++) {
																							JSONObject object9 = object8
																									.optJSONObject(
																											fieldsobject8
																													.getString(
																															i10));
																							if (fieldsobject8.get(i10)
																									.toString()
																									.equals("properties"))
																								propertiesContent(
																										object9,
																										"request");

																							JSONArray fieldsobject9 = null;
																							if (object9 != null)
																								fieldsobject9 = object9
																										.names();

																							if (fieldsobject9 != null) {
																								for (int i11 = 0; i11 < fieldsobject9
																										.length(); i11++) {
																									JSONObject object10 = object9
																											.optJSONObject(
																													fieldsobject9
																															.getString(
																																	i11));
																									if (fieldsobject9
																											.get(i11)
																											.toString()
																											.equals("properties"))
																										propertiesContent(
																												object10,
																												"request");

																									JSONArray fieldsobject10 = null;
																									if (object10 != null)
																										fieldsobject10 = object10
																												.names();

																									if (fieldsobject10 != null) {
																										for (int i12 = 0; i12 < fieldsobject10
																												.length(); i12++) {

																											JSONObject object11 = object10
																													.optJSONObject(
																															fieldsobject10
																																	.getString(
																																			i12));

																											if (fieldsobject10
																													.get(i12)
																													.toString()
																													.equals("properties"))
																												propertiesContent(
																														object11,
																														"request");

																											JSONArray fieldsobject11 = null;
																											if (object11 != null)
																												fieldsobject11 = object11
																														.names();

																											if (fieldsobject11 != null) {
																												for (int i13 = 0; i13 < fieldsobject11
																														.length(); i13++) {

																													JSONObject object12 = object11
																															.optJSONObject(
																																	fieldsobject11
																																			.getString(
																																					i13));

																													if (fieldsobject11
																															.get(i13)
																															.toString()
																															.equals("properties"))
																														propertiesContent(
																																object12,
																																"request");

																													JSONArray fieldsobject12 = null;
																													if (object12 != null)
																														fieldsobject12 = object12
																																.names();

																													if (fieldsobject12 != null) {
																														for (int i14 = 0; i14 < fieldsobject12
																																.length(); i14++) {

																															JSONObject object13 = object12
																																	.optJSONObject(
																																			fieldsobject12
																																					.getString(
																																							i14));

																															if (fieldsobject12
																																	.get(i14)
																																	.toString()
																																	.equals("properties"))
																																propertiesContent(
																																		object13,
																																		"request");

																															JSONArray fieldsobject13 = null;
																															if (object13 != null)
																																fieldsobject13 = object13
																																		.names();

																															if (fieldsobject13 != null) {
																																for (int i15 = 0; i15 < fieldsobject13
																																		.length(); i15++) {

																																	JSONObject object14 = object13
																																			.optJSONObject(
																																					fieldsobject13
																																							.getString(
																																									i15));

																																	if (fieldsobject13
																																			.get(i15)
																																			.toString()
																																			.equals("properties"))
																																		propertiesContent(
																																				object14,
																																				"request");

																																	JSONArray fieldsobject14 = null;
																																	if (object14 != null)
																																		fieldsobject14 = object14
																																				.names();

																																	if (fieldsobject14 != null) {
																																		for (int i16 = 0; i16 < fieldsobject14
																																				.length(); i16++) {

																																			JSONObject object15 = object14
																																					.optJSONObject(
																																							fieldsobject14
																																									.getString(
																																											i16));

																																			if (fieldsobject14
																																					.get(i16)
																																					.toString()
																																					.equals("properties"))
																																				propertiesContent(
																																						object15,
																																						"request");

																																			JSONArray fieldsobject15 = null;
																																			if (object15 != null)
																																				fieldsobject15 = object15
																																						.names();

																																			if (fieldsobject15 != null) {
																																				for (int i17 = 0; i17 < fieldsobject15
																																						.length(); i17++) {

																																					JSONObject object16 = object15
																																							.optJSONObject(
																																									fieldsobject15
																																											.getString(
																																													i17));

																																					if (fieldsobject15
																																							.get(i17)
																																							.toString()
																																							.equals("properties"))
																																						propertiesContent(
																																								object16,
																																								"request");

																																					JSONArray fieldsobject16 = null;
																																					if (object16 != null)
																																						fieldsobject16 = object16
																																								.names();

																																					if (fieldsobject16 != null) {
																																						for (int i18 = 0; i18 < fieldsobject16
																																								.length(); i18++) {

																																							JSONObject object17 = object16
																																									.optJSONObject(
																																											fieldsobject16
																																													.getString(
																																															i18));

																																							if (fieldsobject16
																																									.get(i18)
																																									.toString()
																																									.equals("properties"))
																																								propertiesContent(
																																										object17,
																																										"request");

																																							JSONArray fieldsobject17 = null;
																																							if (object17 != null)
																																								fieldsobject17 = object17
																																										.names();

																																							if (fieldsobject17 != null) {
																																								for (int i19 = 0; i19 < fieldsobject17
																																										.length(); i19++) {

																																									JSONObject object18 = object17
																																											.optJSONObject(
																																													fieldsobject17
																																															.getString(
																																																	i19));

																																									if (fieldsobject17
																																											.get(i19)
																																											.toString()
																																											.equals("properties"))
																																										propertiesContent(
																																												object18,
																																												"request");

																																									JSONArray fieldsobject18 = null;
																																									if (object18 != null)
																																										fieldsobject18 = object18
																																												.names();

																																									if (fieldsobject18 != null) {
																																										for (int i20 = 0; i20 < fieldsobject18
																																												.length(); i20++) {

																																											JSONObject object19 = object18
																																													.optJSONObject(
																																															fieldsobject18
																																																	.getString(
																																																			i20));

																																											if (fieldsobject18
																																													.get(i20)
																																													.toString()
																																													.equals("properties"))
																																												propertiesContent(
																																														object19,
																																														"request");

																																											JSONArray fieldsobject19 = null;
																																											if (object19 != null)
																																												fieldsobject19 = object19
																																														.names();

																																											if (fieldsobject19 != null) {
																																												for (int i21 = 0; i21 < fieldsobject19
																																														.length(); i21++) {

																																													JSONObject object20 = object19
																																															.optJSONObject(
																																																	fieldsobject19
																																																			.getString(
																																																					i21));

																																													if (fieldsobject19
																																															.get(i21)
																																															.toString()
																																															.equals("properties"))
																																														propertiesContent(
																																																object20,
																																																"request");

																																													JSONArray fieldsobject20 = null;
																																													if (object20 != null)
																																														fieldsobject20 = object20
																																																.names();
																																													if (fieldsobject20 != null) {
																																														for (int i22 = 0; i22 < fieldsobject20
																																																.length(); i22++) {

																																															JSONObject object21 = object20
																																																	.optJSONObject(
																																																			fieldsobject20
																																																					.getString(
																																																							i22));

																																															if (fieldsobject20
																																																	.get(i22)
																																																	.toString()
																																																	.equals("properties"))
																																																propertiesContent(
																																																		object21,
																																																		"request");

																																															JSONArray fieldsobject21 = null;
																																															if (object21 != null)
																																																fieldsobject21 = object21
																																																		.names();

																																															if (fieldsobject21 != null) {
																																																for (int i23 = 0; i23 < fieldsobject21
																																																		.length(); i23++) {

																																																	JSONObject object22 = object21
																																																			.optJSONObject(
																																																					fieldsobject21
																																																							.getString(
																																																									23));

																																																	if (fieldsobject21
																																																			.get(i23)
																																																			.toString()
																																																			.equals("properties"))
																																																		propertiesContent(
																																																				object22,
																																																				"request");

																																																	JSONArray fieldsobject22 = null;
																																																	if (object22 != null)
																																																		fieldsobject22 = object22
																																																				.names();

																																																}
																																															}
																																														}
																																													}
																																												}
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}

																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}

					}

					/////////////////////////////////////////////////////////////////////////
					JSONObject responses = operation.optJSONObject("responses");
					JSONArray res = responses.names();

					responsefields = "";
					for (int j = 0; j < res.length(); j++) {
						responsefields = responsefields + "+" + res.getString(j) + " { ";

						// Retrieve the content object
						JSONObject resp = responses.optJSONObject(res.getString(j));
						if (resp != null) {
							JSONArray fieldsresponsebody = resp.names();
							for (int ii2 = 0; ii2 < fieldsresponsebody.length(); ii2++) {
								JSONObject objectt1 = resp.optJSONObject(fieldsresponsebody.getString(ii2));
								if (fieldsresponsebody.get(ii2).toString().equals("properties"))
									propertiesContent(objectt1, "response");

								JSONArray fieldsobjectt1 = null;
								if (objectt1 != null)
									fieldsobjectt1 = objectt1.names();

								if (fieldsobjectt1 != null) {
									for (int i3 = 0; i3 < fieldsobjectt1.length(); i3++) {
										JSONObject object2 = objectt1.optJSONObject(fieldsobjectt1.getString(i3));
										if (fieldsobjectt1.get(i3).toString().equals("properties"))
											propertiesContent(object2, "response");

										JSONArray fieldsobject2 = null;
										if (object2 != null)
											fieldsobject2 = object2.names();

										if (fieldsobject2 != null) {
											for (int i4 = 0; i4 < fieldsobject2.length(); i4++) {
												JSONObject object3 = object2.optJSONObject(fieldsobject2.getString(i4));
												if (fieldsobject2.get(i4).toString().equals("properties"))
													propertiesContent(object3, "response");

												JSONArray fieldsobject3 = null;
												if (object3 != null)
													fieldsobject3 = object3.names();

												if (fieldsobject3 != null) {
													for (int i5 = 0; i5 < fieldsobject3.length(); i5++) {
														JSONObject object4 = object3
																.optJSONObject(fieldsobject3.getString(i5));
														if (fieldsobject3.get(i5).toString().equals("properties"))
															propertiesContent(object4, "response");

														JSONArray fieldsobject4 = null;
														if (object4 != null)
															fieldsobject4 = object4.names();

														if (fieldsobject4 != null) {
															for (int i6 = 0; i6 < fieldsobject4.length(); i6++) {
																JSONObject object5 = object4
																		.optJSONObject(fieldsobject4.getString(i6));
																if (fieldsobject4.get(i6).toString()
																		.equals("properties"))
																	propertiesContent(object5, "response");

																JSONArray fieldsobject5 = null;
																if (object5 != null)
																	fieldsobject5 = object5.names();

																if (fieldsobject5 != null) {
																	for (int i7 = 0; i7 < fieldsobject5
																			.length(); i7++) {
																		JSONObject object6 = object5.optJSONObject(
																				fieldsobject5.getString(i7));
																		if (fieldsobject5.get(i7).toString()
																				.equals("properties"))
																			propertiesContent(object6, "response");

																		JSONArray fieldsobject6 = null;
																		if (object6 != null)
																			fieldsobject6 = object6.names();

																		if (fieldsobject6 != null) {
																			for (int i8 = 0; i8 < fieldsobject6
																					.length(); i8++) {
																				JSONObject object7 = object6
																						.optJSONObject(fieldsobject6
																								.getString(i8));
																				if (fieldsobject6.get(i8).toString()
																						.equals("properties"))
																					propertiesContent(object7,
																							"response");

																				JSONArray fieldsobject7 = null;
																				if (object7 != null)
																					fieldsobject7 = object7.names();

																				if (fieldsobject7 != null) {
																					for (int i9 = 0; i9 < fieldsobject7
																							.length(); i9++) {
																						JSONObject object8 = object7
																								.optJSONObject(
																										fieldsobject7
																												.getString(
																														i9));
																						if (fieldsobject7.get(i9)
																								.toString()
																								.equals("properties"))
																							propertiesContent(object8,
																									"response");
																						JSONArray fieldsobject8 = null;
																						if (object8 != null)
																							fieldsobject8 = object8
																									.names();

																						if (fieldsobject8 != null) {
																							for (int i10 = 0; i10 < fieldsobject8
																									.length(); i10++) {

																								JSONObject object9 = object8
																										.optJSONObject(
																												fieldsobject8
																														.getString(
																																i10));

																								if (fieldsobject8
																										.get(i10)
																										.toString()
																										.equals("properties"))
																									propertiesContent(
																											object9,
																											"response");

																								JSONArray fieldsobject9 = null;
																								if (object9 != null)
																									fieldsobject9 = object9
																											.names();

																								if (fieldsobject9 != null) {
																									for (int i11 = 0; i11 < fieldsobject9
																											.length(); i11++) {

																										JSONObject object10 = object9
																												.optJSONObject(
																														fieldsobject9
																																.getString(
																																		i11));

																										if (fieldsobject9
																												.get(i11)
																												.toString()
																												.equals("properties"))
																											propertiesContent(
																													object10,
																													"response");

																										JSONArray fieldsobject10 = null;
																										if (object10 != null)
																											fieldsobject10 = object10
																													.names();

																										if (fieldsobject10 != null) {
																											for (int i12 = 0; i12 < fieldsobject10
																													.length(); i12++) {

																												JSONObject object11 = object10
																														.optJSONObject(
																																fieldsobject10
																																		.getString(
																																				i12));

																												if (fieldsobject10
																														.get(i12)
																														.toString()
																														.equals("properties"))
																													propertiesContent(
																															object11,
																															"response");

																												JSONArray fieldsobject11 = null;
																												if (object11 != null)
																													fieldsobject11 = object11
																															.names();

																												if (fieldsobject11 != null) {
																													for (int i13 = 0; i13 < fieldsobject11
																															.length(); i13++) {

																														JSONObject object12 = object11
																																.optJSONObject(
																																		fieldsobject11
																																				.getString(
																																						i13));

																														if (fieldsobject11
																																.get(i13)
																																.toString()
																																.equals("properties"))
																															propertiesContent(
																																	object12,
																																	"response");

																														JSONArray fieldsobject12 = null;
																														if (object12 != null)
																															fieldsobject12 = object12
																																	.names();

																														if (fieldsobject12 != null) {
																															for (int i14 = 0; i14 < fieldsobject12
																																	.length(); i14++) {

																																JSONObject object13 = object12
																																		.optJSONObject(
																																				fieldsobject12
																																						.getString(
																																								i14));

																																if (fieldsobject12
																																		.get(i14)
																																		.toString()
																																		.equals("properties"))
																																	propertiesContent(
																																			object13,
																																			"response");

																																JSONArray fieldsobject13 = null;
																																if (object13 != null)
																																	fieldsobject13 = object13
																																			.names();

																																if (fieldsobject13 != null) {
																																	for (int i15 = 0; i15 < fieldsobject13
																																			.length(); i15++) {

																																		JSONObject object14 = object13
																																				.optJSONObject(
																																						fieldsobject13
																																								.getString(
																																										i15));

																																		if (fieldsobject13
																																				.get(i15)
																																				.toString()
																																				.equals("properties"))
																																			propertiesContent(
																																					object14,
																																					"response");

																																		JSONArray fieldsobject14 = null;
																																		if (object14 != null)
																																			fieldsobject14 = object14
																																					.names();

																																		if (fieldsobject14 != null) {
																																			for (int i16 = 0; i16 < fieldsobject14
																																					.length(); i16++) {

																																				JSONObject object15 = object14
																																						.optJSONObject(
																																								fieldsobject14
																																										.getString(
																																												i16));

																																				if (fieldsobject14
																																						.get(i16)
																																						.toString()
																																						.equals("properties"))
																																					propertiesContent(
																																							object15,
																																							"response");

																																				JSONArray fieldsobject15 = null;
																																				if (object15 != null)
																																					fieldsobject15 = object15
																																							.names();

																																				if (fieldsobject15 != null) {
																																					for (int i17 = 0; i17 < fieldsobject15
																																							.length(); i17++) {

																																						JSONObject object16 = object15
																																								.optJSONObject(
																																										fieldsobject15
																																												.getString(
																																														i17));

																																						if (fieldsobject15
																																								.get(i17)
																																								.toString()
																																								.equals("properties"))
																																							propertiesContent(
																																									object16,
																																									"response");

																																						JSONArray fieldsobject16 = null;
																																						if (object16 != null)
																																							fieldsobject16 = object16
																																									.names();

																																						if (fieldsobject16 != null) {
																																							for (int i18 = 0; i18 < fieldsobject16
																																									.length(); i18++) {

																																								JSONObject object17 = object16
																																										.optJSONObject(
																																												fieldsobject16
																																														.getString(
																																																i18));

																																								if (fieldsobject16
																																										.get(i18)
																																										.toString()
																																										.equals("properties"))
																																									propertiesContent(
																																											object17,
																																											"response");

																																								JSONArray fieldsobject17 = null;
																																								if (object17 != null)
																																									fieldsobject17 = object17
																																											.names();

																																								if (fieldsobject17 != null) {
																																									for (int i19 = 0; i19 < fieldsobject17
																																											.length(); i19++) {

																																										JSONObject object18 = object17
																																												.optJSONObject(
																																														fieldsobject17
																																																.getString(
																																																		i19));

																																										if (fieldsobject17
																																												.get(i19)
																																												.toString()
																																												.equals("properties"))
																																											propertiesContent(
																																													object18,
																																													"response");

																																										JSONArray fieldsobject18 = null;
																																										if (object18 != null)
																																											fieldsobject18 = object18
																																													.names();

																																										if (fieldsobject18 != null) {
																																											for (int i20 = 0; i20 < fieldsobject18
																																													.length(); i20++) {

																																												JSONObject object19 = object18
																																														.optJSONObject(
																																																fieldsobject18
																																																		.getString(
																																																				i20));

																																												if (fieldsobject18
																																														.get(i20)
																																														.toString()
																																														.equals("properties"))
																																													propertiesContent(
																																															object19,
																																															"response");

																																												JSONArray fieldsobject19 = null;
																																												if (object19 != null)
																																													fieldsobject19 = object19
																																															.names();

																																												if (fieldsobject19 != null) {
																																													for (int i21 = 0; i21 < fieldsobject19
																																															.length(); i21++) {

																																														JSONObject object20 = object19
																																																.optJSONObject(
																																																		fieldsobject19
																																																				.getString(
																																																						i21));

																																														if (fieldsobject19
																																																.get(i21)
																																																.toString()
																																																.equals("properties"))
																																															propertiesContent(
																																																	object20,
																																																	"response");

																																														JSONArray fieldsobject20 = null;
																																														if (object20 != null)
																																															fieldsobject20 = object20
																																																	.names();

																																														if (fieldsobject20 != null) {
																																															for (int i22 = 0; i22 < fieldsobject20
																																																	.length(); i22++) {

																																																JSONObject object21 = object20
																																																		.optJSONObject(
																																																				fieldsobject20
																																																						.getString(
																																																								i22));

																																																if (fieldsobject20
																																																		.get(i22)
																																																		.toString()
																																																		.equals("properties"))
																																																	propertiesContent(
																																																			object21,
																																																			"response");

																																																JSONArray fieldsobject21 = null;
																																																if (object21 != null)
																																																	fieldsobject21 = object21
																																																			.names();

																																																if (fieldsobject21 != null) {
																																																	for (int i23 = 0; i23 < fieldsobject21
																																																			.length(); i23++) {

																																																		JSONObject object22 = object21
																																																				.optJSONObject(
																																																						fieldsobject21
																																																								.getString(
																																																										i23));

																																																		if (fieldsobject21
																																																				.get(i23)
																																																				.toString()
																																																				.equals("properties"))
																																																			propertiesContent(
																																																					object22,
																																																					"response");

																																																		JSONArray fieldsobject22 = null;
																																																		if (object22 != null)
																																																			fieldsobject22 = object22
																																																					.names();

																																																	}
																																																}
																																															}
																																														}
																																													}
																																												}
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}

																													}

																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					datarow = path + "," + operationName + "," + requestfields + "," + responsefields;
					data.add(datarow);
				}
			} // End of Operation
		} // End of Path

		savedata(data);
		System.out.println("Paths count: " + pathcount);
		System.out.println("Operation count: " + operationcount);
	} // End of function

	////////////////////////////////////////////////////////////////////////
	public static void propertiesContent(JSONObject fields, String reqres) {

		if (fields != null) {
			JSONArray jsonArray = fields.names();
			if (jsonArray != null) {
				for (int i = 0; i < jsonArray.length(); i++) {
					Object obj = jsonArray.get(i);
					Object typeobj = null;
					if (fields.getJSONObject(obj.toString()).has("type"))
						typeobj = fields.getJSONObject(obj.toString()).getString("type");
					if (reqres.equals("request"))
						requestfields = requestfields + obj.toString() + " : " + typeobj + " | ";
					else if (reqres.equals("response"))
						responsefields = responsefields + obj.toString() + " : " + typeobj + " | ";
				}
			}
		}
	}

	//////////////////////////////////////////////////////////////////
	public static void requestbody() {

		for (int i = 1; i < reqbody.size(); i++) {
			List<String[]> rows = new ArrayList<String[]>();
			String[] columns = reqbody.get(i).split("\\|");

			if (columns.length == 1) {
				String[] row = { null, null };
				rows.add(row);
			} else {
				// Process each column and split it into key-value pairs
				for (String column : columns) {
					if (!column.equals(" ")) {
						String[] keyValue = column.trim().split(":");

						// Ignore empty or invalid columns
						if (keyValue.length == 2) {
							String key = keyValue[0].trim();
							String value = keyValue[1].trim();

							// Create a row with key-value pair and add it to the list
							String[] row = { key, value };
							rows.add(row);
						}
					}
				}
			}
			listOfreqBody.add(rows);
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////
	public static void responsebody() {
		for (int i = 1; i < resbody.size(); i++) {
			List<String[]> rows = new ArrayList<String[]>();
			String[] columns = resbody.get(i).split("\\+");

			for (String column : columns) {
				if (!(column.equals("") || column.equals(" "))) {
					String[] value = column.split("\\{");
					String statuscode = value[0];
					for (String val : value) {
						String[] valu = val.split("\\|");
						if (val.equals(" ")) {
							String[] row = { statuscode, null, null };
							rows.add(row);
						} else {
							for (String col : valu) {
								if (!(col.equals("") || col.equals(" "))) {
									String[] keyVal = col.trim().split(":");

									// Ignore empty or invalid columns
									if (keyVal.length == 2) {
										String key = keyVal[0].trim();
										String value2 = keyVal[1].trim();

										// Create a row with key-value pair and add it to the list
										String[] row = { statuscode, key, value2 };
										rows.add(row);
									}
								}
							}
						}

					}
				}
			}
			listOfresBody.add(rows);
		}
	}

	////////////////////////////////////////////////////////////////////////////
	public void Stemmer() throws IOException {
		PorterStemmer stemmer = new PorterStemmer();
		for (int k = 0; k < Parser2.listOfresBody.size(); k++) {
			for (int kk = 0; kk < Parser2.listOfresBody.get(k).size(); kk++) {
				if (Parser2.listOfresBody.get(k).get(kk)[1] != null) {
					stemmer.setCurrent(Parser2.listOfresBody.get(k).get(kk)[1]);
					stemmer.stem();
					Parser2.listOfresBody.get(k).get(kk)[1] = stemmer.getCurrent();
				}
			}
		}

		for (int k = 0; k < Parser2.listOfreqBody.size(); k++) {
			for (int kk = 0; kk < Parser2.listOfreqBody.get(k).size(); kk++) {
				if (Parser2.listOfreqBody.get(k).get(kk)[0] != null) {
					stemmer.setCurrent(Parser2.listOfreqBody.get(k).get(kk)[0]);
					stemmer.stem();
					Parser2.listOfreqBody.get(k).get(kk)[0] = stemmer.getCurrent();
				}
			}
		}
		Parser2.savecontent();
	}

	///////////////////////////////////////////////////////////////////////////////////
	public static void readFile(String csvFile) throws IOException, CsvException {

		List<String[]> rows = new ArrayList<String[]>();
		CSVReader reader = new CSVReader(new FileReader(csvFile));
		rows = reader.readAll();

		for (String[] row : rows) {
			path.add(row[0]);
			operation.add(row[1]);
			reqbody.add(row[2]);
			resbody.add(row[3]);
		}
		path.remove(0);
		operation.remove(0);
	}

	////////////////////////////////////////////////////////////////
	public static void savedata(List<String> list) throws IOException {
		File file = new File("parsing\\" + RemoveRef.openapiname + "_InitialParsing.csv");
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("Path , Operation , Request Body , Response Body");
		bw.newLine();
		for (int k = 0; k < list.size(); k++) {
			bw.write(list.get(k));
			bw.newLine();
		}
		bw.close();
		fw.close();
	}

	//////////////////////////////////////////////////////////////////////////
	public static void savecontent() throws IOException {
		File file = new File("parsing\\" + RemoveRef.openapiname + "_FinalParsing.csv");
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);

		for (int k = 0; k < path.size(); k++) {
			for (int kk = 0; kk < listOfreqBody.get(k).size() || kk < listOfresBody.get(k).size(); kk++) {
				if (kk < listOfreqBody.get(k).size() && kk < listOfresBody.get(k).size()) {
					bw.write(path.get(k) + "," + operation.get(k) + "," + listOfreqBody.get(k).get(kk)[0] + ","
							+ listOfreqBody.get(k).get(kk)[1] + "," + listOfresBody.get(k).get(kk)[0] + ","
							+ listOfresBody.get(k).get(kk)[1] + "," + listOfresBody.get(k).get(kk)[2]);
					bw.newLine();
				} else if (kk >= listOfreqBody.get(k).size() && kk < listOfresBody.get(k).size()) {
					bw.write(path.get(k) + "," + operation.get(k) + "," + null + "," + null + ","
							+ listOfresBody.get(k).get(kk)[0] + "," + listOfresBody.get(k).get(kk)[1] + ","
							+ listOfresBody.get(k).get(kk)[2]);
					bw.newLine();
				} else if (kk < listOfreqBody.get(k).size() && kk >= listOfresBody.get(k).size()) {
					bw.write(path.get(k) + "," + operation.get(k) + "," + listOfreqBody.get(k).get(kk)[0] + ","
							+ listOfreqBody.get(k).get(kk)[1] + "," + null + "," + null + "," + null);
					bw.newLine();
				}
			}
		}
		bw.close();
		fw.close();
	}
}
