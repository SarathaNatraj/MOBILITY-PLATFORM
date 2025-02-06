
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class CalculatorAppTest {

    public static void main(String[] args) {
        try {     
        	 {
        		 //device details
                 DesiredCapabilities capabilities = new DesiredCapabilities();
                 capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
                 capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "motorola moto g85 5G");
                 capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
                 
                 //base package, launcher 
                 capabilities.setCapability("appPackage", "com.example.calculatorapp");
                 capabilities.setCapability("appActivity", ".MainActivity");

                 //driver details
                 AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);

                 //accesing the elements
                 WebElement firstNumber = driver.findElement(By.id("com.example.calculatorapp:id/etFirstNumber"));
                 WebElement secondNumber = driver.findElement(By.id("com.example.calculatorapp:id/etSecondNumber"));
                 WebElement addButton = driver.findElement(By.id("com.example.calculatorapp:id/btnAdd"));
                 WebElement resultView = driver.findElement(By.id("com.example.calculatorapp:id/tvResult"));

                 firstNumber.sendKeys("5"); //type text 5
                 secondNumber.sendKeys("3"); // type text 3
                 addButton.click();

                 String resultText = resultView.getText();
                 if (resultText.contains("8")) {
                     System.out.println("Test Passed: Result is correct.");
                 } else {
                     System.out.println("Test Failed: Incorrect result.");
                 }

                 driver.quit();

        	 }
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
