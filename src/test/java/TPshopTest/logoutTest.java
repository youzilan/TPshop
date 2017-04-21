package TPshopTest;

import Utils.ReportUtils;
import Utils.excelIterator;
import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by hy on 2017/4/21.
 */
public class logoutTest {
    private static Logger logger = Logger.getLogger(logoutTest.class);
    ReportUtils report = new ReportUtils();

    @DataProvider(name = "logout")
    public Iterator<Object[]> logout() throws IOException {
        return new excelIterator("F:\\TPshop\\TestData\\tpshop_case", "退出登录");
    }

    @Test(dataProvider = "logout")
    public void logout(Map<String, String> map) {
        String response = Interfaces.logout.logout(map);
        report.log(response);
        System.out.println(response);
    }
}
