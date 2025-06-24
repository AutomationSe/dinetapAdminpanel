package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PlatformAccountsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public PlatformAccountsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Locators
    private By platformAccountsLink = By.xpath("/html/body/div/div[2]/div[1]/div[2]/div/div[2]/div[3]/div/div/div/ul/li[1]/a");
    private By newAccountButton = By.xpath("/html/body/div/div[2]/div[2]/main/div/div[2]/div/div[1]/div[1]/div[2]/button");
    private By accountNameInput = By.xpath("//label[text()='Account Name']/following::input[1]");
    private By accountIDInput = By.xpath("//label[text()='Account ID']/following::input[1]");
    private By secretkeyInput = By.xpath("//label[text()='Secret Key']/following::input[1]");
    private By publishableKeyInput = By.xpath("//label[text()='Publishable Key']/following::input[1]");
    private By paymentwebhookInput = By.xpath("//label[text()='Payment Webhook Secret Key']/following::input[1]");


    public void navigateToPlatformAccounts() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(platformAccountsLink));
        link.click();
    }

    public void clickNewAccount() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(newAccountButton));
        button.click();
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
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(secretkeyInput));
        input.clear();
        input.sendKeys(key);
    }

    public void enterPublishableKey(String key) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(publishableKeyInput));
        input.clear();
        input.sendKeys(key);
    }

    public void enterPaymentWebhookKey(String key) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentwebhookInput));
        input.clear();
        input.sendKeys(key);
    }
}
