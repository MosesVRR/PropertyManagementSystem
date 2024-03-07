package com.cpm;

class Vehicle {
    private static int idCounter = 0;
    private int id;

    private String vehicleNo;
    private VehicleType type;

    public Vehicle(String vehicleId, VehicleType type) {
        this.id = ++idCounter;
        this.type = type;
    }
}
