package com.sion.myfirstrepository;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.hello_text)
    TextView helloText;
    @BindView(R.id.hello_button)
    AppCompatButton helloButton;
    @BindView(R.id.paint_button)
    AppCompatButton mPaintButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Main");
        }

        helloButton.setOnClickListener(v -> MainActivity.this.handleClick());

        new Thread(() -> {
        });
    }

    private void handleClick() {
        ArrayList<Integer> intArray = new ArrayList<>(Arrays.asList(5, 9, 2, 7, 4, 6, 0, 3, 8, 1));

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError");
            }

            @Override
            public void onNext(String number) {
                Log.d(TAG, number);
            }
        };

        Observable.create((Observable.OnSubscribe<Integer>) subscriber1 -> {
            for (Integer i : intArray) subscriber1.onNext(i);
            subscriber1.onCompleted();
        })
                .map(integer -> integer >= 5 ? integer.toString() + " >= 5" : integer.toString() + " < 5")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    @OnClick(R.id.hello_button)
    public void onHelloButtonClick() {
    }

    @OnClick(R.id.paint_button)
    public void onPaintButtonClick() {
        Fragment fragment = new PaintFragment();
        String tag = fragment.getTag();
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(tag)
                .replace(android.R.id.content, fragment, tag)
                .commit();
    }

//    @OnClick({R.id.hello_button})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.hello_button:
//                startActivity(new Intent(MainActivity.this, HelloActivity.class));
//                break;
//        }
//    }
}
