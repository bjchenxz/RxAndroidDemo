package com.cxz.rxandroid.fragment;

import android.os.Bundle;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.cxz.rxandroid.R;
import com.cxz.rxandroid.utils.XLog;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * RxJava实现搜索关键字推荐
 * Created by chenxz on 2017/3/16.
 */
public class SearchTextChangeFragment extends RxFragment {

    @Bind(R.id.et_search)
    EditText et_search;

    @Bind(R.id.lv_list)
    ListView lv_list;

    @Bind(R.id.iv_x)
    ImageView iv_x;
    private ArrayAdapter<String> mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_text_change,null);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        searchKeyWord();
    }

    /**
     * 搜索关键字
     */
    private void searchKeyWord() {
        RxTextView.textChangeEvents(et_search)
                .debounce(300, TimeUnit.MILLISECONDS)  //debounce:每次文本更改后有300毫秒的缓冲时间，默认在computation调度器
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<TextViewTextChangeEvent, Boolean>() {
                    @Override
                    public Boolean call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        boolean filter = !TextUtils.isEmpty(textViewTextChangeEvent.text());
                        if(!filter && mAdapter != null){
                            //操作UI，这里必须在主线程完成
                            boolean b = Thread.currentThread() == Looper.getMainLooper().getThread();
                            XLog.i("清空::" + b);
                            mAdapter.clear();
                            mAdapter.notifyDataSetChanged();
                        }
                        if(filter){
                            iv_x.setVisibility(View.VISIBLE);
                        }else{
                            iv_x.setVisibility(View.GONE);
                        }
                        return filter;
                    }
                })
                .switchMap(new Func1<TextViewTextChangeEvent, Observable<List<String>>>() {
                    @Override
                    public Observable<List<String>> call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        return getKeyWordFromNet(textViewTextChangeEvent.text().toString().trim())
                                .subscribeOn(Schedulers.io());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread()) //触发后再次回到Android主线程调度器
                .subscribe(new Action1<List<String>>() {
                    @Override
                    public void call(List<String> strings) {
                        initPage(strings);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        XLog.i("error::"+throwable.getMessage());
                    }
                });
    }

    private void initPage(List<String> strings) {
        XLog.i("data::"+strings.toString());
        if(mAdapter == null){
            mAdapter = new ArrayAdapter<String>(getActivity(),R.layout.item_list,R.id.tv_text,strings);
            lv_list.setAdapter(mAdapter);
            lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(SearchTextChangeFragment.this.getActivity(), "搜索:" + mAdapter.getItem(position), Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            mAdapter.clear();
            mAdapter.addAll(strings);
            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 模拟从网络接口获取关键字的列表
     * @param key
     * @return
     */
    private Observable<List<String>> getKeyWordFromNet(final String key){
        return Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                boolean b = Thread.currentThread() == Looper.getMainLooper().getThread();
                XLog.i("IO线程::" + b);

                SystemClock.sleep(1000);
                //这里是网络请求操作。。。
                List<String> datas = new ArrayList<String>();
                for (int i = 0; i < 10; i++) {
                    datas.add("KeyWord:" + key + i);
                }
                subscriber.onNext(datas);
                subscriber.onCompleted();
            }
        });
    }

    @OnClick(R.id.iv_x)
    void iv_clear(){
        et_search.setText("");
    }

}
