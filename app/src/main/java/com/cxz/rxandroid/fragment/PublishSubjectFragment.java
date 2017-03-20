package com.cxz.rxandroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cxz.rxandroid.R;
import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.ButterKnife;
import rx.subjects.PublishSubject;

/**
 * 可连接的Subject
 * 与普通的Subject不同，在订阅时并不立即触发订阅事件，
 * 而是允许我们在任意时刻手动调用onNext(),onError(),onCompleted来触发事件
 * Created by chenxz on 2017/3/20.
 */
public class PublishSubjectFragment extends RxFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_publish,null);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        PublishSubject<String> publishSubject = PublishSubject.create();
        PublishSubjectTopFragment topFragment = new PublishSubjectTopFragment(publishSubject);
        PublishSubjectBottomFragment bottomFragment = new PublishSubjectBottomFragment(publishSubject);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_top, topFragment,"top")
                .replace(R.id.fl_bottom,bottomFragment,"bottom")
                .commit();
    }
}
