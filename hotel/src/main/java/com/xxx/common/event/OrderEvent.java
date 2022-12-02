package com.xxx.common.event;

import org.springframework.context.ApplicationEvent;

import java.util.Map;

/**
 *Defining order events*/
public class OrderEvent extends ApplicationEvent {

    private String message;

    private Map<String,String> map;

    public OrderEvent(Object source,String message,Map<String,String> map){
        super(source);//Force a call
        this.message = message;
        this.map = map;
    }
    @Override
    public Object getSource(){
        return super.getSource();
    }
    public String getMessage(){
        return message;
    }

   public void setMessage(String message) {
    this.message = message;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
