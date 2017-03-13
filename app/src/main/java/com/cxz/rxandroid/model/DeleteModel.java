package com.cxz.rxandroid.model;

/**
 * Created by chenxz on 2017/3/13.
 */
public class DeleteModel {
    private int code;
    private String message;
    private XEntity entity;

    private class XEntity {
        private Data data;

        private class Data {
            private int id;
            private String  name;
            private String value;
            private Links links;

            @Override
            public String toString() {
                return "Data{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", value='" + value + '\'' +
                        ", links=" + links +
                        '}';
            }

            private class Links {
                private String rel;
                private String href;

                @Override
                public String toString() {
                    return "Links{" +
                            "rel='" + rel + '\'' +
                            ", href='" + href + '\'' +
                            '}';
                }
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
        return "DeleteModel{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", entity=" + entity +
                '}';
    }
}
