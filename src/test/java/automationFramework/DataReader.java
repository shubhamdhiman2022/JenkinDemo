package automationFramework;

import static automationFramework.PageActions.log;
import static automationFramework.Config.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataReader {
	public static Object getFileLocation()
			throws FileNotFoundException, IOException, org.json.simple.parser.ParseException {
		JSONParser parser = new JSONParser();
		return parser.parse(new FileReader(Config.fol_jasonFilePath));
	}

	public static String getParameterString(String key, String node)
			throws FileNotFoundException, IOException, org.json.simple.parser.ParseException {
		Object obj = getFileLocation();
		JSONArray array = new JSONArray();
		array.add(obj);
		JSONObject jsonObject = (JSONObject) array.get(0);
		JSONObject record = (JSONObject) jsonObject.get(node);
		String value = (String) record.get(key);
		return value;
	}
		
		// For holyday nav items Json
		public static int getHolidayJsonNodesCount() throws Exception
		{
	        Object object = new JSONParser().parse(new FileReader(Config.fol_HolidayjsonFilePath));
	        JSONObject jsonNodesObject = (JSONObject) object;
	        return jsonNodesObject.size();
		}
		
		public static int getHolidayJsonKeysCount(String NodeValue) throws Exception
		{
	        Object object = new JSONParser().parse(new FileReader(Config.fol_HolidayjsonFilePath));
	        JSONObject jsonNodesObject = (JSONObject) object;
	        JSONObject jsonKeysObject =(JSONObject) jsonNodesObject.get(NodeValue);
	        return jsonKeysObject.size();
		}
		public static boolean verifyHolidayJsonNodeExist(String NodeValue) throws Exception
		{
			 Object object = new JSONParser().parse(new FileReader(Config.fol_HolidayjsonFilePath));
		     JSONObject jsonNodesObject = (JSONObject) object;
		     JSONObject jsonKeysObject =(JSONObject) jsonNodesObject.get(NodeValue);
		     if(jsonKeysObject!=null)
		        {
		        	 log.info(NodeValue+ " value exist in json file");
		        	 return true;
		        }
		        else
		        {
		        	log.info(NodeValue+ " value not exist in json file");
		        	return false;
		        }
		}
		
		public static String getHolidayJsonKeysValue(String NodeValue, String KeyValue) throws Exception
		{
	        Object object = new JSONParser().parse(new FileReader(Config.fol_HolidayjsonFilePath));
	        JSONObject jsonNodesObject = (JSONObject) object;
	        JSONObject jsonKeysObject =(JSONObject) jsonNodesObject.get(NodeValue);
	        String value= (String)jsonKeysObject.get(KeyValue);
	        return value;
		}
}
