package com.example.valuesport;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * RecyclerViewAdapter is used to display a list of objects
 *
 * @author Jonni Uusi-Hakimo
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private final ArrayList<Coupon> mCoupons;

    private final Context mContext;

    /**
     * RecyclerViewAdapter is used to display objects and pictures correctly as lists in BuyCouponActivity and WalletActivity.
     *
     * @param context current context
     * @param coupons list Coupon objects as ArrayList
     */

    public RecyclerViewAdapter(Context context, ArrayList<Coupon> coupons) {

        this.mContext = context;
        this.mCoupons = coupons;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_one_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Log.e(TAG, "onBindViewHolder: called");


        //Glide is used to fetch images from the internet.

        Glide.with(mContext)
                .asBitmap()
                .load(mCoupons.get(i).getCouponImageUrl())
                .into(viewHolder.viewImage);

        //Check if current activity is an instance of BuyCouponActivity or WalletActivity
        viewHolder.viewTitle.setText(mCoupons.get(i).getCouponTitle());
        if (mContext instanceof BuyCouponActivity) {
            viewHolder.viewPrice.setText(String.format("%s kredittiä", String.valueOf(mCoupons.get(i).getCouponPrice())));
        } else if (mContext instanceof WalletActivity) {
            viewHolder.viewPrice.setText("");
        }

        //Decides which activity is launched next based on the current activity mContext.
        viewHolder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mContext instanceof BuyCouponActivity) {

                    Intent nextActivity = new Intent(mContext, BuyDialogActivity.class);
                    nextActivity.putExtra("index", i);
                    mContext.startActivity(nextActivity);

                } else if (mContext instanceof WalletActivity) {
                    Intent nextActivity = new Intent(mContext, UseCouponActivity.class);
                    nextActivity.putExtra("index", i);
                    mContext.startActivity(nextActivity);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCoupons.size();
    }

    /**
     * ViewHolder
     * Adds relevant views for a single Coupon shown in a list of coupons.
     * CircleImageView uses an external library and it's used to round the corners of an image.
     * Price and title are shown as normal TextView's.
     * RelativeLayout is used, and it uses layout_one_item.xml file to display the said coupons data
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        final CircleImageView viewImage;
        final TextView viewTitle;
        final TextView viewPrice;
        final RelativeLayout itemLayout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            viewImage = itemView.findViewById(R.id.image);
            viewTitle = itemView.findViewById(R.id.title);
            viewPrice = itemView.findViewById(R.id.price);
            itemLayout = itemView.findViewById(R.id.item_layout);
        }
    }
}
