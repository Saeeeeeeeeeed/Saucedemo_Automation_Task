package com.test.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pages.constants.GeneralConstants;
import pages.constants.GeneralPaths;
import utilities.PropertiesReader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class helper {

    private static final PropertiesReader propertiesReader = new PropertiesReader();
    private static final Properties generalConfigurationProperties = propertiesReader.loadPropertiesFromFile(GeneralConstants.PATHS_CONFIGURATION_FILE_NAME);

    public static String captureScreenshot(WebDriver driver, String screenShotName) {
        String userDir = System.getProperty(GeneralConstants.USER_DIR);
        String screenshotsDir = generalConfigurationProperties.getProperty(GeneralPaths.SCREEN_SHOTS_DIRECTORY);
        Path dest = Paths.get(userDir + screenshotsDir, screenShotName + ".png");

        try {
            Files.createDirectories(dest.getParent());
            FileOutputStream out = new FileOutputStream(dest.toString());
            out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
            out.close();
        } catch (IOException e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
        }

        return dest.toString();
    }
}
