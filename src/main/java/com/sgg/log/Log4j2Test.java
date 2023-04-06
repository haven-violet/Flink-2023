package com.sgg.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * @BelongsProject: Flink-2023
 * @BelongsPackage: com.sgg.log
 * @Author: jincheng.liao
 * @CreateTime: 2023-04-06  23:44
 * @Description: TODO
 * @Version: 1.0
 */
public class Log4j2Test {
    public static final Logger LOGGER = LogManager.getLogger(Log4j2Test.class);
    @Test
    public void testLog4j() {
        LOGGER.fatal("fatal");
        LOGGER.error("error");
        LOGGER.warn("warn");
        LOGGER.info("info");
        LOGGER.debug("debug");
        LOGGER.trace("trace");

    }
}
