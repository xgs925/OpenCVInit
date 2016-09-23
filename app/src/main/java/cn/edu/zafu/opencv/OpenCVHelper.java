package cn.edu.zafu.opencv;

/**
 * User:lizhangqu(513163535@qq.com)
 * Date:2015-10-30
 * Time: 22:21
 */
public class OpenCVHelper {
    static {
        System.loadLibrary("OpenCV");
    }
    public static native int[] gray(int[] buf, int w, int h);
}
