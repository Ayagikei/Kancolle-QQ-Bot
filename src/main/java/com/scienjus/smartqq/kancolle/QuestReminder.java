package com.scienjus.smartqq.kancolle;

import java.util.Calendar;
import java.util.Date;

public class QuestReminder {

	private boolean threeCvQuest() {

		Calendar c = Calendar.getInstance();
		int day = c.get(Calendar.DATE);

		int lastNum = day % 10;
		if (lastNum == 3 || lastNum == 7 || lastNum == 0)
			return true;
		else
			return false;

	}

	private boolean fiveBjQuest() {
		Date d = new Date();
		int day = d.getDate();

		int lastNum = day % 10;
		if (lastNum == 2 || lastNum == 8)
			return true;
		else
			return false;

	}

	public String reminder() {

		XMLResolver xml = new XMLResolver();

		if (fiveBjQuest())
			return xml.getByTag("quest1");
		else if (threeCvQuest())
			return xml.getByTag("quest2");
		else
			return xml.getByTag("quest3");
	}
}
