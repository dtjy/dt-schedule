package com.dtjy.timer;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author jiangyao
 * @Date 2019/9/20 16:17
 **/
@Component
@Slf4j
public class KafkaTestTask {

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    @Scheduled(cron = "0/10 * * * * ?")
    public void doTask()throws Exception{
        log.info("项目启动，数据采集页面爬取任务开始");
        kafkaTemplate.send("test_topic", LocalDateTime.now().toString());
        log.info("项目启动，数据采集页面爬取任务结束");
    }
}
