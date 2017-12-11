import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {

//        Links<String> links = new StringLinks(args[0]);
       Links<String> links = new DecoratorForString(new DecoratorForURL(args[0]));


        for (String url : links) {
           System.out.println(url);
        }


    }
}
