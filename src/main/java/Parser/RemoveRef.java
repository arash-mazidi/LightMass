package Parser;

import java.io.IOException;
import java.lang.System.Logger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RemoveRef {

	public static List<String> rootreference = new ArrayList<String>();
	public static JsonNode originAPI;

	public static String openapiname;
	public static String threshold;
	public static RemoveRefArray objremovearray = new RemoveRefArray();

	public void convert() throws JsonGenerationException, JsonMappingException, IOException {

		readConfig();
		String OriginopenAPIString = new String(Files.readAllBytes(Paths.get("resource\\" + openapiname + ".json")));
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode OriginOpenAPI = objectMapper.reader().readTree(OriginopenAPIString);

		originAPI = OriginOpenAPI;
		
		// Convert the OpenAPI to new version with no $ref
		JsonNode openApiV2Spec = convertToNewOpenAPI();

		// Write the new OpenAPI
		ObjectMapper objectMap = new ObjectMapper();
		objectMap.writerWithDefaultPrettyPrinter().writeValue(new File("resource\\" + openapiname + "New.json"),
				openApiV2Spec);

		System.out.println("Conversion completed successfully.");

	}
	////////////////////////////////////////////////////////////////////////////////
	private static JsonNode convertToNewOpenAPI() {

		// Iterate over paths
		JsonNode paths = originAPI.get("paths");
		Iterator<String> fieldNamespaths = paths.fieldNames();

		while (fieldNamespaths.hasNext()) {
			JsonNode pathObject = paths.get(fieldNamespaths.next());

			// Iterate over path methods
			Iterator<String> fieldNamesmethods = pathObject.fieldNames();
			while (fieldNamesmethods.hasNext()) {
				JsonNode object2 = pathObject.get(fieldNamesmethods.next());
				if (object2.isArray()) {
					for (JsonNode entry : object2) {
						objremovearray.convertToNewOpenAPI(entry);
					}
				} else {
					removereference(object2);
				}
				Iterator<String> fields3 = object2.fieldNames();
				while (fields3.hasNext()) {
					JsonNode object3 = object2.get(fields3.next());
					if (object3.isArray()) {
						for (JsonNode entry : object3) {
							objremovearray.convertToNewOpenAPI(entry);
						}
					} else {
						removereference(object3);
					}
					Iterator<String> fields4 = object3.fieldNames();

					while (fields4.hasNext()) {
						JsonNode object4 = object3.get(fields4.next());

						if (object4.isArray()) {
							for (JsonNode entry : object4) {
								objremovearray.convertToNewOpenAPI(entry);
							}
						} else {
							removereference(object4);
						}
						Iterator<String> fields5 = object4.fieldNames();

						while (fields5.hasNext()) {
							JsonNode object5 = object4.get(fields5.next());

							if (object5.isArray()) {
								for (JsonNode entry : object5) {
									objremovearray.convertToNewOpenAPI(entry);
								}
							} else {
								removereference(object5);
							}
							Iterator<String> fields6 = object5.fieldNames();

							while (fields6.hasNext()) {
								JsonNode object6 = object5.get(fields6.next());

								if (object6.isArray()) {
									for (JsonNode entry : object6) {
										objremovearray.convertToNewOpenAPI(entry);
									}
								} else {
									removereference(object6);
								}
								Iterator<String> fields7 = object6.fieldNames();

								while (fields7.hasNext()) {
									JsonNode object7 = object6.get(fields7.next());

									if (object7.isArray()) {
										for (JsonNode entry : object7) {
											objremovearray.convertToNewOpenAPI(entry);
										}
									} else {
										removereference(object7);
									}
									Iterator<String> fields8 = object7.fieldNames();

									while (fields8.hasNext()) {
										JsonNode object8 = object7.get(fields8.next());

										if (object8.isArray()) {
											for (JsonNode entry : object8) {
												objremovearray.convertToNewOpenAPI(entry);
											}
										} else {
											removereference(object8);
										}
										Iterator<String> fields9 = object8.fieldNames();

										while (fields9.hasNext()) {
											JsonNode object9 = object8.get(fields9.next());

											if (object9.isArray()) {
												for (JsonNode entry : object9) {
													objremovearray.convertToNewOpenAPI(entry);
												}
											} else {
												removereference(object9);
											}
											Iterator<String> fields10 = object9.fieldNames();

											while (fields10.hasNext()) {
												JsonNode object10 = object9.get(fields10.next());

												if (object10.isArray()) {
													for (JsonNode entry : object10) {
														objremovearray.convertToNewOpenAPI(entry);
													}
												} else {
													removereference(object10);
												}
												Iterator<String> fields11 = object10.fieldNames();

												while (fields11.hasNext()) {
													JsonNode object11 = object10.get(fields11.next());

													if (object11.isArray()) {
														for (JsonNode entry : object11) {
															objremovearray.convertToNewOpenAPI(entry);
														}
													} else {
														removereference(object11);
													}
													Iterator<String> fields12 = object11.fieldNames();

													while (fields12.hasNext()) {
														JsonNode object12 = object11.get(fields12.next());

														if (object12.isArray()) {
															for (JsonNode entry : object12) {
																objremovearray.convertToNewOpenAPI(entry);
															}
														} else {
															removereference(object12);
														}
														Iterator<String> fields13 = object12.fieldNames();

														while (fields13.hasNext()) {
															JsonNode object13 = object12.get(fields13.next());

															if (object13.isArray()) {
																for (JsonNode entry : object13) {
																	objremovearray.convertToNewOpenAPI(entry);
																}
															} else {
																removereference(object13);
															}
															Iterator<String> fields14 = object13.fieldNames();

															while (fields14.hasNext()) {
																JsonNode object14 = object13.get(fields14.next());

																if (object14.isArray()) {
																	for (JsonNode entry : object14) {
																		objremovearray.convertToNewOpenAPI(entry);
																	}
																} else {
																	removereference(object14);
																}
																Iterator<String> fields15 = object14.fieldNames();

																while (fields15.hasNext()) {
																	JsonNode object15 = object14.get(fields15.next());

																	if (object15.isArray()) {
																		for (JsonNode entry : object15) {
																			objremovearray.convertToNewOpenAPI(entry);
																		}
																	} else {
																		removereference(object15);
																	}
																	Iterator<String> fields16 = object15.fieldNames();

																	while (fields16.hasNext()) {
																		JsonNode object16 = object15
																				.get(fields16.next());

																		if (object16.isArray()) {
																			for (JsonNode entry : object16) {
																				objremovearray
																						.convertToNewOpenAPI(entry);
																			}
																		} else {
																			removereference(object16);
																		}
																		Iterator<String> fields17 = object16
																				.fieldNames();

																		while (fields17.hasNext()) {
																			JsonNode object17 = object16
																					.get(fields17.next());

																			if (object17.isArray()) {
																				for (JsonNode entry : object17) {
																					objremovearray
																							.convertToNewOpenAPI(entry);
																				}
																			} else {
																				removereference(object17);
																			}
																			Iterator<String> fields18 = object17
																					.fieldNames();

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

		return originAPI;
	}

	////////////////////////////////////////////////////////////////////////////////
	public static void removereference(JsonNode object) {

		if (object != null) {
			if (object.has("$ref")) {
				String ref = object.get("$ref").asText();
				if (object.isObject()) {
					ObjectNode objectNode = (ObjectNode) object;
					objectNode.remove("$ref");
				}
				JsonNode referencedObject = findReferencedObject(originAPI, ref);
				if (object.isObject()) {
					ObjectNode objnode = (ObjectNode) object;
					objnode.set("xx-", referencedObject);
				}
			}
		}
	}

	/////////////////////////////////////////////////////////////////////////////////
	private static JsonNode findReferencedObject(JsonNode openApiV3, String ref) {
		String[] refComponents = ref.split("/");
		if (!(rootreference.contains(refComponents[1]))) {
			rootreference.add(refComponents[1]);
		}
		JsonNode pathRef = openApiV3.get(refComponents[1]);
		for (int i = 2; i < refComponents.length; i++) {
			if (i == refComponents.length - 1) {
				return pathRef;
			} else {
				pathRef = pathRef.get(refComponents[i]).get(refComponents[i + 1]);
			}
		}
		return null;
	}

	///////////////////////////////////////////////////////////////////////////////////////////
	public static void readConfig() {
		JSONParser parser = new JSONParser();
		try {
			String jsonContent = new String(Files.readAllBytes(Paths.get("config.json")));
			JSONObject jsonObject = new JSONObject(jsonContent);
			openapiname = jsonObject.getString("openAPIname");
			threshold = jsonObject.getString("threshold");
			
		} catch (Exception e) {
		}
	}

}