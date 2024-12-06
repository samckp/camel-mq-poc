package com.camel.kafka.route;

import com.camel.kafka.bean.DummyBean;
import org.apache.camel.Handler;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.Date;

import static org.apache.camel.component.bean.BeanConstants.BEAN_METHOD_NAME;

@Component
public class BeanTestRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("timer:mytimer?period=1&repeatCount=2")
                .routeId("beanTestRoute")
                .setBody( e -> new Date())
                .to("bean:com.camel.kafka.bean.DummyBean?method=testMessage(${body})")   //  Approach 1
                .log(LoggingLevel.INFO, "${body}")

                .setHeader(BEAN_METHOD_NAME, () -> "fromClient")
                .bean(new DummyBean())                                                  // Approach 2
                .log(LoggingLevel.INFO, "${body}")

                .delay(1000)
                .setBody( e -> new Date())
                .setHeader("myHeader", ()-> "TESTER")

                // used  .class in bean()
                .bean(DummyBean.class, "fromServer(${body}, ${header.myHeader} )")      // Approach 3
                .log(LoggingLevel.INFO, "${body}")
                ;
    }

}
