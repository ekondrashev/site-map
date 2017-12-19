import java.io.IOException;
import java.net.URL;


public class Main {

    public static void main(String[] args) throws IOException {

       Links<URL> links = new DefaultLinks(args[0]);

        for (URL url : links) {
           System.out.println(url);
        }


    }
}
