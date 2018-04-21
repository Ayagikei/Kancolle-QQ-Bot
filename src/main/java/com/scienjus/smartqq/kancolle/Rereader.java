package com.scienjus.smartqq.kancolle;

import com.scienjus.smartqq.client.SmartQQClient;

import java.util.LinkedList;
import java.util.Queue;

public class Rereader {
    //队列
    private final int MAX_QUEUE = 6;
    private SmartQQClient client;
    private LinkedList<String> qMessage = new LinkedList<String>();
    private LinkedList<String> qRereaderMessage = new LinkedList<String>();

    Rereader(){
        System.out.println("——复读模块启动成功");
    }

    Rereader(SmartQQClient client){
        this.client = client;
    }

    //接收信息的方法
    public boolean reread(String message){
        //在Application.class判断信息是不是包含contain？
        boolean re = false;

        //复读前提：复读队列中不存在
        if(!qRereaderMessage.contains(message)) {
        /*判断是否要复读
            先判断是不是队列中出现多次*/
            int cnt = 0;
            for (String s : qMessage) {
                if (s.equals(message))
                    cnt++;
            }
            if (cnt >= 2) {
                re = true;
            } else {
                //  再随机1%几率复读
                int randomNum = (int) (Math.random() * 99 + 1);
                if (randomNum == 67) {
                    re = true;
                }
            }
        }

        //把信息存入队列
        if(qMessage.size()<MAX_QUEUE){
            qMessage.offer(message);
        }
        else{
            qMessage.remove();
            qMessage.offer(message);
        }

        //如果要复读，存入复读缓存队列，防止无限复读
        if(re){
            if(qRereaderMessage.size()<MAX_QUEUE){
                qRereaderMessage.offer(message);
            }
            else{
                qRereaderMessage.remove();
                qRereaderMessage.offer(message);
            }
        }

        return re;
    }
}
