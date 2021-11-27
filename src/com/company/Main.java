package com.company;


import java.io.IOException;
import java.lang.Runtime;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        int i=0;
        long count=0;
        try (Stream<Path> myfiles = Files.list(Paths.get("src/фото ночь прогулка"))) {
            count = myfiles.count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (i<count) {
            while (Thread.activeCount() < Runtime.getRuntime().availableProcessors()) {
                Runnable thread = new Editor(i);
                new Thread(thread).start();
                i++;
            }
        }


    }
}