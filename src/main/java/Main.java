import java.io.IOException;
import java.net.URL;

/**
 * Created by pavel on 15/11/2017.
 */
public class Main {

    public static void main(String[] args) throws IOException {

        Links links = new LinksFromSinglePage(new URL("http://www.tutorialspoint.com/"));

        for (URL url : links) {
           System.out.println(url);
        }


    }
}
