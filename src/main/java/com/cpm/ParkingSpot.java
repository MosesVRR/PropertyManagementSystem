package com.cpm;

class ParkingSpot {
    private static int idCounter = 0;
    private int id;
    private SlotStatus status;
    private ParkingSize size;
    int price;
    private Property property;
    private Unit unit;


    public ParkingSpot(int price, Property property) {
        this.id = ++idCounter;
        this.price=price;
        this.property = property;
    }

    public ParkingSpot(int price, Property property, Unit unit) {
        this.id = ++idCounter;
        this.price=price;
        this.property = property;
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SlotStatus getStatus() {
        return status;
    }

    public void setStatus(SlotStatus status) {
        this.status = status;
    }

    public ParkingSize getSize() {
        return size;
    }

    public void setSize(ParkingSize size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
