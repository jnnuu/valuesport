package com.example.valuesport;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

public class UseCouponActivity extends AppCompatActivity {
    private static final WalletSingleton walletSingleton = WalletSingleton.getInstance();
    private int i;

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(WalletSingleton.ownedCoupons);
        Log.d("debug", json);
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
        saveCredits();
        saveData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_coupon);
        Bundle b = getIntent().getExtras();
        i = 0;
        if (b != null) {
            i = b.getInt("index", 0);
        }
        ((TextView)findViewById(R.id.title))
                .setText(walletSingleton.getOwnedCoupons().get(i).getCouponTitle());
        ((TextView)findViewById(R.id.description))
                .setText(walletSingleton.getOwnedCoupons().get(i).getCouponDescription());
        Glide.with(this)
                .asBitmap()
                .load(walletSingleton.getOwnedCoupons().get(i).getCouponImageUrl())
                .into((ImageView) findViewById(R.id.image));

    }
    public void use(View v) {
        WalletSingleton.removeCoupon(i);
        Intent Intent = new Intent(this, WalletActivity.class);
        startActivity(Intent);
    }
}
