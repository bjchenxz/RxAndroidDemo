package com.cxz.rxandroid.rxbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cxz.rxandroid.R;
import com.cxz.rxandroid.utils.XLog;
import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * RxBus
 * Created by chenxz on 2017/3/20.
 */
public class RxBusFragment extends RxFragment {

    private CompositeSubscription mCompositeSubscription;

    @Bind(R.id.tv_rxbus)
    TextView tv_rxbus;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rxbus,null);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rxBusObservers();
    }

    @OnClick(R.id.btn_rxbus)
    public void rxbus_send(){
        RxBus.getInstance().send(HandleEvent.getInstance());
    }

    private void addSubscription(Subscription subscription){
        if (this.mCompositeSubscription == null){
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(subscription);
    }

    private void rxBusObservers(){
        Subscription subscription = RxBus.getInstance()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        if (o instanceof HandleEvent){
                            //TODO do something
                            XLog.i("RxBus--------HandleEvent-----");
                            tv_rxbus.setText("RxBus");
                        }
                    }
                });
        addSubscription(subscription);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        XLog.i("RxBus--Destroy");
        if (this.mCompositeSubscription != null){
            this.mCompositeSubscription.unsubscribe();//取消注册，以避免内存泄漏
        }
    }
}
