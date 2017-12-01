import java.io.IOException;
import java.net.URL;


public class Main {

    public static void main(String[] args) throws IOException {

        Links<String> links = new LinksFromSinglePage<String>(args[0]);

        for (String url : links) {
           System.out.println(url);
        }


    }
}
