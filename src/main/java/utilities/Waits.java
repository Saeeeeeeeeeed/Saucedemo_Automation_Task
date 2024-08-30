package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class Waits {

    WebDriver driver;

    public Waits(WebDriver driver) {
        this.driver = driver;
    }

    private static final Duration waitTime = Duration.ofSeconds(30);
    private static final Duration pollingTime = Duration.ofMillis(500);

    /////////////////////////////// PRESENCE ///////////////////////////////

    public WebElement waitForElementToBePresent(By locator) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(waitTime)
                .pollingEvery(pollingTime)
                .ignoring(NoSuchElementException.class)
                .ignoring(org.openqa.selenium.StaleElementReferenceException.class);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }


    /////////////////////////////// PAGE LOAD ///////////////////////////////

    public void waitForPageLoad() {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(waitTime)
                .pollingEvery(pollingTime)
                .ignoring(NoSuchElementException.class)
                .ignoring(org.openqa.selenium.StaleElementReferenceException.class);
        wait.until((ExpectedCondition<Boolean>) webDriver ->
        {
            assert webDriver != null;
            return ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete");
        });
    }


    /////////////////////////////// VISIBILITY ///////////////////////////////
    public WebElement waitForVisibility(By locator) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(waitTime)
                .pollingEvery(pollingTime)
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(org.openqa.selenium.StaleElementReferenceException.class);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForVisibility(WebElement element) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(waitTime)
                .pollingEvery(pollingTime)
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(org.openqa.selenium.StaleElementReferenceException.class);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForInvisibility(By locator) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(waitTime)
                .pollingEvery(pollingTime)
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementClickInterceptedException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(org.openqa.selenium.StaleElementReferenceException.class);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitForVisibilityOfList(By locator) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(waitTime)
                .pollingEvery(pollingTime)
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(org.openqa.selenium.StaleElementReferenceException.class);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
    public void waitForPresenceOfList(By locator) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(waitTime)
                .pollingEvery(pollingTime)
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(org.openqa.selenium.StaleElementReferenceException.class);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }
    public void waitForVisibilityOfList(List<WebElement> element) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(waitTime)
                .pollingEvery(pollingTime)
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(org.openqa.selenium.StaleElementReferenceException.class);
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }

    /////////////////////////////// CLICKABLE ///////////////////////////////

    public WebElement waitForElementToBeClickable(By locator) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(waitTime)
                .pollingEvery(pollingTime)
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementClickInterceptedException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(org.openqa.selenium.StaleElementReferenceException.class);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));

    }

    public WebElement waitForElementToBeClickable(WebElement element) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(waitTime)
                .pollingEvery(pollingTime)
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementClickInterceptedException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(org.openqa.selenium.StaleElementReferenceException.class);
        return wait.until(ExpectedConditions.elementToBeClickable(element));

    }

    /////////////////////////////// TEXT TO CHANGE ///////////////////////////////

    public void waitForTextToBePresentInElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, driver.findElement(locator).getText()));
    }

    /////////////////////////////// LIST ///////////////////////////////
    public List<WebElement> waitForListToBeNotEmpty(By locator) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(waitTime)
                .pollingEvery(pollingTime)
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(org.openqa.selenium.StaleElementReferenceException.class);
        return wait.until(driver -> {
            List<WebElement> elements = driver.findElements(locator);
            return elements.isEmpty() ? null : elements;
        });
    }

    public List<WebElement> waitForListToHaveSizeOne(By locator) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(waitTime)
                .pollingEvery(pollingTime)
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(org.openqa.selenium.StaleElementReferenceException.class);

        return wait.until(driver -> {
            List<WebElement> elements = driver.findElements(locator);
            return elements.size() == 1 ? elements : null;
        });
    }


}
