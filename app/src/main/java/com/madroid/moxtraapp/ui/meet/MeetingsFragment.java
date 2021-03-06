package com.madroid.moxtraapp.ui.meet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.madroid.moxtraapp.BaseActivity;
import com.madroid.moxtraapp.BaseFragment;
import com.madroid.moxtraapp.R;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarListener;

/**
 * Created by mohamed on 25/03/17.
 */

public class MeetingsFragment extends BaseFragment {

    View view ;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.meetings_fragment, container, false);

        ButterKnife.bind(this,view);
        setUpToolBar("Meetings");
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initCalender();
    }

    private void initCalender() {
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        /** start before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        View calView = view.findViewById(R.id.calendarView);
        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(getActivity(), calView.getId())
                .startDate(startDate.getTime())
                .endDate(endDate.getTime())
                .build();
        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Date date, int position) {
                //do something
            }
        });
    }


    String TAG = "MXChatManager" ;
    @OnClick({R.id.meet_now,R.id.join_meet})
    public void meetNowButton(View view){

        if(view.getId()== R.id.meet_now) {
            Intent intent = new Intent(new Intent(getActivity(), MeetingsContainerActivity.class));
            intent.putExtra("data","start");
            startActivity(intent);

        }else if(view.getId()==R.id.join_meet){
            Intent intent = new Intent(new Intent(getActivity(), MeetingsContainerActivity.class));
            intent.putExtra("data","join");
            startActivity(intent);
        }
    }

    private void setUpToolBar(String pageTitle){
        //add the Toolbar
        //Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);

        LayoutInflater mInflater=LayoutInflater.from(getActivity());
        View mCustomView = mInflater.inflate(R.layout.categories_toolbar, null);
        toolbar.addView(mCustomView);
        TextView title =((TextView)toolbar.findViewById(R.id.title));
        title.setText(pageTitle);
        ((ImageView) toolbar.findViewById(R.id.icon_add)).setVisibility(View.INVISIBLE);
        ((BaseActivity)getActivity()).setSupportActionBar(toolbar);
        ((BaseActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((BaseActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

    }
}
