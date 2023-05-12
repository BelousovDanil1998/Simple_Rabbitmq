package com.example.consumer;

import java.io.Serializable;

public class Home implements Serializable {
    private Integer floor;

    public Home() {
    }

    public Home(Integer floor) {
        this.floor = floor;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

}
