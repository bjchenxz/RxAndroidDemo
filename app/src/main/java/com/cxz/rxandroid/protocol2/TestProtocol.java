package com.cxz.rxandroid.protocol2;

import com.cxz.rxandroid.model.DeleteModel;
import com.cxz.rxandroid.model.GetModel;
import com.cxz.rxandroid.model.PostModel;
import com.cxz.rxandroid.model.PutModel;
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
    public Observable<GetModel> text_Get(){
        String path  = "news/latest";
        return createObservable(BASE_URL + path, XHttpClient.METHOD_GET,null, GetModel.class);
    }

    /**
     * POST
     *
     * @param params
     * @return
     */
    public Observable<PostModel> text_Post(Map<String,Object> params){
        return createObservable(BASE_URL, XHttpClient.METHOD_POST, params, PostModel.class);
    }

    /**
     * PUT
     *
     * @param params
     * @return
     */
    public Observable<PutModel> text_Put(Map<String,Object> params){
        return createObservable(BASE_URL, XHttpClient.METHOD_PUT, params, PutModel.class);
    }

    /**
     * DELETE
     *
     * @return
     */
    public Observable<DeleteModel> text_Delete(){
        String path = "";
        return createObservable(BASE_URL + path, XHttpClient.METHOD_DELETE, null, DeleteModel.class);
    }

}
