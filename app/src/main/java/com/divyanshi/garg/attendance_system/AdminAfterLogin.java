package com.divyanshi.garg.attendance_system;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminAfterLogin extends AppCompatActivity {


    TextView addstudent,addemployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_after_login);



        addemployee = (TextView)findViewById(R.id.addemployee);
        addstudent = (TextView)findViewById(R.id.addstudent);

        addemployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminAfterLogin.this,AddEmployee.class);
                startActivity(intent);
            }
        });

        addstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminAfterLogin.this,AddStudent.class);
                startActivity(intent);
            }
        });



    }
}
