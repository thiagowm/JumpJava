package com.jump.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.jump.test.adapter.VehicleAdapter;
import com.jump.test.model.Vehicles;

import java.util.ArrayList;

public class VehicleList extends AppCompatActivity {

    VehicleAdapter vehicleAdapter;
    ArrayList<Vehicles> vehiclesArrayList;
    RecyclerView recyclerViewVehicleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_list);

        recyclerViewVehicleList = findViewById( R.id.recyclerViewVehicleList );
        recyclerViewVehicleList.setHasFixedSize(true);
        recyclerViewVehicleList.setLayoutManager( new LinearLayoutManager(this) );

        vehiclesArrayList = new ArrayList<>();
        vehicleAdapter = new VehicleAdapter(this,vehiclesArrayList);
        recyclerViewVehicleList.setAdapter( vehicleAdapter );

    }
}