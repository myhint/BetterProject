package com.ad.wubo.betterproject;

/**
 * Created by Administrator on 2016/8/28.
 */
public class MessageEvent {

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MessageEvent(String address) {

        this.address = address;
    }
}
