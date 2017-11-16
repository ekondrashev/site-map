import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by pavel on 16/11/2017.
 */
public class LinksFromSinglePage implements Links {


    List<URL> links = new ArrayList<URL>();

    public LinksFromSinglePage(URL url) {
        Document doc;
        try {
            doc = Jsoup.connect(url.toString()).get();
            Elements elements = doc.body().getElementsByTag("a");

            for (Element element : elements) {
                links.add(new URL(element.attr("abs:href")));
            }

        } catch (IOException e) {
        }
    }

    public Iterator iterator() {
        return links.iterator();
    }
}
