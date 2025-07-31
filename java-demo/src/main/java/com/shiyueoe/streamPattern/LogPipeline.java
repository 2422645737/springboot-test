package com.shiyueoe.streamPattern;

import com.shiyueoe.streamPattern.sink.AbstractLogSink;

public class LogPipeline {
    private final LogSource source;

    private final AbstractLogSink head;

    public LogPipeline(LogSource source, AbstractLogSink head) {
        this.source = source;
        this.head = head;
    }

    public void execute(){
        while(source.tryAdvance(head::accept)){

        }
    }
}
