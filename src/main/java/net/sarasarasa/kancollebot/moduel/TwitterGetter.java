package net.sarasarasa.kancollebot.moduel;

import net.sarasarasa.kancollebot.main.Application;
import net.sarasarasa.kancollebot.tool.XMLResolver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;

/**
 * @author AyagiKei
 * @url https://github.com/Ayagikei
 **/

public class TwitterGetter {

    private static int lastYear;
    private static int lastMonth;
    private static int lastDate;

    private static int lastHour;
    private static int lastMin;
    private static int lastSec;

    private static int tlastYear;
    private static int tlastMonth;
    private static int tlastDate;

    private static int tlastHour;
    private static int tlastMin;
    private static int tlastSec;

    private Stack<String> stTwitter;

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

    public String getTwitter(boolean isGettingSyncTwitter) {
        String a = getRowContent();
        stTwitter = new Stack<>();

        if(getHandledContent(a,isGettingSyncTwitter) == true) {

            setLastTime(tlastYear,tlastMonth,tlastDate,tlastHour,tlastMin,tlastSec);

            StringBuffer stringBuffer = new StringBuffer();
            XMLResolver xml = Application.getXML();
            stringBuffer.append(xml.getByTag("inf"));

            while(!stTwitter.empty()){
                stringBuffer.append("\r\n" + stTwitter.pop());

            }
            return stringBuffer.toString();
        }
        else return null;

    }

    public String getRowContent(){
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
            return a;
        }catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean getHandledContent(String row,int startPoint,boolean isGettingSyncTwitter){
        String a = row;

        try {

            int point = a.indexOf("<i class=\"fa fa-clock-o\"></i> 20",startPoint);
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


            String time1 = String.format("%02d%02d%02d %02d:%02d:%02d", year, month, date, hour, min, sec);
            String time2 = String.format("%02d%02d%02d %02d:%02d:%02d", lastYear, lastMonth, lastDate, lastHour,
                    lastMin, lastSec);

            int ans = DateCompare(time1, time2, "yyyyMMdd HH:mm:ss");
            if (ans == 1)
                isNewer = true;
            else
                isNewer = false;


            if (isNewer || !isGettingSyncTwitter) {

                tlastYear = year;
                tlastMonth = month;
                tlastDate = date;
                tlastHour = hour;
                tlastMin = min;
                tlastSec = sec;

                String sTime = year + "年" + month + "月" + date + "日   " + hour + ":" + min + ":" + sec + "\r\n";
                //System.out.println(year + "年" + month + "月" + date + "日   " + hour + ":" + min + ":" + sec);

                int point4 = a.indexOf("<p>", point3);


                int point5 = a.indexOf("<span class=\"hashtag", point4);

                if(point4 == -1 || point5 == -1)
                    return false;

                String content = a.substring(point4 + 3, point5 - 1);

                // System.out.println(content);


                content = content.replaceAll("<br />", "");
                content = content.replaceAll("<p>", "");
                content = content.replaceAll("</p>", "");

                String aLink = "";
                //如果有链接的话
                int urlPointerStart = content.indexOf("<a href=\"");
                if (urlPointerStart != -1) {

                    int urlPointer = content.indexOf("\"", urlPointerStart);
                    int urlPointerEnd = content.indexOf("\"", urlPointer + 1);

                    if (urlPointer != -1 && urlPointerEnd != -1) {
                        aLink = content.substring(urlPointer + 1, urlPointerEnd);

                    }
                }

                String aImg = "";
                //如果有链接的话
                int ImgPointerStart = content.indexOf("<img src=");
                if (ImgPointerStart != -1) {

                    int ImgPointer = content.indexOf("\"", ImgPointerStart);
                    int ImgPointerEnd = content.indexOf("\"", ImgPointer + 1);

                    if (ImgPointer != -1 && ImgPointerEnd != -1)
                        aImg = "\r\n图片链接：" + content.substring(ImgPointer + 1, ImgPointerEnd);
                }

                content = content.replace("</?[a-zA-Z]+[^><]*>", "");
                content = content.trim();

                int point6;
                if ((point6 = content.indexOf("<a")) != -1) {
                    int point7 = content.indexOf("</a>");
                    String temp2 = content.substring(point6, point7 + 4);
                    content = content.replaceAll(temp2, "");
                }

                XMLResolver xml = Application.getXML();
                //if(stTwitter.empty())
               // sTime = xml.getByTag("inf") + "\r\n" + sTime;

                stTwitter.push(sTime + content + aLink + aImg);

                if(isGettingSyncTwitter)
                getHandledContent(row,point5,isGettingSyncTwitter);

                return true;

            } else {
                return false;
            }

        } catch (StringIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean getHandledContent(String row,boolean isGettingSyncTwitter){ return getHandledContent(row,0,isGettingSyncTwitter);}


    //unuse methon
    public String twitterAnalyze(String content) {
        StringBuilder str = new StringBuilder();
        if (content.contains("メンテナンス")) {
            str.append("维护 ");
            //if(content)
        }

        if (content.contains("アップデート"))
            str.append("更新 ");

        return str.toString();
    }

}