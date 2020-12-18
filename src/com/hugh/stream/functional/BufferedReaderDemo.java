package com.hugh.stream.functional;

import com.hugh.stream.model.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BufferedReaderDemo {

    public static String processFile(BufferedReaderProcessor processor) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\test.txt"))) {
            return processor.process(bufferedReader);
        }
    }


    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        String str = processFile((BufferedReader br) -> br.readLine() + br.readLine());
        System.out.println(str);

        Predicate<String> nonNull = (String s) -> !s.isEmpty();
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("");
        List<String> filter = filter(list, nonNull);
        System.out.println(filter.size());
    }
}
