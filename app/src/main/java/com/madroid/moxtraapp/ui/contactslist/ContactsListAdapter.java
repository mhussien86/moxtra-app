//package com.madroid.moxtraapp.ui.contactslist;
//
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.madroid.moxtraapp.R;
//import com.madroid.moxtraapp.dtos.LoginResponseDTO;
//
//import java.util.ArrayList;
//
//import butterknife.Bind;
//import butterknife.ButterKnife;
//
///**
// * Created by mohamed on 16/01/17.
// */
//public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.ContactViewHolder> {
//
//    private final OnItemClickListener listener;
//
//    private ArrayList<LoginResponseDTO.Contact> contactsList;
//
//    public interface OnItemClickListener {
//        void onItemClick(LoginResponseDTO.Contact contact);
//    }
//
//    public ContactsListAdapter(ArrayList<LoginResponseDTO.Contact> contactsList, OnItemClickListener onItemClickListener) {
//
//        this.listener = onItemClickListener;
//        this.contactsList = contactsList;
//    }
//
//
//    @Override
//    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_item, parent, false);
//
//        return new ContactViewHolder(listItem);
//    }
//
//    @Override
//    public void onBindViewHolder(ContactViewHolder holder, int position) {
//
//        holder.bind(this.contactsList.get(position), listener);
//        LoginResponseDTO.Contact contact = this.contactsList.get(position);
//        holder.contactName.setText(contact.getFirstName() + " " + contact.getLastName());
//        holder.contactMail.setText("" + contact.getEmail());
//    }
//
//    @Override
//    public int getItemCount() {
//        return this.contactsList.size();
//    }
//
//
//    public class ContactViewHolder extends RecyclerView.ViewHolder {
//
//        @Bind(R.id.contactName)
//        TextView contactName;
//
//        @Bind(R.id.contactMail)
//        TextView contactMail;
//
//        public ContactViewHolder(View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//        }
//
//        public void bind(final LoginResponseDTO.Contact contact, final ContactsListAdapter.OnItemClickListener listener) {
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    listener.onItemClick(contact);
//                }
//            });
//        }
//    }
//
//}
