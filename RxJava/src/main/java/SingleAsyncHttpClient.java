import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class SingleAsyncHttpClient extends HttpClient {

    @Override
    public void exec(List<String> urls) throws IOException {
        final String url = urls.get(0);
        CompletableFuture<Response> future = asyncHttpCall(url);
        future.thenAccept(response -> {
            System.out.println("[" + url + "] OK!");
        });
    }
}
