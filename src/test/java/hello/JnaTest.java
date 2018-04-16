package hello;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class JnaTest {

    public interface Clibrary extends Library {
        Clibrary INSTANTCE = (Clibrary) Native.loadLibrary("hello", Clibrary.class);
        void test();
    }

    public static void main(String[] args) {
        Clibrary.INSTANTCE.test();
    }
}  