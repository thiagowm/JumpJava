package com.jump.test.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jump.test.R;
import com.jump.test.VehicleDetailsActivity;
import com.jump.test.model.Vehicle;
import com.jump.test.model.Vehicles;

import java.util.ArrayList;
import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.VehicleHolder> {

    Context context;
    List<Vehicle> vehicles;
    private OnItemClickListener mListener;

    public VehicleAdapter(Context c, ArrayList<Vehicle> v ){
        context = c;
        vehicles = v;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener( OnItemClickListener listener ){
        mListener = listener;
    }

    @Override
    public VehicleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VehicleHolder( LayoutInflater.from( parent.getContext() ).inflate(R.layout.row_vehicles, parent, false ) );
    }


    @Override
    public void onBindViewHolder(VehicleHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textViewPlate.setText("Placa: " + vehicles.get(position).getPlate());
        holder.textViewModel.setText("Modelo: " + vehicles.get(position).getModel());
        holder.imageViewRowDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( holder.itemView.getContext(), VehicleDetailsActivity.class);
                intent.putExtra( "vehicle_id", vehicles.get(position).getVehicle_id() );
                holder.itemView.getContext().startActivity( intent );
            }
        });
    }

    @Override
    public int getItemCount() {
        return vehicles.size();
    }

    class VehicleHolder extends RecyclerView.ViewHolder{

        TextView textViewPlate, textViewModel;
        ImageView imageViewRowDetails;

        public VehicleHolder(View itemView){
            super( itemView );
            textViewPlate = itemView.findViewById( R.id.texViewPlacaRow );
            textViewModel = itemView.findViewById( R.id.texViewModelRow );
            imageViewRowDetails = itemView.findViewById( R.id.imageViewRowDetails );

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if( mListener != null ){
                        int position = getAdapterPosition();
                        if( position != RecyclerView.NO_POSITION ){
                            mListener.onItemClick( position );
                        }
                    }
                }
            });
        }
    }
}
