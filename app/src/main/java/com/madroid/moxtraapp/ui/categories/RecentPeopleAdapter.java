package com.madroid.moxtraapp.ui.categories;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.madroid.moxtraapp.R;
import com.madroid.moxtraapp.dtos.binders.BindersResponseDTO;
import com.moxtra.binder.sdk.MXException;
import com.moxtra.sdk.MXChatManager;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by mohamed on 25/03/17.
 */

public class RecentPeopleAdapter extends RecyclerView.Adapter<RecentPeopleAdapter.MyViewHolder> implements MXChatManager.OnOpenChatListener{

    private static final String TAG = "OpenBinder";
    private final List<BindersResponseDTO.Binder> recentPeople;
    private final Context mContext;
    MXChatManager.OnOpenChatListener onOpenChatListener;

    public RecentPeopleAdapter(List<BindersResponseDTO.Binder> recentPeople, Context context) {
        this.recentPeople = recentPeople;
        this.mContext = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_people_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final BindersResponseDTO.Binder binder = recentPeople.get(position);
//        try {
//            URL url = new URL(binder.getSubBinder().getThumbnailUri());
//            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//            holder.profileImage.setImageBitmap(BitmapUtil.getBitmapFromURL(binder.getSubBinder().getThumbnailUri()));

//        }
//        catch(Exception e){
//
//        }

        if(hasPersonalImage(binder.getSubBinder().getThumbnailUri())){
            Picasso.with(mContext).load(binder.getSubBinder().getThumbnailUri()).into(holder.profileImage);
            holder.profileImage.setVisibility(View.VISIBLE);
            holder.profileImage.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {
                    openChat(binder.getSubBinder().getId());
                }
            });
        }
        else {
            TextDrawable drawable = TextDrawable.builder().beginConfig().fontSize(35).endConfig()
                    .buildRound(initials(binder.getSubBinder().getName()), mContext.getResources().getColor(R.color.light_gray));
            holder.profileText.setImageDrawable(drawable);
            holder.profileText.setVisibility(View.VISIBLE);
            holder.profileText.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {
                    openChat(binder.getSubBinder().getId());
                }
            });
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

    public String initials(String fullname){
        StringBuilder initials = new StringBuilder();
        for (String s : fullname.split(" ")) {
            initials.append(s.charAt(0));
        }
        return initials.toString().toUpperCase();
    }

    public void openChat(String binderId){
        try{
            MXChatManager.getInstance().openChat(binderId, onOpenChatListener);
        }
        catch (MXException.AccountManagerIsNotValid accountManagerIsNotValid){
            Log.e(TAG, "Error when creating MXAccountManager instance.", accountManagerIsNotValid);
        }

    }

    @Override
    public void onOpenChatSuccess() {
        Log.i(TAG, "Open chat success.");
    }

    @Override
    public void onOpenChatFailed(int i, String s) {
        Log.e(TAG, "Failed to open chat with code: " + i + ", msg: " + s);
        Toast.makeText(mContext, "Failed to open chat: " + s, Toast.LENGTH_LONG).show();
    }
}

