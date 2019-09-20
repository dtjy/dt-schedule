package com.dtjy.timer;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author jiangyao
 * @Date 2019/9/19 9:39
 **/
@Component
@Slf4j
public class DoubleColorBalLastDataTask {

    private static final Destination destination = new ActiveMQQueue("dcb-last-data");

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

//    @Scheduled(cron = "${dcb.last.task}")
//    @Scheduled(cron = "0/30 * * * * ?")
    public void doTask()throws Exception{
//双色球
        log.info("项目启动，数据采集任务开始");

        URL url = new URL("http://f.apiplus.net/ssq-1.json");
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();
        InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader br = new BufferedReader(reader);
        jmsMessagingTemplate.convertAndSend(destination, br.readLine());
        log.info("项目启动，数据采集任务结束");
    }
}
