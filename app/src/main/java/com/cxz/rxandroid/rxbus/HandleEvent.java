package com.cxz.rxandroid.rxbus;

/**
 * Created by chenxz on 2017/3/20.
 */
public class HandleEvent {

    private static HandleEvent handleEvent = null;

    public static HandleEvent getInstance(){
        if (handleEvent == null){
            synchronized (HandleEvent.class){
                if(handleEvent == null){
                    handleEvent = new HandleEvent();
                }
            }
        }
        return handleEvent;
    }
}
