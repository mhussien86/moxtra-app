package com.madroid.moxtraapp.ui.contactslist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.madroid.moxtraapp.R;
import com.madroid.moxtraapp.dtos.LoginResponseDTO;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * Created by mohamed on 30/05/17.
 */

public class ContactsSection extends StatelessSection {

    String title;

    private final OnItemClickListener listener;

    private ArrayList<LoginResponseDTO.Contact> contactsList;

    public interface OnItemClickListener {
        void onItemClick(LoginResponseDTO.Contact contact);
    }

    public ContactsSection(ArrayList<LoginResponseDTO.Contact> contactsList, String title, OnItemClickListener onItemClickListener) {
        super(R.layout.section_ex1_header, R.layout.contact_list_item);
        this.listener = onItemClickListener;
        this.contactsList = contactsList;
        this.title = title;
    }

    @Override
    public int getContentItemsTotal() {
        return contactsList.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ItemViewHolder itemHolder = (ItemViewHolder) holder;

        itemHolder.bind(this.contactsList.get(position), listener);
        LoginResponseDTO.Contact contact = this.contactsList.get(position);
        itemHolder.contactName.setText(contact.getFirstName() + " " + contact.getLastName());
        itemHolder.contactMail.setText("" + contact.getEmail());
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

        headerHolder.tvTitle.setText(title);
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;

        public HeaderViewHolder(View view) {
            super(view);

            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.contactName)
        TextView contactName;

        @Bind(R.id.contactMail)
        TextView contactMail;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final LoginResponseDTO.Contact contact, final OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(contact);
                }
            });
        }
    }
}
