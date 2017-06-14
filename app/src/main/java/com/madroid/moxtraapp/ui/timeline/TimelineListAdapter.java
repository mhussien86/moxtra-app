package com.madroid.moxtraapp.ui.timeline;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.madroid.moxtraapp.R;
import com.madroid.moxtraapp.dtos.binders.BindersResponseDTO;
import com.moxtra.isdk.util.TextUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by mohamed on 16/01/17.
 */
public class TimelineListAdapter extends RecyclerView.Adapter<TimelineListAdapter.ContactViewHolder>{

    private final OnItemClickListener listener;
    private final Context context;

    private List<BindersResponseDTO.Binder> sessions ;

    public TimelineListAdapter(Context ctx, List<BindersResponseDTO.Binder> sessions, OnItemClickListener onItemClickListener) {
        this.listener = onItemClickListener ;
        this.sessions = sessions ;
        this.context = ctx ;
    }

    public interface OnItemClickListener {
        void onItemClick(BindersResponseDTO.Binder_ session);
    }


    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_item,parent,false);

        return new ContactViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {

        holder.bind(sessions.get(position).getSubBinder(), listener);
        BindersResponseDTO.Binder_ session  = sessions.get(position).getSubBinder();
        holder.contactName.setText(session.getName());
        holder.contactMail.setText(""+session.getLastFeed().getPublished());
//        if (!TextUtils.isEmpty(session.getBinder().getThumbnailUri())) {
//            holder.userImage.setImageURI(Uri.fromFile(new File(session.getBinder().getThumbnailUri())));
//        } else {
            // Get other member's avatar
//            if (session.getBinder().getRevision().) {
//                List<MXGroupChatMember> members = session.getMembers();
//                if (members != null && members.size() > 0) {
//                    String avatarPath = members.get(0).getAvatarPath();
                    if (!TextUtils.isEmpty(session.getThumbnailUri())) {
//                        Glide.with(context).load(new File(session.getThumbnailUri())).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.user_default_avatar).centerCrop().into(holder.userImage);

//                        Picasso.with(ChatListActivity.this).load(new File(avatarPath)).transform(new CircleTransform(getResources().getColor(R.color.blue_300))).into(theHolder.ivCover);
                    }
//                }
//            }
//        }
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

        @Bind(R.id.logo)
        CircleImageView userImage;

        public ContactViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bind(final BindersResponseDTO.Binder_ session, final TimelineListAdapter.OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(session);
                }
            });
        }
    }

//    private void sortData() {
//        Collections.sort(sessions, new Comparator<MXGroupChatSession>() {
//            @Override
//            public int compare(MXGroupChatSession lhs, MXGroupChatSession rhs) {
//                if (lhs.isAMeet()) return -1;
//                if (rhs.isAMeet()) return 1;
//                if (lhs.getLastFeedTimeStamp() > rhs.getLastFeedTimeStamp()) return -1;
//                return 0;
//            }
//        });
//    }
//
//    public void refreshData() {
//        sessions = MXChatManager.getInstance().getGroupChatSessions();
//        sortData();
//        notifyDataSetChanged();
//    }
}
