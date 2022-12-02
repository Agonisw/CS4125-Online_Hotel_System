package com.xxx.common.event;

import com.xxx.common.utils.MailUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 *@Author Guiyang
 *@Date 2022.10.30*/
@Component
public class EmailListener implements ApplicationListener<OrderEvent> {


    @SneakyThrows
    @Override
    @Async
    public void onApplicationEvent(OrderEvent event) {


        String from = "1469925970@qq.com";
        String subject = "Order Information";
        Map<String, String> map = event.getMap();
        String sendTo = map.get("email");
        String springMailHost = map.get("springMailHost");
        String springMailUserName = map.get("springMailUserName");
        String springMailPassword = map.get("springMailPassword");
        MailUtil.sendMail(springMailHost,springMailUserName,springMailPassword,from,sendTo,subject, event.getMessage());
    }
}

