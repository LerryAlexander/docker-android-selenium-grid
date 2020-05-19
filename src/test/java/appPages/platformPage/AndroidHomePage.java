package appPages.platformPage;

import appPages.BasePage;
import appPages.HomePage;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;

public class AndroidHomePage extends BasePage implements HomePage {

    //page factory
    @AndroidFindBy(accessibility = "Login Screen")
    private MobileElement loginScreenOption;

    public AndroidHomePage(MobileDriver<MobileElement> driver){
        super(driver);
    }

    @Override
    public void choseView(String viewOption) throws Exception {
        switch (viewOption.toUpperCase()){
            case "LOGIN_SCREEN":
                loginScreenOption.click();
                break;
            default:
                throw new Exception("Wrong view option");
        }
    }
}
