package com.cxz.rxandroid.protocol;

import com.cxz.rxandroid.net.XHttpClient;

import java.util.Map;

import rx.Observable;

/**
 * 测试接口
 * Created by chenxz on 2017/3/9.
 */
public class TestProtocol extends BaseProtocol {

    private static final String BASE_URL = "http://news-at.zhihu.com/api/4/";

    /**
     * GET
     *
     * @return
     */
    public Observable<String> text_Get(){
        String path  = "news/latest";
        return createObservable(BASE_URL + path, XHttpClient.METHOD_GET,null);
    }

    /**
     * POST
     *
     * @param params
     * @return
     */
    public Observable<String> text_Post(Map<String,Object> params){
        return createObservable(BASE_URL,XHttpClient.METHOD_POST,params);
    }

    /**
     * PUT
     *
     * @param params
     * @return
     */
    public Observable<String> text_Put(Map<String,Object> params){
        return createObservable(BASE_URL,XHttpClient.METHOD_PUT,params);
    }

    /**
     * DELETE
     *
     * @return
     */
    public Observable<String> text_Delete(){
        String path = "";
        return createObservable(BASE_URL,XHttpClient.METHOD_DELETE,null);
    }

}
