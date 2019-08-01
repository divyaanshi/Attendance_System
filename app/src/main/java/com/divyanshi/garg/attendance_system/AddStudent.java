package com.divyanshi.garg.attendance_system;

import android.app.ProgressDialog;
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

public class AddStudent extends AppCompatActivity {
  EditText   sid,name,course,deptt,rollno,classes;
  Button add;
  String DYG;
  NavigationView nv;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        sid=(EditText)findViewById(R.id.studentid);
        name=(EditText)findViewById(R.id.name);
        course=(EditText)findViewById(R.id.course);
        deptt=(EditText)findViewById(R.id.department);
        rollno=(EditText)findViewById(R.id.rollnumber);
        classes=(EditText)findViewById(R.id.classstudent);
        add=(Button)findViewById(R.id.addButton);


        dl = (DrawerLayout)findViewById(R.id.activity_add_student);
        t = new ActionBarDrawerToggle(this, dl,R.string.open, R.string.close);



        dl.addDrawerListener(t);
        t.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.admin:
                        Intent intent = new Intent(AddStudent.this,Admin.class);
                        startActivity(intent);
                        break;
                    case R.id.employee:
                        Intent intent1 = new Intent(AddStudent.this, EmployeeLogin.class);
                        startActivity(intent1);
                        break;
                    case R.id.student:
                        Intent intent2 = new Intent(AddStudent.this, MainActivity.class);
                        startActivity(intent2);break;

                    case R.id.aboutus:
                        Intent intent3 = new Intent(AddStudent.this, about.class);
                        startActivity(intent3);break;

                    default:
                        return true;
                }


                return true;

            }
        });


        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("Students");


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DYG = sid.getText().toString();

                if (DYG!=""&&DYG.length()>=10&&DYG.startsWith("aec")){

                studentfunc(sid.getText().toString(),name.getText().toString(),course.getText().toString(),deptt.getText().toString(),rollno.getText().toString(),classes.getText().toString());

            }else
                {
                    Toast.makeText(getApplicationContext(),"Please Enter A Valid ID",Toast.LENGTH_LONG).show();
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




    public void studentfunc(String studentid, String name, String course, String department, String roll_Number, String batch)
    {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();
        StudentAddition studentAddition = new StudentAddition(studentid,name,course,department,roll_Number,batch);

        mFirebaseDatabase.child(studentid).setValue(studentAddition);

        progressDialog.dismiss();
        Toast.makeText(getApplicationContext(),"Student Added",Toast.LENGTH_LONG).show();
    }
}
