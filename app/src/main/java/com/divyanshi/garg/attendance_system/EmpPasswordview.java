package com.divyanshi.garg.attendance_system;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.divyanshi.garg.attendance_system.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EmpPasswordview extends AppCompatActivity {
  Button login;
  EditText pass;
  String receivedempid;

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_passwordview);
        pass=(EditText)findViewById(R.id.passwordEdittext);
        login=(Button)findViewById(R.id.passwordlogin);


        Bundle bundle =getIntent().getExtras();
        receivedempid = bundle.getString("sendData");





        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("Employees");


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog mDialog = new ProgressDialog(EmpPasswordview.this);
                mDialog.setMessage("Please Wait...");
                mDialog.show();


                mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        User user = dataSnapshot.child(receivedempid).getValue(User.class);
                        if (user.getPassword().equals(pass.getText().toString())){
                            Intent intent = new Intent(EmpPasswordview.this,Employee.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(EmpPasswordview.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });




            }
        });

    }
}
