package com.github.gpor89.masters.client.util;

import java.io.IOException;
import java.io.OutputStream;

public class CountOutputStream extends OutputStream {

    private long size = 0;

    @Override
    public void write(int b) throws IOException {
        size++;
    }

    public long getSize() {
        return size;
    }
}