import org.apache.commons.validator.routines.UrlValidator;
import org.junit.Test;
import java.io.IOException;

/**
 * Created by pavel on 22/11/2017.
 */
public class TestLinks {

    @Test
    public void testLinksFromSinglePageClass()
    {

        try {
            Main.main(new String[]{"http://www.tutorialspoint.com/"});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testLinksFromSinglePageMethod()
    {
        Links<String> links = new LinksFromSinglePage<String>("http://www.tutorialspoint.com/");

        for (String url : links) {
            System.out.println(url);
            UrlValidator urlValidator = new UrlValidator();
            assert (urlValidator.isValid(url));
        }

    }


}
