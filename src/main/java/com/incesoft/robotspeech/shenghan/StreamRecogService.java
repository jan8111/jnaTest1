package com.incesoft.robotspeech.shenghan;

import com.sun.jna.ptr.LongByReference;
import com.sun.jna.ptr.PointerByReference;

import java.io.*;

import static com.incesoft.robotspeech.shenghan.ShengHanApi1.ShengHanApi2.ShengHanApi;


public class StreamRecogService {
    private long timecost ;
    public String recog(String pathname) throws IOException {
        long sessionId = 0;

        try {
            LongByReference ir = new LongByReference(1);
            long code1 = ShengHanApi.recognizer_createSession(ir.getPointer(), SimpleFacotory._context_ptr, 16000);
            msg(code1, "recognizer_createSession");
            sessionId= ir.getPointer().getLong(0);

            ShengHanApi.recognizer_startSession(sessionId, 0);
            int len = 0;
            byte[] bb = new byte[3200];
            InputStream is = new FileInputStream(new File(pathname));
            while ((len = is.read(bb)) != -1) {
                long resumeFlag = ShengHanApi.recognizer_resumeSession(sessionId, bb, len);
                //xxx sleep 100
                Thread.sleep(100);
            }
            long time1 = System.currentTimeMillis();
            ShengHanApi.recognizer_stopSession(sessionId);

            PointerByReference pointer2 = new PointerByReference();
            long code2 = ShengHanApi.recognizer_getSessionResStr(sessionId,pointer2.getPointer());
            msg(code2, "recognizer_getSessionResStr");
            long time2 = System.currentTimeMillis();
            timecost = time2-time1;
            return pointer2.getValue().getString(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            ShengHanApi.recognizer_destroySession(sessionId);
        }
        return null;
    }

    public long getTimecost() {
        return timecost;
    }

    private static void msg(long errCode, String msgStr) {
        if (errCode != 0L) {
            System.out.println(msgStr + " error! code:" + errCode);
        } else {
           // System.out.println(msgStr + " success.");
        }
    }


}