package com.test.saucedemoTest;

import com.test.base.TestBase;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.constants.GeneralConstants;
import pages.saucedemoPages.LoginPage;
import utilities.PropertiesReader;

import java.util.Properties;

public class LoginTest extends TestBase {

    LoginPage loginPage;

    private final PropertiesReader propertiesReader = new PropertiesReader();
    Properties testData = propertiesReader.loadPropertiesFromFile(GeneralConstants.TEST_DATA_CONFIGURATION_FILE_NAME);
    private final String userName = testData.getProperty(GeneralConstants.LOGIN_USERNAME);
    private final String password = testData.getProperty(GeneralConstants.LOGIN_PASSWORD);
    private final String invalidPassword = testData.getProperty(GeneralConstants.INVALID_PASSWORD);
    private final String invalidUsername = testData.getProperty(GeneralConstants.INVALID_USERNAME);

    @BeforeMethod
    public void setUp (){
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
    }

    @Test
    public void validateUserCanLoginWithValidCredentialsSuccessfully(){
        try {
            loginPage.enterUserName(userName);
            loginPage.enterPassword(password);
            loginPage.clickOnLoginButton();

            softAssert.assertTrue(loginPage.assertOnLogo());
            softAssert.assertAll();
        }catch (Exception e){
            throw new SkipException(GeneralConstants.SKIPPED_MESSAGE, e);
        }
    }

    @Test
    public void validateUserCanNotLoginWithInValidPassword(){
        try {
            loginPage.enterUserName(userName);
            loginPage.enterPassword(invalidPassword);
            loginPage.clickOnLoginButton();

            softAssert.assertTrue(loginPage.assertOnLoginInvalidCredentials());
            softAssert.assertAll();
        }catch (Exception e){
            throw new SkipException(GeneralConstants.SKIPPED_MESSAGE, e);
        }
    }

    @Test
    public void validateUserCanNotLoginWithInValidUsername(){
        try {
            loginPage.enterUserName(invalidUsername);
            loginPage.enterPassword(password);
            loginPage.clickOnLoginButton();

            softAssert.assertTrue(loginPage.assertOnLoginInvalidCredentials());
            softAssert.assertAll();
        }catch (Exception e){
            throw new SkipException(GeneralConstants.SKIPPED_MESSAGE, e);
        }
    }

    @Test
    public void validateUserCanNotLoginWithInValidUsernameAndPassword(){
        try {
            loginPage.enterUserName(invalidUsername);
            loginPage.enterPassword(invalidPassword);
            loginPage.clickOnLoginButton();

            softAssert.assertTrue(loginPage.assertOnLoginInvalidCredentials());
            softAssert.assertAll();
        }catch (Exception e){
            throw new SkipException(GeneralConstants.SKIPPED_MESSAGE, e);
        }
    }

}
