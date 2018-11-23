package junit.functionalities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import functionalities.Facebook;

class FacebookTest {

	private String facebookAccessToken = "";
	
	Facebook facebook = new Facebook(facebookAccessToken);
	
	@Test
	void test() {
		viewPosts();
		getFacebookConnect();
	}

	private void viewPosts() {
		List<String[]> values = new ArrayList<String[]>();
		
		facebook.viewPosts(values);
	}

	private void getFacebookConnect() {
		assertEquals(false, facebook.getFacebookConnect());
	}
}
