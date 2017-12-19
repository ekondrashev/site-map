import java.util.Iterator;

public class DefaultLinks implements Links{

    String url;

    public DefaultLinks(String url) {
        this.url = url;
    }

    public Iterator iterator() {
        return new URLLinks(new StringLinks(url)).iterator();
    }
}
