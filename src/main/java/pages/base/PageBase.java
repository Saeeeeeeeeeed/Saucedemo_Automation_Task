package pages.base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import pages.constants.GeneralConstants;
import pages.constants.GeneralPaths;
import utilities.PropertiesReader;
import utilities.Waits;

import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;

public class PageBase {

    public WebDriver driver;
    JavascriptExecutor jse;

    protected Waits waits;
    Actions actions;

    public static PropertiesReader propertiesReader = new PropertiesReader();
    public static final Properties pathsProperties = propertiesReader.loadPropertiesFromFile(GeneralConstants.PATHS_CONFIGURATION_FILE_NAME);
    public static final String downloadDirectoryPath = System.getProperty(GeneralConstants.USER_DIR) + pathsProperties.getProperty(GeneralPaths.DOWNLOAD_DIRECTORY);
    private static final String browserDefaultUploadPath = System.getProperty(GeneralConstants.USER_DIR) + pathsProperties.getProperty(GeneralPaths.UPLOAD_DIRECTORY);

    private static final Duration waitTime = Duration.ofSeconds(30);
    private static final Duration pollingTime = Duration.ofMillis(500);

    public PageBase(WebDriver driver) {
        this.driver = driver;
        this.jse = (JavascriptExecutor) driver;
        this.waits = new Waits(driver);
    }

    //////////////////////////////// ELEMENTS HANDLING ////////////////////////////////
    public WebElement findElement(By locator) {
        return waits.waitForElementToBePresent(locator);
    }

    public List<WebElement> findElements(By locator) {

        return driver.findElements(locator);
    }

    public boolean isDisplayed(By locator) {
        return findElement(locator).isDisplayed();
    }

