package com.automationexercices.utils;

import com.automationexercices.utils.Logs.LogsManager;

import java.io.IOException;

public class TerminalUtils {
    public static void executeTerminalCommand(String... commandParts) {
        try {

            Process process = Runtime.getRuntime().exec(commandParts); //allure generate -o reports --single-file --clean --- 2line da 2le by5leny can a7ot 2lcommend da 3la 2l terminal
            int exitCode = process.waitFor();//lma 2l process de t5ls hayrg3 ll exitcode zero we kda ma3na an mafe4 8alta
            if (exitCode != 0) {
                LogsManager.Error("Command failed with exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            LogsManager.Error("Failed to execute terminal command: " + String.join(" ", commandParts), e.getMessage());
        }
    }
}


    //TerminalUtils class is a utility class that provides a method to execute terminal commands. like allure generate 3azy excute 2l terminal commend as a coded
