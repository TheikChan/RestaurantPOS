package com.pos.theikchan.restaurantpos.model;

/**
 * Created by Theik Chan on 6/12/2016.
 */
public class OrderItem {
    int mNo;
    int mOrderNo;
    int mTableNo;
    String mProdName;
    int mQty;
    int mPrice;
    int img;

    public OrderItem(){

    }

    public OrderItem(int mNo, int mOrderNo, int mTableNo, String mProdName, int mQty, int mPrice) {
        this.mNo = mNo;
        this.mOrderNo = mOrderNo;
        this.mTableNo = mTableNo;
        this.mProdName = mProdName;
        this.mQty = mQty;
        this.mPrice = mPrice;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getmNo() {
        return mNo;
    }

    public void setmNo(int mNo) {
        this.mNo = mNo;
    }

    public int getmOrderNo() {
        return mOrderNo;
    }

    public void setmOrderNo(int mOrderNo) {
        this.mOrderNo = mOrderNo;
    }

    public int getmTableNo() {
        return mTableNo;
    }

    public void setmTableNo(int mTableNo) {
        this.mTableNo = mTableNo;
    }

    public String getmProdName() {
        return mProdName;
    }

    public void setmProdName(String mProdName) {
        this.mProdName = mProdName;
    }

    public int getmQty() {
        return mQty;
    }

    public void setmQty(int mQty) {
        this.mQty = mQty;
    }

    public int getmPrice() {
        return mPrice;
    }

    public void setmPrice(int mPrice) {
        this.mPrice = mPrice;
    }
}
