package org.example;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;

public class AppiumServer {
    static AppiumDriverLocalService appiumLocalServer;
    public static String serverIp = "127.0.0.1";
    public static int serverPort = 4723;
    public static AppiumServer singleton;

    public static void initServer() {
        if(singleton == null) {
            singleton = new AppiumServer();
            singleton.setup();
            singleton.start();
        } else {
            singleton.start();
        }
    }

    private void setup() {
        if(appiumLocalServer != null) {
            return;
        }
        try {
            File mainJsFile = new File("/opt/homebrew/lib/node_modules/appium/build/lib/main.js");
            File driverFilePath = new File("/opt/homebrew/bin/node");
            File outputLogFile = new File("GS_Appium.log");

            AppiumServiceBuilder builder = new AppiumServiceBuilder();
            builder.withAppiumJS(mainJsFile)
                    .usingDriverExecutable(driverFilePath)
                    .withArgument(GeneralServerFlag.LOCAL_TIMEZONE)
                    .withLogFile(outputLogFile)
                    .withIPAddress(serverIp)
                    .usingPort(serverPort);

            appiumLocalServer = AppiumDriverLocalService.buildService(builder);
        } catch (Exception e){
            GSLogger.print(" Error-100004 AppiumServer setup Exception: "+ e.getLocalizedMessage());
        }
    }



    public void stopLocal() {
        try {
            GSLogger.print(" Attempting to stop server ");
            appiumLocalServer.stop();
            GSLogger.print(" Appium Server is running 🖥️ : "+ appiumLocalServer.isRunning() );
        } catch (Exception e){
            GSLogger.print(" Error-100002 AppiumServer STOP Exception: "+ e.getLocalizedMessage() );
        }
    }

    public void start() {
        if(appiumLocalServer.isRunning()) {
            System.out.println(" Appium Server is ALREADY running 🖥️  ");
            return;
        }
        try {
            GSLogger.print(" Attempting to START server ");
            appiumLocalServer.start();
            GSLogger.print("  Appium Server is running? 🖥️ : "+ appiumLocalServer.isRunning());
            GSLogger.print("  Appium Server URL: "+ appiumLocalServer.getUrl() );
        } catch (Exception e){
            GSLogger.print(" Error-100003 AppiumServer START Exception: "+ e.getLocalizedMessage() );
        }
    }
}
