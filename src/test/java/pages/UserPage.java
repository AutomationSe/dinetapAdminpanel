package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public UserPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    private final By usersMenuLink = By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]/div/div[2]/div[1]/div/div/div/ul/li[3]/a");
    private final By newUserButton = By.xpath("/html/body/div[1]/div[2]/div[2]/main/div/div[2]/div/div[1]/div[1]/div[2]/button");

    private final By nameInput = By.xpath("//label[contains(text(),'Name')]/following::input[1]");
    private final By emailInput = By.xpath("//label[contains(text(),'Email')]/following::input[1]");
    private final By phoneInput = By.xpath("//label[contains(text(),'Phone Number')]/following::input[1]");
    private final By passwordInput = By.xpath("//input[@placeholder='E.g: ••••••••••']");
    private final By createButton = By.xpath("//button[normalize-space()='Create']");

    private final By countryDropdown = By.xpath("(//div[contains(@aria-label,'Select')])[5]");
    private final By singaporeOption = By.xpath("//div[@role='option' and normalize-space()='Singapore']");
    private final By australiaOption = By.xpath("//div[@role='option' and normalize-space()='Australia']");

    private final By roleDropdown = By.xpath("(//div[contains(@aria-label,'Select')])[4]");
    private String roleOptionXpath(String role) {
        return "(//div[@class='flex items-center gap-2'][normalize-space()='" + role + "'])[1]";
    }

    private final By closeModalButton = By.xpath("//button[contains(@class, 'absolute') and @aria-label='Close']");
    private final By backdrop = By.xpath("//div[contains(@class, 'bg-black') and contains(@class,'fixed')]");

    // Actions
    public void openUsersPage() {
        wait.until(ExpectedConditions.elementToBeClickable(usersMenuLink)).click();
    }

    public void clickNewUser() {
        wait.until(ExpectedConditions.elementToBeClickable(newUserButton)).click();
    }

    public void fillName(String name) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput));
        input.clear();
        input.sendKeys(name);
    }

    public void fillEmail(String email) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        input.clear();
        input.sendKeys(email);
    }

    public void fillPhone(String phone) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneInput));
        input.clear();
        input.sendKeys(phone);
    }

    public void fillPassword(String password) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        input.clear();
        input.sendKeys(password);
    }

    public void selectCountry(String country) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(countryDropdown));
        dropdown.click();
        if (country.equalsIgnoreCase("Singapore")) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(singaporeOption)).click();
        } else if (country.equalsIgnoreCase("Australia")) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(australiaOption)).click();
        }
    }

    public void selectRoles(String roles) {
        String[] roleList = roles.split(",");
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(roleDropdown));
        dropdown.click();
        for (String role : roleList) {
            String trimmed = role.trim();
            By option = By.xpath(roleOptionXpath(trimmed));
            wait.until(ExpectedConditions.elementToBeClickable(option)).click();
        }
    }

    public void clickCreate() {
        wait.until(ExpectedConditions.elementToBeClickable(createButton)).click();
    }

    public void closeModalIfOpen() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(closeModalButton)).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(backdrop));
        } catch (Exception e) {
            System.out.println("Modal already closed or not present.");
        }
    }
}
