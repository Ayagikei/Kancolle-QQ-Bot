package com.scienjus.smartqq.kancolle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TwitterGetter {

	static int lastYear;
	static int lastMonth;
	static int lastDate;

	static int lastHour;
	static int lastMin;
	static int lastSec;

	public static void setLastTime(int year, int month, int date, int hour, int min, int sec) {
		lastYear = year;
		lastMonth = month;
		lastDate = date;
		lastHour = hour;
		lastMin = min;
		lastSec = sec;
	}

	public static int DateCompare(String source, String traget, String type) {
		int ret = 2;
		SimpleDateFormat format = new SimpleDateFormat(type);
		Date sourcedate = null;
		try {
			sourcedate = format.parse(source);
		} catch (ParseException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		Date tragetdate = null;
		try {
			tragetdate = format.parse(traget);
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		ret = sourcedate.compareTo(tragetdate);
		return ret;
	}

	public String getSyncTwitter() {
		String a;
		String temp;
		StringBuffer stringBuffer = new StringBuffer();
		try {
			String url = "https://t.kcwiki.moe/";
			BufferedReader in = new BufferedReader(
					new InputStreamReader(new URL(url).openConnection().getInputStream(), "utf-8"));// GB2312可以根据需要替换成要读取网页的编码
			while ((temp = in.readLine()) != null) {
				stringBuffer.append(temp + '\n');

			}

			a = stringBuffer.toString();
			// System.out.println(a);

			int point = a.indexOf("<i class=\"fa fa-clock-o\"></i> 20");
			int point2 = a.indexOf("年", point);

			int year = (int) (a.charAt(point2 - 1)) - '0' + ((int) (a.charAt(point2 - 2)) - '0') * 10
					+ ((int) (a.charAt(point2 - 3)) - '0') * 100 + ((int) (a.charAt(point2 - 4)) - '0') * 1000;

			point2 = a.indexOf("月", point);
			int month = ((int) (a.charAt(point2 - 1)) - '0');
			if (a.charAt(point2 - 2) != '年')
				month += ((int) (a.charAt(point2 - 2)) - '0') * 10;

			point2 = a.indexOf("日", point);
			int date = ((int) (a.charAt(point2 - 1)) - '0');
			if (a.charAt(point2 - 2) != '月')
				date += ((int) (a.charAt(point2 - 2)) - '0') * 10;

			int point3 = a.indexOf(":", point2);
			int hour = ((int) (a.charAt(point3 - 1)) - '0') + ((int) (a.charAt(point3 - 2)) - '0') * 10;

			point3 = a.indexOf(":", point3 + 1);
			int min = ((int) (a.charAt(point3 - 1)) - '0') + ((int) (a.charAt(point3 - 2)) - '0') * 10;

			int sec = ((int) (a.charAt(point3 + 2)) - '0') + ((int) (a.charAt(point3 + 1)) - '0') * 10;

			boolean isNewer;

			// String nowTime = new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(new
			// Date());

			System.out.println("Time" + year + month + date + " " + hour + ":" + min + ":" + sec);
			System.out.println(
					"LastTime" + lastYear + lastMonth + lastDate + " " + lastHour + ":" + lastMin + ":" + lastSec);

			String time1 = String.format("%02d%02d%02d %02d:%02d:%02d", year, month, date, hour, min, sec);
			String time2 = String.format("%02d%02d%02d %02d:%02d:%02d", lastYear, lastMonth, lastDate, lastHour,
					lastMin, lastSec);

			int ans = DateCompare(time1, time2, "yyyyMMdd HH:mm:ss");
			if (ans == 1)
				isNewer = true;
			else
				isNewer = false;

			/*
			 * if (year > lastYear || month > lastMonth || date > lastDate) { isNewer =
			 * true; } else if (hour > lastHour) { isNewer = true; } else if (hour ==
			 * lastHour && min > lastMin) { isNewer = true; } else if (hour == lastHour &&
			 * min == lastMin && sec > lastSec) { isNewer = true; } else isNewer = false;
			 */

			if (isNewer) {

				lastYear = year;
				lastMonth = month;
				lastDate = date;
				lastHour = hour;
				lastMin = min;
				lastSec = sec;

				String sTime = year + "年" + month + "月" + date + "日   " + hour + ":" + min + ":" + sec + "\r\n";
				System.out.println(year + "年" + month + "月" + date + "日   " + hour + ":" + min + ":" + sec);

				int point4 = a.indexOf("<p>", point3);
				// System.out.println(a.charAt(point4 + 3));

				int point5 = a.indexOf("</div", point4);
				String content = a.substring(point4 + 3, point5 - 1);

				// System.out.println(content);

				content = content.replaceAll("<br />", "");
				content = content.replaceAll("<p>", "");
				content = content.replaceAll("</p>", "");
				content = content.replace("</?[a-zA-Z]+[^><]*>", "");
				content = content.trim();

				int point6;
				if ((point6 = content.indexOf("<a")) != -1) {
					int point7 = content.indexOf("</a>");
					String temp2 = content.substring(point6, point7 + 4);
					// System.out.println("这是temp2" + temp2 + "结束");
					content = content.replaceAll(temp2, "");
				}

				System.out.println(content);
				return sTime + content;

			} else
				return null;

		} catch (StringIndexOutOfBoundsException e) {

		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}

		return null;
	}

	public String getNewestTwitter() {
		String a;
		String temp;
		StringBuffer stringBuffer = new StringBuffer();
		try {
			String url = "https://t.kcwiki.moe/";
			BufferedReader in = new BufferedReader(
					new InputStreamReader(new URL(url).openConnection().getInputStream(), "utf-8"));// GB2312可以根据需要替换成要读取网页的编码
			while ((temp = in.readLine()) != null) {
				stringBuffer.append(temp + '\n');

			}

			a = stringBuffer.toString();
			// System.out.println(a);

			int point = a.indexOf("<i class=\"fa fa-clock-o\"></i> 20");
			int point2 = a.indexOf("年", point);
			int year = (int) (a.charAt(point2 - 1)) - '0' + ((int) (a.charAt(point2 - 2)) - '0') * 10
					+ ((int) (a.charAt(point2 - 3)) - '0') * 100 + ((int) (a.charAt(point2 - 4)) - '0') * 1000;

			point2 = a.indexOf("月", point);
			int month = ((int) (a.charAt(point2 - 1)) - '0');
			if (a.charAt(point2 - 2) != '年')
				month += ((int) (a.charAt(point2 - 2)) - '0') * 10;

			point2 = a.indexOf("日", point);
			int date = ((int) (a.charAt(point2 - 1)) - '0');
			if (a.charAt(point2 - 2) != '月')
				date += ((int) (a.charAt(point2 - 2)) - '0') * 10;

			int point3 = a.indexOf(":", point2);
			int hour = ((int) (a.charAt(point3 - 1)) - '0') + ((int) (a.charAt(point3 - 2)) - '0') * 10;

			point3 = a.indexOf(":", point3 + 1);
			int min = ((int) (a.charAt(point3 - 1)) - '0') + ((int) (a.charAt(point3 - 2)) - '0') * 10;

			int sec = ((int) (a.charAt(point3 + 2)) - '0') + ((int) (a.charAt(point3 + 1)) - '0') * 10;

			String sTime = year + "年" + month + "月" + date + "日   " + hour + ":" + min + ":" + sec + "\r\n";
			System.out.println(year + "年" + month + "月" + date + "日   " + hour + ":" + min + ":" + sec);

			int point4 = a.indexOf("<p>", point3);
			// System.out.println(a.charAt(point4 + 3));

			int point5 = a.indexOf("</div", point4);
			String content = a.substring(point4 + 3, point5 - 1);

			// System.out.println(content);

			content = content.replaceAll("<br />", "");
			content = content.replaceAll("<p>", "");
			content = content.replaceAll("</p>", "");
			content = content.replace("</?[a-zA-Z]+[^><]*>", "");
			content = content.trim();

			int point6;
			if ((point6 = content.indexOf("<a")) != -1) {
				int point7 = content.indexOf("</a>");
				String temp2 = content.substring(point6, point7 + 4);
				content = content.replaceAll(temp2, "");
			}

			sTime = "舰队的情报啊，嗯，稍等哦，哦，哦哦，原来如此呐～ \n" + sTime;

			System.out.println(content);
			return sTime + content;

		} catch (MalformedURLException e) {
		} catch (IOException e) {
		} catch (StringIndexOutOfBoundsException e) {
		}

		return null;
	}

}