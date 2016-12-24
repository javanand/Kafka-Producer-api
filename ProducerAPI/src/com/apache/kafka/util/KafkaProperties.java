/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.apache.kafka.util;

public interface KafkaProperties
{
  /*final static String zkConnect = "localhost:2181";
  final static  String groupId = "group1";
  final static String kafkaServerURL = "localhost";
  final static int kafkaServerPort = 9092;
  final static int kafkaProducerBufferSize = 64*1024;
  final static int connectionTimeOut = 100000;
  final static int reconnectInterval = 10000;*/
  
  //Anand
  final static String clientId = "CustomSampler-Jmeter";
  final static String KaflaBrokerList = "localhost:9092,localhost:9093";
  final static String SendBufferBytes = "1048576";
  final static String ProducerType = "async";
  final static String SerializerClass = "kafka.serializer.StringEncoder"; //kafka.serializer.DefaultEncoder
  final static String BatchNumMessages = "400";
  final static String RequestReqdAcks = "0";
  final static String topic_1_name = "Test_Topic_3_Rep_2";
  final static String topic_2_name = "Page_Visits_2_Rep_2";
}
