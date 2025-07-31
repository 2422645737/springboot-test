package com.shiyueoe.streamPattern.sink;

import com.shiyueoe.streamPattern.LogEntry;

import java.util.List;

public class LevelFilterSink extends AbstractLogSink{
    private final List<String> allowLevels;


    public LevelFilterSink(List<String> allowLevels) {
        this.allowLevels = allowLevels;
    }

    @Override
    public void accept(LogEntry log) {
        if(allowLevels.contains(log.getLevel())){
            downstream.accept(log);
        }
    }
}
