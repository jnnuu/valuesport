package com.example.valuesport;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                // tähän store activity (intent intent startPsykoosit())
               // Toast.makeText(this, "kauppaa klikattu", Toast.LENGTH_SHORT).show();
                toStore();
                return true;
            case R.id.wallet:
                // tähän wallet activity (intent intent startPsykoosit())
                Toast.makeText(this, "lompsaa klikattu", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.exit:
                // tähän exitti tai poista tämä case
                Toast.makeText(this, "poistumista yritetty :D", Toast.LENGTH_SHORT).show();
                return true;
            // tähän on helppo lisätä mitä mieleen tulee. R.id.store / R.id.wallet jne resurssit löytyvät
            // app/res/menu/mainmenu.xml
            // ton toastin voi poistaa myöhemmin, kun nappia painamalla aukeaa uusi activity.
            default:
                return false;
        }
    }
    public void toStore() {
        Intent intent = new Intent(this, BuyCouponActivity.class);
        startActivity(intent);
    }
}
