package com.jump.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.jump.test.adapter.VehicleAdapter;
import com.jump.test.model.AppDatabase;
import com.jump.test.model.Vehicle;
import com.jump.test.model.Vehicles;

import java.util.ArrayList;

public class VehicleList extends AppCompatActivity {

    VehicleAdapter vehicleAdapter;
    ArrayList<Vehicles> vehiclesArrayList;
    RecyclerView recyclerViewVehicleList;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_list);

        recyclerViewVehicleList = findViewById( R.id.recyclerViewVehicleList );
        recyclerViewVehicleList.setHasFixedSize(true);
        recyclerViewVehicleList.setLayoutManager( new LinearLayoutManager(this) );
        db = new AppDatabase( this );

        listAllVehicles();

    }

    public void listAllVehicles(){
        vehiclesArrayList = new ArrayList<>();
        vehicleAdapter = new VehicleAdapter(this,(ArrayList<Vehicle>) db.getAllVehicles(1));
        recyclerViewVehicleList.setAdapter( vehicleAdapter );
    }

    @Override
    protected void onResume() {
        super.onResume();
        listAllVehicles();
    }
}