import io.appium.java_client.AppiumBy;
import io.qameta.allure.*;
import org.example.AppiumServer;
import org.example.GSLogger;
import org.example.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class template_class {

    @Severity(SeverityLevel.BLOCKER)
    @Feature("Describe Feature here")
    @Story("Describe what the story does here")
    @Description("Describe the story here")
    @Test
    private void describeFunc() {
        try {
            ModuleBase.checkAppiumDriver();
            //write modular code here
            //Establish Try-Catch for each modular function
        }

        catch (Exception e){
            GSLogger.print(" Error-1003xx: "+ e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
