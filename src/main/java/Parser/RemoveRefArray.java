package Parser;

import java.io.IOException;
import java.lang.System.Logger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.io.File;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RemoveRefArray {


	////////////////////////////////////////////////////////////////////////////////
	public JsonNode convertToNewOpenAPI(JsonNode object0) {

		removereference(object0);
		Iterator<String> fields1 = object0.fieldNames();

		while (fields1.hasNext()) {

			JsonNode object1 = object0.get(fields1.next());

			removereference(object1);
			Iterator<String> fields2 = object1.fieldNames();
			while (fields2.hasNext()) {
				JsonNode object2 = object1.get(fields2.next());

				removereference(object2);
				Iterator<String> fields3 = object2.fieldNames();

				while (fields3.hasNext()) {
					JsonNode object3 = object2.get(fields3.next());

					removereference(object3);
					Iterator<String> fields4 = object3.fieldNames();

					while (fields4.hasNext()) {
						JsonNode object4 = object3.get(fields4.next());
						removereference(object4);
						Iterator<String> fields5 = object4.fieldNames();

						while (fields5.hasNext()) {
							JsonNode object5 = object4.get(fields5.next());
							removereference(object5);
							Iterator<String> fields6 = object5.fieldNames();

							while (fields6.hasNext()) {
								JsonNode object6 = object5.get(fields6.next());
								removereference(object6);
								Iterator<String> fields7 = object6.fieldNames();

								while (fields7.hasNext()) {
									JsonNode object7 = object6.get(fields7.next());
									removereference(object7);
									Iterator<String> fields8 = object7.fieldNames();

									while (fields8.hasNext()) {
										JsonNode object8 = object7.get(fields8.next());
										removereference(object8);
										Iterator<String> fields9 = object8.fieldNames();

										while (fields9.hasNext()) {
											JsonNode object9 = object8.get(fields9.next());
											removereference(object9);
											Iterator<String> fields10 = object9.fieldNames();

											while (fields10.hasNext()) {
												JsonNode object10 = object9.get(fields10.next());
												removereference(object10);
												Iterator<String> fields11 = object10.fieldNames();

												while (fields11.hasNext()) {
													JsonNode object11 = object10.get(fields11.next());
													removereference(object11);
													Iterator<String> fields12 = object11.fieldNames();

													while (fields12.hasNext()) {
														JsonNode object12 = object11.get(fields12.next());
														removereference(object12);
														Iterator<String> fields13 = object12.fieldNames();

														while (fields13.hasNext()) {
															JsonNode object13 = object12.get(fields13.next());
															removereference(object13);
															Iterator<String> fields14 = object13.fieldNames();

															while (fields14.hasNext()) {
																JsonNode object14 = object13.get(fields14.next());
																removereference(object14);
																Iterator<String> fields15 = object14.fieldNames();

																while (fields15.hasNext()) {
																	JsonNode object15 = object14.get(fields15.next());
																	removereference(object15);
																	Iterator<String> fields16 = object15.fieldNames();

																	while (fields16.hasNext()) {
																		JsonNode object16 = object15
																				.get(fields16.next());
																		removereference(object16);
																		Iterator<String> fields17 = object16
																				.fieldNames();

																		while (fields17.hasNext()) {
																			JsonNode object17 = object16
																					.get(fields17.next());
																			removereference(object17);
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

		return RemoveRef.originAPI;
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
				JsonNode referencedObject = findReferencedObject(RemoveRef.originAPI, ref);
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
		if (!(RemoveRef.rootreference.contains(refComponents[1]))) {
			RemoveRef.rootreference.add(refComponents[1]);
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
}