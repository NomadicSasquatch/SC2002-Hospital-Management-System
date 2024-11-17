package com.hms.Manager;

import com.hms.impl.IDataRepository;
import com.hms.impl.IDataServices;
import com.utils.CSVFile;

public abstract class DataManager implements IDataServices<IDataRepository, CSVFile> {
    //for admin to use
    //public static final String[] USER_HEADER = { "userid", "email", "name", "hashedpassword", "dob", "role", "gender" };
    public static final String DATA_ROOT = "hms/src/main/java/com/data";

    public void add(CSVFile csvFile, IDataRepository data) {
        csvFile.add(data.getAttributes());
        // not sure if this is the proper usage of updateCSVFile
        csvFile.updateCSVFile();
    }

    public void remove(CSVFile csvFile, IDataRepository data) {
        csvFile.remove(data.getDataID());
        // not sure if this is the proper usage of updateCSVFile
        csvFile.updateCSVFile();
    }

    public abstract CSVFile view();
}
