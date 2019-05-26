package com.thadocizn.helloworldrxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG  = "Charles";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        behaviorSubjectDemo2();
    }

    void behaviorSubjectDemo2() {


        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();

        behaviorSubject.subscribe(getFirstObserver());

        behaviorSubject.onNext("JAVA");
        behaviorSubject.onNext("KOTLIN");
        behaviorSubject.onNext("XML");

        behaviorSubject.subscribe(getSecondObserver());

        behaviorSubject.onNext("JSON");
        behaviorSubject.onComplete();

        behaviorSubject.subscribe(getThirdObserver());


    }

    void behaviorSubjectDemo1() {

        Observable<String> observable = Observable.just("JAVA", "KOTLIN", "XML", "JSON")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();

        observable.subscribe(behaviorSubject);

        behaviorSubject.subscribe(getFirstObserver());
        behaviorSubject.subscribe(getSecondObserver());
        behaviorSubject.subscribe(getThirdObserver());


    }

    private Observer<String > getFirstObserver(){
        Observer<String >observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, " First Observer onSubscribe ");

            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, " First Observer Received " + s);

            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, " First Observer onError ");

            }

            @Override
            public void onComplete() {
                Log.i(TAG, " First Observer onComplete ");

            }
        };

        return observer;
    }

    private Observer<String > getSecondObserver(){
        Observer<String >observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, " Second Observer onSubscribe ");

            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, " Second Observer Received " + s);

            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, " Second Observer onError ");

            }

            @Override
            public void onComplete() {
                Log.i(TAG, " Second Observer onComplete ");

            }
        };

        return observer;
    }

    private Observer<String > getThirdObserver(){
        Observer<String >observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, " Third Observer onSubscribe ");

            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, " Third Observer Received " + s);

            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, " Third Observer onError ");

            }

            @Override
            public void onComplete() {
                Log.i(TAG, " Third Observer onComplete ");

            }
        };

        return observer;
    }
}
