package com.pos.theikchan.restaurantpos.model;

import java.io.Serializable;

public class TableItem implements Serializable {
    private int id;//table no
    private int tableNo;
    private int state;//table state busy or free
    private String mState;

    public int getTableNo() {
        return tableNo;
    }

    public void setTableNo(int tableNo) {
        this.tableNo = tableNo;
    }

    public String getmState() {
        return mState;
    }

    public void setmState(String mState) {
        this.mState = mState;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
