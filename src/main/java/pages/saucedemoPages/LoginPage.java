package pages.saucedemoPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.PageBase;
import pages.constants.GeneralConstants;

import java.util.Properties;

public class LoginPage extends PageBase {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    Properties urlProperties = propertiesReader.loadPropertiesFromFile(GeneralConstants.TEST_DATA_CONFIGURATION_FILE_NAME);

    By pageTitle = By.xpath("//div[@class='login_logo']");
    By userNameText = By.id("user-name");
    By passwordText = By.id("password");
    By loginButton = By.id("login-button");
    By appLogo = By.xpath("//div[@class='app_logo']");
    By invalidCredentialsError = By.xpath("//h3[@data-test='error']");
    By optionsDropList = By.id("react-burger-menu-btn");
    By logoutOption = By.id("logout_sidebar_link");

    public void navigateToLoginPage() {
        navigateTo(urlProperties.getProperty(GeneralConstants.LOGIN_PAGE_URL));
        if (findElements(pageTitle).isEmpty()){
            navigateTo(urlProperties.getProperty(GeneralConstants.LOGIN_PAGE_URL));
            waits.waitForVisibility(pageTitle);
        }
    }

    public void enterUserName (String username) throws Exception {
        setText(userNameText,username);
    }

    public void enterPassword (String password) throws Exception {
        setText(passwordText,password);
    }

    public void clickOnLoginButton() throws Exception {
        click(loginButton);
    }

    public void clickOnOptionsDropList() throws Exception {
        click(optionsDropList);
    }

    public void clickOnLogoutOption() throws Exception {
        click(logoutOption);
    }

    public boolean assertOnLogo () throws Exception {
        if (!findElements(appLogo).isEmpty()) {
            return getText(appLogo).equals(GeneralConstants.APP_LOGO_TITLE);
        }else return false;
    }

    public boolean assertOnLoginInvalidCredentials() throws Exception {
        return getText(invalidCredentialsError).equals(GeneralConstants.INVALID_CREDENTIALS_ERROR_MESSAGE);
    }

    public boolean assertOnLoginPage(){
        return !findElements(loginButton).isEmpty();
    }

}
