package com.my.asp.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.my.asp.R;
import com.my.asp.model.ProductDetailsModels;
import com.my.asp.model.ProductModel;
import com.my.asp.model.ProductModels;

import java.util.ArrayList;


public class ProductAdapterDetails extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private ArrayList<ProductDetailsModels.Result.PartsDetail> modelList;
    private OnItemClickListener mItemClickListener;

    public ProductAdapterDetails(Context context, ArrayList<ProductDetailsModels.Result.PartsDetail> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<ProductDetailsModels.Result.PartsDetail> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_product_details, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final ProductDetailsModels.Result.PartsDetail model = getItem(position);
            final ViewHolder genericViewHolder = (ViewHolder) holder;

            genericViewHolder.txtName.setText(model.getPartsName());

    /*        genericViewHolder.RRview.setOnClickListener(v -> {

                if(model.isView())
                {
                    model.setView(false);
                    genericViewHolder.llView.setVisibility(View.GONE);

                }else
                {
                    model.setView(true);
                    genericViewHolder.llView.setVisibility(View.VISIBLE);
                }
            });*/

        }
    }

    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private ProductDetailsModels.Result.PartsDetail getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {

        void onItemClick(View view, int position, ProductDetailsModels.Result.PartsDetail model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName;
        RelativeLayout RRview;
        LinearLayout llView;

        public ViewHolder(final View itemView) {
            super(itemView);
            txtName=itemView.findViewById(R.id.txtName);
            RRview=itemView.findViewById(R.id.RRview);
            llView=itemView.findViewById(R.id.llView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));
                }
            });
        }
    }


}

