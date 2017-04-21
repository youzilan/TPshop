package Interfaces;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by hy on 2017/4/20.
 */
public class delCart {
    private static Logger logger=Logger.getLogger(delCart.class);
    //config:properties文件名
    private static ResourceBundle rb=ResourceBundle.getBundle("TPshopConfig");
    //host.url：properties类型文件中的key值

    public final static String HOST_URL_DELETECART=rb.getString("tpshop.host.deletecart.url");
    public final static String HOST_URL_CART=rb.getString("tpshop.host.cart.url");

    public static String cart(Map<String,String> map){
        String stringResponse = null;
        String uri=null;
        String cookies=null;
        if (map==null){
            logger.error("数据为空");
            return null;
        }else {
            uri=HOST_URL_CART;
            cookies=map.get("cookies");
            stringResponse=InterfaceFrameWork.RequestUtils.get(uri,cookies);
            if (stringResponse==null){
                logger.error("进入购物车响应信息为空");
                return null;
            }
        }
        return stringResponse;
    }

    public static String deletecart(Map<String,String> map){
        String stringResponse = null;
        String uri=null;
        String cookies=null;
        if (map==null){
            logger.error("数据为空");
            return null;
        }else {
            if (delCart.cart(map)==null){
                logger.error("购物车响应为空");
                System.out.println("购物车响应为空");
                return null;
            }else {
                uri = HOST_URL_DELETECART;

                String cookies_404="iweb_captcha=fcf362a440df951386UQgFBwUCAAMFAwIJAgUGCFYCCgwCUwAHUgBVVFUHBwNZWgpYTA; iweb_admin_role_name=3b82959a9d521e4bc2VFZUUgUFA1YICQcHB1NTVFsCBwVRCF0MAlBSAAJSAArY1LLRiJXTm5PQo7LRpfw; iweb_admin_id=6a8d620eabeda17388UwlUBQFUAAhRAwcCA1paVgZfVl4HAgMFUQYDUg9VVlAG; iweb_admin_name=2e96e781c71338b3f6AAcIVQRRAVUJBFBSB1QMXAlVBgdeC1cCVQddBFECUFVQVQ9QDw; iweb_admin_pwd=2cdfab8016ced0baf0B1IIAQlUUQUDAwRaCAMHUQwBAAMABF1VDAVWAwkHB10BUgQHUAcCAQdfAwMAD1dSAFRQU1MIBVVfAAYCXl4HUg; Idea-e420ae2c=338866ea-58b7-41dc-9143-452ca0bbb0fd; PHPSESSID=f5bsj6bcc7dkgbr2ao4082hgj2; user_id=1; is_distribut=0; uname=ly; Hm_lvt_39dcd5bd05965dcfa70b1d2457c6dcae=1492659692,1492670551,1492691888,1492736633; Hm_lpvt_39dcd5bd05965dcfa70b1d2457c6dcae=1492759565; is_mobile=0; cn=2";
                String uri_404="http://192.168.1.117/index.php/Home/Tperror/tp404.html";
                InterfaceFrameWork.RequestUtils.get(uri_404,cookies);
                //cookies = map.get("cookies");
                cookies="iweb_captcha=fcf362a440df951386UQgFBwUCAAMFAwIJAgUGCFYCCgwCUwAHUgBVVFUHBwNZWgpYTA; iweb_admin_role_name=3b82959a9d521e4bc2VFZUUgUFA1YICQcHB1NTVFsCBwVRCF0MAlBSAAJSAArY1LLRiJXTm5PQo7LRpfw; iweb_admin_id=6a8d620eabeda17388UwlUBQFUAAhRAwcCA1paVgZfVl4HAgMFUQYDUg9VVlAG; iweb_admin_name=2e96e781c71338b3f6AAcIVQRRAVUJBFBSB1QMXAlVBgdeC1cCVQddBFECUFVQVQ9QDw; iweb_admin_pwd=2cdfab8016ced0baf0B1IIAQlUUQUDAwRaCAMHUQwBAAMABF1VDAVWAwkHB10BUgQHUAcCAQdfAwMAD1dSAFRQU1MIBVVfAAYCXl4HUg; Idea-e420ae2c=338866ea-58b7-41dc-9143-452ca0bbb0fd; PHPSESSID=f5bsj6bcc7dkgbr2ao4082hgj2; user_id=1; is_distribut=0; uname=ly; Hm_lvt_39dcd5bd05965dcfa70b1d2457c6dcae=1492659692,1492670551,1492691888,1492736633; Hm_lpvt_39dcd5bd05965dcfa70b1d2457c6dcae=1492759565; cn=2; is_mobile=0";
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("ids", "1684"));//map.get("参数ids")
                stringResponse = InterfaceFrameWork.RequestUtils.post(uri, cookies, params);
                System.out.println(stringResponse);
                if (stringResponse == null) {
                    logger.error("删除购物车商品响应信息为空");
                    return null;
                }
            }
        }
        return stringResponse;
    }
}
