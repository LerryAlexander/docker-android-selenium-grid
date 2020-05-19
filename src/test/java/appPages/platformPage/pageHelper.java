package appPages.platformPage;

import io.appium.java_client.MobileElement;

public class pageHelper {

    public boolean tapOnElement(MobileElement mobileElement){
        try{
            mobileElement.click();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
