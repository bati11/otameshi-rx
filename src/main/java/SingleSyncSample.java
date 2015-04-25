import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class SingleSyncSample implements Sample {

    @Override
    public void exec(String url, String s) throws IOException {
        httpCall(url);
        System.out.println("[0] OK!");
    }

    public Response httpCall(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        return client.newCall(request).execute();
    }
}
