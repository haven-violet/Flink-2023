package com.sgg.demo;


import com.alibaba.fastjson.JSON;
import com.sgg.function.MySourceFunction;
import com.sgg.vo.EventLog;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @BelongsProject: Flink-2023
 * @BelongsPackage: com.sgg
 * @Author: jincheng.liao
 * @CreateTime: 2023-05-14  22:20
 * @Description: TODO
 * @Version: 1.0
 */
public class _06_CustomSourceFunction {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        DataStreamSource<EventLog> dataStreamSource = env.addSource(new MySourceFunction());
//        dataStreamSource.map(eventLog -> JSON.toJSONString(eventLog)).print();
        dataStreamSource.map(JSON::toJSONString).print();


        env.execute("_06_CustomSourceFunction");
    }
}


