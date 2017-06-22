package com.madroid.moxtraapp.ui.binders_add;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hanks.library.AnimateCheckBox;
import com.madroid.moxtraapp.R;
import com.madroid.moxtraapp.dtos.binders.BindersResponseDTO;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by omarmakeen on 6/22/17.
 */

public class BindersAddAdapter extends RecyclerView.Adapter<BindersAddAdapter.MyViewHolder>{


    private final Context mContext;
    private final List<BindersResponseDTO.Binder> binders;

    public BindersAddAdapter(List<BindersResponseDTO.Binder> binders, Context context) {
        this.binders = binders;
        this.mContext = context;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.binders_add_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final BindersResponseDTO.Binder binder = binders.get(position);
        Picasso.with(mContext).load(binder.getSubBinder().getThumbnailUri()).into(holder.binderImage);
        holder.binderName.setText(binder.getSubBinder().getName());

    }

    public void openAddBindersActivity(){
        Intent intent = new Intent(mContext, BindersAddActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return (binders!=null)?binders.size():0;
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {

        public AnimateCheckBox addCheckbox;
        public RoundedImageView binderImage ;
        public TextView binderName;

        public MyViewHolder(View view) {
            super(view);
            addCheckbox = (AnimateCheckBox) view.findViewById(R.id.add_binder_checkbox);
            binderImage = (RoundedImageView) view.findViewById(R.id.binder_image);
            binderName = (TextView) view.findViewById(R.id.binder_name);
        }

    }
}

