package InterfaceFrameWork;

import java.util.ResourceBundle;

/**
 * Created by hy on 2017/4/23.
 */
public class SeleniumConfig {
    private static ResourceBundle rb=ResourceBundle.getBundle("selenium");
    //driverType
    //Integer REQUEST_TYPE=Integer.valueOf(rb.getString("request.type"));
    public final static Integer FIREFOXDRIVER=Integer.valueOf(rb.getString("firefox.driver"));
    public final static Integer CHROMEDRIVER=Integer.valueOf(rb.getString("chrome.driver"));

}
