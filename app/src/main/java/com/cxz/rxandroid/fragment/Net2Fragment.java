package com.cxz.rxandroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cxz.rxandroid.R;
import com.cxz.rxandroid.model.DeleteModel;
import com.cxz.rxandroid.model.GetModel;
import com.cxz.rxandroid.model.PostModel;
import com.cxz.rxandroid.model.PutModel;
import com.cxz.rxandroid.protocol2.TestProtocol;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.TreeMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * RxJava+OkHttp+Gson
 * Created by chenxz on 2017/3/12.
 */
public class Net2Fragment extends RxFragment {

    @Bind(R.id.tv_result)
    TextView tv_result;
    @Bind(R.id.tv_msg)
    TextView tv_msg;

    private TestProtocol mTestProtocol;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_net,null);
        ButterKnife.bind(this,view);
        tv_msg.setText(R.string.des_demo_net2);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mTestProtocol = new TestProtocol();
    }

    @OnClick(R.id.btn_get)
    void click_get(){
        mTestProtocol.text_Get().compose(this.<GetModel>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<GetModel>() {
                    @Override
                    public void call(GetModel getModel) {
                        tv_result.setText("Get Result:\t\n" + getModel);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        tv_result.setText("Get Error:\t\n" + throwable.getMessage());
                    }
                });
    }

    //@OnClick(R.id.btn_post)
    void click_post(){
        TreeMap<String, Object> params = new TreeMap<>();
        //TODO
        //params.put();
        mTestProtocol.text_Post(params).compose(this.<PostModel>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<PostModel>() {
                    @Override
                    public void call(PostModel postModel) {
                        tv_result.setText("Post Result:\t\n" + postModel);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        tv_result.setText("Post Error:\t\n" + throwable.getMessage());
                    }
                });
    }

    //@OnClick(R.id.btn_put)
    void click_put(){
        TreeMap<String,Object> params = new TreeMap<>();
        //TODO
        //params.put();
        mTestProtocol.text_Put(params)
                .compose(this.<PutModel>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<PutModel>() {
                    @Override
                    public void call(PutModel putModel) {
                        tv_result.setText("Put Result:\t\n" + putModel);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        tv_result.setText("Put Error:\t\n" + throwable.getMessage());
                    }
                });
    }

    //@OnClick(R.id.btn_delete)
    void click_delete(){
        mTestProtocol.text_Delete()
                .compose(this.<DeleteModel>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<DeleteModel>() {
                    @Override
                    public void call(DeleteModel deleteModel) {
                        tv_result.setText("Delete Result:\t\n" + deleteModel);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        tv_result.setText("Delete Error:\t\n" + throwable.getMessage());
                    }
                });
    }

}
