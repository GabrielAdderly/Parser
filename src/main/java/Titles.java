public class Titles {

    private String time;
    private String title;

    public Titles(){

    }

    public Titles(String time, String title){
        setTime(time);
        setTitle((title));
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
