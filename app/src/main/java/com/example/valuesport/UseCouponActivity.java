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
    static WalletSingleton walletSingleton = WalletSingleton.getInstance();
    int i;

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(WalletSingleton.ownedCoupons);
        editor.putString("coupons", json);
        editor.apply();
        Log.d("debug", "Data SAVED!");
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
        setContentView(R.layout.activity_use_coupon);
        Bundle b = getIntent().getExtras();
        i = 0;
        if (b != null) {
            i = b.getInt("index", 0);
        }
        ((TextView)findViewById(R.id.title))
                .setText(walletSingleton.getOwnedCoupons().get(i).getCouponTitle());
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
