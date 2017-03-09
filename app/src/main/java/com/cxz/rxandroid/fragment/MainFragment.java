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
