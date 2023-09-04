import io.appium.java_client.AppiumBy;
import io.qameta.allure.*;
import org.example.GSLogger;
import org.example.Utils;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

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

//            if(goofGPTHeading.isDisplayed())
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
    }



    @Severity(SeverityLevel.BLOCKER)
    @Feature("Add a new Chat")
    @Story("Add a new Chat")
    @Description("Handle Contact permission, and a add new chat")
//   @Test(dependsOnMethods ={"test5_RegisterNew"})
    @Test
    private void contactPermission() {
     //   String otpEnteredErr = "";
        WebElement contactPermissionHeading = ModuleBase.appiumDriver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"“Goof Sports” Would Like to Access Your Contacts\"]"));
        try {
            ModuleBase.checkAppiumDriver();
            Utils.addDelay(500);
            //Utils.addDelay(2000);

            if (contactPermissionHeading.isDisplayed()) {
                GSLogger.print("Contact Permission Dialog Box appears");
                WebElement alertOK = ModuleBase.appiumDriver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"OK\"]"));
                alertOK.click();
                GSLogger.print("Contact Permission accepted");
                Allure.step("Contact Permission accepted");
            }
            else {
                GSLogger.print("Contact Permission pop-up not displayed");
                return;
            }

            Utils.addDelay(3000);
            WebElement contactToTap = ModuleBase.appiumDriver.findElement(AppiumBy.accessibilityId("T1, Test 1User, +16505551231"));
            contactToTap.click();
            Allure.step("Clicked on the contact for P2P chat");
            GSLogger.print("Clicked on the contact for P2P chat");

            Utils.addDelay(3000);
            WebElement chatNavBar = ModuleBase.appiumDriver.findElement(AppiumBy.xpath("(//XCUIElementTypeButton[@name=\"Chat\"])[2]"));
            chatNavBar.click();
            Allure.step("Clicked on the Chat button in Navigation bar");
            GSLogger.print("Clicked on the Chat button in Navigation bar");
            Utils.addDelay(5000);

            WebElement notificationPermissionHeading = ModuleBase.appiumDriver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"“Goof Sports” Would Like to Send You Notifications\"]"));
            if (notificationPermissionHeading.isDisplayed()) {
                GSLogger.print("Notification Permission Dialog Box appears");
                WebElement alertAllow = ModuleBase.appiumDriver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Allow\"]"));
                alertAllow.click();
                Utils.addDelay(3000);
                GSLogger.print("Notification Permission accepted");
                Allure.step("Notification Permission accepted");
            }

            else {
                GSLogger.print("Contact Permission pop-up not displayed");
                return;
            }

            WebElement chatTextField = ModuleBase.appiumDriver.findElement(AppiumBy.xpath("//XCUIElementTypeApplication[@name=\"Goof Sports\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTextView"));
            Utils.addDelay(3000);
            String msgText = "Hi there!!!";
            chatTextField.sendKeys(msgText);

            Utils.addDelay(2000);
            WebElement sendText = ModuleBase.appiumDriver.findElement(AppiumBy.accessibilityId("Arrow Up Circle"));
            Utils.addDelay(2000);
            if(sendText.isDisplayed()) {
                sendText.click();
                GSLogger.print("Clicked send text button");
                Utils.addDelay(3000);
            }
            else{
                System.out.println("Why is it not getting displayed???");
                return;
            }

            WebElement chatTextDisplayed = ModuleBase.appiumDriver.findElement(AppiumBy.xpath("//XCUIElementTypeApplication[@name=\"Goof Sports\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]"));
            Assert.assertTrue(chatTextDisplayed.isDisplayed(), "The keyed in chat message is displayed");
        }

        catch (Exception e) {
            GSLogger.print(" Error-100321: " + e.getLocalizedMessage());
            e.printStackTrace();
        }

//        finally {
//            Assert.assertTrue(contactPermissionHeading.isEnabled(), "Contact Permission Popup is not displayed");
//        }

    }
}