
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DecoratorForURL extends BaseDecorator{


    public DecoratorForURL(Links links) {
        super(links);
    }

    private List<URL> prepareLinks() {

        List<URL> result  = new ArrayList<URL>();

        for (String link : links) {
            try {
                result.add(new URL(link));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public Iterator<URL> iterator() {
        List<URL> links = prepareLinks();
        return links.iterator();

    }
}
