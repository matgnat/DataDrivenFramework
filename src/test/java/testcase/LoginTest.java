package testcase;

import base.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @Test
    public void loginAsBankManager() {

        log.debug("Start login test");
        driver.findElement(By.cssSelector(locators.getProperty("bankManagerLogInBtn"))).click();
        log.debug("Login succesfully executed");

    }
}
