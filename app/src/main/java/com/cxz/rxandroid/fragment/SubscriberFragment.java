package com.cxz.rxandroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cxz.rxandroid.R;
import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 复用订阅者
 * Created by chenxz on 2017/3/20.
 */
public class SubscriberFragment extends RxFragment {

    private Observer mReuseSubscriber;

    @Bind(R.id.tv_result)
    TextView tv_result;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subscriber,null);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        //订阅者
        mReuseSubscriber = new Observer<Object>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                //TODO
                if (o.getClass() == Integer.class){
                    tv_result.setText("The data from btn1---->" + o);
                }else if (o.getClass() == String.class){
                    tv_result.setText("The data from btn2---->" + o);
                }
            }
        };
    }

    @OnClick(R.id.btn1)
    public void btn1(){
        Observable.just(1)
                .compose(this.<Integer>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mReuseSubscriber);
    }

    @OnClick(R.id.btn2)
    public void btn2(){
        Observable.just("btn2")
                .compose(this.<String>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mReuseSubscriber);
    }

}
