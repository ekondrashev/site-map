import java.util.Iterator;

public class BaseDecorator implements Links {

    protected Links links;
    protected String url;

    public Iterator iterator() {
        return links.iterator();
    }

    public BaseDecorator(String url) {
        this.url = url;
    }

    public BaseDecorator(Links links) {
        this.links = links;
    }


}
