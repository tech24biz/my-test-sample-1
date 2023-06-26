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

import java.util.List;

public class T2ChatTest {
    @BeforeClass
    void testSuiteSetup() {
        AppiumServer.initServer();
        AllTests.setupDriverWithAppFile();
    }
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Login")
    @Story("Verify Login")
    @Description("Accept system OTP")
    @Test(dependsOnMethods ={"T1LoginTest.test2_ClickContinueWithValidData"})
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
   // @Test(dependsOnMethods ={"test3_SubmitSystemOTP"})

//    private WebElement goofElementByXPath(String pathStr) {
//        return AllTests.appiumDriver.findElement(AppiumBy.xpath(pathStr));
//    }

    @Test(dependsOnMethods ={"T1LoginTest.test2_ClickContinueWithValidData"})
    private void test4_AssertElements() {
        try {

            AllTests.checkAppiumDriver();
            Utils.addDelay(3000);

            WebElement chatHeading = AllTests.appiumDriver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name='Chats']"));
            //Assert whether Chat heading is displayed in the home page
            Assert.assertTrue(chatHeading.isDisplayed());

            WebElement goofGPT_chat = AllTests.appiumDriver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"GG, Goof GPT\"]"));
            //Assert whether at least Goof GPT is displayed in the chat screen
            Assert.assertTrue(goofGPT_chat.isDisplayed());

            /* Search Box TCs */

            WebElement searchBox = AllTests.appiumDriver.findElement(AppiumBy.accessibilityId("Search"));

            //Assert whether SearchBox is displayed for searching characters
            Assert.assertTrue(searchBox.isDisplayed());
            searchBox.click();
            Allure.step("Tapped Search to key in search characters");
            Utils.addDelay(500);
            searchBox.sendKeys("test");

            // Assert whether the user is able to enter the characters in the search box
            Assert.assertTrue(searchBox.getText() == "test");
            Allure.step("Keyed in search characters");
            Utils.addDelay(5000);

            //Add a new Chat

            WebElement addChatButton =  AllTests.appiumDriver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name= 'Add']"));
            // Assert whether the user is tap the Add button to start a new Chat
            Assert.assertTrue(addChatButton.isDisplayed());

            //Check whether all the navigation elements are available
            WebElement chatIcon = AllTests.appiumDriver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name='Chat']"));
            //Assert whether Chat icon is displayed
            Assert.assertTrue(chatIcon.isDisplayed());

            WebElement gamesIcon = AllTests.appiumDriver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name='Games']"));
            //Assert whether Games icon is displayed
            Assert.assertTrue(gamesIcon.isDisplayed());

            WebElement newsIcon = AllTests.appiumDriver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name='News']"));
            //Assert whether News icon is displayed
            Assert.assertTrue(newsIcon.isDisplayed());

            WebElement profileIcon = AllTests.appiumDriver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name='Profile']"));
            //Assert whether Profile icon is displayed
            Assert.assertTrue(profileIcon.isDisplayed());

        } catch (Exception e) {
            GSLogger.print(" Error-100305: " + e.getLocalizedMessage());
            e.printStackTrace();
        }

    }
}
