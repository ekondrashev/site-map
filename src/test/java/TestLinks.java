import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pavel on 22/11/2017.
 */
public class TestLinks {

    List<URL> list;
    
    @Before
    public void createListLinks() {
        Links<URL> links = new DecoratorForURL (new DecoratorForString("http://www.tutorialspoint.com/"));
         list = new ArrayList<URL>();

        for (URL url : links) {
            list.add(url);
        }
    }

    @Test
    public void testPasitiveCheckLinks()
    {
        int count = 0;

        List<URL> testlist = new ArrayList<URL>();

        String url1 = "https://www.tutorialspoint.com/tutor_connect/index.php";
        String url2 = "https://www.tutorialspoint.com/tutor_connect/index.php";
        String url3 = "https://www.tutorialspoint.com/apache_poi_ppt/index.htm";
        String url4 = "https://www.tutorialspoint.com/design_pattern/index.htm";
        String url5 = "https://www.tutorialspoint.com/hibernate/index.htm";

        try {
            testlist.add(new URL(url1));
            testlist.add(new URL(url2));
            testlist.add(new URL(url3));
            testlist.add(new URL(url4));
            testlist.add(new URL(url5));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < testlist.size(); j++) {
                if (testlist.get(j).equals(list.get(i)))
                {
                    testlist.remove(j);
                }
            }
        }

        assert (count == 0);
      
    }

    @Test
    public void testPasitiveCheckMain() throws IOException {
        Main.main(new String[]{"http://www.tutorialspoint.com/"});
    }


}
