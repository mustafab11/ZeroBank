package com.zerobank.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class Driver {

    private Driver() {
    }

    private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    public static WebDriver getDriver() {

        if (driverPool.get() == null) {

            synchronized (Driver.class) {

                //String browser = ConfigurationReader.getProperty("browser");
                String browser = System.getProperty("browser") != null ? browser = System.getProperty("browser") : ConfigurationReader.getProperty("browser");

//    ----------------------------
                DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                desiredCapabilities.setAcceptInsecureCerts(true);

                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.merge(desiredCapabilities);

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.merge(desiredCapabilities);

                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.merge(desiredCapabilities);
///    ----------------------------

                switch (browser) {
                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        driverPool.set(new ChromeDriver(chromeOptions));
                        driverPool.get().manage().window().maximize();
                        driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                        break;
                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        driverPool.set(new FirefoxDriver(firefoxOptions));
                        driverPool.get().manage().window().maximize();
                        driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                        break;
                    case "edge":
                        WebDriverManager.edgedriver().setup();
                        driverPool.set(new EdgeDriver(edgeOptions));
                        driverPool.get().manage().window().maximize();
                        driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                        break;

                }
            }
        }

        //This same driver will be returned every time we call Driver.getDriver() method
        return driverPool.get();

    }

    public static void closeDriver() {
        if (driverPool.get() != null) {
            driverPool.get().quit();
            driverPool.remove();
        }
    }
}
