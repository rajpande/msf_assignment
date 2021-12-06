import org.openqa.selenium.JavascriptExecutor;
import com.github.dockerjava.api.model.WaitResponse;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

public class BaseClass {
    WebDriver driver;
    public void launchBrowser(){
        WebDriverManager.chromedriver().browserVersion("96.0.4664.45").setup();
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
    }

    public void loadApplication(){
        this.driver.get("https://training.unidata.msf.org/");
        this.driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
    }

    public void login(String username, String password){
        WebElement btnHere = driver.findElement(By.xpath("//div[contains(text(),'non-personal')]/a"));
        btnHere.click();
        WebElement txtUsername = driver.findElement(By.id("login"));
        txtUsername.clear();
        txtUsername.sendKeys(username);
        WebElement txtPassword = driver.findElement(By.id("password"));
        txtPassword.clear();
        txtPassword.sendKeys(password);
        WebElement btnLogin = driver.findElement(By.id("edit-submit"));
        btnLogin.click();
    }

    public void close(){
        this.driver.quit();
    }

    public void navigateToFeedback(){
        WebElement leftPane = driver.findElement(By.xpath("//div[@class='_ebx-vertical-splitter_leftPane']"));
        String attribute = leftPane.getAttribute("style");
        if(attribute.contains("9.62%")){
            WebElement leftPaneOpen = driver.findElement(By.xpath("//div[@class='_ebx-header _ebx-navigation_title']"));
            leftPaneOpen.click();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            WebElement feedBack = driver.findElement(By.xpath("//li[@data-key='890']"));
            feedBack.click();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            WebElement feedBackAll = driver.findElement(By.xpath("//li[@data-key='890']/following-sibling::ul/li[1]"));
            feedBackAll.click();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            WebDriverWait wait = new WebDriverWait(driver, 40);
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h2/span[text()='Feedback (All)']"))));
        }
        else{
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            WebElement feedBack = driver.findElement(By.xpath("//li[@data-key='890']"));
            feedBack.click();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            WebElement feedBackAll = driver.findElement(By.xpath("//li[@data-key='890']/following-sibling::ul/li[1]"));
            feedBackAll.click();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            WebDriverWait wait = new WebDriverWait(driver, 40);
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h2/span[text()='Feedback (All)']"))));
        }
    }
    public void addNewFeedback() throws InterruptedException {
        Thread.sleep(4000);
        driver.switchTo().frame("ebx-legacy-frame_0");
        WebElement btnAddNew = driver.findElement(By.xpath("//div[@class='ebx_ToolsAlignLeft']/div[1]"));
        btnAddNew.click();
        Thread.sleep(3000);
        WebElement rdShowSubject = driver.findElement(By.xpath("//label[contains(text(),'Show Subject')]/ancestor::td[1]//following-sibling::td[2]//*[contains(text(),'Yes')]"));
        rdShowSubject.click();
        Thread.sleep(2000);
        WebElement articleDropDown = driver.findElement(By.xpath("//tr[@id='___articlesId']//button[2]"));
        articleDropDown.click();
        Thread.sleep(2000);
        WebElement dropDownOption = driver.findElement(By.xpath("//div[@id='ebx_ISS_Item_2']"));
        dropDownOption.click();
        Thread.sleep(2000);
        WebElement descriptionDropDown = driver.findElement(By.xpath("//*[@id='___technicalSheetId']//button[2]"));
        descriptionDropDown.click();
        Thread.sleep(2000);
        WebElement dscDropDownOption = driver.findElement(By.xpath("//div[@id='ebx_ISS_Item_3']"));
        dscDropDownOption.click();
        WebElement btnUserAdd = driver.findElement(By.xpath("//tr[@id='___user']/td[3]/div[1]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();",btnUserAdd);
        btnUserAdd.click();
        WebElement usrDropDown = driver.findElement(By.xpath("//*[@id='___user']//li[1]/div[1]//button[@title='Open drop-down list']"));
        usrDropDown.click();
        WebElement tst = driver.findElement(By.xpath("//*[@id='___user']//li[1]/div[1]//input[2]"));
        tst.clear();
        tst.sendKeys("Mrs Marjolein De Wilde");
        WebElement usrDropDownOption = driver.findElement(By.xpath("//div[contains(text(),'Marjolein De Wilde')]"));
        usrDropDownOption.click();
        Thread.sleep(3000);
        WebElement msgAdd = driver.findElement(By.xpath("//tr[@id='___messages']/td[3]/div[1]"));
        js.executeScript("arguments[0].scrollIntoView();",msgAdd);
        msgAdd.click();
        Thread.sleep(2000);
        WebElement msgbox = driver.findElement(By.xpath("//textarea[@id='___40_cfvAO__messages_5b_0_5d___message']"));
        msgbox.clear();
        msgbox.sendKeys("Feedback created by : Raj Pande");
        WebElement btnSaveAndClose = driver.findElement(By.xpath("//button[@type='submit' and contains(text(),'and')]"));
        btnSaveAndClose.click();



    }

    public JSONObject handleJson() throws InterruptedException, IOException, ParseException, org.json.simple.parser.ParseException {
        JSONParser jsonParser = new JSONParser();
        try{
            FileReader reader = new FileReader("src/test/TestData/TestData.json");
            Object object = jsonParser.parse(reader);
            JSONObject feedBackData = (JSONObject) object;
            return feedBackData;
        }catch (FileNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

}
