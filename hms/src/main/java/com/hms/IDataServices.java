package com.hms;

import java.util.List;

public interface IDataServices<T> {
    void add(T data);
    void remove(T data);
    List<T> view(User user);
}
