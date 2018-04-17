package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

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
                FileInputStream fileInputStream = new FileInputStream(genericDir + "//src//test//resources//properties//Config.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            try {
                confing.load(fileInputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                fileInputStream = new FileInputStream(genericDir + "//src//test//resources//properties//Locators.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            try {
                locators.load(fileInputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @AfterSuite
    public void tearDown() {


    }

}
