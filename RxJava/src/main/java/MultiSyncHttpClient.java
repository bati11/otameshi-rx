import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class MultiSyncHttpClient extends HttpClient {
    @Override
    public void exec(List<String> urls) throws IOException {
        Instant start = Instant.now();
        for (String url : urls) {
            syncHttpCall(url);
            System.out.println("[" + url + "] OK!");
        }
        System.out.println("TIME: " + Duration.between(start, Instant.now()).toMillis());
    }
}
