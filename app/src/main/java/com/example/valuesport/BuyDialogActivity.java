package com.example.valuesport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.valuesport.R.id.image;


public class BuyDialogActivity extends AppCompatActivity {

    static StoreContentSingleton storeContentSingleton = StoreContentSingleton.getInstance();
    static WalletSingleton walletSingleton = WalletSingleton.getInstance();

    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_dialog);

        Bundle b = getIntent().getExtras();
        i = 0;
        if (b != null) {
            i = b.getInt("index", 0);
        }
        ((TextView)findViewById(R.id.title))
                .setText(storeContentSingleton.getCoupon(i).getCouponTitle());
        ((TextView)findViewById(R.id.price))
                .setText(String.valueOf(storeContentSingleton.getCoupon(i).getCouponPrice()));


        Glide.with(this)
                .asBitmap()
                .load(storeContentSingleton.getCoupon(i).getCouponImageUrl())
                .into((ImageView) findViewById(R.id.image));


    }
    public void buy(View v) {
        if (WalletSingleton.getCredits() >= storeContentSingleton.getCoupon(i).getCouponPrice()){
            Log.d("osto", String.valueOf(WalletSingleton.getCredits()));
            walletSingleton.addCouponToWallet(storeContentSingleton.getCoupon(i));
            walletSingleton.useCredits(storeContentSingleton.getCoupon(i).getCouponPrice());
            Log.d("osto", String.valueOf(WalletSingleton.getCredits()));
            Toast.makeText(this, "Item succesfully added to wallet! credits left: " + WalletSingleton.getCredits(), Toast.LENGTH_SHORT).show();
        } else {
            Log.d("osto", "Not enough credits");
            Toast.makeText(this, "Not enough credits! You currently have: " + WalletSingleton.getCredits(), Toast.LENGTH_SHORT).show();
        }
    }


}
