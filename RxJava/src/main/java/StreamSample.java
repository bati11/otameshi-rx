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
import java.util.stream.IntStream;

public class StreamSample implements Sample {
    @Override
    public void exec(List<String> urls) throws IOException {
        final Instant start = Instant.now();
        Observable<Response> observable = Observable.empty();
        for (String url : urls) {
            observable = observable.concatWith(Observable.from(httpCall(url)));
        }
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .zipWith(Observable.range(1, 20), (r, i) -> new Pair<>(r, i))
                .map(pair -> "[" + pair._2 + "] OK! ")
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
