package com.jump.test.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class AppDatabase extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "jumptest.db";
    private static Integer DATABASE_VERSION = 1;

    //Table paymentmethods...
    private static String TABLE_NAME_PAYMENT_METHODS = "paymentmethods";
    private static String COLUMN_ESTABLISHMENT_PAYMETHOD_ID = "establishment_payment_method_id";
    private static String COLUMN_PAYMENT_METHOD_NAME = "payment_method_name";
    private static String COLUMN_PRIMITIVE_PAYMENT_METHOD_ID = "primitive_payment_method_id";
    private static String COLUMN_RECEIVING_DAYS = "receiving_days";
    private static String COLUMN_RECEIVING_FEE = "receiving_fee";
    private static String COLUMN_ACCOUNT_ID = "account_id";

    //Table vehicles...
    private static String TABLE_NAME_VEHICLES = "vehicles";
    private static String COLUMN_VEHICLE_ID = "vehicle_id";
    private static String COLUMN_VEHICLE = "vehicle";
    private static String COLUMN_PLATE = "plate";
    private static String COLUMN_MODEL = "model";
    private static String COLUMN_COLOR = "color";
    private static String COLUMN_TYPE_PAYMENT = "type_payment";
    private static String COLUMN_COURTYARD = "courtyard";
    private static String COLUMN_TIME_ENTER = "time_enter";
    private static String COLUMN_TIME_OUT = "time_out";
    private static String COLUMN_TOTAL = "total";

    //Table Items in Prices...
    private static String TABLE_NAME_ITEMS = "items";
    private static String COLUMN_ITEM_PRICE_ID = "id";
    private static String COLUMN_ITEM_ID = "item_id";
    private static String COLUMN_PRICE = "price";
    private static String COLUMN_PERIOD = "period";
    private static String COLUMN_SINCE = "since";
    private static String COLUMN_ESTABLISMENT_ID = "establishment_id";
    private static String COLUMN_TYPE_PRICE = "type_price";

    public AppDatabase(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    public AppDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public AppDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public AppDatabase(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //Table paymentmethods...
        String createTableQuery1 = "CREATE TABLE "+ TABLE_NAME_PAYMENT_METHODS + "("
                + COLUMN_ESTABLISHMENT_PAYMETHOD_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_PAYMENT_METHOD_NAME + " TEXT, "
                + COLUMN_PRIMITIVE_PAYMENT_METHOD_ID + " INTEGER, "
                + COLUMN_RECEIVING_DAYS + " INTEGER, "
                + COLUMN_RECEIVING_FEE + " TEXT, "
                + COLUMN_ACCOUNT_ID + " INTEGER)";
        db.execSQL( createTableQuery1 );

        //Table vehicles...
        String createTableQuery3 = "CREATE TABLE "+ TABLE_NAME_VEHICLES + "("
                + COLUMN_VEHICLE_ID + " INTEGER PRIMARY KEY ,"
                + COLUMN_VEHICLE +" TEXT, "
                + COLUMN_PLATE +" INTEGER, "
                + COLUMN_MODEL +" INTEGER, "
                + COLUMN_COLOR +" TEXT, "
                + COLUMN_TYPE_PAYMENT +" TEXT, "
                + COLUMN_COURTYARD +" INTEGER, "
                + COLUMN_TIME_ENTER +" TEXT,"
                + COLUMN_TIME_OUT +" TEXT,"
                + COLUMN_TOTAL +" TEXT)";
        db.execSQL( createTableQuery3 );

        //Table items in Prices...
        String createTableQuery4 = "CREATE TABLE "+TABLE_NAME_ITEMS +"("
                + COLUMN_ITEM_PRICE_ID +" INTEGER PRIMARY KEY , "
                + COLUMN_ITEM_ID +" INTEGER, "
                + COLUMN_PRICE + " TEXT, "
                + COLUMN_PERIOD + " INTEGER, "
                + COLUMN_SINCE + " INTEGER, "
                + COLUMN_ESTABLISMENT_ID + " INTEGER, "
                + COLUMN_TYPE_PRICE + " TEXT)";
        db.execSQL( createTableQuery4 );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Table paymentmethods...
        String dropTableQuery1 = "DROP TABLE IF EXISTS " + TABLE_NAME_PAYMENT_METHODS;
        db.execSQL( dropTableQuery1 );

        //Table vehicles...
        String dropTableQuery3 = "DROP TABLE IF EXISTS " + TABLE_NAME_VEHICLES;
        db.execSQL( dropTableQuery3 );

        //Table items in prices...
        String dropTableQuery4 = "DROP TABLE IF EXISTS " + TABLE_NAME_ITEMS;
        db.execSQL( dropTableQuery4 );
        onCreate( db );

    }

    public void insertPaymentMethods( PaymentMethods paymentMethods ){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ESTABLISHMENT_PAYMETHOD_ID, paymentMethods.getEstablishmentPaymentMethodId());
        values.put(COLUMN_PAYMENT_METHOD_NAME, paymentMethods.getPaymentMethodName());
        values.put(COLUMN_PRIMITIVE_PAYMENT_METHOD_ID, paymentMethods.getPrimitivePaymentMethodId());
        values.put(COLUMN_RECEIVING_DAYS, paymentMethods.getReceivingDays());
        values.put(COLUMN_RECEIVING_FEE, paymentMethods.getReceivingFee());
        values.put(COLUMN_ACCOUNT_ID, paymentMethods.getAccountId());

        db.insert( TABLE_NAME_PAYMENT_METHODS, null, values );
        db.close();
    }

    public void insertPricesItems( Items items ){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEM_ID, items.getItemId());
        values.put(COLUMN_PRICE, items.getPrice());
        values.put(COLUMN_PERIOD, items.getPeriod());
        values.put(COLUMN_SINCE, items.getSince());
        values.put(COLUMN_ESTABLISMENT_ID, items.getEstablishmentId());
        values.put(COLUMN_TYPE_PRICE, items.getTypePrice());

        db.insert(TABLE_NAME_ITEMS, null, values);
        db.close();
    }

    public void insertVehicles( Vehicle vehicle ){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_VEHICLE, vehicle.getVehicle());
        values.put(COLUMN_PLATE, vehicle.getPlate());
        values.put(COLUMN_MODEL, vehicle.getModel());
        values.put(COLUMN_COLOR, vehicle.getColor());
        values.put(COLUMN_TYPE_PAYMENT, vehicle.getType_payment());
            //value 1 is true and 0 is false
        values.put(COLUMN_COURTYARD, vehicle.getCourtyard());
        values.put(COLUMN_TIME_ENTER, vehicle.getTime_enter());
        values.put(COLUMN_TIME_OUT, vehicle.getTime_out());
        values.put(COLUMN_TOTAL, vehicle.getTotal());

        db.insert(TABLE_NAME_VEHICLES, null, values);
        db.close();
    }

    public List getAllVehicles(Integer court){
        List vehiclesList = new ArrayList(); // mutableListOf<Vehicle>()
        SQLiteDatabase db = getReadableDatabase();
        String query = "";
        if (court == 1){
            query = "SELECT * FROM " + TABLE_NAME_VEHICLES + " WHERE " + COLUMN_COURTYARD + " = 1";
        } else {
            query = "SELECT * FROM " + TABLE_NAME_VEHICLES + " WHERE " + COLUMN_COURTYARD + " = 0";
        }
        Cursor cursor = db.rawQuery( query, null );
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_VEHICLE_ID));
            String vehicle = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_VEHICLE));
            String plate = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PLATE));
            String model = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MODEL));
            String color = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COLOR));
            String type_payment = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TYPE_PAYMENT));
            int courtyard = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_COURTYARD));
            String time_enter = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME_ENTER));
            String time_out = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME_OUT));
            String total = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TOTAL));

            Vehicle vehicles = new Vehicle(id, vehicle, plate, model, color, type_payment, courtyard, time_enter, time_out, total);
            vehiclesList.add( vehicles );
        }
        cursor.close();
        db.close();

        return vehiclesList;
    }

    public void updateVehicle( Vehicle vehicle ){
        SQLiteDatabase db = getWritableDatabase();
        //value courtyard if 1 is true and 0 is false
        ContentValues values = new ContentValues();
        values.put( COLUMN_TYPE_PAYMENT, vehicle.type_payment );
        values.put( COLUMN_COURTYARD, vehicle.courtyard );
        values.put( COLUMN_TIME_OUT, vehicle.time_out );
        values.put( COLUMN_TOTAL, vehicle.total );

        String whereClause = COLUMN_VEHICLE_ID + " = ?";
        String whereArgs = vehicle.vehicle_id.toString();
        db.update(TABLE_NAME_VEHICLES, values, whereClause, new String[]{whereArgs});
        db.close();
    }

    public Vehicle getVehicleGetById(Integer vehicleId){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_VEHICLES + " WHERE " + COLUMN_VEHICLE_ID + " = "+ vehicleId;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_VEHICLE_ID));
        String vehicle = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_VEHICLE));
        String plate = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PLATE));
        String model = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MODEL));
        String color = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COLOR));
        int courtyard = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_COURTYARD));
        String time_enter = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME_ENTER));
        String time_out = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME_OUT));
        String total = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TOTAL));

        cursor.close();
        db.close();
        return new Vehicle(id, vehicle, plate, model, color, "", courtyard, time_enter, time_out, total);
    }

    public List getAllItemsPrice(){
        List itemsPriceList = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_ITEMS;
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()){
            int itemId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ITEM_ID));
            String price = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRICE));
            int period = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PERIOD));
            int since = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SINCE));
            int establishmentId = cursor.getInt(cursor.getColumnIndexOrThrow(
                    COLUMN_ESTABLISMENT_ID));
            String typePrice = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TYPE_PRICE));

            Items itemsPrices = new Items(itemId, price, period, since, establishmentId, typePrice);
            itemsPriceList.add( itemsPrices );
        }
        cursor.close();
        db.close();

        return itemsPriceList;
    }
}
