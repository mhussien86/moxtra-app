package com.madroid.moxtraapp.ui.timeline;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.madroid.moxtraapp.R;
import com.moxtra.sdk.MXChatManager;
import com.moxtra.sdk.MXGroupChatSession;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mohamed on 16/01/17.
 */
public class TimelineListAdapter extends RecyclerView.Adapter<TimelineListAdapter.ContactViewHolder>{

    private final OnItemClickListener listener;

    private List<MXGroupChatSession> sessions ;

    public interface OnItemClickListener {
        void onItemClick(MXGroupChatSession session);
    }

    public TimelineListAdapter(List<MXGroupChatSession> sessions, OnItemClickListener onItemClickListener){

        this.listener = onItemClickListener ;
        this.sessions = sessions ;
    }



    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_item,parent,false);

        return new ContactViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {

        holder.bind(sessions.get(position), listener);
        MXGroupChatSession session  = sessions.get(position);
        holder.contactName.setText(session.getTopic());
        holder.contactMail.setText(""+session.getLastFeedContent());
    }

    @Override
    public int getItemCount() {
        return sessions.size();
    }




    public class ContactViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.contactName)
        TextView contactName ;

        @Bind(R.id.contactMail)
        TextView contactMail ;

        public ContactViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bind(final MXGroupChatSession session, final TimelineListAdapter.OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(session);
                }
            });
        }
    }

    private void sortData() {
        Collections.sort(sessions, new Comparator<MXGroupChatSession>() {
            @Override
            public int compare(MXGroupChatSession lhs, MXGroupChatSession rhs) {
                if (lhs.isAMeet()) return -1;
                if (rhs.isAMeet()) return 1;
                if (lhs.getLastFeedTimeStamp() > rhs.getLastFeedTimeStamp()) return -1;
                return 0;
            }
        });
    }

    public void refreshData() {
        sessions = MXChatManager.getInstance().getGroupChatSessions();
        sortData();
        notifyDataSetChanged();
    }
}
