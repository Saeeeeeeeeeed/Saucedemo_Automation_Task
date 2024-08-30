package com.test.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import pages.constants.GeneralConstants;
import pages.constants.GeneralPaths;
import utilities.PropertiesReader;

import java.util.Properties;

public class ExtentManager {

    public static PropertiesReader propertiesReader = new PropertiesReader();
    public static final Properties pathsProperties = propertiesReader.loadPropertiesFromFile(GeneralConstants.PATHS_CONFIGURATION_FILE_NAME);
        public static ExtentHtmlReporter htmlReporter;
        public static ExtentReports extent;
        public static ExtentTest test;
        public static final String extentXmlPath = System.getProperty(GeneralConstants.USER_DIR) + pathsProperties.getProperty(GeneralPaths.EXTENT_CONFIG_XML);
        public static final String extentReportDirectory = System.getProperty(GeneralConstants.USER_DIR)+pathsProperties.getProperty(GeneralPaths.EXTENT_REPORT_DIRECTORY)+GeneralConstants.EXTENT_REPORT_NAME;

        public static void setExtent() {
            htmlReporter = new ExtentHtmlReporter(extentReportDirectory) {
                @Override
                public int hashCode() {
                    return super.hashCode();
                }
            };
            htmlReporter.loadXMLConfig(extentXmlPath);

            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);

            System.out.println("Extent XML Path: " + extentXmlPath);
            System.out.println("Extent Report Directory: " + extentReportDirectory);
        }

        public static void endReport() {
            extent.flush();
        }

}
