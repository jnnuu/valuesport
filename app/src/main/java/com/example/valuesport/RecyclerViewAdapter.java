package com.example.valuesport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    WalletSingleton walletSingleton = WalletSingleton.getInstance();

    private ArrayList<Coupon> mCoupons;

    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<Coupon> coupons) {

        this.mContext = context;
        this.mCoupons = coupons;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_one_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Log.e(TAG, "onBindViewHolder: called");


        Glide.with(mContext)
                .asBitmap()
                .load(mCoupons.get(i).getCouponImageUrl())
                .into(viewHolder.viewImage);

        viewHolder.viewTitle.setText(mCoupons.get(i).getCouponTitle());
        if (mContext instanceof BuyCouponActivity) {
            viewHolder.viewPrice.setText(String.valueOf(mCoupons.get(i).getCouponPrice()) + " kredittiä");
        } else if (mContext instanceof WalletActivity) {
            viewHolder.viewPrice.setText("");
        }


        viewHolder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick: clicked on: " + mCoupons.get(i).getCouponTitle());
                //Toast.makeText(mContext, "Item clicked", Toast.LENGTH_SHORT).show();
                Log.d("Debug", String.valueOf(mContext));

                if (mContext instanceof BuyCouponActivity) {
                    if (WalletSingleton.getCredits() >= mCoupons.get(i).getCouponPrice()){
                        Log.d(TAG, String.valueOf(WalletSingleton.getCredits()));
                        walletSingleton.addCouponToWallet(mCoupons.get(i));
                        walletSingleton.useCredits(mCoupons.get(i).getCouponPrice());
                        Log.d(TAG, String.valueOf(WalletSingleton.getCredits()));
                        Toast.makeText(mContext, "Item succesfully added to wallet! credits left: " + WalletSingleton.getCredits(), Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d(TAG, "Not enough credits");
                        Toast.makeText(mContext, "Not enough credits! You currently have: " + WalletSingleton.getCredits(), Toast.LENGTH_SHORT).show();
                    }
//                    Log.d(TAG, String.valueOf(WalletSingleton.getCredits()));
//                    walletSingleton.addCouponToWallet(mCoupons.get(i));
//                    walletSingleton.useCredits(mCoupons.get(i).getCouponPrice());
//                    Log.d(TAG, "kauppa");
//                    Log.d(TAG, String.valueOf(WalletSingleton.getCredits()));
                } else if (mContext instanceof WalletActivity) {
                    Log.d(TAG, "lompsa");
                }
                // tästä käyntiin activity jossa hyväksytään kupongin osto


            }
        });
    }

    @Override
    public int getItemCount() {
        return mCoupons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView viewImage;
        TextView viewTitle;
        TextView viewPrice;
        RelativeLayout itemLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            viewImage = itemView.findViewById(R.id.image);
            viewTitle = itemView.findViewById(R.id.title);
            viewPrice = itemView.findViewById(R.id.price);
            itemLayout = itemView.findViewById(R.id.item_layout);
        }
    }
}
