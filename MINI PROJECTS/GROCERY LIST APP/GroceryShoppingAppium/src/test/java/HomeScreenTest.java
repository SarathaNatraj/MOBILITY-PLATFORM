import static org.junit.Assert.*;

import org.asynchttpclient.util.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.OrderWith;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class HomeScreenTest {
    private AndroidDriver driver;

    @Before
   public void setup() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "motorola moto g85 5G");
        caps.setCapability("appPackage", "com.example.groceryshoppinglistapp");
        caps.setCapability("appActivity", "com.example.groceryshoppinglistapp.MainActivity");
        caps.setCapability("automationName", "UiAutomator2");

        driver = new AndroidDriver(new URL("http://localhost:4723"), caps);
    }

    @Test
   public void testFetchAndDisplayItems() {
        // Wait for RecyclerView to load
        try { Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }

        List elements = driver.findElements(By.id("com.example.groceryshoppinglistapp:id/recyclerView"));
        assertTrue("Items should be displayed in RecyclerView",elements.size() > 0);
    }

    @Test
  public  void testSwipeToDelete() {
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }

        List elements = driver.findElements(By.id("com.example.groceryshoppinglistapp:id/item_grocery"));
        if (!elements.isEmpty()) {
            int xStart = ((WebElement) elements.get(0)).getLocation().getX();
            int yStart = ((WebElement) elements.get(0)).getLocation().getY();
            int xEnd = xStart + 500; // Swipe right

            new TouchAction(driver)
                .press(PointOption.point(xStart, yStart))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(xEnd, yStart))
                .release()
                .perform();

            try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }

            List updatedElements = driver.findElements(By.id("com.example.groceryshoppinglistapp:id/item_grocery"));
            assertTrue("Item should be deleted from RecyclerView",updatedElements.size() < elements.size());
        } else {
           fail("No items found to delete");
        }
    }

    @Test
   public void testNavigateToAddItemScreen() {
        driver.findElement(By.id("com.example.groceryshoppinglistapp:id/fabAdd")).click();
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }

        boolean isAddItemScreen = driver.findElements(By.id("com.example.groceryshoppinglistapp:id/editTextItemName")).size() > 0;
        assertTrue("Should navigate to Add Item screen",isAddItemScreen);
    }

    @After
   public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
