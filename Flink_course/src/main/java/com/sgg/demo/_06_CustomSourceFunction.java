package com.sgg.demo;


import com.alibaba.fastjson.JSON;
import com.sgg.function.MyParallelSourceFunction;
import com.sgg.function.MyRichSourceFunction;
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
 * 自定义Source
 *  可以实现 SourceFuntion 或者 RichSourceFunction, 这两个都是非并行的算子
 *  也可以实现 ParallelSourceFunction 或者 RichParallelSourceFunction, 这两个都是可以并行的source算子
 *  -- 带 Rich, 都拥有 open(), close(), getRuntimeContext()
 *  -- 带Parallel, 都是可并行执行的算子
 */
public class _06_CustomSourceFunction {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

//        DataStreamSource<EventLog> dataStreamSource = env.addSource(new MyRichSourceFunction());
                //java.lang.IllegalArgumentException: The parallelism of non parallel operator must be 1.
                //.setParallelism(2);
//        dataStreamSource.map(eventLog -> JSON.toJSONString(eventLog)).print();
        DataStreamSource<EventLog> eventLogDataStreamSource = env.addSource(new MyParallelSourceFunction()).setParallelism(2);
        eventLogDataStreamSource.map(JSON::toJSONString).print();


        env.execute("_06_CustomSourceFunction");
    }
}


