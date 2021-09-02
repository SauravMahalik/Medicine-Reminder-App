package com.example.devan.medicine_reminder.Models;

public class Medicine {
    private final int medicineImage;
    private final String medicineName;
    private final String medicineDosage;
    private final String dateTimeRegistered;

    public Medicine(int medicineImage, String medicineName, String medicineDosage, String dateTimeRegistered){
        this.medicineImage = medicineImage;
        this.medicineName = medicineName;
        this.medicineDosage = medicineDosage;
        this.dateTimeRegistered = dateTimeRegistered;
    }

    public int getMedicineImage(){
        return this.medicineImage;
    }

    public String getMedicineName(){
        return this.medicineName;
    }

    public String getMedicineDosage(){
        return this.medicineDosage;
    }

    public String getDateTimeRegistered(){
        return this.dateTimeRegistered;
    }
}
