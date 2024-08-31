package pages.constants;


import pages.base.PageBase;

import java.util.List;

public class GeneralConstants {

    public static final String USER_DIR = "user.dir";
    public static final String PATHS_CONFIGURATION_FILE_NAME = "pathsconfig.properties";
    public static final String TEST_DATA_CONFIGURATION_FILE_NAME = "generalConfig.properties";
    public static final String EXTENT_REPORT_NAME = "TestExecutionReport.html";
    public static final String LOGIN_USERNAME = "username";
    public static final String LOGIN_PASSWORD = "password";
    public static final String LOGIN_PAGE_URL = "login.page.url";
    public static final String SKIPPED_MESSAGE = "Skipped Test Case";
    public static final String APP_LOGO_TITLE = "Swag Labs";
    public static final String INVALID_PASSWORD = "invalid.password";
    public static final String INVALID_USERNAME = "invalid.username";
    public static final String INVALID_CREDENTIALS_ERROR_MESSAGE = "Epic sadface: Username and password do not match any user in this service";
    public static final List<String> PRODUCT_NAME_LIST = List.of(
            "Sauce Labs Backpack",
            "Sauce Labs Bike Light",
            "Sauce Labs Bolt T-Shirt",
            "Sauce Labs Fleece Jacket",
            "Sauce Labs Onesie",
            "Test.allTheThings() T-Shirt (Red)"
    );
    public static final List<String> PRICE_LIST = List.of(
            "$29.99",
            "$9.99",
            "$15.99",
            "$49.99",
            "$7.99",
            "$15.99"
    );

    public static final String SORT_PRICE_LOW_TO_HIGH = "Price (low to high)";
    public static final String SORT_PRICE_HIGH_TO_LOW = "Price (high to low)";

}
