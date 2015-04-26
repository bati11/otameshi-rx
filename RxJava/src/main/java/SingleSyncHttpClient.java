import java.io.IOException;
import java.util.List;

public class SingleSyncHttpClient extends HttpClient {

    @Override
    public void exec(List<String> urls) throws IOException {
        final String url = urls.get(0);
        syncHttpCall(url);
        System.out.println("[" + url + "] OK!");
    }
}
