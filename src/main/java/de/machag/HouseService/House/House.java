package de.machag.HouseService.House;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import org.springframework.data.annotation.Id;

import java.util.UUID;

public class House {

    @Id
    @Expose
    private int id;

    @Expose
    private String addressFull;

    @Expose
    private double purchasePrice;

    @Expose
    private double rentPerMonth;

    @Expose
    private HouseStatus status;

    @Expose
    private int tennantId;

    public House(String addressFull, double purchasePrice, double rentPerMonth, HouseStatus status, int tennantId) {
        this.addressFull = addressFull;
        this.purchasePrice = purchasePrice;
        this.rentPerMonth = rentPerMonth;
        this.status = status;
        this.tennantId = tennantId;
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

    public int getTennantId() {
        return tennantId;
    }

    public void setTennantId(int tennantId) {
        this.tennantId = tennantId;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(this);
    }
}
