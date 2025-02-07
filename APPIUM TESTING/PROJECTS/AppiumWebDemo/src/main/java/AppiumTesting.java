
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.net.URL;

public class AppiumTesting {

    public static void main(String[] args) {
        try {
            // Set Desired Capabilities for Android driverYour_Device_Name
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "motorola moto g85 5G");
            capabilities.setCapability("browserName", "Chrome");
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");

            // Initialize the WebDriver
            AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);

            // Open the website
            driver.get("https://www.apple.com/in");

            // Wait for the page to load (you may want to add WebDriverWait for a more robust solution)
            Thread.sleep(2000);

            // Find the "Learn More" button (assuming it's a link, you may need to adjust the selector)
            WebElement learnMoreButton = driver.findElement(By.xpath("//a[contains(text(), 'Learn more')]"));
            
            System.out.println("learnMoreButton: "+learnMoreButton);

            // Click the "Learn More" button
            learnMoreButton.click();

            // Wait for the URL to change
            Thread.sleep(2000);

            // Verify URL change
            String currentUrl = driver.getCurrentUrl();
            System.out.println(" current URL :: "+currentUrl);
            if(currentUrl.equalsIgnoreCase("https://www.apple.com/in/iphone-16-pro/")) {
                System.out.println("Successfully navigated to: " + currentUrl);
            } else {
                System.out.println("URL did not change correctly.");
            }

            // Close the browser
            driver.quit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

