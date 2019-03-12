package com.example.valuesport;

/**
 * The Coupon class wraps data of coupons into object
 *
 * @author Jesse Väärälä
 */

public class Coupon {

    private final String couponTitle;
    private final String couponImageUrl;
    private final String couponDescription;
    private final int couponPrice;


    /**
     * Class constructor
     *
     * @param couponTitle    holding title of coupon as String
     * @param couponPrice    price of coupon as int
     * @param couponImageUrl specific URL of coupon icon as String
     */
    public Coupon(String couponTitle, String couponDescription, int couponPrice, String couponImageUrl) {
        this.couponTitle = couponTitle;
        this.couponDescription = couponDescription;
        this.couponPrice = couponPrice;
        this.couponImageUrl = couponImageUrl;
    }

    /**
     * @return title of Coupon object as String
     */
    public String getCouponTitle() {
        return couponTitle;
    }

    /**
     * @return price of Coupon object as integer
     */
    public int getCouponPrice() {
        return couponPrice;
    }

    /**
     * @return specific Url as String
     */
    public String getCouponImageUrl() {
        return couponImageUrl;
    }

    /**
     * @return description of coupon as String
     */
    public String getCouponDescription() {
        return couponDescription;
    }
}

