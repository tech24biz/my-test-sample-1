import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.qameta.allure.*;
import org.apache.log4j.BasicConfigurator;
import org.example.AppiumServer;
import org.example.GSLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import java.net.URL;
import java.util.List;
import org.example.Utils;


public class IosSampleTest {

    public static AppiumDriver appiumDriver;

    @BeforeSuite
    public static void testSuiteSetup() {
        AppiumServer.initServer();
        setupDriverWithAppFile();
    }

    @BeforeTest
    public static void testWiseSetup(){
        setupDriverWithBundleId();
    }

    @AfterTest
    public static void tearDownSetup(){ }

    @AfterSuite
    public static void cleanUp(){
        try {
            appiumDriver.quit();
        } catch (Exception e){
            GSLogger.print(" tearDown Exception: "+ e.getLocalizedMessage() );
        }
    }

    public static void setupDriverWithAppFile() {
        BasicConfigurator.configure();
        try {
            String appPath = "./goofApp/Goof1Beta.app";

            // To find active simulator's device name
            // xcrun simctl list | egrep '(Booted)'
            String deviceName = "iPhone 14 Pro Max";

            // instead of DesiredCapabilities, testOptions are used
            XCUITestOptions testOptions = new XCUITestOptions();
            BasicConfigurator.configure();

            testOptions.setDeviceName(deviceName)
                    .setPlatformVersion("16.4")
                    .setApp(appPath)
//                    .setAutoDismissAlerts(true)
                    .setNoReset(true); // if already installed, do NOT reinstall the app
//                    .setBundleId("com.goofsports.ios1")

            URL appiumServerUrl = new URL("http://"+ AppiumServer.serverIp +":"+ AppiumServer.serverPort);
            appiumDriver = new IOSDriver(appiumServerUrl, testOptions);
            GSLogger.print("\n\n -------------- 🌿 🌿 🌿 appiumDriver initialised with file path  🌿 🌿 🌿 -------------------- \n\n ");

            Utils.addDelay(1000);
        }catch (Exception e){
            GSLogger.print(" Error-100007 AppiumServer START Exception: "+ e.getLocalizedMessage());
            setupDriverWithBundleId();
        }
    }

    public static void setupDriverWithBundleId() {
        try {

            // To find active simulator's device name
            // xcrun simctl list | egrep '(Booted)'
            String deviceName = "iPhone 14 Pro Max";

            // instead of DesiredCapabilities, testOptions are used
            XCUITestOptions testOptions = new XCUITestOptions();
            BasicConfigurator.configure();

            testOptions.setDeviceName(deviceName)
                    .setPlatformVersion("16.4")
//                    .setAutoDismissAlerts(true)
                    .setBundleId("com.goofsports.ios1")
                    .setNoReset(true); // if already installed, do NOT reinstall the app

            URL appiumServerUrl = new URL("http://"+ AppiumServer.serverIp +":"+ AppiumServer.serverPort);
            appiumDriver = new IOSDriver(appiumServerUrl, testOptions);
            GSLogger.print("\n\n -------------- 🌿 🌿 🌿 appiumDriver initialised with bundle id  🌿 🌿 🌿 -------------------- \n\n ");
        }catch (Exception e){
            GSLogger.print(" Error-100007 AppiumServer START Exception: "+ e.getLocalizedMessage());
        }
    }

    public static void checkAppiumDriver() {
        Utils.addDelay(2000);

        if(appiumDriver == null){
            GSLogger.print("\n\n --------  🔥 🔥 🔥  appiumDriver is NULL  🔥 🔥 🔥 --------- \n\n ");
            setupDriverWithAppFile();
        } else {
            GSLogger.print("\n\n --------  ☃️ ☃️ ☃️ appiumDriver is NOT NULL  ☃️ ☃️ ☃️ --------- \n\n ");
        }
    }

    @Severity(SeverityLevel.BLOCKER)
    @Feature("Login")
    @Story("Verify Login")
    @Description("Show pop up for invalid empty credentials")
    @Test
    private void clickEmptyContinue() {
        try{
        checkAppiumDriver();

        appiumDriver.findElement(AppiumBy.accessibilityId("Continue")).click();
        Allure.step("Clicked the Continue button without entering data");
        String warningMessage = "Please enter a valid phone number";

        List<WebElement> textElements = appiumDriver.findElements(By.xpath(String.format("//XCUIElementTypeStaticText[contains(@value, '%s')]", warningMessage)));
        Assert.assertTrue(textElements.size() > 0);


        List<WebElement> buttonElements = appiumDriver.findElements(By.xpath(String.format("//XCUIElementTypeButton[contains(@name, '%s')]", "OK")));
        Assert.assertTrue(buttonElements.size() > 0);
        WebElement alertOkButton = buttonElements.get(0);
        Assert.assertTrue(alertOkButton.isDisplayed());
        alertOkButton.click();
        }
        catch (Exception e){
            GSLogger.print(" Error-100302: "+ e.getLocalizedMessage());
        }
    }

    @Severity(SeverityLevel.BLOCKER)
    @Feature("Login")
    @Story("Verify Login")
    @Description("Show pop up for invalid empty credentials")
    @Test
    private void clickContinueWithValidData() {
        try{
            checkAppiumDriver();
            List<WebElement> textElements = appiumDriver.findElements(By.xpath(String.format("//XCUIElementTypeTextField[contains(@value, '%s')]", "Enter Phone No.")));
            textElements.get(0).sendKeys("2015431179");
            Allure.step("Keyed in phone number");
            appiumDriver.findElement(AppiumBy.accessibilityId("Continue")).click();
            Allure.step("Clicked the Continue button after entering valid data");
        }
        catch (Exception e){
            GSLogger.print(" Error-100301: "+ e.getLocalizedMessage());
        }

    }

}

