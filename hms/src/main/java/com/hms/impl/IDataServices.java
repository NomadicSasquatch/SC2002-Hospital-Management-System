package com.hms.impl;

public interface IDataServices<Y, Z> {
    void add(Y data);
    void remove(Y data);
    Z view();
}
