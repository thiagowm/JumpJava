package com.jump.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jump.test.model.AppDatabase;
import com.jump.test.model.Vehicle;
import com.jump.test.model.Vehicles;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;

public class VehicleEntranceActivity extends AppCompatActivity {

    AppDatabase db;
    TextView textViewPlaca,textViewModel,textViewColor;
    Button btnVehicleEnterParking;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_entrance);

        db = new AppDatabase( this );
        textViewPlaca = findViewById( R.id.textViewPlaca );
        textViewModel = findViewById( R.id.textViewModel );
        textViewColor = findViewById( R.id.textViewColor );
        btnVehicleEnterParking = findViewById( R.id.btnVehicleEnterParking );

        btnVehicleEnterParking.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String plate = textViewPlaca.getText().toString();
                String vehic = textViewModel.getText().toString();
                String model = textViewModel.getText().toString();
                String color = textViewColor.getText().toString();

                Date time = Calendar.getInstance().getTime();
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
                String current = formatter.format(time);

                Vehicle vehicle = new Vehicle( 0, vehic, plate, model, color, "0", 1, current, "0", "0");
                vehicle.setVehicle( vehic );
                vehicle.setPlate( plate );
                vehicle.setModel( model );
                vehicle.setColor( color );
                vehicle.setType_payment("0");
                vehicle.setCourtyard( 1 );
                vehicle.setTime_enter( current );
                vehicle.setTime_out( "0" );
                vehicle.setTotal( "0" );

                db.insertVehicles(vehicle);
                finish();
            }
        });


    }
}