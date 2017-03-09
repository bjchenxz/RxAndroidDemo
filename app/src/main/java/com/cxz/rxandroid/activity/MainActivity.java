package com.cxz.rxandroid.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cxz.rxandroid.R;
import com.cxz.rxandroid.fragment.MainFragment;
import com.cxz.rxandroid.rxbus.RxBus;
import com.trello.rxlifecycle.components.support.RxFragmentActivity;

import rx.Observable;

public class MainActivity extends RxFragmentActivity {

    private RxBus _rxBus;

    public RxBus getRxBusSingleton(){
        if(_rxBus == null){
            _rxBus = new RxBus();
        }
        return _rxBus;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();

    }

    private void initFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_content,new MainFragment(),MainFragment.class.getName())
                .commit();
    }
}
