package pages.saucedemoPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.PageBase;
import pages.constants.GeneralConstants;

import java.util.Properties;

public class ProductList extends PageBase {
    public ProductList(WebDriver driver) {
        super(driver);
    }

    LoginPage loginPage = new LoginPage(driver);

    Properties Properties = propertiesReader.loadPropertiesFromFile(GeneralConstants.TEST_DATA_CONFIGURATION_FILE_NAME);

    private final String username = Properties.getProperty(GeneralConstants.LOGIN_USERNAME);
    private final String password = Properties.getProperty(GeneralConstants.LOGIN_PASSWORD);

    By productsList = By.xpath("//div[@class='inventory_item_name ']");
    By pageTitle = By.xpath("//div[@class='login_logo']");
    By priceList = By.xpath("//div[@class='inventory_item_price']");
    By descriptionList = By.xpath("//div[@class='inventory_item_desc']");
    By productName = By.xpath("//div[@class='inventory_details_name large_size']");
    By productPrice = By.xpath("//div[@class='inventory_details_price']");
    By productDescription = By.xpath("//div[@class='inventory_details_price']");

    public void navigateToProductListPage() throws Exception {
        loginPage.navigateToLoginPage();
        loginPage.enterUserName(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
    }

    public boolean assertOnProductsName() throws Exception {
        return getListSize(productsList) == 6 & getListValues(productsList).containsAll(GeneralConstants.PRODUCT_NAME_LIST);
    }

    public boolean assertOnProductsPrice() throws Exception {
        return getListSize(priceList) == 6 & getListValues(priceList).containsAll(GeneralConstants.PRICE_LIST);
    }

    public boolean assertOnDescription(){
        return getListSize(descriptionList) == 6;
    }

    public void clickOnFirstItem() throws Exception {
        click(findElements(productsList).get(1));
    }

    public String getProductName() throws Exception {
        return getText(productName);
    }

    public String getProductPrice() throws Exception {
        return getText(productPrice);
    }

    public boolean assertOnProductDescription(){
        return !findElements(productDescription).isEmpty();
    }
}
