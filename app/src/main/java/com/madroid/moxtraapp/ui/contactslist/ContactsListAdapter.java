package com.madroid.moxtraapp.ui.contactslist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mohamed on 16/01/17.
 */
public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.ContactViewHolder>{





    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }




    public class ContactViewHolder extends RecyclerView.ViewHolder{


        public ContactViewHolder(View itemView) {
            super(itemView);
        }
    }
}
