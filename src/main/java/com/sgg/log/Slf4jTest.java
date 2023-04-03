package com.sgg.log;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @BelongsProject: Flink-2023
 * @BelongsPackage: com.sgg.log
 * @Author: jincheng.liao
 * @CreateTime: 2023-04-03  22:33
 * @Description: TODO
 * @Version: 1.0
 */
public class Slf4jTest {
    public final static Logger LOGGER = LoggerFactory.getLogger(Slf4jTest.class);
    @Test
    public void testSlf4jSimple() {
        LOGGER.error("error");
        LOGGER.warn("warn");
        LOGGER.info("info");
        LOGGER.debug("debug");
        LOGGER.trace("trace");
        String name = "zhangSan";
        Integer age = 18;
        LOGGER.info("用户: {}, {}", name, age);
        try {
            int i = 1/0;
        }catch (Exception e) {
            LOGGER.info("出现异常", e);
        }
    }
}
