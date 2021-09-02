package com.example.devan.medicine_reminder.businesslayer;

import com.example.devan.medicine_reminder.datalayer.AppDatabase;

public class ResetApplicationLayer {

    public static void resetUserData(final AppDatabase db)
    {
        db.userModel().deleteUser();

    }

    public static void resetMedData(final AppDatabase db)
    {

        db.medModel().deleteAll();

    }
}
