package ClientDemo;



import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

public class OrcTest {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }






//
//    void yv12toYUV(char[]outYuv, char[]inYv12, int width, int height,int widthStep)
//    {
//        int col,row;
//        /*unsigned*/ int Y,U,V;
//        int tmp;
//        int idx;
//        for (row=0; row<height; row++)
//        {
//            idx=row * widthStep;
//            int rowptr=row*width;  
//            for (col=0; col<width; col++)  
//            {  
//                tmp = (row/2)*(width/2)+(col/2);  
//                Y=(unsigned int) inYv12[row*width+col];  
//                U=(unsigned int) inYv12[width*height+width*height/4+tmp];  
//                V=(unsigned int) inYv12[width*height+tmp];  
//
//                outYuv[idx+col*3]   = Y;  
//                outYuv[idx+col*3+1] = U;  
//                outYuv[idx+col*3+2] = V;  
//            }  
//        }  
//    }



    public static void main(String[] args) {


        int height=1080;
        int width=1920;
        byte[] grayData=new byte[width*height*3/2];
        Mat yuvImg=null;

        yuvImg=new Mat(height*3/2, width, CvType.CV_8UC1);

        yuvImg.put(0,0,grayData);
        Mat rgbmat=new Mat(height,width,CvType.CV_8UC3);

        Imgproc.cvtColor(yuvImg, rgbmat, Imgproc.COLOR_YUV2BGR_YV12);


        MatOfByte mob = new MatOfByte();


        Imgcodecs.imencode("jpg", rgbmat, mob);
        // convert the "matrix of bytes" into a byte array
        byte[] byteArray = mob.toArray();
        BufferedImage bufImage = null;
        try {
            InputStream in = new ByteArrayInputStream(byteArray);
            bufImage = ImageIO.read(in);
        } catch (Exception e) {
            e.printStackTrace();
        }




//        Mat pImgYCrCb=null;
//        Mat pImg;
//        pImgYCrCb.create(1080,1920,CvType.CV_8UC3);
//        pImg.create(1080,1920,CvType.CV_8UC3);
//        yv12toYUV(pImgYCrCb.data, (uchar*)pBuf, pFrameInfo->nWidth,pFrameInfo->nHeight,pImgYCrCb.step);
//        cvtColor(pImgYCrCb, pImg, CV_YCrCb2RGB);//转化为RGB图像,pImg是输出的帧




//        Mat dst=new Mat(1080,1920,CvType.CV_8UC3);//这里nHeight为720,nWidth为1280,8UC3表示8bit uchar 无符号类型,3通道值
////        Mat rsource=new Mat(1080,1920,CvType.CV_8UC1,(uchar*)pBuf);
////        new MatOfByte();
//        Mat image = Imgcodecs.imread("E:\\2019_Project\\HikSDK\\1-Client\\Client-NetBeansPro\\VideoYV12253.yuv");	//读入图像
//
//        Imgproc.cvtColor(image,dst,Imgproc.COLOR_YUV2BGR_YV12);



        JFrame frame;
        frame = new JFrame();
        frame.setBounds(20, 100, 1920+100, 1080+80);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel label = new JLabel(""){
            @Override
            public void setLabelFor(Component c) {
                // TODO Auto-generated method stub
                super.setLabelFor(c);
            }
        };
        label.setBounds(0, 0,1920,1080);
        frame.getContentPane().add(label);
        label.setIcon(new ImageIcon(bufImage));








        System.out.println("Welcome to OpenCV " + Core.VERSION);
        Mat m = new Mat(5, 10, CvType.CV_8UC1, new Scalar(0));
        System.out.println("OpenCV Mat: " + m);
        Mat mr1 = m.row(1);
        mr1.setTo(new Scalar(1));
        Mat mc5 = m.col(5);
        mc5.setTo(new Scalar(5));
        System.out.println("OpenCV Mat data:\n" + m.dump());
    }


   static public class MatToBufImg {
        Mat matrix;
        MatOfByte mob;
        String fileExten;

        // The file extension string should be ".jpg", ".png", etc
        public MatToBufImg(Mat amatrix, String fileExtension) {
            matrix = amatrix;
            fileExten = fileExtension;
            mob = new MatOfByte();
        }

        public BufferedImage getImage() {
            // convert the matrix into a matrix of bytes appropriate for
            // this file extension
            Imgcodecs.imencode(fileExten, matrix, mob);
            // convert the "matrix of bytes" into a byte array
            byte[] byteArray = mob.toArray();
            BufferedImage bufImage = null;
            try {
                InputStream in = new ByteArrayInputStream(byteArray);
                bufImage = ImageIO.read(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bufImage;
        }
    }

}

