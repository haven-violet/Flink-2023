package com.sgg;


public class Main {
    public static void main(String[] args) throws Exception {
//        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        env.setRuntimeMode(RuntimeExecutionMode.STREAMING);
//
//        DataStreamSource<String> socketStream = env.socketTextStream("192.168.152.100", 8000);
//
//        socketStream.print();
//
//        env.execute("Flink_Log_test");
        for (int i = 0; i < 11; i++) {
            System.out.println(i);
        }
    }
}