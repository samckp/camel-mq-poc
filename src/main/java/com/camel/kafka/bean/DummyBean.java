package com.camel.kafka.bean;

import org.apache.camel.Handler;

public  class DummyBean {

    @Handler
    public String fromClient(String msg){

        return " from Client :: " + msg;
    }

    @Handler
    public String fromServer(String msg, String header){

        return  " from Server :: " + msg + "; Header :" + header;
    }

    @Handler
    public String testMessage(String msg){

        return  " Test message :: " + msg ;
    }
}