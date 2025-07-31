package com.shiyueoe.streamPattern.sink;

import com.shiyueoe.streamPattern.LogEntry;

//不再向后传递
public class CountingSink extends AbstractLogSink{
    int count = 0;

    @Override
    public void accept(LogEntry log) {
        count++;
    }


    public int getCount(){
        return count;
    }
}
