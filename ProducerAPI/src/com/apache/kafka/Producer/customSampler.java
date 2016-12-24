package com.apache.kafka.Producer;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;



public class customSampler extends AbstractJavaSamplerClient {

	public SampleResult runTest(JavaSamplerContext arg0) {
		// TODO Auto-generated method stub
		 SampleResult result = new SampleResult();
		    boolean success = true;
		    result.sampleStart();
		   
		    //
		    // Write your test code here.
		    //
		   //Customizing kafka with jmeter
		    
		     
		    long events = 1000;
		    //long events = 500;
		    
	        Properties props = new Properties();
	        props.put("client.id", "customSampler-Jmeter");
	        props.put("metadata.broker.list", "localhost:9092,localhost:9093");
	        //props.put("serializer.class", "kafka.serializer.StringEncoder");
	        //props.put("serializer.class", "kafka.serializer.DefaultEncoder");
	        //props.put("send.buffer.bytes", new Integer("200*1024").intValue());
	        props.put("send.buffer.bytes", "1048576"); //added for performance - 2
	        //props.put("partitioner.class", "example.producer.SimplePartitioner");
	        props.put("producer.type","async");
	        props.put("serializer.class", "kafka.serializer.StringEncoder");
	        props.put("partitioner.class", "com.apache.kafka.util.SimplePartitioner");
	        //props.put("batch.num.messages", new Integer("200").intValue());
	        props.put("batch.num.messages", "400"); //added for performance - 1
	        props.put("request.required.acks", "0"); //no acknowledgement from broker
	       
	        ProducerConfig config = new ProducerConfig(props);
	        
	 
	        Producer<String, String> producer = new Producer<String, String>(config);
	        
	        //createTopicFromJava();
	 
	        for (long nEvents = 0; nEvents < events; nEvents++) { 
	           
	               //KeyedMessage<String, String> data = new KeyedMessage<String, String>("my-replicated-topic", "Simple Message uuuuuuu::"+nEvents);
	        		KeyedMessage<String, String> data = new KeyedMessage<String, String>("my-replicated-topic", "Simple Message uuuuuuu::"+nEvents);
	               producer.send(data);
	        }
	        /*for (long nEvents = 0; nEvents < events; nEvents++) { 
		           
	               KeyedMessage<String, String> Testtopicdata = new KeyedMessage<String, String>("Test_Topic_2", "successmessage"+nEvents);
	               producer.send(Testtopicdata);
	        }*/
	       
	        producer.close();	    
		    //end of the customizing the kafka
	        result.setResponseCode("500");
	        
	        result.setResponseMessage("kafka succeed");
		    result.sampleEnd();
		    result.setSuccessful(success);
		    return result;
	}
	
	/*public void createTopicFromJava(ZKClient zk,String str1,)
	{
		AdminUtils.createTopic(arg0, arg1, arg2, arg3, arg4).createTopic);
	}*/
	
	public Arguments getDefaultParameters() {
		
		return null;
	}
	public void teardownTest(JavaSamplerContext context) {
		
	}
	public void setupTest(JavaSamplerContext context) {
		
	}
}