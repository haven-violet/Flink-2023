package com.sgg.log;


import org.junit.Test;
import java.io.IOException;
import java.util.logging.*;

/**
 * @BelongsProject: Flink-2023
 * @BelongsPackage: com.sgg.log
 * @Author: jincheng.liao
 * @CreateTime: 2023-03-26  16:11
 * @Description: TODO
 * @Version: 1.0
 */
public class JULTest {

    @Test
    public void testJUL() {
        //1.获取日志记录器对象
        Logger logger = Logger.getLogger("com.sgg.log.JULTest");
        //日志记录输出
        logger.info("This is info msg");
        logger.log(Level.INFO, "info msg");
        //通过占位符方式输出变量值
        String name = "zhangsan";
        int age = 23;
        logger.log(Level.INFO, "user msg: {0}, {1}", new Object[]{name, age});
    }

    //日志级别
    @Test
    public void testLevel() {
        //TODO JUL 默认级别是 INFO
        Logger logger = Logger.getLogger("com.sgg.log.JULTest");
        logger.log(Level.SEVERE, "severe");
        logger.log(Level.WARNING, "warning");
        logger.log(Level.INFO, "info");
        logger.log(Level.CONFIG, "config");
        logger.log(Level.FINE, "fine");
        logger.log(Level.FINER, "finer");
        logger.log(Level.FINEST, "finest");

    }

    @Test
    public void testCustom() throws IOException {
        //1. 创建日志记录器对象
        Logger logger = Logger.getLogger("com.sgg.log.JULTest");
        //一. 自定义日志级别
        //a. 关闭系统默认配置
        logger.setUseParentHandlers(false);
        //b. 创建handler对象
        ConsoleHandler consoleHandler = new ConsoleHandler();
        //c. 创建formatter对象
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        //d. 进行关联
        consoleHandler.setFormatter(simpleFormatter);
        logger.addHandler(consoleHandler);
        //e. 设置日志级别
        logger.setLevel(Level.ALL);
        consoleHandler.setLevel(Level.ALL);
        //二. 输出到日志文件
        FileHandler fileHandler = new FileHandler("E:\\workspace\\idea_work\\flink\\Flink-2023\\src\\main\\java\\com\\sgg\\log\\jul.log");
        fileHandler.setFormatter(simpleFormatter);
        logger.addHandler(fileHandler);
        //TODO JUL 默认级别是 INFO

        logger.log(Level.SEVERE, "severe");
        logger.log(Level.WARNING, "warning");
        logger.log(Level.INFO, "info");
        logger.log(Level.CONFIG, "config");
        logger.log(Level.FINE, "fine");
        logger.log(Level.FINER, "finer");
        logger.log(Level.FINEST, "finest");

    }
//    @Test
//    public void testJCL() {
//        // 使用JCL 门面创建日志对象
//        Log log = LogFactory.getLog(JULTest.class);
//        log.fatal("fatal");
//        log.error("error");
//        log.warn("warn");
//        log.info("info");
//        log.debug("debug");
//    }
}
