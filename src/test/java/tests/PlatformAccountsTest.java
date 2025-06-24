package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PlatformAccountsPage;

import java.time.Duration;

public class PlatformAccountsTest extends BaseTest {

    @Test
    public void testCreateNewPlatformAccount() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("senel@gmail.com", "Senel2314@");

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[normalize-space()='Rest_777000']")));

        PlatformAccountsPage page = new PlatformAccountsPage(getDriver());
        page.navigateToPlatformAccounts();
        page.clickNewAccount();

        page.enterAccountName("testing@gmail.com");
        page.enterAccountID("acct_1NGFvAELhx7onTSe");
        page.enterSecretKey("sk_test_1234567890");
        page.enterPublishableKey("pk_test_1234567890");
        page.enterPaymentWebhookKey("whsec_1234567890");

//        page.clickCreate();

        // Optional: Add validation/assertion after account is created
    }
}

