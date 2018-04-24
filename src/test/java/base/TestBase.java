package base;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    /*
    * webdriver - done
    * properties - done
    * logs - log4j jar, - logger class
    * extend report -
    * DB -
    * excel -
    * mail -
    * reportNG, extend report -
    * jenkins -
    * */

    public static Properties config = new Properties();
    public static Properties locators = new Properties();
    public static FileInputStream fileInputStream;
    public static WebDriver driver;
    public static Logger log = Logger.getLogger("appLogger");


    @BeforeSuite
    public void setUp() {
        String genericDir = System.getProperty("user.dir");

        if (driver == null) {
            try {
                fileInputStream = new FileInputStream(genericDir + "\\src\\test\\resources\\properties\\Config.properties");
                System.out.println(fileInputStream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            try {
                config.load(fileInputStream);
                log.debug("Config file loaded");
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                fileInputStream = new FileInputStream(genericDir + "\\src\\test\\resources\\properties\\Locators.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            try {
                locators.load(fileInputStream);
                log.debug("Locators file loaded");
            } catch (IOException e) {
                e.printStackTrace();
            }


            if (config.getProperty("browser").equals("firefox")) {

                System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
                driver = new FirefoxDriver();
                log.debug("Initialize Firefox driver");
            } else if (config.getProperty("browser").equals("chrome")) {

                System.setProperty("webdriver.chrome.driver",
                        genericDir + "\\src\\test\\resources\\executables\\chromedriver.exe");
                driver = new ChromeDriver();
                log.debug("Initialize Chrome driver");
            } else if (config.getProperty("browser").equals("ie")) {

                System.setProperty("webdriver.ie.driver",
                        genericDir + "\\src\\test\\resources\\executables\\IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                log.debug("Initialize IE driver");
            }
            log.debug("Open browser");
            driver.get(config.getProperty("urlsite"));
            log.debug("Navigate to " + config.getProperty("urlsite"));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);


        }

    }

    @AfterSuite
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
        log.debug("Test execution completed");

    }

}
