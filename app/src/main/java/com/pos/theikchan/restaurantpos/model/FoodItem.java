package com.pos.theikchan.restaurantpos.model;

/**
 * Created by Theik Chan on 6/8/2016.
 */
public class FoodItem {

    int mId;
    int mNo;
    String mName;
    String mPrice;
    String mImgLink;
    int mImg;
    int mCatId;
    boolean mIsOrder;
    int mQty;

    public FoodItem(){

    }

    public FoodItem(int mId, int mNo, String mName, String mPrice, String mImgLink, int mImg, int mCatId, boolean mIsOrder, int mQty) {
        this.mId = mId;
        this.mNo = mNo;
        this.mName = mName;
        this.mPrice = mPrice;
        this.mImgLink = mImgLink;
        this.mImg = mImg;
        this.mCatId = mCatId;
        this.mIsOrder = mIsOrder;
        this.mQty = mQty;
    }

    public int getmQty() {
        return mQty;
    }

    public void setmQty(int mQty) {
        this.mQty = mQty;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public int getmNo() {
        return mNo;
    }

    public void setmNo(int mNo) {
        this.mNo = mNo;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmPrice() {
        return mPrice;
    }

    public void setmPrice(String mPrice) {
        this.mPrice = mPrice;
    }

    public String getmImgLink() {
        return mImgLink;
    }

    public void setmImgLink(String mImgLink) {
        this.mImgLink = mImgLink;
    }

    public int getmImg() {
        return mImg;
    }

    public void setmImg(int mImg) {
        this.mImg = mImg;
    }

    public int getmCatId() {
        return mCatId;
    }

    public void setmCatId(int mCatId) {
        this.mCatId = mCatId;
    }

    public boolean ismIsOrder() {
        return mIsOrder;
    }

    public void setmIsOrder(boolean mIsOrder) {
        this.mIsOrder = mIsOrder;
    }
}
