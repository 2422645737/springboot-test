package com.shiyueoe.streamPattern;

import java.util.function.Consumer;

public interface LogSource {
    boolean tryAdvance(Consumer<LogEntry> action);
}
