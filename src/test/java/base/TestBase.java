package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static Properties confing = new Properties();
    public static Properties locators = new Properties();
    public static FileInputStream fileInputStream;
    public static WebDriver driver;

    @BeforeSuite
    public void setUp() {

        if (driver == null) {

            String genericDir = System.getProperty("user.dir");

            try {
                fileInputStream = new FileInputStream("/Users/wir03/Documents/java_project/DataDrivenFramework/src/test/resources/properties/Config.properties");
                //fileInputStream = new FileInputStream(genericDir + "/src/test/resources/properties/Config.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            try {
                confing.load(fileInputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                fileInputStream = new FileInputStream("/Users/wir03/Documents/java_project/DataDrivenFramework/src/test/resources/properties/Locators.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            try {
                locators.load(fileInputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }


            if (confing.getProperty("browser").equals("chrome")) {

                System.getProperty("webdriver.chrome.driver", "/Users/wir03/Documents/java_project/DataDrivenFramework/src/test/resources/executables/chromedriver.exe");
                driver = new ChromeDriver();

            } else if (confing.getProperty("browser").equals("ie")){

                System.getProperty("webdriver.ie.driver", genericDir + "/src/test/resources/executables/IEDriverServer.exe");
                driver = new InternetExplorerDriver();

            } else if (confing.getProperty("browser").equals("firefox")) {

                //if working on early release of driver use below line
                System.getProperty("webdriver.gecko.driver", genericDir + "/src/test/resources/executables/geckodriver.exe");
                driver = new FirefoxDriver();
            }

            driver.get(confing.getProperty("urlsite"));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(confing.getProperty("implicit.wait")), TimeUnit.SECONDS);
        }

    }

    @AfterSuite
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }


    }

}
