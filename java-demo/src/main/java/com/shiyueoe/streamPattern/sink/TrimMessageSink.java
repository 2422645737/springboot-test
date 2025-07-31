package com.shiyueoe.streamPattern.sink;

import com.shiyueoe.streamPattern.LogEntry;

public class TrimMessageSink extends AbstractLogSink{
    private final int maxLength;

    public TrimMessageSink(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public void accept(LogEntry log) {
        if(log.getMessage().length() > maxLength){
            log.setMessage(log.getMessage().substring(0, maxLength));
        }
        downstream.accept(log);
    }
}
