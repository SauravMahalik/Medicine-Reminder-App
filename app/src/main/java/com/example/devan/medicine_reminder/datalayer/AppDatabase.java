package com.example.devan.medicine_reminder.datalayer;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.example.devan.medicine_reminder.businesslayer.Converter;

@Database(entities = {Med.class,User.class}, version = 1)
@TypeConverters(Converter.class)

public abstract class AppDatabase extends RoomDatabase {

    private static com.example.devan.medicine_reminder.datalayer.AppDatabase INSTANCE;

    public abstract MedDao medModel();
    public abstract UserDao userModel();


    public static com.example.devan.medicine_reminder.datalayer.AppDatabase getInMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,"Remedaily")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}