package com.scienjus.smartqq.kancolle;

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

public class XMLResolver {

    SAXReader reader;
    Document document;

    XMLResolver()  {
        reader = new SAXReader();


        try {
            document = reader.read(new File("stringKancolle.xml"));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public String getCall(int num){

        Element root = document.getRootElement();


        Element crt = root.element("character");
        Element call = crt.element("call"+num);


        return call.getText();
    }

    public String getTime(int num){

        Element root = document.getRootElement();

        Element crt = root.element("character");
        Element time = crt.element("time"+num);


        return time.getText();
    }

    public String getByTag(String str){

        Element root = document.getRootElement();

        Element crt = root.element("character");
        Element res = crt.element(str);


        return res.getText();
    }




}