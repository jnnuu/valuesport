package com.example.valuesport;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public class BuyCouponActivity extends AppCompatActivity {

    private static final String TAG = "BuyCouponActivity";
    private static final StoreContentSingleton storeContentSingleton = StoreContentSingleton.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_coupon);
        Log.d(TAG, "oncreate started @buycouponactivity");
        StoreCatalog();
    }

    private void StoreCatalog() {
        RecyclerView recyclerView = findViewById(R.id.store);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, storeContentSingleton.getCoupons());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}

