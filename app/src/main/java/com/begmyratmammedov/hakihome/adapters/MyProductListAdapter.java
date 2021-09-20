package com.begmyratmammedov.hakihome.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.begmyratmammedov.hakihome.R;
import com.begmyratmammedov.hakihome.model.ModelCategory;
import com.begmyratmammedov.hakihome.model.ModelProduct;
import com.bumptech.glide.Glide;

import java.util.List;

public class MyProductListAdapter extends RecyclerView.Adapter<MyProductListAdapter.ViewHolder> {

    private Activity context;
    private List<ModelProduct> list;
    private LayoutInflater mInflater;
    private ItemClickListenerProducts mClickListener;
    private int selectedItem;

    // data is passed into the constructor
    public MyProductListAdapter(Activity context, List<ModelProduct> list) {
        this.mInflater = LayoutInflater.from(context);
        this.list = list;
        this.context = context;
        this.selectedItem = 0;
    }

    public int getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(int selectedItem) {
        this.selectedItem = selectedItem;
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.box_item_products, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide
                .with(context)
                .load(list.get(position).getImgURL())
                .centerCrop()
                .placeholder(R.drawable.ic_heart)
                .into(holder.i_image);

    }

    // total number of rows
    @Override
    public int getItemCount() {
        if(list==null)
            return 0;
        return list.size();
    }

    public int dpToPx(int dp, Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView t_title, t_tag, t_price, t_detail;
        ImageView i_image;

        ViewHolder(View itemView) {
            super(itemView);

            t_title = itemView.findViewById(R.id.t_title);
            i_image = itemView.findViewById(R.id.i_image);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClickProducts(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public ModelProduct getItem(int id) {
        return list.get(id);
    }

    // allows clicks events to be caught
    public void setClickListenerProducts(ItemClickListenerProducts itemClickListener) {
        this.mClickListener = itemClickListener;

    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListenerProducts {
        void onItemClickProducts(View view, int position);
    }
}

