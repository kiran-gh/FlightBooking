import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class FlightBooking extends driverFactory{
    @BeforeMethod
    public void launchChromeBrowser() {
        launchBrowser();
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
        driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@value=\"0\"]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[normalize-space()=\"Book Now\"]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@placeholder=\"Enter password\"]")).sendKeys("traveler123");
        driver.findElement(By.xpath("//button[normalize-space()=\"Pay Now\"]")).click();
        Thread.sleep(3000);
        System.out.println(driver.findElement(By.tagName("h2")).getText());
        File bookingSuccess = driver.findElement(By.className("container")).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(bookingSuccess, new File(".//Screenshot/" + "bookingSuccess" + ".png"));
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
