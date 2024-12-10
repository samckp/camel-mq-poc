package com.camel.kafka.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class KafkaRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("timer:mytimer?period=1&repeatCount=5")
                .routeId("producerRoute")
                .setBody( e -> new Date())
                .log(LoggingLevel.INFO,"${body}")
        .to("kafka:mytopic?brokers=localhost:9093"
                + "&securityProtocol=SSL"
                + "&sslTruststoreLocation=D:/kafka_2.13-3.9.0/kafka.truststore.jks"
                + "&sslTruststorePassword="
                + "&sslKeystoreLocation=D:/kafka_2.13-3.9.0/kafka.keystore.jks"
                + "&sslKeystorePassword="
                + "&sslEndpointAlgorithm="
                + "&sslKeyPassword=password")
        ;


        from("kafka:mytopic?brokers=localhost:9093"
                + "&securityProtocol=SSL"
                + "&sslTruststoreLocation=D:/kafka_2.13-3.9.0/kafka.truststore.jks"
                + "&sslTruststorePassword=password"
                + "&sslKeystoreLocation=D:/kafka_2.13-3.9.0/kafka.keystore.jks"
                + "&sslKeystorePassword="
                + "&sslEndpointAlgorithm="
                + "&sslKeyPassword="
                + "&groupId=my-group"
                + "&autoOffsetReset=earliest"
                + "&consumersCount=1"
                + "&keyDeserializer=org.apache.kafka.common.serialization.StringDeserializer"
                + "&valueDeserializer=org.apache.kafka.common.serialization.StringDeserializer")

                .routeId("consumerRoute")
                .log("Received message: ${body}")
                .to("log:received-message")
        ;

    }
}
