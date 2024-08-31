package com.test.saucedemoTest;

import com.test.base.TestBase;
import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.constants.GeneralConstants;
import pages.saucedemoPages.LoginPage;
import pages.saucedemoPages.ProductList;
import utilities.PropertiesReader;

import java.util.Properties;

public class ProductListTest extends TestBase {

    ProductList productList;

    private final PropertiesReader propertiesReader = new PropertiesReader();
    Properties testData = propertiesReader.loadPropertiesFromFile(GeneralConstants.TEST_DATA_CONFIGURATION_FILE_NAME);

    @BeforeMethod
    public void setUp () throws Exception {
        softAssert = new SoftAssert();
        productList = new ProductList(driver);
        productList.navigateToProductListPage();
    }

    @Test
    public void validateThatAllProductsAreDisplayedCorrectly(){
        try {
            softAssert.assertTrue(productList.assertOnProductsName());
            softAssert.assertTrue(productList.assertOnProductsPrice());
            softAssert.assertTrue(productList.assertOnDescription());
            softAssert.assertAll();
        }catch (Exception e){
            throw new SkipException(GeneralConstants.SKIPPED_MESSAGE, e);
        }
    }

    @Test
    public void validateThatUserProductNameDisplayOnProductDetailsPage(){
        try {
            productList.clickOnFirstItem();

            softAssert.assertTrue(GeneralConstants.PRODUCT_NAME_LIST.contains(productList.getProductName()));
            softAssert.assertAll();
        }catch (Exception e){
            throw new SkipException(GeneralConstants.SKIPPED_MESSAGE, e);
        }
    }

    @Test
    public void validateThatUserProductPriceDisplayOnProductDetailsPage(){
        try {
            productList.clickOnFirstItem();

            softAssert.assertTrue(GeneralConstants.PRICE_LIST.contains(productList.getProductPrice()));
            softAssert.assertAll();
        }catch (Exception e){
            throw new SkipException(GeneralConstants.SKIPPED_MESSAGE, e);
        }
    }

    @Test
    public void validateThatUserProductDescriptionDisplayOnProductDetailsPage(){
        try {
            productList.clickOnFirstItem();

            softAssert.assertTrue(productList.assertOnProductDescription());
            softAssert.assertAll();
        }catch (Exception e){
            throw new SkipException(GeneralConstants.SKIPPED_MESSAGE, e);
        }
    }

    @Test
    public void validateThatUserCanSortItemsFromLowToHighPrice(){
        try {
            productList.selectProductSort(GeneralConstants.SORT_PRICE_LOW_TO_HIGH);

            softAssert.assertTrue(productList.assertOnPriceSort(GeneralConstants.SORT_PRICE_LOW_TO_HIGH));
            softAssert.assertAll();

        }catch (Exception e){
            throw new SkipException(GeneralConstants.SKIPPED_MESSAGE, e);
        }
    }

    @Test
    public void validateThatUserCanSortItemsFromHighToLowPrice(){
        try {
            productList.selectProductSort(GeneralConstants.SORT_PRICE_HIGH_TO_LOW);

            softAssert.assertTrue(productList.assertOnPriceSort(GeneralConstants.SORT_PRICE_HIGH_TO_LOW));
            softAssert.assertAll();

        }catch (Exception e){
            throw new SkipException(GeneralConstants.SKIPPED_MESSAGE, e);
        }
    }



//    @Test
//    public void test(){
//        System.out.println(driver.findElements(By.xpath("//div[@class='inventory_item_price']")).get(1).getText());
//    }


}
