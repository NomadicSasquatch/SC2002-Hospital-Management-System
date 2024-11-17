package com.hms.Manager;

import java.io.File;
import java.util.Arrays;

import com.hms.Users;
import com.hms.impl.IDataServices;
import com.utils.CSVFile;
import com.enumclass.UserRole;

public abstract class UserManager implements IDataServices<Users, CSVFile> {
    public static final String[] USER_HEADER = { "userid", "email", "name", "hashedpassword", "dob", "role", "gender" };

    public abstract void add(Users data);

    public abstract void remove(Users data);

    public abstract CSVFile view();

    public void addUser(CSVFile csvFile, Users data, String rootPath) {
        FileManager.addFolders(rootPath + "/" + data.getUserid());
        csvFile.add(Arrays.asList(data.getUserid(), data.getEmail(), data.getName(), data.gethashedPassword(),
                data.getDob().toString(), data.getRole().toString(), String.valueOf(data.getGender())));
        csvFile.updateCSVFile();
    }

    public void removeUser(CSVFile csvFile, Users data, String rootPath) {
        FileManager.removeFolders(new File(rootPath + "/" + data.getUserid()));
        csvFile.remove(data.getUserid());
        csvFile.updateCSVFile();
    }

    public abstract UserRole getRole();
}
