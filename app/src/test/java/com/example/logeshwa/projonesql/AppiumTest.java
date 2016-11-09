package com.example.logeshwa.projonesql;


import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

/**
 * Created by logeshwa on 10/16/2016.
 *
 * Step to be followed :
 *
 * Open uiAutomator and take the screen shots + Emulator is running
 *
 * http://www.software-testing-tutorials-automation.com/2015/10/ui-automator-viewer-get-android-app.html
 *
 * http://www.software-testing-tutorials-automation.com/2015/10/how-to-find-apk-file-package-name-and.html
 *
 **************http://toolsqa.com/mobile-automation/appium/findelement-and-findelements-commands/
 */

public class AppiumTest {


    AppiumDriver driver;
    @BeforeClass
    public void testCaseSetup()throws  Exception
    {
        //service.start();
        //reader.readFile();
        DesiredCapabilities cap=new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android device");
        cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "4000");
        cap.setCapability(MobileCapabilityType.APP, "C:\\Users\\logeshwa\\AndroidStudioProjects\\ProjoneSQL\\app\\build\\outputs\\apk\\app-debug.apk");

        driver=new AndroidDriver(new URL("http://127.0.0.1:4725/wd/hub"),cap);

    }

    @Test
    public void testcase1()throws  Exception
    {

       String taken = driver.findElement(By.id("com.example.logeshwa.projonesql:id/btnAddNewRecord")).getText().toString();
        driver.findElement(By.id("com.example.logeshwa.projonesql:id/btnAddNewRecord")).click();
        System.out.print("success +++++++++++++++++ button clicked with name : " + taken);

        driver.findElement(By.id("com.example.logeshwa.projonesql:id/etFirstName")).sendKeys("Muthu");
        driver.findElement(By.id("com.example.logeshwa.projonesql:id/etLastname")).sendKeys("Kumar");
        driver.findElement(By.id("com.example.logeshwa.projonesql:id/etEmail")).sendKeys("Muthu@Test.com.com");
        driver.findElement(By.id("com.example.logeshwa.projonesql:id/btnDML")).click();
        Assert.assertEquals(driver.findElement(By.id("com.example.logeshwa.projonesql:id/FriTag")).getText().toString(),"Friends");

        System.out.println("success +++++++++++++++++ came to home Page : ");
        //Assert.assertTrue(driver.findElementByID("Example").isDisplayed));
    }

    @Test
    public void test_validation_insert_record() throws Exception
    {
        Assert.assertEquals(driver.findElement(By.id("com.example.logeshwa.projonesql:id/FriTag")).getText().toString(),"Friends");
        System.out.println("+++++++++inside insert record+++++++++++++++++++++");
        List<WebElement> allcontacts = driver.findElements(By.id("com.example.logeshwa.projonesql:id/tvFullName"));


        for (WebElement s:allcontacts) {
            System.out.println("++++++++++++++++++++++++++++++");
            System.out.println(s.getText().toString());

        }   


    }
    @After
    public void testCaseTearDown()
    {
        driver.quit();
    }
}
