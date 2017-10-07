package com.scienjus.smartqq;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// 例如比较当前时间和早上6：00
		// String nowTime = new SimpleDateFormat("HH:MM").format(new Date());
		// System.out.println("当前时间为："+nowTime);
		// System.out.println("与当日06：00相比");
		// 不获取登陆前的推特信息
		Date d = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int date = c.get(Calendar.DATE);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int min = c.get(Calendar.MINUTE);
		int sec = c.get(Calendar.SECOND);
		System.out.println(year + "年" + month + "月" + date + "日" + hour + min + sec);

		int i = DateCompare("20180211 05:0:2", "20170101 0:0:0", "yyyyMMdd HH:mm:ss");
		switch (i) {
		case 0:
			System.out.println("两个时间相等");
			break;
		case 1:
			System.out.println("当前时间晚于06:00");
			break;
		case -1:
			System.out.println("当前时间早于06:00");
			break;
		default:
			break;
		}

	}

	/**
	 * 根据时间类型比较时间大小
	 * 
	 * @param source
	 * @param traget
	 * @param type
	 *            "YYYY-MM-DD" "yyyyMMdd HH:mm:ss" 类型可自定义
	 * @param 传递时间的对比格式
	 * @return 0 ：source和traget时间相同 1 ：source比traget时间大 -1：source比traget时间小
	 * @throws Exception
	 */
	public static int DateCompare(String source, String traget, String type) throws Exception {
		int ret = 2;
		SimpleDateFormat format = new SimpleDateFormat(type);
		Date sourcedate = format.parse(source);
		Date tragetdate = format.parse(traget);
		ret = sourcedate.compareTo(tragetdate);
		return ret;
	}
}