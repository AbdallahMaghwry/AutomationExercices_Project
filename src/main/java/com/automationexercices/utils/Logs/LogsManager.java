package com.automationexercices.utils.Logs;

import com.automationexercices.utils.report.AllureConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogsManager {
    public static final String LOGS_PATH = AllureConstants.USER_DIR + "/test-output/logs/";

    public static Logger logger(){
        return LogManager.getLogger(Thread.currentThread().getStackTrace()[3].getClassName());
    }
    //String mulitiple parameters to be logged in one line with space between them
    //info("error massage"+" " +e.getmassage()); -> info("error massage", e.getmassage());
    public static void Info(String... message){
        logger().info(String.join(" ", message));
    }
    public static void Error(String... message){
        logger().error(String.join(" ", message));
    }
    public static void Debug(String... message){
        logger().debug(String.join(" ", message));
    }
    public static void Warn(String... message){
        logger().warn(String.join(" ", message));
    }
    public static void Fatal(String... message){
        logger().fatal(String.join(" ", message));
    }
}
