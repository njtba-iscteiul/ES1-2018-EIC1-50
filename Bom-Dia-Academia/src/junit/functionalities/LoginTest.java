package junit.functionalities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import functionalities.Login;

class LoginTest {

	private static String userEmail = "teste@iscte-iul.pt";
	private static String userPassword = "teste";
	private static String consumerKey = "consumerKey";
	private static String consumerSecret = "consumerSecret";
	private static String accessToken = "accessToken";
	private static String accessTokenSecret = "accessTokenSecret";
	
	Login login = new Login();
	
	@Test
	void test() {
		Login();
		getUserEmail();
	}

	private void Login() {
		String username = "Teste";
		String password = userPassword;
		
		login.login(username, password);
	}
	
	public void getUserEmail() {
		assertEquals("teste@iscte-iul.pt", login.getUserEmail());
	}
}
