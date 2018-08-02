package com.incesoft.robotspeech.shenghan;

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


       /* errCode[0] = ShengHanApi.recognizer_addDecoder(
                "#HOT_WORD",
                "wfst",
                "/home/shhan/tw_model/model/HOT_WORD.dat");
        msg(errCode, "recognizer_addDecoder HOT_WORD");*/

 /*       errCode[0] = ShengHanApi.recognizer_addDecoder(
                "second-path",
                "wfst-compress",
                "/home/shhan/tw_model/model/xiaoi_hotword_test_0326_0.95_ctc.bin");
        msg(errCode, "recognizer_addDecoder second-path");*/

        LongByReference ir = new LongByReference(1);
        errCode[0]= _context_ptr = ShengHanApi.recognizer_createContext(ir.getPointer());
        msg(errCode, "recognizer_createContext ");
        _context_ptr = ir.getPointer().getLong(0);

        errCode[0] = ShengHanApi.recognizer_setContextAcoustic(_context_ptr, "ctc");
        msg(errCode, "recognizer_setContextAcoustic ");

        errCode[0] = ShengHanApi.recognizer_setContextAcousticParam(_context_ptr, getUnivoiceAcousticParam());
        msg(errCode, "recognizer_setContextAcousticParam  ");

        errCode[0] = ShengHanApi.recognizer_attachContextDecoder(_context_ptr, "first-path", false);
        msg(errCode, "recognizer_attachContextDecoder first-path");

        /*errCode[0] = ShengHanApi.recognizer_setContextDecoderParam(_context_ptr, "first-path", getUnivoiceDecoderParam());
        msg(errCode, "recognizer_setContextDecoderParam  ");*/

       /* errCode[0] = ShengHanApi.recognizer_setContextVadParam(_context_ptr,  getUnivoiceVadParam( ));
        msg(errCode, "recognizer_setContextVadParam  ");
*/

/*        errCode[0] = ShengHanApi.recognizer_setContextRescore(_context_ptr, "first-path", "second-path");
        msg(errCode, "recognizer_setContextRescore  ");*/

      /*  errCode[0] = ShengHanApi.recognizer_attachContextDecoder(_context_ptr, "#HOT_WORD", true);
        msg(errCode, "recognizer_attachContextDecoder #HOT_WORD");*/

    }

    private static ShengHanApi1.UnivoiceVadParam.ByReference getUnivoiceVadParam() {
        ShengHanApi1.UnivoiceVadParam.ByReference en = new ShengHanApi1.UnivoiceVadParam.ByReference();
        return en;
    }

    private static ShengHanApi1.UnivoiceDecoderParam.ByReference getUnivoiceDecoderParam() {
        ShengHanApi1.UnivoiceDecoderParam.ByReference en = new ShengHanApi1.UnivoiceDecoderParam.ByReference();
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

    private static ShengHanApi1.UnivoiceAcousticParam.ByReference getUnivoiceAcousticParam() {
        ShengHanApi1.UnivoiceAcousticParam.ByReference en = new ShengHanApi1.UnivoiceAcousticParam.ByReference();
        en.setCpu_batch_size(40);
        en.setSq_snr_estimate(0);
        en.setSq_clipping_dectect(0);
        return en;
    }


    private static void msg(long[] errCode, String msgStr) {
        if (errCode[0] != 0L) {
            System.out.println(msgStr + " error! code:" + errCode[0]);
        } else {
            System.out.println(msgStr + " success.");
        }
    }

}
