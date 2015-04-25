import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class MultiSyncSample implements Sample {
    @Override
    public void exec(String url, String s) throws IOException {
        final Instant start = Instant.now();
        int n = Integer.parseInt(s);
        for (int i = 0; i < n; i++) {
            httpCall(url);
            System.out.println("[" + i + "] OK!");
        }
        System.out.println("TIME: " + Duration.between(start, Instant.now()).toMillis());
    }

    public Response httpCall(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        return client.newCall(request).execute();
    }

}
