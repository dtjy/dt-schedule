package com.dtjy.timer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dtjy.pojo.DoubleColorBall;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTempQueue;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.core.MessagePostProcessor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.Queue;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


/**
 * 对超过时间的二维码相关订单关闭
 * @Author jiangyao
 * @Date 2019/6/27 17:18
 **/
@Component
@Slf4j
public class DoubleColorBallHistoryDataTask {

    private static final Destination destination = new ActiveMQQueue("dcb-history-data");

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

//    @Scheduled(cron = "${dcb.history.task}")
//        @Scheduled(cron = "0 0/5 * * * ?")
    public void doTask()throws Exception{
        log.info("项目启动，数据采集页面爬取任务开始");
        Integer year = LocalDate.now().getYear();
        for(int i=2003;i<=year;i++) {
            jmsMessagingTemplate.convertAndSend(destination, JSON.toJSONString(queryHistoryData(i)));
        }
        log.info("项目启动，数据采集页面爬取任务结束");
    }
//https://kjh.55128.cn/ssq-history-2019.htm"
    public TreeMap<String,Object> queryHistoryData(Integer year) throws Exception{
        DoubleColorBall ball = null;
        Elements tds = null;
        TreeMap<String,Object> map = new TreeMap<>();
        List<DoubleColorBall> list = new ArrayList<DoubleColorBall>();
        Document doc = Jsoup.connect("https://kjh.55128.cn/ssq-history-"+year+".htm").get();
        Element tbody = doc.getElementsByTag("tbody").first();
        Elements trs = tbody.getElementsByTag("tr");
        for(int j=2;j<trs.size();j++) {
            tds = trs.get(j).getElementsByTag("td");
            ball = new DoubleColorBall(tds.get(1).text(),tds.get(3).text(),tds.get(0).text());
            list.add(ball);
            map.put(tds.get(1).text(), JSONObject.toJSONString(ball));
        }
        Thread.sleep(500);
        return map;
    }
}
