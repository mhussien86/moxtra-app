package com.madroid.moxtraapp.ui.contactslist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.madroid.moxtraapp.AppConstants;
import com.madroid.moxtraapp.BaseActivity;
import com.madroid.moxtraapp.BaseFragment;
import com.madroid.moxtraapp.R;
import com.madroid.moxtraapp.dtos.LoginResponseDTO;
import com.madroid.moxtraapp.ui.contactdetails.ContactDetailsActivity;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

/**
 * Created by mohamed on 16/01/17.
 */
public class ContactsListFragment extends BaseFragment {


    @Bind(R.id.recycle_view)
    RecyclerView recyclerView;

    private SectionedRecyclerViewAdapter sectionAdapter;

    LoginResponseDTO responseDTO;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_contact_list, container, false);

        ButterKnife.bind(this, rootView);
        setUpToolBar("Contacts");
        try {
            Bundle b = getActivity().getIntent().getBundleExtra("bundle");
            responseDTO = Parcels.unwrap(b.getParcelable(AppConstants.LOGIN_RESPONSE));
        } catch (Exception e) {

        }
        sectionAdapter = new SectionedRecyclerViewAdapter();

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);

        for(char alphabet = 'A'; alphabet <= 'Z';alphabet++) {
            List<LoginResponseDTO.Contact> contacts = getContactsWithLetter(alphabet);

            if (contacts.size() > 0) {
                sectionAdapter.addSection(new ContactsSection((ArrayList<LoginResponseDTO.Contact>) contacts, String.valueOf(alphabet), new ContactsSection.OnItemClickListener() {
                    @Override
                    public void onItemClick(LoginResponseDTO.Contact contact) {
                        Intent intent = new Intent(getActivity(), ContactDetailsActivity.class);
                        intent.putExtra("data", Parcels.wrap(contact));
                        startActivity(intent);
                    }
                }));
            }
        }

        recyclerView.setAdapter(sectionAdapter);

    }

    private List<LoginResponseDTO.Contact> getContactsWithLetter(char letter) {
        List<LoginResponseDTO.Contact> contacts = new ArrayList<>();

        for (LoginResponseDTO.Contact contact : responseDTO.getResponse().getUserData().getContacts()) {
                if (contact.getFirstName().charAt(0) == letter) {
                    contacts.add(contact);
                }
        }

        return contacts;
    }
    private void setUpToolBar(String pageTitle){
        //add the Toolbar

        LayoutInflater mInflater=LayoutInflater.from(getActivity());
        View mCustomView = mInflater.inflate(R.layout.categories_toolbar, null);
        toolbar.addView(mCustomView);
        TextView title =((TextView)toolbar.findViewById(R.id.title));
        title.setText(pageTitle);

        ImageView add = (ImageView)toolbar.findViewById(R.id.icon_add);
        add.setVisibility(View.INVISIBLE);

        ((BaseActivity)getActivity()).setSupportActionBar(toolbar);
        ((BaseActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((BaseActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
