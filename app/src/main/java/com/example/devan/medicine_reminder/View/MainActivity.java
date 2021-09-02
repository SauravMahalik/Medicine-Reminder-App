package com.example.devan.medicine_reminder.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.devan.medicine_reminder.R;

public class MainActivity extends AppCompatActivity {
    private TextView txtView;
    public Button userDetailsBtn,editUserDetailsBtn,calenderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtView= findViewById(R.id.txtView);

        userDetailsBtn=findViewById(R.id.userDetailsBtn);

        editUserDetailsBtn=findViewById(R.id.editUserDetailsBtn);

        userDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,UserDetails.class);
                startActivity(intent);
            }
        });

    }

}

