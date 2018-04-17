package com.incesoft.robotspeech.shenghan;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("recognize file = " + args[0]);
        SimpleFacotory.init(Integer.parseInt( args[1]));
        new StreamRecogServlet().doTest(args[0]);
    }
}
