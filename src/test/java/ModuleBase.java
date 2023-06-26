import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.apache.log4j.BasicConfigurator;
import org.example.AppiumServer;
import org.example.GSLogger;
import org.example.Utils;
import org.testng.annotations.*;

import java.net.URL;

public class ModuleBase {

    public static AppiumDriver appiumDriver;

    @BeforeSuite
    public static void testSuiteSetup() {
        AppiumServer.initServer();
        setupDriverWithAppFile();
    }

//    @BeforeTest
//    public static void testWiseSetup(){
//        setupDriverWithBundleId();
//    }
//
//    @AfterTest
//    public static void tearDownSetup(){ }

    @AfterSuite
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
                    .setApp(appPath)
//                    .setAutoDismissAlerts(true)
                    .setNoReset(false); // if already installed, do NOT reinstall the app
//                    .setBundleId("com.goofsports.ios1")

            URL appiumServerUrl = new URL("http://"+ AppiumServer.serverIp +":"+ AppiumServer.serverPort);
            appiumDriver = new IOSDriver(appiumServerUrl, testOptions);
            GSLogger.print("\n\n -------------- 🌿 🌿 🌿 appiumDriver initialised with file path  🌿 🌿 🌿 -------------------- \n\n ");

            Utils.addDelay(1000);
        }catch (Exception e){
            GSLogger.print(" Error-100014 AppiumServer START Exception: "+ e.getLocalizedMessage());
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
                    .setBundleId("com.goofsports.ios1")
                    .setNoReset(false); // if already installed, do NOT reinstall the app

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
}
