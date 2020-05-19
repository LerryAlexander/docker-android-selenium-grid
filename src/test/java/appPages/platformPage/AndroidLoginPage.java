package appPages.platformPage;

import appPages.BasePage;
import appPages.LoginPage;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidLoginPage extends BasePage implements LoginPage {

    public AndroidLoginPage(MobileDriver<MobileElement> driver) {
        super(driver);
    }

    //page factory
    @AndroidFindBy(accessibility = "username")
    private MobileElement username;

    @AndroidFindBy(accessibility = "password")
    private MobileElement password;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"loginBtn\"]/android.widget.TextView")
    private MobileElement loginButton;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.TextView[2]")
    private MobileElement loginWelcomeMessage;


    @Override
    public String login(String user, String pass) {
        username.sendKeys(user);
        password.sendKeys(pass);
        loginButton.click();
        return loginWelcomeMessage.getText();
    }

}
