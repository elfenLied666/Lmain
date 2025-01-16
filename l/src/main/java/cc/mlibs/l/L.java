package cc.mlibs.l;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.concurrent.ConcurrentHashMap;

public class L {
    public static final String TAG = "i_tag";
    public static final String ERR_TAG = "e_tag";
    public static final String D_TAG = "d_tag";
    public static final String TIME_MEASURE = "t_tag";


    private static ConcurrentHashMap<Integer, Long> times = new ConcurrentHashMap<>();
    private static int uniqueKey = 0;

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

    /**
     * Starts measuring time and assigns a unique key to the operation.
     *
     * @return a unique key (Integer) associated with the operation being measured.
     */
    public static Integer startMeasureTime() {
        int key = uniqueKey++;
        long startTime = System.nanoTime();
        times.put(key, startTime);
        return key;
    }

    /**
     * Ends measuring time for the given key and removes the associated record.
     *
     * @param keyTime the unique key returned by {@code startMeasureTime()}.
     * @return the elapsed time in milliseconds, or {@code null} if the key does not exist.
     */
    public static Long endMeasureTime(Integer keyTime) {
        Long startTime = times.remove(keyTime);
        if (startTime == null) {
            System.err.println("Key is not found: " + keyTime);
            return null;
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000;
    }

    /**
     * Ends measuring time for the given key, with an option to retain the key in the map.
     *
     * @param keyTime  the unique key returned by {@code startMeasureTime()}.
     * @param isRemove if {@code true}, the key will be removed; otherwise, it will be retained.
     * @return the elapsed time in milliseconds, or {@code null} if the key does not exist.
     */
    public static Long endMeasureTime(Integer keyTime, Boolean isRemove) {
        if(isRemove) {
             return endMeasureTime(keyTime);
        } else {
            Long startTime = times.get(keyTime);
            if (startTime == null) {
                System.err.println("Key is not found: " + keyTime);
                return null;
            }
            long endTime = System.nanoTime();
            return (endTime - startTime) / 1_000_000;
        }
    }

    /**
     * Ends measuring time for the given key, removes the key, and logs the elapsed time.
     *
     * @param keyTime the unique key returned by {@code startMeasureTime()}.
     * @return the elapsed time in milliseconds, or {@code null} if the key does not exist.
     */
    public static Long endMeasureTimeLog(Integer keyTime) {
        Long timePassed = endMeasureTime(keyTime);
        L.i(TIME_MEASURE, "Time has passed: " + timePassed);
        return timePassed;
    }

    /**
     * Ends measuring time for the given key with an option to retain the key,
     * logs the elapsed time, and optionally removes the key from the map.
     *
     * @param keyTime  the unique key returned by {@code startMeasureTime()}.
     * @param isRemove if {@code true}, the key will be removed; otherwise, it will be retained.
     * @return the elapsed time in milliseconds, or {@code null} if the key does not exist.
     */
    public static Long endMeasureTimeLog(Integer keyTime, Boolean isRemove) {
        Long timePassed = 0L;
        if(isRemove) {
            timePassed = endMeasureTime(keyTime);
            L.i(TIME_MEASURE, "Time has passed: " + timePassed);
            return timePassed;
        } else {
            Long startTime = times.get(keyTime);
            if (startTime == null) {
                System.err.println("Key is not found: " + keyTime);
                return null;
            }
            long endTime = System.nanoTime();
            timePassed = (endTime - startTime) / 1_000_000;
            return timePassed;
        }
    }

    /**
     * Clears all stored time records and resets the unique key counter.
     */
    public static void clearMeasureTime() {
        times.clear();
        uniqueKey = 0;
    }
}
