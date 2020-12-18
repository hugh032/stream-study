package com.hugh.stream.functional;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * 函数式接口demo
 */
@FunctionalInterface
public interface BufferedReaderProcessor {
    /**
     * 任何 BufferedReader -> String 形式的lambda都可以作为参数传递
     * @param b
     * @return
     */
    String process(BufferedReader b) throws IOException;
}
