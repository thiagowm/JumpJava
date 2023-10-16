package com.jump.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.jump.test.data.API;
import com.jump.test.data.HTTPClient;
import com.jump.test.model.AppDatabase;
import com.jump.test.model.ExitResponse;
import com.jump.test.model.Items;
import com.jump.test.model.ManualResponse;
import com.jump.test.model.PaymentMethods;
import com.jump.test.model.Vehicle;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManagementActvity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer = null;
    Button btnVehicleEnter;
    TextView textViewTotalVehicleNumber,textViewMoney,textViewCredit,textViewDebit,textViewTicket,textViewPix,textViewTotalG;
    ProgressBar progressBarManagement;
    AppDatabase db;
    API apiInterface;
    Double moneyTotal = 0.0;
    Double creditTotal = 0.0;
    Double debitTotal = 0.0;
    Double ticketTotal = 0.0;
    Double pixTotal = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_management_actvity);

        btnVehicleEnter = findViewById( R.id.btnVehicleEnter );
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout_menu);
        ImageView btnMenuManagement = (ImageView) findViewById(R.id.btnMenuManagement);
        final NavigationView navigationView2 = (NavigationView) findViewById(R.id.nav_view2);
        navigationView2.setNavigationItemSelectedListener(this);

        db = new AppDatabase( this );

        textViewTotalVehicleNumber = findViewById( R.id.textViewTotalVehicleNumber );
        progressBarManagement = findViewById( R.id.progressBarManagement );
        progressBarManagement.setVisibility( View.GONE );
        textViewMoney = findViewById( R.id.textViewMoney );
        textViewCredit = findViewById( R.id.textViewCredit );
        textViewDebit = findViewById( R.id.textViewDebit );
        textViewTicket = findViewById( R.id.textViewTicket );
        textViewPix = findViewById( R.id.textViewPix );
        textViewTotalG = findViewById( R.id.textViewTotalG );

        getManualAPI();
        getAllVehiclesInYard();
        setAllPaymentMethods();

        btnVehicleEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManagementActvity.this, VehicleEntranceActivity.class));
            }
        });

        btnMenuManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View window = findViewById(R.id.btnMenuManagement);
                window.clearFocus();

                if (drawer.isDrawerOpen(GravityCompat.END)) {
                    drawer.closeDrawer(GravityCompat.END);
                } else {
                    drawer.openDrawer(GravityCompat.END);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllVehiclesInYard();
    }

    public void getManualAPI(){
        SharedPreferences sharedPref = getSharedPreferences("jumptest", Context.MODE_PRIVATE);
        Integer requestServer = sharedPref.getInt("request",0);

        if (requestServer == 0){
            getManual();
        }
    }

    public void getAllVehiclesInYard(){
        List allVehicles = db.getAllVehicles(1);
        textViewTotalVehicleNumber.setText( String.valueOf( allVehicles.size() ) );
    }

    public void setAllPaymentMethods(){
        List<Vehicle> allVehiclesOut = db.getAllVehicles(0);
        for( int i=0; i < allVehiclesOut.size(); i++ ){
            if ( allVehiclesOut.get(i).type_payment == "Dinheiro" ){
                moneyTotal += Double.parseDouble( allVehiclesOut.get(i).total );
            }
            if ( allVehiclesOut.get(i).type_payment == "Crédito" ){
                creditTotal += Double.parseDouble( allVehiclesOut.get(i).total );
            }
            if ( allVehiclesOut.get(i).type_payment == "Débito" ){
                debitTotal += Double.parseDouble( allVehiclesOut.get(i).total );
            }
            if ( allVehiclesOut.get(i).type_payment == "Boleto" ){
                ticketTotal += Double.parseDouble( allVehiclesOut.get(i).total );
            }
            if ( allVehiclesOut.get(i).type_payment == "Pix" ){
                pixTotal += Double.parseDouble( allVehiclesOut.get(i).total );
            }
        }

        String mT = currencyFormat( moneyTotal.toString() );
        String cdT = currencyFormat( creditTotal.toString() );
        String dbT = currencyFormat( debitTotal.toString() );
        String ticT = currencyFormat( ticketTotal.toString() );
        String pixT = currencyFormat( pixTotal.toString() );

        textViewMoney.setText( "R$ " + mT.toString() );
        textViewCredit.setText( "R$ " + cdT.toString()  );
        textViewDebit.setText( "R$ " + dbT.toString() );
        textViewTicket.setText( "R$ " + ticT.toString() );
        textViewPix.setText( "R$ " + pixT.toString() );

        double totalG = moneyTotal + creditTotal + debitTotal + ticketTotal + pixTotal;
        textViewTotalG.setText( currencyFormat( String.valueOf(totalG) ) );
    }

    public String currencyFormat( String amount ){
        DecimalFormat formatter = new DecimalFormat( "###,###,##0.00" );
//        String formatter = DecimalFormat("###,###,##0.00");
        return formatter.format( Double.parseDouble( amount ) );
    }

    public void getManual(){
        showProgress();
        SharedPreferences sharedPref = getSharedPreferences("jumptest", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("request", 1);
        editor.commit();

        getSharedPreference();

        apiInterface = HTTPClient.OkHttpClient().create( API.class );

        Call<ManualResponse> call = apiInterface.getManual( HTTPClient.token,HTTPClient.userId,HTTPClient.establishmentId );
        call.enqueue(new Callback<ManualResponse>() {
            @Override
            public void onResponse(Call<ManualResponse> call, Response<ManualResponse> response) {

                if ( response.isSuccessful() ){
                    hideProgress();
                    insertPaymentMethods((List<PaymentMethods>) response.body().data.paymentMethods);
                    insertItemsPrices( response.body().data.prices.get(0).items );

                } else {
                    hideProgress();
                    showFailure( R.string.error_manual );
                }


            }

            @Override
            public void onFailure(Call<ManualResponse> call, Throwable t) {
                hideProgress();
                showFailure( R.string.internal_error );
            }
        });

    }

    public void insertPaymentMethods(List<PaymentMethods> paymentMethods) {

        for( int i=0; i < paymentMethods.size(); i++ ){

            Integer establishmentPaymentMethodId = paymentMethods.get(i).establishmentPaymentMethodId;
            String paymentMethodName = paymentMethods.get(i).paymentMethodName;
            Integer primitivePaymentMethodId = paymentMethods.get(i).primitivePaymentMethodId;
            Integer receivingDays = paymentMethods.get(i).receivingDays;
            String receivingFee = paymentMethods.get(i).receivingFee;
            Integer accountId = paymentMethods.get(i).accountId;

            PaymentMethods payments = new PaymentMethods(
                    establishmentPaymentMethodId,
                    paymentMethodName,
                    primitivePaymentMethodId,
                    receivingDays,
                    receivingFee,
                    accountId
            );

            payments.setEstablishmentPaymentMethodId( establishmentPaymentMethodId );
            payments.setPaymentMethodName( paymentMethodName );
            payments.setPrimitivePaymentMethodId( primitivePaymentMethodId );
            payments.setReceivingDays( receivingDays );
            payments.setReceivingFee( receivingFee );
            payments.setAccountId( accountId );

            db.insertPaymentMethods( payments );

        }
    }

    public void insertItemsPrices(List<Items> items){

        for ( int i=0; i < items.size(); i++ ){
            Integer itemId = items.get(i).itemId;
            String price = items.get(i).price;
            Integer period = items.get(i).period;
            Integer since = items.get(i).since;
            Integer establishmentId = items.get(i).establishmentId;
            String typePrice = items.get(i).typePrice;

            Items item = new Items(
                    itemId,
                    price,
                    period,
                    since,
                    establishmentId,
                    typePrice
            );
            item.setItemId( itemId );
            item.setPrice( price );
            item.setPeriod( period );
            item.setSince( since );
            item.setEstablishmentId( establishmentId );
            item.setTypePrice( typePrice );

            db.insertPricesItems( item );
        }
    }

    public void getSharedPreference(){
        SharedPreferences sharedPreference = getSharedPreferences("jumptest", Context.MODE_PRIVATE);
        HTTPClient.token = sharedPreference.getString("accessToken", " ").toString();
        HTTPClient.userId =  sharedPreference.getInt("userId",0);
        HTTPClient.sessionId =  sharedPreference.getInt("sessionId",0);
        HTTPClient.establishmentId =  sharedPreference.getInt("establishmentId",0);
    }

    public void exitApp(){
        showProgress();
        getSharedPreference();

        apiInterface = HTTPClient.OkHttpClient().create( API.class );

        Call<ExitResponse> call = apiInterface.exitApp( HTTPClient.token,HTTPClient.userId,HTTPClient.establishmentId,HTTPClient.sessionId );
        call.enqueue(new Callback<ExitResponse>() {
            @Override
            public void onResponse(Call<ExitResponse> call, Response<ExitResponse> response) {

                if ( response.isSuccessful() ){
                    ManagementActvity.this.deleteDatabase("jumptest.db" );
                    SharedPreferences settings = ManagementActvity.this.getSharedPreferences("jumptest", MODE_PRIVATE );
                    settings.edit().clear().commit();

                    hideProgress();
                    finish();
                    System.exit(0);
                } else {
                    hideProgress();
                    showFailure( R.string.error_manual );
                }
            }

            @Override
            public void onFailure(Call<ExitResponse> call, Throwable t) {
                hideProgress();
                showFailure( R.string.internal_error );
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menuListVehicles) {

            drawer.closeDrawer(GravityCompat.END);
            startActivity(new Intent(ManagementActvity.this, VehicleList.class));

        } else if (id == R.id.menuExit) {

            AlertDialog.Builder builder = new AlertDialog.Builder(
                    ManagementActvity.this);
            builder.setTitle(R.string.app_name);
            builder.setMessage(R.string.msg_exit_app);
            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog,
                                    int which) {
                    drawer.closeDrawer(GravityCompat.END);
                    dialog.dismiss();
                    exitApp();
                }
            });
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    drawer.closeDrawer(GravityCompat.END);
                }
            });

            AlertDialog dlg = builder.create();
            dlg.setCancelable(false);
            dlg.setCanceledOnTouchOutside(false);
            dlg.show();

        }

        return true;
    }

    public void showProgress(){
        progressBarManagement.setVisibility( View.VISIBLE );
    }

    public void hideProgress(){
        progressBarManagement.setVisibility( View.GONE );
    }

    public void showFailure(Integer message){
        hideProgress();
        Toast.makeText( ManagementActvity.this, message, Toast.LENGTH_LONG ).show();
    }
}