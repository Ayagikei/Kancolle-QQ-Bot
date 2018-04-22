package net.sarasarasa.kancollebot.moduel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import com.scienjus.smartqq.client.SmartQQClient;
import com.scienjus.smartqq.model.Group;

import net.dongliu.requests.exception.RequestException;

/**
 * @author AyagiKei
 * @url https://github.com/Ayagikei
 *
 **/

public class TimeCounter {

	private int hour;
	private static SmartQQClient client;
	private static List<Group> groupList = new ArrayList<>();

	public TimeCounter(SmartQQClient client) {
		// TODO 自动生成的构造函数存根
		this.client = client;
		try{
		groupList = client.getGroupList();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


	public static void printMessage(String meg) {
		for (Group e : groupList)
			client.sendMessageToGroup(e.getId(), meg);
	}

	public void go() throws Exception {

		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();

		JobDetail job = newJob(HourChimeJob.class).withIdentity("job1", "group1").build();
		CronTrigger trigger = newTrigger().withIdentity("trigger1", "group1").withSchedule(cronSchedule("0 0 * * * ?")).build();
		Date ft = sched.scheduleJob(job, trigger);

		sched.start();
		System.out.println("———— 报时模块启动成功");
	}
}
