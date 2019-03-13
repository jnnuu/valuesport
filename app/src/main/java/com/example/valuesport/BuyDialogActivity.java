package com.example.valuesport;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;


public class BuyDialogActivity extends AppCompatActivity {

    private static final StoreContentSingleton storeContentSingleton = StoreContentSingleton.getInstance();
    private static final WalletSingleton walletSingleton = WalletSingleton.getInstance();
    private int i;

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(WalletSingleton.ownedCoupons);
        editor.putString("coupons", json);
        editor.apply();
        Log.d("debug", "Data SAVED!");
    }

    private void saveCredits() {
        SharedPreferences sharedPreferences = getSharedPreferences("credit preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String jsonc = String.valueOf(WalletSingleton.getCredits());
        Log.d("debug", jsonc);
        editor.putString("credits", jsonc);
        editor.apply();
        Log.d("debug", "Credits SAVED!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("debug", "onPause() called");
        saveData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_dialog);

        Bundle b = getIntent().getExtras();
        i = 0;
        if (b != null) {
            i = b.getInt("index", 0);
        }
        ((TextView) findViewById(R.id.title))
                .setText(storeContentSingleton.getCoupon(i).getCouponTitle());
        ((TextView) findViewById(R.id.description))
                .setText(storeContentSingleton.getCoupon(i).getCouponDescription());
        ((TextView) findViewById(R.id.price))
                .setText(String.valueOf(storeContentSingleton.getCoupon(i).getCouponPrice()));


        Glide.with(this)
                .asBitmap()
                .load(storeContentSingleton.getCoupon(i).getCouponImageUrl())
                .into((ImageView) findViewById(R.id.image));


    }

    public void buy(View v) {
        if (WalletSingleton.getCredits() >= storeContentSingleton.getCoupon(i).getCouponPrice()) {
            Log.d("osto", String.valueOf(WalletSingleton.getCredits()));
            walletSingleton.addCouponToWallet(storeContentSingleton.getCoupon(i));
            walletSingleton.useCredits(storeContentSingleton.getCoupon(i).getCouponPrice());
            Log.d("osto", String.valueOf(WalletSingleton.getCredits()));
            //Toast.makeText(this, "Item succesfully added to wallet! credits left: " + WalletSingleton.getCredits(), Toast.LENGTH_SHORT).show();
            Toast toast = Toast.makeText(this,
                    getResources().getString(R.string.coupon_enough_credits) + WalletSingleton.getCredits(), Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

        } else {
            Log.d("osto", "Not enough credits");
            //Toast.makeText(this, "Not enough credits! You currently have: " + WalletSingleton.getCredits(), Toast.LENGTH_SHORT).show();
            Toast toast = Toast.makeText(this,
                    getResources().getString(R.string.coupon_not_enough_credits) + WalletSingleton.getCredits(), Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }

        saveCredits();
        saveData();
    }

}
