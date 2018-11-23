package junit.files;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.Test;

import files.Json;

class JsonTest {
	
	private static File file;
	private static String username = "Nelson";
	private static List<String[]> values = null;
	private static boolean emailConnect = true;
	private static boolean facebookConnect = true;
	private static boolean twitterConnect = true;
	
	Json json = new Json(username, values, emailConnect, facebookConnect, twitterConnect);
	
	@Test
	void test() {
		createFile();
		write();
		addToArray();
	}

	private void createFile() {
		json.createFile();
	}

	private void write() {
		json.write();
	}

	private void addToArray() {
		json.addToArray();
	}
}
