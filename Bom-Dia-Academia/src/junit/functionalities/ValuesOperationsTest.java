package junit.functionalities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import functionalities.ValuesOperations;

class ValuesOperationsTest {
	
	ValuesOperations values = new ValuesOperations();

	@Test
	void test() {
		sort();
		filter();
		verifyLastWeek();
		verifyLastDay();
		verifyLastHour();
		getFormatDate();
	}

	private void sort() {
//		values.sort(null);
	}

	private void filter() {
//		values.filter();
	}
	
	private void verifyLastWeek() {
		values.verifyLastWeek("Fri Nov 16 00:04:21 GMT 2018");
	}

	private void verifyLastDay() {
		values.verifyLastDay("Fri Nov 16 00:04:21 GMT 2018");
	}

	private void verifyLastHour() {
		values.verifyLastHour("Fri Nov 16 00:04:21 GMT 2018");
	}

	private void getFormatDate() {
		values.getFormatDate("Fri Nov 16 00:04:21 GMT 2018");
	}

}
