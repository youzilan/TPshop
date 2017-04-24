package InterfaceFrameWork;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Mouse;

public class BrowserUtils extends Browser {
    private static Logger logger=Logger.getLogger(BrowserUtils.class);

    public  BrowserUtils(int driverType) {
        super(driverType);
    }



    public WebDriver getDriver(){
        return driver;
    }
    public void openWeb(String url){
        if (url.equals("")||url==null){
            logger.info("url为空");
            return ;
        }else{
            driver.get(url);
            logger.info("url为"+url);
        }
    }

    public void closeWeb(){
        logger.info("关闭浏览器");
        driver.close();
    }

    public void pause(long milliseconds){
        if (milliseconds<=0){
            return;
        }else{
            try {
                Thread.sleep(milliseconds);
                logger.info("等待"+milliseconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public  void quit(){
        logger.info("退出浏览器");
        driver.quit();
    }
    public  String getCurrentUrl(){
       String CurrentUrl=driver.getCurrentUrl();
       logger.info("获取当前的URL："+CurrentUrl);
        return CurrentUrl;
    }
    public void refrash(){
        logger.info("刷新");
        driver.navigate().refresh();
    }

    //
    public WebDriver switchTo_window(String name){
        logger.info("跳转到窗口"+name);
         driver=driver.switchTo().window(name);
        return driver;
    }
    public WebDriver switch_Frame(String frame){
        logger.info("跳转到内嵌页面"+frame);
        driver=driver.switchTo().frame(frame);
        return driver;
    }
    public WebDriver switch_Frame(int frame){
        logger.info("跳转到内嵌页面"+frame);
        driver=driver.switchTo().frame(frame);
        return driver;
    }
    public WebDriver switch_Frame(WebElement element){
        logger.info("跳转到内嵌元素"+element);
        driver=driver.switchTo().frame(element);
        return driver;
    }
    public void forword(){
        logger.info("向前");
        driver.navigate().forward();
    }
    public void back(){
        logger.info("后退");
        driver.navigate().back();
    }

//截屏
    public void screenShot(String string){

    }
    public void screenShot(String string,String string1){

    }
    private void sshot(String string,String string1){

    }
    JavascriptExecutor js;
    Mouse mouse;

}
