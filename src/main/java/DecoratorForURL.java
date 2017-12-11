import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DecoratorForURL extends BaseDecorator{

    public DecoratorForURL(String url) {
        super(url);
    }

    private List<URL> prepareLinks() {

        List<URL> links  = new ArrayList<URL>();
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
            Elements elements = doc.body().getElementsByTag("a");

            for (Element element : elements) {
                String urlName =  element.attr("abs:href");
                if (!urlName.equals("")) {
                    links.add(new URL(urlName));
                }
            }

        } catch (IllegalStateException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }

        return links;
    }

    public Iterator<URL> iterator() {
        List<URL> links = prepareLinks();
        return links.iterator();

    }
}
