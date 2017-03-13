package com.cxz.rxandroid.model;

import java.util.List;

/**
 * Created by chenxz on 2017/3/13.
 */
public class PutModel {
    private int code;
    private String message;
    private XEntity entity;

    private class XEntity {
        private List<Data> data;

        private class Data {
            @Override
            public String toString() {
                return "Data{}";
            }
        }

        @Override
        public String toString() {
            return "XEntity{" +
                    "data=" + data +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "PutModel{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", entity=" + entity +
                '}';
    }
}
