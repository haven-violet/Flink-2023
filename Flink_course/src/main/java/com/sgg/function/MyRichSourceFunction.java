package com.sgg.function;

import com.sgg.vo.EventLog;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;

import java.util.HashMap;

/**
 * @BelongsProject: Flink-2023
 * @BelongsPackage: com.sgg.function
 * @Author: jincheng.liao
 * @CreateTime: 2023-05-15  00:09
 * @Description: TODO
 * @Version: 1.0
 */
public class MyRichSourceFunction extends RichSourceFunction<EventLog> {
    volatile boolean flag = true;
    /**
     * source 组件初始化
     * @param parameters The configuration containing the parameters attached to the contract.
     * @throws Exception
     */
    @Override
    public void open(Configuration parameters) throws Exception {
        RuntimeContext runtimeContext = getRuntimeContext();
        //可以从运行时上下文中，获取到本算子中所属的task的task名
        String taskName = runtimeContext.getTaskName();
        //可以从运行时上下文, 取到伴算子所属的subTask 的 subTaskID
        int indexOfThisSubtask = runtimeContext.getIndexOfThisSubtask();

        /**
         * TODO
         *  spark: taskSet -> flink : task
         *  spark: task    -> flink : subTask
         */
    }


    /**
     * source组件生成数据的过程(核心工作逻辑)
     * @param ctx The context to emit elements to and for accessing locks.
     * @throws Exception
     */
    @Override
    public void run(SourceContext<EventLog> ctx) throws Exception {
        EventLog eventLog = new EventLog();
        String[] events = {"appLaunch", "pageLoad", "adShow", "adClick", "itemShare", "itemCollect", "putBack"};
        HashMap<String, String> eventInfoMap = new HashMap<>();
        while (flag) {
            eventLog.setGuid(RandomUtils.nextLong(1, 1000));
            eventLog.setSessionId(RandomStringUtils.randomAlphabetic(12).toUpperCase());
            eventLog.setTimestamp(System.currentTimeMillis());
            eventLog.setEventId(events[RandomUtils.nextInt(0, events.length)]);

            eventInfoMap.put(RandomStringUtils.randomAlphabetic(1), RandomStringUtils.randomAlphabetic(2));
            eventLog.setEventInfos(eventInfoMap);
            ctx.collect(eventLog);
            eventInfoMap.clear();
            Thread.sleep(500, 1500);
        }
    }

    /**
     * job取消调用的方法
     */
    @Override
    public void cancel() {

    }

    /**
     * 组件关闭调用的方法
     * @throws Exception
     */
    @Override
    public void close() throws Exception {

    }
}
