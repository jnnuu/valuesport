package com.example.valuesport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class BuyCouponActivity extends AppCompatActivity {

    private static final String TAG = "BuyCouponActivity";

    private ArrayList<String> tuoteKuvaus = new ArrayList<>();
    private ArrayList<String> kuva = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_coupon);
        Log.d(TAG, "oncreate started @buycouponactivity");

        TuoteKatalogi();
    }

    private void TuoteKatalogi(){
        kuva.add("https://is1-ssl.mzstatic.com/image/thumb/Purple114/v4/17/3a/8f/173a8fe5-bcb5-3d4c-8769-15cb646b8e3d/source/60x60bb.jpg");
        tuoteKuvaus.add("Euron juusto hesasta 0,50€");
        kuva.add("https://is1-ssl.mzstatic.com/image/thumb/Purple114/v4/17/3a/8f/173a8fe5-bcb5-3d4c-8769-15cb646b8e3d/source/60x60bb.jpg");
        tuoteKuvaus.add("Euron juusto hesasta 0,60€");
        kuva.add("https://is1-ssl.mzstatic.com/image/thumb/Purple114/v4/17/3a/8f/173a8fe5-bcb5-3d4c-8769-15cb646b8e3d/source/60x60bb.jpg");
        tuoteKuvaus.add("Euron juusto hesasta 0,70€");
        kuva.add("https://is1-ssl.mzstatic.com/image/thumb/Purple114/v4/17/3a/8f/173a8fe5-bcb5-3d4c-8769-15cb646b8e3d/source/60x60bb.jpg");
        tuoteKuvaus.add("Euron juusto hesasta 0,80€");
        kuva.add("https://is1-ssl.mzstatic.com/image/thumb/Purple114/v4/17/3a/8f/173a8fe5-bcb5-3d4c-8769-15cb646b8e3d/source/60x60bb.jpg");
        tuoteKuvaus.add("Euron juusto hesasta 2,50€ (plus limu)");
        kuva.add("https://pbs.twimg.com/profile_images/2725100467/41c75598637053c11b75c05d8309650f_400x400.png");
        tuoteKuvaus.add("makkarat -50% lähikaupasta");
        kuva.add("https://wmgk.com/wp-content/uploads/sites/2/2018/01/Ikea-Logo.jpg");
        tuoteKuvaus.add("Jos liikuntasuorituksesi päättyy IKEAAN, yli 150e ostoksissa ilmainen kotiinkuljetus! tartu tarjoukseen");
        kuva.add("https://abordage.bg/english/wp-content/uploads/2017/10/Sodexo-logo.jpg");
        tuoteKuvaus.add("Lounaskahvi 0,55 €");
        LataaKatalogi();
    }

    private void LataaKatalogi(){
        RecyclerView recyclerView = findViewById(R.id.store);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, tuoteKuvaus, kuva);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
