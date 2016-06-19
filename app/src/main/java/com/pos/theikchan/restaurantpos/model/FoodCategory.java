package com.pos.theikchan.restaurantpos.model;

/**
 * Created by Theik Chan on 6/8/2016.
 */
public class FoodCategory {

    int mId;
    int mNo;
    String mName;

    public FoodCategory(){

    }

    public FoodCategory(int mId, int mNo, String mName) {
        this.mId = mId;
        this.mNo = mNo;
        this.mName = mName;
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
}
