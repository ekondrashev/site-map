import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DecoratorForString extends BaseDecorator{

    public DecoratorForString(Links links) {
        super(links);
    }
    private List<String> prepareLinks() {

        List<String> result  = new ArrayList<String>();

        for (Object link : links) {
            result.add(link.toString());
        }

        return result;
    }

    public Iterator<String> iterator() {
        List<String> links = prepareLinks();
        return links.iterator();

    }
}
