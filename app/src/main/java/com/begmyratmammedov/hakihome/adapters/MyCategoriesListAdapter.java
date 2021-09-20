package com.begmyratmammedov.hakihome.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.begmyratmammedov.hakihome.R;
import com.begmyratmammedov.hakihome.model.ModelCategory;

import java.util.List;

public class MyCategoriesListAdapter extends RecyclerView.Adapter<MyCategoriesListAdapter.ViewHolder> {

    private Activity context;
    private List<ModelCategory> list;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private int selectedItem;

    // data is passed into the constructor
    public MyCategoriesListAdapter(Activity context, List<ModelCategory> list) {
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
        View view = mInflater.inflate(R.layout.list_item_categories, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.t_title.setText(list.get(position).getTitle());
        if(selectedItem == position){
            holder.t_title.getBackground().setTint(context.getResources().getColor(R.color.colorTextPrimary));
            holder.t_title.setTextColor(context.getResources().getColor(R.color.white));
        }
        else{
            holder.t_title.getBackground().setTint(context.getResources().getColor(R.color.colorBackground));
            holder.t_title.setTextColor(context.getResources().getColor(R.color.colorTextPrimary));
        }
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

        TextView t_title;

        ViewHolder(View itemView) {
            super(itemView);

            t_title = itemView.findViewById(R.id.t_title);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public ModelCategory getItem(int id) {
        return list.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;

    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

