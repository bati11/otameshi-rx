package subject;

import rx.Observable;
import rx.subjects.PublishSubject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EventBusSample {
    private int observerCount = 0;
    public void start() {
        Observable<String> observable = fromSystemInEvent();

        PublishSubject<String> subject = PublishSubject.create();
        subject.subscribe(s -> {
            if (s.equalsIgnoreCase("add")) {
                observerCount = observerCount + 1;
                int observerId = observerCount;
                subject.subscribe(string -> System.out.println("[" + observerId + "] " + string));
            }
        });
        observable.subscribe(subject);
    }

    private Observable<String> fromSystemInEvent() {
        return Observable.create(subscriber -> {
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            try (BufferedReader in = new BufferedReader(inputStreamReader)) {
                while (true) {
                    if (subscriber.isUnsubscribed() == false) {
                        System.out.print("> ");
                        String s = new String(in.readLine());
                        subscriber.onNext(s);
                    } else {
                        break;
                    }
                }
            } catch (IOException e) {
                subscriber.onError(e);
            }
        });
    }
}
