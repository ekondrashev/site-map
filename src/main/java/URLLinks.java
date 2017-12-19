import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;



public class URLLinks implements Links{

    protected Links <String> links;
    Logger log = Logger.getLogger(Loggin.class.getName());

    public URLLinks(Links<String> links) {
        this.links = links;
    }

    private List<URL> miningLinks() {


        List<URL> result  = new ArrayList<URL>();

        for (String link : links) {
            try {
                URL tempURL = new URL(link);
                result.add(tempURL);
                log.info("Add new link: " + tempURL);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public Iterator<URL> iterator() {
        return miningLinks().iterator();

    }
}
