package com.scienjus.smartqq.kancolle;

import java.util.Date;

public class QuestReminder {

	private boolean threeCvQuest() {
		Date d = new Date();
		int day = d.getDate();

		System.out.println("day" + day);

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

		if (fiveBjQuest())
			return "Guten Morgen! 提督，今天有5补给舰任务。";
		else if (threeCvQuest())
			return "Guten Morgen! 提督，今天有3空母任务。";
		else
			return "Guten Morgen! 提督，今天没有日期尾数特殊任务。";
	}
}
