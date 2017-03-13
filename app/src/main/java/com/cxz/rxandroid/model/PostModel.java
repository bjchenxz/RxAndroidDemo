package com.cxz.rxandroid.model;

/**
 * Created by chenxz on 2017/3/13.
 */
public class PostModel {
    private int code;
    private String message;
    private XEntity entity;

    private class XEntity {
        private Data data;

        private class Data {
            private String xxx;

            @Override
            public String toString() {
                return "Data{" +
                        "xxx='" + xxx + '\'' +
                        '}';
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
        return "PostModel{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", entity=" + entity +
                '}';
    }
}
