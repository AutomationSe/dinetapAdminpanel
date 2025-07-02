package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.PlatformAccountsPage;
import utils.ExcelReader;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PlatformAccountsTest extends BaseTest {

    @BeforeClass
    public void doLoginOnce() {
        loginAs("senel@gmail.com", "Senel2314@");

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[normalize-space()='Rest_777000']")
        ));
    }

    @DataProvider(name = "platformAccountData")
    public Iterator<Object[]> getPlatformAccountData() {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\platformaccounts.xlsx";
        String sheetName = "Sheet1";
        List<Map<String, String>> dataList = ExcelReader.getData(filePath, sheetName);

        return dataList.stream()
                .map(row -> new Object[]{
                        row.getOrDefault("accountname", ""),
                        row.getOrDefault("accountId", ""),
                        row.getOrDefault("secretKey", ""),
                        row.getOrDefault("publishableKey", ""),
                        row.getOrDefault("webhookSecretKey", "")
                })
                .iterator();
    }

    @Test(dataProvider = "platformAccountData")
    public void testCreateNewPlatformAccount(String accountName, String accountId, String secretKey, String publishableKey, String webhookSecretKey) {

        PlatformAccountsPage page = new PlatformAccountsPage(getDriver());

        page.navigateToPlatformAccounts();
        page.clickNewAccount();

        page.enterAccountName(accountName);
        page.enterAccountID(accountId);
        page.enterSecretKey(secretKey);
        page.enterPublishableKey(publishableKey);
        page.enterPaymentWebhookKey(webhookSecretKey);

        page.clickCreate();

        System.out.println("✅ Submitted platform account for: " + accountName);
    }
}
