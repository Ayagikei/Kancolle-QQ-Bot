package com.scienjus.smartqq.kancolle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.scienjus.smartqq.client.SmartQQClient;
import com.scienjus.smartqq.model.Group;

import net.dongliu.requests.exception.RequestException;

public class TimeCounter implements Runnable {

	private int hour;
	private SmartQQClient client;
	private static List<Group> groupList = new ArrayList<>();

	public TimeCounter(SmartQQClient client) {
		// TODO 自动生成的构造函数存根
		Date d = new Date();
		int hours = d.getHours();

		hour = hours;
		this.client = client;
		try{
		groupList = client.getGroupList();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void printMessage(String meg) {
		for (Group e : groupList)
			client.sendMessageToGroup(e.getId(), meg);
	}

	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(1000); // 睡眠1000后再次执行任务，模拟定时任务

				Calendar c = Calendar.getInstance();
				//Date d = new Date();
				//int hours = d.getHours();
				int hours = c.get(Calendar.HOUR_OF_DAY);

				if (hours != hour) {
					
					try{
					
					hour = hours;

					
					}catch(RequestException e){
						e.printStackTrace();
					}

					XMLResolver xml = new XMLResolver();

					switch (hours) {


					case 8:
						printMessage(xml.getTime(8));
						QuestReminder qrer = new QuestReminder();
						printMessage(qrer.reminder());
						break;

					default:
						printMessage(xml.getTime(hours));
						break;


					}
				}

				// System.out.println("时间：" + hours); // 执行任务
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
