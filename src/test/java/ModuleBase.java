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

    static int suiteCounter  = 0;
    @BeforeSuite
    @BeforeClass
    public static void testSuiteSetup() {
        GSLogger.print("\n\n -------------- init 🛠 GS-suiteCounter = " + ++suiteCounter +" -------------------- \n\n ");
        Utils.getNewRegistrationPhoneNumber();
        AppiumServer.initServer();
        setupDriverWithAppFile();
    }

    @AfterSuite
    public static void cleanUp(){
        try {
            GSLogger.print(" Tests Completed. Stopping Server. Releasing driver and port" );
            Utils.addDelay(1000);
            ModuleBase.appiumDriver.quit();
            AppiumServer.singleton.stopLocal();
        } catch (Exception e){
            GSLogger.print(" tearDown Exception: "+ e.getLocalizedMessage() );
            e.printStackTrace();
        }
    }

    static int counter = 0;
    public static void setupDriverWithAppFile() {
        if(appiumDriver == null){
            GSLogger.print("\n\n -------------- init 🛠 GS-appiumDriver counter = " + ++counter +" -------------------- \n\n ");
        } else {
            GSLogger.print("\n\n -------------- ✋🏼 ✋🏼 ✋🏼 No need to GS-appiumDriver  ✋🏼 ✋🏼 ✋🏼 -------------------- \n\n ");
            return;
        }
        try {
            String appPath = "./goofApp/GoofBeta.app";

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
            GSLogger.print("\n\n -------------- 🌿 🌿 🌿 GS-appiumDriver initialised with file path  🌿 🌿 🌿 -------------------- \n\n ");

            Utils.addDelay(1000);
        }catch (Exception e){
            GSLogger.print(" Error-100014 AppiumServer driver Exception: "+ e.getLocalizedMessage());
            e.printStackTrace();
//            setupDriverWithBundleId();
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
//        Utils.addDelay(2000);
        if(appiumDriver == null){
//            GSLogger.print("\n\n --------  🔥 🔥 🔥  GS-appiumDriver is NULL  🔥 🔥 🔥 --------- \n\n ");
            setupDriverWithAppFile();
        } else {
            GSLogger.print("\n\n --------  ☃️ ☃️ ☃️ GS-appiumDriver is NOT NULL  ☃️ ☃️ ☃️ --------- \n\n ");
        }
    }


}
