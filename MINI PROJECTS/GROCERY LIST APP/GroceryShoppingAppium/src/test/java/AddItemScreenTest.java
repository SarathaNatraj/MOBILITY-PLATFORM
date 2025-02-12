import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AddItemScreenTest {
    private static AndroidDriver driver;

    @Before
    public  void setup() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "motorola moto g85 5G");
        caps.setCapability("appPackage", "com.example.groceryshoppinglistapp");
        caps.setCapability("appActivity", "com.example.groceryshoppinglistapp.MainActivity");
        caps.setCapability("automationName", "UiAutomator2");

        driver = new AndroidDriver(new URL("http://localhost:4723"), caps);
    }

    @Test
    public void test1_ValidateEmptyItemName() {
        driver.findElement(By.id("com.example.groceryshoppinglistapp:id/fabAdd")).click();
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }

        driver.findElement(By.id("com.example.groceryshoppinglistapp:id/buttonSave")).click();
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }

        WebElement errorMessage = driver.findElement(By.id("com.example.groceryshoppinglistapp:id/txtErrorMessage"));
        assertTrue("Error message should be displayed for empty item name", errorMessage.isDisplayed());
    }

    @Test
    public void test2_SaveNewItemAndVerify() {
        driver.findElement(By.id("com.example.groceryshoppinglistapp:id/editTextItemName")).sendKeys("Apples");
        driver.findElement(By.id("com.example.groceryshoppinglistapp:id/editTextQuantity")).sendKeys("2");
        driver.findElement(By.id("com.example.groceryshoppinglistapp:id/buttonSave")).click();
        try { Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }

        boolean isHomeScreen = driver.findElements(By.id("com.example.groceryshoppinglistapp:id/recyclerView")).size() > 0;
        assertTrue("Should navigate back to Home Screen", isHomeScreen);

        boolean isItemDisplayed = driver.findElements(By.xpath("//android.widget.TextView[@text='Apples']")).size() > 0;
        assertTrue("Saved item should be displayed on Home Screen", isItemDisplayed);
    }

    @Test
    public void test3_NavigateBackToHomeScreen() {
        driver.findElement(By.id("com.example.groceryshoppinglistapp:id/fabAdd")).click();
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }

        driver.findElement(By.id("com.example.groceryshoppinglistapp:id/buttonBack")).click();
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }

        boolean isHomeScreen = driver.findElements(By.id("com.example.groceryshoppinglistapp:id/recyclerView")).size() > 0;
        assertTrue("Should be back on the Home Screen", isHomeScreen);
    }

    @After
    public  void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
