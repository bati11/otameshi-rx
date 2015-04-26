import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

public class SingleSyncSample implements Sample {

    @Override
    public void exec(List<String> urls) throws IOException {
        final String url = urls.get(0);
        httpCall(url);
        System.out.println("[" + url + "] OK!");
    }

    public Response httpCall(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        return client.newCall(request).execute();
    }
}
