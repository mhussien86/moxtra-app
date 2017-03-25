package com.madroid.moxtraapp.dtos.categories;

import java.util.List;

/**
 * Created by mohamed on 24/03/17.
 */

public class AllCategoriesResponseDTO {


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String code ;
    public Data data ;

    public class Data {
        public int count ;

        public List<Category> getCategories() {
            return categories;
        }

        public void setCategories(List<Category> categories) {
            this.categories = categories;
        }

        public List<Category> categories ;
    }

    public class Category{

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String name ;
        public int id ;
    }
}
