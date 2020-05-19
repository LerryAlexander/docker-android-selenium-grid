import appPages.HomePage;
import appPages.LoginPage;
import appPages.platformPage.AndroidHomePage;
import appPages.platformPage.AndroidLoginPage;
import io.appium.java_client.AppiumDriver;
import static org.junit.Assert.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import utilities.Constants;

import java.io.IOException;
import java.net.URL;

public class TestRunner {

    private String seleniumHost;
    private String seleniumPort;
    private String platform;
    private String platformVersion;
    private String systemPort;
    private String deviceName;

    @BeforeTest
    @Parameters({"seleniumHost", "seleniumPort", "platform", "platformVersion", "systemPort", "deviceName"})
    public void setCapabilities(String seleniumHost, String seleniumPort, String platform, String platformVersion, String systemPort, String deviceName){
        this.seleniumHost = seleniumHost;
        this.seleniumPort = seleniumPort;
        this.platform = platform;
        this.platformVersion = platformVersion;
        this.systemPort = systemPort;
        this.deviceName = deviceName;
    }

    private String APP = "https://github.com/cloudgrey-io/the-app/releases/download/v1.8.0/TheApp-v1.8.0.apk";

    private AppiumDriver driver;
    private WebDriverWait wait;

    @Test
    public void testInstallingDependencies() throws IOException {
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("platformName", platform);
        caps.setCapability("deviceName", deviceName);
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("app", "/root/apk/TheApp-v1.8.0.apk");

        driver = new AppiumDriver(new URL("http://"+System.getenv("DOCKER_SELENIUM_HOST")+":"+System.getenv("DOCKER_SELENIUM_PORT")+"/wd/hub"), caps);
        wait = new WebDriverWait(driver, 10);

        // Test code goes here

        driver.quit();
    }

    @Test(dataProvider = "userCredentials")
    public void testSkippingInstallOfDependencies(String username, String password) throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("platformName", platform);
        caps.setCapability("deviceName", deviceName);
        caps.setCapability("automationName", "UiAutomator2");

        // App is already installed on device, so can be launched by Package name and Activity
        caps.setCapability("appPackage", "io.cloudgrey.the_app");
        caps.setCapability("appActivity", "io.cloudgrey.the_app.MainActivity");

        // Skip the installation of io.appium.settings app and the UIAutomator 2 server.
        caps.setCapability("skipDeviceInitialization", true);
        caps.setCapability("skipServerInstallation", true);

        driver = new AppiumDriver(new URL("http://"+System.getenv("DOCKER_SELENIUM_HOST")+":"+System.getenv("DOCKER_SELENIUM_PORT")+"/wd/hub"), caps);
        wait = new WebDriverWait(driver, 10);

        // Test code goes here
        HomePage homePage = new AndroidHomePage(driver);
        homePage.choseView("login_screen");

        LoginPage loginPage = new AndroidLoginPage(driver);
        assertEquals("Wrong welcome message on Login page", Constants.LOGIN_WELCOME_MESSAGE+username, loginPage.login(username, password));

        driver.quit();
    }

    @DataProvider(name = "userCredentials")
    public Object[][] getUserCredentials(){
        return new Object[][]
            {
                { "alice", "mypassword" },
                { "bob", "totallysecure" }
            };
    }

}