package com.sgg.log;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * @BelongsProject: Flink-2023
 * @BelongsPackage: com.sgg.log
 * @Author: jincheng.liao
 * @CreateTime: 2023-03-26  21:13
 * @Description: TODO
 * @Version: 1.0
 */
public class Log4jTest {

    @Test
    public void testLog4j() {
        //todo Log4j 默认日志级别是 Debug
        //初始化系统配置，不需要配置文件
        BasicConfigurator.configure();
        //1. 创建日志记录器对象
        Logger logger = Logger.getLogger(Log4jTest.class);
        //日志级别
        logger.fatal("fatal log"); //todo 严重错误, 一般会造成系统崩溃和终止运行
        logger.error("error log"); //todo 错误信息, 但不会影响系统运行
        logger.warn("warn log");   //todo 警告信息, 可能会发生问题
        logger.info("info log");   //todo 程序运行信息, 数据库的连接、网络、IO操作等
        logger.debug("debug log"); //todo 调试信息，一般在开发阶段使用, 记录程序的变量，参数等
        logger.trace("trage log"); //todo 追踪信息, 记录程序的所有流程信息

    }
}
