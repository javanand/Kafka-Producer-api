package com.apache.kafka.Producer;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public class CustomSamplerMultiTopic extends AbstractJavaSamplerClient {

	public SampleResult runTest(JavaSamplerContext arg0) {
		// TODO Auto-generated method stub
		 SampleResult result = new SampleResult();
		    boolean success = true;
		    result.sampleStart();
		   
		   //customizing kafka with jmeter
		   
		    long events = 1000;
		    
	        Properties props = new Properties();
	        props.put("metadata.broker.list", "localhost:9092,localhost:9093");
	        props.put("serializer.class", "kafka.serializer.StringEncoder");
	        //props.put("partitioner.class", "example.producer.SimplePartitioner");
	        //props.put("serializer.class", "kafka.serializer.DefaultEncoder");
	        props.put("partitioner.class", "com.apache.kafka.Producer.SimplePartitioner");
	        //props.put("send.buffer.bytes", new Integer("200*1024").intValue());
	        props.put("send.buffer.bytes", "1048576"); //added for performance - 2
	        //props.put("partitioner.class", "example.producer.SimplePartitioner");
	        props.put("producer.type","async");
	        //props.put("batch.num.messages", new Integer("200").intValue());
	        props.put("batch.num.messages", "400"); //added for performance - 1
	        props.put("request.required.acks", "0"); //no acknowledgement from broker
	        
	       
	        ProducerConfig config = new ProducerConfig(props);
	 
	        Producer<String, String> producer = new Producer<String, String>(config);
	        KeyedMessage<String, String> data = null;
	        for (long nEvents = 0; nEvents < events; nEvents++) { 
	        	
	        		if(nEvents%2== 0)
	        			 data = new KeyedMessage<String, String>("Test_Topic_2", "Simple Message uuuuuuu::"+nEvents);
	        		else 
	        			 data = new KeyedMessage<String, String>("Test_Topic_3_Rep_2", "Topic2 message:"+nEvents);	
	               producer.send(data);
	        }
	       
	        producer.close();	    
		    //end of the customizing the kafka
	        result.setResponseCode("500");
	        result.setResponseMessage("kafka succeed");
		    result.sampleEnd();
		    result.setSuccessful(success);
		    result.getTime();
		    return result;
	}
	public Arguments getDefaultParameters() {
		
		return null;
	}
	public void teardownTest(JavaSamplerContext context) {
		
	}
	public void setupTest(JavaSamplerContext context) {
		
	}
}