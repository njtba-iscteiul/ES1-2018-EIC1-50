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
		getEmailConnect();
		getSent();
	}

	private void receiveEmail() {
		
		String email = "teste@iscte-iul.pt";
		String password = "teste";
		List<String[]> values = new ArrayList<String[]>();
		
		e.receiveEmail(email, password, values);
	}
	
	private void getEmailConnect() {
		assertEquals(false, e.getEmailConnect());
	}
	
	private void getSent() {
		assertEquals(false, e.getSent());
	}
}
