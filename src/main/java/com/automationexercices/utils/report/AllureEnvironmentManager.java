package com.automationexercices.utils.report;

import com.automationexercices.utils.Logs.LogsManager;
import com.google.common.collect.ImmutableMap;

import java.io.File;

import static com.automationexercices.utils.dataReader.PropertyReader.GetProperty;
import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

public class AllureEnvironmentManager {
    public static void setEnvironmentVariables() {
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("OS", GetProperty("os.name"))
                        .put("Java version:", GetProperty("java.runtime.version"))
                        .put("Browser", GetProperty("browserType"))
                        .put("Execution Type", GetProperty("executionType"))
                        .put("URL", GetProperty("baseUrlWeb"))
                        .build(), String.valueOf(AllureConstants.RESULTS_FOLDER) + File.separator
        );
        LogsManager.Info("Allure environment variables set.");
        AllureBinaryManager.downloadAndExtract();
    }
}

