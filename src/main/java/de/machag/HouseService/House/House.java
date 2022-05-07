package de.machag.HouseService.House;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class House {

    @Expose
    private String id;

    @Expose
    private String addressFull;

    @Expose
    private double purchasePrice;

    @Expose
    private double rentPerMonth;

    @Expose
    private HouseStatus status;

    @Expose
    private String tennantId;

    public House(String id, String addressFull, double purchasePrice, double rentPerMonth, HouseStatus status, String tennantId) {
        this.id = id;
        this.addressFull = addressFull;
        this.purchasePrice = purchasePrice;
        this.rentPerMonth = rentPerMonth;
        this.status = status;
        this.tennantId = tennantId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getRentPerMonth() {
        return rentPerMonth;
    }

    public void setRentPerMonth(double rentPerMonth) {
        this.rentPerMonth = rentPerMonth;
    }

    public HouseStatus getStatus() {
        return status;
    }

    public void setStatus(HouseStatus status) {
        this.status = status;
    }

    public String getAddressFull() {
        return addressFull;
    }

    public void setAddressFull(String addressFull) {
        this.addressFull = addressFull;
    }

    public String getTennantId() {
        return tennantId;
    }

    public void setTennantId(String tennantId) {
        this.tennantId = tennantId;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(this);
    }
}
