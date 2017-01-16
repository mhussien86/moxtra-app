package com.madroid.moxtraapp.ui.contactslist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.madroid.moxtraapp.BaseFragment;
import com.madroid.moxtraapp.R;
import com.madroid.moxtraapp.dtos.LoginResponseDTO;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mohamed on 16/01/17.
 */
public class ContactsListFragment extends BaseFragment{


    @Bind(R.id.recycle_view)
    RecyclerView recyclerView ;

    RecyclerView.Adapter contactsListAdapter ;


//    ArrayList<LoginResponseDTO.Contact> contactList ;

    LoginResponseDTO responseDTO ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_contact_list,container,false);

        ButterKnife.bind(this,rootView);
        try{
            responseDTO = ((LoginResponseDTO)Parcels.unwrap(getActivity().getIntent().getParcelableExtra("data")));
        }catch(Exception e){

        }

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);

        contactsListAdapter = new ContactsListAdapter((ArrayList<LoginResponseDTO.Contact>) responseDTO.getResponse().getUserData().getContacts(), new ContactsListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(LoginResponseDTO.Contact contact) {


            }
        });

        recyclerView.setAdapter(contactsListAdapter);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
