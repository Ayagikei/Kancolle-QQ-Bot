package com.scienjus.smartqq.kancolle;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

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