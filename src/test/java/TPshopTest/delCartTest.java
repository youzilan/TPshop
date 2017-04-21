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
public class delCartTest {
    private static Logger logger = Logger.getLogger(delCartTest.class);
    ReportUtils report = new ReportUtils();

    @DataProvider(name = "delcart")
    public Iterator<Object[]> addcart() throws IOException {
        return new excelIterator("F:\\TPshop\\TestData\\tpshop_case", "delcart");
    }

    @Test(dataProvider = "delcart")
    public void delcart(Map<String, String> map) {
        String response = Interfaces.delCart.deletecart(map);
        Assertion assertion = new Assertion();
        String errcode = null;
        String result=null;
        report.log(response);
        if (response != null) {
            if (response.contains("status")) {
                System.out.println( map.get("path_status"));
                result = Utils.DataUtil.JSONParse(response, map.get("path_status"));//(response, path_openid);
                assertion.assertEquals(result, map.get("预期结果"));
                report.highLight(result);
                logger.info(response);
            }else{
                System.out.println("删除购物车的商品出错");
            }
        } else {
            System.out.println("响应为空");
            logger.error("响应为空");
        }
    }

}
