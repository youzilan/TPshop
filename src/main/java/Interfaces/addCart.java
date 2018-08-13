package Interfaces;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.testng.log4testng.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by hy on 2017/4/20.
 */
public class addCart {
    private static Logger logger=Logger.getLogger(addCart.class);
    //config:properties文件名
    private static ResourceBundle rb=ResourceBundle.getBundle("TPshopConfig");
    //host.url：properties类型文件中的key值

    public final static String HOST_URL_ADDCART=rb.getString("tpshop.host.addcart.url");
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

    public static String addcart(Map<String,String> map){
        String stringResponse = null;
        String uri=null;
        String cookies=null;
        if (map==null){
            logger.error("数据为空");
            return null;
        }else {
            if (addCart.cart(map)==null){
                logger.error("cart响应为空");
                return null;
            }else {
                uri = HOST_URL_ADDCART;
                cookies = map.get("cookies");
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("goods_spec[尺码]", map.get("参数goods_spec[尺码]")));
                params.add(new BasicNameValuePair("goods_num", map.get("参数goods_num")));
                params.add(new BasicNameValuePair("goods_id", map.get("参数goods_id")));
                params.add(new BasicNameValuePair("__hash__", map.get("参数__hash__")));
                stringResponse = InterfaceFrameWork.RequestUtils.post(uri, cookies, params);
                System.out.println(stringResponse);
                if (stringResponse == null) {
                    logger.error("商品加入购物车响应信息为空");
                    return null;
                }
            }
        }
        return stringResponse;
    }


}
