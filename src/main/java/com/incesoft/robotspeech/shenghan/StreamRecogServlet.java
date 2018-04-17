package com.incesoft.robotspeech.shenghan;

import com.sun.jna.ptr.LongByReference;
import com.sun.jna.ptr.PointerByReference;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.incesoft.robotspeech.shenghan.ShengHanApi1.ShengHanApi2.ShengHanApi;


public class StreamRecogServlet {
    public void doTest(String pathname) {
        long sessionId = 0;

        try {
            LongByReference ir = new LongByReference(1);
            long code1 = ShengHanApi.recognizer_createSession(ir.getPointer(), SimpleFacotory._context_ptr, 16000);
            msg(code1, "recognizer_createSession");
            sessionId= ir.getPointer().getLong(0);

            doRecog(pathname, sessionId);
            System.out.println("second.... " );
            doRecog(pathname, sessionId);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ShengHanApi.recognizer_destroySession(sessionId);
        }
    }

    private void doRecog(String pathname, long sessionId) throws IOException, InterruptedException {
        ShengHanApi.recognizer_startSession(sessionId, 0);
        int len = 0;
        byte[] bb = new byte[3200];
        InputStream is = new FileInputStream(new File(pathname));
        while ((len = is.read(bb)) != -1) {
            long resumeFlag = ShengHanApi.recognizer_resumeSession(sessionId, bb, len);
            System.out.println("resumeFlag = " + resumeFlag);
            Thread.sleep(100);
        }
        ShengHanApi.recognizer_stopSession(sessionId);

        PointerByReference pointer2 = new PointerByReference();
        long code2 = ShengHanApi.recognizer_getSessionResStr(sessionId,pointer2.getPointer());
        msg(code2, "recognizer_getSessionResStr");
        String result = pointer2.getValue().getString(0);
        System.out.println("result = " + result);
    }


    private static void msg(long errCode, String msgStr) {
        if (errCode != 0L) {
            System.out.println(msgStr + " error! code:" + errCode);
        } else {
            System.out.println(msgStr + " success.");
        }
    }


}