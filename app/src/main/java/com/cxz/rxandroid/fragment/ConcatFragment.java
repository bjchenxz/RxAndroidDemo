package com.cxz.rxandroid.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cxz.rxandroid.R;
import com.cxz.rxandroid.model.Contacter;
import com.cxz.rxandroid.utils.DialogUtil;
import com.cxz.rxandroid.utils.XLog;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * concat合并操作符
 * 可以将多个Observables的输出合并，就好像它们是一个单个的Observable一样
 * 模拟先读取(1s)本地缓存数据，再读取(3s)网络数据
 * Created by chenxz on 2017/3/19.
 */
public class ConcatFragment extends RxFragment {

    @Bind(R.id.lv_list)
    ListView lv_list;

    ProgressDialog mDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_concat,null);
        ButterKnife.bind(this,view);
        mDialog = DialogUtil.getWaitDialog(getActivity(),"正在加载...");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        concat();
    }

    private void concat() {
        mDialog.show();
        Observable.concat(
                queryContactsFromLocation(),
                queryContactsFromNet()
        ).compose(this.<List<Contacter>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Contacter>>() {
                    @Override
                    public void call(List<Contacter> contacters) {
                        init(contacters);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        XLog.e(throwable.getMessage());
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        XLog.i("completed!!!");
                    }
                });
    }

    private void init(List<Contacter> contacters) {
        mDialog.dismiss();
        XLog.i(contacters.toString());
        lv_list.setAdapter(new ArrayAdapter<Contacter>(getActivity(),R.layout.item_list,R.id.tv_text,contacters));
    }

    /**
     * 模拟网络联系人列表
     * @return
     */
    private Observable<List<Contacter>> queryContactsFromNet(){
        return Observable.create(new Observable.OnSubscribe<List<Contacter>>() {
            @Override
            public void call(Subscriber<? super List<Contacter>> subscriber) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ArrayList<Contacter> contacters = new ArrayList<Contacter>();
                contacters.add(new Contacter("net:Lily"));
                contacters.add(new Contacter("net:Tom"));
                contacters.add(new Contacter("net:Mary"));
                subscriber.onNext(contacters);
                subscriber.onCompleted();
            }
        });
    }

    /**
     * 模拟查询手机本地联系人
     * @return
     */
    private Observable<List<Contacter>> queryContactsFromLocation(){
        return Observable.create(new Observable.OnSubscribe<List<Contacter>>() {
            @Override
            public void call(Subscriber<? super List<Contacter>> subscriber) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                ArrayList<Contacter> contacters = new ArrayList<Contacter>();
                contacters.add(new Contacter("location:张三"));
                contacters.add(new Contacter("location:李四"));
                contacters.add(new Contacter("location:王五"));
                subscriber.onNext(contacters);
                subscriber.onCompleted();
            }
        });
    }

}
