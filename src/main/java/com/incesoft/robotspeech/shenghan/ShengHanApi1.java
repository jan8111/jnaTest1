package com.incesoft.robotspeech.shenghan;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public class ShengHanApi1 {
    public interface ShengHanApi2 extends Library {
        ShengHanApi2 ShengHanApi = (ShengHanApi2) Native.loadLibrary("recognizer", ShengHanApi2.class);
        String recognizer_getVersion();
        int recognizer_setWorkPath(String path);

        long recognizer_addAcoustic(String ctc, String dnn, String s, int i);

        long recognizer_addDecoder(String s, String wfst, String s1);

        long recognizer_createContext(Pointer pointer);

        long recognizer_setContextAcoustic(long context_ptr, String ctc);

        long recognizer_setContextAcousticParam(long context_ptr, SimpleFacotory.UnivoiceAcousticParam.ByReference univoiceAcousticParam);

        long recognizer_getContextAcousticParam(long context_ptr, SimpleFacotory.UnivoiceAcousticParam.ByReference univoiceAcousticParam);

        long recognizer_attachContextDecoder(long context_ptr, String s, boolean b);

        long recognizer_setContextDecoderParam(long context_ptr, String s, UnivoiceDecoderParam univoiceDecoderParam);

        long recognizer_setContextRescore(long context_ptr, String s, String s1);

        long recognizer_createSession(Pointer pointer,long context_ptr, int i);

        void recognizer_startSession(long sessionId, int i);

        void recognizer_resumeSession(long sessionId, byte[] bb, int len);

        void recognizer_stopSession(long sessionId);

        void recognizer_destroySession(long sessionId);

        long recognizer_getSessionResStr(long sessionId,Pointer pointer);
    }

}
