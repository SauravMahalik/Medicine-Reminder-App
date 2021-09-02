
package com.example.devan.medicine_reminder.businesslayer;

import android.content.Context;

import com.example.devan.medicine_reminder.Models.Medicine;
import com.example.devan.medicine_reminder.Models.NonDailyMedicine;
import com.example.devan.medicine_reminder.datalayer.Med;
import com.example.devan.medicine_reminder.datalayer.MedicineDataLayer;
;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MedicineBusinessLayer {
    private MedicineDataLayer medicineDataObject = null;


    public MedicineBusinessLayer() {
        medicineDataObject = new MedicineDataLayer();
    }

    public ArrayList<Med> getUpcomingMedicineList(Context context) throws ParseException {

        ArrayList<Med> UpcomingMedicineList = new ArrayList<>();

        Map<Date,ArrayList<Med>> NonDailyMedicine = getNonDailyMedicineCalendarWise(context);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        ArrayList<String> MedicineName = new ArrayList<>();
        ArrayList<String> MedicineDosage = new ArrayList<>();
        for(Date DateObj : NonDailyMedicine.keySet()){
            Date CurrentDate = new Date();
            long diff = Math.round((DateObj.getTime() - CurrentDate.getTime()) / (double) 86400000);
            if(diff >= 0){

                if(diff <= 7){
                    for(Med MedObj : NonDailyMedicine.get(DateObj)){
                        if(MedicineName.contains(MedObj.medName) == false){
                            if(MedicineDosage.contains(MedObj.dosage) == false){
                                UpcomingMedicineList.add(MedObj);
                                MedicineName.add(MedObj.medName);
                                MedicineDosage.add(MedObj.dosage);
                            }
                        }
                    }
                }
            }
        }

        List<Med> DailyMedicineList = getDailyMedicineCalendarWise(context);

        for(Med MedObj: DailyMedicineList){
            Date CurrentDate = new Date();
            Date StartDate = simpleDateFormat.parse(MedObj.startDate);
            Date EndDate = simpleDateFormat.parse(MedObj.endDate);
            long diffStart = Math.round((StartDate.getTime() - CurrentDate.getTime()) / (double) 86400000);
            long diffEnd = Math.round((EndDate.getTime() - CurrentDate.getTime()) / (double) 86400000);

            if(diffStart < 7){
                if(diffEnd > 0){
                    UpcomingMedicineList.add(MedObj);
                }
            }
        }
        return UpcomingMedicineList;
    }

    public ArrayList<Medicine> getMissedMedicineList() throws ParseException {

        ArrayList<Medicine> medicineArrayList = new ArrayList<>();

        ArrayList<Object[]> medicineDAO = medicineDataObject.getMissedMedicineList();

        for (int i = 0; i < medicineDAO.size(); i++) {
            medicineArrayList.add(new Medicine(Integer.parseInt(medicineDAO.get(i)[0].toString()), medicineDAO.get(i)[1].toString(), medicineDAO.get(i)[2].toString(), medicineDAO.get(i)[4].toString()));
        }

        return medicineArrayList;
    }

    public List<Med> getDailyMedicineCalendarWise(Context context) throws ParseException {
        return medicineDataObject.getAllDailyMedicines(context);
    }

    public Map<Date, ArrayList<Med>> getNonDailyMedicineCalendarWise(Context context) throws ParseException {
        ArrayList<NonDailyMedicine> medicineArrayList = new ArrayList<>();

        List<Med> getAllMedicines = medicineDataObject.getAllNonDailyMedicines(context);

        Map<Date, ArrayList<Med>> OutputMap = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        for (Med medObj : getAllMedicines) {

            Date StartDate = simpleDateFormat.parse(medObj.startDate);
            Date EndDate = simpleDateFormat.parse(medObj.endDate);

            long diff = Math.round((EndDate.getTime() - StartDate.getTime()) / (double) 86400000);

            for (int days = 0; days <= diff; days++) {

                Calendar c = Calendar.getInstance();
                c.setTime(StartDate);
                c.add(Calendar.DATE, days);

                int DayToSelect = Math.abs(c.get(Calendar.DAY_OF_WEEK)) - 2;

                if (DayToSelect == -1) {
                    DayToSelect = 6; // it's sunday
                }

                if (medObj.timeObject.get(DayToSelect).size() > 0) {
                    if (OutputMap.get(c.getTime()) == null) {
                        ArrayList<Med> MedicinesToInsert = new ArrayList<>();
                        MedicinesToInsert.add(medObj);
                        OutputMap.put(c.getTime(), MedicinesToInsert);
                    } else {
                        ArrayList<Med> MedicinesToInsert = OutputMap.get(c.getTime());
                        MedicinesToInsert.add(medObj);
                        OutputMap.remove(c.getTime());
                        OutputMap.put(c.getTime(), MedicinesToInsert);
                    }
                }
            }
        }

        return new TreeMap<>(OutputMap);
    }

    public List<Med> getAllMedicine(Context context) {
        List<Med> medicines = medicineDataObject.getAllMedicines(context);

        return medicines;
    }

}
