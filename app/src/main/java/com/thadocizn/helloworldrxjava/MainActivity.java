package com.thadocizn.helloworldrxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG  = "Charles";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        publishSubjectDemo2();
    }

    void publishSubjectDemo1() {

        Observable<String> observable = Observable.just("JAVA", "KOTLIN", "XML", "JSON")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        PublishSubject<String> publishSubject = PublishSubject.create();

        observable.subscribe(publishSubject);

        publishSubject.subscribe(getFirstObserver());
        publishSubject.subscribe(getSecondObserver());
        publishSubject.subscribe(getThirdObserver());


    }

    void publishSubjectDemo2() {


        PublishSubject<String> publishSubject = PublishSubject.create();

        publishSubject.subscribe(getFirstObserver());

        publishSubject.onNext("JAVA");
        publishSubject.onNext("KOTLIN");
        publishSubject.onNext("XML");

        publishSubject.subscribe(getSecondObserver());

        publishSubject.onNext("JSON");
        publishSubject.onComplete();

        publishSubject.subscribe(getThirdObserver());


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
