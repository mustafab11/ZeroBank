package com.zerobank.stepdefnitions;

import com.zerobank.utilities.FileUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Study {

@Test
    public void test1() throws Exception{


    WebDriverManager.chromedriver().setup();
    WebDriver driver = new ChromeDriver();
    driver.manage().window().maximize();
    String uri = "http://te.dev.secureci.com/Exercise1.html";
    driver.get(uri);
    int index = 1;
    List<WebElement> allLinks = driver.findElements(By.tagName("area"));
    for (WebElement eachLink : allLinks) {
        URL url = new URL(eachLink.getAttribute("href"));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("HEAD");
        connection.connect();
        ++index;
        if (connection.getResponseCode()!=200){
            System.out.println(eachLink.getAttribute("href"));
            System.out.println(connection.getResponseCode());
            System.out.println("broken link is at index "+ index);
            System.out.println(connection.getResponseCode());
//            break;
//
        }


    }
//        System.out.println(connection.getResponseCode());
    driver.close();


    }

    @Test
    public void test2(){

        String downloadsFolder = System.getProperty("user.home") + "/Downloads/";
        System.out.println("downloadsFolder = " + downloadsFolder);
        File mostRecentFile = FileUtility.getMostRecentFile(downloadsFolder);
        System.out.println("mostRecentFile.getName() = " + mostRecentFile.getName());
    }
}
