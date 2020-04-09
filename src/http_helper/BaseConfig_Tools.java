package http_helper;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class BaseConfig_Tools {
    public static Logger logger = Logger.getLogger(BaseConfig_Tools.class);
    private static Properties prop;
    private static String file_locate=System.getProperty("user.dir")+"/conf/base_inf.properties";
    private final static String Uploadurl="uploadrul";
    private final static String Image_height="image_height";
    private final static String Image_width="image_width";
    private final static String Camera_ip="camera_ip";
    private final static String Username="username";
    private final static String Password="password";
    private final static String Port="port";
    private final static String Send_span="send_span";
    private final static String Check_span="check_span";


//    private final static String Image_height="image_height";



    public static Configuration_Bean getConfiguration_information(){




            prop=new Properties();
            try {
                BufferedReader bufferedReader=new BufferedReader(new FileReader(file_locate));
                prop.load(bufferedReader);
            } catch (IOException e) {
                logger.error(e);
            }




        Configuration_Bean configuration_bean =new Configuration_Bean();

        configuration_bean.setUploadurl(prop.getProperty(Uploadurl));
        configuration_bean.setImage_height(Integer.parseInt(prop.getProperty(Image_height)==null?"1080":prop.getProperty(Image_height)));
        configuration_bean.setImage_width(Integer.parseInt(prop.getProperty(Image_width)==null?"1920":prop.getProperty(Image_width)));
        configuration_bean.setCamera_ip(prop.getProperty(Camera_ip));
        configuration_bean.setUsername(prop.getProperty(Username));
        configuration_bean.setPassword(prop.getProperty(Password));
        configuration_bean.setPort(prop.getProperty(Port));
        configuration_bean.setSend_time_span(Integer.parseInt(prop.getProperty(Send_span)==null?"1000":prop.getProperty(Send_span)));
        configuration_bean.setCheck_span(Long.parseLong(prop.getProperty(Check_span)));

        return configuration_bean;



    }



}
