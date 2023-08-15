package org.example;

public class Utils {
    public static void addDelay(int timeInMilli) {
        try {
            Thread.sleep(timeInMilli);
        } catch (Exception e){
            GSLogger.print(" Error-100001 Thread sleep Exception: "+ e.getLocalizedMessage());
        }
    }
}
