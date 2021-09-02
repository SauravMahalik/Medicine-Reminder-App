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

public class DeleteUser extends AppCompatActivity {
    Button deleteUser;
    public AppDatabase appData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_user);
        deleteUser=(Button) findViewById(R.id.deleteUser);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appData = AppDatabase.getInMemoryDatabase(getApplicationContext());

        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ResetApplicationLayer.resetUserData(appData);

                AlertDialog.Builder builder = new AlertDialog.Builder(DeleteUser.this);
                builder.setCancelable(true);
                builder.setMessage("User Profile Deleted");
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
