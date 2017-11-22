import org.junit.Test;

import java.io.IOException;

/**
 * Created by pavel on 22/11/2017.
 */
public class TestLinks {

    @Test
    public void testLinksFromSinglePage()
    {

        try {
            Main.main(new String[]{"http://www.tutorialspoint.com/"});
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
