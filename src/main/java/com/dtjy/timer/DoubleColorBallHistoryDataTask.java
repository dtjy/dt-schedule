package com.dtjy.timer;

import com.alibaba.fastjson.JSON;
import com.dtjy.pojo.DoubleColorBall;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Scheduled(cron = "${dcb.history.task}")
    public void doTask()throws Exception{

        log.info("项目启动，双色球数据采集页面爬取任务开始");
        Integer year = LocalDate.now().getYear();
        Queue queue = new ActiveMQQueue("dcb-history-data-queue");
        for(int i=2003;i<=year;i++) {
            jmsMessagingTemplate.convertAndSend(queue, JSON.toJSONString(queryHistoryData(year)));
        }
        log.info("项目启动，双色球数据采集页面爬取任务结束");
    }

    public TreeMap<String,Object> queryHistoryData(Integer year) throws Exception{

        Document doc = null;
        Element tbody = null;
        Elements trs = null;
        DoubleColorBall ball = null;
        Elements tds = null;
        TreeMap<String,Object> map = new TreeMap<>();
        List<DoubleColorBall> list = new ArrayList<DoubleColorBall>();

        doc = Jsoup.connect("https://kjh.55128.cn/ssq-history-"+year+".htm").get();
        tbody = doc.getElementsByTag("tbody").first();
        trs = tbody.getElementsByTag("tr");
        for(int j=2;j<trs.size();j++) {
            tds = trs.get(j).getElementsByTag("td");
            ball = new DoubleColorBall(tds.get(1).text(),tds.get(3).text(),tds.get(0).text());
            list.add(ball);
            map.put("dcb-"+tds.get(1).text(),ball);
        }
        Thread.sleep(500);
        return map;
    }
}
