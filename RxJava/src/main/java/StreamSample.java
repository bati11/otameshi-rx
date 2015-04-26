import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class StreamSample implements Sample {
    @Override
    public void exec(String url, String s) throws IOException {
        final Instant start = Instant.now();
        int n = Integer.parseInt(s);
        List<CompletableFuture<Response>> futures = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            futures.add(httpCall(url));
        }
        Observable.range(0, n)
                .subscribeOn(Schedulers.computation())
                .flatMap(i -> Observable.from(futures.get(i)).map(response -> "[" + i + "] OK! "))
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("TIME: " + Duration.between(start, Instant.now()).toMillis());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println(s);
                    }
                });
    }

    private CompletableFuture<Response> httpCall(String url) {
        CompletableFuture<Response> future = CompletableFuture.supplyAsync(() -> {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            try{
                return client.newCall(request).execute();
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        });
        return future;
    }
}
