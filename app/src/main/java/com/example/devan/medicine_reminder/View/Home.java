package com.example.devan.medicine_reminder.View;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.devan.medicine_reminder.R;
import com.example.devan.medicine_reminder.businesslayer.MedicineBusinessLayer;
import com.example.devan.medicine_reminder.datalayer.Med;

import java.text.ParseException;
import java.util.ArrayList;

public class Home extends Hamburger {

    public Button userDetailsBtn, addMed;
    private Context mContext;
    private LinearLayout lLinearLayout;
    private LinearLayout mLinearLayout;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.home);
        super.onCreate(savedInstanceState);

        mContext = getApplicationContext();

        lLinearLayout = findViewById(R.id.upcomingMedicineList);
        addMed = findViewById(R.id.addMed);
        addMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, AddNewMedicineActivity.class);
                startActivity(intent);
            }
        });

        try {
            populateUpcomingMedicine();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        try {
            super.onWindowFocusChanged(hasFocus);
            populateUpcomingMedicine();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    private void showNoMedicationAvailable(LinearLayout linearLayout, int TextID) {
        linearLayout.removeAllViews();
        TextView ChildTextView2 = new TextView(mContext);

        ChildTextView2.setPadding(getDPI(5), getDPI(5), getDPI(0), getDPI(0));

        ChildTextView2.setText(TextID);

        ChildTextView2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);

        ChildTextView2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        ChildTextView2.setTextColor(getColor(R.color.Color_Home_Card_Dosage));

        linearLayout.addView(ChildTextView2);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void showMedicineOnScreen(ArrayList<Med> MedicineArrayList, LinearLayout linearLayout) {
        linearLayout.removeAllViews();
        for (int i = 0; i < MedicineArrayList.size(); i++) {

            CardView CardViewObj = new CardView(mContext);

            int HeightToSet = 100;

            if (MedicineArrayList.get(i).tagDaily == 0) {
                for (int date = 0; date < MedicineArrayList.get(i).timeObject.size(); date++) {
                    if (MedicineArrayList.get(i).timeObject.get(date).size() > 0) {
                        HeightToSet += 20;
                    }
                }
            }

            LinearLayout.LayoutParams ParamsObj = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getDPI(HeightToSet));

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

            if(MedicineArrayList.get(i).imagePath != null){
                ChildImageObj.setImageURI(Uri.parse(MedicineArrayList.get(i).imagePath));
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

            ChildTextView1.setText(MedicineArrayList.get(i).medName);

            ChildTextView1.setBackgroundColor(getColor(R.color.white));

            ChildTextView1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);

            ChildTextView1.setTextColor(getColor(R.color.black));

            ChildLinearLayout.addView(ChildTextView1);

            TextView ChildTextView2 = new TextView(mContext);

            ChildTextView2.setPadding(getDPI(5), getDPI(5), getDPI(0), getDPI(0));

            ChildTextView2.setText(MedicineArrayList.get(i).dosage);

            ChildTextView2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);

            ChildTextView2.setTextColor(getColor(R.color.Color_Home_Card_Dosage));

            ChildLinearLayout.addView(ChildTextView2);

            TextView ChildTextView3 = new TextView(mContext);

            ChildTextView3.setPadding(getDPI(5), getDPI(5), getDPI(0), getDPI(0));

            if (MedicineArrayList.get(i).tagDaily == 1) {

                String text = "Daily ";
                for (String Time : MedicineArrayList.get(i).timeObject.get(0)) {
                    text += Time + " ";
                }
                ChildTextView3.setText(text.trim());
            } else {
                String time = "";
                for (int date = 0; date < MedicineArrayList.get(i).timeObject.size(); date++) {
                    if (MedicineArrayList.get(i).timeObject.get(date).size() > 0) {
                        time += GetdayOfMonth(date) + " ";
                        for (String Time : MedicineArrayList.get(i).timeObject.get(date)) {
                            time += Time + " ";
                        }
                        time += "\n";
                    }
                }
                ChildTextView3.setText(time.trim().toString());
            }

            ChildTextView3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);

            ChildTextView3.setTextColor(getColor(R.color.Color_Home_Card_Dosage));

            ChildLinearLayout.addView(ChildTextView3);

            Parent.addView(ChildLinearLayout);

            CardViewObj.addView(Parent);

            CardViewObj.requestLayout();

            linearLayout.addView(CardViewObj);
        }
    }

    private String GetdayOfMonth(int idx) {
        switch (idx) {
            case 0:
                return "Monday";
            case 1:
                return "Tuesday";
            case 2:
                return "Wednesday";
            case 3:
                return "Thursday";
            case 4:
                return "Friday";
            case 5:
                return "Saturday";
            case 6:
                return "Sunday";
        }
        return "Monday";
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void populateUpcomingMedicine() throws ParseException {

        MedicineBusinessLayer MedicineObj = new MedicineBusinessLayer();

        ArrayList<Med> MedicineArrayList = MedicineObj.getUpcomingMedicineList(getApplicationContext());

        if (MedicineArrayList != null) {
            if (MedicineArrayList.size() != 0) {
                showMedicineOnScreen(MedicineArrayList, lLinearLayout);
            } else {
                showNoMedicationAvailable(lLinearLayout, R.string.NoUpcomingMedicine);
            }
        } else {
            showNoMedicationAvailable(lLinearLayout, R.string.NoUpcomingMedicine);
        }
    }

    public int getDPI(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}