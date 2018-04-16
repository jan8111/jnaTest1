import com.sun.jna.Library;
import com.sun.jna.Native;

public class ShJna {

    public interface Clibrary extends Library {
        Clibrary INSTANTCE = (Clibrary) Native.loadLibrary("recognizer", Clibrary.class);
        String recognizer_getVersion();
        int recognizer_setWorkPath(String path);
    }

    public static void main(String[] args) {
        String version1 = ShJna.Clibrary.INSTANTCE.recognizer_getVersion();
        System.out.println("version1 = " + version1);

        int code = ShJna.Clibrary.INSTANTCE.recognizer_setWorkPath("/opt/shenghan/");
        System.out.println("code = " + code);
    }



}  