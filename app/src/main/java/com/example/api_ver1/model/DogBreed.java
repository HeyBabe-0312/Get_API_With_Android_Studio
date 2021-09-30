package com.example.api_ver1.model;

import com.google.gson.annotations.SerializedName;

public class DogBreed {
    @SerializedName("id")
    private int id ;
    @SerializedName("name")
    private String name ;
    @SerializedName("life_span")
    private String lifeSpan ;
    @SerializedName("origin")
    private String origin ;
    @SerializedName("url")
    private String url ;
    @SerializedName("bred_for")
    private String bredFor ;
    private boolean tym = false;
    public DogBreed(int id , String name , String lifeSpan , String origin,String url,String bredFor, boolean fav){
        this.id = id ;
        this.name = name ;
        this.lifeSpan = lifeSpan ;
        this.origin = origin ;
        this.url = url;
        this.bredFor = bredFor;
        this.tym = fav;
    }

    public boolean getTym() {
        return this.tym;
    }
    public void setTym(boolean tym) {
        this.tym = tym;
    }
    public String getBredFor(){
        return this.bredFor;
    }
    public void setId (int id) {
        this.id = id ;
    }
    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name  ;
    }
    public void setName(String name ) {
        this.name = name ;
    }
    public void setLifeSpan(String lifeSpan) {
        this.lifeSpan = lifeSpan ;
    }
    public String getLifeSpan() {
        return this.lifeSpan ;
    }
    public String getOrigin() {
        return this.origin;
    }
    public void setOrigin(String origin) {
        this.origin = origin ;
    }
    public String getUrl() {return this.url;}

}