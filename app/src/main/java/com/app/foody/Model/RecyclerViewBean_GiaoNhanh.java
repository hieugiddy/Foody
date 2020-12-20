package com.app.foody.Model;

public class RecyclerViewBean_GiaoNhanh {
    int image;
    String course;

    public RecyclerViewBean_GiaoNhanh(){

    }
    public RecyclerViewBean_GiaoNhanh(int image, String course){
        super();
        this.image = image;
        this.course = course;
    }
    public int getImage(){
        return image;
    }
    public String getCourse(){
        return course;
    }
    public void setImage(int image){
        this.image = image;
    }
    public void setCourse(String course){
        this.course = course;
    }
}
