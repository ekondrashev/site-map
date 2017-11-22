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
public class LinksFromSinglePage<T> implements Links<T> {

    List<T> links;
    String url;

    public LinksFromSinglePage(String url) {
        this.url = url;
    }

    private void prepareLinks() {
        if(links == null) {
            links  = new ArrayList<T>();
            Document doc;
            try {
                doc = Jsoup.connect(url).get();
                Elements elements = doc.body().getElementsByTag("a");

                for (Element element : elements) {
                    links.add((T) (element.attr("abs:href")));
                }

            } catch (IOException e) {
            }
        }
    }

    public Iterator<T> iterator() {
        prepareLinks();
        return links.iterator();
        // return new LinksIterator<T>(links.iterator());
    }

//    class LinksIterator<T> implements Iterator<T>    {
//
//        Iterator iterator;
//
//        public LinksIterator(Iterator iterator) {
//            this.iterator = iterator;
//        }
//
//        public boolean hasNext() {
//            return iterator.hasNext();
//        }
//
//        public T next() {
//            return (T)iterator.next();
//        }
//
//        public void remove() {
//            iterator.remove();
//        }
//    }
}
