package com.example.handthishomathon.main.destinations;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.handthishomathon.R;
import com.example.handthishomathon.RestaurantModel;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantVH> {

    private List<RestaurantModel> mRestaurantList;
    private OnItemClickListener mOnItemClickListener;

    public RestaurantAdapter(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void addRestaurant (List<RestaurantModel> mRestaurantList){
        this.mRestaurantList = mRestaurantList;
    }

    @NonNull
    @Override
    public RestaurantVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restaurant_item, parent, false);
        return new RestaurantVH(view, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantVH holder, int position) {
        RestaurantModel restaurantModel = mRestaurantList.get(position);
        holder.rAddress.setText(restaurantModel.getRestaurantAddress());
        holder.rName.setText(restaurantModel.getRestaurantName());
        holder.rLogo.setImageResource(restaurantModel.getRestaurantLogo());

    }

    @Override
    public int getItemCount() {
        if (mRestaurantList.size() == 0){
            return 0;
        }else {
            return mRestaurantList.size();
        }
    }

    class RestaurantVH extends RecyclerView.ViewHolder {

        ImageView rLogo;
        TextView rName, rAddress;
        OnItemClickListener mOnItemClickListener;

        public RestaurantVH(@NonNull View itemView, OnItemClickListener mOnItemClickListener) {
            super(itemView);
            rLogo = itemView.findViewById(R.id.iv_restaurant_logo);
            rName = itemView.findViewById(R.id.tv_restaurant_name);
            rAddress = itemView.findViewById(R.id.tv_restaurant_address);
            this.mOnItemClickListener = mOnItemClickListener;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(v,getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
}
