package com.example.valuesport;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WalletSingleton walletInstance = WalletSingleton.getInstance();

        //wallet käyttöön esim. näin:
        //
        //walletInstance.setCredits(500);
        //walletInstance.addCouponToWallet(/*tähän lisättävä kuponki*/);
        //Log.d("debug", String.valueOf(walletInstance.getCredits()));

    }

    public void showPopup(View v) { //Menu buttonin onClick funktio, avaa pudotusvalikon
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.mainmenu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.store:
                toStore();
                return true;
            case R.id.wallet:
                toWallet();
                return true;
            default:
                return false;
        }
    }

    public void toStore() {
        Intent intent = new Intent(this, BuyCouponActivity.class);
        startActivity(intent);
    }

    public void toWallet() {
        Intent intent = new Intent(this, WalletActivity.class);
        startActivity(intent);
    }

    public void StartExc(View view) {
        Intent intent = new Intent(this, ExerciseActivity.class);
        startActivity(intent);
    }
}
