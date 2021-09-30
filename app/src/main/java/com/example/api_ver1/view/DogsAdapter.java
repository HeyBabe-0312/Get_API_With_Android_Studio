package com.example.api_ver1.view;

import android.content.Context;
import com.bumptech.glide.Glide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.api_ver1.R;
import com.example.api_ver1.model.DogBreed;

import java.util.ArrayList;

public class DogsAdapter extends BaseAdapter {

    private ArrayList<DogBreed> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public DogsAdapter (Context aContext , ArrayList<DogBreed> list) {
        this.listData = list ;
        this.context  = aContext ;
        this.layoutInflater = LayoutInflater.from(aContext);
    }
    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_grid_view, null);
            holder = new ViewHolder();
            holder.dogView= (ImageView) convertView.findViewById(R.id.img_ava);
            holder.tym= (ImageView) convertView.findViewById(R.id.img_like);
            holder.dogNameView = (TextView) convertView.findViewById(R.id.txt_Name);
            holder.descriptionView = (TextView) convertView.findViewById(R.id.txt_chuthich);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        DogBreed dog  = this.listData.get(position);
        holder.dogNameView.setText(dog.getName());
        holder.descriptionView.setText(dog.getBredFor());

        Glide.with(convertView).load(dog.getUrl()).into(holder.dogView);
        if(dog.getTym()) holder.tym.setImageResource(R.drawable.ic_baseline_favorite_true);
        else holder.tym.setImageResource(R.drawable.ic_baseline_favorite_24);
        return convertView;
    }
    static class ViewHolder {
        ImageView dogView;
        ImageView tym;
        TextView dogNameView ;
        TextView descriptionView ;
    }
}
