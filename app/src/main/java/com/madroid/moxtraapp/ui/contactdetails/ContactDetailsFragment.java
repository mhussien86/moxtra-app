package com.madroid.moxtraapp.ui.contactdetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.madroid.moxtraapp.BaseFragment;
import com.madroid.moxtraapp.R;
import com.madroid.moxtraapp.dtos.LoginResponseDTO;
import com.moxtra.binder.sdk.MXException;
import com.moxtra.sdk.MXChatManager;
import com.moxtra.sdk.MXSDKException;

import org.parceler.Parcels;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by mohamed on 25/02/17.
 */

public class ContactDetailsFragment extends BaseFragment {

    @Bind(R.id.contact_name)
    TextView contactName;

    @Bind(R.id.contact_email)
    TextView contactEmail;

    @Bind(R.id.contact_phone)
    TextView contactPhone;

    @Bind(R.id.chat_button)
    Button chatButton;

    @Bind(R.id.meet_button)
    Button meetButton;

    LoginResponseDTO.Contact contact;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.contact_details_fragment, container, false);

        ButterKnife.bind(this, view);
        try {
            contact = Parcels.unwrap(getActivity().getIntent().getParcelableExtra("data"));
        } catch (Exception e) {

        }
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        contactEmail.setText(contact.getEmail());
        contactName.setText(contact.getFirstName() + contact.getLastName());
        contactPhone.setText(contact.getPhone());

    }

    @OnClick({R.id.chat_button, R.id.meet_button})
    public void onButtonClicked(View view) {

        if (view.getId() == R.id.meet_button) {
            try {
                MXChatManager.getInstance().startMeet("degwy" + "'s meet", null,
                        Arrays.asList(new String[]{contact.email}), new MXChatManager.OnStartMeetListener() {
                            @Override
                            public void onStartMeetDone(String meetId, String meetUrl) {
                                Log.d("Create", "Meet started: " + meetId);
                            }

                            @Override
                            public void onStartMeetFailed(int i, String s) {
                                Log.e("Create", "onStartMeetFailed: " + s);
                            }
                        });
            } catch (MXSDKException.Unauthorized unauthorized) {
                Log.e("Create", "Error when start meet", unauthorized);
            } catch (MXSDKException.MeetIsInProgress meetIsInProgress) {
                Log.e("Create", "Error when start meet", meetIsInProgress);
            }

        } else if (view.getId() == R.id.chat_button) {

            try {
                MXChatManager.getInstance().createChat("Hello", Arrays.asList(new String[]{contact.email}), new MXChatManager.OnCreateChatListener() {
                    @Override
                    public void onCreateChatSuccess(String binderId) {
                        Log.i("Create", "Create Chat Success. The binderId = " + binderId);
                    }

                    @Override
                    public void onCreateChatFailed(int i, String s) {
                        Log.e("create", "Failed to create chat with code: " + i + ", msg: " + s);
                        Toast.makeText(getActivity(), "Failed to create chat: " + s, Toast.LENGTH_LONG).show();
                    }
                });

            } catch (MXException.AccountManagerIsNotValid accountManagerIsNotValid) {

            }

        }

    }

}
