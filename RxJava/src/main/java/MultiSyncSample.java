import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class MultiSyncSample implements Sample {
    @Override
    public void exec(List<String> urls) throws IOException {
        Instant start = Instant.now();
        for (String url : urls) {
            httpCall(url);
            System.out.println("[" + url + "] OK!");
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
