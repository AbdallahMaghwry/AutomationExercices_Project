package com.automationexercices.utils;

import com.automationexercices.utils.Logs.LogsManager;
import com.automationexercices.utils.dataReader.PropertyReader;

import java.io.File;
import java.io.IOException;

import static com.automationexercices.utils.report.AllureConstants.USER_DIR;
import static org.apache.commons.io.FileUtils.copyFile;

public class FileUtils {
private static final String DIR_PATH = PropertyReader.GetProperty("user.dir")+File.separator;

    private FileUtils(){
        //prevent instantiation
    }

    // Renaming
    public static void renameFile(String oldName, String newName) {
        try {
            var targetFile = new File(oldName);
            String targetDirectory = targetFile.getParentFile().getAbsolutePath();
            File newFile = new File(targetDirectory + File.separator + newName);
            if (!targetFile.getPath().equals(newFile.getPath())) {
                copyFile(targetFile, newFile);
                org.apache.commons.io.FileUtils.deleteQuietly(targetFile);
                LogsManager.Info("Target File Path: \"" + oldName + "\", file was renamed to \"" + newName + "\".");
            } else {
                LogsManager.Info(("Target File Path: \"" + oldName + "\", already has the desired name \"" + newName + "\"."));
            }
        } catch (IOException e) {
            LogsManager.Error("Error Rename :"+e.getMessage());
        }
    }

    //creating Directory
    public static void createDirectory(String path){
        try {
            File file = new File(DIR_PATH + path);
            if (!file.exists()) {
                file.mkdirs();
                LogsManager.Info("Directory created: " + path);
            }
        }catch (Exception e){
            LogsManager.Error("Failed to create directory: " + path + " - " + e.getMessage());
        }
    }
    //Force delete
    public static void forceDeleteDirectory(File file){
        try {
            org.apache.commons.io.FileUtils.forceDeleteOnExit(file);
            LogsManager.Info("File deleted: " + file.getAbsolutePath());

        }
        catch (Exception e){
            LogsManager.Error("Failed to cleaning directory: " + file.getAbsolutePath() + " - " + e.getMessage());
        }
    }


    //cleaning Directory
    public static void cleanDirectory(File file){
        try {
            org.apache.commons.io.FileUtils.deleteQuietly(file);

        }
        catch (Exception e){
            LogsManager.Error("Failed to cleaning directory: " + file.getAbsolutePath() + " - " + e.getMessage());
        }
    }

    //check if the file exists
    public static boolean isFileExists(String filename){
        String filePath = System.getProperty("user.dir")+File.separator + "/src/test/resources/downloads/" ;
        File file = new File(filePath+ filename);
        return file.exists();
    }

    public static boolean isFileExist (String FileName , int NumberofRetries)
    {
        boolean isFileExist = false;
        int retries = 0;
        while (retries < NumberofRetries) {
            try{
                String filepath = USER_DIR +"/src/test/resources/downloads/";
                isFileExist = (new File(filepath + FileName)).getAbsoluteFile().exists();
            }catch (Exception e){
                LogsManager.Error("Error checking file existence: " + e.getMessage());
            }
            if (!isFileExist)
            {
                try{
                    Thread.sleep(500); // Wait for half second before retrying
                }catch (Exception e){
                    LogsManager.Error("Error waiting for file: " + e.getMessage());
                }
            }
            retries++;
        }
        return isFileExist;
    }

}
