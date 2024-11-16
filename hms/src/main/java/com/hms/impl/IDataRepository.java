package com.hms.impl;

import java.util.List;

public interface IDataRepository {
    String getDataID();
    List<String> getAttributes();
    String getDataName();
}