package net.sarasarasa.kancollebot.moduel;

import java.util.Calendar;

import net.sarasarasa.kancollebot.main.Application;
import net.sarasarasa.kancollebot.tool.XMLResolver;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author AyagiKei
 * @url https://github.com/Ayagikei
 *
 **/

public class HourChimeJob implements Job {

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        Calendar c = Calendar.getInstance();
        int hours = c.get(Calendar.HOUR_OF_DAY);

        XMLResolver xml = Application.getXML();

        switch (hours) {

            case 8:
                TimeCounter.printMessage(xml.getTime(8));
                QuestReminder qrer = new QuestReminder();
                TimeCounter.printMessage(qrer.reminder());
                break;

            default:
                TimeCounter.printMessage(xml.getTime(hours));
                break;

        }
    }

}