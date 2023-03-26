package com.sgg.source;

import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.kafka.clients.consumer.OffsetResetStrategy;


/**
 * @BelongsProject: Flink-2023
 * @BelongsPackage: com.sgg.source
 * @Author: jincheng.liao
 * @CreateTime: 2023-03-12  22:14
 * @Description: TODO 测试 Flink1.14.4 和 kafkaSource 结合
 * @Version: 1.0
 */
public class FlinkKafkaSourceTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.STREAMING);

        /**
         * 从Kafka中读取数据得到数据流
         */
        KafkaSource.<String>builder()
                .setTopics("bigdata01")
                .setBootstrapServers("")
                .setGroupId("FlinkKafkaSourceTest")
                // OffsetsInitiallizer.committedOffsets(OffsetResetStrategy.LATEST) 消费起始位移选择之前所提交的偏移量(如果没有, 则重置为LATEST)
                // OffsetsInitializer.committedOffsets(OffsetResetStrategy.EARLIEST) 消费起始偏移量选择之前提交的偏移量(如果没有, 则重置为EARLIEST)
                // OffsetsInitializer.earliest() 消费起始偏移量直接选择为 "最早"
                // OffsetsInitializer.latest() 消费起始偏移量直接选择为 "最晚"
                .setStartingOffsets(OffsetsInitializer.committedOffsets(OffsetResetStrategy.LATEST))
                .setValueOnlyDeserializer(new SimpleStringSchema())

                //开启了Kafka底层消费者的自动位移提交机制, 它会把最新的消费位移提交到kafka的consumer_offsets中
                //计算把自动位移提交机制开启, kafkaSource 依然不依赖自动位移提交机制(宕机重启时, 优先从flink自己的状态中去获取偏移量<更可靠>)
                .setProperty("auto.offset.commit", "true")
                //把本source算子设置成 BOUNDED 属性(有界流), 将来本source去读取数据的时候, 读到指定的位置,就停止读取并退出
                //常用于补数或者重跑一段历史数据
                //.setBounded(OffsetsInitializer.committedOffsets())

                //把本source算子设置成 UNBOUNDED属性(无界流), 但是并不会一直读数据, 而是达到指定位置就停止读取,但程序不退出
                // 主要应用场景 需要从kafka中读取一段固定长度的数据, 然后拿着这段数据去跟另外一个真正的无界流联合处理
                //.setUnbounded(OffsetsInitializer.latest())

        ;

        env.execute();
    }
}
