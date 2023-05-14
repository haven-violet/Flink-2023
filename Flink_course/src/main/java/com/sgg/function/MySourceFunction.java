package com.sgg.function;

import com.sgg.vo.EventLog;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.util.HashMap;

/**
 * @BelongsProject: Flink-2023
 * @BelongsPackage: com.sgg.vo
 * @Author: jincheng.liao
 * @CreateTime: 2023-05-14  22:21
 * @Description: TODO
 * @Version: 1.0
 */
public class MySourceFunction implements SourceFunction<EventLog> {
    volatile boolean flag = true;
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

    @Override
    public void cancel() {
        flag = false;
    }
}
