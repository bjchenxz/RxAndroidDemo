package com.cxz.rxandroid.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.cxz.rxandroid.R;
import com.f2prateek.rx.preferences.Preference;
import com.f2prateek.rx.preferences.RxSharedPreferences;
import com.jakewharton.rxbinding.widget.RxCompoundButton;
import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * 随着CheckBox状态发生改变UI而改变
 * Created by chenxz on 2017/3/15.
 */
public class CheckBoxUpdateFragment extends RxFragment {

    @Bind(R.id.cb_1)
    AppCompatCheckBox checkBox1;

    @Bind(R.id.cb_2)
    AppCompatCheckBox checkBox2;

    @Bind(R.id.btn_login)
    Button btn_login;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checkbox_update,null);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        check_update1();
        check_update2();
    }

    /**
     * 同步UI
     */
    private void check_update2() {
        RxCompoundButton.checkedChanges(checkBox2)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        btn_login.setClickable(aBoolean);
                        btn_login.setBackgroundResource(aBoolean ? R.color.can_login : R.color.not_login);
                    }
                });
    }

    /**
     * 同步SharedPreferences
     */
    private void check_update1() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        RxSharedPreferences rxSp = RxSharedPreferences.create(sp);
        Preference<Boolean> preference = rxSp.getBoolean("preference",false);

        checkBox1.setChecked(preference.get());

        RxCompoundButton.checkedChanges(checkBox1)
                .subscribe(preference.asAction());

    }

    @OnClick(R.id.btn_login)
    void login(){
        Toast.makeText(getActivity(),R.string.login,Toast.LENGTH_LONG).show();
    }

}
