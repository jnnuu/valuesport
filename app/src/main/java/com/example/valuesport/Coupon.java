package com.example.valuesport;

public class Coupon {
    private String couponTitle;
    private int couponPrice;
    private boolean couponUsable;
    private String couponImageUrl;

    public Coupon(String couponTitle, int couponPrice, boolean couponUsable, String couponImageUrl) {
        this.couponTitle = couponTitle;
        this.couponPrice = couponPrice;
        this.couponUsable = couponUsable;
        this.couponImageUrl =  couponImageUrl;
    }

    public String getCouponTitle() {
        return couponTitle;
    }

    public int getCouponPrice() {
        return couponPrice;
    }

    public boolean isCouponUsable() {
        return couponUsable;
    }

    public String getCouponImageUrl() {
        return couponImageUrl;
    }

    public void setCouponUsable(boolean couponUsable){
        this.couponUsable = couponUsable;
    }
}
