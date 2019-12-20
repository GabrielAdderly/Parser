import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Parser {

    private static final String URL = "https://www.udemy.com/course/learn-flutter-dart-to-build-ios-android-apps/";
    private static final String USER_AGENT = "Mozilla/5.0";

    private Elements parse(String select) throws IOException {
        Document doc = Jsoup.connect(URL)
                .userAgent(USER_AGENT)
                .timeout(5000)
                .get();
        return doc.getAllElements().select(select);
    }

    public void parseURL() throws IOException {
        //    String charset = "UTF-8";

        System.out.println("Sending request...");

        Elements elements = parse(".details");
        Elements elements2 = parse(".left-content");

        ArrayList<Titles> list = new ArrayList<>();

        for (int i = 0; i < elements.size(); i++) {
            String time = elements.get(i).select(".content-summary").text();
            String title = elements2.get(i).select(".title a").text();
            if ( !(time.isEmpty() || time == "" || title.isEmpty() || title == "") ){
                Titles titles = new Titles(time, title);
                list.add(titles);
            }

        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm:ss");

        Collections.sort(list, new Comparator<Titles>() {
            @Override
            public int compare(Titles object1, Titles object2) {
                return object1.getTime().compareTo(object2.getTime());
            }
        });


        for (int i = 0; i < list.size(); i++) {
            System.out.println("\n" + list.get(i).getTime());
            System.out.println(list.get(i).getTitle());
        }
    }


    public static void main(String[] args) throws IOException {
        Parser parser = new Parser();
        parser.parseURL();
    }
}
