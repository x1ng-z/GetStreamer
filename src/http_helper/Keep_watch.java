package http_helper;


import org.apache.log4j.Logger;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Keep_watch implements Runnable {


    private static Logger log = Logger.getLogger(Keep_watch.class);
    private Long flush_timestamp = System.currentTimeMillis();
    private boolean first_time = false;
    private static String file_locate = System.getProperty("user.dir") + "/conf/start.bat";
    private Configuration_Bean configuration_bean;

    public Keep_watch() {

    }

    public void first() {

        // ExecuteCmd.execute(linkedBlockingQueue,cmd);

    }


    public Long getFlush_timestamp() {
        return flush_timestamp;
    }

    public void setFlush_timestamp(Long flush_timestamp) {
        this.flush_timestamp = flush_timestamp;
    }

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {
            log.info("Yes");

            if ((System.currentTimeMillis() - flush_timestamp) >configuration_bean.getCheck_span() && (first_time)) {

                try {
                    log.error("restart");
                    Runtime.getRuntime().exec(file_locate);
                } catch (Exception e) {
                    log.error(e);
                }

                setFirst_time(false);
            }

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                log.error(e);
            }

        }


    }

    public boolean isFirst_time() {
        return first_time;
    }

    public void setFirst_time(boolean first_time) {
        this.first_time = first_time;
    }

    public void setConfiguration_bean(Configuration_Bean configuration_bean) {
        this.configuration_bean = configuration_bean;
    }
}
