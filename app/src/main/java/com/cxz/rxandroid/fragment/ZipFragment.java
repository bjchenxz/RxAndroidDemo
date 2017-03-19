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
import rx.functions.Action1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Zip数据合并操作
 * Created by chenxz on 2017/3/16.
 */
public class ZipFragment extends RxFragment {

    @Bind(R.id.lv_list)
    ListView lv_list;

    ProgressDialog mDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zip,null);
        ButterKnife.bind(this,view);
        mDialog = DialogUtil.getWaitDialog(getActivity(),"正在加载...");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDialog.show();
        getContactData();
    }

    /**
     *
     */
    private void getContactData() {
        Observable.zip(
                queryContactsFromLocation(),
                queryContactsFromNet(),
                new Func2<List<Contacter>, List<Contacter>, List<Contacter>>() {
                    @Override
                    public List<Contacter> call(List<Contacter> contacters, List<Contacter> contacters2) {
                        contacters.addAll(contacters2);
                        return contacters;
                    }
                }
        ).compose(this.<List<Contacter>>bindToLifecycle())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<List<Contacter>>() {
            @Override
            public void call(List<Contacter> contacters) {
                init(contacters);
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
