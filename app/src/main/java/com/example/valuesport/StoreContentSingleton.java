package com.example.valuesport;

import java.util.ArrayList;

/**
 *
 * Singleton class to hold placeholder content of store items.
 * @author Jesse Väärälä
 */
class StoreContentSingleton {
    private static final StoreContentSingleton ourInstance = new StoreContentSingleton();

    /**
     *
     * @return single instance of StoreContentSingleton
     */
    static StoreContentSingleton getInstance() {
        return ourInstance;
    }

    /**
     *
     * @return ArrayList of Coupon class objects
     */
    public ArrayList<Coupon> getCoupons() {
        return coupons;
    }

    /**
     *
     * @param i index of element of coupons to be returned as integer value
     * @return object of Coupon class
     */
    public Coupon getCoupon(int i) {
        return coupons.get(i);
    }

    /**
     *
     * @param coupons ArrayList of Coupon class objects
     */
    public void setCoupons(ArrayList<Coupon> coupons) {
        this.coupons = coupons;
    }

    private ArrayList<Coupon> coupons;

    /**
     *
     */
    private StoreContentSingleton() {
        coupons = new ArrayList<>();


        // Add placeholder coupons to ArrayList coupons
        coupons.add(new Coupon("makkarat -50% lähikaupasta", 150,"https://pbs.twimg.com/profile_images/2725100467/41c75598637053c11b75c05d8309650f_400x400.png"));
        coupons.add(new Coupon("Euron juusto hesasta", 250,"https://is2-ssl.mzstatic.com/image/thumb/Purple124/v4/3b/16/77/3b167783-02ca-629f-fb43-3284013e4fe6/source/256x256bb.jpg"));
        coupons.add(new Coupon("Euron juusto hesasta", 250,"https://is2-ssl.mzstatic.com/image/thumb/Purple124/v4/3b/16/77/3b167783-02ca-629f-fb43-3284013e4fe6/source/256x256bb.jpg"));
        coupons.add(new Coupon("Euron juusto hesasta", 250,"https://is2-ssl.mzstatic.com/image/thumb/Purple124/v4/3b/16/77/3b167783-02ca-629f-fb43-3284013e4fe6/source/256x256bb.jpg"));
        coupons.add(new Coupon("Euron juusto hesasta", 250,"https://is2-ssl.mzstatic.com/image/thumb/Purple124/v4/3b/16/77/3b167783-02ca-629f-fb43-3284013e4fe6/source/256x256bb.jpg"));
        coupons.add(new Coupon("Euron juusto hesasta", 250,"https://is2-ssl.mzstatic.com/image/thumb/Purple124/v4/3b/16/77/3b167783-02ca-629f-fb43-3284013e4fe6/source/256x256bb.jpg"));
        coupons.add(new Coupon("Euron juusto hesasta", 250,"https://is2-ssl.mzstatic.com/image/thumb/Purple124/v4/3b/16/77/3b167783-02ca-629f-fb43-3284013e4fe6/source/256x256bb.jpg"));
        coupons.add(new Coupon("Jos liikuntasuorituksesi päättyy IKEAAN, yli 150e ostoksissa ilmainen kotiinkuljetus! tartu tarjoukseen", 100,"https://wmgk.com/wp-content/uploads/sites/2/2018/01/Ikea-Logo.jpg"));

    }
}
