package com.example.valuesport;

import android.util.Log;

import java.util.ArrayList;

/**
 *
 * Singleton class which holds credits and coupons of users.
 * @author Jesse Väärälä
 */
class WalletSingleton {
    private static final WalletSingleton ourInstance = new WalletSingleton();

    static int credits;
    static ArrayList<Coupon> ownedCoupons;

    /**
     *
     * @return single instance of WalletSingleton
     */
    static WalletSingleton getInstance() {
        return ourInstance;
    }

    private WalletSingleton() {
        ownedCoupons = new ArrayList<>();
    }

    /**
     *
     * @return ArrayList of objects of Coupon class
     */
    ArrayList<Coupon> getOwnedCoupons() {
        return ownedCoupons;
    }

    /**
     * Sets content of ArrayList ownedCoupons
     * @param ownedCoupons ArrayList of Coupon objects
     */
    void setOwnedCoupons(ArrayList<Coupon> ownedCoupons) {
        WalletSingleton.ownedCoupons = ownedCoupons;
    }

    /**
     *
     * @param coupon Coupon object as parameter
     */
    void addCouponToWallet(Coupon coupon) {
        ownedCoupons.add(coupon);
        Log.d("debug", "Coupon added");
    }

    /**
     *
     * @return amount of credits as integer value
     */
    static int getCredits() {
        return credits;
    }

    /**
     * Removes specified index from ArrayList ownedCoupons
     * @param i index to be removed as integer value
     */
    static void removeCoupon(int i) {
        ownedCoupons.remove(i);
    }

    /**
     * Sets amount of credits to given value
     * @param credits amount of credits to be set as integer value
     */
    void setCredits(int credits) {
        WalletSingleton.credits = credits;
    }

    /**
     * Decreases credits by given amount
     * @param amount amount of credits to be decreased as integer value
     */
    void useCredits(int amount) {
        credits = credits - amount;
    }


}
