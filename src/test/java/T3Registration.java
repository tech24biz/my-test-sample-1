import io.appium.java_client.AppiumBy;
import io.qameta.allure.*;
import org.example.AppiumServer;
import org.example.GSLogger;
import org.example.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.List;

public class T3Registration {

    @BeforeClass
    public static void testSuiteSetup() {
        AppiumServer.initServer();
        AllTests.setupDriverWithAppFile();
    }

    private String userSequence = "0016";

    @Severity(SeverityLevel.BLOCKER)
    @Feature("Registration")
    @Story("Register a new phone number")
    @Description("Check if the number is previously registered and register if not")
    @Test
    private void test5_RegisterNew() {
        try{
            AllTests.checkAppiumDriver();
            Utils.addDelay(1000);
            List<WebElement> textElements = AllTests.appiumDriver.findElements(By.xpath(String.format
                    ("//XCUIElementTypeTextField[contains(@value, '%s')]", "Enter Phone No.")));
            GSLogger.print("phone number textElements index count "+ textElements.size());

            WebElement phoneNumberInputField = textElements.get(0);
            Assert.assertTrue(phoneNumberInputField.isDisplayed());// Check if the phone number field is displayed

            // Due to extra inputs from swift code, characters must be entered in specific sequence
            phoneNumberInputField.sendKeys("1");
            phoneNumberInputField.sendKeys("01");
            phoneNumberInputField.sendKeys("010");
            phoneNumberInputField.sendKeys(userSequence);

            Utils.addDelay(500);
            GSLogger.print(" phoneNumberInputField Text "+ phoneNumberInputField.getText());

            Allure.step("Keyed in a new phone number for registration");
        }
        catch (Exception e){
            GSLogger.print(" Error-100308: "+ e.getLocalizedMessage());
            e.printStackTrace();
        }

        try{
            AllTests.appiumDriver.findElement(AppiumBy.accessibilityId("Continue")).click();
            Allure.step("Clicked the Continue button after entering valid phone number");
            Utils.addDelay(3000);
        }
        catch (Exception e){
            GSLogger.print(" Error-100309: "+ e.getLocalizedMessage());
            e.printStackTrace();
        }

    }

