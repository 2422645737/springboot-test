package com.shiyueoe.streamPattern;

import com.shiyueoe.streamPattern.sink.CountingSink;
import com.shiyueoe.streamPattern.sink.LevelFilterSink;
import com.shiyueoe.streamPattern.sink.PrintSink;
import com.shiyueoe.streamPattern.sink.TrimMessageSink;

import java.util.List;
import java.util.function.Predicate;

public class App {
    public static void main(String[] args) {
        List<LogEntry> logs = List.of(
                new LogEntry("INFO", "彤彤上线啦~", System.currentTimeMillis()),
                new LogEntry("WARN", "哥哥今天还没写测试", System.currentTimeMillis()),
                new LogEntry("ERROR", "服务器炸了！！！！！", System.currentTimeMillis()),
                new LogEntry("INFO", "喝水休息一下吧", System.currentTimeMillis())
        );

        LogSource source = new ListLogSource(logs);

        LevelFilterSink filter = new LevelFilterSink(List.of("WARN", "ERROR"));
        TrimMessageSink trim = new TrimMessageSink(15);
        PrintSink printer = new PrintSink();
        CountingSink counter = new CountingSink();

        //组合管道
        filter.link(trim).link(printer).link(counter);
        //trim.link(counter);

        //执行
        LogPipeline pipeline = new LogPipeline(source,filter);
        pipeline.execute();

        Predicate<Integer> available = (n) -> n >= 0 && n <= 100;
        Predicate<Integer> pass = (n) -> n >= 60;
        Predicate<Integer> notVeryGood = (n) -> n <= 90;

        Predicate<Integer> res = available.and(pass).and(notVeryGood);
        System.out.println(res.test(70));
    }

}
