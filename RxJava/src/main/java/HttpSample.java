import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HttpSample {
    public static void start() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        List<String> urls = new ArrayList<>();
        HttpClient client;
        while (true) {
            System.out.print("> ");
            String s = new String(in.readLine());
            if (s.equalsIgnoreCase("singlesync")) {
                client = new SingleSyncHttpClient();
                break;
            } else if (s.equalsIgnoreCase("singleasync")) {
                client = new SingleAsyncHttpClient();
                break;
            } else if (s.equalsIgnoreCase("multisync")) {
                client = new MultiSyncHttpClient();
                break;
            } else if (s.equalsIgnoreCase("multiasync")) {
                client = new MultiAsyncHttpClient();
                break;
            } else {
                urls.add(s);
            }
        }
        client.exec(urls);
        while (true) {
            String s = new String(in.readLine());
            if (s.equals("end")) {
                break;
            }
        }
        System.out.println("\nBye!");
    }
}