    @Severity(SeverityLevel.BLOCKER)
    @Feature("Login")
    @Story("Verify Login")
    @Description("Accept system OTP")
   @Test(dependsOnMethods ={"test5_RegisterNew"})
    private void test6_SubmitSystemOTP() {
        try {
            AllTests.checkAppiumDriver();
            Utils.addDelay(3000);
            List<WebElement> textElements = AllTests.appiumDriver.findElements(By.xpath(String.format
                    ("//XCUIElementTypeTextField[contains(@value, '%s')]", "")));
            GSLogger.print("otpInputField index count " + textElements.size());

            WebElement otpInputField = textElements.get(0);

            otpInputField.sendKeys("1");

            Utils.addDelay(500);
            GSLogger.print(" otpInputField Text " + otpInputField.getText());

            Allure.step("Keyed in OTP");
        } catch (Exception e) {
            GSLogger.print(" Error-100310: " + e.getLocalizedMessage());
            e.printStackTrace();
        }

        try{
            AllTests.appiumDriver.findElement(AppiumBy.accessibilityId("Continue")).click();
            Allure.step("Clicked the Continue button after entering OTP");
            Utils.addDelay(15000);
        }
        catch (Exception e){
            GSLogger.print(" Error-100311: "+ e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    @Severity(SeverityLevel.BLOCKER)
    @Feature("Login")
    @Story("Verify Login")
    @Description("Accept system OTP")
    @Test(dependsOnMethods ={"test5_RegisterNew"})
    private void test8_EnterRegnDetails() {
        try {
            AllTests.checkAppiumDriver();
            Utils.addDelay(3000);

            WebElement firstName = AllTests.appiumDriver.findElement(AppiumBy.accessibilityId("RegistrationFirstName"));
            //firstName.click();
            firstName.sendKeys("firstName_"+ userSequence);

            Utils.addDelay(500);
            //GSLogger.print(" otpInputField Text " + otpInputField.getText());
            Allure.step("Keyed in First Name");

            WebElement lastName = AllTests.appiumDriver.findElement(AppiumBy.accessibilityId("RegistrationLastName"));
            lastName.sendKeys("lastName_" + userSequence);

            Utils.addDelay(500);
            Allure.step("Keyed in Last Name");

            WebElement email = AllTests.appiumDriver.findElement(AppiumBy.accessibilityId("RegistrationEmail"));
            email.sendKeys("testUser"+ userSequence +"@goof.com");

            Utils.addDelay(500);
            Allure.step("Keyed in E-mail ID");

            WebElement stateDropdown = AllTests.appiumDriver.findElement(AppiumBy.accessibilityId("Select State"));
            stateDropdown.click();

            Utils.addDelay(500);
            Allure.step("Keyed in E-mail ID");

            WebElement selectedState = AllTests.appiumDriver.findElement(AppiumBy.accessibilityId("Alabama"));
            selectedState.click();
            Utils.addDelay(500);
            Allure.step("Selected state dropdown");

            WebElement Continue = AllTests.appiumDriver.findElement(AppiumBy.accessibilityId("Continue"));
            Continue.click();
            Utils.addDelay(5000);
            Allure.step("Clicked Continue to complete registration");

            WebElement profileBtn = AllTests.appiumDriver.findElement(AppiumBy.accessibilityId("Profile"));
            profileBtn.click();
            Utils.addDelay(3000);
            Allure.step("Navigate to Profile button");

           //Delete Account process after successful registration

           WebElement delLink = AllTests.appiumDriver.findElement(AppiumBy.accessibilityId("Delete my account"));
           delLink.click();
           Allure.step("Clicked on Delete Account option");
            Utils.addDelay(3000);

///*
//          List<WebElement> alert = AllTests.appiumDriver.findElements(By.xpath(String.format("//XCUIElementTypeTypeButton[contains(@name, '%s')]", "Delete")));
//          Assert.assertTrue(alert.isEmpty());
//*/
//
//            AllTests.appiumDriver.findElement(By.name("Delete my account")).click();
//            Allure.step("Clicked on Delete Account option");
//            Utils.addDelay(5000);
//
//            WebElement accDeleted = AllTests.appiumDriver.findElement(By.name("Account Deleted."));
//            Assert.assertTrue(accDeleted.isDisplayed()); //Asserts that the dialog box is displayed, confirming account deletion
//            Allure.step("Account Deleted confirmation dialog box is displayed");
//            Utils.addDelay(5000);

        } catch (Exception e) {
            GSLogger.print(" Error-100311: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    @Severity(SeverityLevel.BLOCKER)
    @Feature("Delete Account")
    @Story("Delete Account")
    @Description("Delete Account")
    @Test(dependsOnMethods ={"test8_EnterRegnDetails"})
    private void test9_delAccountDetails() {
        try {
            AllTests.checkAppiumDriver();
            Utils.addDelay(3000);

            WebElement profileBtn = AllTests.appiumDriver.findElement(AppiumBy.accessibilityId("Profile"));
            profileBtn.click();
            Utils.addDelay(3000);
            Allure.step("Navigate to Profile button");

            //Delete Account process after successful registration

            WebElement delLink = AllTests.appiumDriver.findElement(AppiumBy.accessibilityId("Delete my account"));
            delLink.click();
            Allure.step("Clicked on Delete Account option");
            Utils.addDelay(3000);

//          List<WebElement> alert = AllTests.appiumDriver.findElements(AppiumBy.xpath(String.format("//XCUIElementTypeTypeButton[contains(@name, '%s')]", "Delete")));
//          Assert.assertTrue(alert.isEmpty());


//            AllTests.appiumDriver.findElement(AppiumBy.linkText("Delete my account")).click();
//            Allure.step("Clicked on Delete Account option");
//            Utils.addDelay(5000);

            WebElement accDeleted = AllTests.appiumDriver.findElement(AppiumBy.name("Account Deleted."));
            Assert.assertTrue(accDeleted.isDisplayed()); //Asserts that the dialog box is displayed, confirming account deletion
            Allure.step("Account Deleted confirmation dialog box is displayed");
            Utils.addDelay(5000);

        } catch (Exception e) {
            GSLogger.print(" Error-100312: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

}