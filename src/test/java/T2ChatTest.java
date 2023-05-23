import io.appium.java_client.AppiumBy;
import io.qameta.allure.*;
import org.example.GSLogger;
import org.example.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class T2ChatTest {

    @Severity(SeverityLevel.BLOCKER)
    @Feature("Login")
    @Story("Verify Login")
    @Description("Accept system OTP")
    @Test(dependsOnMethods ={"test2_ClickContinueWithValidData"})
    private void test3_SubmitSystemOTP() {
        try {
            AllTests.checkAppiumDriver();
            Utils.addDelay(3000);
            List<WebElement> textElements = AllTests.appiumDriver.findElements(By.xpath(String.format
                    ("//XCUIElementTypeTextField[contains(@value, '%s')]", "")));
            GSLogger.print("otpInputField index count " + textElements.size());

            WebElement otpInputField = textElements.get(0);

            otpInputField.sendKeys("404040");

            Utils.addDelay(500);
            GSLogger.print(" otpInputField Text " + otpInputField.getText());

            Allure.step("Keyed in OTP");
        } catch (Exception e) {
            GSLogger.print(" Error100303: " + e.getLocalizedMessage());
            e.printStackTrace();
        }

        try{
            AllTests.appiumDriver.findElement(AppiumBy.accessibilityId("Continue")).click();
            Allure.step("Clicked the Continue button after entering OTP");
            Utils.addDelay(15000);
        }
        catch (Exception e){
            GSLogger.print(" Error100304: "+ e.getLocalizedMessage());
            e.printStackTrace();
        }
    }



    @Severity(SeverityLevel.BLOCKER)
    @Feature("Chat")
    @Story("Search for Chat")
    @Description("Search for Chat and open it")
    @Test(dependsOnMethods ={"test3_SubmitSystemOTP"})
    private void test4_SearchChars() {
        try {
            AllTests.checkAppiumDriver();
            Utils.addDelay(3000);
            WebElement searchBox = AllTests.appiumDriver.findElement(AppiumBy.accessibilityId("Search"));
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
