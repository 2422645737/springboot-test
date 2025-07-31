package com.shiyueoe.streamPattern.sink;

import com.shiyueoe.streamPattern.LogEntry;

public class PrintSink extends AbstractLogSink{
    @Override
    public void accept(LogEntry log) {
        System.out.println(log);
    }
}
