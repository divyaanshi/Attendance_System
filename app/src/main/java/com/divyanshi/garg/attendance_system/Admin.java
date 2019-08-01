package com.divyanshi.garg.attendance_system;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.divyanshi.garg.attendance_system.Model.about;

public class Admin extends AppCompatActivity {

    EditText adminName,adminPassword;
    Button submit;
    String name,pass; private NavigationView nv;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        dl = (DrawerLayout)findViewById(R.id.activity_admin);
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
                        Intent intent = new Intent(Admin.this,Admin.class);
                        startActivity(intent);
                        break;
                    case R.id.employee:
                        Intent intent1 = new Intent(Admin.this, EmployeeLogin.class);
                        startActivity(intent1);
                        break;
                    case R.id.student:
                        Intent intent2 = new Intent(Admin.this, MainActivity.class);
                        startActivity(intent2);break;

                    case R.id.aboutus:
                        Intent intent3 = new Intent(Admin.this, about.class);
                        startActivity(intent3);break;


                    default:
                        return true;
                }


                return true;

            }
        });



        adminName = (EditText)findViewById(R.id.adminName);
        adminPassword = (EditText)findViewById(R.id.adminPassword);
        adminPassword.setInputType(InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_VARIATION_PASSWORD);
        submit = (Button)findViewById(R.id.login);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = adminName.getText().toString();
                pass = adminPassword.getText().toString();

                if (name.equalsIgnoreCase("DY")&&pass.equals("agra"))
                {
                    Intent intent = new Intent(Admin.this,AdminAfterLogin.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),"Wrong Id or Password",Toast.LENGTH_LONG).show();
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

}
