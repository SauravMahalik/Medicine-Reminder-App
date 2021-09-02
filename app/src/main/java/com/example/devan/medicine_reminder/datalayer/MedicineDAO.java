package com.example.devan.medicine_reminder.datalayer;

import android.content.Context;


import com.example.devan.medicine_reminder.Models.Medicine;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MedicineDAO {

    public ArrayList<Object[]> getUpcomingMedicineDetails(){

        ArrayList<Object[]> arrayDAO = new ArrayList<>();

        Object[] dummyStringValue = new Object[5];
        dummyStringValue[0] = 0;
        dummyStringValue[1] = "Medicine001";
        dummyStringValue[2] = "D1";
        dummyStringValue[3] = "U1";
        dummyStringValue[4] =  Calendar.getInstance().getTime().toString();
        arrayDAO.add(dummyStringValue);

        dummyStringValue = new Object[5];
        dummyStringValue[0] = 0;
        dummyStringValue[1] = "Medicine002";
        dummyStringValue[2] = "D2";
        dummyStringValue[3] = "U2";
        dummyStringValue[4] =  Calendar.getInstance().getTime().toString();
        arrayDAO.add(dummyStringValue);

        dummyStringValue = new Object[5];
        dummyStringValue[0] = 0;
        dummyStringValue[1] = "Medicine002";
        dummyStringValue[2] = "D2";
        dummyStringValue[3] = "U2";
        dummyStringValue[4] =  Calendar.getInstance().getTime().toString();
        arrayDAO.add(dummyStringValue);

        dummyStringValue = new Object[5];
        dummyStringValue[0] = 0;
        dummyStringValue[1] = "Medicine002";
        dummyStringValue[2] = "D2";
        dummyStringValue[3] = "U2";
        dummyStringValue[4] =  Calendar.getInstance().getTime().toString();
        arrayDAO.add(dummyStringValue);

        dummyStringValue = new Object[5];
        dummyStringValue[0] = 0;
        dummyStringValue[1] = "Medicine002";
        dummyStringValue[2] = "D2";
        dummyStringValue[3] = "U2";
        dummyStringValue[4] =  Calendar.getInstance().getTime().toString();
        arrayDAO.add(dummyStringValue);

        return arrayDAO;
    }

    public ArrayList<Object[]> getMissedMedicineDetails(){

        ArrayList<Object[]> arrayDAO = new ArrayList<>();

        Object[] dummyStringValue = new Object[5];
        dummyStringValue[0] = 0;
        dummyStringValue[1] = "Medicine003";
        dummyStringValue[2] = "D1";
        dummyStringValue[3] = "U1";
        dummyStringValue[4] =  Calendar.getInstance().getTime().toString();
        arrayDAO.add(dummyStringValue);

        return arrayDAO;
    }

    public ArrayList<String> getMedicineDates(Context Context) {
        ArrayList<String> DateList = new ArrayList<>();


        List<Med> medicineList = AppDatabase.getInMemoryDatabase(Context).medModel().loadAllMeds();


        for(Med MedObj:medicineList){
            DateList.add(MedObj.startDate);
        }

        return DateList;
    }

    public List<Med> getAllMedicines(Context context){
        return AppDatabase.getInMemoryDatabase(context).medModel().loadAllMeds();
    }


    public ArrayList<Med> getMedicineDataStubByDateTime(String Date){

        ArrayList<Med> MedicineList = new ArrayList<>();

        List<Med> medicineList = new AppDatabase_Impl().medModel().loadMedByDate(Date);

        for(Med MedicineObj : medicineList){
            MedicineList.add(MedicineObj);
        }

        return  MedicineList;
    }

    public ArrayList<Medicine> getDailyMedicineListDataStub() {
        ArrayList<Medicine> MedicineList = new ArrayList<>();
        MedicineList.add(new Medicine(0,"TestDailyMedcine_2018_11_1_001","TestMedicineDosage_2018_11_1_001","0001"));
        MedicineList.add(new Medicine(0,"TestDailyMedcine_2018_11_1_002","TestMedicineDosage_2018_11_1_001","0001"));
        MedicineList.add(new Medicine(0,"TestDailyMedcine_2018_11_1_003","TestMedicineDosage_2018_11_1_001","0001"));

        return MedicineList;
    }
}
