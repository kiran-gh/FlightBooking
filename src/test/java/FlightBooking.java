import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class FlightBooking extends driverFactory{
    WebDriverWait wait;
    By searchFlightsButtonEle = By.id("searchBtn");
    By selectFlightEle = By.xpath("(//input[@type=\"radio\"])[1]");
    By bookNowButtonEle = By.tagName("button");
    By enterPasswordEle = By.xpath("//input[@type=\"password\"]");
    By headingElement = By.tagName("h2");


    @BeforeMethod
    public void launchChromeBrowser() {
        launchBrowser();
        wait = new WebDriverWait(driver, Duration.ofSeconds(35));
    }


    public void clickOnAnElement(By locator){
        WebElement element = driver.findElement(locator);
        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
    }

    @Test(priority = 1)
    public void FlightBookingPageTest() throws InterruptedException, IOException {
        driver.get("https://qaflightbooking.ccbp.tech/");
        waitForURL();
        driver.findElement(By.id("departureCity")).sendKeys("New York");
        driver.findElement(By.id("destinationCity")).sendKeys("Los Angeles");
        driver.findElement(By.id("travelDate")).sendKeys("01/08/2023");
        driver.findElement(By.id("passengers")).sendKeys("2");
        Thread.sleep(3000);
        clickOnAnElement(searchFlightsButtonEle);
        clickOnAnElement(selectFlightEle);
        Thread.sleep(3000);
        String availableFlightsHeading = driver.findElement(headingElement).getText();
//        checks if available flights heading is available or not
        Assert.assertEquals(availableFlightsHeading,"Available Flights","Available flights heading miss-matching ");
        clickOnAnElement(bookNowButtonEle);
        String paymentDetailsHeading = driver.findElement(headingElement).getText();
//        checks if payment details heading is available or not
        Assert.assertEquals(paymentDetailsHeading,"Payment Details","Payment details heading miss-matching ");
        wait.until(ExpectedConditions.visibilityOfElementLocated(enterPasswordEle));
        driver.findElement(enterPasswordEle).sendKeys("traveler123");
        clickOnAnElement(bookNowButtonEle);
        Thread.sleep(3000);
        String bookingSuccessHeading = driver.findElement(headingElement).getText();
//        checks if booking success heading is available or not
        Assert.assertEquals(bookingSuccessHeading,"Booking Success!","Booking success heading miss-matching ");
//        Taking Screen shot
        File bookingSuccess = driver.findElement(By.className("container")).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(bookingSuccess, new File(".//Screenshot/" + "bookingSuccess" + ".png"));
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
