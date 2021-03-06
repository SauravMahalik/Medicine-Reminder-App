package com.example.devan.medicine_reminder.datalayer;

import android.content.Context;

import com.example.devan.medicine_reminder.Models.Medicine;

import java.util.ArrayList;
import java.util.List;

public class MedicineDataLayer {

    private final MedicineDAO medicineDAOObject;

    public MedicineDataLayer() {
        medicineDAOObject = new MedicineDAO();
    }

    public ArrayList<Object[]> getCurrentMedicineList() {

        return medicineDAOObject.getUpcomingMedicineDetails();

    }

    public ArrayList<Object[]> getMissedMedicineList() {

        return medicineDAOObject.getMissedMedicineDetails();
    }

    public ArrayList<String> getAllMedicineDates(Context context){
        return medicineDAOObject.getMedicineDates(context);
    }

    public ArrayList<Med> getMedicineFromDateTime(String DateObj){
        return medicineDAOObject.getMedicineDataStubByDateTime(DateObj);
    }

    public ArrayList<Medicine> getDailyMedicineList(){
        return medicineDAOObject.getDailyMedicineListDataStub();
    }

    public List<Med> getAllMedicines(Context context){
        return medicineDAOObject.getAllMedicines(context);
    }

    public List<Med> getAllNonDailyMedicines(Context context){
        return AppDatabase.getInMemoryDatabase(context).medModel().loadMedByTag(0);
    }

    public List<Med> getAllDailyMedicines(Context context){
        return AppDatabase.getInMemoryDatabase(context).medModel().loadMedByTag(1);
    }
}
