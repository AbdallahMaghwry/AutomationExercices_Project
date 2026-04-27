package com.automationexercices.utils;

import java.text.SimpleDateFormat;

public class TimeManager {

//screenshots - logs - reports
    public static String GetCurrentTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new java.util.Date());
    }

    //unique timestamp for each data
    //عشان مثلا في ال register هل كل ما اعمل اكونت هاقعد امسحو بعد ما اخلص ال run فا الفانكشن دي بتساعدنا في اننا عندنا الداتا تبقي unique
    public static String getSimpleTimestamp(){
        return  Long.toString(System.currentTimeMillis());
    }
}
