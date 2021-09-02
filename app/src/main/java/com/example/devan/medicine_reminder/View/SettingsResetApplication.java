package com.example.devan.medicine_reminder.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.devan.medicine_reminder.R;
import com.example.devan.medicine_reminder.businesslayer.ResetApplicationLayer;
import com.example.devan.medicine_reminder.datalayer.AppDatabase;

public class SettingsResetApplication extends AppCompatActivity {
    Button resetButton;
    public AppDatabase appData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_application);
        resetButton=(Button) findViewById(R.id.resetButton);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appData = AppDatabase.getInMemoryDatabase(getApplicationContext());

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.print("Reset Button");

                ResetApplicationLayer.resetMedData(appData);
                ResetApplicationLayer.resetUserData(appData);

                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsResetApplication.this);
                builder.setCancelable(true);
                builder.setMessage("Data Reset Successful");
                builder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(getApplicationContext(),Home.class);
                                startActivity(intent);
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
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
