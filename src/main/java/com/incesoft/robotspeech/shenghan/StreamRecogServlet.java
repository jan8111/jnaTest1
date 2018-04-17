package com.incesoft.robotspeech.shenghan;

import com.sun.jna.ptr.LongByReference;
import com.sun.jna.ptr.PointerByReference;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.incesoft.robotspeech.shenghan.ShengHanApi1.ShengHanApi2.ShengHanApi;


public class StreamRecogServlet {
    public void doTest(String pathname) throws IOException {
        long sessionId = 0;
        InputStream is = new FileInputStream(new File(pathname));
        try {
            LongByReference ir = new LongByReference(1);
            long code1 = ShengHanApi.recognizer_createSession(ir.getPointer(), SimpleFacotory._context_ptr, 16000);
            msg(code1, "recognizer_createSession");
            sessionId= ir.getPointer().getLong(0);

            ShengHanApi.recognizer_startSession(sessionId, 0);
            int len = 0;
            byte[] bb = new byte[3200];
            while ((len = is.read(bb)) != -1) {
                ShengHanApi.recognizer_resumeSession(sessionId, bb, len);
            }
            ShengHanApi.recognizer_stopSession(sessionId);

            PointerByReference  pointer2 = new PointerByReference();
            long code2 = ShengHanApi.recognizer_getSessionResStr(sessionId,pointer2.getPointer());
            msg(code2, "recognizer_getSessionResStr");
            String result = pointer2.getValue().getString(0);
            System.out.println("result = " + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ShengHanApi.recognizer_destroySession(sessionId);
        }
    }


    private static void msg(long errCode, String msgStr) {
        if (errCode != 0L) {
            System.out.println(msgStr + " error! code:" + errCode);
        } else {
            System.out.println(msgStr + " success.");
        }
    }


}