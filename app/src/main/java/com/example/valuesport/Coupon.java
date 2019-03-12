package com.example.valuesport;

/**
 * The Coupon class wraps data of coupons into object
 *
 * @author Jesse Väärälä
 */

public class Coupon {

    private String couponTitle;
    private int couponPrice;
    private String couponImageUrl;

    /**
     * Class constructor
     * @param couponTitle holding title of coupon as String
     * @param couponPrice price of coupon as int
     * @param couponImageUrl specific URL of coupon icon as String
     */
    public Coupon(String couponTitle, int couponPrice, String couponImageUrl) {
        this.couponTitle = couponTitle;
        this.couponPrice = couponPrice;
        this.couponImageUrl = couponImageUrl;
    }

    /**
     *
     * @return title of Coupon object as String
     */
    public String getCouponTitle() {
        return couponTitle;
    }

    /**
     *
     * @return price of Coupon object as integer
     */
    public int getCouponPrice() {
        return couponPrice;
    }

    /**
     *
     * @return specific Url as String
     */
    public String getCouponImageUrl() {
        return couponImageUrl;
    }
}
