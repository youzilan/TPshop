package InterfaceFrameWork;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by hy on 2017/4/23.
 */
public class Browser {
    WebDriver driver;

    public  Browser(int driverType) {
       // Runtime  runtime=new Runtime();

        setDriverType(driverType);
        maxBrowser();
    }

    public void maxBrowser(){
        driver.manage().window().maximize();
    }
    private  void setDriverType(int driverType) {

        switch (driverType) {
            case 1: {
                setUpFirefoxDriver();
                break;
            }
            case 2: {
                setUpChromeDriver();
                break;
            }

        }

    }

    private  void setUpFirefoxDriver() {
        //设置火狐的驱动路径
        System.setProperty("webdriver.gecko.driver", "F:\\hy\\Driver\\geckodriver.exe");
        //设置火狐的安装路径
        System.setProperty("webdriver.firefox.bin","F:\\Browser\\firefox.exe");
        driver = new FirefoxDriver();
    }

    private  void setUpChromeDriver() {
        //
        System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");
        driver = new ChromeDriver();
    }

    public static void main(String[] args) {
        Browser browser=new Browser(1);
    }

}