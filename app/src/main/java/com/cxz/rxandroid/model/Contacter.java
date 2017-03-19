package com.cxz.rxandroid.model;

/**
 * Created by chenxz on 2017/3/19.
 */
public class Contacter {

    private String name;

    public Contacter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Contacter{" +
                "name='" + name + '\'' +
                '}';
    }
}
