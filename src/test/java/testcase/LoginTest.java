package testcase;

import base.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @Test
    public void loginAsBankManager() {

        driver.findElement(By.cssSelector(locators.getProperty("bankManagerLogInBtn"))).click();
    }
}
