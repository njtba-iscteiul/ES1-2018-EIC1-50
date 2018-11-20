package functionalities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JComboBox;

public class ValuesOperations {

	public List<String[]> sort(List<String[]> values) {

		Collections.sort(values, new Comparator<String[]>() {
			public int compare(String[] string1, String[] string2) {

				DateFormat dateFormat = new SimpleDateFormat("MMM dd HH:mm:ss zzz yyyy", new Locale("en"));

				String[] tokens1 = string1[0].split(" ");
				String[] tokens2 = string2[0].split(" ");

				String dateToFormat1 = tokens1[1] + " " + tokens1[2] + " " + tokens1[3] + " " + tokens1[4] + " "
						+ tokens1[5];
				String dateToFormat2 = tokens2[1] + " " + tokens2[2] + " " + tokens2[3] + " " + tokens2[4] + " "
						+ tokens2[5];

				Date date1 = null;
				Date date2 = null;
				
				try {
					date1 = dateFormat.parse(dateToFormat1);
					date2 = dateFormat.parse(dateToFormat2);
				} catch (ParseException e) {
					e.printStackTrace();
				}

				return date1.compareTo(date2);
			}
		});
		Collections.reverse(values);

		return values;
	}

	public void filter(JComboBox filterComboBox, JComboBox searchComboBox, List<String[]> values,
			List<String[]> valuesToShow) {

		if (filterComboBox.getSelectedItem().equals("All")) {
			for (int i = 0; i < values.size(); i++) {
				if (searchComboBox.getSelectedItem().equals("All time")) {
					valuesToShow.add(values.get(i));
				} else {
					if (searchComboBox.getSelectedItem().equals("Last hour")) {
						if (verifyLastHour(values.get(i)[0]))
							valuesToShow.add(values.get(i));
					} else {
						if (searchComboBox.getSelectedItem().equals("Last 24 hours")) {
							if (verifyLastDay(values.get(i)[0]))
								valuesToShow.add(values.get(i));
						} else {
							if (searchComboBox.getSelectedItem().equals("Last week")) {
								if (verifyLastWeek(values.get(i)[0]))
									valuesToShow.add(values.get(i));
							}
						}
					}
				}
			}
		} else {
			for (int i = 0; i < values.size(); i++) {
				if (values.get(i)[1].equals(filterComboBox.getSelectedItem())) {
					if (searchComboBox.getSelectedItem().equals("All time")) {
						valuesToShow.add(values.get(i));
					} else {
						if (searchComboBox.getSelectedItem().equals("Last hour")) {
							if (verifyLastHour(values.get(i)[0]))
								valuesToShow.add(values.get(i));
						} else {
							if (searchComboBox.getSelectedItem().equals("Last 24 hours")) {
								if (verifyLastDay(values.get(i)[0]))
									valuesToShow.add(values.get(i));
							} else {
								if (searchComboBox.getSelectedItem().equals("Last week")) {
									if (verifyLastWeek(values.get(i)[0]))
										valuesToShow.add(values.get(i));
								}
							}
						}
					}
				}
			}
		}
	}

	private boolean verifyLastWeek(String string) {

		Date atualDate = new Date();
		Date date = getFormatDate(string);

		if (atualDate.getMonth() == date.getMonth()) {
			if (atualDate.getDate() == date.getDate()) 
				return true;
			if (atualDate.getDate() - 7 < date.getDate()) 
				return true;
			if (atualDate.getDate() - 7 == date.getDate()) {
				if (atualDate.getHours() < date.getHours()) 
					return true;
				if (atualDate.getHours() == date.getHours()) {
					if (atualDate.getMinutes() <= date.getMinutes()) 
						return true;
				}
			}
		}
		return false;
	}

	private boolean verifyLastDay(String string) {

		Date atualDate = new Date();
		Date date = getFormatDate(string);

		if (atualDate.getMonth() == date.getMonth()) {
			if (atualDate.getDate() == date.getDate())
				return true;
			if (atualDate.getDate() - 1 == date.getDate()) {
				if (atualDate.getHours() < date.getHours())
					return true;
				if (atualDate.getHours() == date.getHours()) {
					if (atualDate.getMinutes() <= date.getMinutes())
						return true;
				}
			}
		}
		return false;
	}

	private boolean verifyLastHour(String string) {

		Date atualDate = new Date();
		Date date = getFormatDate(string);

		if (atualDate.getMonth() == date.getMonth()) {
			if (atualDate.getDate() == date.getDate()) {
				if (atualDate.getHours() - 1 < date.getHours()) {
					if (atualDate.getMinutes() > date.getMinutes())
						return true;
				}
				if (atualDate.getHours() - 1 == date.getHours()) {
					if (atualDate.getMinutes() < date.getMinutes())
						return true;
				}
			}
		}
		return false;
	}

	private Date getFormatDate(String string) {

		DateFormat dateFormat = new SimpleDateFormat("MMM dd HH:mm:ss zzz yyyy", new Locale("en"));

		String[] tokens = string.split(" ");

		String dateToFormat = tokens[1] + " " + tokens[2] + " " + tokens[3] + " " + tokens[4] + " " + tokens[5];

		Date date = null;
		try {
			date = dateFormat.parse(dateToFormat);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return date;
	}
}
