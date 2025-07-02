package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class PlatformAccountsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public PlatformAccountsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By platformAccountsLink = By.xpath("/html/body/div/div[2]/div[1]/div[2]/div/div[2]/div[3]/div/div/div/ul/li[1]/a");
    private By newAccountButton = By.xpath("/html/body/div/div[2]/div[2]/main/div/div[2]/div/div[1]/div[1]/div[2]/button");

    private By accountNameInput = By.xpath("//label[text()='Account Name']/following::input[1]");
    private By accountIDInput = By.xpath("//label[text()='Account ID']/following::input[1]");
    private By secretKeyInput = By.xpath("//label[text()='Secret Key']/following::input[1]");
    private By publishableKeyInput = By.xpath("//label[text()='Publishable Key']/following::input[1]");
    private By webhookSecretKeyInput = By.xpath("//label[text()='Payment Webhook Secret Key']/following::input[1]");

    private By createButton = By.xpath("//button[normalize-space()='Create']");

    public void navigateToPlatformAccounts() {
        // Wait for modal or overlay to disappear
        try {
            By backdrop = By.cssSelector("div[class*='bg-black'][class*='fixed']");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(backdrop));
        } catch (TimeoutException | NoSuchElementException ignored) {}

        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(platformAccountsLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", link);
        link.click();
    }

    public void clickNewAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(newAccountButton)).click();
    }

    public void enterAccountName(String name) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(accountNameInput));
        input.clear();
        input.sendKeys(name);
    }

    public void enterAccountID(String id) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(accountIDInput));
        input.clear();
        input.sendKeys(id);
    }

    public void enterSecretKey(String key) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(secretKeyInput));
        input.clear();
        input.sendKeys(key);
    }

    public void enterPublishableKey(String key) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(publishableKeyInput));
        input.clear();
        input.sendKeys(key);
    }

    public void enterPaymentWebhookKey(String key) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(webhookSecretKeyInput));
        input.clear();
        input.sendKeys(key);
    }

    public void clickCreate() {
        wait.until(ExpectedConditions.elementToBeClickable(createButton)).click();
    }
}
