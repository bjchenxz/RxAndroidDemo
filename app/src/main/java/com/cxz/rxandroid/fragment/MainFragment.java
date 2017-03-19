package com.cxz.rxandroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cxz.rxandroid.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 主菜单
 * Created by chenxz on 2017/3/8.
 */
public class MainFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,null);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick(R.id.btn_net)
    public void btn_net(){
        open(new NetFragment());
    }

    @OnClick(R.id.btn_net2)
    public void btn_net2(){
        open(new Net2Fragment());
    }

    @OnClick(R.id.btn_not_more)
    public void btn_not_more(){
        open(new NotMoreClickFragment());
    }

    @OnClick(R.id.btn_checkbox_state_update)
    public void btn_checkbox_state_update(){
        open(new CheckBoxUpdateFragment());
    }

    @OnClick(R.id.btn_text_change)
    public void btn_text_change(){
        open(new SearchTextChangeFragment());
    }

    @OnClick(R.id.btn_buffer)
    public void btn_buffer(){
        open(new BufferFragment());
    }

    @OnClick(R.id.btn_zip)
    public void btn_zip(){
        open(new ZipFragment());
    }

    @OnClick(R.id.btn_concat)
    public void btn_concat(){
        open(new ConcatFragment());
    }

    @OnClick(R.id.btn_loop)
    public void btn_loop(){
        open(new LoopFragment());
    }

    @OnClick(R.id.btn_timer)
    public void btn_timer(){
        open(new TimerFragment());
    }

    /**
     * 开启新的Fragment
     * @param fragment
     */
    public void open(Fragment fragment){
        final String tag = fragment.getClass().toString();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(tag)
                .replace(R.id.main_content,fragment,tag)
                .commit();
    }

}
