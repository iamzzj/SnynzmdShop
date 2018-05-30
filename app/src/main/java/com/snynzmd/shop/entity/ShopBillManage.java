package com.snynzmd.shop.entity;

/**
 * Created by z on 2018/3/12.
 */

public class ShopBillManage {
    private BillType billType;

    public ShopBillManage(BillType billType) {
        this.billType = billType;
    }

    public BillType getBillType() {
        return billType;
    }

    public void setBillType(BillType billType) {
        this.billType = billType;
    }

    @Override
    public String toString() {
        return "ShopBillManage{" +
                "billType=" + billType +
                '}';
    }

    public enum BillType { ShopNormalBill,ShopPresellBill,ShopNakedPriceBill,ShopActivityBill}
}
