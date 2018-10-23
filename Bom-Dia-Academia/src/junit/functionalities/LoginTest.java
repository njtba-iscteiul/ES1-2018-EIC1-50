package junit.functionalities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import functionalities.Login;

class LoginTest {

	Login login = new Login();
	
	@Test
	void test() {
		Login();
	}

	private void Login() {
		String username = "Teste";
		String password = "teste";
		
		login.login(username, password);
	}
}
