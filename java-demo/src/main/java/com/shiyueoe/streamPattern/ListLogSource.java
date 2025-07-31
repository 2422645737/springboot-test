package com.shiyueoe.streamPattern;

import java.util.List;
import java.util.function.Consumer;

public class ListLogSource implements LogSource{

    private final List<LogEntry> logs;

    private int index = 0;

    public ListLogSource(List<LogEntry> logs) {
        this.logs = logs;
    }
    @Override
    public boolean tryAdvance(Consumer<LogEntry> action) {
        if(index < logs.size()){
            action.accept(logs.get(index++));
            return true;
        }
        return false;
    }
}

