package com.snynzmd.shop.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by z on 2018/3/8.
 */

public class Check {
    private String billNo;
    private String time;
    private String money;

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Check{" +
                "billNo='" + billNo + '\'' +
                ", time='" + time + '\'' +
                ", money='" + money + '\'' +
                '}';
    }
}
