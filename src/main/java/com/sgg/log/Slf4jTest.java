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

    /**
     * TODO 桥接器
     *      老项目使用使用了 log4j
     *      现在想要使用 slf4j + logback, 这样需要修改代码
     *      但是又不想修改代码, 所以退出针对老项目福音, 适配器
     */
    @Test
    public void testAdapter() {
        org.apache.log4j.Logger log4jAdapter = org.apache.log4j.Logger.getLogger("log4j_adapter");
        log4jAdapter.info("hello log4j adapter");
    }

    public static void main(String[] args) {
        java.util.logging.Logger log = java.util.logging.Logger.getLogger("slf4jTest");
        long start_time_1 = System.currentTimeMillis();
        for (int i = 0; i < 100_0000; i++) {
            System.out.println(i);
        }
        long end_time_1 = System.currentTimeMillis();
        System.out.println(" 使用System.out.println(), 耗时: " + (end_time_1 - start_time_1) + " ms ");

        long start_time_2 = System.currentTimeMillis();
        for (int i = 0; i < 100_0000; i++) {
            log.info("");
        }
        long end_time_2 = System.currentTimeMillis();
        System.out.println(" 使用System.out.println(), 耗时: " + (end_time_2 - start_time_2) + " ms ");


    }

}
