package com.akofood.server.entity;

public class OrderCreateForm {
    private String name;
    private int totalPrice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}

//받고싶은 정보 더 받아서 넣어주기!!!