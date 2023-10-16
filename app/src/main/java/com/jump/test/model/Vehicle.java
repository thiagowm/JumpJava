package com.jump.test.model;

public class Vehicle {

    public Integer vehicle_id;
    public String vehicle;
    public String plate;
    public String model;
    public String color;
    public String type_payment;
    public Integer courtyard;
    public String time_enter;
    public String time_out;
    public String total;

    public Vehicle(int id, String vehicle, String plate, String model, String color, String typePayment, int courtyard, String timeEnter, String timeOut, String total) {
    }

    public Integer getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(Integer vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType_payment() {
        return type_payment;
    }

    public void setType_payment(String type_payment) {
        this.type_payment = type_payment;
    }

    public Integer getCourtyard() {
        return courtyard;
    }

    public void setCourtyard(Integer courtyard) {
        this.courtyard = courtyard;
    }

    public String getTime_enter() {
        return time_enter;
    }

    public void setTime_enter(String time_enter) {
        this.time_enter = time_enter;
    }

    public String getTime_out() {
        return time_out;
    }

    public void setTime_out(String time_out) {
        this.time_out = time_out;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
