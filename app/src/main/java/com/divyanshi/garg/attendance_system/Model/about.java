package com.divyanshi.garg.attendance_system.Model;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.divyanshi.garg.attendance_system.Admin;
import com.divyanshi.garg.attendance_system.EmployeeLogin;
import com.divyanshi.garg.attendance_system.MainActivity;
import com.divyanshi.garg.attendance_system.R;

public class about extends AppCompatActivity {

    TextView about;
    private NavigationView nv;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        about=(TextView)findViewById(R.id.aboutus);

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
                        Intent intent = new Intent(about.this,Admin.class);
                        startActivity(intent);
                        break;
                    case R.id.employee:
                        Intent intent1 = new Intent(about.this, EmployeeLogin.class);
                        startActivity(intent1);
                        break;
                    case R.id.student:
                        Intent intent2 = new Intent(about.this, MainActivity.class);
                        startActivity(intent2);break;

                    case R.id.aboutus:
                        Intent intent3 = new Intent(about.this, about.class);
                        startActivity(intent3);break;


                    default:
                        return true;
                }


                return true;

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
