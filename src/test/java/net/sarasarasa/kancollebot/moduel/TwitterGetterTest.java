package net.sarasarasa.kancollebot.moduel;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * @author AyagiKei
 * @url https://github.com/Ayagikei
 **/
public class TwitterGetterTest {

    @Test
    public void getTwitter() {

        // 不获取登陆前的推特信息
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        // Calendar返回值0~11
        int month = c.get(Calendar.MONTH) + 1;
        int date = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);
        int sec = c.get(Calendar.SECOND);
        TwitterGetter.setLastTime(year, month, date, hour, min, sec);

        TwitterGetter twitterGetter = new TwitterGetter();
        System.out.println(twitterGetter.getTwitter(false));
    }
}