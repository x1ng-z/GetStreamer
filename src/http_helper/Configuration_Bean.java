package http_helper;

public class Configuration_Bean {

    private   String Uploadurl;
    private   int Image_height;
    private   int Image_width;
    private   String Camera_ip;
    private   String Username;
    private   String Password;
    private   String Port;
    private int send_time_span;
    private Long check_span;


    public String getUploadurl() {
        return Uploadurl;
    }

    public void setUploadurl(String uploadurl) {
        Uploadurl = uploadurl;
    }

    public int getImage_height() {
        return Image_height;
    }

    public void setImage_height(int image_height) {
        Image_height = image_height;
    }

    public int getImage_width() {
        return Image_width;
    }

    public void setImage_width(int image_width) {
        Image_width = image_width;
    }

    public String getCamera_ip() {
        return Camera_ip;
    }

    public void setCamera_ip(String camera_ip) {
        Camera_ip = camera_ip;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPort() {
        return Port;
    }

    public void setPort(String port) {
        Port = port;
    }

    public int getSend_time_span() {
        return send_time_span;
    }

    public void setSend_time_span(int send_time_span) {
        this.send_time_span = send_time_span;
    }

    public Long getCheck_span() {
        return check_span;
    }

    public void setCheck_span(Long check_span) {
        this.check_span = check_span;
    }
}
