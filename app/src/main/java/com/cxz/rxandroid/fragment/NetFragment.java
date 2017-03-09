package com.cxz.rxandroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cxz.rxandroid.R;
import com.cxz.rxandroid.protocol.TestProtocol;
import com.cxz.rxandroid.utils.XLog;
import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * RxJava网络请求
 * Created by chenxz on 2017/3/8.
 */
public class NetFragment extends RxFragment {

    @Bind(R.id.tv_result)
    TextView tv_result;

    private TestProtocol mTestProtocol;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_net,null);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mTestProtocol = new TestProtocol();
    }

    @OnClick(R.id.btn_get)
    void click_get(){
        mTestProtocol.text_Get()
                .compose(this.<String>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        XLog.d(data);
                        tv_result.setText("Get Result:\r\n" + data);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        tv_result.setText("Get Error\r\n" + throwable.getMessage());
                    }
                });
    }

}
