package http_helper;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.File;

public class Http_tools {
    public static Logger logger = Logger.getLogger(Http_tools.class);


    public static void upload_img(String url, byte[] wait_to_sent) throws Exception {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {

            HttpPost httppost = new HttpPost(url);//"http://172.16.22.107:8079/vedio"

            ByteArrayBody bin = new ByteArrayBody(wait_to_sent, ContentType.IMAGE_JPEG, "break.jpg");
            StringBody comment = new StringBody("for test", ContentType.TEXT_PLAIN);

            HttpEntity reqEntity = MultipartEntityBuilder.create()
                    .addPart("image", bin)
                    .addPart("department", comment)
                    .addPart("method", new StringBody("upload", ContentType.TEXT_PLAIN))
                    .build();
            httppost.setEntity(reqEntity);

            logger.info("executing request " + httppost.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
//                    System.out.println("----------------------------------------");
//                    System.out.println(response.getStatusLine());
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    logger.info("Response content length: " + resEntity.getContentLength());
                }
                EntityUtils.consume(resEntity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }

}

