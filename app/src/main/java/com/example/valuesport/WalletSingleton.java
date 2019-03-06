package com.example.valuesport;

import android.util.Log;

import java.util.ArrayList;

class WalletSingleton {
    private static final WalletSingleton ourInstance = new WalletSingleton();

    static int credits = 1000;
    public static ArrayList<Coupon> ownedCoupons;

    static WalletSingleton getInstance() {
        return ourInstance;
    }

    private WalletSingleton() {
        ownedCoupons = new ArrayList<>();
    }

    public static WalletSingleton getOurInstance() {
        return ourInstance;
    }

    public ArrayList<Coupon> getOwnedCoupons() {
        return ownedCoupons;
    }

    public static void setOwnedCoupons(ArrayList<Coupon> ownedCoupons) {
        WalletSingleton.ownedCoupons = ownedCoupons;
    }

    public void addCouponToWallet(Coupon coupon) {
        ownedCoupons.add(coupon);
        Log.d("debug", "Coupon added");
    }

    public static int getCredits() {
        return credits;
    }

    public static void removeCoupon(int i) {
        ownedCoupons.remove(i);
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void useCredits(int amount) {
        this.credits = this.credits - amount;
    }


}
