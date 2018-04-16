package com.incesoft.robotspeech.shenghan;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;

import static com.incesoft.robotspeech.shenghan.ShengHanApi1.ShengHanApi2.ShengHanApi;

public class SimpleFacotory {
    static long _context_ptr;
    public static void init() {
        final long[] errCode = {0L};
        System.out.println("recognizer_getVersion = " + ShengHanApi.recognizer_getVersion());
        ShengHanApi.recognizer_setWorkPath("/home/shhan/tw_model/");

        errCode[0] = ShengHanApi.recognizer_addAcoustic(
                "ctc",
                "dnn",
                "/home/shhan/tw_model/model/final_0808_online.bin",
                0);
        msg(errCode, "recognizer_addAcoustic");

        errCode[0] = ShengHanApi.recognizer_addDecoder(
                "first-path",
                "wfst",
                "/home/shhan/tw_model/model/xiaoi_hotword_test_0326_5.65e-9_ctc.dat");
        msg(errCode, "recognizer_addDecoder first-path");


      /*  errCode[0] = ShengHanApi.recognizer_addDecoder(
                "#HOT_WORD",
                "wfst",
                "/home/shhan/tw_model/model/HOT_WORD.dat");
        msg(errCode, "recognizer_addDecoder HOT_WORD");

        errCode[0] = ShengHanApi.recognizer_addDecoder(
                "second-path",
                "wfst-compress",
                "/home/shhan/tw_model/model/xiaoi_hotword_test_0326_0.95_ctc.bin");
        msg(errCode, "recognizer_addDecoder second-path");*/

        LongByReference ir = new LongByReference(1);
        errCode[0]= _context_ptr = ShengHanApi.recognizer_createContext(ir.getPointer());
        msg(errCode, "recognizer_createContext ");
        _context_ptr = ir.getPointer().getLong(0);
        System.out.println("_context_ptr = " + _context_ptr);

        errCode[0] = ShengHanApi.recognizer_setContextAcoustic(_context_ptr, "ctc");
        msg(errCode, "recognizer_setContextAcoustic ");

        errCode[0] = ShengHanApi.recognizer_setContextAcousticParam(_context_ptr, getUnivoiceAcousticParam());
        msg(errCode, "recognizer_setContextAcousticParam  ");

        UnivoiceAcousticParam.ByReference param2 = new UnivoiceAcousticParam.ByReference();
        errCode[0] = ShengHanApi.recognizer_getContextAcousticParam(_context_ptr, param2);
        msg(errCode, "recognizer_getContextAcousticParam  ");
        System.out.println("param2.getCpu_batch_size() = " + param2.getCpu_batch_size());
        System.out.println("param2.getSq_clipping_dectect() = " + param2.getSq_clipping_dectect());
        System.out.println("param2.getSq_snr_estimate() = " + param2.getSq_snr_estimate());

        errCode[0] = ShengHanApi.recognizer_attachContextDecoder(_context_ptr, "first-path", false);
        msg(errCode, "recognizer_attachContextDecoder first-path");

    /*    errCode[0] = ShengHanApi.recognizer_setContextDecoderParam(_context_ptr, "first-path", getUnivoiceDecoderParam());
        msg(errCode, "recognizer_setContextDecoderParam  ");

        errCode[0] = ShengHanApi.recognizer_setContextRescore(_context_ptr, "first-path", "second-path");
        msg(errCode, "recognizer_setContextRescore  ");

        errCode[0] = ShengHanApi.recognizer_attachContextDecoder(_context_ptr, "#HOT_WORD", true);
        msg(errCode, "recognizer_attachContextDecoder #HOT_WORD");*/

    }

    private static UnivoiceDecoderParam getUnivoiceDecoderParam() {
        UnivoiceDecoderParam en = new UnivoiceDecoderParam();
        en.setLmScale(1);
        en.setAmScale((float) 2.3);
        en.setMaxTSN(500);
        en.setMinTSN(300);
        en.setBeamWidth(50);
        en.setWordBeam(35);
        en.setWordPenalty(0);
        en.setLoopPenalty(0);
        en.setTransPenalty(0);
        en.setnBest(100);
        en.setEpsTransLimit(20);
        en.setGenPartialResult(0);
        en.setGenPartialFrame(200);
        en.setDebug(0);
        en.setBlankSkip(1);
        return en;
    }

    private static UnivoiceAcousticParam.ByReference getUnivoiceAcousticParam() {
        UnivoiceAcousticParam.ByReference en = new UnivoiceAcousticParam.ByReference();
        en.setCpu_batch_size(40);
        en.setSq_snr_estimate(1);
        en.setSq_clipping_dectect(1);
        return en;
    }


    static void msg(long[] errCode, String msgStr) {
        if (errCode[0] != 0L) {
            System.out.println(msgStr + " error! code:" + errCode[0]);
        } else {
            System.out.println(msgStr + " success.");
        }
    }

    static void msg(long errCode, String msgStr) {
        if (errCode != 0L) {
            System.out.println(msgStr + " error! code:" + errCode);
        } else {
            System.out.println(msgStr + " success.");
        }
    }

    public static class UnivoiceAcousticParam  extends Structure {
        public static class ByReference extends UnivoiceAcousticParam implements Structure.ByReference{
        }

        public static class ByValue extends UnivoiceAcousticParam implements Structure.ByValue{
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
}