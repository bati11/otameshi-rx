package subject;

import rx.subjects.BehaviorSubject;

public class DataBindingSample {

    public void start() {
        ViewModel viewModel = new ViewModel("default");

        View view1 = new View();
        System.out.println("######################");
        System.out.println("view1: " + view1.value);

        viewModel.bind(view1);

        System.out.println("######################");
        System.out.println("view1: " + view1.value);

        viewModel.set("hoge");

        System.out.println("######################");
        System.out.println("view1: " + view1.value);

        View view2 = new View();
        viewModel.bind(view2);
        System.out.println("######################");
        System.out.println("view1: " + view1.value);
        System.out.println("view2: " + view2.value);

        viewModel.set("fuga");

        System.out.println("######################");
        System.out.println("view1: " + view1.value);
        System.out.println("view2: " + view2.value);
    }

    private static class View {
        public String value;
    }

    private static class ViewModel {
        private BehaviorSubject<String> behaviorSubject;
        private String s;

        public ViewModel(String s) {
            this.s = s;
            this.behaviorSubject = BehaviorSubject.create(s);
        }

        public String get() {
            return s;
        }

        public void set(String s) {
            this.s = s;
            behaviorSubject.onNext(s);
        }

        public void bind(View view) {
            behaviorSubject.subscribe(s -> view.value = s);
        }
    }

}
