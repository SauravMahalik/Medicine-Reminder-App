package com.example.devan.medicine_reminder.businesslayer;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.devan.medicine_reminder.datalayer.AppDatabase;
import com.example.devan.medicine_reminder.datalayer.Med;
import com.example.devan.medicine_reminder.datalayer.User;

import java.util.ArrayList;
import java.util.List;

public class AddNewMedBusinessLayer {

    public static void AddMeds(@NonNull final AppDatabase db, int tagDaily, String medName,
                               String dosage, String imagePath, String startDate, String endDate, ArrayList<ArrayList<String>> arrTimeItem) throws Exception {
        AddMeds task = new
                AddMeds(db, tagDaily, medName, dosage, imagePath, startDate, endDate, arrTimeItem);
        task.execute();
    }


    private static boolean InsertMeds(@NonNull final AppDatabase db, int tagDaily, String medName,
                                      String dosage, String imagePath, String startDate,
                                      String endDate, ArrayList<ArrayList<String>> arrTimeItem) throws Exception {
        Med med = new Med();
        med.tagDaily = tagDaily;
        med.medName = medName;
        med.dosage = dosage;
        med.imagePath = imagePath;
        med.startDate = startDate;
        med.endDate = endDate;
        med.timeObject = arrTimeItem;
        db.medModel().insertMeds(med);
        return true;
    }

    public static List<Med> GetMedData(@NonNull final AppDatabase db) {
        List<Med> medList = db.medModel().loadAllMeds();
        return medList;
    }

    public static String GetEmailID(@NonNull final AppDatabase db) {
        List<User> userList = db.userModel().loadAllUsers();
        return userList.get(0).emailID;

    }

    private static class AddMeds extends AsyncTask<String, String, String> {

        private final AppDatabase mDb;
        private final int tagDaily;
        private final String medName, dosage, imagePath, startDate,endDate ;
        private final ArrayList<ArrayList<String>> arrTimeItem;
        AddMeds(AppDatabase db, int tagDaily, String medName,
                String dosage, String imagePath, String startDate, String endDate, ArrayList<ArrayList<String>> arrTimeItem) {
            mDb = db;
            this.tagDaily =tagDaily;
            this.medName= medName;
            this.dosage= dosage;
            this.imagePath= imagePath;
            this.startDate= startDate;
            this.endDate= endDate;
            this.arrTimeItem= arrTimeItem;
        }

        @Override
        protected String doInBackground(final String... params) {
            try {
                InsertMeds(mDb, tagDaily, medName, dosage, imagePath, startDate, endDate, arrTimeItem);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
