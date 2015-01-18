import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kovtunov on 17/12/14.
 */
public class PageSpeedGoogle {

    String url = "https://developers.google.com/speed/pagespeed/insights/?url=";
    String url2 = "http://gtmetrix.com/analyze.html/";
    static List<String> speedUrls = new ArrayList<String>();


    void replaceSubString() throws FileNotFoundException {

        PrintWriter fileWriter = new PrintWriter("Links.txt"); // наследовать

        for (String i : XmlReader.urls) {
            speedUrls.add(i.replace(i, url + i));
        }
        System.out.println("[REPLACED]");

        for (String i : speedUrls) {
            fileWriter.println(i);
        }
        fileWriter.close();
        System.out.println("[DONE]");



    }
}
