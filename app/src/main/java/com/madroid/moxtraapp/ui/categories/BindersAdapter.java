package com.madroid.moxtraapp.ui.categories;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.madroid.moxtraapp.R;
import com.madroid.moxtraapp.dtos.binders.BindersResponseDTO;
import com.makeramen.roundedimageview.RoundedImageView;
import com.moxtra.binder.sdk.MXException;
import com.moxtra.sdk.MXChatManager;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by mohamed on 25/03/17.
 */

public class BindersAdapter extends RecyclerView.Adapter<BindersAdapter.MyViewHolder> implements MXChatManager.OnOpenChatListener{


    private static final String TAG = "OpenBinder";
    private final Context mContext;
    private final List<BindersResponseDTO.Binder> binders;
    MXChatManager.OnOpenChatListener onOpenChatListener;


    public BindersAdapter(List<BindersResponseDTO.Binder> binders, Context context) {
        this.binders = binders;
        this.mContext = context;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_binders_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final BindersResponseDTO.Binder binder = binders.get(position);
        Picasso.with(mContext).load(binder.getSubBinder().getThumbnailUri()).into(holder.binderImage);
        holder.binderImage.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                openChat(binder.getSubBinder().getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return (binders!=null)?binders.size():0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public RoundedImageView binderImage;
        public MyViewHolder(View view) {
            super(view);
            binderImage = (RoundedImageView) view.findViewById(R.id.binder_image);
        }

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
