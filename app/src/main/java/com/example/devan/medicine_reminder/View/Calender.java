package com.example.devan.medicine_reminder.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.devan.medicine_reminder.R;
import com.example.devan.medicine_reminder.businesslayer.MedicineBusinessLayer;
import com.example.devan.medicine_reminder.datalayer.Med;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class Calender extends AppCompatActivity {

    private Context mContext;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);


        mContext = getApplicationContext();
        scrollView = findViewById(R.id.calendarViewResult);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try {
            showCalendarView();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void showCalendarView() throws ParseException {

        LinearLayout lLayout = new LinearLayout(mContext);

        LinearLayout.LayoutParams ParamsObj = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getDPI(100));

        ParamsObj.weight = 1.0f;

        lLayout.setLayoutParams(ParamsObj);
        lLayout.setOrientation(LinearLayout.VERTICAL);

        MedicineBusinessLayer MedicineBusinessLayerObj = new MedicineBusinessLayer();
        List<Med> MedicineObj = MedicineBusinessLayerObj.getDailyMedicineCalendarWise(getApplicationContext());

        if(MedicineObj.size() > 0){


            TextView TextViewObj = new TextView(mContext);


            TextViewObj.setPadding(getDPI(5), getDPI(5), getDPI(0), getDPI(0));

            TextViewObj.setText(R.string.txtdailyMedicineActivity);


            TextViewObj.setBackgroundColor(getColor(R.color.white));


            TextViewObj.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);

            TextViewObj.setTextColor(getColor(R.color.black));

            lLayout.addView(TextViewObj);

            for (int i = 0; i < MedicineObj.size(); i++) {

                CardView CardViewObj = new CardView(mContext);

                ParamsObj = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getDPI(100));

                ParamsObj.weight = 1.0f;

                CardViewObj.setLayoutParams(ParamsObj);

                CardViewObj.setBackgroundColor(getColor(R.color.white));

                ViewGroup.MarginLayoutParams cardViewMarginParams = (ViewGroup.MarginLayoutParams) CardViewObj.getLayoutParams();
                cardViewMarginParams.setMargins(getDPI(5), getDPI(5), getDPI(5), getDPI(5));

                LinearLayout Parent = new LinearLayout(mContext);

                ParamsObj = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                ParamsObj.weight = 1.0f;

                Parent.setOrientation(LinearLayout.HORIZONTAL);

                Parent.setPadding(getDPI(5), getDPI(5), getDPI(5), getDPI(5));

                Parent.setLayoutParams(ParamsObj);

                ImageView ChildImageObj = new ImageView(mContext);

                ParamsObj = new LinearLayout.LayoutParams(getDPI(180), ViewGroup.LayoutParams.WRAP_CONTENT);

                ParamsObj.weight = 1.0f;

                ChildImageObj.setLayoutParams(ParamsObj);

                ChildImageObj.setPadding(getDPI(20), getDPI(20), getDPI(10), getDPI(20));

                if(MedicineObj.get(i).imagePath != null){
                    ChildImageObj.setImageURI(Uri.parse(MedicineObj.get(i).imagePath));
                }else{

                    int ImageID = R.drawable.medicine_pill;

                    ChildImageObj.setImageResource(ImageID);
                }

                Parent.addView(ChildImageObj);

                LinearLayout ChildLinearLayout = new LinearLayout(mContext);

                ParamsObj = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                ParamsObj.weight = 1.0f;

                ChildLinearLayout.setOrientation(ChildLinearLayout.VERTICAL);

                ChildLinearLayout.setLayoutParams(ParamsObj);

                ParamsObj = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                ParamsObj.weight = 1.0f;

                TextView ChildTextView1 = new TextView(mContext);

                ChildTextView1.setPadding(getDPI(5), getDPI(5), getDPI(0), getDPI(0));

                ChildTextView1.setText(MedicineObj.get(i).medName);

                ChildTextView1.setBackgroundColor(getColor(R.color.white));

                ChildTextView1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);

                ChildTextView1.setTextColor(getColor(R.color.black));

                ChildLinearLayout.addView(ChildTextView1);

                TextView ChildTextView2 = new TextView(mContext);

                ChildTextView2.setPadding(getDPI(5), getDPI(5), getDPI(0), getDPI(0));

                ChildTextView2.setText(MedicineObj.get(i).dosage);

                ChildTextView2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);

                ChildTextView2.setTextColor(getColor(R.color.Color_Home_Card_Dosage));

                ChildLinearLayout.addView(ChildTextView2);

                TextView ChildTextView3 = new TextView(mContext);

                ChildTextView3.setPadding(getDPI(5), getDPI(5), getDPI(0), getDPI(0));

                String time = "";
                for(String _time: MedicineObj.get(i).timeObject.get(0)){
                    time += _time + " ";
                }
                ChildTextView3.setText(time.trim());

                ChildTextView3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);

                ChildTextView3.setTextColor(getColor(R.color.Color_Home_Card_Dosage));

                ChildLinearLayout.addView(ChildTextView3);

                Parent.addView(ChildLinearLayout);

                CardViewObj.addView(Parent);

                CardViewObj.requestLayout();

                lLayout.addView(CardViewObj);

            }
        }

        Map<Date, ArrayList<Med>> NonDailyMedicines = MedicineBusinessLayerObj.getNonDailyMedicineCalendarWise(getApplicationContext());

        if(NonDailyMedicines.size() > 0){


            for (Date DateKeys: NonDailyMedicines.keySet()) {

                TextView TextViewObj = new TextView(mContext);

                TextViewObj.setPadding(getDPI(5), getDPI(5), getDPI(0), getDPI(0));

                TextViewObj.setText(new SimpleDateFormat("EEEE dd - MM - yyyy").format(DateKeys));

                TextViewObj.setBackgroundColor(getColor(R.color.white));

                TextViewObj.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);

                TextViewObj.setTextColor(getColor(R.color.black));

                lLayout.addView(TextViewObj);

                for(Med MedList:NonDailyMedicines.get(DateKeys)){

                    CardView CardViewObj = new CardView(mContext);

                    ParamsObj = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getDPI(100));

                    ParamsObj.weight = 1.0f;

                    CardViewObj.setLayoutParams(ParamsObj);

                    CardViewObj.setBackgroundColor(getColor(R.color.white));

                    ViewGroup.MarginLayoutParams cardViewMarginParams = (ViewGroup.MarginLayoutParams) CardViewObj.getLayoutParams();
                    cardViewMarginParams.setMargins(getDPI(5), getDPI(5), getDPI(5), getDPI(5));

                    LinearLayout Parent = new LinearLayout(mContext);

                    ParamsObj = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                    ParamsObj.weight = 1.0f;

                    Parent.setOrientation(LinearLayout.HORIZONTAL);

                    Parent.setPadding(getDPI(5), getDPI(5), getDPI(5), getDPI(5));

                    Parent.setLayoutParams(ParamsObj);

                    ImageView ChildImageObj = new ImageView(mContext);

                    ParamsObj = new LinearLayout.LayoutParams(getDPI(180), ViewGroup.LayoutParams.WRAP_CONTENT);

                    ParamsObj.weight = 1.0f;

                    ChildImageObj.setLayoutParams(ParamsObj);

                    ChildImageObj.setPadding(getDPI(20), getDPI(20), getDPI(10), getDPI(20));

                    if(MedList.imagePath != null){
                        Bitmap bm = BitmapFactory.decodeFile(MedList.imagePath);
                        ChildImageObj.setImageBitmap(bm);
                    }else{

                        int ImageID = R.drawable.medicine_pill;

                        ChildImageObj.setImageResource(ImageID);
                    }

                    Parent.addView(ChildImageObj);

                    LinearLayout ChildLinearLayout = new LinearLayout(mContext);

                    ParamsObj = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                    ParamsObj.weight = 1.0f;

                    ChildLinearLayout.setOrientation(ChildLinearLayout.VERTICAL);

                    ChildLinearLayout.setLayoutParams(ParamsObj);

                    ParamsObj = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                    ParamsObj.weight = 1.0f;

                    TextView ChildTextView1 = new TextView(mContext);

                    ChildTextView1.setPadding(getDPI(5), getDPI(5), getDPI(0), getDPI(0));

                    ChildTextView1.setText(MedList.medName);

                    ChildTextView1.setBackgroundColor(getColor(R.color.white));

                    ChildTextView1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);

                    ChildTextView1.setTextColor(getColor(R.color.black));

                    ChildLinearLayout.addView(ChildTextView1);

                    TextView ChildTextView2 = new TextView(mContext);

                    ChildTextView2.setPadding(getDPI(5), getDPI(5), getDPI(0), getDPI(0));

                    ChildTextView2.setText(MedList.dosage);

                    ChildTextView2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);

                    ChildTextView2.setTextColor(getColor(R.color.Color_Home_Card_Dosage));

                    ChildLinearLayout.addView(ChildTextView2);

                    TextView ChildTextView3 = new TextView(mContext);

                    ChildTextView3.setPadding(getDPI(5), getDPI(5), getDPI(0), getDPI(0));

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

                    Date StartDate = simpleDateFormat.parse(MedList.startDate);

                    Calendar StartTimeCalendar = Calendar.getInstance();
                    StartTimeCalendar.setTime(DateKeys);
                    int DayToSelect = Math.abs(StartTimeCalendar.get(Calendar.DAY_OF_WEEK)) - 2;

                    if(DayToSelect == -1){
                        DayToSelect = 6;
                    }

                    String Dates = "";
                    for(String DatesString : MedList.timeObject.get(Integer.parseInt(Long.toString(DayToSelect)))){
                        Dates += DatesString + " ";
                    }

                    ChildTextView3.setText(Dates.trim());

                    ChildTextView3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);

                    ChildTextView3.setTextColor(getColor(R.color.Color_Home_Card_Dosage));

                    ChildLinearLayout.addView(ChildTextView3);

                    Parent.addView(ChildLinearLayout);

                    CardViewObj.addView(Parent);

                    CardViewObj.requestLayout();

                    lLayout.addView(CardViewObj);
                }
            }
        }

        if(MedicineObj.size() == 0 && NonDailyMedicines.size() == 0){

            TextView TextViewObj = new TextView(mContext);

            TextViewObj.setPadding(getDPI(15), getDPI(15), getDPI(15), getDPI(15));

            TextViewObj.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            TextViewObj.setText("No medicine available");

            TextViewObj.setBackgroundColor(getColor(R.color.white));

            TextViewObj.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);

            TextViewObj.setTextColor(getColor(R.color.black));

            lLayout.addView(TextViewObj);
        }
        scrollView.addView(lLayout);

    }

    public int getDPI(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}