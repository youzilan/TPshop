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
 * Created by hy on 2017/4/20.
 */
public class addCartTest {
    private static Logger logger = Logger.getLogger(cartTest.class);
    ReportUtils report = new ReportUtils();

    @DataProvider(name = "addcart")
    public Iterator<Object[]> addcart() throws IOException {
        return new excelIterator("F:\\TPshop\\TestData\\tpshop_case", "加入购物车");
    }

    @Test(dataProvider = "addcart")
    public void addcart(Map<String, String> map) {
        String response = Interfaces.addCart.addcart(map);
        Assertion assertion = new Assertion();
        String errcode = null;
        String result=null;
        report.log(response);
        if (response != null) {
            if (response.contains("status")) {
                result = Utils.DataUtil.JSONParse(response, map.get("path_status"));//(response, path_openid);
                assertion.assertEquals(result, map.get("预期结果"));
                report.highLight(result);
                logger.info(response);
            }
        } else {
            System.out.println("响应为空");
            logger.error("响应为空");
        }
    }

}
