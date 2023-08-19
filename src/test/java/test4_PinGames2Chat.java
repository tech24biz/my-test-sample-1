import io.appium.java_client.AppiumBy;
import io.qameta.allure.*;
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

public class test4_PinGames2Chat {

    @Severity(SeverityLevel.BLOCKER)
    @Feature("Pin Games to Chat")
    @Story("Pin Game to a newly created Chat")
    @Description("Traverse to Chat window, add a new contact for a new chat" +
            "go back to chat screen, " +
            "go to Games tab, goto NFL, " +
            "long press on any game (left tab)" +
            "Pin the game to the newly created chat" +
            "Traverse to the chat if the game is pinned correctly and as expected")
    @Test
    private void AddaNewChat() {
        try{
            ModuleBase.checkAppiumDriver();
//            Utils.addDelay(1000);

            WebElement goofGPTHeading = ModuleBase.appiumDriver.findElement(AppiumBy.accessibilityId("Goof GPT"));
            WebElement chatMenu = ModuleBase.appiumDriver.findElement(AppiumBy.accessibilityId("Chat"));

            WebElement addChatBtn = ModuleBase.appiumDriver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Add\"]"));

//            if(goofGPTHeading.isDisplayed()){
            chatMenu.click();
            GSLogger.print("Chat Menu clicked");
            Utils.addDelay(1500);


            addChatBtn.click();
            GSLogger.print("Chat add button clicked");
        }
        catch (Exception e){
            GSLogger.print(" Error-100320: "+ e.getLocalizedMessage());
            e.printStackTrace();
        }

//        try{
//            ModuleBase.appiumDriver.findElement(AppiumBy.accessibilityId("Continue")).click();
//            Allure.step("Clicked the Continue button after entering valid phone number");
////            Utils.addDelay(3000);
//        }
//        catch (Exception e){
//            GSLogger.print(" Error-100309: "+ e.getLocalizedMessage());
//            e.printStackTrace();
//        }

    }

    @Severity(SeverityLevel.BLOCKER)
    @Feature("Login")
    @Story("Verify Login")
    @Description("Accept system OTP")
//   @Test(dependsOnMethods ={"test5_RegisterNew"})
    @Test
    private void test6_SubmitSystemOTP() {
        String otpEnteredErr = "";

        try {
            ModuleBase.checkAppiumDriver();
            Utils.addDelay(500);
            List<WebElement> textElements = ModuleBase.appiumDriver.findElements(By.xpath(String.format
                    ("//XCUIElementTypeTextField[contains(@value, '%s')]", "")));
            GSLogger.print("otpInputField index count " + textElements.size());

            WebElement otpInputField = textElements.get(0);

            otpInputField.sendKeys("000000");

            Utils.addDelay(500);
            GSLogger.print(" otpInputField Text " + otpInputField.getText());

            Allure.step("Keyed in OTP");
        } catch (Exception e) {
            otpEnteredErr = " Error-100310: " + e.getLocalizedMessage();
            GSLogger.print(otpEnteredErr);
            e.printStackTrace();
        } finally {
            Assert.assertTrue(otpEnteredErr.isEmpty(), "Failed to enter OTP. "+ otpEnteredErr);
        }

        String foundContinueBtnErr = "";
        try{
            ModuleBase.appiumDriver.findElement(AppiumBy.accessibilityId("Continue")).click();
            Allure.step("Clicked the Continue button after entering OTP");
            Utils.addDelay(500);
        }
        catch (Exception e){
            foundContinueBtnErr = " Error-100313: "+ e.getLocalizedMessage();
            GSLogger.print(foundContinueBtnErr);
            e.printStackTrace();
        } finally {
            Assert.assertTrue(foundContinueBtnErr.isEmpty(), "Failed to locate Continue button. "+ foundContinueBtnErr);
        }
    }

    @Severity(SeverityLevel.BLOCKER)
    @Feature("Login")
    @Story("Verify Login")
    @Description("Accept system OTP")
//    @Test(dependsOnMethods ={"test5_RegisterNew"})
    @Test
    private void test8_EnterRegnDetails() {

        String regErr = "";
        try {
            ModuleBase.checkAppiumDriver();
//            Utils.addDelay(3000);

            String regNameId = "RegistrationFirstName";
            WebDriverWait wait = new WebDriverWait( ModuleBase.appiumDriver, Duration.ofSeconds(15));
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

            WebElement stateDropdown = ModuleBase.appiumDriver.findElement(AppiumBy.accessibilityId("RegistrationSelectState"));
            stateDropdown.click();
            Utils.addDelay(5000);

            Utils.addDelay(5000);
            Allure.step("Clicked state dropdown");

            WebElement selectedState = ModuleBase.appiumDriver.findElement(AppiumBy.accessibilityId("Alabama"));
            selectedState.click();
            Utils.addDelay(500);
            Allure.step("Selected state dropdown");

            WebElement Continue = ModuleBase.appiumDriver.findElement(AppiumBy.accessibilityId("Continue"));
            Continue.click();
            Utils.addDelay(500);
            Allure.step("Clicked Continue to complete registration");
            Utils.addDelay(5000);
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
            regErr = " Error-100311: " + e.getLocalizedMessage();
            GSLogger.print(regErr);
            e.printStackTrace();
            ModuleBase.cleanUp();
        } finally {
            Assert.assertTrue(regErr.isEmpty(), " \n\n -------- \n  🔥  🔥  🔥 Registration failed... \n " + regErr + "\n "+"  🔥 🔥 🔥 \n\n");
        }
    }

//    @Severity(SeverityLevel.BLOCKER)
//    @Feature("Logout")
//    @Story("Verify Logout")
//    @Description("Logout and verify user is redirected to Login screen")
//    @Test
//
//    private void logout(){
//        try{
//
//            ModuleBase.checkAppiumDriver();
//            Utils.addDelay(2000);
//            WebElement logoutBtn = ModuleBase.appiumDriver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Logout\"]"));
//            GSLogger.print("Located Logout button");
//
//            logoutBtn.click();
//
//
//        }
//        catch (){
//
//
//        }
//
//}




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