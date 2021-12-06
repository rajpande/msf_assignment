import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.json.Json;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

public class TestClass extends BaseClass  {
    @BeforeClass
    public void openBrowser(){
        launchBrowser();
    }
   /* @Test
    public void loginApplication(){
        loadApplication();
        login("CANDIDATE", "quality");
        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[contains(text(),'QA and Support')]"))));
    }*/
    @Test
    public void writeFeedBack() throws InterruptedException {
        loadApplication();
        login("CANDIDATE", "quality");
        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[contains(text(),'QA and Support')]"))));
        navigateToFeedback();
        addNewFeedback();
    }
    /*@AfterClass
    public void closeBrowser(){close();}*/
}
