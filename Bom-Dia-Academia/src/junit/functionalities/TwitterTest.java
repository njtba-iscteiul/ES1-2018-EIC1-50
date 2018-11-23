package junit.functionalities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import functionalities.Twitter;

class TwitterTest {

	Twitter twitter = new Twitter("","","","");
	
	@Test
	void test() {
		viewTweets();
		getTwitterConnect();
	}

	private void viewTweets() {
		List<String[]> values = new ArrayList<String[]>();
		
		twitter.viewTweets(values);
	}

	private void getTwitterConnect() {
		assertEquals(false, twitter.getTwitterConnect());
	}
}
