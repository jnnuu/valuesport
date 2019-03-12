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

//    /**
//     *
//     * @param coupons ArrayList of Coupon class objects
//     */
//    public void setCoupons(ArrayList<Coupon> coupons) {
//        this.coupons = coupons;
//    }

    private final ArrayList<Coupon> coupons;

    /**
     *
     */
    private StoreContentSingleton() {
        coupons = new ArrayList<>();


        // Add placeholder coupons to ArrayList coupons
        coupons.add(new Coupon("Netflix Free Month", "Get a free month with this coupon!", 250, "https://assets.brand.microsites.netflix.io/assets/d13b73da-c4e7-11e7-8d40-066b49664af6_cm_800w.png?v=7"));
        coupons.add(new Coupon("Netflix for 3 months", "Get a 3 free months with this coupon!", 1400, "https://assets.brand.microsites.netflix.io/assets/d13b73da-c4e7-11e7-8d40-066b49664af6_cm_800w.png?v=7"));
        coupons.add(new Coupon("Netflix for 6 months", "Get a whole year free with this coupon!", 2500, "https://assets.brand.microsites.netflix.io/assets/d13b73da-c4e7-11e7-8d40-066b49664af6_cm_800w.png?v=7"));
        coupons.add(new Coupon("Spotify Premium for a month", "Get month of Spotify Preium!", 250, "https://seeklogo.net/wp-content/uploads/2015/09/spotify-logo-vector-download.jpg"));
        coupons.add(new Coupon("Spotify Premium for 6 months", "Get 6 months of Spotify Preium!", 1400, "https://seeklogo.net/wp-content/uploads/2015/09/spotify-logo-vector-download.jpg"));
        coupons.add(new Coupon("Spotify Premium for a year", "Get whole year of Spotify Preium!", 2500, "https://seeklogo.net/wp-content/uploads/2015/09/spotify-logo-vector-download.jpg"));
        coupons.add(new Coupon("BicMac for 2€","Legendary Burger with just 2 euros!", 100, "https://www.mcdonalds.com/content/dam/usa/logo/m_logo.png"));
        coupons.add(new Coupon("Drink of choice from McDonalds","Refresh yourself with drink of choice!", 50, "https://www.mcdonalds.com/content/dam/usa/logo/m_logo.png"));


//        coupons.add(new Coupon("makkarat -50% lähikaupasta", 150,"https://pbs.twimg.com/profile_images/2725100467/41c75598637053c11b75c05d8309650f_400x400.png"));
//        coupons.add(new Coupon("Euron juusto hesasta", 250,"https://is2-ssl.mzstatic.com/image/thumb/Purple124/v4/3b/16/77/3b167783-02ca-629f-fb43-3284013e4fe6/source/256x256bb.jpg"));
//        coupons.add(new Coupon("Euron juusto hesasta", 250,"https://is2-ssl.mzstatic.com/image/thumb/Purple124/v4/3b/16/77/3b167783-02ca-629f-fb43-3284013e4fe6/source/256x256bb.jpg"));
//        coupons.add(new Coupon("Euron juusto hesasta", 250,"https://is2-ssl.mzstatic.com/image/thumb/Purple124/v4/3b/16/77/3b167783-02ca-629f-fb43-3284013e4fe6/source/256x256bb.jpg"));
//        coupons.add(new Coupon("Euron juusto hesasta", 250,"https://is2-ssl.mzstatic.com/image/thumb/Purple124/v4/3b/16/77/3b167783-02ca-629f-fb43-3284013e4fe6/source/256x256bb.jpg"));
//        coupons.add(new Coupon("Euron juusto hesasta", 250,"https://is2-ssl.mzstatic.com/image/thumb/Purple124/v4/3b/16/77/3b167783-02ca-629f-fb43-3284013e4fe6/source/256x256bb.jpg"));
//        coupons.add(new Coupon("Euron juusto hesasta", 250,"https://is2-ssl.mzstatic.com/image/thumb/Purple124/v4/3b/16/77/3b167783-02ca-629f-fb43-3284013e4fe6/source/256x256bb.jpg"));
//        coupons.add(new Coupon("Jos liikuntasuorituksesi päättyy IKEAAN, yli 150e ostoksissa ilmainen kotiinkuljetus! tartu tarjoukseen", 100,"https://wmgk.com/wp-content/uploads/sites/2/2018/01/Ikea-Logo.jpg"));

    }
}
