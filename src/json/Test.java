package json;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Test {
	public void test2() {
	}
	
	public String test() throws IOException, ParseException {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("json/seoul_sgg.geojson").getFile());
		FileReader fr = new FileReader(file);
		Object sggObj = new JSONParser().parse(fr);
		JSONObject sggJsonObj = (JSONObject) sggObj;
		JSONArray arr = (JSONArray) sggJsonObj.get("features");
		String[] coordinates = null;
		System.out.println(arr.size());
		for (int i = 0; i < arr.size(); i++) {
			JSONObject tmpObj = (JSONObject) arr.get(i);
			JSONObject properties = (JSONObject) tmpObj.get("properties");
			if (properties.get("ADM_SECT_C").toString().equals("11110")) {
				JSONObject geometry = (JSONObject) tmpObj.get("geometry");
				JSONArray coordinatesJson = (JSONArray) geometry.get("coordinates");
				coordinates = coordinatesJson.toString().substring(3, coordinatesJson.toString().length() - 3)
						.replaceAll("\\[", "").replaceAll("\\],", "/").replaceAll("\\]", "").split("/");
			}
		}
		String path = "";
		for (int i = 0; i < coordinates.length; i++) {
			if (i == coordinates.length - 1) {
				path += coordinates[i].split(",")[1] + "," + coordinates[i].split(",")[0];
			} else {
				path += coordinates[i].split(",")[1] + "," + coordinates[i].split(",")[0] + "/";
			}
		}
		System.out.println(path);
		return "";
	}
	
	public static void main(String[] args) throws IOException, ParseException {
		Test  t = new Test();
		System.out.println(t.test());
	}
	
}
