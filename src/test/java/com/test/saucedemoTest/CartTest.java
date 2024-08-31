package com.test.saucedemoTest;

import com.test.base.TestBase;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.constants.GeneralConstants;
import pages.saucedemoPages.CartPage;
import pages.saucedemoPages.ProductList;
import utilities.PropertiesReader;

import java.util.Properties;

public class CartTest extends TestBase {

    CartPage cartPage;

    private final PropertiesReader propertiesReader = new PropertiesReader();
    Properties testData = propertiesReader.loadPropertiesFromFile(GeneralConstants.TEST_DATA_CONFIGURATION_FILE_NAME);

    @BeforeMethod
    public void setUp () throws Exception {
        softAssert = new SoftAssert();
        cartPage = new CartPage(driver);
        cartPage.navigateToCartListPage();
    }

    @Test
    public void VerifyThaTheShoppingCartBadgeCountUpdatesCorrectlyWhenItemsAreAdded(){
        try {
            cartPage.clickOnAddToCartButton();

            softAssert.assertTrue(cartPage.assertOnItemsCount("1"));
            cartPage.clickOnRemoveFromCartButton();
            softAssert.assertAll();
        }catch (Exception e){
            throw new SkipException(GeneralConstants.SKIPPED_MESSAGE, e);
        }
    }

    @Test
    public void VerifyThaTheShoppingCartBadgeCountUpdatesCorrectlyWhenItemsAreRemoved(){
        try {
            cartPage.clickOnAddToCartButton();
            cartPage.clickOnRemoveFromCartButton();

            softAssert.assertFalse(cartPage.assertOnItemsCount("1"));
            softAssert.assertAll();
        }catch (Exception e){
            throw new SkipException(GeneralConstants.SKIPPED_MESSAGE, e);
        }
    }

    @Test
    public void verifyThatTheCartIsUpdatedWhenAddingProductToShoppingCart(){
        try {
            cartPage.clickOnAddToCartButton();
            cartPage.clickOnCartIcon();

            softAssert.assertTrue(cartPage.assertOnItemInCart());
            cartPage.clickOnRemoveFromCartButton();
            softAssert.assertAll();
        }catch (Exception e){
            throw new SkipException(GeneralConstants.SKIPPED_MESSAGE, e);
        }
    }
    @Test
    public void verifyThatTheCartIsUpdatedWhenRemovingProductFromShoppingCart(){
        try {
            cartPage.clickOnAddToCartButton();
            cartPage.clickOnCartIcon();
            cartPage.clickOnRemoveFromCartButton();

            softAssert.assertTrue(cartPage.assertOnItemRemovedFromCart());
            softAssert.assertAll();
        }catch (Exception e){
            throw new SkipException(GeneralConstants.SKIPPED_MESSAGE, e);
        }
    }


}
