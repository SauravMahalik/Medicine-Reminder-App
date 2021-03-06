package com.example.devan.medicine_reminder.View;

public class TimeEntry implements Comparable<TimeEntry>{
    private int hourOfDay;
    private int minute;

    public TimeEntry(int hourOfDay, int minute) {
        this.hourOfDay = hourOfDay;
        this.minute = minute;
    }

    public int getHour() {return hourOfDay;}
    public int getMinute() {return minute;}

    @Override
    public int compareTo(TimeEntry timeEntry) {
        if(this.hourOfDay > timeEntry.hourOfDay) {
            return 1;
        }
        if(this.hourOfDay < timeEntry.hourOfDay) {
            return -1;
        }
        if(this.minute > timeEntry.minute) {
            return 1;
        }
        if(this.minute < timeEntry.minute) {
            return -1;
        }

        return 0;
    }
}
