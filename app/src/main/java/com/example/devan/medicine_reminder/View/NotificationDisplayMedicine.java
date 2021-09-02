package com.example.devan.medicine_reminder.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.devan.medicine_reminder.R;

public class NotificationDisplayMedicine extends AppCompatActivity {

    TextView title;
    TextView medName;
    TextView medDosage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.display_current_medicine);
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        title=findViewById(R.id.dayTv);
        medName=findViewById(R.id.medicineNameTv);
        medDosage=findViewById(R.id.medicineDosageTv);

        title.setText("Medicine Details");
        medName.setText(extras.getString("name"));
        medDosage.setText(extras.getString("description"));
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