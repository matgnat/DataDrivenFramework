package rought;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {

    public static void main(String[] args) throws IOException {


        /**
         * load properties
         */

        String genericDir = System.getProperty("user.dir");

        System.out.printf(genericDir +  "//src//test//resources//properties//Config.properties");

        FileInputStream fileInputStream = new FileInputStream(genericDir + "//src//test//resources//properties//Config.properties");

        Properties confing = new Properties();

        confing.load(fileInputStream);
    }

}
