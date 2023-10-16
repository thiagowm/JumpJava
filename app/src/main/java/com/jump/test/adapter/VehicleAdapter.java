package com.jump.test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jump.test.R;
import com.jump.test.model.Vehicles;

import java.util.ArrayList;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.VehicleHolder> {

    Context context;
    ArrayList<Vehicles> vehicles;
    private OnItemClickListener mListener;

    public VehicleAdapter(Context c, ArrayList<Vehicles> v ){
        context = c;
        vehicles = v;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener( OnItemClickListener listener ){
        mListener = listener;
    }

    @NonNull
    @Override
    public VehicleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VehicleHolder( LayoutInflater.from( parent.getContext() ).inflate(R.layout.row_vehicles, parent, false ) );
    }


    @Override
    public void onBindViewHolder(@NonNull VehicleHolder holder, int position) {
        holder.textViewPlate.setText( R.string.plate +": " + vehicles.get( position ).getPlate() );
        holder.textViewModel.setText( R.string.email +": " + vehicles.get( position ).getModel() );
    }

    @Override
    public int getItemCount() {
        return vehicles.size();
    }

    class VehicleHolder extends RecyclerView.ViewHolder{

        TextView textViewPlate, textViewModel;

        public VehicleHolder(View itemView){
            super( itemView );
            textViewPlate = itemView.findViewById( R.id.textViewPlate );
            textViewModel = itemView.findViewById( R.id.textViewModel );

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
