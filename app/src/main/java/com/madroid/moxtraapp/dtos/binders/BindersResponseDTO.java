package com.madroid.moxtraapp.dtos.binders;

import java.util.List;

/**
 * Created by mohamed on 13/06/17.
 */

public class BindersResponseDTO {


    private Data data;
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

        private String published;
        private String updated;
        private String objectType;
        private String displayName;
        private String id;
        private String image;
        private String uniqueId;
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

        private Integer category;
        private Binder_ binder;

        public Integer getCategory() {
            return category;
        }

        public void setCategory(Integer category) {
            this.category = category;
        }

        public Binder_ getBinder() {
            return binder;
        }

        public void setBinder(Binder_ binder) {
            this.binder = binder;
        }

    }

    public class Binder_ {

        private String id;
        private String name;
        private Integer createdTime;
        private Integer updatedTime;
        private Integer totalComments;
        private Integer totalMembers;
        private Integer totalPages;
        private Integer totalTodos;
        private Integer revision;
        private String thumbnailUri;
        private Boolean conversation;
        private List<User> users = null;
        private Boolean restricted;
        private Boolean team;
        private String description;
        private Integer feedsTimestamp;
        private String status;
        private LastFeed lastFeed;
        private String binderEmail;
        private java.lang.Object tags;
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

        public Integer getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(Integer createdTime) {
            this.createdTime = createdTime;
        }

        public Integer getUpdatedTime() {
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

        public Integer getFeedsTimestamp() {
            return feedsTimestamp;
        }

        public void setFeedsTimestamp(Integer feedsTimestamp) {
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

        private Integer unreadFeeds;
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

        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }


    }

    public class LastFeed {

        private String published;
        private Actor actor;
        private String verb;
        private Target target;
        private Generator generator;
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

        private String published;
        private String updated;
        private String objectType;
        private String id;
        private String displayName;
        private String status;
        private java.lang.Object recordingUrl;
        private String startTime;
        private String endTime;
        private String image;
        private String uniqueId;
        private String email;
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

        private String published;
        private String updated;
        private String objectType;
        private String id;
        private String displayName;
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

        private Integer revision;
        private String status;
        private String type;
        private Integer updatedTime;
        private Integer createdTime;
        private User_ user;
        private java.lang.Object team;
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

        public Integer getUpdatedTime() {
            return updatedTime;
        }

        public void setUpdatedTime(Integer updatedTime) {
            this.updatedTime = updatedTime;
        }

        public Integer getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(Integer createdTime) {
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

        private String id;
        private String email;
        private String name;
        private String pictureUri;
        private String phoneNumber;
        private String uniqueId;
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
