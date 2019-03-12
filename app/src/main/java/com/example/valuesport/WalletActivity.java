package com.example.valuesport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

public class WalletActivity extends AppCompatActivity {

    private static final String TAG = "WalletActivity";
    static WalletSingleton walletSingleton = WalletSingleton.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.d(TAG, "oncreate started @walletactivity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        WalletCatalog();

    }

    private void WalletCatalog() {
        RecyclerView recyclerView = findViewById(R.id.wallet);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, walletSingleton.getOwnedCoupons());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}

