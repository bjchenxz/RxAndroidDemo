package com.cxz.rxandroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cxz.rxandroid.R;
import com.jakewharton.rxbinding.view.RxView;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Buffer操作符
 * Created by chenxz on 2017/3/16.
 */
public class BufferFragment extends RxFragment {

    @Bind(R.id.btn_buffer_count)
    Button btn_buffer_count;

    @Bind(R.id.btn_buffer_count_skip)
    Button btn_buffer_count_skip;

    @Bind(R.id.et_input)
    EditText et_input;

    @Bind(R.id.tv_output)
    TextView tv_output;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buffer,null);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bufferCount();

    }

    /**
     * 每次统计到点击次数为3次，发送一次请求
     */
    private void bufferCount() {
        RxView.clicks(btn_buffer_count)
                .buffer(3)
                .compose(this.<List<Void>>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Void>>() {
                    @Override
                    public void call(List<Void> voids) {
                        Toast.makeText(BufferFragment.this.getActivity(),R.string.des_demo_buffer_count,Toast.LENGTH_LONG).show();
                    }
                });
    }

    @OnClick(R.id.btn_buffer_count_skip)
    void buffer_count_skip(){
        tv_output.setText("");
        char[] chars = et_input.getText().toString().trim().toCharArray();
        Character[] chs = new Character[chars.length];
        for (int i = 0; i < chs.length; i++) {
            chs[i] = chars[i];
        }

        Observable.from(chs)
                .buffer(2,3)
                .compose(this.<List<Character>>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Character>>() {
                    @Override
                    public void call(List<Character> characters) {
                        tv_output.setText(tv_output.getText() + characters.toString());
                    }
                });
    }

}
