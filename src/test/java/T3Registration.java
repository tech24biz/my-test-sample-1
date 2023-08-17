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

public class T3Registration {

//    @BeforeClass
//    public static void testSuiteSetup() {
//        AppiumServer.initServer();
//        ModuleBase.setupDriverWithAppFile();
//    }


    @Severity(SeverityLevel.BLOCKER)
    @Feature("Registration")
    @Story("Register a new phone number")
    @Description("Check if the number is previously registered and register if not")
    @Test
    private void test5_RegisterNew() {
        try{
            ModuleBase.checkAppiumDriver();
//            Utils.addDelay(1000);
            List<WebElement> textElements = ModuleBase.appiumDriver.findElements(By.xpath(String.format
                    ("//XCUIElementTypeTextField[contains(@value, '%s')]", "Enter Phone No.")));
            GSLogger.print("phone number textElements index count "+ textElements.size());

            WebElement phoneNumberInputField = textElements.get(0);
            Assert.assertTrue(phoneNumberInputField.isDisplayed());// Check if the phone number field is displayed

            // Due to extra inputs from swift code, characters must be entered in specific sequence
            phoneNumberInputField.sendKeys("1");
            phoneNumberInputField.sendKeys("01");
            phoneNumberInputField.sendKeys("010");
            phoneNumberInputField.sendKeys( Utils.newRegistrationNumberSuffix);

            Utils.addDelay(500);
            GSLogger.print(" phoneNumberInputField Text "+ phoneNumberInputField.getText());

            Allure.step("Keyed in a new phone number for registration");
        }
        catch (Exception e){
            GSLogger.print(" Error-100308: "+ e.getLocalizedMessage());
            e.printStackTrace();
        }

        try{
            ModuleBase.appiumDriver.findElement(AppiumBy.accessibilityId("Continue")).click();
            Allure.step("Clicked the Continue button after entering valid phone number");
//            Utils.addDelay(3000);
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
//   @Test(dependsOnMethods ={"test5_RegisterNew"})
    @Test
    private void test6_SubmitSystemOTP() {
        try {
            ModuleBase.checkAppiumDriver();
//            Utils.addDelay(500);
            List<WebElement> textElements = ModuleBase.appiumDriver.findElements(By.xpath(String.format
                    ("//XCUIElementTypeTextField[contains(@value, '%s')]", "")));
            GSLogger.print("otpInputField index count " + textElements.size());

            WebElement otpInputField = textElements.get(0);

            otpInputField.sendKeys("000000");

            Utils.addDelay(500);
            GSLogger.print(" otpInputField Text " + otpInputField.getText());

            Allure.step("Keyed in OTP");
        } catch (Exception e) {
            GSLogger.print(" Error-100310: " + e.getLocalizedMessage());
            e.printStackTrace();
        }

        try{
            ModuleBase.appiumDriver.findElement(AppiumBy.accessibilityId("Continue")).click();
            Allure.step("Clicked the Continue button after entering OTP");
            Utils.addDelay(500);
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
//    @Test(dependsOnMethods ={"test5_RegisterNew"})
    @Test
    private void test8_EnterRegnDetails() {
        try {
            ModuleBase.checkAppiumDriver();
//            Utils.addDelay(3000);

            String regNameId = "RegistrationFirstName";
            WebDriverWait wait = new WebDriverWait( ModuleBase.appiumDriver, Duration.ofSeconds(15), Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(regNameId)));
            WebElement firstNameTextField = ModuleBase.appiumDriver.findElement(AppiumBy.accessibilityId(regNameId));

            firstNameTextField.sendKeys("firstName_"+  Utils.newRegistrationNumberSuffix);

            Utils.addDelay(500);
            //GSLogger.print(" otpInputField Text " + otpInputField.getText());
            Allure.step("Keyed in First Name");

            WebElement lastNameTextField = ModuleBase.appiumDriver.findElement(AppiumBy.accessibilityId("RegistrationLastName"));
            lastNameTextField.sendKeys("lastName_" +  Utils.newRegistrationNumberSuffix);

            Utils.addDelay(500);
            Allure.step("Keyed in Last Name");

            WebElement emailTextField = ModuleBase.appiumDriver.findElement(AppiumBy.accessibilityId("RegistrationEmail"));
            emailTextField.sendKeys("testUser"+  Utils.newRegistrationNumberSuffix +"@goof.com");

            Utils.addDelay(500);
            Allure.step("Keyed in E-mail ID");

            WebElement stateDropdown = ModuleBase.appiumDriver.findElement(AppiumBy.accessibilityId("Select State"));
            stateDropdown.click();

            Utils.addDelay(500);
            Allure.step("Keyed in E-mail ID");

            WebElement selectedState = ModuleBase.appiumDriver.findElement(AppiumBy.accessibilityId("Alabama"));
            selectedState.click();
            Utils.addDelay(500);
            Allure.step("Selected state dropdown");

            WebElement Continue = ModuleBase.appiumDriver.findElement(AppiumBy.accessibilityId("Continue"));
            Continue.click();
            Utils.addDelay(500);
            Allure.step("Clicked Continue to complete registration");

//            WebElement profileBtn = ModuleBase.appiumDriver.findElement(AppiumBy.accessibilityId("Profile"));
//            profileBtn.click();
//            Utils.addDelay(3000);
//            Allure.step("Navigate to Profile button");
//
//           //Delete Account process after successful registration
//
//           WebElement delLink = ModuleBase.appiumDriver.findElement(AppiumBy.accessibilityId("Delete my account"));
//           delLink.click();
//           Allure.step("Clicked on Delete Account option");
//            Utils.addDelay(3000);

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

            ModuleBase.cleanUp();

        } catch (Exception e) {
            GSLogger.print(" Error-100311: " + e.getLocalizedMessage());
            e.printStackTrace();
            ModuleBase.cleanUp();
        }
    }

    @Severity(SeverityLevel.BLOCKER)
    @Feature("Delete Account")
    @Story("Delete Account")
    @Description("Delete Account")
//    @Test(dependsOnMethods ={"test8_EnterRegnDetails"})
    @Test
    private void test9_delAccountDetails() {
        try {
            ModuleBase.checkAppiumDriver();
//            Utils.addDelay(3000);

            WebElement profileBtn = ModuleBase.appiumDriver.findElement(AppiumBy.accessibilityId("Profile"));
            profileBtn.click();
//            Utils.addDelay(3000);
            Allure.step("Navigate to Profile button");

            //Delete Account process after successful registration

            WebElement delLink = ModuleBase.appiumDriver.findElement(AppiumBy.accessibilityId("Delete my account"));
            delLink.click();
            Allure.step("Clicked on Delete Account option");
//            Utils.addDelay(3000);

//          List<WebElement> alert = AllTests.appiumDriver.findElements(AppiumBy.xpath(String.format("//XCUIElementTypeTypeButton[contains(@name, '%s')]", "Delete")));
//          Assert.assertTrue(alert.isEmpty());


//            AllTests.appiumDriver.findElement(AppiumBy.linkText("Delete my account")).click();
//            Allure.step("Clicked on Delete Account option");
//            Utils.addDelay(5000);

            WebElement accDeleted = ModuleBase.appiumDriver.findElement(AppiumBy.name("Account Deleted."));
            Assert.assertTrue(accDeleted.isDisplayed()); //Asserts that the dialog box is displayed, confirming account deletion
            Allure.step("Account Deleted confirmation dialog box is displayed");
            Utils.addDelay(5000);

        } catch (Exception e) {
            GSLogger.print(" Error-100312: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

}