    public boolean isElementPresent(By locator) {
        try {
            findElement(locator);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    //////////////////////////////// LIST HANDLING ////////////////////////////////
    public WebElement getElementFromListByIndex(By listLocator, int index) {
        waits.waitForVisibility(listLocator);
        List<WebElement> elements = driver.findElements(listLocator);
        if (index < 0 || index >= elements.size()) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for list of size " + elements.size());
        }
        return elements.get(index);
    }

    public WebElement getElementFromListByText(By listLocator, String targetText) throws Exception {
        waits.waitForVisibility(listLocator);
        List<WebElement> elements = driver.findElements(listLocator);
        for (WebElement element : elements) {
            if (element.getText().trim().toLowerCase().contains(targetText.trim().toLowerCase())) {
                return element;
            }
        }
        throw new NoSuchElementException("Element with text " + targetText + " not found");
    }

    public int getElementIndexByText(By locator, String text) {
        List<WebElement> elements = driver.findElements(locator);
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).getText().equals(text)) {
                return i;
            }
        }
        throw new NoSuchElementException("Element with text " + text + " not found");
    }

    public WebElement getElementFromListByWholeText(By listLocator, String targetText) throws Exception {
        waits.waitForVisibility(listLocator);
        List<WebElement> elements = findElements(listLocator);
        for (WebElement element : elements) {
            if (element.getText().trim().equalsIgnoreCase(targetText.trim())) {
                return element;
            }
        }
        throw new NoSuchElementException("Element with text " + targetText + " not found");
    }

    public String getTheElementTextByIndex(By locator, int index) throws Exception {
        if (locator != null) {
            return getText(getElementFromListByIndex(locator, index));
        } else
            throw new Exception("Web element 'locator' is null .. it could not be located");
    }

    public String getTheLastElementText(By locator) throws Exception {
        if (locator != null) {
            return getText(getElementFromListByIndex(locator, findElements(locator).size() - 1));
        } else
            throw new Exception("Web element 'locator' is null .. it could not be located");
    }

    public void selectFromListByIndex(By listLocator, int index) throws Exception {
        if (listLocator != null) {
            waits.waitForVisibilityOfList(listLocator);
            waits.waitForElementToBeClickable(getElementFromListByIndex(listLocator, index)).click();
        } else
            throw new Exception("Web element 'locator' is null .. it could not be located");
    }

    public void selectFromListByText(By listLocator, String targetText) throws Exception {
        waits.waitForVisibility(listLocator);
        getElementFromListByText(listLocator, targetText).click();
    }

    public void selectFromListByWholeText(By listLocator, String targetText) throws Exception {
        waits.waitForVisibility(listLocator);
        getElementFromListByWholeText(listLocator, targetText).click();
    }

    // Method to click on a random element in a list of WebElements
    public void selectRandomElementFromList(By locator) {
        waits.waitForVisibility(locator);
        List<WebElement> elements = findElements(locator);
        if (elements == null || elements.isEmpty()) {
            System.out.println("The list of WebElements is empty or null.");
            return;
        }
        Random random = new Random();
        int randomIndex = random.nextInt(elements.size());
        waits.waitForElementToBeClickable(elements.get(randomIndex)).click();
    }

    public List<String> getListValues(By list) throws Exception {
        waits.waitForVisibility(list);
        List<String> values = new LinkedList<>();
        List<WebElement> elementList = findElements(list);
        for (WebElement element : elementList) {
            values.add(getText(element));
        }
        return values;
    }
    public List<String> getListValues(List<WebElement> elements) throws Exception {
        List<String> textList = new ArrayList<>();
        for (WebElement element : elements) {
            textList.add(getText(element));
        }
        return textList;
    }

    public int getListSize(By list) {
        return findElements(list).size();
    }

    public boolean checkTextInListOfElements(By locator, String targetText) throws Exception {
        waits.waitForListToBeNotEmpty(locator);
        List<WebElement> elements = findElements(locator);
        for (WebElement element : elements) {
            String elementText = getText(element);
            if (elementText.contains(targetText)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkValueInList(List<String> options, String... searchTerms) {
        for (String term : searchTerms) {
            boolean found = false;
            for (String option : options) {
                if (option.contains(term)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("the item [" + term + "] does not found in the list");
                return false;
            }
        }
        return true;
    }

    public boolean isTextPresentInAllElements(By locator, String targetText) throws Exception {
        waits.waitForVisibilityOfList(locator);
        List<WebElement> elements = findElements(locator);
        for (WebElement element : elements) {
            String elementText = getText(element);
            if (!elementText.contains(targetText)) {
                return false;
            }
        }
        return true;
    }

    //////////////////////////////// CLICK HANDLING////////////////////////////////
    public void click(By locator) throws Exception {
        if (locator != null) {
            waits.waitForElementToBeClickable(locator).click();
        } else
            throw new Exception("Web element 'locator' is null .. it could not be located");
    }

    public void click(WebElement element) throws Exception {
        if (element != null) {
            waits.waitForElementToBeClickable(element).click();
        } else
            throw new Exception("Web element 'locator' is null .. it could not be located");
    }

    public void clickJSE(By locator) throws Exception {
        if (locator != null) {
            jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].click();", waits.waitForElementToBeClickable(locator));
        } else
            throw new Exception("Web element 'locator' is null .. it could not be located");
    }

    public void clickJSE(WebElement element) throws Exception {
        if (element != null) {
            jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].click();", waits.waitForElementToBeClickable(element));
        } else
            throw new Exception("Web element 'locator' is null .. it could not be located");
    }

    public void scrollAndClickByJSE(By locator) throws Exception {
        if (locator != null) {
            scrollToElement(locator);
            clickJSE(locator);
        } else
            throw new Exception("Web element 'locator' is null .. it could not be located");
    }

    public void scrollAndClickByJSE(WebElement element) throws Exception {
        if (element != null) {
            scrollToElement(element);
            clickJSE(element);
        } else
            throw new Exception("Web element 'locator' is null .. it could not be located");
    }


    //////////////////////////////// TEXT HANDLING ////////////////////////////////
    public void setText(By locator, String text) throws Exception {
        if (locator != null) {
            scrollAndClickByJSE(locator);
            clear(locator);
            findElement(locator).sendKeys(text);
            loseFocusFromField();
        } else
            throw new Exception("Web element 'locator' is null .. it could not be located");
    }

    public String getText(By locator) throws Exception {
        if (locator != null) {
            waits.waitForVisibility(locator);
            return findElement(locator).getText();
        } else
            throw new Exception("Web element 'locator' is null .. it could not be located");
    }

    public String getText(WebElement element) throws Exception {
        if (element != null) {
            waits.waitForVisibility(element);
            return element.getText();
        } else
            throw new Exception("Web element 'locator' is null .. it could not be located");
    }

    public String getTextBoxCurrentValue(By locator) throws Exception {
        if (locator != null) {
            waits.waitForVisibility(locator);
            return findElement(locator).getAttribute("value");
        } else
            throw new Exception("Web element 'locator' is null .. it could not be located");
    }

    public void clear(By locator) throws Exception {
        if (locator != null) {
            waits.waitForVisibility(locator);
            findElement(locator).clear();
        } else
            throw new Exception("Web element 'locator' is null .. it could not be located");

    }

    public void clearUsingBackspace(By locator, int counter) throws Exception {
        if (locator != null) {
            waits.waitForVisibility(locator);
            for (int i=0 ; i<counter; i++)
                findElement(locator).sendKeys(Keys.BACK_SPACE);
        } else
            throw new Exception("Web element 'locator' is null .. it could not be located");
    }

    public void loseFocusFromField()  {
        jse = (JavascriptExecutor) driver;
        jse.executeScript("if (document.activeElement) document.activeElement.blur();");
    }

    public String getLastNChars(String input, int n) {
        if (input == null || n <= 0) {
            return "";
        }
        if (input.length() <= n) {
            return input;
        }
        return input.substring(input.length() - n);
    }

    //////////////////////////////// SCROLL HANDLING ////////////////////////////////
    public void scrollToElement(By locator) {
        jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", findElement(locator));
    }

    public void scrollToElement(WebElement element) {
        jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    public void scrollIntoViewAndClick(By locator) throws Exception {
        scrollToElement(locator);
        clickJSE(locator);
    }

    public void scrollDown() {
        jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,200)", "");
    }

    public void scrollToPageEnd() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void scrollIntoView(By locator) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", findElement(locator));
    }

    public void scrollToElementUsingTextPresentInElement(By locator) {
        jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView();", findElement(locator));
        waits.waitForTextToBePresentInElement(locator);
    }

    public void scrollEndOfPageUsingKeys(By locator) {
        driver.findElement(locator).sendKeys(Keys.END);
    }

    public void hover(By locator) {
        waits.waitForVisibility(locator);
        actions = new Actions(driver);
        actions.moveToElement(findElement(locator)).perform();
    }

    public void navigateTo(String URL) {
        driver.get(URL);
    }

}
