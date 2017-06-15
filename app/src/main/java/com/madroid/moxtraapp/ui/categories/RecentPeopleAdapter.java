package com.madroid.moxtraapp.ui.categories;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.madroid.moxtraapp.R;
import com.madroid.moxtraapp.dtos.binders.BindersResponseDTO;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by mohamed on 25/03/17.
 */

public class RecentPeopleAdapter extends RecyclerView.Adapter<RecentPeopleAdapter.MyViewHolder>{


    private final List<BindersResponseDTO.Binder> recentPeople;
    private final Context mContext;
    public RecentPeopleAdapter(List<BindersResponseDTO.Binder> recentPeople, Context context) {
        this.recentPeople = recentPeople;
        this.mContext = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_people_item_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        BindersResponseDTO.Binder binder = recentPeople.get(position);
//        try {
//            URL url = new URL(binder.getSubBinder().getThumbnailUri());
//            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//            holder.profileImage.setImageBitmap(BitmapUtil.getBitmapFromURL(binder.getSubBinder().getThumbnailUri()));

//        }
//        catch(Exception e){
//
//        }

//        Picasso.with(mContext).load(binder.getSubBinder().getThumbnailUri()).into(holder.profileImage);
//        TextDrawable drawable = TextDrawable.builder()
//                .buildRound("A", ResourcesCompat.getColor(mContext.getResources(), R.color.oldlavender, null));

        if(hasPersonalImage(binder.getSubBinder().getThumbnailUri())){
            Picasso.with(mContext).load(binder.getSubBinder().getThumbnailUri()).into(holder.profileImage);
            holder.profileImage.setVisibility(View.VISIBLE);
//            holder.profileText.setVisibility(View.GONE);
        }
        else {
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound("A", Color.GRAY);
            holder.profileText.setImageDrawable(drawable);
//            holder.profileImage.setVisibility(View.GONE);
            holder.profileText.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return (recentPeople!=null)?recentPeople.size():0;
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {

        public CircleImageView profileImage;
        public ImageView profileText;

        public MyViewHolder(View view) {
            super(view);
            profileImage = (CircleImageView) view.findViewById(R.id.profile_image);
            profileText = (ImageView) view.findViewById(R.id.profile_text);

        }
    }

    public boolean hasPersonalImage(String url){

        if(url.contains("default"))
            return false;
        return true;

    }
}
