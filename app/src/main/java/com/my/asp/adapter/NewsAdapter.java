package com.my.asp.adapter;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.my.asp.R;
import com.my.asp.act.ProductAddWeb;
import com.my.asp.model.NewsModels;
import com.my.asp.model.ProductModel;

import java.util.ArrayList;


public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private ArrayList<NewsModels.Result> modelList;
    private OnItemClickListener mItemClickListener;


    public NewsAdapter(Context context, ArrayList<NewsModels.Result> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<NewsModels.Result> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_news, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final NewsModels.Result model = getItem(position);
            final ViewHolder genericViewHolder = (ViewHolder) holder;

            genericViewHolder.txtName.setText(model.getDescription());

            Glide.with(mContext)
                    .load(model.getNewsImage())
                    .into(genericViewHolder.img);

            genericViewHolder.imgFb.setOnClickListener(v -> {
               // mContext.startActivity(new Intent(mContext, ProductAddWeb.class).putExtra("Url","https://www.facebook.com/atlantic.com.pt"));
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.facebook.com/atlantic.com.pt"));
                mContext.startActivity(intent);

            });

            genericViewHolder.imginsta.setOnClickListener(v -> {
               // mContext.startActivity(new Intent(mContext, ProductAddWeb.class).putExtra("Url","https://www.instagram.com/accounts/login/?next=/atlanticspareparts/"));

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.instagram.com/accounts/login/?next=/atlanticspareparts/"));
                mContext.startActivity(intent);

            });

            genericViewHolder.imgYoutube.setOnClickListener(v -> {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.youtube.com/channel/UCXG4h0v3lT0lgeAr3g7xctQ"));
                mContext.startActivity(intent);
                //mContext.startActivity(new Intent(mContext, ProductAddWeb.class).putExtra("Url","https://www.youtube.com/channel/UCXG4h0v3lT0lgeAr3g7xctQ"));

            });

            genericViewHolder.imgLinkDin.setOnClickListener(v -> {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.linkedin.com/company/asp---atlantic-spare-parts/"));
                mContext.startActivity(intent);

                //mContext.startActivity(new Intent(mContext, ProductAddWeb.class).putExtra("Url","https://www.linkedin.com/company/asp---atlantic-spare-parts/"));
            });

            genericViewHolder.txtMore.setOnClickListener(v -> {
              //  mContext.startActivity(new Intent(mContext, ProductAddWeb.class).putExtra("Url","https://www.atlantic.com.pt/grupo-atlantic-celebra-dia-internacional-da-mulher/"));
                mContext.startActivity(new Intent(mContext, ProductAddWeb.class).putExtra("Url",model.getNewsLink()));
            });
        }
    }

    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private NewsModels.Result getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {

        void onItemClick(View view, int position, NewsModels.Result model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName;
        TextView txtMore;
        ImageView img;
        ImageView imgFb;
        ImageView imginsta;
        ImageView imgYoutube;
        ImageView imgLinkDin;

        public ViewHolder(final View itemView) {
            super(itemView);
            txtName=itemView.findViewById(R.id.txtName);
            txtMore=itemView.findViewById(R.id.txtMore);
            img=itemView.findViewById(R.id.img);
            imgFb=itemView.findViewById(R.id.imgFb);
            imginsta=itemView.findViewById(R.id.imginsta);
            imgYoutube=itemView.findViewById(R.id.imgYoutube);
            imgLinkDin=itemView.findViewById(R.id.imgLinkDin);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));
                }
            });
        }
    }


}

