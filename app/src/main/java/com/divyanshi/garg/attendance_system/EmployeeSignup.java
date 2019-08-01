package com.divyanshi.garg.attendance_system;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.divyanshi.garg.attendance_system.Model.about;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EmployeeSignup extends AppCompatActivity {

    EditText empname,emppassword;
    Button signup;
    String receiveddata,DY_PASS;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    NavigationView nv;



    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_signup);

        dl = (DrawerLayout)findViewById(R.id.activity_sign_up);
        t = new ActionBarDrawerToggle(this, dl,R.string.open, R.string.close);
        dl.addDrawerListener(t);
        t.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        empname = (EditText)findViewById(R.id.empname);
        emppassword = (EditText)findViewById(R.id.emppassword);

        signup = (Button)findViewById(R.id.empsignup);


        Bundle bundle =getIntent().getExtras();
        receiveddata = bundle.getString("SendData");


        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.admin:
                        Intent intent = new Intent(EmployeeSignup.this,Admin.class);
                        startActivity(intent);
                        break;
                    case R.id.employee:
                        Intent intent1 = new Intent(EmployeeSignup.this, EmployeeLogin.class);
                        startActivity(intent1);
                        break;
                    case R.id.student:
                        Intent intent2 = new Intent(EmployeeSignup.this, MainActivity.class);
                        startActivity(intent2);break;

                    case R.id.aboutus:
                        Intent intent3 = new Intent(EmployeeSignup.this, about.class);
                        startActivity(intent3);break;

                    default:
                        return true;
                }


                return true;

            }
        });


        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("Employees");



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DY_PASS = emppassword.getText().toString();
                if (DY_PASS.equals("12345")){
                Toast.makeText(getApplicationContext(),"Please Enter Another Password",Toast.LENGTH_LONG).show();

            }else {
                    EmpAdd(receiveddata,emppassword.getText().toString(),empname.getText().toString());
                }
            }
        });
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    public void EmpAdd(String EmpID, String Password, String Name)
    {
        EmployeeAddition employeeAddition = new EmployeeAddition(EmpID,Password,Name);
        mFirebaseDatabase.child(receiveddata).setValue(employeeAddition);
        Toast.makeText(getApplicationContext(),"Employee Added",Toast.LENGTH_LONG).show();


    }
}
