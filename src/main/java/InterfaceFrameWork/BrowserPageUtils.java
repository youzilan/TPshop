package InterfaceFrameWork;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by hy on 2017/4/23.
 */
public class BrowserPageUtils extends BrowserUtils{

    private static Logger logger=Logger.getLogger(BrowserPageUtils.class);

    public BrowserPageUtils(int driverType) {
        super(driverType);

    }


    public WebElement findElementById(String id){
        List<WebElement> element=null;
        if (id.equals(" ")||id==null){
            logger.error("id为空，找不到元素");
        }else{
            element=driver.findElements(By.id(id));
            if (element==null){
                logger.info("找不到id为"+id+" 的元素");
            }else if(element.size()>1){
                logger.info("找到的元素不止一个，返回第一个元素");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return element.get(0);
            }else{
                logger.info("查找到id为"+id+" 的元素");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return element.get(0);
            }
        }
        return element.get(0);
    }

    public WebElement findElementByLinkText(String linkText){
        List<WebElement> element=null;
        if (linkText.equals(" ")||linkText==null){
            logger.error("id为空，找不到元素");
            return null;
        }else{
            element=driver.findElements(By.id(linkText));

            if (element==null||element.size()==0){
                logger.info("找不到linkText为"+linkText+" 的元素");
                return null;
            }else if(element.size()>1){
                logger.info("找到的元素不止一个，返回第一个元素");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return element.get(0);
            }else{
                logger.info("查找到linkText为"+linkText+" 的元素");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return element.get(0);
            }
        }
    }


    public List<WebElement> findElementsByTagName(String tagName){
        List<WebElement> element=null;
        if (tagName.equals(" ")||tagName==null){
            logger.error("tagName为空，找不到元素");
            return null;
        }else{
            element=driver.findElements(By.id(tagName));
            if (element==null){
                logger.info("找不到tagName为"+tagName+" 的元素");
                return null;
            }else{
                logger.info("查找到tagName为"+tagName+" 的所有元素");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return element;
            }
        }
    }

    public WebElement findElementByTagName(String tagName , int index){
        List<WebElement> element=null;
        if (tagName.equals(" ")||tagName==null){
            logger.error("tagName为空，找不到元素");
            return null;
        }else{
            element=driver.findElements(By.tagName(tagName));
            if (element==null){
                logger.info("找不到tagName为"+tagName+" 的元素");
                return null;
            }else{
                logger.info("查找到tagName为"+tagName+"的第"+index+1+"个的元素");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return element.get(index);
            }
        }
    }

    public WebElement findElementByXpath(String path){
        WebElement element=null;
        if (path==null||path.equals(" ")){
            logger.error("path为空，找不到元素");
            return null;
        }else{
            element=driver.findElement(By.xpath(path));
            if (element==null){
                logger.info("路径为"+path+"的元素在该页面不存在");
                return null;
            }else{
                logger.info("查找到路径为"+path+"的元素");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return element;
            }
        }
    }

    public void click(WebElement element){
        if (element==null){
            logger.info("元素为空，无法进行鼠标单击事件");
        }else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            element.click();
        }
    }

    public void  sendKeys(WebElement element, String value){
        if (element==null){
            logger.info("元素为空，无法进行输入事件");
        }else if (value==null){
            logger.info("要输入的内人value为空");
        }else {
            logger.info("输入"+value+"到元素");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            element.sendKeys(value);
        }
    }

    public  void submit(WebElement element){
        if (element==null){
            logger.info("元素element为空");
        }else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            element.submit();
        }
    }
    public  int getSize_height(WebElement element){
        int height=0;
        if (element==null){
            logger.info("元素element为空");
            return height;
        }else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            height=element.getSize().getHeight();
            logger.info("元素的高为："+height);
            return height;
        }

    }
    public  int getSize_width(WebElement element){
        int width=0;
        if (element==null){
            logger.info("元素element为空");
            return width;
        }else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            width= element.getSize().getWidth();
            logger.info("元素的宽为"+width);
            return width;
        }
    }
    public String getText(WebElement element){
        String text=null;
        if (element==null){
            logger.info("元素element为空");
            return null;
        }else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            text = element.getText();
            logger.info("获取的元素文本为：" + text);
            return text;
        }
    }
}
