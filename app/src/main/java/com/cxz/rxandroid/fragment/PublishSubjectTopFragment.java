package com.cxz.rxandroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.cxz.rxandroid.R;
import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.subjects.PublishSubject;

/**
 * Created by chenxz on 2017/3/20.
 */
public class PublishSubjectTopFragment extends RxFragment {

    @Bind(R.id.et_input)
    EditText et_input;

    private PublishSubject<String> publishSubject;

    public PublishSubjectTopFragment(){}

    public PublishSubjectTopFragment(PublishSubject<String> publishSubject) {
        this.publishSubject = publishSubject;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_publish_top,null);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick(R.id.btn_send)
    public void sendToBottom(){
        String result = et_input.getText().toString().trim();
        publishSubject.onNext(result);
    }

}
