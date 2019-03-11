package com.example.valuesport;

import android.util.Log;

import java.util.ArrayList;

class WalletSingleton {
    private static final WalletSingleton ourInstance = new WalletSingleton();

    static int credits;
    static ArrayList<Coupon> ownedCoupons;

    static WalletSingleton getInstance() {
        return ourInstance;
    }

    private WalletSingleton() {
        ownedCoupons = new ArrayList<>();
    }

    public WalletSingleton getOurInstance() {
        return ourInstance;
    }

    ArrayList<Coupon> getOwnedCoupons() {
        return ownedCoupons;
    }

    void setOwnedCoupons(ArrayList<Coupon> ownedCoupons) {
        WalletSingleton.ownedCoupons = ownedCoupons;
    }

    void addCouponToWallet(Coupon coupon) {
        ownedCoupons.add(coupon);
        Log.d("debug", "Coupon added");
    }

    static int getCredits() {
        return credits;
    }

    static void removeCoupon(int i) {
        ownedCoupons.remove(i);
    }

    void setCredits(int credits) {
        this.credits = credits;
    }

    void useCredits(int amount) {
        this.credits = this.credits - amount;
    }


}
