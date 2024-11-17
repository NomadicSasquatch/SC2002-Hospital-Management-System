package com.hms.impl;

public interface IDataServices<Y, Z> {
    void add(Z repo, Y data);
    void remove(Z repo, Y data);
    Z view();
}
