package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.UserPage;
import utils.ExcelReader;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class UserTest extends BaseTest {

    @BeforeClass
    public void doLoginOnce() {
        loginAs("senel@gmail.com", "Senel2314@");

        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[normalize-space()='Rest_777']")
                )
        );
    }

    @DataProvider(name = "userData")
    public Object[][] getUserData() {
        String path = "C:\\Users\\senel\\Desktop\\dinetapAdminpanel\\src\\test\\resources\\users.xlsx";
        List<Map<String, String>> data = ExcelReader.getData(path, "Sheet1");
        Object[][] result = new Object[data.size()][1];
        for (int i = 0; i < data.size(); i++) {
            result[i][0] = data.get(i);
        }
        return result;
    }

    @Test(dataProvider = "userData")
    public void createUserTest(Map<String, String> userData) {
        UserPage userPage = new UserPage(getDriver());

        userPage.openUsersPage();
        userPage.clickNewUser();

        userPage.fillName(userData.get("Name"));
        userPage.fillEmail(userData.get("Email"));
        userPage.selectRoles(userData.get("Role"));
        userPage.fillPhone(userData.get("Phone"));
        userPage.selectCountry(userData.get("Country"));
        userPage.fillPassword(userData.get("Password"));
        userPage.clickCreate();
        userPage.closeModalIfOpen();

        System.out.println("✅ Created user: " + userData.get("Email"));
    }
}
