package com.cxz.rxandroid.protocol;

import android.text.TextUtils;

import com.cxz.rxandroid.net.XHttpClient;
import com.squareup.okhttp.Request;

import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by chenxz on 2017/3/9.
 */
public abstract class BaseProtocol {

    /**
     * 创建一个工作在IO线程的被观察者（被订阅者）对象
     * @param url
     * @param method
     * @param params
     * @return
     */
    protected Observable<String> createObservable(final String url,final String method,final Map<String, Object> params){
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Request request = XHttpClient.getHttpClient().getRequest(url,method,params);
                String json = XHttpClient.getHttpClient().execute2String(request);
                setData(subscriber,json);
            }
        }).subscribeOn(Schedulers.io());
    }

    /**
     * 为观察者（订阅者）设置返回数据
     * @param subscriber
     * @param json
     */
    protected void setData(Subscriber<? super String> subscriber, String json) {
        if(TextUtils.isEmpty(json)){
            subscriber.onError(new Throwable("not data"));
            return;
        }
        subscriber.onNext(json);
        subscriber.onCompleted();
    }

}
