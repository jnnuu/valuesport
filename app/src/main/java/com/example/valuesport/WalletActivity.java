package com.example.valuesport;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.Objects;

public class WalletActivity extends AppCompatActivity {

    private static final String TAG = "WalletActivity";
    private static WalletSingleton walletSingleton = WalletSingleton.getInstance();

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.d(TAG, "oncreate started @walletactivity");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        WalletCatalog();

    }

    private void WalletCatalog() {
        RecyclerView recyclerView = findViewById(R.id.wallet);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, walletSingleton.getOwnedCoupons());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}

