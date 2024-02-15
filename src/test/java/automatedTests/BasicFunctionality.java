package automatedTests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//Test account:
//test@protonmail.com
//Intern1!
public class BasicFunctionality {
    WebDriver driver;
    WebDriverWait wait;

    public void click(By by) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
        element.click();
    }

    public void sendKeys(By by, String text) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        element.sendKeys(text);
    }

    @Before
    public void setup() {
        System.setProperty("webdriver.edge.driver", "C:\\Selenium\\msedgedriver.exe");
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--remote-allow-origins=*", "--ignore-certificate-errors");
        driver = new EdgeDriver(edgeOptions);
        driver.manage().window().maximize();
        driver.get("https://eclectic-cucurucho-2ae5e1.netlify.app/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
    }

    @After
    public void teardown() {
        driver.quit();

    }

    @Test
    public void Login() {
        click(By.cssSelector(".sc-gsTDqH > button:nth-child(1)"));
        sendKeys(By.name("email"), "test@protonmail.com");
        sendKeys(By.name("password"), "Intern1!");
        click(By.cssSelector(".sc-hBEZvw"));
        click(By.cssSelector(".sc-crrtmM > svg:nth-child(1)"));
        Assert.assertEquals("Welcome, John", driver.findElement(By.cssSelector(".sc-kfzCjt > h1:nth-child(1)")).getText());
    }

    @Test
    public void Logout() {
        click(By.cssSelector(".sc-gsTDqH > button:nth-child(1)"));
        sendKeys(By.name("email"), "test@protonmail.com");
        sendKeys(By.name("password"), "Intern1!");
        click(By.cssSelector(".sc-hBEZvw"));
        click(By.cssSelector(".sc-crrtmM > svg:nth-child(3)"));
        Assert.assertEquals("You are no longer logged in.", driver.findElement(By.cssSelector(".sc-dtwofg > h1:nth-child(1)")).getText());
    }

}