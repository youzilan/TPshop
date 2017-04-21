package Interfaces;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by hy on 2017/4/20.
 */
public class cart {
    private static Logger logger=Logger.getLogger(cart.class);
    //config:properties文件名
    private static ResourceBundle rb=ResourceBundle.getBundle("TPshopConfig");
    //host.url：properties类型文件中的key值

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
}
