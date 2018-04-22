package net.sarasarasa.kancollebot.tool;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
 * @author AyagiKei
 * @url https://github.com/Ayagikei
 *
 **/

public class XMLResolver {


    private static String id = "001";
    private static boolean isInited = false;

    SAXReader reader;
    Document document;


    public XMLResolver()  {

        reader = new SAXReader();

        try {
            document = reader.read(new File("stringKancolle.xml"));
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        if(!isInited) {
            id = document.getRootElement().element("role").getText();
            isInited = true;
            System.out.println("————XML解析模块启动成功");
        }

    }

    public String getCall(int num){

        Element root = document.getRootElement();

        Element crt = getCharacter();

        if(crt == null) {
            System.out.println("getCall error");
            return "提督~未找到所要cosplay的角色";
        }

        Element call = crt.element("call"+num);


        return call.getText();
    }

    public String getTime(int num){

        Element root = document.getRootElement();

        Element crt = getCharacter();

        if(crt == null) {
            System.out.println("getTime error");
            return "提督~未找到所要cosplay的角色";
        }

        Element time = crt.element("time"+num);


        return time.getText();
    }

    public String getByTag(String str){

        Element root = document.getRootElement();

        Element crt = getCharacter();

        if(crt == null) {
            System.out.println("getByTag error");
            return "提督~未找到所要cosplay的角色";
        }

        Element res = crt.element(str);

        return res.getText();
    }

    private Element getCharacter(){

        Element root = document.getRootElement();
        Element crt = null;

        List<Element> list = root.elements() ;
        //遍历character
        for (Element e:list){
            if(e.getName().equals("character")) {
                if (e.attributeValue("id").equals(id))
                    crt = e;
            }else continue;
        }

        //当找不到所需要的ID
        if(crt == null) {
            id = "001";
            System.out.println("————不存在所调用的COSPLAY角色");
            return null;
        }

        return crt;

    }

    public String showCharacter(){
        StringBuffer stringBuffer = new StringBuffer();


        Element root = document.getRootElement();
        List<Element> list = root.elements() ;
        for (Element e:list){
            if(e.attributeValue("id") != null)
            stringBuffer.append(e.attributeValue("id") + "  " + e.attributeValue("name") +"\r\n");
        }

        return stringBuffer.toString();
    }


    public boolean changeCharacter(String ctr){
        System.out.println("————尝试COSPLAY" + ctr);

       id = ctr;
       if(getCharacter() == null)
           return false;

       return true;
    }



}