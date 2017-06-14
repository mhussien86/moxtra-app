package com.madroid.moxtraapp.dtos.binders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mohamed on 13/06/17.
 */

public class BindersResponseDTO {


        @SerializedName("data")
        @Expose
        private Data data;
        @SerializedName("code")
        @Expose
        private String code;

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }



    public class Actor {

        @SerializedName("published")
        @Expose
        private String published;
        @SerializedName("updated")
        @Expose
        private String updated;
        @SerializedName("objectType")
        @Expose
        private String objectType;
        @SerializedName("displayName")
        @Expose
        private String displayName;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("unique_id")
        @Expose
        private String uniqueId;
        @SerializedName("email")
        @Expose
        private String email;

        public String getPublished() {
            return published;
        }

        public void setPublished(String published) {
            this.published = published;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }

        public String getObjectType() {
            return objectType;
        }

        public void setObjectType(String objectType) {
            this.objectType = objectType;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getUniqueId() {
            return uniqueId;
        }

        public void setUniqueId(String uniqueId) {
            this.uniqueId = uniqueId;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

    }

    public class Binder {

        @SerializedName("category")
        @Expose
        private int category;
        @SerializedName("binder")
        @Expose
        private Binder_ binder;

        public int getCategory() {
            return category;
        }

        public void setCategory(int category) {
            this.category = category;
        }

        public Binder_ getSubBinder() {
            return binder;
        }

        public void setSubBinder(Binder_ binder) {
            this.binder = binder;
        }

    }

    public class Binder_ {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("created_time")
        @Expose
        private long createdTime;
        @SerializedName("updated_time")
        @Expose
        private long updatedTime;
        @SerializedName("total_comments")
        @Expose
        private Integer totalComments;
        @SerializedName("total_members")
        @Expose
        private Integer totalMembers;
        @SerializedName("total_pages")
        @Expose
        private Integer totalPages;
        @SerializedName("total_todos")
        @Expose
        private Integer totalTodos;
        @SerializedName("revision")
        @Expose
        private Integer revision;
        @SerializedName("thumbnail_uri")
        @Expose
        private String thumbnailUri;
        @SerializedName("conversation")
        @Expose
        private Boolean conversation;
        @SerializedName("users")
        @Expose
        private List<User> users = null;
        @SerializedName("restricted")
        @Expose
        private Boolean restricted;
        @SerializedName("team")
        @Expose
        private Boolean team;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("feeds_timestamp")
        @Expose
        private long feedsTimestamp;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("last_feed")
        @Expose
        private LastFeed lastFeed;
        @SerializedName("binder_email")
        @Expose
        private String binderEmail;
        @SerializedName("tags")
        @Expose
        private java.lang.Object tags;
        @SerializedName("unread_feeds")
        @Expose
        private Integer unreadFeeds;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(long createdTime) {
            this.createdTime = createdTime;
        }

        public long getUpdatedTime() {
            return updatedTime;
        }

        public void setUpdatedTime(Integer updatedTime) {
            this.updatedTime = updatedTime;
        }

        public Integer getTotalComments() {
            return totalComments;
        }

        public void setTotalComments(Integer totalComments) {
            this.totalComments = totalComments;
        }

        public Integer getTotalMembers() {
            return totalMembers;
        }

        public void setTotalMembers(Integer totalMembers) {
            this.totalMembers = totalMembers;
        }

        public Integer getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(Integer totalPages) {
            this.totalPages = totalPages;
        }

        public Integer getTotalTodos() {
            return totalTodos;
        }

        public void setTotalTodos(Integer totalTodos) {
            this.totalTodos = totalTodos;
        }

        public Integer getRevision() {
            return revision;
        }

        public void setRevision(Integer revision) {
            this.revision = revision;
        }

        public String getThumbnailUri() {
            return thumbnailUri;
        }

        public void setThumbnailUri(String thumbnailUri) {
            this.thumbnailUri = thumbnailUri;
        }

        public Boolean getConversation() {
            return conversation;
        }

        public void setConversation(Boolean conversation) {
            this.conversation = conversation;
        }

        public List<User> getUsers() {
            return users;
        }

        public void setUsers(List<User> users) {
            this.users = users;
        }

        public Boolean getRestricted() {
            return restricted;
        }

        public void setRestricted(Boolean restricted) {
            this.restricted = restricted;
        }

        public Boolean getTeam() {
            return team;
        }

        public void setTeam(Boolean team) {
            this.team = team;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public long getFeedsTimestamp() {
            return feedsTimestamp;
        }

        public void setFeedsTimestamp(long feedsTimestamp) {
            this.feedsTimestamp = feedsTimestamp;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public LastFeed getLastFeed() {
            return lastFeed;
        }

        public void setLastFeed(LastFeed lastFeed) {
            this.lastFeed = lastFeed;
        }

        public String getBinderEmail() {
            return binderEmail;
        }

        public void setBinderEmail(String binderEmail) {
            this.binderEmail = binderEmail;
        }

        public java.lang.Object getTags() {
            return tags;
        }

        public void setTags(java.lang.Object tags) {
            this.tags = tags;
        }

        public Integer getUnreadFeeds() {
            return unreadFeeds;
        }

        public void setUnreadFeeds(Integer unreadFeeds) {
            this.unreadFeeds = unreadFeeds;
        }

    }

    public class Data {

        @SerializedName("unread_feeds")
        @Expose
        private Integer unreadFeeds;
        @SerializedName("binders")
        @Expose
        private List<Binder> binders = null;

        public Integer getUnreadFeeds() {
            return unreadFeeds;
        }

        public void setUnreadFeeds(Integer unreadFeeds) {
            this.unreadFeeds = unreadFeeds;
        }

        public List<Binder> getBinders() {
            return binders;
        }

        public void setBinders(List<Binder> binders) {
            this.binders = binders;
        }

    }



    public class Generator {

        @SerializedName("id")
        @Expose
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    }

    public class LastFeed {

        @SerializedName("published")
        @Expose
        private String published;
        @SerializedName("actor")
        @Expose
        private Actor actor;
        @SerializedName("verb")
        @Expose
        private String verb;
        @SerializedName("object")
        @Expose
        private Object object;
        @SerializedName("target")
        @Expose
        private Target target;
        @SerializedName("generator")
        @Expose
        private Generator generator;
        @SerializedName("id")
        @Expose
        private Integer id;

        public String getPublished() {
            return published;
        }

        public void setPublished(String published) {
            this.published = published;
        }

        public Actor getActor() {
            return actor;
        }

        public void setActor(Actor actor) {
            this.actor = actor;
        }

        public String getVerb() {
            return verb;
        }

        public void setVerb(String verb) {
            this.verb = verb;
        }

        public Object getObject() {
            return object;
        }

        public void setObject(Object object) {
            this.object = object;
        }

        public Target getTarget() {
            return target;
        }

        public void setTarget(Target target) {
            this.target = target;
        }

        public Generator getGenerator() {
            return generator;
        }

        public void setGenerator(Generator generator) {
            this.generator = generator;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

    }

    public class Object {

        @SerializedName("published")
        @Expose
        private String published;
        @SerializedName("updated")
        @Expose
        private String updated;
        @SerializedName("objectType")
        @Expose
        private String objectType;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("displayName")
        @Expose
        private String displayName;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("recording_url")
        @Expose
        private java.lang.Object recordingUrl;
        @SerializedName("start_time")
        @Expose
        private String startTime;
        @SerializedName("end_time")
        @Expose
        private String endTime;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("unique_id")
        @Expose
        private String uniqueId;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("url")
        @Expose
        private String url;

        public String getPublished() {
            return published;
        }

        public void setPublished(String published) {
            this.published = published;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }

        public String getObjectType() {
            return objectType;
        }

        public void setObjectType(String objectType) {
            this.objectType = objectType;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public java.lang.Object getRecordingUrl() {
            return recordingUrl;
        }

        public void setRecordingUrl(java.lang.Object recordingUrl) {
            this.recordingUrl = recordingUrl;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getUniqueId() {
            return uniqueId;
        }

        public void setUniqueId(String uniqueId) {
            this.uniqueId = uniqueId;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

    }


    public class Target {

        @SerializedName("published")
        @Expose
        private String published;
        @SerializedName("updated")
        @Expose
        private String updated;
        @SerializedName("objectType")
        @Expose
        private String objectType;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("displayName")
        @Expose
        private String displayName;
        @SerializedName("summary")
        @Expose
        private String summary;

        public String getPublished() {
            return published;
        }

        public void setPublished(String published) {
            this.published = published;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }

        public String getObjectType() {
            return objectType;
        }

        public void setObjectType(String objectType) {
            this.objectType = objectType;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

    }


    public class User {

        @SerializedName("revision")
        @Expose
        private Integer revision;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("updated_time")
        @Expose
        private long updatedTime;
        @SerializedName("created_time")
        @Expose
        private long createdTime;
        @SerializedName("user")
        @Expose
        private User_ user;
        @SerializedName("team")
        @Expose
        private java.lang.Object team;
        @SerializedName("notification_off")
        @Expose
        private Boolean notificationOff;

        public Integer getRevision() {
            return revision;
        }

        public void setRevision(Integer revision) {
            this.revision = revision;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public long getUpdatedTime() {
            return updatedTime;
        }

        public void setUpdatedTime(long updatedTime) {
            this.updatedTime = updatedTime;
        }

        public long getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(long createdTime) {
            this.createdTime = createdTime;
        }

        public User_ getUser() {
            return user;
        }

        public void setUser(User_ user) {
            this.user = user;
        }

        public java.lang.Object getTeam() {
            return team;
        }

        public void setTeam(java.lang.Object team) {
            this.team = team;
        }

        public Boolean getNotificationOff() {
            return notificationOff;
        }

        public void setNotificationOff(Boolean notificationOff) {
            this.notificationOff = notificationOff;
        }

    }


    public class User_ {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("picture_uri")
        @Expose
        private String pictureUri;
        @SerializedName("phone_number")
        @Expose
        private String phoneNumber;
        @SerializedName("unique_id")
        @Expose
        private String uniqueId;
        @SerializedName("org_id")
        @Expose
        private String orgId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPictureUri() {
            return pictureUri;
        }

        public void setPictureUri(String pictureUri) {
            this.pictureUri = pictureUri;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getUniqueId() {
            return uniqueId;
        }

        public void setUniqueId(String uniqueId) {
            this.uniqueId = uniqueId;
        }

        public String getOrgId() {
            return orgId;
        }

        public void setOrgId(String orgId) {
            this.orgId = orgId;
        }

    }
}
