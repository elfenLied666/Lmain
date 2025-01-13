package cc.mlibs.l;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class L {
    private static final String TAG = "i_tag";
    private static final String ERR_TAG = "e_tag";
    private static final String D_TAG = "d_tag";

    public static void ilog(String info) {
        Log.i(TAG, info);
    }

    public static void i(String info) {
        Log.i(TAG, info);
    }

    public static void d(String info) {
        Log.d(D_TAG, info);
    }

    public static void e(String info) {
        Log.e(ERR_TAG, info);
    }

    public static void i(String tag, String info) {
        Log.i(tag, info);
    }

    public static void d(String tag, String info) {
        Log.d(tag, info);
    }

    public static void e(String tag, String info) {
        Log.e(tag, info);
    }

    public static void i() {
        Log.i(TAG, "");
    }

    public static void d() {
        Log.d(D_TAG, "");
    }

    public static void e() {
        Log.e(ERR_TAG, "");
    }

    public static void toastL(Context context, String info) {
        Toast.makeText(context, info, Toast.LENGTH_LONG).show();
    }

    public static void toastSh(Context context, String info) {
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
    }

    public static String toStr(String str) {
        return str != null && !str.isEmpty() ? str : "";
    }

    public static String toStr(String str, String ret) {
        return str != null && !str.isEmpty() ? str : ret;
    }
}
