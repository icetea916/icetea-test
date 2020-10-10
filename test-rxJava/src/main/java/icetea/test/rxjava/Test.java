package icetea.test.rxjava;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test {

    public static void main(String[] args) {
        // 创建小说（被观察者）
        Observable<Object> novel = Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                emitter.onNext("连载1");
                emitter.onNext("连载2");
                emitter.onNext("连载3");
                emitter.onComplete();
            }
        });

        // 创建观察者
        Observer<String> reader = new Observer<String>() {
            private Disposable mDisposable;

            @Override
            public void onSubscribe(Disposable disposable) {
                mDisposable = disposable;
                log.info("on subscribe");
            }

            @Override
            public void onNext(String s) {
                if ("exit".equals(s)) {
                    mDisposable.dispose();
                    log.info("dispose success");
                    return;
                }
                log.info("on next : {}", s);
            }

            @Override
            public void onError(Throwable throwable) {
                log.info("on error", throwable);
            }

            @Override
            public void onComplete() {
                log.info("on complete");
            }
        };

        // 观察者和被观察着建立关系
        novel.subscribe();

    }
}
