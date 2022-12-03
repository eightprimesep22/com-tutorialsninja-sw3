package myaccounts;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

/**
 * Created by Jay Vaghani
 */
public class MyAccountsTest extends Utility {
    String baseUrl = "http://tutorialsninja.com/demo/index.php?";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    public void selectMyAccountOptions(String option) {
        List<WebElement> myAccountList = driver.findElements(By.xpath("//div[@id='top-links']//li[contains(@class,'open')]/ul/li"));
        try {
            for (WebElement options : myAccountList) {
                if (options.getText().equalsIgnoreCase(option)) {
                    options.click();
                }
            }
        } catch (StaleElementReferenceException e) {
            myAccountList = driver.findElements(By.xpath("//div[@id='top-links']//li[contains(@class,'open')]/ul/li"));
        }
    }

    @Test
    public void verifyUserShouldNavigateToRegisterPageSuccessfully() {
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));
        selectMyAccountOptions("Register");
        String expectedMessage = "Register Account";
        String actualMessage = getTextFromElement(By.xpath("//h1[contains(text(),'Register Account')]"));
        Assert.assertEquals("Register page not displayed", expectedMessage, actualMessage);
    }

    @Test
    public void verifyUserShouldNavigateToLoginPageSuccessfully() {
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));
        selectMyAccountOptions("Login");
        String expectedMessage = "Returning Customer";
        String actualMessage = getTextFromElement(By.xpath("//h2[contains(text(),'Returning Customer')]"));
        Assert.assertEquals("Login page not displayed", expectedMessage, actualMessage);
    }

    @Test
    public void verifyThatUserRegisterAccountSuccessfully() {
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));
        selectMyAccountOptions("Register");
        sendTextToElement(By.id("input-firstname"), "prime" + getAlphaNumericString(4));
        sendTextToElement(By.id("input-lastname"), "test" + getAlphaNumericString(4));
        sendTextToElement(By.id("input-email"), "prime" + getAlphaNumericString(4) + "@gmail.com");
        sendTextToElement(By.id("input-telephone"), "07988112233");
        sendTextToElement(By.id("input-password"), "test123");
        sendTextToElement(By.id("input-confirm"), "test123");
        List<WebElement> radioButtons = driver.findElements(By.xpath("//fieldset[3]//input"));
        for (WebElement e : radioButtons) {
            if (e.getText().equals("Yes")) {
                e.click();
                break;
            }
        }
        clickOnElement(By.xpath("//div[@class = 'buttons']//input[@name='agree']"));
        clickOnElement(By.xpath("//div[@class = 'buttons']//input[@value='Continue']"));
        Assert.assertEquals("", "Your Account Has Been Created!",
                getTextFromElement(By.xpath("//h1[contains(text(),'Your Account Has Been Created!')]")));
        clickOnElement(By.xpath("//a[contains(text(),'Continue')]"));
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));
        selectMyAccountOptions("Logout");
        String expectedMessage = "Account Logout";
        String actualMessage = getTextFromElement(By.xpath("//h1[contains(text(),'Account Logout')]"));
        Assert.assertEquals("Not logged out", expectedMessage, actualMessage);
        clickOnElement(By.xpath("//a[contains(text(),'Continue')]"));
    }

    @Test
    public void verifyThatUserShouldLoginAndLogoutSuccessfully() {
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));
        selectMyAccountOptions("Login");
        sendTextToElement(By.id("input-email"), "prime123@gmail.com");
        sendTextToElement(By.id("input-password"), "test123");
        clickOnElement(By.xpath("//form[contains(@action,'login')]//input[@type='submit']"));
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));
        selectMyAccountOptions("Logout");
        String expectedMessage = "Account Logout";
        String actualMessage = getTextFromElement(By.xpath("//h1[contains(text(),'Account Logout')]"));
        Assert.assertEquals("Not logged out", expectedMessage, actualMessage);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }

}
