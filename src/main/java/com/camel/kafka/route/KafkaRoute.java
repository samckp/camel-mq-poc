package com.camel.kafka.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class KafkaRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {


        from("")
                .log("")
                ;
    }
}
