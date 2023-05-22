import io.appium.java_client.AppiumBy;
import io.qameta.allure.*;
import org.example.GSLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.List;
import org.example.Utils;


public class T1LoginTest {

    @Severity(SeverityLevel.BLOCKER)
    @Feature("Login")
    @Story("Verify Login")
    @Description("Show pop up for invalid empty credentials")

    @Test//(groups = "a")
    private void test1_ClickEmptyContinue() {
        try{
            AllTests.checkAppiumDriver();
            AllTests.appiumDriver.findElement(AppiumBy.accessibilityId("Continue")).click();
            Allure.step("Clicked the Continue button without entering data");
            String warningMessage = "Please enter a valid phone number";

            List<WebElement> textElements = AllTests.appiumDriver.findElements(By.xpath(String
                    .format("//XCUIElementTypeStaticText[contains(@value, '%s')]", warningMessage)));
            Assert.assertTrue(textElements.size() > 0);

            List<WebElement> buttonElements = AllTests.appiumDriver.findElements(By.xpath(String
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
    @Test(dependsOnMethods ={"test1_ClickEmptyContinue"})
    private void test2_ClickContinueWithValidData() {
        try{
            AllTests.checkAppiumDriver();
            Utils.addDelay(1000);
            List<WebElement> textElements = AllTests.appiumDriver.findElements(By.xpath(String.format
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
            AllTests.appiumDriver.findElement(AppiumBy.accessibilityId("Continue")).click();
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
            GSLogger.print(" Error-100303: " + e.getLocalizedMessage());
            e.printStackTrace();
        }

        try{
            AllTests.appiumDriver.findElement(AppiumBy.accessibilityId("Continue")).click();
            Allure.step("Clicked the Continue button after entering OTP");
            Utils.addDelay(15000);
        }
        catch (Exception e){
            GSLogger.print(" Error-100304: "+ e.getLocalizedMessage());
            e.printStackTrace();
        }
    }



}

