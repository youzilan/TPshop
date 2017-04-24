package UI;

import InterfaceFrameWork.Browser;
import InterfaceFrameWork.BrowserPageUtils;
import InterfaceFrameWork.BrowserUtils;

/**
 * Created by hy on 2017/4/24.
 */
public class openShouYe {
    public String openShouYe(String url){
        String response=null;
        BrowserPageUtils browser=new BrowserPageUtils(2);
        browser.openWeb(url);
        return response;
    }

}
