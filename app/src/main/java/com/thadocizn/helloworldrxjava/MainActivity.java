package com.thadocizn.helloworldrxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private String greeting = "Hello from RxJava. We're using Composite Disposable";
    private Observable<String>myObservable;
    private DisposableObserver<String> myObserver;
    private TextView textView;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tvGeeting);

        myObservable = Observable.just(greeting);

        myObservable.subscribeOn(Schedulers.io()); //limitless thread pool
        myObservable.observeOn(AndroidSchedulers.mainThread()); //main thread
        /*myObservable.subscribeOn(Schedulers.newThread()); //creates a new thread
        myObservable.subscribeOn(Schedulers.single()); // has a single thread handling task right after another
        myObservable.subscribeOn(Schedulers.trampoline()); //fifo, recurring tasks
        myObservable.subscribeOn(Schedulers.from(Executor executor)); //creates and return custom scheduler
        */
        myObserver = new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {

                Log.i("Charles", "on next invoked");
                textView.setText(greeting);

            }

            @Override
            public void onError(Throwable e) {
                Log.i("Charles", "on error invoked");

            }

            @Override
            public void onComplete() {
                Log.i("Charles", "on complete invoked");

            }
        };
        compositeDisposable.add(myObserver);
        myObservable.subscribe(myObserver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //compositeDisposable.dispose();//cant add more disposables

        // if you want to add more disposables use clear method
        compositeDisposable.clear();

       // myObserver.dispose();
    }
}
