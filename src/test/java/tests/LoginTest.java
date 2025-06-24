package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ExcelReader;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Iterator<Object[]> loginDataProvider() {
        String excelPath = "src/test/resources/sendkeys.xlsx";
        String sheetName = "Sheet1"; // Make sure this matches your Excel sheet name

        List<Map<String, String>> testData = ExcelReader.getData(excelPath, sheetName);
        return testData.stream()
                .map(data -> new Object[]{data.get("email"), data.get("password")})
                .iterator();
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String email, String password) {
        WebDriver driver = getDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);

        // Wait up to 5 seconds for either success or failure element
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        boolean isRestaurantsVisible = false;
        boolean isLoginErrorVisible = false;

        try {
            isRestaurantsVisible = wait.until(ExpectedConditions.or(
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[@class='text-2xl font-bold tracking-tight']"))
            )) != null;
        } catch (Exception ignored) {}

        try {
            isLoginErrorVisible = driver.findElements(By.xpath("//div[@class='text-lg font-semibold']")).size() > 0;
        } catch (Exception ignored) {}


        if (isRestaurantsVisible && !isLoginErrorVisible) {
            System.out.println("✅ Login successful for: " + email);
            Assert.assertTrue(true);
        } else if (isLoginErrorVisible) {
            System.out.println("❌ Login failed for: " + email);
            Assert.fail("Login failed due to incorrect credentials: " + email);
        } else {
            System.out.println("⚠ Unexpected state for: " + email);
            Assert.fail("Unexpected login result for: " + email);
        }
    }
}
