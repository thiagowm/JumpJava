package com.jump.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.jump.test.model.AppDatabase;
import com.jump.test.model.Prices;
import com.jump.test.model.Vehicle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class VehicleDetailsActivity extends AppCompatActivity {
    AppDatabase db;
    TextView textViewPlateDetails, textViewModelDetails, textViewColorDetails;
    Button btnVehicleEnter;
    String timeEnter, type_payment = "";
    Integer typePayment = 0;
//    String type_payment = ""
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_details);
        db = new AppDatabase( this );
        textViewPlateDetails = findViewById( R.id.textViewPlateDetails );
        textViewModelDetails = findViewById( R.id.textViewModelDetails );
        textViewColorDetails = findViewById( R.id.textViewColorDetails );
        btnVehicleEnter = findViewById( R.id.btnVehicleEnter );

        Integer vehicleId = getIntent().getIntExtra("vehicle_id", -1);
        if( vehicleId == -1 ){
            finish();
            return;
        }

        Vehicle vehicle = db.getVehicleGetById( vehicleId );
        textViewPlateDetails.setText( "Placa: " + vehicle.getPlate() );
        textViewModelDetails.setText( "Modelo: " + vehicle.getModel() );
        textViewColorDetails.setText( "Cor: " + vehicle.getColor() );
        timeEnter = vehicle.getTime_enter();

        btnVehicleEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(
                        VehicleDetailsActivity.this);
                builder.setTitle(R.string.out_vehicles);
                builder.setMessage(R.string.msg_exit_vehicle);
                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Date time = Calendar.getInstance().getTime();
                        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
                        String currentTime = formatter.format( time );

                        switch(typePayment) {
                            case 1:
                                type_payment = "Dinheiro";
                                break;
                            case 2:
                                type_payment = "Crédito";
                                break;
                            case 3:
                                type_payment = "Débito";
                                break;
                            case 4:
                                type_payment = "Boleto";
                                break;
                            default:
                                type_payment = "Pix";
                                break;
                        }

                        double diferentTime = 0.0;
                        try {
                            diferentTime = intervalValid( timeEnter, currentTime );
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }

                        Integer dif = (int) diferentTime;

                        List<Prices> allItemsPrice = db.getAllItemsPrice();
                        Double total = 0.0;

                        total = total + 1;

//                        for( int i=0; i < allItemsPrice.size(); i++ ){
//                            if ( dif <= allItemsPrice.get(i).items.get(i).getPeriod() ){
//                                total = Double.parseDouble( allItemsPrice.get(i).items.get(i).price );
//                                break;
//                            } else if ( dif > 60 ){
//                                total = Double.parseDouble( allItemsPrice.get(i).items.get(i).price ) + 1;
//                                break;
//                            }
//                        }

                        Vehicle updateVehicle = new Vehicle(vehicleId, "","","","", type_payment, 0, timeEnter, currentTime, total.toString());
                        updateVehicle.setVehicle_id( vehicleId );
                        updateVehicle.setType_payment( type_payment );
                        updateVehicle.setCourtyard( 0 );
                        updateVehicle.setTime_enter( timeEnter );
                        updateVehicle.setTime_out( currentTime );
                        updateVehicle.setTotal( total.toString() );

                        db.updateVehicle(updateVehicle);

                        dialog.dismiss();
                        finish();
                        Toast.makeText( VehicleDetailsActivity.this, R.string.msg_confirm_out_vehicle, Toast.LENGTH_LONG ).show();


                    }
                });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dlg = builder.create();
                dlg.setCancelable(false);
                dlg.setCanceledOnTouchOutside(false);
                dlg.show();

            }
        });

    }

    public Long intervalValid(String timeBegin, String timeFinish) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");

        Date date1 = simpleDateFormat.parse(timeBegin);
        Date date2 = simpleDateFormat.parse(timeFinish);

        long difference = date2.getTime() - date1.getTime();
        int days = (int) (difference / (1000*60*60*24));
        int hours = (int) ((difference - (1000*60*60*24*days)) / (1000*60*60));
        int min = (int) (difference - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);
        hours = (hours < 0 ? -hours : hours);

        return Long.valueOf(hours);
//        SimpleDateFormat format = new SimpleDateFormat("hh:mm");
//        format.setTimeZone(TimeZone.getTimeZone("UTC"));
//        String timeDiffString = "";
//        try {
//            Date date1 = (Date) format.parse(timeBegin);
//            Date date2 = (Date) format.parse(timeFinish);
//            //time difference in milliseconds
//            long timeDiff = date1.getTime() - date2.getTime();
//            //new date object with time difference
//            Date diffDate = new Date(timeDiff);
//            //formatted date string
//            timeDiffString = format.format(diffDate);
////            System.out.println("Time Diff = "+ timeDiffString );
//        } catch (ParseException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return Long.valueOf(timeDiffString);
    }

    public void onRadioButtonDetails(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.btnRadioMoney:
                if (checked)
                    typePayment = 1;
                    break;
            case R.id.btnRadioCredit:
                if (checked)
                    typePayment = 2;
                    break;
            case R.id.btnRadioDebit:
                if (checked)
                    typePayment = 3;
                    break;
            case R.id.btnRadioTicket:
                if (checked)
                    typePayment = 4;
                break;
            case R.id.btnRadioPix:
                if (checked)
                    typePayment = 5;
                break;
        }
    }
}