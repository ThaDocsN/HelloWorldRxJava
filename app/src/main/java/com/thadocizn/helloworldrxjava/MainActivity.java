package com.thadocizn.helloworldrxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    private String greeting = "Hello from RxJava";
    private Observable<String>myObservable;
    private Observer<String>myObserver;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tvGeeting);

        myObservable = Observable.just(greeting);
        myObserver = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i("Charles", "on subscribe invoked");
            }

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
        myObservable.subscribe(myObserver);
    }
}
