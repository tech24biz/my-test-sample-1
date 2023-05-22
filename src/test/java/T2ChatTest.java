import io.appium.java_client.AppiumBy;
import io.qameta.allure.*;
import org.example.GSLogger;
import org.example.Utils;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class T2ChatTest {

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
