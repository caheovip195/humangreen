package com.app.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.app.Models.TenHang;
import com.app.humangreen.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ThanhCong on 23/4/2018.
 */

public class AdapterFragment_Buys extends RecyclerView.Adapter<AdapterFragment_Buys.ViewHolder>{

    List<TenHang>dshang;
    Context context;
    public AdapterFragment_Buys(List<TenHang>dshang,Context context){
        this.dshang=dshang;
        this.context=context;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.custom_list_main,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(context).load(dshang.get(position).getHinhanh()).into(holder.img);
        holder.txtdongia.setText(dshang.get(position).getDongia());
        holder.txtdonvi.setText(dshang.get(position).getDonvi());
    }

    @Override
    public int getItemCount() {
        return dshang.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView txtdongia,txtdonvi;
        public ViewHolder(View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img_mainbuys);
            txtdongia=itemView.findViewById(R.id.txtbuys_cost);
            txtdonvi=itemView.findViewById(R.id.txtbuys_unit);
        }
    }
}
