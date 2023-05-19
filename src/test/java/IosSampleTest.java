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

    @BeforeClass
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

    @AfterClass
    public static void cleanUp(){
        try {
            appiumDriver.quit();
        } catch (Exception e){
            GSLogger.print(" tearDown Exception: "+ e.getLocalizedMessage() );
            e.printStackTrace();
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
                    .setApp(appPath);
//                    .setAutoDismissAlerts(true)
//                    .setNoReset(true); // if already installed, do NOT reinstall the app
//                    .setBundleId("com.goofsports.ios1")

            URL appiumServerUrl = new URL("http://"+ AppiumServer.serverIp +":"+ AppiumServer.serverPort);
            appiumDriver = new IOSDriver(appiumServerUrl, testOptions);
            GSLogger.print("\n\n -------------- 🌿 🌿 🌿 appiumDriver initialised with file path  🌿 🌿 🌿 -------------------- \n\n ");

            Utils.addDelay(1000);
        }catch (Exception e){
            GSLogger.print(" Error-100007 AppiumServer START Exception: "+ e.getLocalizedMessage());
            e.printStackTrace();
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
                    .setBundleId("com.goofsports.ios1");
//                    .setNoReset(false); // if already installed, do NOT reinstall the app

            URL appiumServerUrl = new URL("http://"+ AppiumServer.serverIp +":"+ AppiumServer.serverPort);
            appiumDriver = new IOSDriver(appiumServerUrl, testOptions);
            GSLogger.print("\n\n -------------- 🌿 🌿 🌿 appiumDriver initialised with bundle id  🌿 🌿 🌿 -------------------- \n\n ");
        }catch (Exception e){
            GSLogger.print(" Error-100007 AppiumServer START Exception: "+ e.getLocalizedMessage());
            e.printStackTrace();
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
    private void test1_ClickEmptyContinue() {
        try{
            checkAppiumDriver();
            appiumDriver.findElement(AppiumBy.accessibilityId("Continue")).click();
            Allure.step("Clicked the Continue button without entering data");
            String warningMessage = "Please enter a valid phone number";

            List<WebElement> textElements = appiumDriver.findElements(By.xpath(String
                    .format("//XCUIElementTypeStaticText[contains(@value, '%s')]", warningMessage)));
            Assert.assertTrue(textElements.size() > 0);

            List<WebElement> buttonElements = appiumDriver.findElements(By.xpath(String
                    .format("//XCUIElementTypeButton[contains(@name, '%s')]", "OK")));
            Assert.assertTrue(buttonElements.size() > 0);
            WebElement alertOkButton = buttonElements.get(0);
            Assert.assertTrue(alertOkButton.isDisplayed());
            alertOkButton.click();
        }
        catch (Exception e){
            GSLogger.print(" Error-100302: "+ e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    @Severity(SeverityLevel.BLOCKER)
    @Feature("Login")
    @Story("Verify Login")
    @Description("Ask OTP after valid system phone number")
    @Test
    private void test2_ClickContinueWithValidData() {
        try{
            checkAppiumDriver();
            Utils.addDelay(1000);
            List<WebElement> textElements = appiumDriver.findElements(By.xpath(String.format
                    ("//XCUIElementTypeTextField[contains(@value, '%s')]", "Enter Phone No.")));
            GSLogger.print("phone number textElements index count "+ textElements.size());

            WebElement phoneNumberInputField = textElements.get(0);

            // Due to extra inputs from swift code, characters must be entered in specific sequence
            phoneNumberInputField.sendKeys("1");
            phoneNumberInputField.sendKeys("01");
            phoneNumberInputField.sendKeys("010");
            phoneNumberInputField.sendKeys("0001");

            Utils.addDelay(500);
            GSLogger.print(" phoneNumberInputField Text "+ phoneNumberInputField.getText());

            Allure.step("Keyed in phone number");
        }
        catch (Exception e){
            GSLogger.print(" Error-100301: "+ e.getLocalizedMessage());
            e.printStackTrace();
        }
        try{
            appiumDriver.findElement(AppiumBy.accessibilityId("Continue")).click();
            Allure.step("Clicked the Continue button after entering valid data");
            Utils.addDelay(3000);
        }
        catch (Exception e){
            GSLogger.print(" Error-100301: "+ e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    @Severity(SeverityLevel.BLOCKER)
    @Feature("Login")
    @Story("Verify Login")
    @Description("Accept system OTP")
    @Test
    private void test3_SubmitSystemOTP() {
        try {
            checkAppiumDriver();
            Utils.addDelay(3000);
            List<WebElement> textElements = appiumDriver.findElements(By.xpath(String.format
                    ("//XCUIElementTypeTextField[contains(@value, '%s')]", "")));
            GSLogger.print("otpInputField index count " + textElements.size());

            WebElement otpInputField = textElements.get(0);

            otpInputField.sendKeys("404040");

            Utils.addDelay(500);
            GSLogger.print(" otpInputField Text " + otpInputField.getText());

            Allure.step("Keyed in OTP");
        } catch (Exception e) {
            GSLogger.print(" Error-100303: " + e.getLocalizedMessage());
            e.printStackTrace();
        }

        try{
            appiumDriver.findElement(AppiumBy.accessibilityId("Continue")).click();
            Allure.step("Clicked the Continue button after entering OTP");
            Utils.addDelay(15000);
        }
        catch (Exception e){
            GSLogger.print(" Error-100304: "+ e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    @Severity(SeverityLevel.BLOCKER)
    @Feature("Chat")
    @Story("Search for Chat")
    @Description("Search for Chat and open it")
    @Test
    private void test4_SearchChars() {
        try {
            checkAppiumDriver();
            Utils.addDelay(3000);
            WebElement searchBox = appiumDriver.findElement(AppiumBy.accessibilityId("Search"));
            searchBox.click();
            Allure.step("Tapped Search to key in search characters");

            Utils.addDelay(500);
            searchBox.sendKeys("nishant");
            Allure.step("Keyed in search characters");

        } catch (Exception e) {
            GSLogger.print(" Error-100305: " + e.getLocalizedMessage());
            e.printStackTrace();
        }

    }

}

