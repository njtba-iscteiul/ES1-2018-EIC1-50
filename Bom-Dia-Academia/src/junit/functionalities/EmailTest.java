package junit.functionalities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import functionalities.Email;

class EmailTest {

	Email e = new Email();
	
	@Test
	void test() {
		receiveEmail();
	}

	private void receiveEmail() {
		
		String email = "teste@iscte-iul.pt";
		String password = "teste";
		List<String[]> values = new ArrayList<String[]>();
		
		e.receiveEmail(email, password, values);
	}
}
