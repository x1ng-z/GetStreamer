package http_helper;

import org.apache.log4j.Logger;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public class Serivce4upload implements Runnable {
    public static Logger logger = Logger.getLogger(Serivce4upload.class);
    private int height=1080;
    private int width=1920;
    private String url;
    private Keep_watch keep_watch;

    public Serivce4upload(String url,int height, int width,Keep_watch keep_watch) {
        this.height = height;
        this.width = width;
        this.url=url;
        this.keep_watch=keep_watch;

    }


    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()){
            logger.info("queue size: "+Buffer_Service.getQueue().size());

            http_helper.Carrier_Byte carrier_byte =http_helper.Buffer_Service.getData();
            if(carrier_byte.equals(null)){
                continue;
            }

//          byte[] grayData=new byte[width*height*3/2];
            Mat yuvImg=null;

            yuvImg=new Mat(height*3/2, width, CvType.CV_8UC1);

            yuvImg.put(0,0,carrier_byte.getRealdata());
            Mat rgbmat=new Mat(height,width,CvType.CV_8UC3);
            Imgproc.cvtColor(yuvImg, rgbmat, Imgproc.COLOR_YUV2BGR_YV12);
            MatOfByte mob = new MatOfByte();
            Imgcodecs.imencode(".jpg", rgbmat, mob);
            // convert the "matrix of bytes" into a byte array
            byte[] byteArray = mob.toArray();
//            BufferedImage bufImage = null;
            try {
//                InputStream in = new ByteArrayInputStream(byteArray);
                http_helper.Http_tools.upload_img(url,byteArray);
                keep_watch.setFlush_timestamp(System.currentTimeMillis());
                keep_watch.setFirst_time(true);

//                            bufImage = ImageIO.read(in);
            } catch (Exception e) {
                logger.error(e);
            }

            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                logger.error(e);
            }


        }




    }
}
