package Enum;

public enum VehicleType {
    TWOWHEELER(0),
    FOURWHEELER(1),
    TRUCK(2);

    private final int id;
    VehicleType(int id) { this.id = id; }
    public int getId() { return id; }
}