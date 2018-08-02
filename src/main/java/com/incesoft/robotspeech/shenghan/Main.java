package com.incesoft.robotspeech.shenghan;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static ExecutorService executor = null;

    public static void main(String[] args)   {
        System.out.println("recognize file : " + args[0]);
        System.out.println("nThreads: " + args[1]);
        System.out.println("total num: " + args[2]);
        executor = Executors.newFixedThreadPool(Integer.parseInt(args[1]));
        SimpleFacotory.init();


        for (int i = 0; i < Integer.parseInt(args[2]); i++) {
            executor.submit(()->{
                try {
                    StreamRecogService service = new StreamRecogService();
                    String result = service.recog(args[0]);
                    System.out.println(result+"time:"+service.getTimecost());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }



    }
}
