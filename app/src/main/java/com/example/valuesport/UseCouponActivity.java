package com.example.valuesport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class UseCouponActivity extends AppCompatActivity {
    static WalletSingleton walletSingleton = WalletSingleton.getInstance();
    int i;

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
