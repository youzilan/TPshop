package Interfaces;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by hy on 2017/4/21.
 */
public class logout {
    private static Logger logger=Logger.getLogger(logout.class);
    //config:properties文件名
    private static ResourceBundle rb=ResourceBundle.getBundle("TPshopConfig");
    //host.url：properties类型文件中的key值

    public final static String HOST_URL_LOGOUT=rb.getString("tpshop.host.logout.url");

    public static String logout(Map<String,String> map){
        String stringResponse = null;
        String uri=null;
        String cookies=null;
        if (map==null){
            logger.error("数据为空");
            return null;
        }else {
            uri=HOST_URL_LOGOUT;
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
