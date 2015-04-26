import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class StreamHttpClient extends HttpClient {
    @Override
    public void exec(List<String> urls) throws IOException {
        final Instant start = Instant.now();
        Observable<Response> observable = Observable.empty();
        for (String url : urls) {
            observable = observable.concatWith(Observable.from(asyncHttpCall(url)));
        }
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .zipWith(Observable.from(urls), (r, url) -> new Pair<>(r, url))
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
}
