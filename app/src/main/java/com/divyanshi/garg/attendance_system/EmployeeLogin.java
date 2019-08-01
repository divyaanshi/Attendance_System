package com.divyanshi.garg.attendance_system;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.divyanshi.garg.attendance_system.Model.User;
import com.divyanshi.garg.attendance_system.Model.about;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EmployeeLogin extends AppCompatActivity {

    EditText employeeID;
    Button Login;
    private String EmpUserID;
    NavigationView nv;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    String empuser,emppassword,employeeIDString,employeePasswordString,sendEmpID;
    String TAG ="Divyanshi";
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_login);


        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("Employees");

        dl = (DrawerLayout)findViewById(R.id.activity_employee_login);
        t = new ActionBarDrawerToggle(this, dl,R.string.open, R.string.close);
        dl.addDrawerListener(t);
        t.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        employeeID = (EditText)findViewById(R.id.employeeId);
        Login = (Button)findViewById(R.id.loginButton);

        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.admin:
                        Intent intent = new Intent(EmployeeLogin.this,Admin.class);
                        startActivity(intent);
                        break;
                    case R.id.employee:
                        Intent intent1 = new Intent(EmployeeLogin.this, EmployeeLogin.class);
                        startActivity(intent1);
                        break;
                    case R.id.student:
                        Intent intent2 = new Intent(EmployeeLogin.this, MainActivity.class);
                        startActivity(intent2);break;

                    case R.id.aboutus:
                        Intent intent3 = new Intent(EmployeeLogin.this, about.class);
                        startActivity(intent3);break;

                    default:
                        return true;
                }


                return true;

            }
        });



        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendEmpID = employeeID.getText().toString();

                final ProgressDialog mDialog = new ProgressDialog(EmployeeLogin.this);
                mDialog.setMessage("Please Wait...");
                mDialog.show();

                mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if(dataSnapshot.child(employeeID.getText().toString()).exists())
                        {

                    mDialog.dismiss();
                    User user = dataSnapshot.child(employeeID.getText().toString()).getValue(User.class);
                    if (user.getPassword().equals("12345")){
                        Intent intent = new Intent(EmployeeLogin.this,EmployeeSignup.class);
                        intent.putExtra("SendData",sendEmpID);
                        startActivity(intent);
                    }else {

                        Intent intent = new Intent(EmployeeLogin.this,EmpPasswordview.class);
                        intent.putExtra("sendData",sendEmpID);
                        startActivity(intent);
                    }

                        }else {
                            mDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"User not found",Toast.LENGTH_LONG).show();
                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });






    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }







}
