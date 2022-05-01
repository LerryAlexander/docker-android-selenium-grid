package utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class Hooks {

    private AppiumDriver driver;
    private WebDriverWait wait;

    public void closeApp(MobileDriver<MobileElement> driver) {
        if (driver.isAppInstalled(Constants.APP_ID)) {
            driver.removeApp(Constants.APP_ID); //uninstalling the app
        }
    }

    public void launchApp(MobileDriver<MobileElement> driver) {
        if (!driver.isAppInstalled(Constants.APP_ID)) {
            driver.installApp(Constants.APP_CONTAINER_PATH); //installing the app
            driver.launchApp(); //launching the app
        }
    }

    public boolean initDriver(DriverCapabilities driverCapabilities) throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", driverCapabilities.getPlatform());
        //caps.setCapability("platformVersion", driverCapabilities.getPlatformVersion());
        caps.setCapability("deviceName", driverCapabilities.getDeviceName());
        //caps.setCapability("appium:uiautomator2ServerInstallTimeout", 60000);
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("app", Constants.APP_CONTAINER_PATH);
        caps.setCapability("noSign", true);

        try{
            driver = new AppiumDriver(new URL("http://"+System.getenv("DOCKER_SELENIUM_HOST")+":"+System.getenv("DOCKER_SELENIUM_PORT")+"/wd/hub"), caps);
            wait = new WebDriverWait(driver, 10);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public MobileDriver<MobileElement> getDriver(){
        return driver;
    }

}
