package com.apache.kafka.Producer;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

//public class TestProducer extends AbstractJavaSamplerClient implements Serializable{
public class TestProducer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long events = 10;
        
 
        Properties props = new Properties();
        props.put("metadata.broker.list", "localhost:9091,localhost:9093");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        //props.put("partitioner.class", "example.producer.SimplePartitioner");
        props.put("request.required.acks", "0");
 
        ProducerConfig config = new ProducerConfig(props);
 
        Producer<String, String> producer = new Producer<String, String>(config);
        long nEvents = 0;
        for ( nEvents = 0; nEvents < events; nEvents++) { 
           
               KeyedMessage<String, String> data = new KeyedMessage<String, String>("test_topic_1", "Simple Message uuuuuuu::"+nEvents);
               producer.send(data);
        }
        producer.close();
        System.out.println("Done sending " + nEvents);
	}

	//@Override
	/*public SampleResult runTest(JavaSamplerContext arg0) {
		// TODO Auto-generated method stub
		System.out.println("executed");
		
		return null;
	}*/
	
	 //@Override
	/*  public SampleResult runTest(JavaSamplerContext ctx) {
		 System.out.println("here only");
	    JMeterVariables vars = JMeterContextService.getContext().getVariables();
	    vars.put("demo", "demoVariableContent");

	    SampleResult sampleResult = new SampleResult();
	    sampleResult.setSuccessful(true);
	    sampleResult.setResponseCodeOK();
	    sampleResult.setResponseMessageOK();
	    return sampleResult;
	  }  */
	
}
