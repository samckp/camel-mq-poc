<h2>How to use Apache Camel Bean Component? </h2> 

<h3> .to("bean:com.camel.kafka.bean.DummyBean?method=testMessage(${body})")   //  Approach 1 </h3> 
 <h3>.bean(new DummyBean())                                                  // Approach 2 </h3>
 <h3>.bean(DummyBean.class, "fromServer(${body}, ${header.myHeader} )")      // Approach 3 </h3>
