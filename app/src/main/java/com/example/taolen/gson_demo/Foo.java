package com.example.taolen.gson_demo;

/**
 * Created by taoLen on 4/10/2018.
 */

public class Foo<T> {
    private T object;

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
