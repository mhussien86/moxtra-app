package com.madroid.moxtraapp.dtos.categories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by omarmakeen on 6/23/17.
 */

public final class BindersAddCategoryRequestDTO {

    @SerializedName("binders")
    @Expose
    private List<Binder> binders = null;

    public List<Binder> getBinders() {
        return binders;
    }

    public void setBinders(List<Binder> binders) {
        this.binders = binders;
    }

    public class Binder {

        @SerializedName("binder")
        @Expose
        private Binder_ binder;

        public Binder_ getBinder() {
            return binder;
        }

        public void setBinder(Binder_ binder) {
            this.binder = binder;
        }

        public Binder(Binder_ binder){
            this.binder = binder;

        }

    }

    public class Binder_ {

        @SerializedName("id")
        @Expose
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Binder_(String id){
            this.id = id;

        }
    }
}
