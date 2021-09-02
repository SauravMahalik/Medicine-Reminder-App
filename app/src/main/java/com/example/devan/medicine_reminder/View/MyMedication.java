package com.example.devan.medicine_reminder.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.MenuItem;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MyMedication extends AppCompatActivity {

    public Button userDetailsBtn, addMed;
    private Context mContext;
    private LinearLayout lLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_medication);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mContext = getApplicationContext();

        lLinearLayout = findViewById(R.id.medicationList);
        addMed = findViewById(R.id.addMed);
        addMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyMedication.this, AddNewMedicineActivity.class);
                startActivity(intent);
            }
        });

        try {
            populateMedication();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private void populateMedication() throws ParseException {

        MedicineBusinessLayer MedicineObj = new MedicineBusinessLayer();

        List<Med> MedicineList = MedicineObj.getAllMedicine(mContext);

        Collections.sort(MedicineList, new Comparator<Med>() {
            @Override
            public int compare(Med medicine1, Med medicine2) {
                return medicine1.medName.compareTo(medicine2.medName);

            }
        });

        if (MedicineList != null) {
            if (MedicineList.size() != 0) {
                showMedicineOnScreen(MedicineList, lLinearLayout);
            } else {
                showNoMedicationAvailable(lLinearLayout, "NO MEDICAITONS ADDED");
            }
        } else {
            showNoMedicationAvailable(lLinearLayout, "NO MEDICAITONS ADDED");
        }
    }

    private void showMedicineOnScreen(List<Med> MedicineArrayList, LinearLayout linearLayout) {
        for (int i = 0; i < MedicineArrayList.size(); i++) {

            CardView CardViewObj = new CardView(mContext);

            LinearLayout.LayoutParams ParamsObj = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getDPI(100));

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

            int ImageID = R.drawable.medicine_pill;

            ChildImageObj.setImageResource(ImageID);

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

            ChildTextView3.setText(MedicineArrayList.get(i).startDate.toString());

            ChildTextView3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);

            ChildTextView3.setTextColor(getColor(R.color.Color_Home_Card_Dosage));

            ChildLinearLayout.addView(ChildTextView3);

            Parent.addView(ChildLinearLayout);

            CardViewObj.addView(Parent);

            CardViewObj.requestLayout();

            linearLayout.addView(CardViewObj);
        }

    }

    private void showNoMedicationAvailable(LinearLayout linearLayout, String TextID){
        TextView ChildTextView2 = new TextView(mContext);

        ChildTextView2.setPadding(getDPI(5), getDPI(5), getDPI(0), getDPI(0));

        ChildTextView2.setText(TextID);

        ChildTextView2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);

        ChildTextView2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        ChildTextView2.setTextColor(getColor(R.color.Color_Home_Card_Dosage));

        linearLayout.addView(ChildTextView2);
    }

    public int getDPI(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
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
}
