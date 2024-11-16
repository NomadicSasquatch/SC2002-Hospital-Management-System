package com.hms.Manager;

import com.hms.Users;
import com.hms.impl.IDataServices;
import com.utils.CSVFile;

public abstract class UserManager implements IDataServices<Users, CSVFile>{
    public abstract void add(Users data);
    public abstract void remove(Users data);
    public abstract CSVFile view();
}
