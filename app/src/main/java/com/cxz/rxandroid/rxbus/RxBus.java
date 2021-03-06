package com.cxz.rxandroid.rxbus;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by chenxz on 2017/3/8.
 */
public class RxBus {

    private static RxBus mRxBus = null;

    //private final PublishSubject<Object> _bus = PublishSubject.create();//线程不安全

    private final Subject<Object,Object> _bus = new SerializedSubject<>(PublishSubject.create());

    public static RxBus getInstance(){
        if(mRxBus == null){
            synchronized (RxBus.class){
                if(mRxBus == null){
                    mRxBus = new RxBus();
                }
            }
        }
        return mRxBus;
    }

    public void send(Object o){
        if(hasObservers())
            _bus.onNext(o);
    }

    /**
     * 获取实际的Bus对象
     * @return
     */
    public Observable<Object> toObservable(){
        return _bus;
    }

    /**
     * 是否含有观察者
     * @return
     */
    public boolean hasObservers(){
        return _bus.hasObservers();
    }

}
