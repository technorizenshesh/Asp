package com.my.asp.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.my.asp.R;
import com.my.asp.model.ProductModel;
import com.my.asp.model.ProductModels;


import java.util.ArrayList;


public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private ArrayList<ProductModels.Result> modelList;
    private OnItemClickListener mItemClickListener;


    public ProductAdapter(Context context, ArrayList<ProductModels.Result> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<ProductModels.Result> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_product, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final ProductModels.Result model = getItem(position);
            final ViewHolder genericViewHolder = (ViewHolder) holder;

            genericViewHolder.txtName.setText(model.getProductName());

            Glide.with(mContext)
                    .load(model.getImage())
                    .placeholder(R.drawable.loader)
                    .into(genericViewHolder.img);
           // genericViewHolder.img.setImageResource(model.getImg());

        }
    }

    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private ProductModels.Result getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {

        void onItemClick(View view, int position, ProductModels.Result model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName;
        ImageView img;

        public ViewHolder(final View itemView) {
            super(itemView);
            txtName=itemView.findViewById(R.id.txtName);
            img=itemView.findViewById(R.id.img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));
                }
            });
        }
    }


}

