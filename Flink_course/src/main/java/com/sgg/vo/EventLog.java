package com.sgg.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

/**
 * @BelongsProject: Flink-2023
 * @BelongsPackage: com.sgg.vo
 * @Author: jincheng.liao
 * @CreateTime: 2023-05-14  23:32
 * @Description: TODO
 * @Version: 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class EventLog {
    private Long guid;
    private String sessionId;
    private String eventId;
    private Long timestamp;
    private Map<String, String> eventInfos;
}
