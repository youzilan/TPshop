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
public class getHashTest {
    private static Logger logger = Logger.getLogger(getHashTest.class);
    ReportUtils report = new ReportUtils();

    @DataProvider(name = "gethash")
    public Iterator<Object[]> gethash() throws IOException {
        return new excelIterator("F:\\TPshop\\TestData\\tpshop_case", "获取hash");
    }

    @Test(dataProvider = "gethash")
    public void gethash(Map<String, String> map) {
        String response = Interfaces.getHash.gethash(map);
        Assertion assertion = new Assertion();
        String errcode = null;
        String result=null;
        report.log(response);
        System.out.println(response);
        if (response != null) {
            if (response.contains("购物车")) {
                result = Utils.DataUtil.htmlParse(response, map.get("path_title"));//(response, path_openid);
                assertion.assertEquals(result, map.get("预期结果"));
                report.highLight(result);
            }else{
                result=Utils.DataUtil.JSONParse(response, map.get("path_title"));
                report.highLight(result);
            }
        } else {
            System.out.println("响应为空");
        }
    }
}
