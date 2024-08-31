package pages.saucedemoPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.PageBase;
import pages.constants.GeneralConstants;

public class CartPage extends PageBase {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    LoginPage loginPage = new LoginPage(driver);

    java.util.Properties Properties = propertiesReader.loadPropertiesFromFile(GeneralConstants.TEST_DATA_CONFIGURATION_FILE_NAME);

    private final String username = Properties.getProperty(GeneralConstants.LOGIN_USERNAME);
    private final String password = Properties.getProperty(GeneralConstants.LOGIN_PASSWORD);

    By addToCartFirstItem = By.xpath("(//button[@class='btn btn_primary btn_small btn_inventory '])[1]");
    By CartIconCount = By.xpath("//span[@class='shopping_cart_badge']");
    By removeFromCartFirstItem = By.id("remove-sauce-labs-backpack");
    By cartIcon = By.xpath("//a[@class='shopping_cart_link']");
    By cartItems = By.xpath("//div[@class='cart_item']");
    By removedItemsFromCart = By.xpath("//div[@class='removed_cart_item']");


    public void navigateToCartListPage() throws Exception {
        loginPage.navigateToLoginPage();
        loginPage.enterUserName(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
    }

    public void clickOnAddToCartButton() throws Exception {
        click(addToCartFirstItem);
    }

    public void clickOnRemoveFromCartButton() throws Exception {
        click(removeFromCartFirstItem);
    }

    public void clickOnCartIcon() throws Exception {
        click(cartIcon);
    }

    public boolean assertOnItemInCart(){
        return !findElements(cartItems).isEmpty();
    }

    public boolean assertOnItemRemovedFromCart(){
        return !findElements(removedItemsFromCart).isEmpty();
    }

    public boolean assertOnItemsCount(String count) throws Exception {
        if (!findElements(CartIconCount).isEmpty())
        return getText(CartIconCount).equals(count);
        else return false;
    }




}
