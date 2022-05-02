import appPages.HomePage;
import appPages.LoginPage;
import appPages.platformPage.AndroidHomePage;
import appPages.platformPage.AndroidLoginPage;

import static org.junit.Assert.*;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import utilities.Constants;
import utilities.DriverCapabilities;
import utilities.Hooks;

public class TestRunner {

    private MobileDriver<MobileElement> driver;
    private Hooks hooks;
    private DriverCapabilities driverCapabilities;
    private HomePage homePage;
    private LoginPage loginPage;

    @BeforeTest
    @Parameters({"platform", "deviceName"})
    public void setCapabilities(String platform, String platformVersion, String deviceName){
        driverCapabilities = new DriverCapabilities();
        driverCapabilities.setPlatform(platform);
        driverCapabilities.setDeviceName(deviceName);
    }

    @BeforeClass
    public boolean initDriver() throws Exception {
        hooks = new Hooks();
        for(int i=0; i<3; i++){
            if(hooks.initDriver(driverCapabilities)){
                driver = hooks.getDriver();
                return true;
            }
        }
        throw new Exception("Error initializing driver");
    }

    @BeforeMethod
    public void launchApp() throws Exception {
        hooks.launchApp(driver);
    }

    @Test(threadPoolSize = 2, dataProvider = "userCredentials")
    public void loginTest(String username, String password) throws Exception {
        homePage = new AndroidHomePage(driver);
        homePage.choseView("login_screen");

        loginPage = new AndroidLoginPage(driver);
        assertEquals("Wrong welcome message on Login page", Constants.LOGIN_WELCOME_MESSAGE+username, loginPage.login(username, password));
    }

    @AfterMethod
    public void closeApp(){
        hooks.closeApp(driver);
    }

    @AfterTest
    public void tearDown(){
        if(driver!=null){
            driver.quit();
        }
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