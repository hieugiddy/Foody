package com.app.foody.Model;

public class Ver1 {
    private int resourceID;
    private String name;
    private String address;

    public Ver1(int resourceID, String name, String address) {
        this.resourceID = resourceID;
        this.name = name;
        this.address = address;
    }

    public int getResourceID() {
        return resourceID;
    }

    public void setResourceID(int resourceID) {
        this.resourceID = resourceID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
