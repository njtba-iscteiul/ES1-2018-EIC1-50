package files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Json {

	private static File file;
	private static String username;
	private static List<String[]> values;
	private static boolean emailConnect;
	private static boolean facebookConnect;
	private static boolean twitterConnect;
	
	public Json(String username, List<String[]> values, boolean emailConnect, boolean facebookConnect, boolean twitterConnect) {
		this.values = values;
		this.emailConnect = emailConnect;
		this.facebookConnect = facebookConnect;
		this.twitterConnect = twitterConnect;
		this.username = username;
	}

	/**
	 * Creates a file with the username
	 */
	public static void createFile() {
		
		file = new File("./" + username + ".json");
				
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to write emails, tweets and facebook posts in a json file
	 */
	public static void write() {
		
		JSONObject jsonObj = null;
		
		JSONArray jsonList = new JSONArray();
		
		if(values != null) {
			for (int i = 0; i < values.size(); i++) {
				
				jsonObj = new JSONObject();
				
				jsonObj.put("Data", values.get(i)[0]);
				jsonObj.put("Source", values.get(i)[1]);	
				jsonObj.put("Sender", values.get(i)[2]);
				jsonObj.put("Subject", values.get(i)[3]);
				jsonObj.put("Content", values.get(i)[4]);
				
				jsonList.add(jsonObj);
			}
		}
		
		try (FileWriter fileWriter = new FileWriter(file)) {

            fileWriter.write(jsonList.toString());
            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Method to insert in an array the last information from the last connection, when an account can't connect
	 */
	public static void addToArray() {
		
        JSONParser parser = new JSONParser();

		try {
			
			JSONArray jsonList = (JSONArray) parser.parse(new FileReader(file));
			
			if(!jsonList.isEmpty()) {
				for (int i = 0; i < jsonList.size(); i++) {
					
					JSONObject jsonObj =  (JSONObject) jsonList.get(i);
					
					String data = (String) jsonObj.get("Data");
					String source = (String) jsonObj.get("Source");
					String sender = (String) jsonObj.get("Sender");
					String subject = (String )jsonObj.get("Subject");
					String content = (String) jsonObj.get("Content");
					
					if(emailConnect == false && source.equals("Email")) 
						values.add(new String[] {data, source, sender, subject,  content, "View"});
					
					if(facebookConnect == false && source.equals("Facebook")) 
						values.add(new String[] {data, source, sender, subject,  content, "View"});
					
					if(twitterConnect == false && source.equals("Twitter")) 
						values.add(new String[] {data, source, sender, subject,  content, "View"});
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			//e.printStackTrace();
		}
    }
}
