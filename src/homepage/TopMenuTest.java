package homepage;

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
public class TopMenuTest extends Utility {
    String baseUrl = "http://tutorialsninja.com/demo/index.php?";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    public void selectMenu(String menu) {
        List<WebElement> topMenuList = driver.findElements(By.xpath("//nav[@id='menu']//ul/li[contains(@class, 'open')]/div/child::*"));
        try {
            for (WebElement element : topMenuList) {
                if (element.getText().equalsIgnoreCase(menu)) {
                    element.click();
                }
            }
        } catch (StaleElementReferenceException e) {
            topMenuList = driver.findElements(By.xpath("//nav[@id='menu']//ul/li[contains(@class, 'open')]/div/child::*"));
        }
    }

    @Test
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully() {
        mouseHoverToElementAndClick(By.linkText("Desktops"));
        selectMenu("Show All Desktops");
        String expectedText = "Desktops";
        String actualText = getTextFromElement(By.xpath("//h2[contains(text(),'Desktops')]"));
        Assert.assertEquals("Not navigate to Desktop page", expectedText, actualText);
    }

    @Test
    public void verifyUserShouldNavigateToLaptopsAndNotebooksPageSuccessfully() {
        mouseHoverToElementAndClick(By.linkText("Laptops & Notebooks"));
        selectMenu("Show All Laptops & Notebooks");
        Assert.assertEquals("Not navigate to Laptops and Notebooks page",
                "Laptops & Notebooks",
                getTextFromElement(By.xpath("//h2[contains(text(),'Laptops & Notebooks')]")));
    }

    @Test
    public void verifyUserShouldNavigateToComponentsPageSuccessfully() {
        mouseHoverToElementAndClick(By.linkText("Components"));
        selectMenu("Show All Components");
        Assert.assertEquals("Not navigate to Laptops and Notebooks page",
                "Components", getTextFromElement(By.xpath("//h2[contains(text(),'Components')]")));
    }

    @After
    public void tearDown(){
        closeBrowser();
    }

}
