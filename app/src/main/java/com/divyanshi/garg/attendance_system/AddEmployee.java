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

public class AddEmployee extends AppCompatActivity {

    EditText EmpID;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String EmpUserID;
    Button addEmployee;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        dl = (DrawerLayout)findViewById(R.id.activity_add_employee);
        t = new ActionBarDrawerToggle(this, dl,R.string.open, R.string.close);



        dl.addDrawerListener(t);
        t.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        EmpID = (EditText)findViewById(R.id.empId);
        addEmployee = (Button)findViewById(R.id.addButton);


        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.admin:
                        Intent intent = new Intent(AddEmployee.this,Admin.class);
                        startActivity(intent);
                        break;
                    case R.id.employee:
                        Intent intent1 = new Intent(AddEmployee.this, EmployeeLogin.class);
                        startActivity(intent1);
                        break;
                    case R.id.student:
                        Intent intent2 = new Intent(AddEmployee.this, MainActivity.class);
                        startActivity(intent2);break;

                    case R.id.aboutus:
                        Intent intent3 = new Intent(AddEmployee.this, about.class);
                        startActivity(intent3);break;

                    default:
                        return true;
                }


                return true;

            }
        });





        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("Employees");

        EmpUserID=mFirebaseDatabase.push().getKey();




        addEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EmpAdd(EmpID.getText().toString().trim(),"12345","");

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
        mFirebaseDatabase.child(EmpID).setValue(employeeAddition);
        Toast.makeText(getApplicationContext(),"Employee Added",Toast.LENGTH_LONG).show();


    }
}
