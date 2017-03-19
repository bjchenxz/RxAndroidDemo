package com.cxz.rxandroid.fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cxz.rxandroid.R;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * RxJava 定时器
 * Created by chenxz on 2017/3/19.
 */
public class TimerFragment extends RxFragment {

    @Bind(R.id.iv_welcome)
    ImageView iv_welcome;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer,null);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        startTimer();
    }

    private void startTimer() {
        Observable.timer(3000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .compose(this.<Long>bindToLifecycle())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        //Glide.with(TimerFragment.this.getActivity()).load("http://p2.zhimg.com/10/7b/107bb4894b46d75a892da6fa80ef504a.jpg").into(iv_welcome);
                        iv_welcome.setVisibility(View.VISIBLE);
                        ObjectAnimator
                                .ofFloat(iv_welcome,"alpha",0.0F,1.0F)
                                .setDuration(500)
                                .start();
                    }
                });

    }
}
