import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class driverFactory {
    public WebDriver driver;
    public WebDriverWait wait;

    public void launchBrowser(){
        System.setProperty("webdriver.chrome.driver","src/test/java/webdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    public void waitForElement(String xpath){
        wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        WebElement waitElement = driver.findElement(By.xpath(xpath));
        wait.until(ExpectedConditions.visibilityOf(waitElement));
    }
    public String waitForURL(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        String waitUrl = driver.getCurrentUrl();
        wait.until(ExpectedConditions.urlToBe(waitUrl));
        return waitUrl;
    }
    public void webElementScreenShot(String className) throws IOException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(35));
        WebElement screenshotElement = driver.findElement(By.className(className));
        wait.until(ExpectedConditions.visibilityOf(screenshotElement));
        File fileName = screenshotElement.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(fileName, new File(".//Screenshot/"+className+".png"));
    }

    public void radioButtonClick(String id, String xpath) throws IOException {
        driver.findElement(By.id(id)).click();
        waitForElement(xpath);
        String culturalFileName = driver.findElement(By.xpath(xpath)).getText();
        File culturalyFile = driver.findElement(By.xpath(xpath)).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(culturalyFile,new File(".//Screenshot/"+culturalFileName+".png"));
    }

    public void checkBoxClick(String id, String className) throws IOException {
        WebElement checkBoxElement = driver.findElement(By.id(id));
        checkBoxElement.click();
        boolean checkBoxElementStatus  = checkBoxElement.isSelected();
        webElementScreenShot(className);
    }

    public void appStore(String xpath) throws IOException, InterruptedException {
        WebElement appWebElementName = driver.findElement(By.xpath(xpath));
        appWebElementName.click();
        Thread.sleep(3000);
    }


}
