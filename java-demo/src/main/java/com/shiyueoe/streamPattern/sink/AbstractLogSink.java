package com.shiyueoe.streamPattern.sink;

import com.shiyueoe.streamPattern.LogEntry;

public abstract class AbstractLogSink {
    protected AbstractLogSink downstream;

    public AbstractLogSink link(AbstractLogSink next) {
        this.downstream = next;
        return this.downstream;
    }

    public abstract void accept(LogEntry log);
}
