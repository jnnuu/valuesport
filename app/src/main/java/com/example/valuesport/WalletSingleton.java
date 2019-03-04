package com.example.valuesport;

import java.util.ArrayList;

class WalletSingleton {
    private static final WalletSingleton ourInstance = new WalletSingleton();

    int credits;
    private ArrayList<Coupon> ownedCoupons;


    static WalletSingleton getInstance() {
        return ourInstance;
    }

    private WalletSingleton() {
    }

    public static WalletSingleton getOurInstance() {
        return ourInstance;
    }

    public ArrayList<Coupon> getOwnedCoupons() {
        return ownedCoupons;
    }

    public void addCouponToWallet(Coupon coupon) {
        ownedCoupons.add(coupon);
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }


}
