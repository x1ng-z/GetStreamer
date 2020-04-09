package http_helper;

import org.apache.log4j.Logger;

import java.util.concurrent.LinkedBlockingQueue;

public class Buffer_Service {
    public static Logger logger = Logger.getLogger(Buffer_Service.class);
    private final static int max_count = 20;
    private final static LinkedBlockingQueue<http_helper.Carrier_Byte> queue = new LinkedBlockingQueue<http_helper.Carrier_Byte>(max_count);


    public static http_helper.Carrier_Byte getData() {

        try {
            http_helper.Carrier_Byte carrier_byte = queue.take();
            return carrier_byte;
        } catch (InterruptedException e) {
            logger.error(e);
        }

        return null;


    }

    public static LinkedBlockingQueue<http_helper.Carrier_Byte> getQueue() {
        return queue;
    }

    public static void inputData(http_helper.Carrier_Byte carrier_byte) {

        while (queue.size() >= max_count) {
            getQueue();
        }

        try {
            queue.put(carrier_byte);
        } catch (InterruptedException e) {
           logger.error(e);
        }


    }


}
