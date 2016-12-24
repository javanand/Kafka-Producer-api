package com.apache.kafka.Producer;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import com.apache.kafka.util.KafkaProperties;

public class CustomSamplerOff extends AbstractJavaSamplerClient {

	public SampleResult runTest(JavaSamplerContext arg0) {
		
		 	SampleResult result = new SampleResult();
		    boolean success = true;
		    result.sampleStart();

		   //Customizing kafka with jmeter
		   
		    long events = 1000;

		    //Kafka configuration parameters
	        Properties props = new Properties();
	        props.put("client.id", KafkaProperties.clientId);
	        props.put("metadata.broker.list", KafkaProperties.KaflaBrokerList);
	        props.put("send.buffer.bytes", KafkaProperties.SendBufferBytes); //added for performance - 2
	        props.put("producer.type",KafkaProperties.ProducerType); //added for perf
	        props.put("serializer.class", KafkaProperties.SerializerClass);
	        props.put("partitioner.class", "com.apache.kafka.Producer.SimplePartitioner"); //added
	        props.put("batch.num.messages", KafkaProperties.BatchNumMessages); //added for performance - 1
	        props.put("request.required.acks", KafkaProperties.RequestReqdAcks); //changed to 0 from 1 for perf
	       
	        ProducerConfig config = new ProducerConfig(props);
	 
	        Producer<String, String> producer = new Producer<String, String>(config);
	        
	        String topic_1_name = KafkaProperties.topic_1_name;
	        String topic_2_name = KafkaProperties.topic_2_name;
	        
	        for (long nEvents = 0; nEvents < events; nEvents++) {
	        	   KeyedMessage<String, String> data = new KeyedMessage<String, String>(topic_1_name, "Simple Test Message to Topic:: "+topic_1_name + " count:: " + nEvents);
	               KeyedMessage<String, String> Testtopicdata = new KeyedMessage<String, String>(topic_2_name, "Simple Test Message to Topic:: "+ topic_2_name + " count:: "+nEvents);
	               producer.send(data);
	               producer.send(Testtopicdata);
	        }
	       
	        producer.close();	    
		    //end of the customizing the kafka
	        
	        result.setResponseCode("500");
	        result.setResponseMessage("kafka succeed");
		    result.sampleEnd();
		    result.setSuccessful(success);
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