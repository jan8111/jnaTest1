package com.incesoft.robotspeech.shenghan;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

public class ShengHanApi1 {
    public interface ShengHanApi2 extends Library {
        ShengHanApi2 ShengHanApi = (ShengHanApi2) Native.loadLibrary("recognizer", ShengHanApi2.class);
        String recognizer_getVersion();
        int recognizer_setWorkPath(String path);

        long recognizer_addAcoustic(String ctc, String dnn, String s, int i);

        long recognizer_addDecoder(String s, String wfst, String s1);

        long recognizer_createContext(Pointer pointer);

        long recognizer_setContextAcoustic(long context_ptr, String ctc);

        long recognizer_setContextAcousticParam(long context_ptr, UnivoiceAcousticParam.ByReference univoiceAcousticParam);

        long recognizer_attachContextDecoder(long context_ptr, String s, boolean b);

        long recognizer_setContextDecoderParam(long context_ptr, String s, UnivoiceDecoderParam.ByReference univoiceDecoderParam);

        long recognizer_setContextVadParam(long context_ptr, UnivoiceVadParam.ByReference univoiceVadParam);

        long recognizer_setContextRescore(long context_ptr, String s, String s1);

        long recognizer_createSession(Pointer pointer,long context_ptr, int i);

        long recognizer_startSession(long sessionId, int i);

        long recognizer_resumeSession(long sessionId, byte[] bb, int len);

        long recognizer_stopSession(long sessionId);

        void recognizer_destroySession(long sessionId);

        long recognizer_getSessionResStr(long sessionId,Pointer pointer);
    }


    

    public static class UnivoiceVadParam  extends Structure {
        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList("vad_type","vad_threshold","vad_ms_lead_sil_timeout","vad_ms_beg_acc_sph","vad_ms_beg_acc_sph_lead_sil","vad_ms_end_cont_sie");
        }

        public static class ByReference extends UnivoiceVadParam implements Structure.ByReference{
        }

        public int   vad_type = 0;                     // 0: no vad, 1: vad_mode_1(RNN), 2: vad_mode_2(pitch)
        public float vad_threshold = (float) 0.5;              // default: 0.5(only for vad_mode_2)
        public int vad_ms_lead_sil_timeout = 5000;       // default: 5000 ms(only for vad_mode_1)
        public int vad_ms_beg_acc_sph = 100;           // default: 100 ms for vad_mode_1; 200 ms for vad_mode_2
        public int vad_ms_beg_acc_sph_lead_sil = 600;  // default: 600 ms(only for vad_mode_1)
        public int vad_ms_end_cont_sie = 1000;          // default: 1000 ms for vad_mode_1; 500 ms for vad_mode_2
    }

    public static class UnivoiceAcousticParam  extends Structure {
        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList("cpu_batch_size","sq_snr_estimate","sq_clipping_dectect");
        }

        public static class ByReference extends UnivoiceAcousticParam implements Structure.ByReference{
        }

        public int cpu_batch_size;
        public int sq_snr_estimate;
        public int sq_clipping_dectect;

        public int getCpu_batch_size() {
            return cpu_batch_size;
        }

        public void setCpu_batch_size(int cpu_batch_size) {
            this.cpu_batch_size = cpu_batch_size;
        }

        public int getSq_snr_estimate() {
            return sq_snr_estimate;
        }

        public void setSq_snr_estimate(int sq_snr_estimate) {
            this.sq_snr_estimate = sq_snr_estimate;
        }

        public int getSq_clipping_dectect() {
            return sq_clipping_dectect;
        }

        public void setSq_clipping_dectect(int sq_clipping_dectect) {
            this.sq_clipping_dectect = sq_clipping_dectect;
        }
    }

    public static class UnivoiceDecoderParam  extends Structure {
        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList("lmScale","amScale","maxTSN","minTSN","beamWidth","wordBeam","wordPenalty","loopPenalty"
                    ,"transPenalty","nBest","epsTransLimit","genPartialResult","genPartialFrame","debug","blankSkip");
        }

        public static class ByReference extends UnivoiceDecoderParam implements Structure.ByReference{
        }

        public float lmScale;
        public float amScale;
        public int maxTSN;
        public int minTSN;
        public float beamWidth;
        public float wordBeam;
        public float wordPenalty;
        public float loopPenalty;
        public float transPenalty;
        public int nBest;
        public int epsTransLimit;
        public int genPartialResult;
        public int genPartialFrame;
        public int debug;
        public float blankSkip;

        public float getLmScale() {
            return lmScale;
        }

        public void setLmScale(float lmScale) {
            this.lmScale = lmScale;
        }

        public float getAmScale() {
            return amScale;
        }

        public void setAmScale(float amScale) {
            this.amScale = amScale;
        }

        public int getMaxTSN() {
            return maxTSN;
        }

        public void setMaxTSN(int maxTSN) {
            this.maxTSN = maxTSN;
        }

        public int getMinTSN() {
            return minTSN;
        }

        public void setMinTSN(int minTSN) {
            this.minTSN = minTSN;
        }

        public float getBeamWidth() {
            return beamWidth;
        }

        public void setBeamWidth(float beamWidth) {
            this.beamWidth = beamWidth;
        }

        public float getWordBeam() {
            return wordBeam;
        }

        public void setWordBeam(float wordBeam) {
            this.wordBeam = wordBeam;
        }

        public float getWordPenalty() {
            return wordPenalty;
        }

        public void setWordPenalty(float wordPenalty) {
            this.wordPenalty = wordPenalty;
        }

        public float getLoopPenalty() {
            return loopPenalty;
        }

        public void setLoopPenalty(float loopPenalty) {
            this.loopPenalty = loopPenalty;
        }

        public float getTransPenalty() {
            return transPenalty;
        }

        public void setTransPenalty(float transPenalty) {
            this.transPenalty = transPenalty;
        }

        public int getnBest() {
            return nBest;
        }

        public void setnBest(int nBest) {
            this.nBest = nBest;
        }

        public int getEpsTransLimit() {
            return epsTransLimit;
        }

        public void setEpsTransLimit(int epsTransLimit) {
            this.epsTransLimit = epsTransLimit;
        }

        public int getGenPartialResult() {
            return genPartialResult;
        }

        public void setGenPartialResult(int genPartialResult) {
            this.genPartialResult = genPartialResult;
        }

        public int getGenPartialFrame() {
            return genPartialFrame;
        }

        public void setGenPartialFrame(int genPartialFrame) {
            this.genPartialFrame = genPartialFrame;
        }

        public int getDebug() {
            return debug;
        }

        public void setDebug(int debug) {
            this.debug = debug;
        }

        public float getBlankSkip() {
            return blankSkip;
        }

        public void setBlankSkip(float blankSkip) {
            this.blankSkip = blankSkip;
        }

    }

}
