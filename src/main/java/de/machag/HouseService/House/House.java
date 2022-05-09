package de.machag.HouseService.House;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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

    @Expose
    private Timestamp buyDate;

    @Expose
    @Transient
    String buyDateInfo;

    public House(String addressFull, double purchasePrice, double rentPerMonth, HouseStatus status, int tennantId, Timestamp buyDate) {
        this.addressFull = addressFull;
        this.purchasePrice = purchasePrice;
        this.rentPerMonth = rentPerMonth;
        this.status = status;
        this.tennantId = tennantId;
        this.buyDate = buyDate;
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

    public Timestamp getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Timestamp buyDate) {
        this.buyDate = buyDate;
    }

    @Override
    public String toString() {
        buyDateInfo = "Immobilie seit " + this.calculateDaysInOwnership(buyDate) + " Tage(n) im Besitz...";
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(this);
    }

    private int calculateDaysInOwnership(Timestamp dbBuyDate) {
        Calendar c = Calendar.getInstance();
        int days = 0;
        while(c.getTimeInMillis() > dbBuyDate.getTime()) {
            c.add(Calendar.DAY_OF_YEAR, -1);
            days ++;
        }
        return days;
    }
}
