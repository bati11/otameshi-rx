import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MultiAsyncHttpClient extends HttpClient {
    @Override
    public void exec(List<String> urls) throws IOException {
        for (final String url : urls) {
            CompletableFuture<Response>future = asyncHttpCall(url);
            future.thenAccept(response -> {
                System.out.println("[" + url + "] OK!");
            });
        }
    }

}
