package com.github.cris16228.fresco.interfaces;

import com.github.cris16228.fresco.MemoryCache;

public interface ConnectionErrors {

    void FileNotFound(String url);

    default void OutOfMemory(MemoryCache memoryCache) {
        memoryCache.clear();
    }

    void NormalError();
}
