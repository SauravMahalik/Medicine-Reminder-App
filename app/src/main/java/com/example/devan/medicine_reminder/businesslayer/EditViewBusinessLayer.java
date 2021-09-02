package com.example.devan.medicine_reminder.businesslayer;

import android.support.annotation.NonNull;

import com.example.devan.medicine_reminder.datalayer.AppDatabase;
import com.example.devan.medicine_reminder.datalayer.User;

import java.util.List;

public class EditViewBusinessLayer {

    public static User ShowEditUserInfo(AppDatabase appData) {
        List<User> Users = ShowUsers(appData);
        return Users.get(0);
    }
    private static List<User> ShowActiveUser(AppDatabase db) {
        List<User>  users = db.userModel().loadAllUsers();
        return users;
    }
    private static List<User>  ShowUsers(@NonNull final AppDatabase db) {
        return ShowActiveUser(db);
    }
}
