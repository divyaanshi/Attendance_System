package com.divyanshi.garg.attendance_system;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.divyanshi.garg.attendance_system.Model.Studentdetails;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;

public class Employee extends AppCompatActivity {

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    SurfaceView cameraPreview;
    TextView txtResult,Result;
    BarcodeDetector barcodeDetector;
    Button showdetails;
    CameraSource cameraSource;
    final int RequestCameraPermissionID = 1001;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RequestCameraPermissionID: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    try {
                        cameraSource.start(cameraPreview.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            break;
        }
    }


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

            mFirebaseInstance = FirebaseDatabase.getInstance();
            mFirebaseDatabase = mFirebaseInstance.getReference("Students");
            cameraPreview = (SurfaceView) findViewById(R.id.cameraPreview);
            txtResult = (TextView) findViewById(R.id.txtResult);



            barcodeDetector = new BarcodeDetector.Builder(this)
                    .setBarcodeFormats(Barcode.QR_CODE)
                    .build();
            cameraSource = new CameraSource
                    .Builder(this, barcodeDetector)
                    .setRequestedPreviewSize(640, 480)
                    .build();
            //Add Event
            cameraPreview.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder surfaceHolder) {
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        //Request permission
                        ActivityCompat.requestPermissions(Employee.this,
                                new String[]{Manifest.permission.CAMERA},RequestCameraPermissionID);
                        return;
                    }
                    try {
                        cameraSource.start(cameraPreview.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                    cameraSource.stop();

                }
            });

            barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
                @Override
                public void release() {

                }

                @Override
                public void receiveDetections(Detector.Detections<Barcode> detections) {
                    final SparseArray<Barcode> qrcodes = detections.getDetectedItems();
                    if(qrcodes.size() != 0)
                    {
                        txtResult.post(new Runnable() {
                            @Override
                            public void run() {
                                //Create vibrate
                                //Vibrator vibrator = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                               // vibrator.vibrate(1000);
                                txtResult.setText(qrcodes.valueAt(0).displayValue);

                                final String DY=qrcodes.valueAt(0).displayValue;


                                mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                        Studentdetails studentdetails = dataSnapshot.child(DY).getValue(Studentdetails.class);
                                        for (DataSnapshot dSnapshot :dataSnapshot.child(DY).getChildren()) {
                                           final String  id, name, course, department, rn, classes;
                                            id = studentdetails.getStudentID();
                                            name = studentdetails.getName();
                                            course = studentdetails.getCourse();
                                            department = studentdetails.getDepartment();
                                            rn = studentdetails.getRoll_Number();
                                            classes = studentdetails.getBatch();


                                            AlertDialog.Builder builder = new AlertDialog.Builder(Employee.this);
                                            builder.setTitle("Student Details");
                                            builder.setMessage( "ID: "+ DY + "\nNAME: "+ name +"\nCOURSE: "+ course+"\nDEPARTMENT: "+department+"\nROLL NUMBER: "+rn+"\nCLASS: "+classes );

                                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    // You don't have to do anything here if you just
                                                    // want it dismissed when clicked;
                                                }

                                            });
                                            builder.create().show();

                                           // Toast.makeText(getApplicationContext(), DY+ "\n "+ name +"\n"+ course +"\n"+ department+"\n" + rn +"\n"+ classes, Toast.LENGTH_LONG).show();


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
            });



    }
}
