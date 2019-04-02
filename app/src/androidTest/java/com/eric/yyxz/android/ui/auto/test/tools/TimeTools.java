package com.eric.yyxz.android.ui.auto.test.tools;

import java.util.concurrent.TimeUnit;

public class TimeTools {

    public static boolean isTimeout(long time, long timeout){
        return TimeUnit.MILLISECONDS.toMillis(System.currentTimeMillis() - time) > timeout;
    }

    public static void sleepBySeconds(long timeout){
        sleep(TimeUnit.SECONDS, timeout);
    }

    public static void sleepByMilliSeconds(long timeout){
        sleep(TimeUnit.MILLISECONDS, timeout);
    }

    public static void sleep(TimeUnit timeUnit, long timeout){
        try {
            timeUnit.sleep(timeout);
        } catch (InterruptedException ignored) {}
    }

}

